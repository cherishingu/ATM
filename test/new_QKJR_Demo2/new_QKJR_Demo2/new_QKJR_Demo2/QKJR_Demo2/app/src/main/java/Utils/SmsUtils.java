package Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by zhuzhuxia on 16/5/11.
 */
public class SmsUtils {

    private static String httpUrl = "http://sms.bechtech.cn/Api/send/data/json";
    private static String httpArg = "accesskey=4746&secretkey=ebefae7a919a4e3fbe6cabf65bb0849b3e187839" +
                                "&mobile=";
    /**
     * @return 返回结果
     */
    public static String request(String phoneNum,String content) {

        httpArg=httpArg+phoneNum+"&content=";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            //"请勿告诉他人，验证码：432100。【乾康金融】"

            httpUrl+= URLEncoder.encode(content, "utf-8");
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
