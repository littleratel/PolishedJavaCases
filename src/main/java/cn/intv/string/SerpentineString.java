package cn.intv.string;

import org.junit.Test;

/**
 * 蛇形字符串
 *
 * 蛇形字符串的定义：
 *
 * 1.蛇形字符串由连续字符对组成，其特点如下：
 * 字符对定义：字符对由同一字母的大写和小写组成（前大后小）。如 Aa,Dd
 * 蛇形字符串中包含的字符对，必须是连续字母，并按照字母顺序排序。如 AaBbCc or OoPpQqRrSs
 *
 *  2.从输入中寻找字符组成蛇形字符串（字符顺序不限），符合规则：
 * 每次寻找必须是最长的蛇形字符串
 * 使用过的字符不能重复使用
 *
 * Example
 * Input:
 * SxxsrR^AaSs
 *
 * Output:
 * RrSs
 * Aa
 * Ss
 * */
public class SerpentineString {

    @Test
    public void mainTest() {
        String str = "SwSE$3454356DD$$E#eswsxxsssAAWDxxdderfvcRFER65645hbg^^%%^UnbnvccTRChnyvcxcvVCFR";
        int[] upCaseArr = new int[26];
        int[] lowCaseArr = new int[26];

        //
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                upCaseArr[str.charAt(i) - 'A']++;
            } else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                lowCaseArr[str.charAt(i) - 'a']++;
            }
        }

        //
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (upCaseArr[i] > 0 && lowCaseArr[i] > 0) {
                for (int k = i; k < 26; k++) {
                    if (upCaseArr[k] != 0 && lowCaseArr[k] != 0) {
                        sb.append((char) (k + 'A')).append((char) (k + 'a'));
                        upCaseArr[k]--;
                        lowCaseArr[k]--;
                    } else {
                        sb.append("=");
                        break;
                    }
                }
            }
        }

        //
        String[] tmp = sb.toString().split("=");
        for (String s : tmp) {
            if (!s.isEmpty()) {
                System.out.println(s);
            }
        }
    }
}