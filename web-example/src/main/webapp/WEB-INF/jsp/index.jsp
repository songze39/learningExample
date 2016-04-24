<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="security" uri="http://rabbitframework.security.com/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%-- <img src="${pageContext.request.contextPath}/images/test.jpg"/>--%>
<security:principal/>
<br/>
test,${userName}:hello web-example!
</body>
</html>