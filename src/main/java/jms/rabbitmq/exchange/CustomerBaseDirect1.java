package jms.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class CustomerBaseDirect1 {
	private static final String EXCHANGE = "Exchange-ezfanbi";
	private static final String[] routingKeys = new String[] { "info", "error" };

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE, "direct");//Exchange Type : "direct" 1:1
		
		// 获取匿名队列名称
		String queueName = channel.queueDeclare().getQueue();
		
		// 根据路由关键字进行绑定
		for (String routingKey : routingKeys) {
			channel.queueBind(queueName, EXCHANGE, routingKey);
			System.out.println("Customer1 queue:" + queueName + ", BindRoutingKey:" + routingKey);
		}
		
		System.out.println("Customer1  Waiting for messages!");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				
				int tagId =(int) envelope.getDeliveryTag();
				String message = new String(body, "UTF-8");
				System.out.println("Customer1 get [" + envelope.getRoutingKey() + "]: '" + message + "'");
				channel.basicAck(tagId, false);
			}
		};
		
		boolean autoAck = false;
		channel.basicConsume(queueName, autoAck, consumer);
	}
}