package cn.intv.array;

import java.math.BigInteger;
import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
public class GroupAnagrams {

    public static void main(String[] args) {

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<BigInteger, List<String>> res = new HashMap<>();
        Map<Character, BigInteger> map = getMap();
        BigInteger pro;
        List<String> lst;

        for (String str : strs) {
            pro = new BigInteger("1");

            for (char c : str.toCharArray()) {
                pro = pro.multiply(map.get(c));
            }

            lst = res.get(pro);
            if (lst == null) {
                lst = new LinkedList<>();
                lst.add(str);
                res.put(pro, lst);
            } else {
                lst.add(str);
            }
        }

        return new ArrayList<>(res.values());
    }

    /**
     *
     */
    private Map getMap() {
        Map<Character, BigInteger> res = new HashMap();
        String[] arr = {"2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43",
                "47", "53", "59", "61", "67", "71", "73", "79", "83", "89", "97", "101"};
        int base = 'a';
        for (int i = 0; i < 26; i++) {
            res.put((char) (base + i), new BigInteger(arr[i]));
        }

        return res;
    }
}
