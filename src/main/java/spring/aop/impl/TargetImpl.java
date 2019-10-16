package spring.aop.impl;

import org.springframework.stereotype.Component;
import spring.aop.Target;

@Component
public class TargetImpl implements Target {

	public void print() {
		System.out.println(this.getClass().getSimpleName() + ".print().");
	}

	public void doPrint() {
		System.out.println(this.getClass().getSimpleName() + "doPrint() is called.");
	}
}
