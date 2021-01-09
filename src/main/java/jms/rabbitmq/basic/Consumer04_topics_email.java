package jms.rabbitmq.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jms.rabbitmq.dlx.ConsumerHandle;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer04_topics_email {
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String EXCHANGE_TOPICS_INFORM = "Exchange_EZFANBI";
    private static final String ROUTINGKEY_EMAIL = "inform.#.email.#";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
//        channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
//        channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_TOPICS_INFORM, ROUTINGKEY_EMAIL);

        channel.basicConsume(QUEUE_INFORM_EMAIL, true, new ConsumerHandle(channel));
    }
}
