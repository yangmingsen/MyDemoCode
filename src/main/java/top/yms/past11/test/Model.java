package top.yms.past11.test;

import java.util.Random;

class  A{
    private String code; //座位唯一编码
    private String no; //座位编号(用于显示,可被改变)
    private int typeCode; //座位类别：0-灵活；1-固定；2-公共
    private String departmentCode; //部门唯一编码

    public A(String code, String no, int typeCode, String departmentCode) {
        this.code = code;
        this.no = no;
        this.typeCode = typeCode;
        this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"no\":\"" + no + '\"' +
                ", \"typeCode\":" + typeCode +
                ",\"departmentCode\":\"" +departmentCode + "\""+
                '}'+",";
    }
}
public class Model {

    public static int randomTypeCode() {
        int min = 0;
        int max = 3;

        return new Random().nextInt(max-min)+min;
    }


    public static String getCode(int i) {
        if (i<10) {
            return "00"+i;
        }
        if (i < 100) {
            return "0"+i;
        }

        return ""+i;
    }

    public static String getNo(int i) {
        if (i<10) {
            return "A00"+i;
        }
        if (i < 100) {
            return "A0"+i;
        }

        return "A"+i;
    }

    public static String getDepartmentCode(int n) {
        int [] arr = {312586851,
                212788513,
                58476173,
                58476171,
                58476167,
                355909505,
                139691268,
                58476172,
                58476174,
                58476170,
                69891083
        };

        int rsn = n/10;
        if (rsn > 10) {
            rsn = 10;
        }

        return Integer.toString(arr[rsn]);


    }

    public static void main(String[] args) {
        for (int i = 1; i < 136; i++) {
            System.out.println(new A(getCode(i),getNo(i),randomTypeCode(), getDepartmentCode(i)).toString());
        }




    }

}
