package top.yms.past11.code;

import java.util.ArrayList;
import java.util.List;

public class Code13 {
    static class Goods {
        public String name;
        public int weight;
        public int value;

        public Goods(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    static List<Goods> goods = new ArrayList<>();

    static {
        goods.add(new Goods("宝石",4,   50));
        goods.add(new Goods("剃须刀",5, 40));
        goods.add(new Goods("ipad",2,    60));
        goods.add(new Goods("充电宝",1,  20));
        goods.add(new Goods("iphone",2,   30));
    }

    static int N = 10;
    static void maxValueFun() {
        int len = goods.size();
        for (int i = 0; i < len; i++) {
            int sumWeight = 0;
            int sumValue = 0;
            for (int j = i; j < len; j++) {
                sumValue += goods.get(j).value;
                sumWeight+= goods.get(j).weight;

                if (sumWeight == N) {

                    for (int k = i; k <= j; k++) {
                        System.out.print(goods.get(k).toString());
                    }
                    System.out.println(" sumValue="+sumValue+"  sumWeight="+sumWeight);
                    break;
                }
            }
        }
    }




    public static void main(String[] args) {
        maxValueFun();
    }

}
