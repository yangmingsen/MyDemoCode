package top.yms.recent.c202108;

public class Code004 {
    //部分数值列举如下：
    //
    //["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
    //部分非数值列举如下：
    //
    //["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
    //

    private boolean hasAlpha(String str) {
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c) || Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberStr(String str) {
        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                 return false;
            }
        }
        return true;
    }

    public boolean isNumber(String s) {

        //判断包含 .
        if (s.indexOf(".") > 0) {
            //考虑是否包含字母
            if (hasAlpha(s)) {
                return false;
            }
            String trimStr = s.trim();

            //处理只有一个 . 的问题
            if (trimStr.length() < 2) return false;

            //考虑 . 位置
            int pointIdx = trimStr.indexOf(".");
            if (pointIdx == 0 && isNumberStr(trimStr.substring(1))) {
                return true;
            }
            

            //考虑 +和- 位置



        }

        //判断包含e或E
        if(s.indexOf("e") > 0 || s.indexOf("E") > 0) {

        }

        //判断是否为整数
        return false;

    }
}
