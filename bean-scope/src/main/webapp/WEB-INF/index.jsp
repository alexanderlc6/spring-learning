<%--
  Created by IntelliJ IDEA.
  User: AlexLc
  Date: 2020/4/9
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
                    pageEncoding="UTF-8" />
<html>
<head>
    <link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css"/>
</head>
<body>
    \${user.name} : ${user.name}
</body>
</html>
