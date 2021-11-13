package designpattern.decorator.decorators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import designpattern.decorator.Decorator;
import throwable.exceptions.NonExistantDecoratorException;

/**
 * Factory of decorators.
 */
public final class Decorators {

	private static Map<Class<? extends Decorator>, Decorator> decorators = new HashMap<Class<? extends Decorator>, Decorator>();
	private static Set<Class<? extends Decorator>> decoratorClasses = new HashSet<Class<? extends Decorator>>();

	// Scanning all classes inherited from Decorator under Decorators's package
	static {
		String packageName = Decorators.class.getPackage().getName();
		Reflections refle = new Reflections(packageName);
		decoratorClasses = refle.getSubTypesOf(Decorator.class);
	}

	private Decorators() {
	}

	/**
	 * Get the Decorator instance based on the input class
	 * 
	 * @param clazz
	 */
	public static Decorator getDecorator(Class<? extends Decorator> clazz) {
		// throw exception if clazz is not existence in decoratorClasses
		if (!decoratorClasses.contains(clazz)) {
			throw new NonExistantDecoratorException(clazz.getName() + " is not existence.");
		}

		if (!decorators.containsKey(clazz)) {
			Decorator decorator = null;
			try {
				decorator = clazz.newInstance();
				decorators.put(clazz, decorator);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return decorator;
		} else {
			return decorators.get(clazz);
		}
	}
}
