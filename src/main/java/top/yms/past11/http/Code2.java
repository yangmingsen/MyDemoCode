package top.yms.past11.http;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.arronlong.httpclientutil.builder.HCB;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;

public class Code2 {
    public static void flush() throws HttpProcessException {

        String reqUrl = "https://form-preview-api.eqxiu.com/lp/r/8348078/5682620418?code=A7rj0GGc";

        HttpClientContext context = new HttpClientContext();
        CookieStore cookieStore = new BasicCookieStore();
        context.setCookieStore(cookieStore);

//		//打印参数，可以看到cookie里已经有值了。
//		cookieStore = context.getCookieStore();
//		for (Cookie cookie : cookieStore.getCookies()) {
//			System.out.println(cookie.getName()+"--"+cookie.getValue());
//		}

//        HttpClientContext context1 = HttpCookies.custom().getContext();

        //访问积分管理页面
        Header[] headers = HttpHeader.custom().userAgent("Mozilla/5.0").build();

        HCB hcb = HCB.custom();
        HttpClient client =hcb.build();
        HttpConfig config = HttpConfig.custom().url(reqUrl).encoding("utf-8").context(context);

        String result2 = HttpClientUtil.post(config);    //post请求
        System.out.println(result2);

    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            try {
                flush();
            } catch (HttpProcessException e) {
                e.printStackTrace();
            }
        }
    }




    /**
     * 通过正则表达式获取内容
     *
     * @param regex		正则表达式
     * @param from		原字符串
     * @return
     */
    public static String[] regex(String regex, String from){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(from);
        List<String> results = new ArrayList<String>();
        while(matcher.find()){
            for (int i = 0; i < matcher.groupCount(); i++) {
                results.add(matcher.group(i+1));
            }
        }
        return results.toArray(new String[]{});
    }

}
