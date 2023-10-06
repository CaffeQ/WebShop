<%@ page import="com.example.webshop.ui.OrderInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webshop.bo.CartItem" %>
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
    </style>
</head>
<body>



<div class="container">
    <h1>Your Orders</h1>
    <%
        ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>) request.getSession().getAttribute("order");
        for(OrderInfo order : orders){
    %>
    <div class="order">
        <form action="controller-servlet" method="post">
            <%= order.getOrderID() %> - <%= order.getUserID() %> - <%= order.getDate() %> - <%= order.getStatus()%>
            <input type="hidden" name="action" value="sendOrder">
            <input type="hidden" name="sendOrderID" value=<%= order.getOrderID()%>>
            <input type="submit" class="btn-send-order" value="Send">
        </form>
        <% for (CartItem item : order.getItems()){%>
        <div class="item-info">
            <%= item.getItem().getName() %> <%= item.getItem().getPrice() %> <%= item.getItem().getDescription() %><%= item.getItem().getCategory() %>  <%= item.getQuantity()%>
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
