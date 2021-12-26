package jms.rabbitmq.exchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class CustomerBaseDirect2 {
	private static final String QUEUE = "Queue_hw_2";
	private static final String EXCHANGE = "Exchange-ezfanbi";
	// 路由关键字
	private static final String[] routingKeys = new String[] { "info", "warning" };

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE, "direct");
		channel.queueDeclare(QUEUE,false,true,false,null);

		// 根据路由关键字进行多重绑定
		for (String severity : routingKeys) {
			channel.queueBind(QUEUE, EXCHANGE, severity);
			System.out.println("Customer2 queue:" + QUEUE + ", BindRoutingKey:" + severity);
		}
		
		System.out.println("Customer2 Waiting for messages!");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException  {
				String message = new String(body, "UTF-8");
				int tagId =(int) envelope.getDeliveryTag();
				System.out.println("Customer2 get [" + envelope.getRoutingKey() + "]: '" + message + "'");
				channel.basicAck(tagId, false);
			}
		};

		boolean autoAck = false;
		channel.basicConsume(QUEUE, autoAck, consumer);
	}
}