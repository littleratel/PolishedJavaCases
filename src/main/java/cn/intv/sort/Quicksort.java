package cn.intv.sort;

public class Quicksort {

    public static void main(String[] args) {
        int[] array = {10, 15, 11, 11, 12, -3, 9, 3, 1};
        sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    private static void sort(int[] array) {
        quicksort(array, 0, array.length - 1);
        quicksort2(array, 0, array.length - 1);
    }

    // O()
    private static void quicksort2(int[] array, int left, int right) {
        if (left > right) return;

        int i = left, j = right;
        int key = array[i];

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

        array[i] = key;
        quicksort2(array, left, i - 1);
        quicksort2(array, i + 1, right);
    }

    //
    private static void quicksort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left, j = right;
        int key = array[i], tmp;

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

        array[left] = array[i];
        array[i] = key;

        quicksort(array, left, i - 1);
        quicksort(array, i + 1, right);
    }
}
