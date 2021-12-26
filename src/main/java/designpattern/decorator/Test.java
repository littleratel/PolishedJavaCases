package designpattern.decorator;

import designpattern.decorator.components.ComponentA;
import designpattern.decorator.components.Components;
import designpattern.decorator.decorators.DecoratorA;
import designpattern.decorator.decorators.DecoratorB;
import designpattern.decorator.decorators.DecoratorC;
import designpattern.decorator.decorators.Decorators;

public class Test {

	public static void main(String[] args) {
		testAddDecoratorInExecutionOrder();
		// testRemoveInnerDecoratorWithPara();
		// testRemoveInnerDecorator();
	}

	private static void testAddDecoratorInExecutionOrder() {
		ComponentBuilder builder = new ComponentBuilder();

		builder.setComponent(Components.getComponent(ComponentA.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorA.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorB.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorC.class));

		ExtendedComponent cmp = (ExtendedComponent) builder.build();
		cmp.send("Hello word!");

		System.out.println("=========== getInnerDecorator ===========");
		ExtendedComponent inner = cmp.getInnerDecorator();
		System.out.println("the core is " + inner.getClass().getSimpleName());
	}

	private static void testRemoveInnerDecoratorWithPara() {
		ComponentBuilder builder = new ComponentBuilder();
		builder.setComponent(Components.getComponent(ComponentA.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorA.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorB.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorC.class));
		ExtendedComponent cmp = (ExtendedComponent) builder.build();
		cmp.send("Hello word!");

		System.out.println("=========== 1st ===========");
		cmp.removeInnerDecorator(Decorators.getDecorator(DecoratorB.class));
		cmp.send("Hello word!");

		System.out.println("=========== 2nd ===========");
		cmp.removeInnerDecorator(Decorators.getDecorator(DecoratorB.class));
		cmp.send("Hello word!");
	}

	private static void testRemoveInnerDecorator() {
		ComponentBuilder builder = new ComponentBuilder();

		builder.setComponent(Components.getComponent(ComponentA.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorA.class));
		builder.addDecoratorInExecutionOrder(Decorators.getDecorator(DecoratorC.class));

		ExtendedComponent cmp = (ExtendedComponent) builder.build();
		cmp.send("Hello word!");

		System.out.println("=========== 1st ===========");
		cmp.removeInnerDecorator();
		cmp.send("Hello word!");
		System.out.println("=========== 2nd ===========");
		cmp.removeInnerDecorator();
		cmp.send("Hello word!");

		// will throw exception
		System.out.println("=========== 3rd ===========");
		cmp.removeInnerDecorator();
	}

}
