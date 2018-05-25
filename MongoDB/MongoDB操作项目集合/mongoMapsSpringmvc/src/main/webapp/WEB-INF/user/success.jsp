<%--
  Created by IntelliJ IDEA.
  User: shang
  Date: 2018/5/7
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>.
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<center>
    <table border="1" cellpadding="0" cellspacing="0" width="50%">
        <caption>学生信息显示</caption>
        <tr>
            <th>姓名</th>
            <th>年龄</th>
            <th>email</th>
            <th>java成绩</th>
            <th>hadoop成绩</th>
            <th>hive成绩</th>
            <th>所在城市</th>
            <th>所拥有的书</th>
        </tr>

        <c:choose>
            <c:when test="${empty ulist}">
                <tr>
                    <td colspan="8">没有学生信息</td>
                </tr>
            </c:when>

            <c:otherwise>
                <c:forEach items="${ulist}" var="st" varStatus="index">
                    <tr>
                        <td>${st.stuname}</td>
                        <td>${st.age}</td>
                        <td>${st.email}</td>
                        <td>${st.java}</td>
                        <td>${st.hadoop}</td>
                        <td>${st.hive}</td>
                        <td>${st.city}</td>
                        <td>
                            <c:forEach items="${st.books}" var="arr">
                                ${arr}
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>


                <tr>
                    <td colspan="8">
                        <a href="ps.do?page=1">首页</a>
                        <c:if test="${!(page==1)}">
                            <a href="ps.do?page=${page-1 }">上一页</a>
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
                            <a href="ps.do?page=${p}">第${p}页</a>
                        </c:forEach>


                        <c:if test="${!(page==pageCount)}">
                            <a href="ps.do?page=${page+1 }">下一页</a>
                        </c:if>
                        <a href="ps.do?page=${pageCount}">尾页</a>
                        <a href="#">共${pageCount}页</a>
                    </td>
                </tr>

            </c:otherwise>
        </c:choose>


    </table>
</center>
</body>
</html>
