package cn.intv.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 给定多个整数，求这些数能拼成的最大数
 *
 * Input:
 * {"9", "98", "990", "22", "977"}
 *
 * OutPut:
 * 9 + 990 + 98 + 977 + 22
 * */
public class BigNumbers {

    @Test
    public void main() {
        String[] arr = {"9", "98", "990", "22", "977"};
        List lst = Arrays.stream(arr).sorted(
                (x, y) -> {
                    int len = Math.min(x.length(), y.length());
                    for (int i = 0; i < len; i++) {
                        if (x.charAt(i) == y.charAt(i)) {
                            continue;
                        } else {
                            return y.charAt(i) - x.charAt(i);
                        }
                    }

                    return x.length() - y.length();
                }
        ).collect(Collectors.toList());
        System.out.println(lst.toString());
    }

}