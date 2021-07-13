package top.yms.recent.code4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Code403 {

    private static String jsonRead() {
        final String NEW_FILE2 = "E:\\勿删桌面\\needChuli.txt";
        List<String> chName = new ArrayList<>();
        List<String> enName = new ArrayList<>();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(NEW_FILE2));

            StringBuilder json = new StringBuilder(new String());
            String tmp = new String();
            int cnt = 0;
            while ((tmp = bufferedReader.readLine()) != null) {
                cnt++;
                if (cnt == 1) {
                    String[] split = tmp.split("\\t");
                    chName.add(split[split.length -1]);
                    //System.out.println(split[split.length -1]);
                }
                if (cnt == 2) {enName.add(tmp);
                    //System.out.println(tmp);
                }

                if (cnt == 3) {
                    //System.out.println(tmp);
                }

                if (cnt==4) {
                    //System.out.println(tmp);
                }

                if (cnt == 5) {
                   System.out.println(tmp);
                }


                if(cnt == 5) cnt = 0;
            }

            bufferedReader.close();
            return json.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "[]";
    }

    public static void main(String[] args) {
        jsonRead();
    }


}
