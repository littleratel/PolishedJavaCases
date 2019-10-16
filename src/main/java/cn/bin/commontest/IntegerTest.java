package cn.bin.commontest;

import java.lang.reflect.Field;

/**
 * 实现函数swap, 交换两个Integer的值
 * 
 **/
public class IntegerTest {
	
	 public static void main(String[] args) throws Exception {
	  Integer a = 1;
	  Integer b = -150;
	  System.out.println("a="+a+", b="+b);
	  
//	  swap1(a, b);
//	  System.out.println("Use setInt: a="+a+", b="+b);

	  swap2(a, b);
	  System.out.print("Use set:    a="+a+", b="+b);
	  
	 }

	// 使用 field.setInt()
	 private static void swap1(Integer a, Integer b) throws Exception{
		 int tem=a.intValue();
	     Field field=Integer.class.getDeclaredField("value");
	     field.setAccessible(true);
	     
	     field.setInt(a, b);
	     System.out.println(tem);
	     field.setInt(b, tem);
	     System.out.println(tem);
	 }
	 
	 // 使用 field.set()
	 private static void swap2(Integer a, Integer b) throws Exception{
		 int tem=a.intValue();
	     Field field=Integer.class.getDeclaredField("value");
	     field.setAccessible(true);

	     //field.set 这种方式会因为自动拆装箱和缓存导致结果有问题
	     field.set(a, b);
	     System.out.println(tem);
	     field.set(b, tem);
	     System.out.println(tem);
	 }
}
