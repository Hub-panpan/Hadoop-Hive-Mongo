<%--Created by IntelliJ IDEA.
  User: shang
  Date: 2018/5/8
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
<table border="1" cellpadding="0" cellspacing="0" width="50%">
<caption>联系人信息显示</caption>
<tr>
    <th>联系人名</th>
    <th>联系方式</th>
    <th>添加时间</th>
</tr>

<c:choose>
    <c:when test="${empty ulist}">
        <tr>
            <td colspan="8">没有联系人信息</td>
        </tr>
    </c:when>
    <c:otherwise>
        <c:forEach items="${ulist}" var="st" varStatus="index">
            <c:forEach items="${st.lxrinformation}" var="li">
                <tr>
                <td>${li.lxrname}</td>
                <c:forEach items="${li.telinformation}" var="tl">
                        <td>${tl.tel}</td>
                        <td>${tl.addtime}</td>
                </c:forEach>
                </tr>
            </c:forEach>
        </c:forEach>

    </c:otherwise>
    </c:choose>
</table>
</center>
</body>
</html>
