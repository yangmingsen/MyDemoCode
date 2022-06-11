package top.yms.recent.c202206;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class Code0602 {

    static
    class MyCircularDeque {
        LinkedBlockingDeque lbd;
        private int size;

        public MyCircularDeque(int k) {
            lbd = new LinkedBlockingDeque(k);
            size = k;
        }

        public boolean insertFront(int value) {
            try {
                lbd.addFirst(value);
            } catch (Exception e) {
                return false;
            }
             return true;
        }

        public boolean insertLast(int value) {
            try {
                lbd.addLast(value);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        public boolean deleteFront() {
            try {
                lbd.removeFirst();
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        public boolean deleteLast() {
            try {
                lbd.removeLast();
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        public int getFront() {
            try {
                return (Integer) lbd.getFirst();
            } catch (Exception e) {
                return -1;
            }
        }

        public int getRear() {
            try {
                return (Integer) lbd.getLast();
            } catch (Exception e) {
                return -1;
            }
        }

        public boolean isEmpty() {
            return lbd.isEmpty();
        }

        public boolean isFull() {
            return lbd.size() == size;
        }
    }


    @Test
    public void testS() {

        Integer [] tst = {9,1,2,3,4,5,6,7};

        Arrays.sort(tst, (o1, o2) -> {
            int r = o1 - o2;
            return r;

        });

        System.out.println(Arrays.toString(tst));


    }

    @Test
    public void test1641() {
        int [][] tst1 = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        pt1(tst1);
        pt1(reconstructQueue(tst1));


    }


    public void pt1(int [][] arr) {
        for(int [] as : arr) {
            System.out.print(Arrays.toString(as)+",");
        }
        System.out.println();
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
       pt1(people);
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }




}
