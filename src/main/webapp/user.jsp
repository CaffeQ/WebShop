<%@ page import="com.example.webshop.ui.UserInfo" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 2023-10-09
  Time: 08:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style>

        option[disabled] { display: none; }


        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            color: #333;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        .user {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }

        .btn-send-order {
            padding: 5px 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            text-decoration: none;
            cursor: pointer;
        }

        .btn-send-order:hover {
            background-color: #0056b3;
        }


    </style>
</head>
<body>

<div class="container">
    <h1>Users</h1>
    <a href="controller-servlet?action=welcome">Welcome</a>
    <a href="controller-servlet?action=login">Login</a>
    <a href="controller-servlet?action=cart">Cart</a>
    <a href="controller-servlet?action=product">Product</a>
    <a href="controller-servlet?action=order">Order</a>
    <a href="controller-servlet?action=user">Users</a>

    <h2 class="heading">Overview</h2>

    <form action="controller-servlet" method="get">
        <input type="hidden" name="action" value="user">
        <select name="userFilter" onchange="this.form.submit()">
            <option disabled selected value>Filter</option>
            <option value="all">All Users</option>
            <option value="active">Active</option>
            <option value="inactive">Inactive</option>
        </select>
    </form>

    <%
        ArrayList<UserInfo> users = (ArrayList<UserInfo>) request.getSession().getAttribute("users");
        for (UserInfo user : users){
    %>
    <div class="user">
        <p>
            <strong>Mail:</strong> <%= user.getEmail() %>,
            <strong>Role:</strong> <%= user.getRole() %>,
            <strong>Token:</strong> <%= user.getToken() %>,
        </p>

        <form action="controller-servlet" method="post">
            <input type="hidden" name="action" value="changeUserRole">
            <input type="hidden" name="changeUserRoleByEmail" value=<%= user.getEmail()%>>
            <select name="changeUserRoleToRole" onchange="this.form.submit()">
                <option disabled selected value>Role</option>
                <option value="customer">Customer</option>
                <option value="staff">Staff</option>
                <option value="admin">Admin</option>
            </select>
        </form>

        <div>
            <form action="controller-servlet" method="post">
                <input type="hidden" name="action" value="activateUser">
                <input type="hidden" name="changeUserStatusByUserName" value=<%= user.getEmail()%>>
                <input type="submit" class="btn-send-order" value="activate">
            </form>
            <form action="controller-servlet" method="post">
                <input type="hidden" name="action" value="removeUser">
                <input type="hidden" name="changeUserStatusByUserName" value=<%= user.getEmail()%>>
                <input type="submit" class="btn-send-order" value="remove">
            </form>
        </div>


    </div>

    <%
        }
    %>
</div>
</body>
</html>
