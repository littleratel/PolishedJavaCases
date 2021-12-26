package designpattern.decorator;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ComponentBuilder {

	private final Deque<Decorator> decoratorStack = new ArrayDeque<>();
	private DecoratorCore cmp;

	public ComponentBuilder addDecoratorInExecutionOrder(final Decorator decorator) {
		decoratorStack.push(decorator);
		return this;
	}

	// set the core
	public void setComponent(Component cmp) {
		this.cmp = new DecoratorCore(cmp);
	}

	public Component build() {
		if (cmp == null) {
			System.out.println("throw Exception(\"Component is null!\")");
			return cmp;
		}

		// 装饰该 Component Core
		return decorateComponent(cmp);
	}

	private ExtendedComponent decorateComponent(ExtendedComponent cmp) {
		while (!decoratorStack.isEmpty()) {
			Decorator dec = decoratorStack.pop();
			cmp.addInnerDecorator(dec);
		}
		
		return cmp;
	}

}
