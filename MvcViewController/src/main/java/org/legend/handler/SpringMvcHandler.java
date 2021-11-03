package org.legend.handler;


import org.legend.entity.Person;
import org.legend.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//接口/类型 注解 配置
/*
    org.xxx.servlet
    org.xxx.controller
    org.xxx.handler
    org.xxx.action
都是指后台(Servlet组件环节)controller层
 */
//@SessionAttributes(value="studentModelAndView")//如果要在request中存放studentModelAndView对象 则同时会将该对象放入session域中
//@SessionAttributes(types = {Student.class, Address.class})//如果要在request中存放Student类型的对象，则同时会将该类型对象放入session域中
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
    public String  testObjectProperties(Student student) {//Student属性必须和form表单中的属性Name值一致(且支持级联属性)
//        String name = request.getParameter("uname");
        System.out.println(student);
        return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    @RequestMapping(value="testServletAPI")
    public String  testServletAPI(HttpServletRequest request, HttpServletResponse response) {//Student属性必须和form表单中的属性Name值一致(且支持级联属性)
//        String name = request.getParameter("uname");
        System.out.println(request);
        return "success";//  views/success.jsp，默认使用了 请求转发的 跳转方式
    }
    //给响应页面带入值
    @RequestMapping(value="testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mv = new ModelAndView("success");

        Student student = new Student();
        student.setStuNo(2);
        student.setStuName("lilei");

        //只存储到request域中
        mv.addObject("studentModelAndView", student);//相当于request.setAttribute("student", student);给success.jsp带入数据
        return mv;
    }
    @RequestMapping(value="testModelMap")
    public String testModelMap(ModelMap mm) {
        Student student = new Student();
        student.setStuNo(2);
        student.setStuName("lilei");

        mm.put("studentModelMap", student);//相当于request.setAttribute("student", student);给success.jsp带入数据
//        forward:            redirect:
        return "redirect:/views/success.jsp";
    }
    @RequestMapping(value="testMap")
    public String testMap(Map<String, Object> m) {
        Student student = new Student();
        student.setStuNo(2);
        student.setStuName("lilei");

        m.put("studentMap", student);//相当于request.setAttribute("student", student);给success.jsp带入数据
        return "success";
    }
    @RequestMapping(value="testModel")
    public String testModel(Model model) {
        Student student = new Student();
        student.setStuNo(2);
        student.setStuName("lilei");

        model.addAttribute("studentModel", student);//相当于request.setAttribute("student", student);给success.jsp带入数据
        return "success";
    }

    @ModelAttribute//在任何一次请求前，都会执行@ModelAttribute修饰的方法
    //在请求该类的各个方法前，均被执行的涉及是基于一个思想：一个控制器 只做一个功能
    public void queryStudentById(Map<String, Object> m) {
        //模拟调用三层查询数据库的操作(好比这些是从数据库中查询到的一条student数据)
        Student student = new Student();
        student.setStuNo(31);
        student.setStuName("lilei");
        student.setAge(23);

        m.put("studentModelAttribute", student);//约定：map的key就是方法(testModelAttribute)参数 类型的首字母小写
    }
    @RequestMapping(value="testModelAttribute")
    public String testModelAttribute(@ModelAttribute("studentModelAttribute")Student student) {
        //修改
        student.setStuName(student.getStuName());//将名字修改为请求填写传入的名字
        System.out.println(student);
        return "success";
    }
    //测试转换器
    @RequestMapping(value="testConverter")
    public String testConverter(@RequestParam("studentInfo")  Student student) {// 前端：2-zs-23
        System.out.println(student);
        return "success";
    }
    //测试数据化
    @RequestMapping(value="testDateTimeFormat")//如果Student格式化出错，会将错误信息传入result中
    /**
     * 被校验的属性: Student对象
     * BindingResult:发生异常时的错误信息
     * Map:获取请求值传递给HttpServletRequest
     */
    public String testDateTimeFormat(@Valid Student student, BindingResult result, Map<String, Object> map) {// 前端：2-zs-23
        System.out.println(student);
        if(result.getErrorCount() > 0) {
            for(FieldError error: result.getFieldErrors()){
                System.out.println(error);
                map.put("errors", result.getFieldErrors());//将错误信息传入request域中的errors中
            }
        }
        return "success";
    }
    //测试Ajax
    @ResponseBody//告诉srpigmvc此时的返回不是一个view页面，而是一个Ajax调用的返回值(Json数组)！(谁调就返回给谁，响应给Ajax)
    @RequestMapping(value="testJson", method = RequestMethod.POST, produces="application/json")
    //produces: 指定返回的内容类型 ，仅当request请求头中的(Accept)类型中包含该指定类型才返回(本例子要求响应返回json)
    //补充: consumes: 指定处理请求的 提交内容类型 （Content-Type），例如 application/json, text/html
    public List<Person> testJson() {
        //Controller-Service-dao
        //StudetnService studetnService = new StudetnService();
        //List<Student> students = studetnService.queryAllStudent();
        //模拟调用service的查询操作

        Person person1 = new Person(1,"zs",23);
        Person person2 = new Person(2,"ls",24);
        Person person3 = new Person(3,"ww",25);
        Person person4 = new Person(4,"zl",26);
        List<Person> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);

        return persons;//返回给Ajax，以Json数组的形式返回给Ajax的result.
    }
    //测试上传文件
    @RequestMapping(value = "testUploadFile", method = RequestMethod.POST)
    public String testUploadFile(@RequestParam("describe") String describe, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("文件描述：" + describe);
        //文件上传到硬盘
        //流可以传输一切
        InputStream input = file.getInputStream();//IO
        String filename = file.getOriginalFilename();//获取上传文件的名字
        OutputStream output = new FileOutputStream("d:\\" + filename);//输出到哪个硬盘位置

        byte[] bs = new byte[1024];//每次读1M(缓冲)
        int len = -1;
        while ((len = input.read(bs)) != -1) {
            output.write(bs, 0, len);//bs读取到的数据，从0下标开始写
        }
        input.close();
        output.close();
        System.out.println("上传完毕！");
        return "success";
    }
    @RequestMapping(value = "testInterceptor")
    public String testInterceptor() {//
        System.out.println("处理请求的方法....");
        return "success";
    }
    @RequestMapping(value = "testException")
    public String testException() {
        System.out.println(1/0);
        return "success";
    }
}
