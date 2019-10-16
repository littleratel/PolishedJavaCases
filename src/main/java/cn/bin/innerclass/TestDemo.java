package cn.bin.innerclass;

import cn.bin.innerclass.FunLisenter;

public class TestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainFun();
	}
	
	public static void mainFun() {
		final String str = null;

		new FunLisenter() {
			@Override
			public void fun() {
				System.out.println(str);
			}
		}.fun();
		
	}
	
}
