<%--
  Created by IntelliJ IDEA.
  User: arcti
  Date: 23/11/2025
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Stock</title>
</head>
<body>
  <div>
      <form:form action="${pageContext.request.contextPath}/stock/excel/upload/"
                 method="post"
                 enctype="multipart/form-data">
          <input type="file" name="excelFile"/>
          <button type="submit">Upload File</button>

      </form:form>
      <form:form action="${pageContext.request.contextPath}/stock/excel/export/"
                 method="get">
          <button type="submit">Import File</button>
      </form:form>
  </div>
</body>
</html>
