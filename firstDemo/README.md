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


>项目中(会)同时兼容 springMVC和Servlet  
<servlet-mapping>  
<servlet-name>springDispatcherServlet</servlet-name>  会找springDispatcherServlet(交给spring容器拦截)，不是springDispatcherServlet(交给servlet)
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
指定springmvc配置文件的路径，如果要省略，必须放到 默认路径：/WEB-INF/springDispatcherServlet-servlet.xml  

> 现在是:(推荐)
/WEB-INF/springDispatcherServlet-servlet.xml

> 也可以改为，则<servlet-name>AAA</servlet-name>
/WEB-INF/AAA-servlet.xml

> <param-name>的值为org.springframework.web.servlet.DispatcherServlet中的contextConfigLocation属性



