package top.yms.past11.test;

import java.util.ArrayList;
import java.util.List;

class Ds {
    private Integer num;
    private String name;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ds{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
public class Hi {

    public static void doOther(List<Ds> list) {
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Ds> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Ds ds = new Ds();
            ds.setName("haha+"+i);
            ds.setNum(i);

            list.add(ds);
        }

        List<Ds> l2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Ds ds = list.get(i);

            Ds dd = new Ds();
            dd.setNum(ds.getNum());
            dd.setName(ds.getName());

            l2.add(dd);
        }

        Ds ds = list.get(0);
        Ds ds1 = l2.get(0);
        System.out.println(ds.getNum() == ds1.getNum());
        System.out.println(ds.getName() == ds1.getName());

        list.get(0).setNum(234);
        list.get(0).setName("yangmingsen");
        System.gc();

        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doOther(l2);



    }
}
