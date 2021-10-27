# springmvc:
1. jar
- spring-aop.jar
- spring-bean.jar
- spring-context.jar
- spring-core.jar
- spring-web.jar 
- spring-webmvc.jar 
- commons-logging.jar

报错NoClassDefFoundError：缺少jar


2. 第一个SpringMVC程序
- Servet - Springmvc
- jsp ->Servlet (Springmvc)->Jsp

url

- springmvc配置文件 springmvc.xml
  - 选中常用的命名空间：beans  aop context  mvc

- 普通的servlet流程：
  - 请求-url-pattern -交给对应的servlet去处理

>如果现在想用springmvc，而不是普通的servlet，如何告知程序？-如何让springmvc 介入程序：
需要配置一个 Springmvc自带的servlet

通过以下配置，拦截所有请求，交给SpringMVC处理：
>
    <servlet>  
        <servlet-name>springDispatcherServlet</servlet-name>  
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>  
        <init-param>  
            <param-name>contextConfigLocation</param-name>  
            <param-value>classpath:springmvc.xml</param-value>  
        </init-param>  
        <load-on-startup>1</load-on-startup>  
    </servlet>`

  `<servlet-mapping>
  	<servlet-name>springDispatcherServlet</servlet-name>
  	<url-pattern>/</url-pattern>
  </servlet-mapping>`

>其中：  
<url-pattern>.action</url-pattern>

>/:一切请求  ，注意不是 /*
/user:拦截(接收)以 /user开头的请求
/user/abc.do  :只拦截该请求
.action:只拦截(接收) .action结尾的请求


>项目中同时兼容 springMVC和Servlet  
<servlet-mapping>  
<servlet-name>springDispatcherServlet</servlet-name>  
<url-pattern>.action</url-pattern>  
</servlet-mapping>  


> 
    <servlet>
        <servlet-name>springDispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
      </servlet>
通过  

 	<init-param>
  			<param-name>contextConfigLocation</param-name>
  			<param-value>classpath:springmvc.xml</param-value>
  	</init-param>
指定springmvc配置文件的路径，如果要省略，必须放到 默认路径：    
/WEB-INF/springDispatcherServlet-servlet.xml  

## RequestMapping注解映射
- 是去匹配@RequestMapping注解，可以和方法名、类名不一致  
- 通过method指定请求方式(get  post  delete put):  
  - @RequestMapping(value="welcome",method=RequestMethod.POST)//映射  
  
- 设置name="xxxx"的情况：
  - params= {"name2=zs","age!=23"}
  - name2:必须有name="name2"参数
  - age!=23:
    - a.如果有name="age"，则age值不能是23
    - b.没有age
    !height  ：不能height"属性，否则不能拦截(接收)

- 设置headers="xxx"的情况:
  - 指定请求头中键值的值，否则不被接收
    - @RequestMapping(value="welcome2", headers="Accept-Encoding=gzip, deflate, br")
      - 接收示例：
        > @RequestMapping(value="welcome2",headers= {"Accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9","Accept-Encoding=gzip, deflate, br"})
      
## ant风格的请求路径
?  单字符  
* 任意个字符（0或多个）  
** 任意目录   

- @RequestMapping(value="welcome3/**/test")
接受示例：
> a href="welcome3/abc/xyz/abccc/test"


- 通过@PathVariable获取动态参数  
>public String  welcome5(@PathVariable("name") String name ) {    
System.out.println(name);    
return "success" ;  
}


## REST风格 ：软件编程风格

> Springmvc:  

- GET  :查
- POST  ：增
- DELETE ：删
- PUT ：改

普通浏览器只支持get post方式；其他请求方式 如 delelte|put请求是通过 过滤器新加入的支持。

springmvc实现 ：put|post请求方式的步骤
a.增加过滤器

		<!-- 增加HiddenHttpMethodFilte过滤器：目的是给普通浏览器 增加 put|delete请求方式 -->
	<filter>
			<filter-name>HiddenHttpMethodFilte</filter-name>
			<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
	
	</filter>
	
	<filter-mapping>
			<filter-name>HiddenHttpMethodFilte</filter-name>
			<url-pattern>/*</url-pattern>
	</filter-mapping>

b.表单

	<form action="handler/testRest/1234" method="post">
		<input type="hidden"  name="_method" value="DELETE"/>
		<input type="submit" value="删">
	</form>
i:必须是post方式
ii:通过隐藏域 的value值 设置实际的请求方式 DELETE|PUT

c.控制器
@RequestMapping(value="testRest/{id}",method=RequestMethod.DELETE)
public String  testDelete(@PathVariable("id") Integer id) {
System.out.println("delete：删 " +id);
//Service层实现 真正的增
return "success" ;//  views/success.jsp，默认使用了 请求转发的 跳转方式
}
通过	method=RequestMethod.DELETE	匹配具体的请求方式



此外，可以发现 ，当映射名相同时@RequestMapping(value="testRest)，可以通过method处理不同的请求。


过滤器中 处理put|delete请求的部分源码：
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
throws ServletException, IOException {

		HttpServletRequest requestToUse = request;

		if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
			String paramValue = request.getParameter(this.methodParam);
			if (StringUtils.hasLength(paramValue)) {
				requestToUse = new HttpMethodRequestWrapper(request, paramValue);
			}
		}

		filterChain.doFilter(requestToUse, response);
	}
原始请求：request，改请求默认只支持get post  header
但是如果 是"POST"  并且有隐藏域		<input type="hidden"  name="_method" value="DELETE"/>
则，过滤器 将原始的请求 request加入新的请求方式DELETE，并将原始请求 转为 requestToUse 请求（request+Delete请求）
最后将requestToUse 放入 请求链中， 后续再事情request时  实际就使用改造后的 requestToUse

@RequestParam("uname") String name,@RequestParam(value="uage",required=false,defaultValue="23")

@RequestParam("uname"):接受前台传递的值，等价于request.getParameter("uname");

required=false:该属性 不是必须的。
defaultValue="23"：默认值23





获取请求头信息 @RequestHeader
public String  testRequestHeader(@RequestHeader("Accept-Language")  String al  ) {

通过@RequestHeader("Accept-Language")  String al   获取请求头中的Accept-Language值，并将值保存再al变量中

通过mvc获取cookie值（JSESSIONID）
@CookieValue
(前置知识： 服务端在接受客户端第一次请求时，会给该客户端分配一个session （该session包含一个sessionId）),并且服务端会在第一次响应客户端时 ，请该sessionId赋值给JSESSIONID 并传递给客户端的cookie中


小结：
SpringMVC处理各种参数的流程/逻辑：
请求：  前端发请求a-> @RequestMappting("a")
处理请求中的参数xyz：
@RequestMappting("a")
public String  aa(@Xxx注解("xyz")  xyz)
{

	}

使用对象（实体类Student）接受请求参数

在SpringMVC中使用原生态的Servlet API  :HttpServletRequest ：直接将 servlet-api中的类、接口等 写在springMVC所映射的方法参数中即可：
@RequestMapping(value="testServletAPI")
public String testServletAPI(HttpServletRequest  request,HttpServletResponse response) {
//			request.getParameter("uname") ;
System.out.println(request);
return "success" ;
}









