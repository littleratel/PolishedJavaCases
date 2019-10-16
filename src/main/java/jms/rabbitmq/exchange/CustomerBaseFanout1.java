package jms.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class CustomerBaseFanout1 {
	private static final String EXCHANGE_NAME = "Exchange-ezfanbi";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("guest");
		factory.setPassword("guest");
		factory.setHost("127.0.0.1");
		factory.setVirtualHost("/");
		factory.setPort(5672);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		// 产生一个随机的队列名称
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");// 对队列进行绑定

		System.out.println("Customer-1 waiting for messages");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				int tagId =(int) envelope.getDeliveryTag();
				if (tagId % 2 == 0) {
					System.out.println("Customer-1 received [" + message + "].");
					// 手动确认，通知服务器此消息已经被处理
					channel.basicAck(tagId, false);
				} else {
					// 通知服务器消息处理失败,重新放回队列,false表示处理失败消息不放会队列，直接删除
					channel.basicReject(tagId, false);
				}
			}
		};

		/**
		 * false--关闭自动确认, 手动确认消息; true--设置自动回复，队列会自动删除
		 */
		boolean autoAck = false;
		channel.basicConsume(queueName, autoAck, consumer);
	}
}
