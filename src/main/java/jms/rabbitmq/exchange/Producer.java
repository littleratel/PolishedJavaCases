package jms.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	private static final String EXCHANGE_NAME = "Exchange-ezfanbi";

	public static void main(String[] args) {
		// testExchangeFanout();
		testExchangeFanoutBaseDefinedConfirm();
		// testExchangeDirect();
		// testExchangeTopics();
	}

	private static void testExchangeFanout() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setHost("127.0.0.1");
		factory.setVirtualHost("/");
		factory.setPort(5672);

		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.confirmSelect();

			// fanout--分发，所有的消费者得到同样的队列信息
			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

			for (int i = 1; i < 10; i++) {
				String message = "MSG: " + i;
				channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
				if (channel.waitForConfirms()) {
					System.out.println("MSG sent successful. '" + message + "'");
				} else {
					System.out.println("MSG sent failed!!! with MSG '" + message + "'");
				}
				Thread.sleep(1000);
			}

			// 也可以设置批量确认
			// channel.waitForConfirmsOrDie();

			channel.close();
			connection.close();
		} catch (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// fanout
	private static void testExchangeFanoutBaseDefinedConfirm() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setHost("127.0.0.1");
		factory.setVirtualHost("/");
		factory.setPort(5672);

		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			// 开启确认模式
			channel.confirmSelect();
			// 添加自定义确认
			channel.addConfirmListener(new MyConfirmListener());
			channel.addReturnListener(new MyReturnListener());

			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

			for (int i = 1; i < 10; i++) {
				String message = "MSG: " + i;
				channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
				Thread.sleep(1000);
			}

			channel.close();
			connection.close();
		} catch (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// direct
	private static void testExchangeDirect() {
		// 路由关键字，Direct模式需要路由键完全匹配
		final String[] routingKeys = new String[] { "info", "warning", "error" };
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			// 声明交换机, 注意是direct, 这里durable=false
			channel.exchangeDeclare(EXCHANGE_NAME, "direct", false);

			String message;
			for (String routingKey : routingKeys) {
				for (int i = 1; i < 6; i++) {
					message = "MSG: " + routingKey + " and num " + i;
					channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
					System.out.println("Producer send [" + routingKey + "] '" + message + "'");
					Thread.sleep(1000);
				}
			}
			channel.close();
			connection.close();
		} catch (IOException | TimeoutException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// topic
	private static void testExchangeTopics() {
		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");
			//
			String[] routingKeys = new String[] { "quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
					"lazy.brown.fox", "quick.brown.fox", "quick.orange.male.rabbit", "lazy.orange.male.rabbit" };
			// 发送消息
			String message;
			for (String routingKey : routingKeys) {
				message = "MSG is " + routingKey;
				System.out.println("Producer sent msg '" + message + "', which is form [" + routingKey + "].");
				channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (channel != null) {
					channel.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (TimeoutException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}