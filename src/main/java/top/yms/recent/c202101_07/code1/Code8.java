package top.yms.recent.c202101_07.code1;

import com.alibaba.fastjson.JSONObject;

import java.beans.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Code8 {
    public static void main(String[] args) throws Exception {
        xmlEncoder2();
    }

    private static final PersistenceDelegate bigDecimalPersistenceDelegate = new DefaultPersistenceDelegate() {
        protected Expression instantiate(Object oldInstance,
                                         Encoder out) {
            BigDecimal bd = (BigDecimal) oldInstance;
            return new Expression(oldInstance, oldInstance
                    .getClass(), "new", new Object[] { bd
                    .toString() });
        }

        protected boolean mutatesTo(Object oldInstance,
                                    Object newInstance) {
            return oldInstance.equals(newInstance);
        }
    };

    //将对象从XML文档中读出来
    private static void xmlEncoder2() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\tmp\\testGit\\xx.xml"));

        XMLDecoder xmlDncoder = new XMLDecoder(bufferedInputStream);
        HashMap<String, Object> map = (HashMap) xmlDncoder.readObject();

        JSONObject jsonObject = new JSONObject();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                HashMap<String, String> mmp = (HashMap)value;
                JSONObject secondJsonObj = new JSONObject();
                for(Map.Entry<String, String> entry1 : mmp.entrySet()) {
                    secondJsonObj.put(entry1.getKey(), entry1.getValue());
                }
                jsonObject.put(key,secondJsonObj);
            } else {
                jsonObject.put(key, value);
            }
        }


        System.out.println(jsonObject.toJSONString());

        xmlDncoder.close();

    }


    //将对象从XML文档中读出来
    private static void xmlEncoder() throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\tmp\\testGit\\xx.xml"));

        XMLDecoder xmlDncoder = new XMLDecoder(bufferedInputStream);
        HashMap map = (HashMap) xmlDncoder.readObject();

        Object fd_status = map.get("fd_status");
        System.out.println(fd_status);
        map.put("fd_status","已经回收");
        System.out.println(map.get("fd_status"));



        Object fd_detail = map.get("fd_detail");
        ArrayList<HashMap<String, Object>> list = (ArrayList)fd_detail;

        HashMap<String, Object> map2 = list.get(0);
        for(Map.Entry<String, Object> entry : map2.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            System.out.println(key+"&"+value);
        }



//        xmlEncoder(map);

        String xmlStr = objectXmlEncoder(map);
        List list1 = objectXMLDecoderByString(xmlStr);
        System.out.println("size ="+list1.size());
        HashMap<String, Object> mmap = (HashMap)list1.get(0);
        for(Map.Entry<String, Object> entry : mmap.entrySet()) {
            System.out.println(entry.getKey()+"&"+entry.getValue());
        }
        Object fd_status1 = mmap.get("fd_status");
        System.out.println(fd_status1);
        mmap.put("fd_status","已经回收2");
        System.out.println(mmap.get("fd_status"));

        xmlDncoder.close();
    }

    //将对象写入XML文档中
    private static void xmlEncoder(Map map) throws Exception {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\tmp\\testGit\\xx1.xml"));
        XMLEncoder xmlEncoder = new XMLEncoder(bufferedOutputStream);

        xmlEncoder.setPersistenceDelegate(BigDecimal.class,
                bigDecimalPersistenceDelegate);

        //实例化的类必须是public 否则会报错
        xmlEncoder.writeObject(map);
        xmlEncoder.close();
    }


    /**
     * 将对象转xml
     * @param obj
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public static String objectXmlEncoder(Object obj)
            throws FileNotFoundException, IOException, Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(baos);
        encoder.setPersistenceDelegate(BigDecimal.class,
                bigDecimalPersistenceDelegate);
        encoder.writeObject(obj);
        encoder.flush();
        encoder.close();
        String rtnVal = new String(baos.toByteArray(), "UTF-8");
        baos.close();
        return rtnVal;
    }

    /**
     * 读取由objSource指定的XML文件中的序列化保存的对象,返回的结果经过了List封装
     *
     * @param objSource
     *            带全部文件路径的文件全名
     * @return 由XML文件里面保存的对象构成的List列表(可能是一个或者多个的序列化保存的对象)
     * @throws FileNotFoundException
     *             指定的对象读取资源不存在
     * @throws IOException
     *             读取发生错误
     * @throws Exception
     *             其他运行时异常发生
     */
    public static List objectXmlDecoder(String objSource)
            throws FileNotFoundException, IOException, Exception {
        File fin = new File(objSource);
        return objectXmlDecoder(new FileInputStream(fin));
    }

    /**
     * 将String形式的xml内容转对象
     * @param ins
     * @return
     * @throws Exception
     */
    public static List objectXMLDecoderByString(String ins) throws Exception {
        String safeIns = ins.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
        return objectXmlDecoder(new ByteArrayInputStream(safeIns.getBytes("UTF-8")));
    }

    public static List objectXmlDecoder(InputStream ins) throws IOException,
            Exception {
        List objList = new ArrayList();
        XMLDecoder decoder = new XMLDecoder(ins);
        Object obj = null;
        try {
            while ((obj = decoder.readObject()) != null) {
                objList.add(obj);
            }
            // readObject结束是以 ArrayIndexOutOfBoundsException异常为标识
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        finally {
            ins.close();
            decoder.close();
        }
        return objList;
    }


}
