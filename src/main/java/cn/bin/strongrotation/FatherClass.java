package cn.bin.strongrotation;

public class FatherClass implements Interfac {

	public String clazzName = FatherClass.class.getSimpleName();
	
	public void InterfDefinMethod() {
		System.out.println(clazzName+" InterfDefinMethod!");
	}
	
	public void InterfImplMethod() {
		System.out.println(clazzName+"' InterfImplMethod!");
	}
}
