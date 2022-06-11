package top.yms.recent.c202205;

import org.junit.jupiter.api.Test;
import top.yms.utils.ListNode;

import java.util.*;

public class Code202205301018 {


    @Test
    public void test1141() {
        int [] arr = {1,4,3,2,5,2};
        int [] arr2 = {2,1};
        int [] arr3 = {1,2};
        ListNode listNode = ListNode.create(arr);

        ListNode.print(listNode);

        ListNode res = partition2(listNode, 3);

        ListNode.print(listNode);

    }

    public ListNode partition2(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }



    public ListNode partition(ListNode head, int x) {
        if (head == null) return head;

        ListNode th = head;
        int n = 0;
        while (th != null) {
            n++;
            th = th.next;
        }
        if(n == 1) return head;

        int [] arr = new int[n];

        int i=0;
        th = head;
        while (th != null) {
            arr[i++] = th.val;
            th = th.next;

        }

        int fi = -1;
        for(int q =0; q<n; q++) {
            if (arr[q] >= x) {
                fi = q;
                break;
            }
        }
        if(fi == -1) return head;


        //cp
        for(int j = fi+1; j<n; j++) {
            if (arr[j] < x) {
                int aa = arr[j];
                for(int k = j; k>=fi && k>0; k--) {
                    arr[k] = arr[k-1];
                }
                arr[fi++]=aa;
            }
        }

        th = head;
        int d = 0;
        while (th  != null) {
            th.val = arr[d++];
            th = th.next;
        }

        return  head;

    }


    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < s.length() - all_len + 1; i++) {
            String tmp = s.substring(i, i + all_len);
            HashMap<String, Integer> tmp_map = new HashMap<>();
            for (int j = 0; j < all_len; j += one_word) {
                String w = tmp.substring(j, j + one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
            }
            if (map.equals(tmp_map)) res.add(i);
        }
        return res;
    }



    public void pL(List<List<String>> ll) {
        ll.forEach( x -> {
            System.out.print(x+",");
        });
        System.out.println();
    }

    @Test
    public void test1024() {
        String [] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        pL(groupAnagrams(strs));

        String [] strs2 = {""};
        pL(groupAnagrams(strs2));


        String [] strs3 = {"a"};
        pL(groupAnagrams(strs3));
    }


    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>>  res = new ArrayList<>();

        int sLen = strs.length;

        if (sLen == 1) {
            res.add( new ArrayList<String>() {{
                add(strs[0]);
            }});

            return res;
        }


        Map<String, List<String>> mL = new HashMap<>();
        for (int i=0; i<sLen; i++) {
            char [] cs = strs[i].toCharArray();
            Arrays.sort(cs);

            String nS = new String(cs);

            List<String> mls = mL.get(nS);
            if (mls == null) {
                mls = new ArrayList<>();
                mL.put(nS, mls);
            }
            mls.add(strs[i]);
        }

        mL.forEach( (x,y) -> res.add(y));


        return res;
    }
}
