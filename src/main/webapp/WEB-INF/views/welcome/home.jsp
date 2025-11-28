<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div id="wrapper">
        <h1>Hello world!</h1>
        <p>The time on the server is ${serverTime}.</p>
        <form action="${pageContext.request.contextPath}/number/" method="get">
            <button type="submit">Go to Number Page</button>
        </form>
        <form action="${pageContext.request.contextPath}/stock/excel/" method="get">
            <button type="submit">Go to Upload Excel</button>
        </form>
    </div>

</body>
</html>
