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



</body>
</html>
