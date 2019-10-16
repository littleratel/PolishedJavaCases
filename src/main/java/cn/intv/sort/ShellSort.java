package cn.intv.sort;

public class ShellSort {
	public static void main(String[] args) {
		int[] arr = { 2, 13, 5, -1, 23, 6, -8, 34, 3, 4, -5, 78, 34, 65, 32, 11, 16, 32, 76, 1, 9 };
		shellSort(arr);
		System.out.println("排序后:");
		printArr(arr);
	}

	public static void shellSort(int[] arr) {
		int n = arr.length, gap = n / 2;
		while (gap > 0) {
			// 将数组列在一个表中,并对列分别进行插入排序
			for (int i = gap; i < n; i++) {
				int j = i;
				while (j >= gap && arr[j - gap] > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[j - gap];
					arr[j - gap] = temp;
					j -= gap;
				}
			} // end for
			System.out.println("gap = "+gap);
			printArr(arr);
			gap = gap / 2;
		} // end while
	}

	static void printArr(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
}