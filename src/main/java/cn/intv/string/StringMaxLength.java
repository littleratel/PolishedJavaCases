package cn.intv.string;

/**
 * 字符串最大长度，有两种情况，
 * 1. 存在常量池中的字符串最大长度为 65535-1 = 65534 位，
 * 2. 在运行时产生的字符串可以很大，此时限制字符串长度的因子是heap大小
 */
public class StringMaxLength {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 65535; i++) {
            sb.append("a");
        }

        String re = "aaaaa...aaa";
        System.out.println(sb.toString());
    }
}
