package top.yms.recent;

import com.google.gson.Gson;
import top.yms.utils.MyHttpUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCurrencyDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    static class CurrencyTask implements Runnable {

        private CurrencyParam currencyParam;
        private String url;

        public CurrencyTask(CurrencyParam currencyParam, String url) {
            this.currencyParam = currencyParam;
            this.url = url;
        }

        @Override
        public void run() {

            Gson gson = new Gson();
            String gsonStr = gson.toJson(currencyParam);

            try {
                String rsStr = MyHttpUtils.doJsonRequest(url, null, gsonStr);
                System.out.println("结果|"+rsStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {


        int n = 3;
        CurrencyParam [] currencyParams = new CurrencyParam[n];
        String [] url = new String[n];

        String key = "start";

        url[0] = "http://10.22.84.11:8080/currency/"+key;
        url[1] = "http://10.22.84.12:8080/currency/"+key;
        url[2] = "http://localhost:8080/currency/"+key;


        for(int i=0; i<n; i++) {
            CurrencyParam currencyParam = new CurrencyParam();
            currencyParam.setPeopleNum(3);
            currencyParam.setStart(11);
            currencyParam.setEnd(20);
            currencyParam.setUo(2);

            currencyParams[i] = currencyParam;

        }

        currencyParams[0].setReqName("Alice");
        currencyParams[1].setReqName("yangmingsen");
        currencyParams[2].setReqName("Blob");


        for(int i=0; i<n; i++) {
            executorService.execute(new CurrencyTask(currencyParams[i], url[i]));
        }




    }
}
