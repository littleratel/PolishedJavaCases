package spring.ioc.resource;

import spring.ioc.resourcedata.G2RbsResource;

public class G2RbsImpl implements G2Rbs {

	private final G2RbsResource resource;

	public G2RbsImpl(G2RbsResource resource) {
		this.resource = resource;
	}

	public G2RbsImpl(G2RbsResource resource, Object... objs) {
		this.resource = resource;
		// do other setup
	}

	// do other operations
	public String getCli() {
		return resource.getSerialCli();
	}

	public void start() {
		System.out.println("strarting to start the node...");
	}
}
