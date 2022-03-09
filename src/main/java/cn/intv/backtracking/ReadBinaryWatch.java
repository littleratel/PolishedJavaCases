package cn.intv.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 401. 二进制手表
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 */
public class ReadBinaryWatch {
    public static void main(String[] args) {
        ReadBinaryWatch tool = new ReadBinaryWatch();
        System.out.println(tool.readBinaryWatch(1));
    }

    private int[] cache = new int[60];
    private List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch(int turnedOn) {
        for (int i = 0, hn; i < 12; i++) {
            hn = getCountOfOne(i);
            if (hn > turnedOn) {
                continue;
            } else if (hn == turnedOn) {
                res.add(String.format("%d:00", i));
                continue;
            }

            for (int j = 0; j < 60; j++) {
                if (hn + getCountOfOne(j) != turnedOn) {
                    continue;
                }

                if (j < 10) {
                    res.add(String.format("%d:0%d", i, j));
                } else {
                    res.add(String.format("%d:%d", i, j));
                }
            }
        }

        return res;
    }

    private int getCountOfOne(int num) {
        if (num == 0) {
            return 0;
        }
        if (cache[num] != 0) {
            return cache[num];
        }

        cache[num] = Integer.bitCount(num);
        return cache[num];
    }
}
