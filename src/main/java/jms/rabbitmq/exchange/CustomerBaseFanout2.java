package jms.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.DirectExchange;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class CustomerBaseFanout2 {
	private static final String EXCHANGE = "Exchange-ezfanbi";

	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
	    // 设置RabbitMQ所在主机ip或者主机名
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE, "fanout");

		// 产生一个随机的队列名称
		String routingKey = "";
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE, routingKey);// 对队列进行绑定

		System.out.println("Customer-2 waiting for messages");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				int tagId =(int) envelope.getDeliveryTag();
				
				if (tagId>2 && tagId <7 ) {
					System.out.println("Customer-1 received [" + message + "].");
					channel.basicAck(tagId, false);
				} 
				else {
					// 通知服务器消息处理失败,重新放回队列,false表示处理失败消息不放会队列，直接删除
					channel.basicReject(tagId, false);
				}
			}
		};
		// 配合手动确认消息
		channel.basicConsume(queueName, false, consumer);
	}
}
