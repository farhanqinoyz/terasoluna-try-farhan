<%--
  Created by IntelliJ IDEA.
  User: arcti
  Date: 03/12/2025
  Time: 09:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <h2>Current Stock Menu</h2>

        <c:if test="${not empty stockMenuDtoList}">
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Available Quantity</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${stockMenuDtoList}" var="stock">
                    <tr>
                        <td>${stock.nama}</td>
                        <td>${stock.harga}</td>
                        <td>${stock.jumlah}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty stockMenuDtoList}">
            <p>Stock kosong</p>
        </c:if>
    </table>
</body>
</html>
