package cn.bin;

public class HelloWordTest {
    public static void main(String[] args) {
        char[] arr = "23456789".toCharArray();
        for (char ch : arr) {
            int idx = ch - '2';
            System.out.print((char) (3 * idx + 'a'));
            System.out.print((char) (3 * idx+ 1 + 'a'));
            System.out.println((char) (3 * idx + 2 + 'a'));
        }
    }

}
