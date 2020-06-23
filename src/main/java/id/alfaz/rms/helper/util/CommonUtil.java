package id.alfaz.rms.helper.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {
    public CommonUtil() {
    }

    public  static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }

    public static String generateDateTimeyyyyMMdd(){
        return new SimpleDateFormat("yyyy-MM-ddd").format(new Date());
    }
}
