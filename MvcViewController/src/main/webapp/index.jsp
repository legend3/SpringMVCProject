<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>MvcViewController</title>
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function () {
        $("#testJson").click(function () {
          //通过Ajax请求springmvc
          $.post(
                  "handler/testJson",//服务器地址
                  // {"name": "zs"},//{"name": "zs", "age": 23}
                  function (result) {//服务端处理完毕后的回调函数
                    for(var i=0;i<result.length;i++) {
                        alert(result[i].id + "-" + result[i].name + "-" + result[i].age)
                    }
                  }
          );
        });
      });
    </script>
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
  <form action="/MvcViewController/handler/welcome" method="post">
    name:<input name="name" ><br/>
    age:<input name="age" >
    <%--    height:<input name="height" >--%>
    <input type="submit" value="post"/>
  </form>

  <%--  rest风格——四种请求方式，其中delete/put需要通过过滤器配置再处理  --%>
  <form action="/MvcViewController/handler/testRest/1234" method="post">
    <input type="submit" value="增"/>
  </form>
  <form action="/MvcViewController/handler/testRest/1234" method="post">
    <input type="hidden"  name="_method" value="DELETE"/>
    <input type="submit" value="删"/>
  </form>
  <form action="/MvcViewController/handler/testRest/1234" method="post">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="submit" value="改"/>
  </form>
  <form action="/MvcViewController/handler/testRest/1234" method="get">
    <input type="submit" value="查"/>
  </form>
  <%--  普通风格  --%>
  <form action="/MvcViewController/handler/testParam" method="get">
    获取请求参数name: <input name="uname" type="text">
    <%--    age:<input name="uage" type="text">--%>
    <input type="submit" value="获取"/>
  </form>
  <%--  普通风格-获取请求头信息  --%>
  <a href="/MvcViewController/handler/testRequestHeader">获取请求头信息值</a>
  <br/>
  <a href="/MvcViewController/handler/testCookie">获取JSESSIONID</a>

  <form method="post" action="/MvcViewController/handler/testObjectProperties">
    <%--  属性必须和form表单中的属性Name值  --%>
    学号：<input type="text" name="stuNo"/>
    学生姓名：<input type="text" name="stuName"/>
    <%--  且支持级联属性  --%>
    学校地址：<input type="text" name="address.schoolAddress"/>
    家庭地址：<input type="text" name="address.homeAddress"/>
    <input type="submit" value="学生信息查询"/>
  </form>
  </br>
  <a href="/MvcViewController/handler/testServletAPI">springmvc直接使用servletapi</a>
  </br>
  </br>
  <a href="/MvcViewController/handler/testModelAndView">测试ModelAndView给响应页面带入数据</a>
  </br>
  <a href="/MvcViewController/handler/testModelMap">测试ModelMap给响应页面带入数据</a>
  </br>
  <a href="/MvcViewController/handler/testMap">测试Map给响应页面带入数据</a>
  </br>
  <a href="/MvcViewController/handler/testModel">测试Model给响应页面带入数据</a>
  <form action="/MvcViewController/handler/testModelAttribute" method="post">
    学号：<input name="stuNo" type="hidden" value="31">
    名字：<input name="stuName" type="text">
    <input type="submit" value="修改">
  </form>
  </br>
<%-- 测试静态资源 --%>
  <a href="/handler/testMvcViewController">testMvcViewController</a>
  </br>
<%-- 测试类型转换器 --%>
  <form action="/MvcViewController/handler/testConverter" method="post">
    学号信息:<input name="studentInfo" type="text"/>
    <input type="submit" value="自定义转换器"/>
  </form>
  <form action="/MvcViewController/handler/testDateTimeFormat" method="post">
    学号:<input name="stuNo" type="text"/>
    姓名:<input name="stuName" type="text"/>
    年龄:<input name="stuAge" type="text"/>
    出生日期:<input name="stuBirthDate" type="text"/>
    邮箱:<input name="Email" type="text"/>
    <input type="submit" value="查询"/>
  </form>
  <br/>
  <input type="button" value="testJson" id="testJson">
  <br/>
  <br/>
  <form action="/MvcViewController/handler/testUploadFile" method="post" enctype="multipart/form-data">
    <input name="file" type="file"/>
    描述:<input name="describe" type="text"/>
    <input type="submit" value="上传"/>
  </form>
  <br/>
  <%-- 测试拦截器 --%>
  <a href="/MvcViewController/handler/testInterceptor">测试自定义拦截器</a>
  </br>
  </br>
  <a href="/MvcViewController/second/testExceptionHandler">测试springmvc异常捕获</a>
  </br>
  </br>
  <a href="/MvcViewController/second/testExceptionHandler2">测试springmvc多个异常捕获</a>
  </br>
  </br>
  <a href="/MvcViewController/handler/testException">测试springmvc总异常捕获</a>
  </br>
  </br>
  </body>
</html>
