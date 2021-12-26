package cn.intv.search;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据社保号排序，去重.
 * Input:
 * 29WTS12345, 79EXS43213, 30ABD23557, 30ABD23446, 29WTS12345
 *
 * Output:
 * 29WTS12345 2
 * 30ABD23446 1
 * 30ABD23557 1
 * 79EXS43213 1
 *
 * */
public class SocialSecurityNumbers {

    @Test
    public void main() {
        String[] arr = {
                "29WTS12345",
                "79EXS43213", "30ABD23557",
                "30ABD23446", "29WTS12345",
                "79EXS43213"};

        List<String> lst = Arrays.stream(arr).sorted().collect(Collectors.toList());
        int cnt = 1;
        for (int i = 1; i < lst.size(); i++) {
            if (lst.get(i).equals(lst.get(i - 1))) {
                cnt++;
                continue;
            }

            System.out.println(lst.get(i - 1) + " " + cnt);
            cnt = 1;
        }
        System.out.println(lst.get(lst.size() - 1) + " " + cnt);

    }

}