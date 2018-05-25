<%@ page import="com.po.lxrUsers" %><%--
  Created by IntelliJ IDEA.
  User: shang
  Date: 2018/5/18
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h2>你好
        <c:if test="${not empty lu.truename}">
            ${lu.truename}
        </c:if>
        <c:if test="${empty lu.truename}">
            ${lu.uname}
        </c:if>
        ,欢迎使用超超通信录!</h2>
    <a href="/lxr/searchAll.do">搜所全部联系人</a><br>
    按联系人关键字搜索
    <form action="/lxr/search.do" method="get">
        联系人关键字：<input type="text" value="关键字" name="keyword"/><br>
        <input type="submit" value="搜索"/>
    </form>
</center>

</body>
</html>
