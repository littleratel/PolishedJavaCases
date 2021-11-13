package spring.ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.io.Resource;

import throwable.exceptions.ResourceManagerException;
import spring.ioc.factory.Factory;
import spring.ioc.resource.ResourceProvider;
import spring.ioc.resource.ResourceProviderImpl;
import spring.ioc.resourcedata.ConfigurationData;
import spring.ioc.resourcedata.ResourceDataProvider;
import spring.ioc.resourcedata.ResourceDataProviderImpl;
import spring.ioc.unit.FactoryConfigurationDataMapper;
import spring.ioc.unit.ResourceProviderMethod;
import spring.ioc.unit.ResourceDataProviderMethod;

@SuppressWarnings("unchecked")
public class ResourceFacade {
	private Map<Class<?>, List<?>> cache = new LinkedHashMap<>();
	private final ResourceDataProvider stp = new ResourceDataProviderImpl();
	private final ResourceProvider resourceProvider = new ResourceProviderImpl();
	private FactoryConfigurationDataMapper mapper = new FactoryConfigurationDataMapper();
	private static ResourceFacade facade = null;

	private ResourceFacade() {
	}

	public static <T, D extends ConfigurationData> List<T> getResources(Class<T> clazz) {
		if (facade == null) {
			facade = new ResourceFacade();
		}
		return facade.getResourcesByClass(clazz);
	}

	private <T, D extends ConfigurationData> List<T> getResourcesByClass(Class<T> clazz) {
		List<T> resources = new ArrayList<>();
		List<Pair<ResourceProviderMethod, ResourceDataProviderMethod>> mapping = mapper.mapResourceToResourceDataFor(clazz);

		for (Pair<ResourceProviderMethod, ResourceDataProviderMethod> factoryMethodToStpResource : mapping) {
			ResourceProviderMethod resourceProviderMethod = factoryMethodToStpResource.getKey();
			ResourceDataProviderMethod resourceDataProviderMethod = factoryMethodToStpResource.getValue();
			Factory<T, D> factory = getResourceFactory(resourceProviderMethod);
			List<? extends D> resourceData = getResourceDataFromStpResource(resourceDataProviderMethod);

			if (resourceData != null && factory != null) {
				resources.addAll(getNodeListOfTypeIfPossible((Class) resourceProviderMethod.getResourceClass(),
						resourceData, factory));
			}
		}
		return resources;
	}

	private <T, D extends ConfigurationData> Factory<T, D> getResourceFactory(ResourceProviderMethod factoryMethod) {
		try {
			Method providerMethodGetCalzzFactory = resourceProvider.getClass().getMethod(factoryMethod.getMethodName());
			return (Factory<T, D>) providerMethodGetCalzzFactory.invoke(resourceProvider);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Getting resource factory throw the exception " + e.toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println(
					"Getting resource factory by invoking reflection method throw the exception " + e.toString());
		}

		throw new ResourceManagerException("Cannot get resource factory because of exception thrown.");
	}

	/**
	 * 
	 * */
	private <T extends Resource, D extends ConfigurationData> List<? extends D> getResourceDataFromStpResource(
			ResourceDataProviderMethod resourceDataMethod) {
		try {
			Method stpMethodGetCalzzList = stp.getClass().getMethod(resourceDataMethod.getMethodName());
			return (List<? extends D>) stpMethodGetCalzzList.invoke(stp);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Getting resource data from stp resource throw the exception {}" + e.toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out
					.println("Getting resource data by invoking reflection method throw the exception " + e.toString());
		}

		throw new ResourceManagerException("Cannot get resource data from stp resource because of exception thrown.");
	}

	/**
	 * 
	 * */
	private <T, D extends ConfigurationData> List<T> getNodeListOfTypeIfPossible(Class<? extends T> implClass,
			List<? extends D> resourceDatas, Factory<T, D> factory) {

		if (!cache.containsKey(implClass)) {
			List<T> nodeList = new ArrayList<>();
			for (D resourceData : resourceDatas) {
				T node = factory.create(resourceData);
				nodeList.add(node);
			}
			cache.put(implClass, nodeList);
		}

		return (List<T>) getCopyFromCache(implClass);
	}

	private <T> List<T> getCopyFromCache(Class<T> nodeType) {

		List<T> list = new ArrayList<>();
		List<T> cachedList = (List<T>) cache.get(nodeType);
		if (cachedList != null) {
			list.addAll(cachedList);
		}

		return list;
	}
}
