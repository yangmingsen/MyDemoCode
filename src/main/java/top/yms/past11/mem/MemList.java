package top.yms.past11.mem;

import java.util.ArrayList;
import java.util.List;

public class MemList {

    static class Mem {
        private String name = "hello world";
    }

    public static void main(String[] args) {
        List<Mem> list = new ArrayList<>();
        while (true) {
            list.add(new Mem());
        }

    }

}
