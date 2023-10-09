<%@ page import="com.example.webshop.ui.UserInfo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>WebShop</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #333;
        }
        a {
            text-decoration: none;
            margin-right: 10px;
            color: #007BFF;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%
    UserInfo userInfo = (UserInfo) session.getAttribute("user");
    String name = "";
    String role = " Customer";
    if(userInfo != null) {
        name = userInfo.getEmail();
        if(userInfo.getRole().equals("warehouse_staff"))
            role = " Warehouse staff";
        else if(userInfo.getRole().equals("admin"))
            role = "n Admin";
    }
%>
<h1><%= "Welcome " + name + " to the Silk Road!" %></h1>
<h1><%= "You are a" + role + "!" %></h1>
<br/>
<a href="controller-servlet?action=welcome">Welcome</a>
<a href="controller-servlet?action=login">Login</a>
<a href="controller-servlet?action=sign_in">Sign in</a>
<a href="controller-servlet?action=cart">Cart</a>
<a href="controller-servlet?action=product">Product</a>
<a href="controller-servlet?action=order">Order</a>
<a href="controller-servlet?action=user">Users</a>
</body>
</html>
