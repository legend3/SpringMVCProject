package org.legend.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//接口/类型 注解 配置
/*
    org.xxx.servlet
    org.xxx.controller
    org.xxx.handler
    org.xxx.action
都是指后台(Servlet组件环节)controller层
 */
@Controller
@RequestMapping(value="handler")
public class SpringMvcHandler {
    //是去匹配@RequestMapping注解，可以和方法名、类名不一致
    @RequestMapping(value="welcome", method = RequestMethod.POST, params = {"name=zs", "age!=23", "!height"})//映射
    public String welcome(){
        return "success";//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    //验证请求头(接收的请求的请求头键值一定要是指定的)
    @RequestMapping(value="welcome2",headers= {"Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9","Accept-Encoding=gzip, deflate, br"})
    public String  welcome2() {
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="welcome3/a?c/test")
    public String  welcome3() {
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="welcome4/a*c/test")
    public String  welcome4() {
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="welcome5/**/test")
    public String  welcome5() {
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="welcome6/{name}")
    public String  welcome6(@PathVariable("name") String name) {
        System.out.println("@PathVariable动态接收值：" + name);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }

    @RequestMapping(value="testRest/{id}", method = RequestMethod.POST)
    public String  welcome7(@PathVariable("id") Integer id) {
        System.out.println("post值:" + id);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testRest/{id}", method = RequestMethod.DELETE)
    public String  welcome8(@PathVariable("id") Integer id) {
        System.out.println("delete值:" + id);
        return "redirect:/views/success.jsp" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testRest/{id}", method = RequestMethod.PUT)
    public String  welcome9(@PathVariable("id") Integer id) {
        System.out.println("put值:" + id);
        return "redirect:/views/success.jsp" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testRest/{id}", method = RequestMethod.GET)
    public String  welcome6(@PathVariable("id") Integer id) {
        System.out.println("get值:" + id);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
}
