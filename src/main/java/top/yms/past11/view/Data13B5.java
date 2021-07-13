package top.yms.past11.view;

import java.util.ArrayList;
import java.util.List;

public class Data13B5 {
    public static String toFixString(double left) {
        return String.format("%.1f",left)+"%";
    }

    private static double left = 20.2d;
    private static double [] top = {15.7, 21.7, 27.6, 33.6, 39.6, 45.6,  51.6, 57.6, 63.6, 69.6, 75.3, 81.3};
    private static int [] code = new int[12];

    private static double wdt = 80;
    private static int count = 10;
    private static double num = wdt/count;

    private static List<ViewModel> list = new ArrayList<>();

    private static int currIdx = 0;

    public static void calTop() {
        left = 20.2d;
        list.add(new ViewModel(code[currIdx],"rotate180",toFixString(top[currIdx]), toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+num;
            list.add(new ViewModel(code[currIdx]+i,"rotate180",toFixString(top[currIdx]), toFixString(left)));
        }
    }

    public static void calButtom() {
        left = 20.2d;
        list.add(new ViewModel(code[currIdx],"",toFixString(top[currIdx]), toFixString(left)));
        for (int i = 1; i < count; i++) {
            left = left+num;
            list.add(new ViewModel(code[currIdx]+i,"",toFixString(top[currIdx]), toFixString(left)));
        }
    }

   public static void init() {
       code[0] = 386;
       for(int i=1; i<12; i++) {
           code[i] = code[i-1]+10;
       }
   }

    public static void main(String[] args) {
        init();

        for (int i=1; i<=12; i++) {
            if (i%2 == 0) {
                calButtom();
            } else {
                calTop();
            }
            currIdx++;
        }

        list.stream().forEach(x -> System.out.println(x.toString()));
    }

}
