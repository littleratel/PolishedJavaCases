package jms.rabbitmq.rpc;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class FunctionSvc {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "queue_rpc_request";
    private String replyQueueName = "queue_rpc_reply";
    
    public FunctionSvc() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");

        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(replyQueueName, false, false, false, null);
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();
        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
        String ctag = channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });

        String result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] argv) {
        FunctionSvc client = null;
        String response = null;
        try {
            client = new FunctionSvc();

            for (int i = 10; i < 16; i++) {
                String i_str = Integer.toString(i);
                System.out.println(" [x] Requesting fib(" + i_str + ")");
                response = client.call(i_str);
                System.out.println(" [FunctionSvc] Got '" + response + "'");
            }
        }
        catch  (IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if (client!= null) {
                try {
                    client.close();
                }
                catch (IOException _ignore) {}
            }
        }
    }
}