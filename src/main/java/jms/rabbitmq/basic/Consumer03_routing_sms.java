package jms.rabbitmq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jms.rabbitmq.dlx.ConsumerHandle;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer03_routing_sms {
    private static final String QUEUE_INFORM_SMS = "queue_sms";
    private static final String EXCHANGE_ROUTING = "EXCHANGE_ROUTING";
    private static final String ROUTINGKEY_SMS = "routing_key_sms";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume(QUEUE_INFORM_SMS, true, new ConsumerHandle(channel));

        //关闭连接, 先关闭通道
        channel.close();
        connection.close();
    }
}
