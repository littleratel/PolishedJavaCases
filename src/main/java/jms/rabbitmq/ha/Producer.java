package jms.rabbitmq.ha;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final String EXCHANGE = "EXCHANGE-HA";
    private static final String[] ROUTING_KEYS = new String[]{"info", "warning", "error"};
    private static final String QUEUE_NAME = "queue_ha";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT, true, false, null);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // binding routing key
            channel.queueBind(QUEUE_NAME, EXCHANGE, "info");
            channel.queueBind(QUEUE_NAME, EXCHANGE, "error");

            // 开启确认模式
            channel.confirmSelect();
            // 添加自定义确认
            channel.addConfirmListener(new ConfirmListenerHa());

            channel.addReturnListener(new ReturnListenerHa());

            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                    contentEncoding("UTF-8").build();

            String message;
            boolean mandatory = true;
            for (String routingKey : ROUTING_KEYS) {
                for (int i = 1; i < 3; i++) {
                    message = "Send to routingKey: " + routingKey + ", id: " + i;
                    channel.basicPublish(EXCHANGE, routingKey, mandatory, properties, message.getBytes());
                    System.out.println("Producer send [" + routingKey + "] '" + message + "'");
                }
            }

//            channel.close();
//            connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

}