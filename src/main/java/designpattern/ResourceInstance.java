package designpattern;

public enum ResourceInstance {
    INSTANCE;
	
    private Resource instance;
    private ResourceInstance() {
        instance = new Resource();
    }
    public Resource getInstance() {
        return instance;
    }
    
    public class Resource {
    	private Resource() {}
    	public void doSth() {
    		System.out.println("This is Resource.doSth().");
    	}
    }
}
