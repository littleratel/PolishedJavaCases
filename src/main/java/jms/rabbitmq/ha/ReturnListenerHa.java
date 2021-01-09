package jms.rabbitmq.ha;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.ReturnListener;
import org.springframework.amqp.utils.SerializationUtils;

import java.io.IOException;

/**
 * mandatory
 * 当mandatory标志位设置为true时，如果exchange根据routeKey无法找到一个符合条件的queue，
 * 那么会调用basic.return方法将消息返回给生产者（Basic.Return + Content-Header + Content-Body）；
 * 当mandatory设置为false时，出现上述情形broker会直接将消息扔掉。
 * <p>
 * Exchange执行handleAck，并不能完全表示消息已经进入了对应的队列，只能表示对应的exchange成功的接收了消息。
 * 可以设置ReturnListener监听来有没有匹配的队列。
 */
public class ReturnListenerHa implements ReturnListener {

    @Override
    public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                             BasicProperties properties, byte[] body) {

        String result = new StringBuilder()
                .append("ReturnListenerHa get exception msg:\n")
                .append("replyCode: " + replyCode)
                .append(",\nreplyText: " + replyText)
                .append(",\nexchange: " + exchange)
                .append(",\nroutingKey: " + routingKey)
                .append(",\nproperties: " + properties.toString())
                .append(",\nbody: " +  new String(body))
                .toString();

        System.out.println(result);
    }

}
