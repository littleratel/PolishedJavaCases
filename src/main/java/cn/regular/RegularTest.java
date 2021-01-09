package cn.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {
    public static void main(String[] args) {
//        String pStr = "dus5679[\u0001]{0,1}\u001B\\[0m[\u0002]{0,1}> *$";
//        Pattern pattern = Pattern.compile(pStr);
//
//        // find isMatched string from OriginalString
//        String originalString = "\u0001\u001B[1m\u0002dus5679\u0001\u001B[0m\u0002>";
//        Matcher matcher = pattern.matcher(originalString);
//
//        System.out.println(matcher.find());

        match();
    }

    private static void match() {
        String pStr = "find isMatched string from OriginalString";
        Pattern pattern = Pattern.compile(pStr);

        // find isMatched string from OriginalString
        String originalString = "find isMatched string from OriginalString";
        Matcher matcher = pattern.matcher(originalString);

        System.out.println(matcher.matches());
    }
}
