package jms.rabbitmq.pushget;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    private static final String QUEUE = "queue_push_get";
    private static final String EXCHANGE = "exchange_push_get";
    private static final String[] ROUTINGKEY = {"info.email"};

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE, true, false, false, null);
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.TOPIC,true);
            channel.basicQos(3,true);

            for (String routingKey : ROUTINGKEY) {
                channel.queueBind(QUEUE, EXCHANGE, routingKey);
            }

            /**
             * 参数：String exchange, String routingKey, BasicProperties props, byte[] body
             * 参数明细：
             * 1、exchange，交换机，如果不指定将使用mq的默认交换机（设置为""）
             * 2、routingKey，路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
             * 3、props，消息的属性
             * 4、body，消息内容
             */
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                    contentEncoding("UTF-8").build();
            for (int i = 1; i < 20; i++) {
                String message = "Email MSG to user_" + i;
                channel.basicPublish(EXCHANGE, "info.email", properties, message.getBytes());
                System.out.println("Producer send msg to mq: " + message);
            }

            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
