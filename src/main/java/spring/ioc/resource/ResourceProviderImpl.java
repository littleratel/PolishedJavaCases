package spring.ioc.resource;

import spring.ioc.factory.DefaultResourceConstructorFactory;
import spring.ioc.factory.Factory;
import spring.ioc.resourcedata.G2RbsResource;

public class ResourceProviderImpl implements ResourceProvider {

	@Override
	public Factory<G2Rbs, G2RbsResource> getG2RbsProvider() {
		return new DefaultResourceConstructorFactory<>(G2RbsImpl.class);
	}

}
