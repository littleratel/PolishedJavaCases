package cn.intv;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AMainTest {
    public static void main(String[] args) {
        String a = "Thu Nov  4 09:02:24 UTC 2021";
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
                .ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        ZonedDateTime time = ZonedDateTime.parse(a, DATE_TIME_FORMATTER);
        System.out.println(time);

//        DATE_TIME_FORMATTER.format(a);
//        TimeConverter.convertStringTimeToZonedDateTime(a, DATE_TIME_FORMATTER);
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        String s1 = "";
        int n = s.length();
        int ll = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += ll) {
                s1 += s.charAt(i + j);
                if (i != 0 && i != numRows - 1 && j + ll - i < n)
                    s1 += s.charAt(j + ll - i);
            }
        }

        return s1;
    }
}