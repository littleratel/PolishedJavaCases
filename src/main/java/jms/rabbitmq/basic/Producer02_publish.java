package jms.rabbitmq.basic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer02_publish {
    private static final String QUEUE_EMAIL = "queue_ps_email";
    private static final String QUEUE_SMS = "queue_ps_sms";
    private static final String EXCHANGE_FANOUT = "EXCHANGE_FANOUT";

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
            //创建会话通道,生产者和mq服务所有通信都在channel通道中完成
            channel = connection.createChannel();

            /**
             * 声明队列，如果队列在mq 中没有则要创建
             * 参数：String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
             * 1、queue 队列名称
             * 2、durable 是否持久化，如果持久化，mq重启后队列还在
             * 3、exclusive 是否独占连接，队列只允许在该连接中访问，如果connection连接关闭队列则自动删除,如果将此参数设置true可用于临时队列的创建
             * 4、autoDelete 自动删除，当最后一个消费者退订后即被删除.如果将此参数和exclusive参数设置为true就可以实现临时队列（队列不用了就自动删除）
             * 5、arguments 参数，可以设置一个队列的扩展参数，比如：可设置存活时间
             */
            channel.queueDeclare(QUEUE_EMAIL, true, false, false, null);
            channel.queueDeclare(QUEUE_SMS, true, false, false, null);

            /**
             * 声明一个交换机
             * 参数：String exchange, String type
             * 1、交换机的名称
             * 2、交换机的类型
             * fanout：无路由交换机, 它和路由键routingKey没有关系，设不设置routingKey效果一样;
             *         对应的 Rabbitmq 的工作模式是 publish/subscribe,
             * direct：对应的 Routing 工作模式
             * topic：对应的 Topics 工作模式
             * headers： 对应的 Headers 工作模式
             */
            channel.exchangeDeclare(EXCHANGE_FANOUT, BuiltinExchangeType.FANOUT);

            /**
             * 进行交换机和队列绑定
             * String queue, String exchange, String routingKey
             * 1、queue 队列名称
             * 2、exchange 交换机名称
             * 3、routingKey 路由key，作用是交换机根据路由key的值将消息转发到指定的队列中，在发布订阅模式中调协为空字符串
             */
            channel.queueBind(QUEUE_EMAIL, EXCHANGE_FANOUT, "");
            channel.queueBind(QUEUE_SMS, EXCHANGE_FANOUT, "");

            /**
             * 发送消息
             * 参数：String exchange, String routingKey, BasicProperties props, byte[] body
             * 1、exchange，交换机，如果不指定将使用mq的默认交换机（设置为""）
             * 2、routingKey，路由key，交换机根据路由key来将消息转发到指定的队列，如果使用默认交换机，routingKey设置为队列的名称
             * 3、props, 消息的属性, 如 message持久化 MessageProperties.PERSISTENT_TEXT_PLAIN
             * 4、body，消息内容
             */
            for (int i = 0; i < 5; i++) {
                String message = "[Producer02 MSG]";
                channel.basicPublish(EXCHANGE_FANOUT, "", null, message.getBytes());
                System.out.println("send to mq " + message);

                Thread.sleep(3000);
            }

            //关闭连接, 先关闭通道
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
