<%--
  Created by IntelliJ IDEA.
  User: Shen Shilei
  Date: 2020/6/1
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<html>
<head>
    <title>购物车</title>
    <script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
</head>
<body>
    <c:if test="${!empty user}">
        <div align="center">
            当前用户：${user.name}
        </div>
    </c:if>
    <table align="center" border="1" style="margin-top: 240px">
        <tr>
            <td>id</td>
            <td>名称</td>
            <td>价格</td>
            <td>购买</td>
        </tr>
        <c:forEach items="${products}" var="product" varStatus="st">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>
                    <form action="addOrderItem" method="post">
                        数量<input type="text" name="num" value="1">
                        <input type="hidden" name="pid" id="pid" value="${product.id}">
                        <input type="submit" value="购买">
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <form action="listOrderItem" method="post">
                    <input type="submit" value="查看购物车" align="center">
                </form>
            </td>
        </tr>
    </table>

</body>
</html>

