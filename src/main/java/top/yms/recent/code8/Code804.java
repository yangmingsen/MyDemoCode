package top.yms.recent.code8;

public class Code804 {

    static class ListNode {
        int val;
        ListNode nextNode;

        public ListNode(int val) {
            this.val = val;
            this.nextNode = null;
        }
    }


    static ListNode reverstNode(ListNode listNode) {
        if (listNode == null)return listNode;
        ListNode pre = null;
        ListNode cur = listNode;
        ListNode res = null;

        while (cur != null) {
            ListNode nextNode = cur.nextNode;
            if (nextNode == null) {
                res = cur;
            }
            cur.nextNode = pre;
            pre = cur;
            cur = nextNode;
        }

        return res;
    }

    public static void main(String[] args) {

        for(int i=0; i<50; i++) {
            System.out.println("theFun("+i+")="+theFun(i));
            System.out.println("theRec("+i+")="+theRec(i));
            System.out.println();
        }

    }


    static int theRec(int n) {
        if (n < 0) return -1;
        if (n <3) return 1;

        return theRec(n-1)+theRec(n-2);
    }

    static int theFun(int n) {
        if (n < 0) return -1;
        if (n < 3) return 1;
        int [] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;


        for(int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }




}
