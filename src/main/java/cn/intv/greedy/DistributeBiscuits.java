package cn.intv.greedy;

import java.util.Arrays;

/**
 * 假设你是一位家长，想给孩子们一些小饼干,每个孩子最多只能给一块饼干
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，可以将这个饼干j分配给孩子i，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * */
public class DistributeBiscuits {

    public static void main(String[] args) {
        int[] g = {1, 2, 3};
        int[] s = {3};

        // do svc
        Arrays.sort(g);
        Arrays.sort(s);
        int cnt = 0, sidx = s.length - 1, gidx = g.length - 1;
        while (gidx >= 0 && sidx >= 0) {
            if (s[sidx] >= g[gidx]) {
                cnt++;
                gidx--;
                sidx--;
            } else {
                gidx--;
            }
        }
        System.out.println(cnt);
    }
}