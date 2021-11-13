package cn.intv.greedy;

import java.util.LinkedList;
import java.util.List;

/**
 * 字符串 s 由小写字母组成;
 * 把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中;
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"
 * */
public class PartitionLabels {
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }

    private static List<Integer> partitionLabels(String s) {
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;
        }

        List<Integer> res = new LinkedList<>();
        List<String> seq = new LinkedList<>();
        int idx = 0;
        int last = 0;
        for (int i = 0; i < chars.length; i++) {
            idx = Math.max(idx, edge[chars[i] - 'a']);
            if (i == idx) {
                seq.add(s.substring(last, idx + 1));
                res.add(i - last + 1);
                last = i + 1;
            }
        }

        System.out.println(seq.toString());
        return res;
    }
}
