package top.yms.recent;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AutoGen {

    static String tg1 = "\t";
    static String tg2 = "\t\t";
    static String tg3 = "\t\t\t";
    static String tg4 = "\t\t\t\t";
    static String tg5 = "\t\t\t\t\t";
    static String tg6 = "\t\t\t\t\t\t";

    Map<String, String> inputConditionMap = new HashMap<String, String>();

    static class GenTaget {

        public String dynamicSelectName;
        public String id;

        public String tableName;

        public String [] inputCondition;

        public String [] resultProperty;
        public String [] resultJavaType;
        public String [] resultColumn;
        public String [] resultLongName;

        public String [] searchCondition;

    }

    private GenTaget genTaget = new GenTaget();


    private void genSelectOne() throws Exception{

        String sql = "\t<sql>\n" +
                "\t\t<![CDATA[ \n" +
                "\t\t\tselect * from %s where fcbpdt = #frntdt# and fcbpsq = #frntsq# \n" +
                "         ]]>\n" +
                "     </sql>\n";

        //英文名
        String [] ek = genTaget.resultProperty;

        //zh name
        String [] zk = genTaget.resultLongName;

        //java类型
        String [] javaTypes = genTaget.resultJavaType;

        String [] searchConditions = genTaget.searchCondition;

        int len = ek.length;
        String p1 = "<select cache=\"none\" method=\"selectOne\" type=\"sql\" id=\"%s\" longname=\"%s\">\n";
        System.out.printf(p1,genTaget.id, genTaget.dynamicSelectName);

        System.out.printf(sql, genTaget.tableName);

        //输入参数
        System.out.println(tg1+"<parameterMap class=\"java.util.Map\">");
        for (String ipc : genTaget.inputCondition) {
            String res = inputConditionMap.get(ipc);
            if (res == null) {
                throw new Exception("Not found => "+ipc);
            }
            System.out.println(tg2+res);
        }
        System.out.println(tg1+"</parameterMap>");

        //返回信息
        System.out.println(tg1+"<resultMap class=\"java.util.Map\">");
        for (int i=0; i<len; i++) {
            String eN = ek[i].toLowerCase();
            String str = tg2+"<result property=\"%s\" javaType=\"%s\" jdbcType=\"VARCHAR\" column=\"%s\" longname=\"%s\"/>\n";
            System.out.printf(str,eN,javaTypes[i],ek[i],zk[i]);
        }
        System.out.println(tg1+"</resultMap>");




        System.out.println("</select>");
    }


    private void genDynamicSelect( ) throws Exception {
        //英文名
        String [] ek = genTaget.resultProperty;

        //zh name
        String [] zk = genTaget.resultLongName;

        String [] javaTypes = genTaget.resultJavaType;

        String [] searchConditions = genTaget.searchCondition;



        int len = ek.length;
        String p1 = "<dynamicSelect cache=\"none\" method=\"selectPageWithCount\" type=\"sql\" id=\"%s\" longname=\"%s\">\n";
        System.out.printf(p1,genTaget.id, genTaget.dynamicSelectName);


        //输入参数
        System.out.println(tg1+"<parameterMap class=\"java.util.Map\">");
        for (String ipc : genTaget.inputCondition) {
            String res = inputConditionMap.get(ipc);
            if (res == null) {
                throw new Exception("Not found => "+ipc);
            }
            System.out.println(tg2+res);
        }
        System.out.println(tg1+"</parameterMap>");


        //返回信息
        System.out.println(tg1+"<resultMap class=\"java.util.Map\">");
        for (int i=0; i<len; i++) {
            String eN = ek[i].toLowerCase();
            String str = tg2+"<result property=\"%s\" javaType=\"%s\" jdbcType=\"VARCHAR\" column=\"%s\" longname=\"%s\"/>\n";
            System.out.printf(str,eN,javaTypes[i],ek[i],zk[i]);
        }
        System.out.println(tg1+"</resultMap>");




        //动态查询条件
        System.out.println(tg1+"<dynamicSql>");

        //sql zone
        System.out.println(tg2+"<str type=\"Str\">");
        System.out.println(tg3+"<![CDATA[ ");
        String selSql = tg4+"select * from %s where ";
        //获取输入条件
        Set<String> inputSets = new HashSet<>();
        for(String str : genTaget.inputCondition) {
            inputSets.add(str);
        }
        //获取排除条件-除去排除条件
        for(String str : genTaget.searchCondition) {
            inputSets.remove(str);
        }
        //gen

        StringBuilder tmpSqlStr = new StringBuilder(selSql);
        for (String iStr : inputSets) {
            tmpSqlStr.append(" ").append(iStr).append("=#").append(iStr).append("# and ");
        }
        tmpSqlStr.append("\n");

        selSql = tmpSqlStr.toString();
        System.out.printf(selSql, genTaget.tableName);

        //sql zone end
        System.out.println(tg3+" ]]>");
        System.out.println(tg2+"</str>");


        // start dnamic gen
        for(String condition : searchConditions) {
            String s1 = tg2+"<if test=\"%s != &quot;&quot; &amp;&amp; %s != null\" type=\"If\">\n";
            System.out.printf(s1, condition, condition);

            String s2 = tg3+"<str type=\"Str\">";
            System.out.println(s2);

            String s3 = tg4+"<![CDATA[and %s=#%s#]]>\n";
            System.out.printf(s3, condition, condition);

            String s4 = tg3+"</str>";
            System.out.println(s4);

            System.out.println(tg2+"</if>");
        }
        System.out.println(tg1+"</dynamicSql>");


        System.out.println("</dynamicSelect>");


    }

    String spCode = ",";


    private Integer parseVarchar2(String str) {
        if ( !str.startsWith("VARCHAR2")) {
            return 30;
        }

        StringBuilder tmpStr = new StringBuilder();
        for( int i=8; i<str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                tmpStr.append(c);
            }
        }

        return Integer.parseInt(tmpStr.toString());


    }



    private void getInputCon() {

        inputConditionMap.put("brchno","<parameter property=\"brchno\" ref=\"CnapDict.Cnap.brchno\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_BR0012\" mode=\"in\" longname=\"机构号\" parameterType=\"condition\"/>");
        inputConditionMap.put("subsys","<parameter property=\"subsys\" ref=\"CnapDict.Ccms.subsys\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_CD0004\" mode=\"in\" longname=\"系统编号\" parameterType=\"condition\"/>");
        inputConditionMap.put("msetsq","<parameter property=\"msetsq\" ref=\"CnapDict.Ccms.msetsq\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_SQ0020\" mode=\"in\" longname=\"报文委托流水\" parameterType=\"condition\"/>");
        inputConditionMap.put("iotype","<parameter property=\"iotype\" ref=\"CnapDict.Ccms.iotype\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_TG0001\" mode=\"in\" longname=\"来往标志\" parameterType=\"condition\"/>");
        inputConditionMap.put("begndt","<parameter property=\"begndt\" ref=\"CnapDict.Ccms.begndt\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_DT0008\" mode=\"in\" longname=\"开始日期 \" parameterType=\"condition\"/>");
        inputConditionMap.put("termdt","<parameter property=\"termdt\" ref=\"CnapDict.Ccms.termdt\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_DT0008\" mode=\"in\" longname=\"结束日期 \" parameterType=\"condition\"/>");


        inputConditionMap.put("mesgtp","<parameter property=\"mesgtp\" ref=\"CnapDict.Ccms.mesgtp\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_TP0020\" mode=\"in\" longname=\"报文类型\" parameterType=\"condition\"/>");
        inputConditionMap.put("busitp","<parameter property=\"busitp\" ref=\"CnapDict.Ccms.busitp\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_TP0010\" mode=\"in\" longname=\"业务类型\" parameterType=\"condition\"/>");
        inputConditionMap.put("busils","<parameter property=\"busils\" ref=\"CnapDict.Ccms.busils\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_TP0010\" mode=\"in\" longname=\"业务种类\" parameterType=\"condition\"/>");


        inputConditionMap.put("sendid","<parameter property=\"sendid\" ref=\"CnapDict.Cnap.sendid\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_CD0004\" mode=\"in\" longname=\"发送系统号\" parameterType=\"condition\"/>");
        inputConditionMap.put("sendog","<parameter property=\"sendog\" ref=\"CnapDict.Cnap.sendog\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_NO0014\" mode=\"in\" longname=\"发起参与机构\" parameterType=\"condition\"/>");
        inputConditionMap.put("msetdt","<parameter property=\"msetdt\" ref=\"CnapDict.Cnap.msetdt\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_DT0008\" mode=\"in\" longname=\"报文委托日期\" parameterType=\"condition\"/>");


        inputConditionMap.put("tranam","<parameter property=\"tranam\" ref=\"CnapDict.Ccms.tranam\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_AM0019\" mode=\"in\" longname=\"待查询业务金额 \" parameterType=\"condition\"/>");
        inputConditionMap.put("procst","<parameter property=\"procst\" ref=\"CnapDict.Ccms.procst\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_ST0004\" mode=\"in\" longname=\"业务状态\" parameterType=\"condition\"/>");
        inputConditionMap.put("qurytp","<parameter property=\"qurytp\" ref=\"CnapDict.Ccms.qurytp\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_LS0004\" mode=\"in\" longname=\"查询类型QT00：整包查询|QT01：单笔查询\" parameterType=\"condition\"/>");

        inputConditionMap.put("repytg","<parameter property=\"repytg\" ref=\"CnapDict.Ccms.repytg\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_ID0002\" mode=\"in\" longname=\"是否已回复(Y:已回复|N:未回复) \" parameterType=\"condition\"/>");

        inputConditionMap.put("frntdt","<parameter property=\"frntdt\" ref=\"CnapDict.Ccms.frntdt\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_DT0008\" mode=\"in\" longname=\"原前置日期\" parameterType=\"condition\"/>");
        inputConditionMap.put("frntsq","<parameter property=\"frntsq\" ref=\"CnapDict.Ccms.frntsq\" jdbcType=\"VARCHAR\" javaType=\"DcspBaseType.U_SQ0020\" mode=\"in\" longname=\"原前置流水\" parameterType=\"condition\"/>");



    }


    private String [] getEnglishName() {
        String str = "FCBPDT\n" +
                "FCBPSQ\n" +
                "IOTYPE\n" +
                "SUBSYS\n" +
                "MESGTP\n" +
                "SENDOG\n" +
                "SDCLOG\n" +
                "RECVOG\n" +
                "RVCLOG\n" +
                "MSETDT\n" +
                "MSETSQ\n" +
                "MESGTM\n" +
                "PROCST\n" +
                "QURYTP\n" +
                "ORMGTP\n" +
                "ORSDOG\n" +
                "ORMEDT\n" +
                "ORMESQ\n" +
                "ORBSTP\n" +
                "ORISOG\n" +
                "ORIROG\n" +
                "ORDEDT\n" +
                "ORDESQ\n" +
                "ORIOTP\n" +
                "CRCYCD\n" +
                "TRANAM\n" +
                "QUCONT\n" +
                "REPYTG\n" +
                "ORFBDT\n" +
                "ORFBSQ\n" +
                "CITYCD\n" +
                "BRCHNO\n" +
                "USERID\n" +
                "CKBKUS\n" +
                "AUTHUS\n" +
                "VERSTG\n" +
                "STPSTG\n" +
                "ACPICD\n" +
                "TXARID\n" +
                "PRINNM\n";


        return str.split("\n");


    }


    private String [] getZhName() {
        String str = "前置日期\n" +
                "前置流水                                           \n" +
                "来往标志          0：往|1：来                      \n" +
                "系统编号                                           \n" +
                "报文类型                                           \n" +
                "发起参与机构                                       \n" +
                "发起直接参与机构                                   \n" +
                "接收参与机构                                       \n" +
                "接收直接参与机构                                   \n" +
                "报文委托日期                                       \n" +
                "报文委托流水                                       \n" +
                "报文发送时间  业务头信息                           \n" +
                "业务状态                                           \n" +
                "查询类型          QT00：整包查询|QT01：单笔查询    \n" +
                "原报文类型        待查询的报文，查复时为原查询报文 \n" +
                "原发起参与机构    待查询的报文，查复时为原查询报文 \n" +
                "原委托日期        待查询的报文，查复时为原查询报文 \n" +
                "原报文序号        待查询的报文，查复时为原查询报文 \n" +
                "原明细业务类型编码                                 \n" +
                "原明细发起间接参与机构                             \n" +
                "原明细接收间接参与机构                             \n" +
                "原明细委托日期                                     \n" +
                "原明细报文序号                                     \n" +
                "原业务来往标志          0：往|1：来                \n" +
                "货币符号                                           \n" +
                "待查询业务金额                                     \n" +
                "查询/查复内容                                      \n" +
                "是否已回复        Y:已回复|N:未回复                \n" +
                "原前置日期                                         \n" +
                "原前置流水                                         \n" +
                "分行号                        \n" +
                "机构号                        \n" +
                "操作柜员号                    \n" +
                "复核柜员号                                         \n" +
                "授权柜员号                                         \n" +
                "一代或二代标志 1：一代|2：二代                     \n" +
                "村镇标识 0-主体行 1-村镇银行                       \n" +
                "所属法人行号                                       \n" +
                "地区代码\n" +
                " ";

        String[] split = str.split("\n");
        for (int i=0; i<split.length; i++) {
            String trimStr = split[i].trim();
            if (StringUtils.isBlank(trimStr)) {
                trimStr = "*v*";
            }
            split[i] = trimStr;
        }

        return split;

    }


    private String [] getJavaTypes() {
       String str = "VARCHAR2(8)\n" +
               "VARCHAR2(20)\n" +
               "VARCHAR2(1)\n" +
               "VARCHAR2(4)\n" +
               "VARCHAR2(20)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(8)\n" +
               "VARCHAR2(20)\n" +
               "VARCHAR2(23)\n" +
               "VARCHAR2(4)\n" +
               "VARCHAR2(4)\n" +
               "VARCHAR2(20)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(8)\n" +
               "VARCHAR2(20)\n" +
               "VARCHAR2(4)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(8)\n" +
               "VARCHAR2(8)\n" +
               "VARCHAR2(1)\n" +
               "VARCHAR2(3)\n" +
               "NUMBER(19,2)\n" +
               "VARCHAR2(1536)\n" +
               "VARCHAR2(2)\n" +
               "VARCHAR2(8)\n" +
               "VARCHAR2(20)\n" +
               "VARCHAR2(12)\n" +
               "VARCHAR2(12)\n" +
               "VARCHAR2(12)\n" +
               "VARCHAR2(12)\n" +
               "VARCHAR2(12)\n" +
               "VARCHAR2(2)\n" +
               "VARCHAR2(2)\n" +
               "VARCHAR2(14)\n" +
               "VARCHAR2(12)\n" +
               "NUMBER(22)\n";

        String[] javaTypes = str.split("\n");
        for (int i = 0; i < javaTypes.length; i++) {
            javaTypes[i] = javaTypes[i].trim();
        }


        for (int i = 0; i < javaTypes.length; i++) {
            int values = parseVarchar2(javaTypes[i]);
            if (values <= 10) {
                javaTypes[i] = "DcspBaseType.U_CD0010";
            } else if (values <= 30) {
                javaTypes[i] = "DcspBaseType.U_AC0032";
            } else if (values <= 60) {
                javaTypes[i] = "DcspBaseType.U_AD060";
            } else if (values <= 180) {
                javaTypes[i] = "DcspBaseType.U_NA0180";
            } else if (values <= 256) {
                javaTypes[i] = "DcspBaseType.U_TX0256";
            } else if (values <= 315) {
                javaTypes[i] = "DcspBaseType.U_TX0315";
            } else if (values <= 500) {
                javaTypes[i] = "DcspBaseType.U_AD0500";
            } else if (values <= 600) {
                javaTypes[i] = "DcspBaseType.U_TX0600";
            } else if (values <= 768) {
                javaTypes[i] = "DcspBaseType.U_TX0768";
            } else if (values <= 1536) {
                javaTypes[i] = "DcspBaseType.U_TX1536";
            } else if (values <= 3000) {
                javaTypes[i] = "DcspBaseType.U_TX3000";
            } else if (values <= 4096) {
                javaTypes[i] = "DcspBaseType.U_SG4096";
            }


        }

        return javaTypes;
    }

    private String [] getSearchCondition() {
        String str = "msetsq";
        String [] strs = str.split(",");
        for (int i=0; i<strs.length; i++) {
            strs[i] = strs[i].toLowerCase().trim();
        }

        return strs;
    }

    private String [] getInputCondition() {
        String str = "frntdt,frntsq";


        String [] strs = str.split(",");

        for (int i=0; i<strs.length; i++) {
            strs[i] = strs[i].toLowerCase().trim();
        }

        return strs;

    }

    private void doDynamicSelectConfig() {

        genTaget.dynamicSelectName = "查询CCMS_M314明细";
        genTaget.id = "selCcms_m314_detail";
        genTaget.tableName = "ccms_m314";

        genTaget.inputCondition = getInputCondition();

        genTaget.resultProperty = getEnglishName();
        genTaget.resultJavaType = getJavaTypes();
        genTaget.resultLongName = getZhName();

        genTaget.searchCondition = getSearchCondition();


    }


    @Test
    public void doTestdoDynamicSelect() {
        getInputCon();
        doDynamicSelectConfig();
        try {
            genDynamicSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void doGenSelectOne() {
        getInputCon();
        doDynamicSelectConfig();

        try {
            genSelectOne();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
