package top.yms.recent.c202107;

public class Code001 {
    //test1: "flower","flow","flight" => "fl"
    //test2: "dog","racecar","car" => ""

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 1) return "";
        String match = strs[0];
        char [] tmp = new char[200];
        for(int i=1; i<strs.length; i++) {
            String tmpMath = strs[i];
            int ii = 0;
            int minLen = Math.min(tmpMath.length(), match.length());
            for(int j=0; j<minLen; j++) {
                if (match.charAt(j) == tmpMath.charAt(j)) {
                    tmp[ii++] = match.charAt(j);
                } else {
                    break;
                }
            }
            match = new String(tmp,0,ii);
            //如果是空串 直接返回
            if(match.equals("")) return "";
        }
        return match;
    }


    public static void main2(String[] args) {
        char [] chars = new char[]{'a','b','c','d','e','f'};
        String st = new String(chars,0,3);
        System.out.println(st);
    }

    public static void main(String[] args) {

        Code001 code001 = new Code001();

        String [] strs1 = new String[]{"flower","flow","flight"};
        System.out.println(code001.longestCommonPrefix(strs1));

        String [] strs2 = new String[]{"dog","racecar","car"};
        System.out.println(code001.longestCommonPrefix(strs2));

        String [] strs3 = new String[]{"flower","flow","flight","floweg","fd"};
        System.out.println(code001.longestCommonPrefix(strs3));


    }
}
