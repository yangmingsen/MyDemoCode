package top.yms.recent.c202101_07.code1;

import java.util.ArrayList;
import java.util.List;

public class Code19 {
    public static void main(String[] args) {
        System.out.println(RoleEnum.SYSADMIN.getRoleCode());
    }
    public static void main1(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> list = new ArrayList<>();
        int size = list.size();
        System.out.println(size);
        String sho =null;

        for(String str : list) {
            stringBuilder.append(str);
        }
        sho = stringBuilder.toString();
        System.out.println("x="+sho);



    }
}
