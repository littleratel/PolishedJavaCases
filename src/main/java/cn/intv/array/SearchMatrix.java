package cn.intv.array;

/**
 * 在二维矩阵中搜索一个数
 * 该矩阵从上到下，从左到右，数值逐渐递增;
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50},
                {60, 61, 76, 90}
        };
        int target = 7;
        System.out.println(searchMatrixDiagonalSearch(matrix, target));
        System.out.println(searchMatrixBinarySearch(matrix, target));
        System.out.println(searchMatrix(matrix, target));
    }

    // 对角线搜索（矩阵有序）
    private static boolean searchMatrixDiagonalSearch(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int rows = matrix.length, cols = matrix[0].length;
        int row = rows - 1, col = 0;

        while (row >= 0 && col < cols) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }

        return false;
    }

    // 二分搜索（每行有序）
    public static boolean searchMatrixBinarySearch(int[][] matrix, int target) {
        for (int[] row : matrix) {
            if (binarySearch(row, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearch(int[] row, int target) {
        int left = 0, right = row.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    // 二分搜索（每行有序）
    private static boolean searchMatrix(int[][] matrix, int target) {
        int yLen = matrix[0].length - 1;
        int l = 0, r = matrix.length - 1;
        int x = 0;
        // find x
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[mid][0] <= target && matrix[mid][yLen] >= target) {
                x = mid;
                break;
            } else if (matrix[mid][yLen] < target) {
                l = mid + 1;
            } else if (matrix[mid][0] > target) {
                r = mid - 1;
            }
        }

        // find y
        l = 0;
        r = yLen;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (matrix[x][mid] == target) {
                System.out.println("x: " + x + ", y: " + mid);
                return true;
            } else if (matrix[x][mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }
}
