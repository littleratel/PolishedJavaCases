package jms.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生成者
 */
public class Producer {
	public static final String QUEUE_NAME = "RabbitMQ-ezfanbi";
	private static final String EXCHANGE = "Exchange-ezfanbi";
	private static final String vHost = "vHost-ezfanbi";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setVirtualHost(vHost);

		// 创建连接和通道
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE, "fanout");

		/**
		 * 声明一个队列, queueDeclare 
		 * 第一个参数表示队列名称 
		 * 第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）;
		 * 第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除） 
		 * 第四个参数为当所有消费者客户端连接断开时是否自动删除队列
		 * 第五个参数为队列的其他参数
		 */
		channel.queueDeclare(QUEUE_NAME, false, false, true, null);

		for (int i = 1; i < 20; i++) {
			String message = "MSG: " + i;
			/**
			 * basicPublish 
			 * 第一个参数为交换机名称; 
			 * 第二个参数为队列映射的路由key;
			 * 第三个参数为消息的其他属性;
			 * 第四个参数为发送信息的主体
			 */
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println("Producer Send +'" + message + "'");
			Thread.sleep(5000);
		}

		channel.close();
		connection.close();
	}

}