package top.yms.utils;


public class BitMap { // Java中char类型占16bit，也即是2个字节
    private char[] bytes;
    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits/16+1];
    }

    public void set(int k) {
        if (k > nbits) return;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] |= (1 << bitIndex);
    }

    public boolean get(int k) {
        if (k > nbits) return false;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }

    public void printBinary(int k) {
        StringBuilder str =new StringBuilder();
        if (k == 0) {System.out.println("0");return;}

        int k1 = k;
        while (k>0) {
            int r = k&1;
            str.append(r);
            k = k >> 1;
        }
        System.out.println(k1+"="+str.reverse());
    }


    public static void main(String[] args) {
        BitMap bitMap = new BitMap(160);
        bitMap.set(70);
        bitMap.set(71);
        bitMap.set(72);
        bitMap.set(73);



    }

}
