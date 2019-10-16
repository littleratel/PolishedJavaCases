package jms.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Customer {
	private final static String QUEUE_NAME = "RabbitMQ-ezfanbi";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");

		// 创建一个新的连接,通道
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 声明要关注的队列
		// channel.queueDeclare(QUEUE_NAME, false, false, true, null);
		System.out.println("Customer Waiting Received messages");

		// DefaultConsumer类实现了Consumer接口，通过传入一个频道，
		// 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
		Consumer consumer = new DefaultConsumer(channel) {
			/**
			 * envelope主要存放生产者相关信息,比如交换机、路由key等; 
			 * body是消息实体;
			 */
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Customer received '" + message + "' from " + envelope.getRoutingKey());
			}
		};
		
		/**
		 * 自动回复队列应答 -- RabbitMQ中的消息确认机制;
		 * autoAck,第2个参数，表示是否自动回复，如果为true的话，每次生产者只要发送信息就会从内存中删除；
		 * 那么如果消费者程序异常退出，那么就无法获取数据，当然我们不希望这样，所以需要手动回复，每当消费者收到并处理信息然后再通知生成者，最后从队列中删除这条信息。
		 * 如果消费者异常退出，如果还有其他消费者，那么就会把队列中的消息发送给其他消费者，如果没有，等消费者启动时候再次发送。
		 */
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}