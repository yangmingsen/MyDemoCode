package top.yms.custom;

import jalikejdbc.DB;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import top.yms.past11.app.DateHelper;
import top.yms.utils.POIExcelUtil;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class ExcelImport1 {

    private static Connection conn;

    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest2?characterEncoding=utf8&useSSL=false",
                    "root", "ymsyms");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    /**
     * @param list  expend
     * @param list2 income
     */
    public static void doWrite(final List<MyIncome> list, final List<MyIncome> list2) {

        System.out.println("保存数据到db....");

//        String sql = "INSERT INTO `yms`.`my_expend_copy2`(`name`, `money`, `pay_type`, `remarks`, `pay_time`) " +
//                "VALUES (?, ?, ?, ?, ?)";
//
//        String sql2 = "INSERT INTO `yms`.`my_income_copy2`(`name`, `money`, `pay_type`, `remarks`, `pay_time`) " +
//                "VALUES (?, ?, ?, ?, ?)";


        String sql = "INSERT INTO `yms`.`my_expend`(`name`, `money`, `pay_type`, `remarks`, `pay_time`) " +
                "VALUES (?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO `yms`.`my_income`(`name`, `money`, `pay_type`, `remarks`, `pay_time`) " +
                "VALUES (?, ?, ?, ?, ?)";


        try {
            for (MyIncome expend : list) {
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, expend.getName());
                pst.setBigDecimal(2, expend.getMoney());
                pst.setString(3, expend.getPayType());
                pst.setString(4, expend.getRemarks());
                pst.setTimestamp(5, new Timestamp(expend.getPayTime().getTime()));

                pst.executeUpdate();
            }

            for (MyIncome income : list2) {
                PreparedStatement pst = conn.prepareStatement(sql2);
                pst.setString(1, income.getName());
                pst.setBigDecimal(2, income.getMoney());
                pst.setString(3, income.getPayType());
                pst.setString(4, income.getRemarks());
                pst.setTimestamp(5, new Timestamp(income.getPayTime().getTime()));

                pst.executeUpdate();
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("保存db完成....");


    }


    public static Date getDateTime(int year, int curMonth, int timeNum) {
        int[] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (year % 4 == 0 || year % 400 == 0) {
            month[2] = 29;
        }
        int base = 42886;

        int day = 0;
        timeNum = timeNum - base;

        for (int i = 6; i < curMonth; i++) {
            timeNum = timeNum - month[i];
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, curMonth - 1, timeNum, 00, 00, 00);
        return calendar.getTime();
    }


    public static Date getDateTime(String dateStr) {

        return DateHelper.strToDateTime(dateStr);
    }


    public static void doImport1() throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\2017年账务.xls";

        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        List<MyIncome> list = new ArrayList<>();
        List<MyIncome> list2 = new ArrayList<>();


        for (int ii = 6; ii <= 12; ii++) {
            String sName = ii + "月";
            Sheet sheet = workbook.getSheet(sName);

            if (sheet == null) continue;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();


            for (int i = 3; i < physicalNumberOfRows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) break;
                if (StringUtils.isBlank(row.getCell(0).toString())) break;

                MyIncome myIncome = new MyIncome();
                myIncome.setName(row.getCell(0).toString());

                //System.out.println(row.getCell(1).toString());
                Cell payMoney = row.getCell(1);
                Cell recvMoney = row.getCell(2);
                boolean payOk = true; //默认支出
                if (payMoney == null) {
                    if (recvMoney != null && StringUtils.isNotBlank(recvMoney.toString())) {
                        payOk = false; //支付内容
                        myIncome.setMoney(new BigDecimal(recvMoney.toString()));
                    } else {
                        System.out.println("出错....at "
                                + getDateTime(2017, 7, new Integer(POIExcelUtil.getCellValueByCell(row.getCell(3)))));
                        myIncome.setMoney(new BigDecimal("0.0"));
                    }
                } else {
                    myIncome.setMoney(new BigDecimal(payMoney.toString()));
                }
                ;

                myIncome.setPayTime(getDateTime(2017, 7, new Integer(POIExcelUtil.getCellValueByCell(row.getCell(3)))));
                //System.out.println(row.getCell(4));
                myIncome.setRemarks(row.getCell(4) == null ? " " : row.getCell(4).toString());

                myIncome.setPayType("现金或支付宝,微信");

                if (payOk) {
                    list.add(myIncome);
                } else {
                    list2.add(myIncome);
                }

            }

        }

        //list.forEach(x -> System.out.println(DateHelper.getDateStr(x.getPayTime())));
        //  list2.forEach( x -> System.out.println(x.toString()));

        doWrite(list, list2);


        workbook.close();


    }

    public static void doImport2018() throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\2018年账务.xlsx";

        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        List<MyIncome> list = new ArrayList<>();
        List<MyIncome> list2 = new ArrayList<>();


        for (int ii = 1; ii <= 9; ii++) {
            String sName = ii + "月";
            Sheet sheet = workbook.getSheet(sName);

            if (sheet == null) continue;

            int rows = sheet.getPhysicalNumberOfRows();


            for (int i = 2; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) break;
                if (StringUtils.isBlank(row.getCell(0).toString())) break;

                MyIncome myIncome = new MyIncome();
                myIncome.setName(row.getCell(0).toString());

                //System.out.println(row.getCell(1).toString());
                Cell payMoney = row.getCell(1);
                Cell recvMoney = row.getCell(2);
                boolean payOk = true; //默认支出
                if (payMoney == null) {
                    if (recvMoney != null && StringUtils.isNotBlank(recvMoney.toString())) {
                        payOk = false; //支付内容
                        myIncome.setMoney(new BigDecimal(recvMoney.toString()));
                    } else {
                        System.out.println("出错....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(3))));
                        myIncome.setMoney(new BigDecimal("0.0"));
                    }
                } else {
                    //System.out.println(payMoney.getNumericCellValue());
                    myIncome.setMoney(new BigDecimal(payMoney.getNumericCellValue()));
                }


                //System.out.println( POIExcelUtil.getCellValueByCell(row.getCell(3)) );
                myIncome.setPayTime(getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(3))));

                //System.out.println(row.getCell(4));
                myIncome.setRemarks(row.getCell(4) == null ? " " : row.getCell(4).toString());

                myIncome.setPayType("2018-现金或支付宝,微信");

                if (payOk) {
                    list.add(myIncome);
                } else {
                    list2.add(myIncome);
                }

            }
        }

        // list.forEach(x -> System.out.println(x));
        //   list2.forEach( x -> System.out.println(x.toString()));

        doWrite(list, list2);


        workbook.close();
    }


    public static void doImport201810later() throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\2018年账务.xlsx";

        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        List<MyIncome> list = new ArrayList<>();
        List<MyIncome> list2 = new ArrayList<>();


        for (int ii = 10; ii <= 12; ii++) {
            String sName = ii + "月";
            Sheet sheet = workbook.getSheet(sName);

            if (sheet == null) continue;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();


            for (int i = 8; i < physicalNumberOfRows; i++) {

                Row row = sheet.getRow(i);
                if (row == null) break;
                if (StringUtils.isBlank(row.getCell(2).toString())) break; //名称帕努单

                MyIncome myIncome = new MyIncome();

                myIncome.setName(row.getCell(2).getStringCellValue()); //名称
                //System.out.println(row.getCell(1).toString());
                Cell payMoney = row.getCell(3); //支出金额

                if (payMoney != null) {
                    // System.out.println(payMoney.toString());
                    myIncome.setMoney(new BigDecimal(payMoney.getNumericCellValue()));
                } else {
                    System.out.println("出错....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                    myIncome.setMoney(new BigDecimal("0.0"));
                }

                myIncome.setPayTime(getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));

                myIncome.setPayType("2018-现金或支付宝,微信");


                myIncome.setRemarks(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : " ");

                list.add(myIncome);


                //收入处理
                Cell recvTime = row.getCell(6);//recv time
                Cell recvName = row.getCell(7);//recv time
                if (recvTime != null && recvName != null && StringUtils.isNotBlank(recvName.getStringCellValue())) {
                    MyIncome myIncome2 = new MyIncome();

                    Cell recvMoney = row.getCell(8);

                    if (payMoney != null) {
                        // System.out.println(payMoney.toString());
                        myIncome2.setMoney(new BigDecimal(recvMoney.getNumericCellValue()));
                    } else {
                        System.out.println("出错 收入....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                        myIncome2.setMoney(new BigDecimal("0.0"));
                    }

                    myIncome2.setName(recvName.getStringCellValue());
                    myIncome2.setPayTime(getDateTime(POIExcelUtil.getCellValueByCell(recvTime)));
                    myIncome2.setPayType("收入-现金或支付宝,微信");
                    myIncome2.setRemarks(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : " ");

                    list2.add(myIncome2);
                }


            }

        }

        //list.forEach(x -> System.out.println(x));
        System.out.println("-----------------------");
        // list2.forEach( x -> System.out.println(x.toString()));

        doWrite(list, list2);


        workbook.close();


    }


    public static void doImport2019() throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\2019年账务.xlsx";

        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        List<MyIncome> list = new ArrayList<>();
        List<MyIncome> list2 = new ArrayList<>();


        for (int ii = 1; ii <= 12; ii++) {
            String sName = ii + "月";
            Sheet sheet = workbook.getSheet(sName);

            if (sheet == null) continue;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();


            for (int i = 7; i < physicalNumberOfRows; i++) {

                Row row = sheet.getRow(i);
                if (row == null) break;
                if (StringUtils.isBlank(row.getCell(2).toString())) break; //名称判断

                MyIncome myIncome = new MyIncome();

                myIncome.setName(row.getCell(2).getStringCellValue()); //名称
                //System.out.println(row.getCell(1).toString());
                Cell payMoney = row.getCell(3); //支出金额

                if (payMoney != null) {
                    // System.out.println(payMoney.toString());
                    myIncome.setMoney(new BigDecimal(payMoney.getNumericCellValue()));
                } else {
                    System.out.println("出错....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                    myIncome.setMoney(new BigDecimal("0.0"));
                }

                myIncome.setPayTime(getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));

                myIncome.setPayType("2019-现金或支付宝,微信");


                myIncome.setRemarks(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : " ");

                list.add(myIncome);


                //收入处理
                Cell recvTime = row.getCell(6);//recv time
                Cell recvName = row.getCell(7);//recv time
                if (recvTime != null && recvName != null && StringUtils.isNotBlank(recvName.getStringCellValue())) {
                    MyIncome myIncome2 = new MyIncome();

                    Cell recvMoney = row.getCell(8);

                    if (payMoney != null) {
                        // System.out.println(payMoney.toString());
                        myIncome2.setMoney(new BigDecimal(recvMoney.getNumericCellValue()));
                    } else {
                        System.out.println("出错 收入....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                        myIncome2.setMoney(new BigDecimal("0.0"));
                    }

                    myIncome2.setName(recvName.getStringCellValue());
                    myIncome2.setPayTime(getDateTime(POIExcelUtil.getCellValueByCell(recvTime)));
                    myIncome2.setPayType("收入-现金或支付宝,微信");
                    myIncome2.setRemarks(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : " ");

                    list2.add(myIncome2);
                }


            }

        }

        //list.forEach(x -> System.out.println(x));
        System.out.println("-----------------------");
        //list2.forEach( x -> System.out.println(x.toString()));

        doWrite(list, list2);


        workbook.close();


    }


    public static void doImport2020() throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\2020年账务.xlsx";

        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        List<MyIncome> list = new ArrayList<>();
        List<MyIncome> list2 = new ArrayList<>();


        for (int ii = 1; ii <= 12; ii++) {
            String sName = ii + "月";
            Sheet sheet = workbook.getSheet(sName);

            if (sheet == null) continue;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();


            for (int i = 8; i < physicalNumberOfRows; i++) {

                Row row = sheet.getRow(i);
                if (row == null) break;
                if (StringUtils.isBlank(row.getCell(2).toString())) break; //名称判断

                MyIncome myIncome = new MyIncome();

                myIncome.setName(row.getCell(2).getStringCellValue()); //名称
                //System.out.println(row.getCell(1).toString());
                Cell payMoney = row.getCell(3); //支出金额

                if (payMoney != null) {
                    // System.out.println(payMoney.toString());
                    myIncome.setMoney(new BigDecimal(payMoney.getNumericCellValue()));
                } else {
                    System.out.println("出错....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                    myIncome.setMoney(new BigDecimal("0.0"));
                }

                Date expendTime = getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1)));
                myIncome.setPayTime(expendTime == null ? list.get(list.size() - 1).getPayTime() : expendTime);

                myIncome.setPayType("2020-现金或支付宝,微信");

                try {

                    myIncome.setRemarks(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : " ");
                } catch (Exception e) {
                    Cell cell = row.getCell(4);
                    // System.out.println(myIncome);
                    //  e.printStackTrace();

                    myIncome.setRemarks(POIExcelUtil.getCellValueByCell(cell));
                }

                list.add(myIncome);


                //收入处理
                Cell recvTime = row.getCell(6);//recv time
                Cell recvName = row.getCell(7);//recv time
                if (recvTime != null && recvName != null && StringUtils.isNotBlank(recvName.getStringCellValue())) {
                    MyIncome myIncome2 = new MyIncome();

                    Cell recvMoney = row.getCell(8);

                    if (payMoney != null) {
                        // System.out.println(payMoney.toString());
                        myIncome2.setMoney(new BigDecimal(recvMoney.getNumericCellValue()));
                    } else {
                        System.out.println("出错 收入....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                        myIncome2.setMoney(new BigDecimal("0.0"));
                    }

                    myIncome2.setName(recvName.getStringCellValue());

                    Date expendTime2 = getDateTime(POIExcelUtil.getCellValueByCell(recvTime));
                    //如果不存在时间，用最近的时间
                    myIncome2.setPayTime(expendTime2 == null ? list.get(list.size() - 1).getPayTime() : expendTime2);

                    myIncome2.setPayType("收入-现金或支付宝,微信");
                    myIncome2.setRemarks(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : " ");

                    list2.add(myIncome2);
                }


            }

        }

        // list.forEach(x -> {System.out.println(x);});
        System.out.println("-----------------------");
        //list2.forEach( x -> System.out.println(x.toString()));

        doWrite(list, list2);


        workbook.close();


    }


    public static void doImport2021l1() throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\2021年账务.xlsx";

        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        List<MyIncome> list = new ArrayList<>();
        List<MyIncome> list2 = new ArrayList<>();


        for (int ii = 1; ii <= 3; ii++) {
            String sName = ii + "月";
            Sheet sheet = workbook.getSheet(sName);

            if (sheet == null) continue;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();


            for (int i = 8; i < physicalNumberOfRows; i++) {

                Row row = sheet.getRow(i);
                if (row == null) break;
                if (StringUtils.isBlank(row.getCell(2).toString())) break; //名称判断

                MyIncome myIncome = new MyIncome();

                myIncome.setName(row.getCell(2).getStringCellValue()); //名称
                //System.out.println(row.getCell(1).toString());
                Cell payMoney = row.getCell(3); //支出金额

                if (payMoney != null) {
                    // System.out.println(payMoney.toString());
                    myIncome.setMoney(new BigDecimal(payMoney.getNumericCellValue()));
                } else {
                    System.out.println("出错....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                    myIncome.setMoney(new BigDecimal("0.0"));
                }

                Date expendTime = getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1)));
                myIncome.setPayTime(expendTime == null ? list.get(list.size() - 1).getPayTime() : expendTime);

                myIncome.setPayType("2021-现金或支付宝,微信");

                try {

                    myIncome.setRemarks(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : " ");
                } catch (Exception e) {
                    Cell cell = row.getCell(4);
                    // System.out.println(myIncome);
                    //  e.printStackTrace();

                    myIncome.setRemarks(POIExcelUtil.getCellValueByCell(cell));
                }

                list.add(myIncome);


                //收入处理
                Cell recvTime = row.getCell(6);//recv time
                Cell recvName = row.getCell(7);//recv time
                if (recvTime != null && recvName != null && StringUtils.isNotBlank(recvName.getStringCellValue())) {
                    MyIncome myIncome2 = new MyIncome();

                    Cell recvMoney = row.getCell(8);

                    if (payMoney != null) {
                        // System.out.println(payMoney.toString());
                        myIncome2.setMoney(new BigDecimal(recvMoney.getNumericCellValue()));
                    } else {
                        System.out.println("出错 收入....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                        myIncome2.setMoney(new BigDecimal("0.0"));
                    }

                    myIncome2.setName(recvName.getStringCellValue());

                    Date expendTime2 = getDateTime(POIExcelUtil.getCellValueByCell(recvTime));
                    //如果不存在时间，用最近的时间
                    myIncome2.setPayTime(expendTime2 == null ? list.get(list.size() - 1).getPayTime() : expendTime2);

                    myIncome2.setPayType("2021收入-现金或支付宝,微信");
                    myIncome2.setRemarks(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : " ");

                    list2.add(myIncome2);
                }


            }

        }

        // list.forEach(x -> {System.out.println(x);});
        System.out.println("-----------------------");
        //  list2.forEach( x -> System.out.println(x));

        doWrite(list, list2);


        workbook.close();


    }


    public static void doImport2021l2() throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\2021年账务.xlsx";

        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        List<MyIncome> list = new ArrayList<>();
        List<MyIncome> list2 = new ArrayList<>();


        for (int ii = 4; ii <= 5; ii++) {
            String sName = ii + "月";
            Sheet sheet = workbook.getSheet(sName);

            if (sheet == null) continue;

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();


            for (int i = 8; i < physicalNumberOfRows; i++) {

                Row row = sheet.getRow(i);
                if (row == null) break;
                if (StringUtils.isBlank(row.getCell(2).toString())) break; //名称判断

                MyIncome myIncome = new MyIncome();

                Cell ceName = row.getCell(2);
                try {
                    myIncome.setName(ceName.getStringCellValue()); //名称

                } catch (Exception e) {
                    //e.printStackTrace();
                    String tmpName = POIExcelUtil.getCellValueByCell(ceName);
                    System.out.println(tmpName);
                    myIncome.setName(tmpName);
                }

                //System.out.println(row.getCell(1).toString());
                Cell payMoney = row.getCell(3); //支出金额

                if (payMoney != null) {
                    // System.out.println(payMoney.toString());
                    myIncome.setMoney(new BigDecimal(payMoney.getNumericCellValue()));
                } else {
                    System.out.println("出错....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                    myIncome.setMoney(new BigDecimal("0.0"));
                }

                Date expendTime = getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1)));
                myIncome.setPayTime(expendTime == null ? list.get(list.size() - 1).getPayTime() : expendTime);

                myIncome.setPayType("2021-现金或支付宝,微信");

                try {

                    myIncome.setRemarks(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : " ");
                } catch (Exception e) {
                    Cell cell = row.getCell(4);
                    // System.out.println(myIncome);
                    //  e.printStackTrace();

                    myIncome.setRemarks(POIExcelUtil.getCellValueByCell(cell));
                }

                list.add(myIncome);


                //收入处理
                Cell recvTime = row.getCell(6);//recv time
                Cell recvName = row.getCell(7);//recv time
                if (recvTime != null && recvName != null && StringUtils.isNotBlank(recvName.getStringCellValue())) {
                    MyIncome myIncome2 = new MyIncome();

                    Cell recvMoney = row.getCell(8);

                    if (payMoney != null) {
                        // System.out.println(payMoney.toString());
                        myIncome2.setMoney(new BigDecimal(recvMoney.getNumericCellValue()));
                    } else {
                        System.out.println("出错 收入....at " + getDateTime(POIExcelUtil.getCellValueByCell(row.getCell(1))));
                        myIncome2.setMoney(new BigDecimal("0.0"));
                    }

                    myIncome2.setName(recvName.getStringCellValue());

                    Date expendTime2 = getDateTime(POIExcelUtil.getCellValueByCell(recvTime));
                    //如果不存在时间，用最近的时间
                    myIncome2.setPayTime(expendTime2 == null ? list.get(list.size() - 1).getPayTime() : expendTime2);

                    myIncome2.setPayType("2021收入-现金或支付宝,微信");
                    myIncome2.setRemarks(row.getCell(9) != null ? row.getCell(9).getStringCellValue() : " ");

                    list2.add(myIncome2);
                }


            }

        }

        // list.forEach(x -> {System.out.println(x.getPayTime());});
        System.out.println("-----------------------");
        //  list2.forEach( x -> System.out.println(x));

        doWrite(list, list2);


        workbook.close();


    }

    public static void mapPrint(Map<String, AliExpend> map) {
        for (Map.Entry<String, AliExpend> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
    public static void mapPrint2(Map<String, AliExpendOther> map) {
        for (Map.Entry<String, AliExpendOther> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }


    public static PreparedStatement getPst(AliExpend aliExpend, String sql) throws Exception{
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, aliExpend.getOrderId());
        pst.setString(2, aliExpend.getCounterparty());
        pst.setString(3, aliExpend.getTitle());
        pst.setString(4, aliExpend.getPaymentMethod());
        pst.setBigDecimal(5, aliExpend.getAmount());
        pst.setString(6, aliExpend.getCategory());
        pst.setTimestamp(7, new Timestamp(aliExpend.getTxTime().getTime()));

        return pst;
    }

    public static void doAliRecordToDb(Map<String, AliExpend> expendMap, Map<String, AliExpend> incomeMap,
                                       Map<String, AliExpendOther> tmpMap, Map<String, AliExpendOther> otherMap ) {
        System.out.println("开始输出到Db");
        String expendSql = "INSERT INTO `dbtest2`.`expend`(`id`, `counterparty`, `title`, `payment_method`, `amount`, `category`, `tx_time`) VALUES (?,?,?,?,?,?,?)";
        String incomeSql = "INSERT INTO `dbtest2`.`income`(`id`, `counterparty`, `title`, `payment_method`, `amount`, `category`, `tx_time`) VALUES (?,?,?,?,?,?,?)";
        String tmpSql = "INSERT INTO `dbtest2`.`expend_tmp`(`id`, `counterparty`, `title`, `payment_method`, `amount`, `category`, `tx_time`,`tx_status`) VALUES (?,?,?,?,?,?,?,?)";
        String otherSql = "INSERT INTO `dbtest2`.`expend_other`(`id`, `counterparty`, `title`, `payment_method`, `amount`, `category`, `tx_time`,`tx_status`) VALUES (?,?,?,?,?,?,?,?)";

        try {

            expendMap.forEach( (x, y) -> {
                try {
                    PreparedStatement pst = getPst(y, expendSql);
                    pst.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            });

            incomeMap.forEach( (x, y) -> {
                try {
                    PreparedStatement pst = getPst(y, incomeSql);
                    pst.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            });

            tmpMap.forEach((x,y) -> {
                try {
                    PreparedStatement pst = getPst(y, tmpSql);
                    pst.setString(8, y.getTxStatus());
                    pst.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            });

            otherMap.forEach((x,y) -> {
                try {
                    PreparedStatement pst = getPst(y, otherSql);
                    pst.setString(8, y.getTxStatus());
                    pst.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            });


        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("输出到Db完毕...");

    }


    public static String getCellStr(Cell cell) {
        return POIExcelUtil.getCellValueByCell(cell).trim();
    }


    private static AliExpend parseAliCells(Cell[] cells, AliExpend aliExpend) throws Exception {
        String cell0Str = cells[0].getStringCellValue();

        Cell cell1 = cells[1];
        //Cell cell2 = row.getCell(2);
        Cell cell3 = cells[3];
        Cell cell4 = cells[4];
        Cell cell5 = cells[5];
        Cell cell6 = cells[6];
        Cell cell7 = cells[7];
        Cell cell8 = cells[8];
        //Cell cell9 = row.getCell(9);
        Cell cell10 = cells[10];


        //AliExpend aliExpend = new AliExpend();
        aliExpend.setOrderId(getCellStr(cell8)); //订单id
        aliExpend.setCounterparty(getCellStr(cell1)); //交易对方
        aliExpend.setTitle(getCellStr(cell3)); //交易名称
        aliExpend.setPaymentMethod(getCellStr(cell4)); //交易方式

        //交易金额判断
        String amountStr = getCellStr(cell5);
        if (StringUtils.isBlank(amountStr)) {
            throw new Exception("订单号[" + aliExpend.getOrderId() + "] 金额为空");
        }
        try {
            new Double(amountStr);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        aliExpend.setAmount(new BigDecimal(amountStr)); //交易金额

        aliExpend.setCategory(getCellStr(cell7)); //交易分类

        Date expendTime = getDateTime(POIExcelUtil.getCellValueByCell(cell10));
        aliExpend.setTxTime(expendTime); // 交易时间

        return aliExpend;
    }

    public static void toIncomeSuccess(Cell [] cells, Map<String, AliExpend> map) throws Exception {
        toExpendSuccess(cells, map);
    }

    public static void toExpendSuccess(Cell[] cells, Map<String, AliExpend> map) throws Exception {
        AliExpend aliExpend = new AliExpend();
        parseAliCells(cells, aliExpend);
        //add
        map.put(aliExpend.getOrderId(), aliExpend);

    }

    public static void toTmpExpend(Cell[] cells, Map<String, AliExpendOther> map) throws Exception {
        //0 => 收/支(支出,其他) , 1=> 交易对方, 2 => 对方账号, 3 => 商品说明, 4=> 收/付款方式,
        //5=> 金额, 6=> 交易状态, 7=> 交易分类, 8=> 订单号, 9=>商家订单号, 10=> 交易时间
        AliExpendOther otherAliExpend = new AliExpendOther();
        //解析
        parseAliCells(cells, otherAliExpend);

        //交易状态
        otherAliExpend.setTxStatus(getCellStr(cells[6]));

        map.put(otherAliExpend.getOrderId(), otherAliExpend);

    }

    public static void toOtherExpend(Cell[] cells, Map<String, AliExpendOther> map) throws Exception {
        toTmpExpend(cells, map);

    }

    private static final String PAY_OUT = "支出";
    private static final String PAY_OHER = "其他";


    private static final String WAIT_FOR_RECV = "等待确认收货";
    private static final String TX_CLOSE = "交易关闭";
    private static final String TX_SUCCESS = "交易成功";
    private static final String PAYMENT_SUCCESS = "支付成功";
    private static final String RETURN_SUCCESS = "退款成功";
    private static final String AUTO_RETURN = "自动还款";
    private static final String UNFREEZE_SUCCESS = "解冻成功";
    private static final String CREDIT_SERVICE_USE_SUCCESS = "信用服务使用成功";


    public static void doParseAliRecord(Workbook workbook) throws Exception {

        Map<String, AliExpend> incomeMap = new HashMap<>(); //收入表

        Map<String, AliExpend> expendMap = new HashMap<>(); //支出表

        Map<String, AliExpendOther> otherExpendMap = new HashMap<>(); //其他交易数据

        Map<String, AliExpendOther> tmpExpendMap = new HashMap<>(); //零时不重要的数据

        String sheetName = "alipay_record_20220607_191433";
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) throw new Exception(sheetName + " 未找到");

        int totalRows = sheet.getPhysicalNumberOfRows();
        // ali的从第3行开始
        for (int i = 2; i < totalRows; i++) {
            Row row = sheet.getRow(i);//拿到某一行

            //0 => 收/支(支出,其他) , 1=> 交易对方, 2 => 对方账号, 3 => 商品说明, 4=> 收/付款方式,
            //5=> 金额, 6=> 交易状态, 7=> 交易分类, 8=> 订单号, 9=>商家订单号, 10=> 交易时间

            Cell cell0 = row.getCell(0);
            if (cell0 == null || StringUtils.isBlank(cell0.getStringCellValue()) || cell0.getStringCellValue().startsWith("---------"))
                break;
           //收入/支出
            String cell0Str = cell0.getStringCellValue();

            Cell[] cells = new Cell[11];
            for (int j = 0; j < 11; j++) {
                cells[j] = row.getCell(j);
            }

            //交易状态
            String cell6Str = getCellStr(cells[6]);
            if (cell0Str.contains(PAY_OUT)) {
                if (cell6Str.contains(TX_SUCCESS) || cell6Str.contains(PAYMENT_SUCCESS)) {
                    toExpendSuccess(cells, expendMap);
                } else if (cell6Str.contains(WAIT_FOR_RECV) || cell6Str.contains(TX_CLOSE)) {
                    toOtherExpend(cells, otherExpendMap);
                } else {
                    throw new Exception("支出异常交易状态[" + cell6Str + "] 订单号[" + getCellStr(cells[8]) + "]");
                }

            } else if (cell0Str.contains(PAY_OHER)) {
                if (cell6Str.contains(TX_SUCCESS)) {
                    //只有是投资支出计入支出，其他出了收益都入零时表
                    String txTarget = getCellStr(cells[1]); //交易对象
                    String txTitle = getCellStr(cells[3]); //交易名称
                    if(txTarget.contains("蚂蚁财富") && txTitle.contains("买入")) {
                        toExpendSuccess(cells, expendMap);
                    } else if ((txTarget.contains("天弘基金管理有限公司") && txTitle.contains("收益发放")) ||
                            (txTarget.contains("蚂蚁财富") && txTitle.contains("卖出至余额宝"))
                    ) {
                        //入收益表
                        toIncomeSuccess(cells, incomeMap);
                    } else {
                        toTmpExpend(cells, tmpExpendMap);
                    }
                } else if (cell6Str.contains(UNFREEZE_SUCCESS) || cell6Str.contains(TX_CLOSE)) {
                    toOtherExpend(cells, otherExpendMap);
                } else if (cell6Str.contains(CREDIT_SERVICE_USE_SUCCESS)){
                    toOtherExpend(cells, otherExpendMap);
                } else {
                    toTmpExpend(cells, tmpExpendMap);
                }

            } else {
                throw new Exception(row.toString());
            }

        }

        //mapPrint(expendMap);
        //mapPrint(incomeMap);
        //mapPrint2(otherExpendMap);
        //mapPrint2(tmpExpendMap);

        doAliRecordToDb(expendMap, incomeMap, tmpExpendMap, otherExpendMap);

    }


    public static void main(String[] args) throws Exception {
        String fileName = "C:\\Users\\yangmingsen\\Desktop\\test_record\\alipay_record_20220607_191433.xlsx";
        Workbook workbook = POIExcelUtil.getWorkbook(new FileInputStream(fileName), fileName);
        try {
            doParseAliRecord(workbook);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }
    }


}
