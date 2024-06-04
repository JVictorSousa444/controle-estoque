package gmail.davidsousalves.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DataUtils {

    public static Date converterLocalDateTimeParaDate(LocalDateTime  localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
