package util.helpers;

/**
 * Helper class that contains method for checking which OS is uses.
 * */
public class OsHelper {
    private static final String OS = System.getProperty("os.name").toLowerCase();

    private OsHelper() {
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("sunos");
    }

    public static boolean isWindows() {
        return OS.contains("win");
    }
}
