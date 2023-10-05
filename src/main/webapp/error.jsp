<%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 2023-10-05
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<h1>Error Page</h1>
<p>An error occurred. Please try again later.</p>
<p>Error message: <%= request.getAttribute("errorMessage") %></p>
</body>
</html>
