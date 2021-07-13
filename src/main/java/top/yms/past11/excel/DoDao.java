package top.yms.past11.excel;

import top.yms.past11.excel.write.DoWrite;

import java.util.ArrayList;
import java.util.List;

public class DoDao {
    public void save(List<Goods> list) {

        List<Goods> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Goods g1 = list.get(i);

            Goods goods = new Goods();
            goods.setCode(g1.getCode());
            goods.setGoodsId(g1.getGoodsId());
            goods.setName(g1.getName());
            goods.setNum(g1.getNum());

            list1.add(g1);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        List list2 = list;

        DoWrite.wirte(list1);
    }
}
