package spring.ioc.resource;

import spring.ioc.factory.Factory;
import spring.ioc.resourcedata.G2RbsResource;

public interface ResourceProvider {
	Factory<G2Rbs, G2RbsResource> getG2RbsProvider();
}
