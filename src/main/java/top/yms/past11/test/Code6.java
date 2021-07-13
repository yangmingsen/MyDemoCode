package top.yms.past11.test;


import java.util.ArrayList;

class B {
    private int code;
    private String classs;
    private String top;
    private String left;

    public B(int code, String classs, String top, String left) {
        this.code = code;
        this.classs = classs;
        this.top = top;
        this.left = left;
    }

    @Override
    public String toString() {
        return "{" +
                "code: \'" + code +"\'"+
                ", class: \'" + classs + '\'' +
                ", top: \'" + top +"\'"+
                ", left: \'" + left +"\'"+
                "},";
    }
}

public class Code6 {


    public static void oneCal() {
        int startCode = 149;
        String cls = ""; //rotate180
        String top = "24%";

        double wid = 58;
        int count = 7;
        double res = wid/count;

        double total =2.0d;
        ArrayList<B> bs = new ArrayList<>();
        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        bs.stream().forEach(x -> System.out.println(x.toString()));

    }



    public static void twoCal() {
        int startCode = 204;
        String cls = "rotate180"; //rotate180
        String top = "52.1%";

        double wid = 74;
        int count = 11;
        double res = wid/11;

        double total =2.0d;
        ArrayList<B> bs = new ArrayList<>();
        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < 11; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }
        //-------------------------------------
        startCode = 215;
        cls = "";
        top = "58.5%";
        total =2.0d;

        bs.add(new B(startCode,cls,top, getLeft(total)));
        for (int i = 1; i < 11; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        bs.stream().forEach(x -> System.out.println(x.toString()));
    }


    public static void Test() {
        int startCode = 222;
        String cls = "rotate180"; //rotate180
        String top = "64.6%";

        double wid = 51d;
        int count = 7;
        double res = wid/count;

        double total =25.7d;
        ArrayList<B> bs = new ArrayList<>();
        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        bs.stream().forEach(x -> System.out.println(x.toString()));

    }

    public static void main(String[] args) {
        fourCal();
    }


    public static void fourCal() {
        int startCode = 260;
        String cls = "rotate180"; //rotate180
        String top = "62.5%";

        double wid = 20.0d;
        int count = 3;
        double res = wid/count;

        double total = 2.0d;
        ArrayList<B> bs = new ArrayList<>();
        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }


        startCode = 263;
        cls = "rotate180";
        top = "70.7%";
        total =2.0d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        startCode = 266;
        cls = "";
        top = "77.1%";
        total =2.0d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        ///----------two end



        startCode = 269;
        cls = "rotate180";
        top = "82%";
        total =2.0d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        startCode = 272;
        cls = "";
        top = "88.2%";
        total =2.0d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        ///----------three end
        bs.stream().forEach(x -> System.out.println(x.toString()));


    }


    public static void threeCal() {
        int startCode = 222;
        String cls = "rotate180"; //rotate180
        String top = "64.6%";

        double wid = 51d;
        int count = 7;
        double res = wid/count;

        double total =25.7d;
        ArrayList<B> bs = new ArrayList<>();
        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }
        //-------------------------------------
        startCode = 229;
        cls = "";
        top = "71.2%";
        total =25.7d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }


        ///----------one end



        startCode = 236;
        cls = "rotate180";
        top = "75%";
        total =25.7d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        startCode = 243;
        cls = "";
        top = "81.6%";
        total =25.7d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        ///----------two end



        startCode = 250;
        cls = "rotate180";
        top = "86.1%";
        total =25.7d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }

        startCode = 257;
        cls = "";
        top = "92.6%";
        total =25.7d;

        bs.add(new B(startCode,cls,top, getLeft(total)));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new B(startCode-i,cls,top ,getLeft(total)));
        }


        //three end

        bs.stream().forEach(x -> System.out.println(x.toString()));




    }

    public static String getLeft(double left) {
        return String.format("%.1f",left)+"%";
    }

}
