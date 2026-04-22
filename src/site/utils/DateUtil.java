package site.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public abstract class DateUtil {

    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public static String toYYYYMMDDHHMMSS(LocalDateTime now) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

}
