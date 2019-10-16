package cn.intv.sort;

public class Quicksort {

	public static void main(String[] args) {

		int[] array = { 10, 15, 11, 11, 12, -3, 9, 3, 1 };
		sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ",");
		}
	}

	private static void sort(int[] array) {
//		quicksort(array, 0, array.length - 1);
		quicksort2(array, 0, array.length - 1);
	}

	// 快速排序1
	private static void quicksort(int[] array, int left, int right) {
		if (left > right) {
			return;
		}
		int key = array[left], tmp;
		int i = left, j = right;

		// 交换位置
		while (i < j) {
			while (array[j] >= key && i < j) {
				j--;
			}
			while (array[i] <= key && i < j) {
				i++;
			}
			if (i < j) {
				tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
			}
		}

		// 最终将基准数归位
		array[left] = array[i];
		array[i] = key;

		quicksort(array, left, i - 1);
		quicksort(array, i + 1, right);
	}

	// 快速排序2
	private static void quicksort2(int[] array, int left, int right) {
		if (left > right) return;
		
		int key = array[left];
		int i = left, j = right;

		// 交换位置
		while (i < j) {
			while (array[j] >= key && i < j) {
				j--;
			}
			array[i] = array[j];
			while (array[i] <= key && i < j) {
				i++;
			}
			array[j] = array[i];
		}

		// 最终将基准数归位
		array[i] = key;

		quicksort2(array, left, i - 1);
		quicksort2(array, i + 1, right);
	}

}
