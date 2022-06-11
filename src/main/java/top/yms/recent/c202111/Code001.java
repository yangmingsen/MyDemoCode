package top.yms.recent.c202111;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Code001 {

    static class MyCode {
        private String name;

        @Override
        public String toString() {
            return "MyCode{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void test() {

        int [][] arr = new int[2][3];
        String [] [] strs = new String[2][3];

        MyCode [][] myCodes = new MyCode[2][3];

        Integer [][] ins = new Integer[2][3];

        for(int i=0; i<2; i++) {
            myCodes[i] = new MyCode[3];
            for(int j=0; j<3; j++) {
                myCodes[i][j] = new MyCode();
                strs[i][j] = i+"+"+j;
                ins[i][j] = i*j;
                myCodes[i][j].name = strs[i][j];
            }
        }

        System.out.println(strs);

    }

    @Test
    public void doTheTest() throws ParseException {
        String dt = "20211206";
//        Date date = new Date(dt);
//        System.out.println(date);
        Date yyyymmdd = new SimpleDateFormat("YYYYMMDD").parse(dt);
        System.out.println(yyyymmdd.toString());
        System.out.println(new SimpleDateFormat("YYYYMMDD").parse(dt).toString());
    }

}
