package spring.ioc;

import spring.ioc.resource.G2Rbs;

public class Client {

	public static void main(String[] args) {

		G2Rbs G2Rbs = ResourceFacade.getResources(G2Rbs.class).get(0);
		System.out.println(G2Rbs.getCli());
		G2Rbs.start();

	}

}
