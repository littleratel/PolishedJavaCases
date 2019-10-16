package socket.nio.demo4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {

	// 通道管理器
	private Selector selector;

	// 获取一个ServerSocket通道，并初始化通道
	public NioServer init(int port) throws IOException {
		// 获取一个ServerSocket通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		// 获取通道管理器
		selector = Selector.open();
		// 将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
		// 只有当该事件到达时，Selector.select()会返回，否则一直阻塞。
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		return this;
	}

	public void listen() throws IOException {
		System.out.println("服务器端启动成功");

		int ready = 0;
		// 使用轮询访问selector
		while (true) {
			// 当有注册的事件到达时，方法返回，否则阻塞。
			ready = selector.select();
			if (ready == 0) {
				continue;
			} else if (ready < 0) {
				break;
			}

			// 获取selector中的迭代器，选中项为注册的事件
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

			while (ite.hasNext()) {
				SelectionKey key = ite.next();
				// 删除已选key，防止重复处理
				ite.remove();

				if (key.isAcceptable()) {// 客户端请求连接事件
					ServerSocketChannel server = (ServerSocketChannel) key.channel();

					// 获得客户端连接通道
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);

					// 向客户端发消息
					channel.write(ByteBuffer.wrap(new String("Server send message to client").getBytes()));

					// 在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。
					channel.register(selector, SelectionKey.OP_READ);

					System.out.println("客户端请求连接事件");
				} else if (key.isReadable()) {// 有可读数据事件
					// 获取客户端传输数据可读取消息通道
					SocketChannel channel = (SocketChannel) key.channel();

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

					key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

				} else if (key.isWritable()) { // 写事件
					ByteBuffer buffer = ((ByteBuffer) key.attachment());
					buffer.flip();
					((SocketChannel) key.channel()).write(buffer);

					if (!buffer.hasRemaining()) {
						key.attach(null);
						key.interestOps(SelectionKey.OP_READ);
					}
				} // end if
			} // while (ite.hasNext())
		} // while (true)
	}

	public static void main(String[] args) throws IOException {
		new NioServer().init(9981).listen();
	}
}