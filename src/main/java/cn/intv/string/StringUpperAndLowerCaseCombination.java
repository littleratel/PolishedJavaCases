package cn.intv.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个字符串，只包含字母和数字，输出字符串的所有大小写组合（若包含非字母，直接输出即可）
 */
public class StringUpperAndLowerCaseCombination {

    private static final int DIFF = 'a' - 'A';

    public static void main(String[] args) {
        String inputStr = "ab2D";
        List<String> lst = recordResults(inputStr);
        lst.forEach(System.out::println);
    }

    private static List<String> recordResults(final String inputStr) {
        char[] str2Char = inputStr.toCharArray();
        // 记录字母位置的集合
        List<Integer> lettersIndexArray = new ArrayList<>();
        for (int i = 0; i < inputStr.length(); i++) {
            if (str2Char[i] >= 'A' && str2Char[i] <= 'z') {
                lettersIndexArray.add(i);
            }
        }

        int lettersIndexArraySize = lettersIndexArray.size();
        // 最终结果总共有 count 种可能
        // 若 count=8, lettersIndexArraySize = 3,
        int count = (int) Math.pow(2, lettersIndexArraySize);
        List<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            String binaryStr = Integer.toBinaryString(i);
            int binaryStrLength = binaryStr.length();
            for (int j = binaryStrLength - 1; j >= 0; j--) {
                int lettersIndex = lettersIndexArray.get(lettersIndexArraySize - binaryStrLength + j);
                if (binaryStr.charAt(j) == '1') { // Need to change case
                    str2Char[lettersIndex] = toggleCase(str2Char[lettersIndex]);
                }
            }
            result.add(String.valueOf(str2Char));
            str2Char = inputStr.toCharArray();
        }

        return result;
    }

    private static char toggleCase(char ch) {
        return (char) (ch >= 'a' ? (ch - DIFF) : (ch + DIFF));
    }

}
