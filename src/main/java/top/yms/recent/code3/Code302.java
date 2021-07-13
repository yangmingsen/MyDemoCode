package top.yms.recent.code3;

public class Code302 {
    private static String capitalize(final String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        final char firstChar = str.charAt(0);
        final char newChar = Character.toTitleCase(firstChar);
        if (firstChar == newChar) {
            return str;
        }
        char[] newChars = new char[strLen];
        newChars[0] = newChar;
        str.getChars(1, strLen, newChars, 1);
        return String.valueOf(newChars);
    }

    public static void main(String[] args) throws Exception {
        int [] ids = new int[10];
        for (int i = 0; i < 10; i++) {
            ids[i]=i;
        }
        if (1 <- 5) {
            System.out.println();
        }
        System.out.println(-5);
        for(int idx=0; idx < -ids.length; idx++) {
            System.out.println(idx);
        }

    }

    private long getLong() throws Exception{
        int y = 10;
        long x = 10;
        try {
            return x;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出数据生成文件失败！");
        } finally {
            x = 11;
        }
    }


}
