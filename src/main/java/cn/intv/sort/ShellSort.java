package cn.intv.sort;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {2, 13, 5, -1, 23, 6, -8, 34, 3, 4, -5, 78, 34, 65, 32, 11, 16, 32, 76, 1, 9};
        shellSort(arr);
        System.out.println("排序后:");
        printArr(arr);
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = (n / 2); gap > 0; gap /= 2) {
            for (int j = gap; j < n; j++) {
                insertSort(arr, gap, j);
            }
        }
    }

    private static void insertSort(int[] arr, int gap, int j) {
        while (j >= gap && arr[j - gap] > arr[j]) {
            swap(arr, j, j - gap);
            j -= gap;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    static void printArr(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}