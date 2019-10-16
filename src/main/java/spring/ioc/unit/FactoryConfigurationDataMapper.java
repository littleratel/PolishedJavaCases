package spring.ioc.unit;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import exceptions.ResourceManagerException;
import spring.ioc.factory.Factory;
import spring.ioc.resource.ResourceProvider;
import spring.ioc.resourcedata.ConfigurationData;
import spring.ioc.resourcedata.ResourceDataProvider;

public class FactoryConfigurationDataMapper {

	private final static Class<?> FACTORY = Factory.class;
	private final static Class<?> COLLECTION = Collection.class;
	private final static Class<?> CONFIGURATION_DATA = ConfigurationData.class;
	private static final Class<?> RESOURCE_PROVIDER_CLASS = ResourceProvider.class;
	private static final Class<?> RESOURCE_DATA_PROVIDER = ResourceDataProvider.class;

	private final static int RESOURCE_ORDER = 0;
	private final static int RESOURCE_DATA_ORDER = 1;
	private boolean isCached;

	private List<ResourceProviderMethod> resourceProviderMethods;
	private List<ResourceDataProviderMethod> resourceDataProviderMethods;

	public List<Pair<ResourceProviderMethod, ResourceDataProviderMethod>> mapResourceToResourceDataFor(Class<?> clazz) {
		cacheResults();

		// 获取与 Resource class 相匹配的 ResourceProviderMethods
		Set<ResourceProviderMethod> matchedResourceProviderMethods = findResourceProviderMethodMatchWith(clazz);
		if (matchedResourceProviderMethods.isEmpty()) {
			matchedResourceProviderMethods.addAll(findResourceProviderMethodMatchSubResource(clazz));
		}

		// findResourceDataProviderMethodUsedByFactories
		List<ResourceDataProviderMethod> resourceDataMethods = getResourceDataMethodUsedByFactories(
				matchedResourceProviderMethods);
		return mapFactoryMethodToStpResourceProviderMethod(matchedResourceProviderMethods, resourceDataMethods);
	}

	private List<ResourceDataProviderMethod> getResourceDataMethodUsedByFactories(
			Set<ResourceProviderMethod> resourceProviderMethods) {
		// 将 resourceProviderMethods 中的属性 ResourceDataClass 取出组成一个 set
		Set<Class<?>> resourceDataClasses = resourceProviderMethods.stream()
				.map(ResourceProviderMethod::getResourceDataClass).collect(Collectors.toSet());

		return resourceDataProviderMethods.stream().filter(
				rdm -> doesResouceClassIsExactlyOneOfClassesInSet(rdm.getResourceDataClass(), resourceDataClasses))
				.collect(Collectors.toList());
	}

	private boolean doesResouceClassIsExactlyOneOfClassesInSet(Class<?> resourceClass, Set<Class<?>> searchedSet) {
		return searchedSet.contains(resourceClass);
	}

	//
	private List<Pair<ResourceProviderMethod, ResourceDataProviderMethod>> mapFactoryMethodToStpResourceProviderMethod(
			Set<ResourceProviderMethod> resourceMethods, List<ResourceDataProviderMethod> resourceDataMethods) {
		List<Pair<ResourceProviderMethod, ResourceDataProviderMethod>> mapping = new ArrayList<>();
		for (ResourceDataProviderMethod resourceDataMethod : resourceDataMethods) {
			Optional<ResourceProviderMethod> possibleFactory = mapExactlyResourceDataToFactory(resourceDataMethod,
					resourceMethods);
			if (possibleFactory.isPresent()) {
				ResourceProviderMethod factoryMethod = possibleFactory.get();
				mapping.add(ImmutablePair.of(factoryMethod, resourceDataMethod));
			} else {
				ResourceProviderMethod factoryMethod = findAnyFactoryWhichCanUseResourceData(resourceDataMethod,
						resourceMethods);
				mapping.add(ImmutablePair.of(factoryMethod, resourceDataMethod));
			}
		}
		return mapping;
	}

	private Optional<ResourceProviderMethod> mapExactlyResourceDataToFactory(
			ResourceDataProviderMethod resourceDataMethod, Set<ResourceProviderMethod> resourceMethods) {
		for (ResourceProviderMethod factoryMethod : resourceMethods) {
			if (factoryMethod.getResourceDataClass().equals(resourceDataMethod.getResourceDataClass())) {
				return Optional.of(factoryMethod);
			}
		}
		return Optional.empty();
	}

	private ResourceProviderMethod findAnyFactoryWhichCanUseResourceData(ResourceDataProviderMethod resourceDataMethod,
			Set<ResourceProviderMethod> resourceMethods) {
		for (ResourceProviderMethod factoryMethod : resourceMethods) {
			if (factoryMethod.getResourceDataClass().isAssignableFrom(resourceDataMethod.getResourceDataClass())) {
				return factoryMethod;
			}
		}

		throw new ResourceManagerException(
				"Could not find a factory to create resources from: " + resourceDataMethod.getResourceDataClass());
	}

	private void cacheResults() {
		if (!isCached) {
			resourceProviderMethods = getResourceProviderMethods();
			resourceDataProviderMethods = getResourceDataProviderMethods();
			isCached = true;
		}
	}

	/**
	 * 获取 RESOURCE_PROVIDER_CLASS 所有Method 并构造Method对应的 ResourceProviderMethod
	 */
	private List<ResourceProviderMethod> getResourceProviderMethods() {
		Method[] allMethods = RESOURCE_PROVIDER_CLASS.getMethods();
		return Arrays.stream(allMethods).filter(FactoryConfigurationDataMapper::isMethodReturnFactory)
				.map(FactoryConfigurationDataMapper::pairMethodNameAndFactoryParameters)
				.map(FactoryConfigurationDataMapper::createResourceProviderMethod).collect(Collectors.toList());
	}

	// method的返回值 是 Factory 的子类/接口
	private static boolean isMethodReturnFactory(Method method) {
		return FACTORY.isAssignableFrom(method.getReturnType());
	}

	// 获取 方法名 & 返回值的参数列表
	private static Pair<String, Type[]> pairMethodNameAndFactoryParameters(Method method) {
		String methodName = method.getName();
		ParameterizedType returnType = (ParameterizedType) method.getGenericReturnType();
		Type[] typeArguments = returnType.getActualTypeArguments();
		return ImmutablePair.of(methodName, typeArguments);
	}

	/**
	 * 构造 ResourceProviderMethod 对象 使 methodName 对应着其返回值中的resourceClass 与
	 * resourceDataClass
	 */
	private static ResourceProviderMethod createResourceProviderMethod(Pair<String, Type[]> method) {
		Class<?> resourceClass = getClassFromType(method.getValue()[RESOURCE_ORDER]);
		Class<?> resourceDataClass = getClassFromType(method.getValue()[RESOURCE_DATA_ORDER]);
		return new ResourceProviderMethod(method.getKey(), resourceClass, resourceDataClass);
	}

	private static Class<?> getClassFromType(Type type) {
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			return (Class<?>) parameterizedType.getActualTypeArguments()[0];
		}
		return (Class<?>) type;
	}

	/**
	 * 获取 ResourceDataProvider 中所有的 Method 对于任意一个Method，构造
	 * ResourceDataProviderMethod 对象,使Method的方法名与Method的返回值中类型对应
	 */
	private List<ResourceDataProviderMethod> getResourceDataProviderMethods() {
		Method[] allMethods = RESOURCE_DATA_PROVIDER.getDeclaredMethods();
		return Arrays.stream(allMethods).filter(FactoryConfigurationDataMapper::doesMethodReturnCollection)
				.map(FactoryConfigurationDataMapper::pairMethodNameAndReturnType)
				.filter(FactoryConfigurationDataMapper::doesReturnTypeExtendsConfgurationDate)
				.map(FactoryConfigurationDataMapper::getResourceDataMethod).collect(Collectors.toList());
	}

	private static boolean doesMethodReturnCollection(Method method) {
		return COLLECTION.isAssignableFrom(method.getReturnType());

	}

	private static Pair<String, Class<?>> pairMethodNameAndReturnType(Method method) {
		String methodName = method.getName();
		Class<?> classFromType = getClassFromType(method.getGenericReturnType());
		return ImmutablePair.of(methodName, classFromType);
	}

	private static boolean doesReturnTypeExtendsConfgurationDate(Pair<String, Class<?>> method) {
		return CONFIGURATION_DATA.isAssignableFrom(method.getValue());
	}

	private static ResourceDataProviderMethod getResourceDataMethod(Pair<String, Class<?>> method) {
		return new ResourceDataProviderMethod(method.getKey(), method.getValue());
	}

	// 找出与resourceClazz相匹配的所有ResourceProviderMethod
	private Set<ResourceProviderMethod> findResourceProviderMethodMatchWith(Class<?> resource) {
		return resourceProviderMethods.stream().filter(fm -> resource.equals(fm.getResourceClass()))
				.collect(Collectors.toSet());
	}

	/**
	 * A.isAssignableFrom(B) return true if A>=B: <br>
	 * A is father class / interface of B.<br>
	 * A = B<br>
	 * resource 是父接口
	 */
	private Set<ResourceProviderMethod> findResourceProviderMethodMatchSubResource(Class<?> resource) {
		return resourceProviderMethods.stream().filter(fm -> resource.isAssignableFrom(fm.getResourceClass()))
				.collect(Collectors.toSet());
	}
}
