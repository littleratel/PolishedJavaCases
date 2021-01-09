package jms.rabbitmq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Producer {

    // Normal Exchange and Queue
    private static String exchangeNormal = "exchange_dlx_normal";
    private static String queueNormal = "queue_dlx_normal";
    private static String routingKey = "dlx.#";

    // Dead Exchange and Queue
    private static String EXCHANGE_DLX = "exchange_dlx";
    private static String QUEUE_DLX = "queue_dlx";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        // Dead Exchange and Queue
        channel.exchangeDeclare(EXCHANGE_DLX, BuiltinExchangeType.TOPIC, true, false, null);
        channel.queueDeclare(QUEUE_DLX, true, false, false, null);
        channel.queueBind(QUEUE_DLX, EXCHANGE_DLX, routingKey);


        //Dead Msg Exchange
        Map<String, Object> agruments = new HashMap<>();
        agruments.put("x-message-ttl", 10000); // 消息生存时间,以队列超期时间为主
        agruments.put("x-dead-letter-exchange", EXCHANGE_DLX);
        agruments.put("x-dead-letter-routing-key", "dlx.test");

        // Normal Exchange and Queue
        channel.exchangeDeclare(exchangeNormal, BuiltinExchangeType.TOPIC, true, false, null);
        channel.queueDeclare(queueNormal, true, false, false, agruments);
        channel.queueBind(queueNormal, exchangeNormal, routingKey);

        // send msg
        String msg = "Message of RabbitMQ";
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 1: nonpersistent 2: persistent
                .contentEncoding("UTF-8")
                .expiration("5000") // can also declare it in agruments
                .build();

        System.out.println("Producer send mag...");
        channel.basicPublish(exchangeNormal, routingKey, true, properties, msg.getBytes());
    }

}
