package socket.nio.demo4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
	// 管道管理器
	private Selector selector;

	public NioClient init(String serverIp, int port) throws IOException {
		// 获取socket通道
		SocketChannel channel = SocketChannel.open();

		channel.configureBlocking(false);
		// 获得通道管理器
		selector = Selector.open();

		// 客户端连接服务器，需要调用channel.finishConnect();才能实际完成连接。
		channel.connect(new InetSocketAddress(serverIp, port));
		// 为该通道注册SelectionKey.OP_CONNECT事件
		channel.register(selector, SelectionKey.OP_CONNECT);
		return this;
	}

	public void listen() throws IOException {
		System.out.println("客户端启动");
		// 轮询访问selector
		while (true) {
			// 选择注册过的io操作的事件(第一次为SelectionKey.OP_CONNECT)
			selector.select();
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

			while (ite.hasNext()) {
				SelectionKey selectionKey = ite.next();
				ite.remove();// 删除已选的key，防止重复处理

				if (selectionKey.isConnectable()) {
					SocketChannel channel = (SocketChannel) selectionKey.channel();

					// 如果正在连接，则完成连接
					if (channel.isConnectionPending()) {
						channel.finishConnect();
					}

					channel.configureBlocking(false);
					// 向服务器发送消息
					channel.write(ByteBuffer.wrap(new String("send message to server.").getBytes()));

					// 连接成功后，注册接收服务器消息的事件
					channel.register(selector, SelectionKey.OP_READ);
					System.out.println("客户端连接成功");
				} else if (selectionKey.isReadable()) { // 有可读数据事件。
					
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					// 创建读取数据缓冲器
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					ByteBuffer responseBuffer = ByteBuffer.allocate(1024);

					int read = channel.read(buffer);
					if (read > 0) {
						byte[] data = buffer.array();
						String message = new String(data);
						System.out.println("receive msg from client, size:" + buffer.position() + " msg: " + message);

						buffer.flip();
						responseBuffer.put(buffer);
					}
					if (read == -1) {
						System.out.println("Server socket closed!");
						channel.close();
						return;
					}

					selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
				}//end if
			} // end while
		}//while (true) 
	}

	public static void main(String[] args) throws IOException {
		new NioClient().init("127.0.0.1", 9981).listen();
	}
}
