package cn.intv.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 784. 字母大小写全排列
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 */
public class LetterCasePermutation {
    public static void main(String[] args) {
        LetterCasePermutation tool = new LetterCasePermutation();
        System.out.println(tool.letterCasePermutation("a1b2"));
    }

    public List<String> letterCasePermutation(String s) {
        char[] arr = s.toCharArray();
        List<String> res = new ArrayList<>();

        backtrack(arr, 0, res);

        return res;
    }

    private void backtrack(char[] arr, int idx, List<String> res) {
        res.add(new String(arr));

        for (int i = idx; i < arr.length; i++) {
            if (arr[i] < 'A') {
                continue;
            }

            arr[i] ^= 32;
            backtrack(arr, i + 1, res);
            arr[i] ^= 32;
        }
    }
}
