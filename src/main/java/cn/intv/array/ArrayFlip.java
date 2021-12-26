package cn.intv.array;

/**
 * Rotate the array 90 degrees clockwise
 */
public class ArrayFlip {

    public static void main(String[] args) {
        Object[][] rawData = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}};
        print(rawData);
        Object[][] res = arrayFlip(rawData);
        print(res);
    }

    private static Object[][] arrayFlip(Object[][] matrix) {
        Object[][] temp = new Object[matrix[0].length][matrix.length];
        int dst = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++, dst--) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][dst] = matrix[i][j];
            }
        }

        return temp;
    }

    private static void print(Object[][] rawData) {
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
