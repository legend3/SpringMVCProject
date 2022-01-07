<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>success</title>
</head>
<body>
    <%--  中文就调i18n_zh_CN.properties
          英文就调i18n_en_US.properties
      --%>
    <fmt:message key="resource.welcome"></fmt:message>
    <fmt:message key="resource.exists"></fmt:message>
    <br/>
    welcome to springMVC ==== request:
    <br/>
    ${requestScope.studentModelAndView.stuNo} - ${requestScope.studentModelAndView.stuName}
    <br/>
    ${requestScope.studentModelMap.stuNo} - ${requestScope.studentModelMap.stuName}
    <br/>
    ${requestScope.studentMap.stuNo} - ${requestScope.studentMap.stuName}
    <br/>
    ${requestScope.studentModel.stuNo} - ${requestScope.studentModel.stuName}
    ==== session:<br/>
    ${sessionScope.studentModelAndView.stuNo} - ${sessionScope.studentModelAndView.stuName}
    <br/>
    ${sessionScope.studentModelMap.stuNo} - ${sessionScope.studentModelMap.stuName}
    <br/>
    ${sessionScope.studentMap.stuNo} - ${sessionScope.studentMap.stuName}
    <br/>
    ${sessionScope.studentModel.stuNo} - ${sessionScope.studentModel.stuName}
</body>
</html>
