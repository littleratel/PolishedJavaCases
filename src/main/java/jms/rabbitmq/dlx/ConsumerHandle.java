package jms.rabbitmq.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import jms.rabbitmq.dlx.AckType;

import java.io.IOException;

public class ConsumerHandle extends DefaultConsumer {

    private final AckType ackType;

    public ConsumerHandle(Channel channel) {
        this(channel, AckType.NONE);
    }

    public ConsumerHandle(Channel channel, AckType ackType) {
        super(channel);
        this.ackType = ackType;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//        String exchange = envelope.getExchange();
//        long deliveryTag = envelope.getDeliveryTag();
        System.err.println("-----------consume message----------");
        System.err.println("consumerTag: " + consumerTag);
        System.err.println("envelope: " + envelope);
        System.err.println("properties: " + properties);
        System.err.println("body: " + new String(body));

        long tagId = envelope.getDeliveryTag();
        switch (ackType){
            case ACK:
                getChannel().basicAck(tagId, false);
                break;
            case NACK:
                // nack并且不重新入队列，该消息会从MQ中删除
                getChannel().basicNack(tagId, false,false);
                break;
            case REJECT:
                getChannel().basicReject(tagId,false);
                break;
            case NONE:

        }
    }
}