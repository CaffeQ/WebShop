<%@ page import="com.example.webshop.ui.OrderInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webshop.ui.CartItemInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: timjohansson
  Date: 2023-10-04
  Time: 08:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
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

        .order {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }

        .item-info {
            margin-bottom: 5px;
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

<div class="container">
    <h1>Orders</h1>
    <a href="controller-servlet?action=welcome">Welcome</a>
    <a href="controller-servlet?action=login">Login</a>
    <a href="controller-servlet?action=cart">Cart</a>
    <a href="controller-servlet?action=product">Product</a>
    <a href="controller-servlet?action=order">Order</a>
    <a href="controller-servlet?action=user">Users</a>

    <h2 class="heading">Overview</h2>


    <form action="controller-servlet" method="get">
        <input type="hidden" name="action" value="order">
        <select name="orderFilter" onchange="this.form.submit()">
            <option disabled selected value>Filter</option>
            <option value="all">All Statuses</option>
            <option value="active">Active</option>
            <option value="sent">Sent</option>
            <option value="cancelled">Cancelled</option>
        </select>
    </form>


    <%
        ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>) request.getSession().getAttribute("order");
        for(OrderInfo order : orders){
    %>
    <div class="order">
        <form action="controller-servlet" method="post">
            <p>
                <strong>Order ID:</strong> <%= order.getOrderID() %>,
                <strong>User ID:</strong> <%= order.getUserID() %>,
                <strong>Date:</strong> <%= order.getDate() %>,
                <strong>Status:</strong> <%= order.getStatus() %>
                <input type="hidden" name="action" value="sendOrder">
                <input type="hidden" name="sendOrderID" value=<%= order.getOrderID()%>>
                <% if ("active".equals(order.getStatus())) { %>
                <input type="submit" class="btn-send-order" value="Send">
                <% } %>
            </p>
        </form>

        <% for (CartItemInfo item : order.getItems()){%>
        <div class="item-info">
            <p>
                <strong>Name:</strong> <%= item.getItem().getName() %>,
                <strong>Price:</strong> <%= item.getItem().getPrice() %>,
                <strong>Description:</strong> <%= item.getItem().getDescription() %>,
                <strong>Category:</strong> <%= item.getItem().getCategory() %>,
                <strong>Quantity:</strong> <%= item.getQuantity() %>
            </p>
        </div>
        <%
            }
        %>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
