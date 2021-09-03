package top.yms.recent.c202109;


import java.io.File;
import java.nio.charset.StandardCharsets;

public class Test003 {



    private static String replaceSeparator(String path) {
        String separator = File.separator;

        StringBuilder tmpStr = new StringBuilder();

        if (path.indexOf("\\") > 0) {
            String[] split = path.split("\\\\");
            for(String tt : split) {
                tmpStr.append(tt).append(separator);
            }
        } else {
            String[] split = path.split("/");
            for(String tt : split) {
                tmpStr.append(tt).append(separator);
            }
        }
        return tmpStr.toString();
    }

    public static void main(String[] args) {

        String separator = File.separator;
        System.out.println("se = "+separator);

        String s = "src\\main\\java\\top\\yms";
        System.out.println("sourceC="+s);

        String s1 = replaceSeparator(s);


        //System.out.println(s.substring(2,s.length()));
       // File file = new File(s);
       // file.mkdirs();
        

    }
}
