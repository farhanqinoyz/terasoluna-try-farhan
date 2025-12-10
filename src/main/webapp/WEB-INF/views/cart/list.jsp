<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script type="text/javascript">
      const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/resources/app/js/toastUtils.js"></script>
    <script src="${pageContext.request.contextPath}/resources/app/js/payment.js"></script>
</head>
<body>
<table>
    <h2>Current Stock Menu</h2>

    <c:if test="${not empty cartList}">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cartList}" var="cart">
                <tr>
                    <td>${cart.itemName}</td>
                    <td>${cart.price}</td>
                    <td>${cart.quantity}</td>
                    <td>${cart.subTotal}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty cartList}">
        <p>Stock kosong</p>
    </c:if>
    <button onclick="pay()" ${empty cartList ? 'disabled' : ''}>
        Pay
    </button>
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
</table>
</body>
</html>
