package cn.intv.offer66;

public class ShortMethodes {
	public static void main(String[] args) {

	}

	/**
	 * 用2*1的小矩形横着或竖着去覆盖更大的矩形，请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	 * 类似于上楼梯，一次可以上1个楼梯，也可以一次上2个楼梯，问上到n个楼梯可以有几种算法。
	 */
	public int RectCover(int target) {
		if (target < 1) {
			return 0;
		} else if (target == 1 || target == 2) {
			return target;
		} else {
			return RectCover(target - 1) + RectCover(target - 2);
		}
	}

	/**
	 * 二进制中1的个数，其中负数用补码表示。
	 */
	public int NumberOf1(int n) {
		int count = 0;
		while (n != 0) {
			++count;
			n = (n - 1) & n;
		}
		return count;
	}

}
