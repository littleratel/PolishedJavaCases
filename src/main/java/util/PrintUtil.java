package util;

public class PrintUtil {

    public static void print(Object[] nums) {
        for (Object item : nums) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static void twoDimensionalArray(int[][] people) {
        int len = people.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append("{").append(people[i][0]).append(", ").append(people[i][1]).append("}, ");
        }
        System.out.println(sb.toString());
    }
}
