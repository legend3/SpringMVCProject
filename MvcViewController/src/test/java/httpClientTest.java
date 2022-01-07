import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class httpClientTest {
    @Test
    public void testGet01() throws UnsupportedEncodingException {
        /**
         * 使用httpclient发送get请求
         * 1.无参数请求
         * 2.加请求头
         * 3.有参的情况，需要urlencode(处理特殊符号)
         * 4.增加一些httpclient的api认识
         */
        //1.HttpClients: httpclient包的工具类, 可关闭的httpclient客户端，相当于你打开的一个浏览器
        CloseableHttpClient closeableHttpClient =  HttpClients.createDefault();
        String urlStr = "https://www.baidu.com";
        String nameParam = "123+abc";
        //做urlencode: 如果时浏览器的话，浏览器会帮我们自动给做了
        nameParam = URLEncoder.encode(nameParam, StandardCharsets.UTF_8.name());
        urlStr = "http://localhost:8080/MvcViewController/handler/testParam?uname=" + nameParam;
        //2.构造httGet请求对象
        HttpGet httpGet = new HttpGet(urlStr);
        //加请求头
            //User-Agent: 用什么浏览器发送的；解决httpclient被认为不是真人行为
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36");
            //防盗链，value的值要是发生防盗链的网站;(爬虫时可能爬不下，有防盗链)
        httpGet.addHeader("Referer", "https://www.baidu.com");
        //3.响应对象
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            //5.StatusLine代表本次请求的成功/失败的状态
                //常量响应码类:   HttpStatus
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK) {
                System.out.println("响应成功!");
                 //6.获取响应头
                Header[] allHeaders = response.getAllHeaders();//返回xxx请求的所有响应信息
                for (Header header: allHeaders) {
                    System.out.println("响应头-" + header.getName() + ": " + header.getValue());
                }
                String JSESSIONID = response.getHeaders("Set-Cookie")[0].getValue().split("JSESSIONID=")[1].split("; Path=")[0];//按照发送顺序依次返回所有指定的标头信息
                System.out.println("JSESSIONID: " + JSESSIONID);
            } else {
                System.out.println("响应失败: " + statusLine.getStatusCode());
            }
            //获取响应结果（HttpEntity，接口，各种结果类型的实现）：此处的实现->DecompressingEntity
                /*
                    7.HttpEntity不仅可以作为结果，也可以作为请求的参数实体，有很多的实现！
                 */
            HttpEntity entity = response.getEntity();
            System.out.println(entity.getContentType());//通过HttpEntity获取xxx响应头
            //8.工具类，对HttpEntity操作的工具类
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            //确保(输入)流关闭
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if(closeableHttpClient !=null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void testGet02() throws UnsupportedEncodingException {
        /**
         * 获取网络上的图片并保存到本地
         */
        CloseableHttpClient closeableHttpClient =  HttpClients.createDefault();
        String urlStr = "https://www.baidu.com";
        HttpGet httpGet = new HttpGet(urlStr);
        //可关闭的响应
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            //通过HttpEntity获取Content-Type
            //image/jpg image/jpeg image/png image/图片后缀
            entity.getContentType().getValue();
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            EntityUtils.consume(entity);

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if(closeableHttpClient !=null) {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
