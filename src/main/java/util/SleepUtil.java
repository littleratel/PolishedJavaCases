package util;

public class SleepUtil {

    public static void sleep(int timeToSleepInSeconds) {
        sleepInMillis(timeToSleepInSeconds * 1000);
    }

    public static void sleepInMillis(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
