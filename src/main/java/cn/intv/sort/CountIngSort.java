package cn.intv.sort;

public class CountIngSort {

	public static void main(String[] args) {
		int[] input = { -1, -2, 6, 5, 7, 6, 7, 6, 9, 10 };
		System.out.println("排序前:");
		print(input);
		System.out.println("排序后:");
		CountingSort(input, -2, 10);
		print(input);
	}

	public static void CountingSort(int[] input, int leftBorder, int rightBorder) {
		int len = rightBorder - leftBorder + 1;
		int[] countArr = new int[len];

		int arrLength = input.length;
		int[] inputCpy = new int[arrLength];
		for (int i = 0; i < arrLength; i++) {
			inputCpy[i] = input[i] - leftBorder;
		}

		// 计数
		for (int i = 0; i < arrLength; i++) {
			countArr[inputCpy[i]]++;
		}

		// 累加
		for (int i = 1; i < len; i++) {
			countArr[i] += countArr[i - 1];
		}

		// countArr[v]的值，代表v在排序后的数组input中的位置
		for (int i = arrLength - 1; i >= 0; i--) {
			input[--countArr[inputCpy[i]]] = inputCpy[i] + leftBorder;
		}
	}

	private static void print(int[] input) {
		for (int a : input) {
			System.out.print(a + " ");
		}
		System.out.println();
	}
}
