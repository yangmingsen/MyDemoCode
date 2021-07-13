package top.yms.recent.code2;

import java.util.LinkedList;

public class Code22 {

    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        theTestCase(n,m);
    }

    private static void theTestCase(int n, int m) {
        if (n < 1) return;
        if (m < 1) return;
        if (m >= n) {}//how to do}

        System.out.println(doJosephus(n,m)+1);
        System.out.println(doJosephusCir(n,m));
        System.out.println(doJosephusSimulate(n,m));
    }


    /**
     *
     * 模拟实现
     * time => O(nm)
     * memory => O(n)
     * @param n
     * @param m
     * @return
     */
    private static int doJosephusSimulate(int n, int m) {
        LinkedList<Integer> pList = new LinkedList<>();
        for (int i = 1; i <= n ; i++) {
            pList.add(i);
        }
        int i = 0;
        while (n > 1) {
            for (int j = 0; j < m; j++) {
                i++;
            }
            i = (i-1)%n;
            pList.remove(i);
            n--;
        }
        return pList.get(0);
    }


    /**
     * 递归实现(最后结果需要加1)
     * time => O(n)
     * memory => O(1)
     * @param n
     * @param m
     * @return
     */
    private static int doJosephus(int n, int m) {
        if (n <= 1) return 0;
        return (doJosephus(n-1,m)+m)%n;
    }

    /**
     * 循环实现
     * time => O(n)
     * memory => O(1)
     * @param n
     * @param m
     * @return
     */
    private static int doJosephusCir(int n,int m)
    {
        int p=0;
        for(int i=2;i<=n;i++)
        {
            p=(p+m)%i;
        }
        return p+1;
    }

}
