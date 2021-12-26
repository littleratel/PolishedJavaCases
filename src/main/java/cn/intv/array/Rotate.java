package cn.intv.array;

/**
 * LeetCode 48. 旋转图像:
 * <p>
 * 给定一个 len × len 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 */
public class Rotate {

    public static void main(String[] args) {
        String[][] rawData = {
                {"00", "01", "02", "03", "04", "05", "06"},
                {"10", "11", "12", "13", "14", "15", "16"},
                {"20", "21", "22", "23", "24", "25", "26"},
                {"30", "31", "32", "33", "34", "35", "36"},
                {"40", "41", "42", "43", "44", "45", "46"},
                {"50", "51", "52", "53", "54", "55", "56"},
                {"60", "61", "62", "63", "64", "65", "66"},
        };

        print(rawData);
        rotate(rawData);
        print(rawData);
    }

    /**
     * 原地旋转
     * <p>
     * 时间复杂度：O(len^2)
     * 空间复杂度：O(1)
     */
    public static void rotate(String[][] matrix) {
        int len = matrix.length;
        String temp;
        for (int i = 0; i < len / 2; ++i) {
            for (int j = 0; j < (len + 1) / 2; ++j) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[len - j - 1][i];
                matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
                matrix[j][len - i - 1] = temp;
            }
        }
    }

    /**
     * 用翻转代替旋转
     * <p>
     * 时间复杂度：O(len^2)
     * 空间复杂度：O(1)
     */
    public void rotate2(int[][] matrix) {
        int len = matrix.length, temp;
        // 水平翻转
        for (int i = 0; i < len / 2; ++i) {
            for (int j = 0; j < len; ++j) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[len - i - 1][j];
                matrix[len - i - 1][j] = temp;
            }
        }

        // 主对角线翻转
        for (int i = 0; i < len; ++i) {
            for (int j = 0; j < i; ++j) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void print(String[][] rawData) {
        StringBuilder sb = new StringBuilder();
        int len = rawData[0].length, rows = rawData.length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < len; j++) {
                sb.append(rawData[i][j]);
                sb.append(" ");
            }
            sb.append("\r\n");
        }

        System.out.println(sb);
    }
}
