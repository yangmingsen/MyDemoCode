package top.yms.past11.excel.write;

import com.alibaba.excel.EasyExcel;
import top.yms.past11.excel.DemoData;
import top.yms.past11.excel.Goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoWrite {



    public static void wirte(List<Goods> list) {
        // 写法1
        String fileName = "E:\\勿删桌面\\simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Goods.class).sheet("模板").doWrite(list);
        System.out.println("hell world");
        System.out.println("whatsdf  ");
        System.out.println("wo what this sdlkf ldsjfljsdlfj;alsdfl a;lsdfj;lasjd;l fhai");

    }

    private static List<Goods> data2() {
        List<Goods> list = new ArrayList<Goods>();
        for (int i = 0; i < 10; i++) {
            Goods data = new Goods();

            data.setCode("code"+i);
            data.setGoodsId("usyc_qhkm_xbb67_20_cl");
            data.setName("name"+i);
            data.setNum(i);

            list.add(data);
        }
        return list;
    }



    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    public static void main(String[] args) {
        // 写法1
        String fileName = "E:\\勿删桌面\\simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Goods.class).sheet("模板").doWrite(data2());
    }

    public static void main1(String[] args) {
        // 写法1
        String fileName = "E:\\勿删桌面\\simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

    }

}
