<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>welcome</title>
</head>
<body>
<a href="handler/welcome">mapping springmvc - welcome</a>
<br/>
<a href="handler/welcome2">222 mapping springmvc - welcome</a>
<br/>
<a href="handler/welcome3/abc/test">3333 mapping springmvc - welcome</a>
<br/>
<a href="handler/welcome4/abbbc/test">444 mapping springmvc - welcome</a>
<br/>
<a href="handler/welcome5/asb/xxx/yyy/test">555 springmvc - welcome</a>
<br/>
<a href="handler/welcome6/zs">666 welcome - welcome</a>
<br/>
<form action="/mappingDemo/handler/welcome" method="post">
    name:<input name="name" ><br/>
    age:<input name="age" >
<%--    height:<input name="height" >--%>
    <input type="submit" value="post"/>
</form>

<%--  rest风格——四种请求方式，其中delete/put需要通过过滤器配置再处理  --%>
<form action="/mappingDemo/handler/testRest/1234" method="post">
    <input type="submit" value="增"/>
</form>
<form action="/mappingDemo/handler/testRest/1234" method="post">
    <input type="hidden"  name="_method" value="DELETE"/>
    <input type="submit" value="删"/>
</form>
<form action="/mappingDemo/handler/testRest/1234" method="post">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="submit" value="改"/>
</form>
<form action="/mappingDemo/handler/testRest/1234" method="get">
    <input type="submit" value="查"/>
</form>
<%--  普通风格  --%>
<form action="/mappingDemo/handler/testParam" method="get">
    name:<input name="uname" type="text">
<%--    age:<input name="uage" type="text">--%>
    <input type="submit" value="查"/>
</form>
<%--  普通风格-获取请求头信息  --%>
<a href="/mappingDemo/handler/testRequestHeader">获取请求头信息值</a>
<br/>
<a href="/mappingDemo/handler/testCookie">获取JSESSIONID</a>

<form method="post" action="/mappingDemo/handler/testObjectProperties">
    <%--  属性必须和form表单中的属性Name值  --%>
    学号：<input type="text" name="stuNo"/>
    学生姓名：<input type="text" name="stuName"/>
    <%--  且支持级联属性  --%>
    学校地址：<input type="text" name="address.schoolAddress"/>
    家庭地址：<input type="text" name="address.homeAddress"/>
    <input type="submit" value="学生信息查询"/>
</form>
</br>
<form action="/mappingDemo/handler/testServletAPI">
    springmvc直接使用servletapi获取请求参数: <input type="text" name="uname">
    <input type="submit" value="获取">
</form>
</br>
</br>
<a href="/mappingDemo/handler/testModelAndView">测试ModelAndView给响应页面带入数据</a>
</br>
<a href="/mappingDemo/handler/testModelMap">测试ModelMap给响应页面带入数据</a>
</br>
<a href="/mappingDemo/handler/testMap">测试Map给响应页面带入数据</a>
</br>
<a href="/mappingDemo/handler/testModel">测试Model给响应页面带入数据</a>
<form action="/mappingDemo/handler/testModelAttribute" method="post">
    学号：<input name="stuNo" type="hidden" value="31">
    名字：<input name="stuName" type="text">
    <input type="submit" value="修改">
</form>
</br>
<a href="/mappingDemo/handler/testI18n">测试国际化</a>

</body>
</html>
