package designpattern.decorator.decorators;

import designpattern.decorator.Decorator;

public class DecoratorB extends Decorator {
	@Override
	public void send(String cmd) {
		System.out.println(this.getClass().getSimpleName()+" before decorate the send.");
		super.send(cmd);
		System.out.println(this.getClass().getSimpleName()+" after decorate the send.");
	}
}
