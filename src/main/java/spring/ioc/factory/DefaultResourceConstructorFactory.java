package spring.ioc.factory;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import throwable.exceptions.ResourceConfigurationException;
import spring.ioc.resourcedata.ConfigurationData;

/**
 * 根据class的全限定名创建该class的实例
 * 
 */
public class DefaultResourceConstructorFactory<T, R extends ConfigurationData> implements Factory<T, R> {

	private final Class<? extends T> implementationClass;

	public DefaultResourceConstructorFactory(Class<? extends T> implementationClass) {
		this.implementationClass = implementationClass;
	}

	@Override
	public T create(R resourceData) {
		final Object obj = new Object();
		final Class<?>[] argsClass = findConstructorParameterTypes(implementationClass, resourceData, obj, obj);
		final Object[] args = new Object[] { resourceData };
		final String errorMsg = "Class: " + implementationClass.getSimpleName() + " with resource id "
				+ resourceData.getId() + " could not be instantiated";

		try {
			final Constructor<? extends T> constructor = implementationClass.getDeclaredConstructor(argsClass);
			constructor.setAccessible(true);
			return constructor.newInstance(args);
		} catch (Exception e) {
			throw new ResourceConfigurationException(errorMsg, e);
		}
	}

	private Class<?>[] findConstructorParameterTypes(Class<?> clazz, Object... args) {

		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			boolean matchingArgs = false;
			for (int i = 0; i < parameterTypes.length; i++) {
				if (parameterTypes[i].isAssignableFrom(args[i].getClass())) {
					matchingArgs = true;
				} else {
					matchingArgs = false;
					break;
				}
			}

			if (matchingArgs) {
				return parameterTypes;
			}
		}

		throw new ResourceConfigurationException(
				"Did not find any constructor matching the provided arguments: " + Arrays.asList(args));
	}
}
