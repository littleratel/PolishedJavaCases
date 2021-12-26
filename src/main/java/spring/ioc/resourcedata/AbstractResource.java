package spring.ioc.resourcedata;

public abstract class AbstractResource implements ConfigurationData {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class<? extends ConfigurationData> getType() {
		// This code returns the first interface in the same package as this class that
		// extends ConfigurationData
		Class[] interfaces = getClass().getInterfaces();
		for (Class iface : interfaces) {
			if (iface.getPackage().equals(AbstractResource.class.getPackage())
					&& ConfigurationData.class.isAssignableFrom(iface)) {
				return iface;
			}
		}
		throw new IllegalStateException(
				"Unable to find any resource interface implemented by this class: " + getClass().getName());
	}
}
