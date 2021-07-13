package top.yms.recent.code9;

import java.util.LinkedHashMap;
import java.util.Map;

public class Code906 {

    private char [] charOrderd = null; //save char 顺序
    private int [] charIndexToCount= null; //保存字符数
    private int newIndex = 0;

    private void init(int len) {
        newIndex = 0;
        charOrderd = new char[len];
        charIndexToCount = new int[256];
    }

    private int findChar(char c) {

        for(int i=0; i<newIndex; i++) {
            if (c == charOrderd[i]) {
                return i;
            }
        }
        return -1;
    }





    private void put(char c) {
        if (findChar(c) != -1) {
            charIndexToCount[(int)c]++;
        } else { //如果不存在
            charOrderd[newIndex++] = c;
            charIndexToCount[(int)c]++;
        }
    }

    private char findFirst() {
        for(int i=0; i<newIndex; i++) {
            if (charIndexToCount[charOrderd[i]] == 1) {
                return charOrderd[i];
            }
        }
        return ' ';
    }

    public char firstUniqChar(String s) {
        if (s != null && s.length() > 0) {
            init(s.length());
            for(char c : s.toCharArray()) {
                put(c);
            }
            return findFirst();
        }
        return ' ';
    }


    public char firstUniqChar1(String s) {
        if (s != null && s.length() > 0) {
            Map<Character, Integer> orderedMap = new LinkedHashMap<>();
            for(char c : s.toCharArray()) {
                orderedMap.merge(c, 1, Integer::sum);
            }

            for(Map.Entry<Character, Integer> entry : orderedMap.entrySet() ) {
                Integer value = entry.getValue();
                if (value.compareTo(1) == 0) {
                    return entry.getKey();
                }
            }
        }

        return ' ';
    }

    //. 第一个只出现一次的字符
    //
    //在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
    //
    //示例:
    //
    //s = "abaccdeff"
    //返回 "b"
    //
    //s = ""
    //返回 " "
    //
    //
    //
    //限制：
    //
    //0 <= s 的长度 <= 50000
    //通过次数111,730
    //提交次数182,516
    public static void main(String[] args) {
        Code906 code906 = new Code906();

        String str = "abaccdeff";

        char c = code906.firstUniqChar(str);
        System.out.println(c);
    }

}
