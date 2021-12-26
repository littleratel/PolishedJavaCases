package spring.ioc.resourcedata;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * */
public class ResourceDataProviderImpl extends AbstractResource implements ResourceDataProvider {

	@Override
	public String getId() {
		return ResourceDataProviderImpl.class.getName();
	}

	@Override
	public List<G2RbsResource> getG2RbsList() {
		final List<G2RbsResource> g2RbsList = new ArrayList<>();

		// 从配置文件中读取G2RbsResource的配置数据，装入 g2RbsList
		g2RbsList.add(new G2RbsResourceImpl());

		return g2RbsList;
	}
}
