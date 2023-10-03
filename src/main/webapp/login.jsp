<%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 2023-10-02
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <h1>Login</h1>
    <form action ="controller-servlet" method="post">
        <div>
            <label for="name" style="display: inline-block; margin-bottom: 5px;">User Name</label>
            <input type="text" id="name" name="name" style="display: inline-block;">
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password">
        </div>
        <div>
            <input type ="submit" value="submit">
            <input type="hidden" name="action" value="processLogin">
        </div>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div style="color: red;">
            <%= request.getAttribute("errorMessage") %>
        </div>
        <% } %>
    </form>
</head>

<body>

</body>
</html>
