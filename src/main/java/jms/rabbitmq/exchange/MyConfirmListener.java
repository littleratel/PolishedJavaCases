package jms.rabbitmq.exchange;

import java.io.IOException;
import com.rabbitmq.client.ConfirmListener;

/**
 * 生产者发送消息到exchange成功的回调方法
 * 消息被Exchange接受以后，如果没有匹配的Queue，则会被丢弃。但是可以设置ReturnListener监听来监听有没有匹配的队列。
 * 因此handleAck执行了，并不能完全表示消息已经进入了对应的队列，只能表示对应的exchange成功的接收了消息。
 * 消息被exchange接收过后，还需要通过一定的匹配规则分发到对应的队列queue中。
 */
public class MyConfirmListener implements ConfirmListener {

	@Override
	public void handleAck(long deliveryTag, boolean multiple) throws IOException {
		// deliveryTag是broker给消息指定的唯一id（从1开始）
		System.out.println("Exchange接收Ack, deliveryTag=" + deliveryTag + ",multiple=" + multiple);
	}

	/**
	 * 生产者发送消息到服务器broker失败的回调方法，服务器丢失了此消息。
	 * 注意，丢失的消息仍然可以传递给消费者，但broker不能保证这一点。（不明白，既然丢失了，为啥还能发送）
	 */
	@Override
	public void handleNack(long deliveryTag, boolean multiple) throws IOException {
		System.out.println("Exchange接收Nack：deliveryTag=" + deliveryTag + ",服务器broker丢失了消息!");
	}

}
