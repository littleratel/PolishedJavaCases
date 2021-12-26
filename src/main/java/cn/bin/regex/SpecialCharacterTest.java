package cn.bin.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacterTest {

    @Test
    public void testPattern_split() {
        String escapedBuffer = "[ -f /root/.scapy_history ] && rm /root/.scapy_history\r\nsekilx1090:~ # ";
        String regexPrompt = "\n";

        Pattern pattern = Pattern.compile(regexPrompt);
        Matcher matcher = pattern.matcher(escapedBuffer);

        boolean isFind = matcher.find();
        System.out.println(isFind);
    }

    @Test
    public void testMatch() {
        String escapedBuffer = "root\r\n\u001B[23;80H \\u001B[24;1H\\n\\u001B[23;80H \\u001B[24;1HLast login: Sat Jan  1 00:53:14 UTC 2000 from 169.254.200.252 on pts/1\\r\\n\\u001B[23;80H \\u001B[24;1H\\u001B[1;1H\\u001B[24;80H\\r^[[24;80Rroot@r609:~# \\u0007;80R";
        String regexPrompt = "ltesim@seliiasim00115:~> ";

        Pattern pattern = Pattern.compile(regexPrompt);
        Matcher matcher = pattern.matcher(escapedBuffer);

        boolean isFind = matcher.find();
        System.out.println(isFind);
    }
}
