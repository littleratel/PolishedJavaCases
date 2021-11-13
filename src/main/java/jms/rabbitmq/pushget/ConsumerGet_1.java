package jms.rabbitmq.pushget;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ConsumerGet_1 {
    private static final String QUEUE = "queue_push_get";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        for (int i = 0; i < 100; i++) {
            GetResponse response = channel.basicGet(QUEUE, false);
            if (response == null) {
                TimeUnit.SECONDS.sleep(1);
                continue;
            }

            String data = new String(response.getBody());
            long msgId = response.getEnvelope().getDeliveryTag();
            System.out.println("Consumer_1 get: " + data + "! msgId: " + msgId);
            TimeUnit.SECONDS.sleep(2);
            channel.basicAck(msgId, false);
//            System.out.println("========> Consumer_1 send basicAck.");
        }

        channel.close();
        connection.close();
    }
}
