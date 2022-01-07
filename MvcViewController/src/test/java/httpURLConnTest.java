import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class httpURLConnTest {
    @Test
    public void test() throws Exception {
        /**
         * jdk原生api发送http请求
         */
        String urlStr = "https://www.baidu.com";
        URL url = new URL(urlStr);//字符串转为URL
        URLConnection urlConnection = url.openConnection();//打开一个connection连接;抽象对象，很多连接协议
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;//强转http协议
        //设置请求类型
        /*
            请求行
            空格
            请求头
            请求体
         */
        httpURLConnection.setRequestMethod("GET");//设置请求方式
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");//设置请求的编码格式
        //获取httpURLConnection的输入流
        try(
                InputStream is = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                ){
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }

        }


    }
}
