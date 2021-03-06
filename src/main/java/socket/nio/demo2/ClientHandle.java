package socket.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientHandle implements Runnable {
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean started;

	public ClientHandle(String ip, int port) {
		this.host = ip;
		this.port = port;
		init();
	}

	private void init() {
		try {
			// 创建选择器
			selector = Selector.open();
			// 打开监听通道
			socketChannel = SocketChannel.open();
			// 如果为 true，则此通道将被置于阻塞模式；如果为 false，则此通道将被置于非阻塞模式
			socketChannel.configureBlocking(false);//开启非阻塞模式
			started = true;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop() {
		started = false;
	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// 循环遍历selector
		while (started) {
			try {
				// 无论是否有读写事件发生，selector每隔1s被唤醒一次
				selector.select(1000);
				// 阻塞,只有当至少一个注册的事件发生的时候才会继续.
				// selector.select();
				Set<SelectionKey> keySet = selector.selectedKeys();
				Iterator<SelectionKey> it = keySet.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();//删除已选的key，防止重复处理
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		// selector关闭后会自动释放里面管理的资源
		if (selector != null)
			try {
				selector.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private void handleInput(SelectionKey selectionKey) throws IOException {
		if (selectionKey.isValid()) {
			SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

			if (selectionKey.isConnectable()) {
				if (socketChannel.finishConnect())
					;
				else
					System.exit(1);
			}

			// 读消息
			if (selectionKey.isReadable()) {
				// 创建ByteBuffer，并开辟一个1M的缓冲区
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				// 读取请求码流，返回读取到的字节数
				int readBytes = socketChannel.read(buffer);
				// 读取到字节，对字节进行编解码
				if (readBytes > 0) {
					// 将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
					buffer.flip();
					// 根据缓冲区可读字节数创建字节数组
					byte[] bytes = new byte[buffer.remaining()];
					// 将缓冲区可读字节数组复制到新建的数组中
					buffer.get(bytes);
					String result = new String(bytes, "UTF-8");
					System.out.println("[Client][" + Thread.currentThread().getName() + "]Get result: " + result);
				} else if (readBytes < 0) {// 没有读取到字节 忽略
					// 链路已经关闭，释放资源
					selectionKey.cancel();
					socketChannel.close();
				}
			}
		}
	}

	public void sendMsg(String msg) throws Exception {
		socketChannel.register(selector, SelectionKey.OP_READ);
		doWrite(socketChannel, msg);
	}

	// 异步发送消息
	private void doWrite(SocketChannel channel, String request) throws IOException {
		// 将消息编码为字节数组
		byte[] bytes = request.getBytes();
		// 根据数组容量创建ByteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		// 将字节数组复制到缓冲区
		writeBuffer.put(bytes);
		// flip操作
		writeBuffer.flip();
		// 发送缓冲区的字节数组
		channel.write(writeBuffer);
		// ****此处不含处理“写半包”的代码
	}

	private void doConnect() throws IOException {
		if (!socketChannel.connect(new InetSocketAddress(host, port)))
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}
}
