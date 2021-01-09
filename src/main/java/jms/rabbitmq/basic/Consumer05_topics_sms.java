package jms.rabbitmq.basic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import jms.rabbitmq.dlx.ConsumerHandle;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer05_topics_sms {
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_TOPICS_INFORM="Exchange_EZFANBI";
    private static final String[] ROUTINGKEY_SMS = {"inform.#.sms.#"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_INFORM_SMS,true,true,false,null);
        channel.exchangeDeclare(EXCHANGE_TOPICS_INFORM, BuiltinExchangeType.TOPIC);
        // 绑定路由
        for (String routingKey : ROUTINGKEY_SMS) {
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_TOPICS_INFORM, routingKey);
        }

        channel.basicConsume(QUEUE_INFORM_SMS,true,new ConsumerHandle(channel));
    }
}
