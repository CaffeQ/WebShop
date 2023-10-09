<%@ page import="com.example.webshop.ui.UserInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 2023-10-09
  Time: 08:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <div class="container">
        <title>Users</title>
        <%
            ArrayList<UserInfo> users = (ArrayList<UserInfo>) request.getSession().getAttribute("userInfo");
            for (UserInfo user : users) {
        %>
        <div class="product">
            <div class="product-info">
                <p>
                    <strong>Mail:</strong> <%= user.getName() %>,
                    <strong>Role:</strong> <%= user.getRole() %>,
                    <strong>Token:</strong> <%= user.getToken() %>
                </p>
            </div>

        </div>
    </div>
    <%
        }
    %>
</head>
<body>

</body>
</html>
