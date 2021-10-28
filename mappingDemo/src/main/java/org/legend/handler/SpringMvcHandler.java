package org.legend.handler;

import org.legend.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    //rest风格
    @RequestMapping(value="testRest/{id}", method = RequestMethod.POST)
    public String  testPost(@PathVariable("id") Integer id) {
        System.out.println("post值:" + id);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testRest/{id}", method = RequestMethod.DELETE)
    public String  testDelete(@PathVariable("id") Integer id) {
        System.out.println("delete值:" + id);
        return "redirect:/views/success.jsp" ;//delete在Tomcat7以上都只能重定向，转发：405
    }
    @RequestMapping(value="testRest/{id}", method = RequestMethod.PUT)
    public String  testPut(@PathVariable("id") Integer id) {
        System.out.println("put值:" + id);
        return "redirect:/views/success.jsp" ;//put在Tomcat7以上都只能重定向，转发：405
    }
    @RequestMapping(value="testRest/{id}", method = RequestMethod.GET)//Restful风格传值用@PathVariable
    public String  testGet(@PathVariable("id") Integer id) {
        System.out.println("get值:" + id);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    //普通风格-获取请求参数值
    @RequestMapping(value="testParam")
    public String  testParam(@RequestParam("uname") String name, @RequestParam(value = "uage", required = false, defaultValue = "23") Integer age) {//普通风格传值用@RequestParam
//        String name = request.getParameter("uname");
        System.out.println(name);
        System.out.println(age);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    //普通风格-获取请求头信息
    @RequestMapping(value="testRequestHeader")
    public String  testHeader(@RequestHeader("Accept-Language") String AL) {//普通风格传Header用@RequestHeader
//        String name = request.getParameter("uname");
        System.out.println(AL);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testCookie")
    public String  testCookie(@CookieValue String JSESSIONID) {//普通风格传Header用@RequestHeader
//        String name = request.getParameter("uname");
        System.out.println(JSESSIONID);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testObjectProperties")
    public String  testStudent(Student student) {//Student属性必须和form表单中的属性Name值一致(且支持级联属性)
//        String name = request.getParameter("uname");
        System.out.println(student);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testServletAPI")
    public String  testStudent(httpServletRequest request, httpServletResponse response) {//Student属性必须和form表单中的属性Name值一致(且支持级联属性)
//        String name = request.getParameter("uname");
        System.out.println();
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
}
