package cn.intv.array;

/**
 * Rotate the array 90 degrees clockwise
 * */
public class ArrayFlip {

    public static void main(String[] args) {
        Object[][] rawData = {
                {43, 25, 93},
                {83, 73, 55},
                {86, 65, 82},
                {72, 53, 39}};
        System.out.println(rawData.length + " " + rawData[0].length);
        Object[][] res = arrayFlip(rawData);
        System.out.println(res.length + " " + res[0].length);
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
}
