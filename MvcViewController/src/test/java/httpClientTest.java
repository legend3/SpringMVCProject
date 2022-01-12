import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
         * 保存网络上的图片并保存到本地
         */
        CloseableHttpClient closeableHttpClient =  HttpClients.createDefault();
        String urlStr = "http://cd.bendibao.com/images/logo-new.jpg";
        HttpGet httpGet = new HttpGet(urlStr);
        //可关闭的响应
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            //通过HttpEntity获取Content-Type
            //image/jpg image/jpeg image/png image/图片后缀
            String contentType = entity.getContentType().getValue();//获取请求是啥Content-Type(内容类型)
            String suffix = ".jpg";
            if(contentType.contains("jpg") || contentType.contains("jpeg")) {
                suffix = ".jpg";
            } else if(contentType.contains("bmp") || contentType.contains("bitmap")) {
                suffix = ".bmp";
            } else if(contentType.contains("png")) {
                suffix = ".png";
            } else if(contentType.contains("gif")) {
                suffix = ".gif";
            }
            byte[] bytes = EntityUtils.toByteArray(entity);
//            System.out.println("字节数组大小: " + bytes.length);
            String localAbsPath = "d:\\本地宝" + suffix;
            FileOutputStream fos = new FileOutputStream(localAbsPath);
            fos.write(bytes);
            fos.close();
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
    public void testGet03() {
        /**
         * 1.设置访问代理:    避同一ip高频率的访问被网站安全机制封掉ip，可以配置代理ip，"一会儿用这个，一会儿用那个"避免被封！
         * 2.连接超时和读取超时
         */
        CloseableHttpClient client = HttpClients.createDefault();
        String urlStr = "http://localhost:8080/MvcViewController/handler/testMap";
        HttpGet httpGet = new HttpGet(urlStr);
        //创建一个代理
        String ip = "165.225.194.101";
        int port = 10605;
        HttpHost proxy = new HttpHost(ip, port);
        //对每一个请求进行配置，会覆盖全局的默认请求配置
        RequestConfig requestConfig = RequestConfig.custom()
//                        .setProxy(proxy)
                //设置超时时间,ms，完成tcp3次握手的时间上限
//                .setConnectTimeout(30000)
                //读取超时，m，从请求的网址获得响应数据的时间间隔
                .setSocketTimeout(3000)
                //指的从连接池里面获取connection的超时时间
//                .setConnectionRequestTimeout(100)
                .build();
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void TestPost01() {
        /**
         * 发送application/x-www-form-urlencoded类型的post请求
         */
        CloseableHttpClient client = HttpClients.createDefault();
        String urlStr = "http://localhost:8080/MvcViewController/handler/testObjectProperties";
        //创建httpPost对象
        HttpPost httpPost = new HttpPost(urlStr);
        //设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        //给post对象设置参数
        /*
            NameValuePair:  <input type="text" name="stuNo"/>的name(stuNo)和input标签里面输入的值就构成了一个NameValuePair对象
            实现类:    BasicNameValuePair
         */
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("stuNo", "1"));
        list.add(new BasicNameValuePair("stuName", "李振国"));
        list.add(new BasicNameValuePair("address.schoolAddress", "杭州中学"));
        list.add(new BasicNameValuePair("address.homeAddress", "古翠路"));
        //把参数集合设置到formEntity
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
        httpPost.setEntity(formEntity);//设置成httpPost的属性

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestPost02() {
        /**
         * 发送application/json类型的post请求
         */
        CloseableHttpClient client = HttpClients.createDefault();
        String urlStr = "http://localhost:8080/MvcViewController/handler/testJsonObjectProperties";
        //配置fiddler代理
        RequestConfig requestConfig = RequestConfig.custom().setProxy(HttpHost.create("127.0.0.1:8888")).build();
        //创建httpPost对象
        HttpPost httpPost = new HttpPost(urlStr);
        //添加代理
        httpPost.setConfig(requestConfig);
        //string: 是一个json字符串
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObj = mapper.createObjectNode();
        jsonObj.put("stuNo","1");
        jsonObj.put("stuName", "李振国");
        jsonObj.put("address.schoolAddress", "杭州中学");
        jsonObj.put("address.homeAddress", "古翠路");

        /*  配置Entity的属性*/
        StringEntity jsonEntity = new StringEntity(jsonObj.toPrettyString(), Consts.UTF_8);
        //也需要给entity设置内容类型
        jsonEntity.setContentType(new BasicHeader("Content-Type", "application/json; charset=utf-8"));
        //设置entity的编码格式
        jsonEntity.setContentEncoding(Consts.UTF_8.name());
        httpPost.setEntity(jsonEntity);//设置成httpPost的属性

        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String toStringResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            System.out.println(toStringResult);
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
