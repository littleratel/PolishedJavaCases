package cn.intv.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 1~9中间插入加号或减号使运算结果等于100
 */
public class InsertPlusOrMinusBetween1To9MakeResult100 {
    public static void main(String[] args) {
        List<String> resultList = doCalculate("123456789", 9, 100);
        for (String s : resultList) {
            System.out.println(s + "=100");
        }
        System.out.println("Total : " + resultList.size());
    }

    public static List<String> doCalculate(String numSeq, int n, int N) {
        List<String> ans = new ArrayList<>();
        for (int i = 0, lastPart; i < n; i++) {
            lastPart = Integer.parseInt(numSeq.substring(i, n));
            if (i == 0 && lastPart == N) {
                ans.add(numSeq.substring(i));
                continue;
            }

            List<String> plusR = doCalculate(numSeq, i, N + lastPart);
            for (String s : plusR) {
                ans.add(s + "-" + lastPart);
            }

            List<String> minusR = doCalculate(numSeq, i, N - lastPart);
            for (String s : minusR) {
                ans.add(s + "+" + lastPart);
            }
        }

        return ans;
    }
}
