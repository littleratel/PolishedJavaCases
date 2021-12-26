package jms.rabbitmq.basic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Producer06_headers {
    private static final String EXCHANGE_HEADER = "EXCHANGE_HEADER";
    private static final String QUEUE_HEADER_SMS = "queue_header_sms";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;

        Map<String, Object> headers = new HashMap<>();
        headers.put("health", "Nice");
        headers.put("mentality", "Good");
        headers.put("x-match", "all"); // all, any

        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_HEADER, BuiltinExchangeType.HEADERS);
            channel.queueDeclare(QUEUE_HEADER_SMS, true, false, false, null);

            // set routingKey "" instead of null, even if routingKey is no need for Header mode.
            channel.queueBind(QUEUE_HEADER_SMS, EXCHANGE_HEADER, "", headers);

            // 生成发送消息的属性
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .headers(headers)
                    .build();
            String message = "This is the message that producer really wants to deliver.";
            channel.basicPublish(EXCHANGE_HEADER, "", props, message.getBytes("UTF-8"));
            System.out.println("Producer_headers send: " + message);

            //
            testAnotherHeader(channel);
            testAnotherHeader2(channel);

            //关闭连接, 先关闭通道
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // anotherHeader have
    private static void testAnotherHeader(Channel channel) throws IOException {
        Map<String, Object> anotherHeader = new HashMap<>();
        anotherHeader.put("health", "Nice");
        anotherHeader.put("mentality", "Good");
        anotherHeader.put("adult", "False");
        anotherHeader.put("x-match", "all"); // all, any

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .headers(anotherHeader)
                .build();

        String message = "Message base the head: " + anotherHeader.toString();
        channel.basicPublish(EXCHANGE_HEADER, "", props, message.getBytes("UTF-8"));
        System.out.println("Producer_headers is : " + anotherHeader.toString());
    }

    private static void testAnotherHeader2(Channel channel) throws IOException {
        Map<String, Object> anotherHeader = new HashMap<>();
        anotherHeader.put("health", "Nice");
        anotherHeader.put("adult", "False");
        anotherHeader.put("x-match", "all"); // all, any

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .headers(anotherHeader)
                .build();

        String message = "Message base the head: " + anotherHeader.toString();
        channel.basicPublish(EXCHANGE_HEADER, "", props, message.getBytes("UTF-8"));
        System.out.println("Producer_headers is : " + anotherHeader.toString());
    }
}
