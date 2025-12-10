<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <title>Title</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script type="text/javascript">
      const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/resources/app/js/toastUtils.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/js/addToCart.js"></script>
</head>
<body>
    <div>
        <button onclick="window.location.href='<c:url value="/cart/"/>'">
            See your cart
        </button>
    </div>
    <table>
        <h2>Current Stock Menu</h2>

        <c:if test="${not empty stockMenuDtoList}">
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Available Quantity</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${stockMenuDtoList}" var="stock">
                    <tr>
                        <td>${stock.nama}</td>
                        <td>${stock.harga}</td>
                        <td>${stock.jumlah}</td>
                        <td><button onclick="addToCart('${stock.codeBarang}')">Add to cart</button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty stockMenuDtoList}">
            <p>Stock kosong</p>
        </c:if>
    </table>
    <div id="toastContainer" class="toast-container position-fixed top-0 end-0 p-3">
        <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="me-auto">System Notification</strong>
                <small>Just now</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body" id="toast-message">
            </div>
        </div>
    </div>
</body>
</html>
