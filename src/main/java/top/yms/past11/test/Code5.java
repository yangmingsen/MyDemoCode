package top.yms.past11.test;

public class Code5 {
    public static void main(String[] args) {
        String [] dates = new String[15];
        for (int i = 0; i < 15; i++) {
            dates[i] = "2020-05-"+(10+i);
        }

        String str = "2020-05-19";
        int idx = -1;
        for (int i = 0; i < 15; i++) {
            if (dates[i].equals(str)) {
                idx = i;
                break;
            }
        }

        System.out.println(idx);



    }
}
