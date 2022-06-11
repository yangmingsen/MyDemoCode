package top.yms.recent.c202205;

import org.junit.jupiter.api.Test;
import top.yms.utils.BitMap;

public class Code202205251012 {

    @Test
    public void test01() {
        BitMap bitMap = new BitMap(100);
        bitMap.set(100);

        System.out.println(bitMap.get(100));
        System.out.println(bitMap.get(99));
        bitMap.printBinary(255);
    }

}
