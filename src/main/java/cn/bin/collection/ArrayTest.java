package cn.bin.collection;

public class ArrayTest {

    public static void main(String[] args) {
        arraycopy();
    }

    private static void arraycopy() {
        String[] srcArr = {"iam", "Jim", "what's your qq num?"};
        String[] lastArr = new String[3];

        // 浅拷贝
        System.arraycopy(srcArr, 0, lastArr, 0, srcArr.length);
        print(lastArr);
    }

    private static void print(String[] arr) {
        for (String e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
