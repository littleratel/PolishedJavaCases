package cn.intv.offer66.calculator;

public class Test {
	public static void main(String[] args) {
		String expr1 = "2+(7+((1+6)*2-11)/2-5)*2-(1+2)/3"; // 8.0
		System.out.println(Calculator.conversion(expr1));
	}
}
