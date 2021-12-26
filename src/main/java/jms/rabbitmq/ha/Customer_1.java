package jms.rabbitmq.ha;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer_1 {
    private static final String QUEUE_NAME = "queue_ha";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        // 消费者接收到队列里的消息,但没有确认之前,将不会分发给它新的消息
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                int tagId = (int) envelope.getDeliveryTag();
                String message = new String(body, "UTF-8");

                if (tagId > 0 && tagId <= 3) {
                    System.out.println("Customer received [" + tagId + " : " + message + "], Ack");
                    // 手动确认，通知服务器此消息已经被处理
//                    channel.basicAck(tagId, false);
                } else if (tagId > 3 && tagId <= 10) {
                    System.out.println("Customer received [" + tagId + " : " + message + "], Nack");
                    //basicNack支持一次拒绝0个或多个消息，并且也可以设置是否requeue。
                    channel.basicNack(tagId, false, true);
                } else {
                    System.out.println("Customer received [" + tagId + " : " + message + "], Reject");
                    // 通知服务器消息处理失败,重新放回队列,false表示处理失败消息不放会队列，直接删除
                    channel.basicReject(tagId, false);
                }
            }
        };

        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}