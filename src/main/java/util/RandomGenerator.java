package util;

import java.util.Random;

/**
 * 已知一随机发生器，产生0的概率是p，产生1的概率是1-p，现要求：<br>
 * 构造一个发生器，使得它构造0和1的概率均为1/2;<br>
 * 构造一个发生器，使得它构造1、2、3的概率均为1/3;<br>
 * 构造一个发生器，使得它构造1、2、3、...n的概率均为1/n，要求复杂度最低.
 *
 * 思路:<br>
 * 对n=2，认为01表示0、10表示1，等概率，其他情况放弃;<br>
 * 对n=3，认为001表示1、010表示2，100表示3，等概率，其他情况放弃;<br>
 * 对n=4，认为0001表示1、0010表示2，0100表示3，1000表示4，等概率，其他情况放弃
 */
public class RandomGenerator {

	private static Random random = new Random();

	public static void main(String[] args) {
		int count = 0;
		int times = 20000;
		for (int i = 0; i < times; i++) {
			count += getRandom();
		}
		System.out.println("count of 1 : " + count);

		count = 0;
		for (int i = 0; i < times; i++) {
			count += getRandom1();
		}
		System.out.println("count of 1 : " + count);
	}

	private static int getRandom() {
		int i, j;
		i = random.nextInt(2);
		j = random.nextInt(2);

		if ((i ^ j) == 1) {
			return 0;
		}
		return 1;
	}

	private static int getRandom1() {
		int i, j;
		while (true) {
			i = random.nextInt(2);
			j = random.nextInt(2);
			if (i == 0 && j == 1) {
				return 0;
			} else if (i == 1 && j == 0) {
				return 1;
			} else {
				continue;
			}
		}
	}

}
