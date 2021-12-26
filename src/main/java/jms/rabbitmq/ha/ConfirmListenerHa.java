package jms.rabbitmq.ha;

import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

public class ConfirmListenerHa implements ConfirmListener {

	@Override
	public void handleAck(long deliveryTag, boolean multiple) throws IOException {
		// deliveryTag是broker给消息指定的唯一id（从1开始）
		System.out.println("Ack from Exchange, deliveryTag=" + deliveryTag + ",multiple=" + multiple);
	}

	/**
	 * 生产者发送消息到服务器broker失败的回调方法，服务器丢失了此消息。
	 * 注意，丢失的消息仍然可以传递给消费者，但broker不能保证这一点。（不明白，既然丢失了，为啥还能发送）
	 */
	@Override
	public void handleNack(long deliveryTag, boolean multiple) throws IOException {
		System.out.println("Nack from Exchange, deliveryTag=" + deliveryTag + ",服务器broker丢失了消息!");
	}

}
