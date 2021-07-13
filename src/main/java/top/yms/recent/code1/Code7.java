package top.yms.recent.code1;

import java.util.*;

public class Code7 {
    public static void main(String[] args) {

        LinkedHashSet<String> lhset = new LinkedHashSet<>();
        String str = "东方丝路|电子丝路|电子丝路|香港维屿|香港维屿|香港维屿|东方丝路|东方丝路|电子丝路|电子丝路|电子丝路|东方丝路|电子丝路|电子丝路|电子丝路|香港维屿";
        String str2 = "人民币|美元|人民币|美元|美元|日本日圆|人民币|美元|日本日圆|香港元|人民币|香港元|台币|香港元|美元|香港元";
        String str3 = "1002.11.01 银行存款 : 银行存款-公司账户-DFSL : 银行存款-公司账户-DFSL-工行-9030 RMB|1002.41.11 银行存款 : 银行存款-公司账户-HK : 银行存款-公司账户-HK-ESR Limited-富邦-1825 USD|1002.42.16 银行存款 : 银行存款-个人账户-HK : 银行存款-个人账户-HK-FWL-中行-2683 RMB|1002.44.35 银行存款 : 银行存款-第三方账户-HKWY : 银行存款-第三方账户-HKWY-PAPAL-USD|1002.71.03 银行存款 : 银行存款-公司账户-HKWY : 银行存款-公司账户-HKWY-渣打0197-USD|1002.71.04 银行存款 : 银行存款-公司账户-HKWY : 银行存款-公司账户-HKWY-渣打2378-JPY|1003.11.03 平台账户 : 平台账户-收款账户-DFSL : 平台账户-收款账户-DFSL-【淘宝店】esr亿色-支付宝 RMB|1003.11.08 平台账户 : 平台账户-收款账户-DFSL : 平台账户-收款账户-DFSL-阿里巴巴-通用账户 USD|1002.41.13 银行存款 : 银行存款-公司账户-HK : 银行存款-公司账户-HK-ESR株式会社-乐天银行-9803 JPY|1002.41.14 银行存款 : 银行存款-公司账户-HK : 银行存款-公司账户-HK-ESR Limited-富邦-1825 HKD|1002.43.32 银行存款 : 银行存款-第三方账户-HK : 银行存款-第三方账户-HK-DFSL-PAPAL USD（ESR-EBAY)|1002.11.10 银行存款 : 银行存款-公司账户-DFSL : 银行存款-公司账户-DFSL-富邦-6880 HKD|1002.41.02 银行存款 : 银行存款-公司账户-HK : 银行存款-公司账户-HK-TW DFSL-富邦银行-0978 NTD|1002.41.07 银行存款 : 银行存款-公司账户-HK : 银行存款-公司账户-HK-ESR Limited-汇丰-9838 HKD|1002.41.08 银行存款 : 银行存款-公司账户-HK : 银行存款-公司账户-HK-ESR Limited-汇丰-9838 USD|1002.71.05 银行存款 : 银行存款-公司账户-HKWY : 银行存款-公司账户-HKWY-渣打0189-HKD";

        String[] split = str.split("\\|");
        System.out.println("len="+split.length);
        for(String s : split) {
            lhset.add(s);
        }

        for(Object obj : lhset.toArray()) {
            String tS = obj.toString();
            System.out.println(tS+"|"+tS);
        }


//        StringBuilder res1 = new StringBuilder();
//        for(Object st : split) {
//            String tStr =  st.toString();
//            res1.append("\""+tStr+"\",");
//        }
//        System.out.println(res1.toString());





    }
}
