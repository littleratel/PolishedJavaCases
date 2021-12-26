package socket.rpc.provider;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class RpcProvider {
	// 存储注册的服务列表
	private static List<Object> serviceList;

	/**
	 * 发布rpc服务
	 * 
	 * @param object
	 * @param port
	 * @throws Exception
	 */
	public static void export(int port, Object... services) throws Exception {
		serviceList = Arrays.asList(services);
		ServerSocket server = new ServerSocket(port);
		Socket client = null;
		while (true) {
			// 阻塞，等待连接
			client = server.accept();
			// 每一个请求，启动一个线程处理
			new Thread(new ServerThread(client, serviceList)).start();
		}
	}
}
