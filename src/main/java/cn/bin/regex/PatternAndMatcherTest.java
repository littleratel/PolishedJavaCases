package cn.bin.regex;

import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternAndMatcherTest {

    @Test
    public void testOr() {
//        StringBuilder sb = new StringBuilder("Active Lane Alarms   : NONE \n --More--Hello");
        StringBuilder sb = new StringBuilder("Active Lane Alarms   : NONE \n coli>Hello");
        String currentPromt = "((--More--)|(coli>))";

        Pattern regexPattern = Pattern.compile(currentPromt);
        Matcher matcher = regexPattern.matcher(sb);
        if (matcher.find(5)) {
            System.out.println("Matched: " + matcher.group());
            System.out.println("Start: " + matcher.start());
            System.out.println("End :" + matcher.end());
            sb.delete(matcher.start(), matcher.end());
            System.out.println(sb.toString());
        }
    }

    @Test
    public void testUsingStringBuilderAsRawString() {
        String modifiedPrompt = "]]>]]>" + " *$";
        Pattern regexPattern = Pattern.compile(modifiedPrompt, Pattern.MULTILINE);
        StringBuilder sb = new StringBuilder();
        sb.append("</data>\n </rpc-reply>");
        Matcher matcher = regexPattern.matcher(sb);
        if (matcher.find(0)) {
            System.out.println(matcher.groupCount());
            System.out.println("group:" + matcher.group());
            System.out.println("start:" + matcher.start());
            System.out.println("end:" + matcher.end());
        }

        sb.setLength(0);
        sb.append("rpcReply>]]>]]>");
        if (matcher.find(0)) {  // index of "start" is needed
            System.out.println(matcher.groupCount());
            System.out.println("group:" + matcher.group());
            System.out.println("start:" + matcher.start());
            System.out.println("end:" + matcher.end());
        }
    }

    @Test
    public void testPattern_split() {
        String regex = "Java";
        Pattern pattern = Pattern.compile(regex);
        String str = "123Java456Java789Java";

        //
        String[] result = pattern.split(str); // lenght=3 (123,456,789)
        Arrays.stream(result).forEach(System.out::println);

        result = pattern.split(str, 10); // lenght=4 (123,456,789, )
        Arrays.stream(result).forEach(System.out::println);

        result = pattern.split(str, -2); // lenght=4 (123,456,789, )
        Arrays.stream(result).forEach(System.out::println);
    }

    @Test
    public void testMatcher_find_index() {
        String regex = "Java";
        Pattern pattern = Pattern.compile(regex);

        // matches
        Matcher matcher = pattern.matcher("Java"); // True
        System.out.println("Matchs Java: " + matcher.matches());
        matcher = pattern.matcher("Java1234"); // False
        System.out.println("Matchs Java1234: " + matcher.matches());

        // lookingAt
        matcher = pattern.matcher("Java1234"); // True
        System.out.println("LookingAt Java1234: " + matcher.lookingAt());
        matcher = pattern.matcher("1234Java"); // False
        System.out.println("LookingAt 1234Java: " + matcher.lookingAt());

        // find
        matcher = pattern.matcher("Java"); // True
        System.out.println("Find in Java: " + matcher.find());
        matcher = pattern.matcher("12Java34"); // True
        System.out.println("Find in 12Java34: " + matcher.find());

        // find str from fixed-index
        matcher = pattern.matcher("1234Java"); // True
        System.out.println(matcher.find(3)); // find from index=3
        matcher = pattern.matcher("1234Java"); // True
        System.out.println(matcher.find(4));
        matcher = pattern.matcher("1234Java"); // False
        System.out.println(matcher.find(5));
        matcher = pattern.matcher("1234Java"); // False
        System.out.println(matcher.find(6));
    }

    @Test
    public void testMatcher_find_group_start_end() {
        Pattern pattern = Pattern.compile("(Java)(Python)");
        Matcher matcher = pattern.matcher("123JavaPython456");
        if (matcher.find()) {
            System.out.println(matcher.groupCount()); // 2

            System.out.println("group: " + matcher.group());

            System.out.println("group(1): " + matcher.group(1)); //Java, 返回第一组匹配到的字符串"Java"，注意起始索引是1
            System.out.println(matcher.start(1)); // 3，第一组起始索引
            System.out.println(matcher.end(1)); // 7 第一组结束索引

            System.out.println("group(2): " + matcher.group(2)); //Python, 返回第二组匹配到的字符串"Python"
            System.out.println(matcher.start(2)); // 7，第二组起始索引
            System.out.println(matcher.end(2)); // 13 第二组结束索引
        }
    }

    @Test
    public void testMatcher_reset() {
        Pattern pattern = Pattern.compile("Java");
        Matcher matcher = pattern.matcher("JavaDemo123");

        matcher.find();
        System.out.println(matcher.group()); //返回Java

        matcher.reset(); // 从起始位置重新匹配
        matcher.find();
        System.out.println(matcher.group()); //返回Java

        matcher.reset("Python");
        System.out.println(matcher.find()); //返回false
    }

    @Test
    public void testMatcher_replaceAll() {
        Pattern pattern = Pattern.compile("Java");
        Matcher matcher = pattern.matcher("JavaJava");
        System.out.println(matcher.replaceAll("Python"));// PythonPython
        System.out.println(matcher.replaceFirst("python"));// PythonJava
    }

    @Test
    public void testMatcher_appendXXX() {
        Pattern pattern = Pattern.compile("Java");
        Matcher matcher = pattern.matcher("12Java34");

        if (matcher.find()) {
            StringBuffer sb = new StringBuffer();

            matcher.appendReplacement(sb, "Python");
            System.out.println("appendReplacement: " + sb); // 12Python

            matcher.appendTail(sb);
            System.out.println("appendTail: " + sb); // 12Python34
        }
    }
}
