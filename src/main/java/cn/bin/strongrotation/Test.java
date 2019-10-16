package cn.bin.strongrotation;

public class Test {

	public static void main(String[] args) {
		
		// 1.子类可以自动转型为父类
		Interfac intf = new FatherClass();
		intf.InterfDefinMethod();

		// 浅拷贝，现在impl也保存了这个InterfImpl实例的引用
		FatherClass impl = (FatherClass)intf;
		impl.InterfImplMethod();
		
		int num = impl.hashCode();
		
		if(intf.equals(impl)){
			System.out.println(num);
		}
		
//		test();
	}
	
	public static void test() {
		
		// 1.子类可以自动转型为父类
		Interfac fatherClazz = new FatherClass();
		SubClass sbuClazz = (SubClass) fatherClazz;
		
		sbuClazz.subCalzzMethod();
	}
	
}
