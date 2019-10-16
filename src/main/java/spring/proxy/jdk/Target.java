package spring.proxy.jdk;

public interface Target extends TargetCreate, TargetQuery {
	
	public void create();

	public void query(String str);
}
