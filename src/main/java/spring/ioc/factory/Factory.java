package spring.ioc.factory;

public interface Factory<T, R> {

	/**
	 * Creates a new equipment instance
	 * 
	 * @param resource
	 *            the configuration data used as factory input
	 * @return Equipment instance of type T
	 */
	public T create(R resourceData);
}
