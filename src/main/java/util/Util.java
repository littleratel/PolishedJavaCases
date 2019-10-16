package util;

import java.util.Arrays;
import java.util.Random;

public class Util {
	
	public static void main(String[] args){
		int[] arr = mockArray(10,100);
		Arrays.stream(arr).forEach(i -> System.out.print(i+" "));
	}
	
	/**
	 * 随机数组产生器
	 * */
	public static final int[] mockArray(int length, int randomNubRange) {
		if (length <= 0) {
			return null;
		}

		int[] array = new int[length];
		//由47做种子，产生的随机数更能体现随机性
		//种子只是随机算法的起源数字，和生成的随机数的区间无关
		Random random = new Random(47);
		for (int i = 0; i < length; i++) {
			array[i] = random.nextInt(randomNubRange);
		}

		return array;
	}
	
	
	
}
