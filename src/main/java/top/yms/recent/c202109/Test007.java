package top.yms.recent.c202109;

import java.util.ArrayList;
import java.util.List;

public class Test007 {

    static class Section {
        long start;
        long end;

        public Section(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Section{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


    public static List<Section> compSection(long total, int core) {

        long avg = total/core;
        long sp = 0;

        List<Section> list = new ArrayList<>();
        for(int i=0; i<core; i++) {
            if (i+1!=core) {
                list.add(new Section(sp,sp+avg));
                sp = sp+avg;
            } else {
                list.add(new Section(sp, sp+(total-sp)));
            }
        }

        return list;
    }

    public static void main(String[] args) {
        long total = 1001;
        int core = 8;

        compSection(total, core).stream().forEach(System.out::println);

    }
}
