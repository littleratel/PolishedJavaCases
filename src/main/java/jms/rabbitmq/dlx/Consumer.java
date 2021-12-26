package jms.rabbitmq.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer {

    private static String QUEUE_NORMAL = "queue_dlx_normal";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        /**
         * 消费者在接收到队列里的消息,但没有返回确认结果之前,它不会将新的消息分发给它
         * */
        channel.basicQos(1);

        // autoAck is false + Reject
        channel.basicConsume(QUEUE_NORMAL, false, new ConsumerHandle(channel, AckType.NACK));
    }
}
