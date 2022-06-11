package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Code002 {


    @Test
    public void testLpad() {
        System.out.println(lpad("hi",4,"??"));
        System.out.println(lpad("hi",1,"??"));
        System.out.println(lpad("hi",2,"??"));
        System.out.println(lpad(null,2,"??"));
        System.out.println(lpad("",2,"??"));
        System.out.println(lpad("hi",7,"??x"));
        System.out.println(lpad("hi",3,"??x"));
    }

    @Test
    public void test01() {
        String d1 = toChar(new Date(), "yyyyMMddHH24miss");
        String d2 = "20211217095015";
        System.out.println(d1.compareTo(d2));
        System.out.println(d2.compareTo(d1));
    }

    @Test
    public void test() {
        System.out.println(toChar(new Date(), "yyyymmddhh24missSSS"));
        System.out.println(toChar(new Date(), "yyyyMMddHH24miss"));
        System.out.println(toChar(new Date(), "yyyymmdd"));
        System.out.println(toChar(dateTimeOp(new Date(),-10,Calendar.MINUTE),"hh24:mi:ss"));
        String l_sqnodt = "20211215";

        System.out.println(toChar(dateTimeOp(toDate(l_sqnodt),1,Calendar.DATE), "yyyymmdd"));
        System.out.println(toChar(dateTimeOp(toDate(l_sqnodt),1,Calendar.MONTH), "yyyymmdd"));
        System.out.println(toChar(dateTimeOp(toDate(l_sqnodt),12,Calendar.MONTH), "yyyymmdd"));
    }

    private static String lpad(String str, long padded_length1, String pad_string ) {

        int padded_length = Integer.parseInt(padded_length1+"");

        if (str == null || "".equals(str)) return str;

        int strLen = str.length();

        if (strLen == padded_length) return str;
        else if (strLen > padded_length) {
            return str.substring(0,padded_length);
        } else {
            int pad_len = pad_string.length();
            int differLen = padded_length - strLen;

            char [] lcs = new char[differLen];
            int lci = 0;
            do {
                for (int i = 0; i < pad_len; i++) {
                    lcs[lci++] = pad_string.charAt(i);
                    if (lci == differLen) break;
                }
            } while (lci != differLen);

            return new String(lcs)+str;
        }

    }

    //yyyymmddhh24missSSS -> yyyy-MM-dd HH:mm:ss ->yyyyMMddHHmmss
    //hh24:mi:ss => HH:mm:ss
    /**
     * oracle 时间转换仿写
     * @param date
     * @param pattern
     * @return
     */
    private static String toChar(Date date,String pattern) {
        if (pattern.equals("yyyymmddhh24missSSS")|| pattern.equals("yyyyMMddHH24miss")) {
            pattern = "yyyyMMddHHmmss";
        }
        if (pattern.equals("hh24:mi:ss")) {
            pattern = "HH:mm:ss";
        }
        if (pattern.equals("yyyymmdd")) {
            pattern = "yyyyMMdd";
        }

        return new SimpleDateFormat(pattern).format(date);
    }


    //dateStr format must be yyyyMMdd
    /**
     * 将yyyyMMdd格式解析为java Date
     * @param
     * @return
     */
    private static Date toDate(String dateString) {
        try {
            String dateStr =dateString.substring(0, 4)+"-"+dateString.substring(4, 6)+"-"+dateString.substring(6);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算时间
     * @param date 日期
     * @param nums 操作的数（可以是任何日期时间),负数表示减法,正数为加法
     * @param op 操作的对象：如Calendar.MINUTE
     * @return date
     */
    private static Date dateTimeOp(Date date, int nums, int op) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(op,nums);
        Date date1 = new Date(calendar.getTimeInMillis());

        return date1;
    }


}
