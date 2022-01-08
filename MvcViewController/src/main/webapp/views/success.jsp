<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>success</title>
</head>
<body>
<%--    <c:forEach items="${requestScope.errors}" var="error">--%>
<%--        ${error.getDefaultMessage()}<br/>--%>
<%--    </c:forEach>--%>

    <br/>
    welcome to springMVC
    <br/>
    ==== request:<br/>
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
