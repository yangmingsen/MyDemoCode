package top.yms.past11.code;

public class Code21 {
    public int findMaxForm(String[] strs, int m, int n) {
        sLen = strs.length;
        if (sLen == 1) return 1;
        mm = m;
        nn = n;

        map = new int[601];
        int ans = doRec(strs, 0, 0, 0);
        if (m1 == 0 || n1 == 0) {
            return 0;
        }
        return ans;

    }

    int sLen = -1;
    int mm = -1;
    int nn = -1;
    int[] map = null;
    boolean flag = false;

    int m1 = -1;
    int n1 = -1;

    int doRec(String[] strs, int k, int m, int n) {
        if (m == mm && n == nn) {
            return 0;
        }
        if (k >= sLen | m > mm | n > nn) return 0;

      //  if (map[k] != 0) return map[k];

        String str = strs[k];
        int tLen = str.length();
        int tm = m;
        int tn = n;
        for (int i = 0; i < tLen; i++) {
            char c = str.charAt(i);
            if (c == '0') m++;
            else
                n++;
        }
        m1 = m;
        n1 = n;

        int choose = -1;
        if (m <= mm && n <= nn) {
            choose = doRec(strs, k + 1, m, n) + 1;
        }
        int unChoose = doRec(strs, k + 1, tm, tn);

        int max = Math.max(choose, unChoose);
      //  map[k] = max;

        return max;
    }

    public static void main(String[] args) {
        Code21 code21 = new Code21();
        String[] strs1 = {"10", "0001", "111001", "1", "0"};
        String[] strs2 = {"10", "0", "1"};
        String[] strs3 = {"10"};
        String[] strs33 = {"0"};
        String[] strs4 = {"00", "000", "0000", "0"}; // => 0
        String [] strs5 = {"0","0","1","1"};
        String [] strs6 = {"001", "110","0000","0000"};
        //m = 9, n =2; right => 3 ; my => 2
        String [] strs7 = {"111","1000","1000","1000"};
        //m=9,n=3; right => 3 ; my=2;

        int m = 50;
        int n = 44;
        System.out.println(code21.findMaxForm(strs6, 9, 2));
    }


}
