package designpattern.decorator.components;

import designpattern.decorator.Component;

public class ComponentA implements Component{

	@Override
	public void send(String cmd) {
		System.out.println("It's from core " + this.getClass().getSimpleName() + " deal the cmd: " + cmd);
	}

}
