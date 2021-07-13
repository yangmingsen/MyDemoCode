package top.yms.past11.view;

import java.util.ArrayList;

public class Data10B7 {

    public static String toFixString(double left) {
        return String.format("%.1f",left)+"%";
    }


    public static void calTwo() {
        int startCode = 288;
        String cls = "rotate270"; //rotate180
        String left = "90.5%";

        double hgt = 31;
        int count = 6;
        double res = hgt/count;

        double top =16d;
        ArrayList<ViewModel> bs = new ArrayList<>();
        bs.add(new ViewModel(startCode,cls,toFixString(top), left));

        for (int i = 1; i < count; i++) {
            top = top+res;
            bs.add(new ViewModel(startCode+i,cls,toFixString(top), left));
        }
        bs.stream().forEach(x -> System.out.println(x.toString()));

    }

    public static void calOne() {
        int startCode = 283;
        String cls = "rotate90"; //rotate180
        String left = "51.5%";

        double hgt = 28;
        int count = 5;
        double res = hgt/count;

        double total =3.8d;
        ArrayList<ViewModel> bs = new ArrayList<>();
        bs.add(new ViewModel(startCode,cls,toFixString(total), left));

        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new ViewModel(startCode+i,cls,toFixString(total), left));
        }


        startCode = 278;
        cls = "rotate270";
        left = "60%";
        total = 3.8d;

        bs.add(new ViewModel(startCode,cls,toFixString(total), left));
        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new ViewModel(startCode+i,cls,toFixString(total), left));
        }


        startCode = 273;
        cls = "rotate90";
        left = "68.5%";
        total = 3.8d;

        bs.add(new ViewModel(startCode,cls,toFixString(total), left));
        for (int i = 1; i < count; i++) {
            total = total+res;
            bs.add(new ViewModel(startCode+i,cls,toFixString(total), left));
        }



        bs.stream().forEach(x -> System.out.println(x.toString()));
    }

    public static void calThree() {
        int startCode = 310;
        String cls = "rotate180"; //rotate180
        String top = "38%";

        double wdt = 63;
        int count = 9;
        double res = wdt/count;

        double left =22.4d;
        ArrayList<ViewModel> bs = new ArrayList<>();
        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));

        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }


        cls = "";
        top = "44.8%";
        startCode=319;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }


        bs.stream().forEach(x -> System.out.println(x.toString()));
    }


    public static void calThreeDotOne() {
        int startCode = 301;
        String cls = ""; //rotate180
        String top = "34.5%";

        double wdt = 59;
        int count = 8;
        double res = wdt/count;

        double left =26.4d;
        ArrayList<ViewModel> bs = new ArrayList<>();
        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));

        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }

        bs.stream().forEach(x -> System.out.println(x.toString()));
    }


    public static void calFour() {
        int startCode = 326;
        String cls = "rotate180"; //rotate180
        String top = "50%";

        double wdt = 57;
        int count = 7;
        double res = wdt/count;

        double left =22.4d;
        ArrayList<ViewModel> bs = new ArrayList<>();
        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));

        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }


        cls = "";
        top = "56.6%";
        startCode=333;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }


        //two
        cls = "rotate180";
        top = "61.1%";
        startCode=340;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }

        cls = "";
        top = "67.6%";
        startCode=347;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }


        //three
        cls = "rotate180";
        top = "72.1%";
        startCode=354;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }

        cls = "";
        top = "78.5%";
        startCode=361;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }


        //foure
        cls = "rotate180";
        top = "82.9%";
        startCode=368;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }

        cls = "";
        top = "89.3%";
        startCode=375;
        left =22.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }



        bs.stream().forEach(x -> System.out.println(x.toString()));
    }

    public static void calFive() {
        int startCode = 377;
        String cls = "rotate180"; //rotate180
        String top = "66.6%";

        double wdt = 15;
        int count = 2;
        double res = wdt/count;

        double left =3.4d;
        ArrayList<ViewModel> bs = new ArrayList<>();
        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));

        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }


        cls = "";
        top = "73%";
        startCode=379;
        left =3.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }



        cls = "rotate180";
        top = "77.1%";
        startCode=381;
        left =3.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }

        cls = "";
        top = "83.2%";
        startCode=383;
        left =3.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }

        cls = "rotate180";
        top = "89.1%";
        startCode=385;
        left =3.4d;

        bs.add(new ViewModel(startCode,cls,top, toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+res;
            bs.add(new ViewModel(startCode-i,cls,top, toFixString(left)));
        }




        bs.stream().forEach(x -> System.out.println(x.toString()));

    }





    public static void main(String[] args) {
        calThreeDotOne();
    }
}
