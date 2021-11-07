package top.yms.recent.c202110;

import org.junit.jupiter.api.Test;
import top.yms.past11.solu.TreeNode;

import java.util.*;

public class Code202111011034 {

    private static class Number implements Comparable{
        int num;
        String name;
        int age;

        public Number(int num, String name, int age) {
            this.num = num;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "num=" + num +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            Number other = (Number)o;
            if (this.num < other.num) return -1;
            else return 1;
        }
    }

    public static int[] randomNumbers(int n) {
        int [] res = new int[n];

        Random random = new Random(30);
        for(int i=0; i<n; i++) {
            res[i] = random.nextInt(20);
        }
        return res;
    }

    private static Number[] randomNumber(int n) {
        Number [] res = new Number[n];
        Random random = new Random(30);
        for(int i=0; i<n; i++) {
            int num = random.nextInt(20);
            res[i] = new Number(num,"name"+num, num+1);
        }
        return res;
    }

    private static List<Number> getRandomNumber(int n) {
        return Arrays.asList(randomNumber(n));
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    @Test
    public void doTest() {

        //Number[] numbers = randomNumber(20);
        int[] numbers = randomNumbers(20);
        System.out.println(Arrays.toString(numbers));
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(16,0.75f,true);
        for(int num : numbers) {
            Integer total = map.get(num);
            if (total != null) {
                map.put(num, total+1);
            } else {
                map.put(num, 1);
            }
        }

        map.forEach( (x,y) -> System.out.println(x+" ,"+y));



    }

    public static void main1(String[] args) {

        int[] arr = randomNumbers(10);

        Arrays.sort(arr);

        Number[] numbers = randomNumber(10);

        Collections.sort(Arrays.asList(numbers), (o1, o2) -> {
            if (o1.num < o2.num) return 1;
            return 0;
        });

        TreeMap <Integer, Number> map = new TreeMap<Integer, Number>((o1, o2) -> {
            if (o1 < o2) return -1;
            else return 1;
        });


        for(Number number : numbers) {
           int hashCode =  15& hash(number);
           map.put(hashCode, number);
        }

        SortedMap<Integer, Number> sortedMap = map.subMap(3, 20);
        sortedMap.forEach((x,y) -> System.out.println(x));


    }
}
