package cn.intv.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字符串排序
 *
 * */
public class StringSorting {

    @Test
    public void main() {
        String[] strArr = {"Abc", "cap", "to", "cat", "card", "two", "too", "up", "boat", "boot"};
        List<String> res = Arrays.stream(strArr).sorted(
                (x, y) -> {
                    return y.compareTo(x); // Desc order
                }
        ).collect(Collectors.toList());
        System.out.println(res.toString());
    }

    /**
     * x compareTo y
     * x == y  return 0;
     * x > y   return 1;
     * x < y   return -1;
     * */
    private int compareTo(String x, String y) {
        int lim = Math.min(x.length(), y.length());
        for (int i = 0; i < lim; i++) {
            if (x.charAt(i) > y.charAt(i)) {
                return 1;
            } else if (x.charAt(i) == y.charAt(i)) {
                return -1;
            } else {
                continue;
            }
        }

        return 0;
    }
}