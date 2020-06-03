<%--
  Created by IntelliJ IDEA.
  User: Shen Shilei
  Date: 2020/6/2
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>查看订单</title>
</head>
<body>
    <c:if test="${!empty user}">
        <div align="center">
            当前用户：${user.name}
        </div>
    </c:if>
    <table align="center" border="1">
        <tr>
            <td>名称</td>
            <td>单价</td>
            <td>数量</td>
            <td>合计</td>
        </tr>
        <c:forEach var="oi" items="${orderList}" varStatus="st">
            <tr>
                <td>${oi.product.name}</td>
                <td>${oi.product.price}</td>
                <td>${oi.num}</td>
                <td>${oi.product.price*oi.num}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
