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
    <input type="submit" value="post">
</form>


</body>
</html>
