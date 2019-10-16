package jms.rabbitmq.rpc;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class RPCClient {
	private final String requestQueueName = "RPC-ezfanbi";
	private Connection connection;
	private Channel channel;
	private String replyQueueName;
	private QueueingConsumer consumer;

	public RPCClient() {
		init();
	}

	private void init() {
		// 建立一个连接和通道，并声明了一个唯一的“回调”队列的答复
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			
			replyQueueName = channel.queueDeclare().getQueue();
			consumer = new QueueingConsumer(channel);
			channel.basicConsume(replyQueueName, true, consumer);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}

	public String call(int message) throws IOException, InterruptedException {
		String response;
		String corrID = UUID.randomUUID().toString();
		AMQP.BasicProperties props = new AMQP.BasicProperties().builder().correlationId(corrID).replyTo(replyQueueName)
				.build();
		channel.basicPublish("", requestQueueName, props, String.valueOf(message).getBytes("UTF-8"));
		
		QueueingConsumer.Delivery delivery;
		while (true) {
			delivery = consumer.nextDelivery();
			if (delivery.getProperties().getCorrelationId().equals(corrID)) {
				response = new String(delivery.getBody(), "UTF-8");
				break;
			}
		}
		return response;
	}

	public void close() throws Exception {
		connection.close();
	}

	public static void main(String[] args) throws Exception {
		RPCClient rpcClient = new RPCClient();
		String response;
		try {
			for (int i = 2; i < 21; i += 2) {
				System.out.println("Client requesting fib(" + i + ")");
				response = rpcClient.call(i);
				System.out.println("Client  Got '" + response + "'");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rpcClient != null) {
				rpcClient.close();
			}
		}
	}
}