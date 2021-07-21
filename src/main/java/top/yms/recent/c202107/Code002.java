package top.yms.recent.c202107;

public class Code002 {

    //   haystack == ""   && needle == ""
    //   haystack == ""   && needle == "12345"
    //   haystack == "12" && needle = ""
    //   haystack == "12" && needle = "1"
    public int strStr(String haystack, String needle) {
        if ( haystack.equals("") && needle.equals("")) return 0;
        if ( needle.equals("")) return 0;

        return haystack.indexOf(needle);
    }
}
