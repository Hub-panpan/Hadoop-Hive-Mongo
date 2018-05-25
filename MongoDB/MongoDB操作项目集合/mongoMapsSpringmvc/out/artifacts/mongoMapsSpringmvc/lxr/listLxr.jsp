<%@ page import="com.po.lxrUsers" %><%--
  Created by IntelliJ IDEA.
  User: shang
  Date: 2018/5/11
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<c:if test="${pd==1}">
&lt;%&ndash;    <c:redirect url="/listLxr.do">
        <c:param name="page" value="1"/>
    </c:redirect>&ndash;%&gt;
    <jsp:forward page="/listLxr.do?page=1"/>
</c:if>--%>
<center>
    <p1>你好，${lu.truename}！</p1>
<%--    <c:if test="${lu.truename==null}">
        null
    </c:if>
    <c:if test="${lu.truename!=null}">

    </c:if>

    <%
        lxrUsers lu = (lxrUsers)session.getAttribute("lu");
    %>
    <%=lu.getTruename()%>--%>

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
                                <tr>
                                    <td>${tl.tel}</td>
                                    <td>${tl.addtime}</td>
                                </tr>
                            </c:forEach>
                        </tr>
<%--                    <tr>
                        <td>${st.stuname}</td>
                        <td>${st.age}</td>
                        <td>${st.email}</td>
                        <td>${st.java}</td>
                        <td>${st.hadoop}</td>
                        <td>${st.hive}</td>
                        <td>${st.city}</td>
                        <td>

                        </td>
                    </tr>--%>
                    </c:forEach>
                </c:forEach>


                <tr>
                    <td colspan="8">
                        <a href="listLxr.do?page=1">首页</a>
                        <c:if test="${!(page==1)}">
                            <a href="listLxr.do?page=${page-1 }">上一页</a>
                        </c:if>
                            <%--计算begin和end --%>
                        <c:choose>
                            <%--如果总页数不足10，那么就把所有的页都显示出来 --%>
                            <c:when test="${pageCount<=10}">
                                <c:set var="begin" value="1" />
                                <c:set var="end" value="${pageCount}" />
                            </c:when>
                            <c:otherwise>
                                <%--如果总页数大于10，通过公式计算出begin和end --%>
                                <c:set var="begin" value="${page}" />
                                <c:set var="end" value="${page+9}" />
                                <c:set var="bz" value="1"></c:set>
                                <%--头溢出 --%>
                                <c:if test="${begin<1}">
                                    <c:set var="begin" value="1"></c:set>
                                    <c:set var="end" value="10"></c:set>
                                </c:if>
                                <%--尾溢出 --%>
                                <c:if test="${end>pageCount}">
                                    <c:set var="begin" value="${pageCount - 9}"></c:set>
                                    <c:set var="end" value="${pageCount}"></c:set>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="p" begin="${begin}" end="${end}">
                            <a href="listLxr.do?page=${p}">第${p}页</a>
                        </c:forEach>


                        <c:if test="${!(page==pageCount)}">
                            <a href="listLxr.do?page=${page+1 }">下一页</a>
                        </c:if>
                        <a href="listLxr.do?page=${pageCount}">尾页</a>
                        <a href="#">共${pageCount}页</a>
                    </td>
                </tr>

            </c:otherwise>
        </c:choose>
<%--            ${page}
            ${pageCount}--%>

    </table>
</center>

</body>
</html>
