<%@ page import="com.example.webshop.ui.OrderInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webshop.ui.ItemInfo" %>
<%@ page import="com.example.webshop.bo.CartItem" %><%--
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
    <h1>Order</h1>
</head>
<body>
<%
    ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>) request.getSession().getAttribute("order");
    for(OrderInfo order : orders){
%>
<p>
    <form action="controller-servlet" method="post">
        <%= order.getOrderID() %> <%= order.getUserID() %> <%= order.getDate() %> <%= order.getStatus()%>
        <input type="hidden" name="action" value="sendOrder">
        <input type="hidden" name="sendOrderID" value=<%=order.getOrderID()%>>
        <input type="submit" value="Send">
    </form>
    <% for (CartItem<ItemInfo> item : order.getItems()){%>
        <p>
        <%= item.getItem().getName() %> <%= item.getItem().getPrice() %> <%= item.getItem().getDescription() %><%= item.getItem().getCategory() %>  <%= item.getQuantity()%>
        </p>
    <%
    }
    %>
    </p>
<%
    }
%>
</body>
</html>
