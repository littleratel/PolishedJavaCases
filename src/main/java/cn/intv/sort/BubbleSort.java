package cn.intv.sort;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 6, 10, 10, 10, 4, 11, 12 };
		System.out.println("排序前：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
		
		sort(arr);
		System.out.println("排序后：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}

	// 冒泡排序
	private static void sort(int[] arr) {
		int len = arr.length - 1;
		boolean exchanged;
		for (int i = len; i > 0; i--) {
			exchanged = false;
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
					exchanged = true;
				}
			}
			if (!exchanged) {
				System.out.println("This is a sorted array!");
				break;
			}
		}
	}
}
