# springmvc: 
> springmvc主要负责Controller层！  
org.xxx.servlet  
org.xxx.controller  
org.xxx.handler  
org.xxx.action  
.....  
都是指后台(Servlet组件环节)controller层  

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
    !name2  ：不能name="name2"的属性

      
ant风格的请求路径
?  单字符
*  任意个字符（0或多个）
   ** 任意目录


@RequestMapping(value="welcome3/**/test")
接受示例：

a href="welcome3/abc/xyz/abccc/test"


通过@PathVariable获取动态参数
<>









