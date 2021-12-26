package jms.rabbitmq.exchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    private static final String EXCHANGE = "Exchange-EZFANBI";

    public static void main(String[] args) {
//		 testExchangeFanout();
//		testExchangeFanoutBaseDefinedConfirm();
        testExchangeDirect();
//        testExchangeTopics();
    }

    private static void testExchangeFanout() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.confirmSelect();

            // fanout--分发，所有的消费者得到同样的队列信息
            channel.exchangeDeclare(EXCHANGE, "fanout");
            String routingKey = "";

            for (int i = 1; i < 10; i++) {
                String message = "MSG: " + i;
                channel.basicPublish(EXCHANGE, routingKey, null, message.getBytes());
                if (channel.waitForConfirms()) {
                    System.out.println("MSG sent successful. '" + message + "'");
                } else {
                    System.out.println("MSG sent failed!!! with MSG '" + message + "'");
                }
                Thread.sleep(1000);
            }

            channel.close();
            connection.close();
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * fanout -- 确认模式<br>
     *
     * <pre>
     * 也可以通过声明事务的方式来实现
     * channel.txSelect(); // 声明事务
     * channel.basicPublish(EXCHANGE, routingKey, null, message.getBytes());
     * channel.txCommit(); // 提交事务
     * </pre>
     */
    private static void testExchangeFanoutBaseDefinedConfirm() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        String routingKey = "";

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, "fanout");

            // 开启确认模式
            channel.confirmSelect();
            // 添加自定义确认
            channel.addConfirmListener(new MyConfirmListener());
            channel.addReturnListener(new MyReturnListener());

            Map<String, Object> map = new HashMap<>();
            map.put("domain", "sekilx1183.wrbs.rnd.ki.sw.ericsson.se");
            map.put("type", ".txt");
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .headers(map)
                    .build();

            for (int i = 1; i < 10; i++) {
                String message = "MSG: " + i;
                channel.basicPublish(EXCHANGE, routingKey, true, properties, message.getBytes());
                System.out.println("Producer send:  [" + message + "].");
                Thread.sleep(3000);
            }

            // 直到所有信息都发布，只要有一个未确认就会IOException
            // channel.waitForConfirmsOrDie();
            // System.out.println("全部执行完成");

            channel.close();
            connection.close();
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // direct
    private static void testExchangeDirect() {
        // 路由关键字，Direct模式需要路由键完全匹配
        final String[] routingKeys = new String[]{"info", "warning", "error"};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        String queueName = "Queue_hw_1";
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            // 声明交换机, 注意是direct, 这里durable=false
            channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(queueName, false, false, false, null);

            // 根据路由关键字进行绑定
            for (String routingKey : routingKeys) {
                channel.queueBind(queueName, EXCHANGE, routingKey);
                System.out.println("Customer1 queue:" + queueName + ", BindRoutingKey:" + routingKey);
            }

            // 开启确认模式
            channel.confirmSelect();
            // 添加自定义确认
            channel.addConfirmListener(new MyConfirmListener());
            channel.addReturnListener(new MyReturnListener());

            String message;
            for (String routingKey : routingKeys) {
                for (int i = 1; i < 5; i++) {
                    message = "MSG: " + routingKey + " and num " + i;
                    channel.basicPublish(EXCHANGE, routingKey, null, message.getBytes());
                    System.out.println("Producer send [" + routingKey + "] '" + message + "'");
                    Thread.sleep(1000);
                }
            }

            channel.close();
            connection.close();
        } catch (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // topic
    private static void testExchangeTopics() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE, "topic");
            //
            String[] routingKeys = new String[]{"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
                    "lazy.brown.fox", "quick.brown.fox", "quick.orange.male.rabbit", "lazy.orange.male.rabbit"};
            // 发送消息
            String message;
            for (String routingKey : routingKeys) {
                message = "MSG is " + routingKey;
                System.out.println("Producer sent msg '" + message + "', which is form [" + routingKey + "].");
                channel.basicPublish(EXCHANGE, routingKey, null, message.getBytes());
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (TimeoutException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}