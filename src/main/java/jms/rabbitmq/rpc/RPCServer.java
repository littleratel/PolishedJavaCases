package jms.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RPCServer {
	private static final String QUEUE_NAME = "RPC-ezfanbi";

	private static int fib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}

		return fib(n - 1) + fib(n - 1);
	}

	public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
		// 连接，通道，队列
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		/**
		 * Qos服务质量保证,即在非自动确认消息的前提下,如果一定数目的消息未被确认前,不再消费新的消息。
		 * 
		 * 试想一下，如果1个消费者1分钟最多处理60条消息，生产者1分钟可发300条消息，
		 * 1台消费者客户端，如果在1分钟内要接收到300条消息，已经超过它的最大负载，此时就可能导致，服务器资源被耗尽，消费者客户端卡死等情况。
		 * 
		 * 我们可能运行多个服务器进程，为了分散负载服务器压力，设置channel.basicQos(1);
		 * 
		 */
		channel.basicQos(1);
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, false, consumer);

		System.out.println("Server wating RPC request");

		QueueingConsumer.Delivery delivery;
		BasicProperties props;
		BasicProperties replyProps;
		String message;
		while (true) {
			delivery = consumer.nextDelivery();
			props = delivery.getProperties();
			replyProps = new AMQP.BasicProperties.Builder().correlationId(props.getCorrelationId()).build();

			message = new String(delivery.getBody(), "UTF-8");
			System.out.println("Server received client request fib(" + message + ")");
			
			String result = String.valueOf( fib(Integer.parseInt(message)));
			// 往指定的queue中推送消息，由replyTo获取
			channel.basicPublish("", props.getReplyTo(), replyProps, result.getBytes());
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}