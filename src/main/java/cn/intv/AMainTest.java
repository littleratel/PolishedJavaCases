package cn.intv;

import java.util.Arrays;
import java.util.List;

public class AMainTest {

    public static void main(String[] args) {
        AMainTest tool = new AMainTest();
        System.out.println(tool.wordBreak("leetcode", Arrays.asList(new String[]{"leet", "code"})));
        System.out.println(tool.wordBreak("applepenapple", Arrays.asList(new String[]{"apple", "pen"})));
        System.out.println(tool.wordBreak("catsandog", Arrays.asList(new String[]{"cats", "dog", "sand", "and", "cat"})));
    }

    public boolean wordBreak(String s, List<String> wordDict) {


        return false;
    }
}
