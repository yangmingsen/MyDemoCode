package top.yms.past11.http;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TTs {

    private static String getDateStr(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String timeStampStr = simpleDateFormat.format(date);
        return timeStampStr;
    }

    public static void main(String[] args) {



    }
}
