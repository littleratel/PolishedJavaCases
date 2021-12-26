package jms.rabbitmq.basic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Random;

public class Producer03_direct {
    private static final String QUEUE_INFORM_EMAIL = "queue_email";
    private static final String QUEUE_INFORM_SMS = "queue_sms";
    private static final String EXCHANGE_ROUTING = "EXCHANGE_ROUTING";
    private static final String ROUTINGKEY_EMAIL = "routing_key_email";
    private static final String ROUTINGKEY_SMS = "routing_key_sms";

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

            /**
             * 声明队列，如果队列在mq 中没有则要创建
             * 1、queue 队列名称
             * 2、durable 是否持久化，如果持久化，mq重启后队列还在
             * 3、exclusive 是否独占连接，队列只允许在该连接中访问，如果connection连接关闭队列则自动删除,如果将此参数设置true可用于临时队列的创建
             * 4、autoDelete 自动删除，队列不再使用时是否自动删除此队列，如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
             * 5、arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
             */
            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, null);
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);

            //声明交换机
            //参数：String exchange, String type
            /**
             * 参数明细：
             * 1、交换机的名称
             * 2、交换机的类型
             * fanout：对应的rabbitmq的工作模式是 publish/subscribe
             * direct：对应的Routing工作模式
             * topic：对应的Topics工作模式
             * headers： 对应的headers工作模式
             */
            channel.exchangeDeclare(EXCHANGE_ROUTING, BuiltinExchangeType.DIRECT);
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTING, ROUTINGKEY_EMAIL);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING, ROUTINGKEY_SMS);

            // channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING, ROUTINGKEY_EMAIL);

            //发送消息
            //参数：String exchange, String routingKey, BasicProperties props, byte[] body
            /**
             * 参数明细：
             * 1、exchange，交换机，如果不指定将使用mq的默认交换机（设置为""）
             * 2、routingKey，路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
             * 3、props，消息的属性
             * 4、body，消息内容
             */
            Random r = new Random();
            int num;

            for (int i = 0; i < 10; i++) {
                num = r.nextInt(100) + 1;
                if (num % 2 == 0) {
                    String message = "[Email message!]";
                    channel.basicPublish(EXCHANGE_ROUTING, ROUTINGKEY_EMAIL, null, message.getBytes());
                    System.out.println("Producer03 send to mq " + message);
                    continue;
                }
                String message = "[Sms inform message]";
                channel.basicPublish(EXCHANGE_ROUTING, ROUTINGKEY_SMS, null, message.getBytes());
                System.out.println("send to mq " + message);

                Thread.sleep(2000);
            }

            //关闭连接, 先关闭通道
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
