package util;

public class StringUtils {
    public static String eol() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            return "\r\n";
        } else {
            return "\n";
        }
    }
}
