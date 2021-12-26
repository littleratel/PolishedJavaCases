package spring.ioc.unit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a method in FactoryProvider.
 */
public class ResourceProviderMethod {

	private final Class<?> resourceClass;
	private final String methodName;
	private final Class<?> resourceDataClass;

	/**
	 * Creates method representation.
	 * 
	 * @param methodName
	 * @param resourceClass
	 *            resource class which factory represented by this method creates
	 * @param resourceDataClass
	 *            resource data class used by this factory
	 */
	public ResourceProviderMethod(String methodName, Class<?> resourceClass, Class<?> resourceDataClass) {
		this.methodName = methodName;
		this.resourceClass = resourceClass;
		this.resourceDataClass = resourceDataClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public Class<?> getResourceDataClass() {
		return resourceDataClass;
	}

	public Class<?> getResourceClass() {
		return resourceClass;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(methodName).append(resourceClass).append(resourceDataClass).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		ResourceProviderMethod other = (ResourceProviderMethod) obj;
		return new EqualsBuilder().append(methodName, other.methodName).append(resourceClass, other.resourceClass)
				.append(resourceDataClass, other.resourceDataClass).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
