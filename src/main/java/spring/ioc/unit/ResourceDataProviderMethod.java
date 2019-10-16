package spring.ioc.unit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a method in StpResource.
 */
public class ResourceDataProviderMethod {

	protected final String methodName; //getG2RbsList
	private final Class<?> resourceDataClass; //Method 返回值中对应的ResourceData Class

	/**
	 * Creates method representation.
	 * 
	 * @param methodName
	 * @param resourceDataClass
	 *            resource data class returned by this method
	 */
	public ResourceDataProviderMethod(String methodName, Class<?> resourceDataClass) {
		this.methodName = methodName;
		this.resourceDataClass = resourceDataClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public Class<?> getResourceDataClass() {
		return resourceDataClass;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(methodName).append(resourceDataClass).toHashCode();
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
		ResourceDataProviderMethod other = (ResourceDataProviderMethod) obj;
		return new EqualsBuilder().append(methodName, other.methodName)
				.append(resourceDataClass, other.resourceDataClass).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
