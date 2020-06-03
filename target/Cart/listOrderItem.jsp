<%--
  Created by IntelliJ IDEA.
  User: Shen Shilei
  Date: 2020/6/2
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>查看购物车</title>
</head>
<body>
    <table align="center" border="1">
        <tr>
            <td>商品名称</td>
            <td>单价</td>
            <td>数量</td>
            <td>小计</td>
            <td>删除</td>
        </tr>

        <c:forEach var="oi" items="${ois}" varStatus="st">
            <tr>
                <td>${oi.product.name}</td>
                <td>${oi.product.price}</td>
                <td>${oi.num}</td>
                <td>${oi.product.price * oi.num}</td>
                <td>
                    <form action="deleteOrderItem" method="post">
                        <input type="hidden" value="${oi.product.id}" name="pid">
                        <input type="submit" value="删除">
                    </form>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td><a href="createOrder" style="text-decoration: none;">生成订单</a></td>
        </tr>
    </table>
</body>
</html>
