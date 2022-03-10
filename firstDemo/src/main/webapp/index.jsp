<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/10/24
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <!-- 如果web.xml中的配置是
	  <servlet-mapping>
  			<servlet-name>springDispatcherServlet</servlet-name>
  			<url-pattern>.action</url-pattern>
 	 </servlet-mapping>

	<a href="user/welcome.action">first springmvc - welcome</a>	交由springmvc处理 找 @RuestMapping映射
	<a href="user/welcome.action">first springmvc - welcome</a>交由springmvc处理  找 @RuestMapping映射
	<a href="xxx/welcome">first springmvc - welcome</a>			交由servlet处理  找url-parttern /@WebServlet()
 -->
  <a href="welcome">first springmvc - welcome</a>
  </body>
</html>
