package jms.rabbitmq.exchange;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class CustomerBaseDirect1 {
    private static final String QUEUE = "Queue_hw_1";
    private static final String EXCHANGE = "Exchange-EZFANBI";
    private static final String[] routingKeys = new String[]{"info", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(QUEUE, false, false, false, null);

        // 根据路由关键字进行绑定
        for (String routingKey : routingKeys) {
            channel.queueBind(QUEUE, EXCHANGE, routingKey);
            System.out.println("Customer1 queue:" + QUEUE + ", BindRoutingKey:" + routingKey);
        }

        System.out.println("Customer1  Waiting for messages!");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                int tagId = (int) envelope.getDeliveryTag();
                String message = new String(body, "UTF-8");

                if (tagId > 0 && tagId <= 3) {
                    System.out.println("Customer-1 received [" + message + "].");
                    // 手动确认，通知服务器此消息已经被处理
                    channel.basicAck(tagId, false);
                } else if (tagId > 3 && tagId <= 6) {
                    //basicNack支持一次拒绝0个或多个消息，并且也可以设置是否requeue。
                    channel.basicNack(tagId, false, false);
                } else {
                    // basicReject 一次只能拒绝接收一个消息
                    // 通知服务器消息处理失败,重新放回队列,false表示处理失败消息不放会队列，直接删除
                    channel.basicReject(tagId, false);
                }
            }
        };

        boolean autoAck = false;
        channel.basicConsume(QUEUE, autoAck, consumer);
    }
}