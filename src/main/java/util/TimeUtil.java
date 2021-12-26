package util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class TimeUtil {

    private static final String ZONE_SWEDISH = "CET";
    private static final DateTimeFormatter dtf = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .toFormatter();

    public static String now() {
        return dtf.format(LocalDateTime.now(ZoneId.of(ZONE_SWEDISH)));
    }

    /**
     * Calculate the time difference from the oldDate to now.
     *
     * @param oldDate // "2019-11-18 04:38:28";
     * @return time difference, time unit is hour.
     */
    public static float timeDifferenceFromNowToHour(String oldDate) {

        LocalDateTime old = LocalDateTime.parse(oldDate, dtf);
        LocalDateTime now = LocalDateTime.now(ZoneId.of(ZONE_SWEDISH));
        Duration re = Duration.between(old, now);

        return (float) re.getSeconds() / (60 * 60);
    }

    public static void sleepInMinutes(long m) {
        sleep(m * 60 * 1000);
    }

    public static void sleepInSeconds(long s) {
        sleep(s * 1000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
