package spring.ioc.resourcedata;

import java.util.List;

public interface ResourceDataProvider extends ConfigurationData{

	List<G2RbsResource> getG2RbsList();
	
}
