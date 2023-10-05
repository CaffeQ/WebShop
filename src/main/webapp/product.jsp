<%--
  Created by IntelliJ IDEA.
  User: timjohansson
  Date: 2023-10-02
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>

<body>
<%@ page import="com.example.webshop.bo.handler.ItemHandler" %>
<%@ page import="com.example.webshop.ui.ItemInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.example.webshop.ui.UserInfo" %>

<h1>Products</h1>
<h1>Add Items to Cart</h1>

<a href="controller-servlet?action=item">Add items</a>
<%
    Collection<ItemInfo> items = (Collection<ItemInfo>) request.getSession().getAttribute("itemInfo");
    for(ItemInfo item : items){
%>
<form action="controller-servlet" method="post">
    <p>
    <%= item.getName() %> <%= item.getPrice() %> <%= item.getDescription() %> <%= item.getQuantity()%> <%= item.getStatus() %>
    <label for="quantity"> Quantity:</label>
        <input type="hidden" name="action" value="addItemToCart">
        <input type="hidden" name="cartItemName" value=<%=item.getName()%>>
        <input type="number" id="quantity" name="cartItemQuantity" min="1" required>
        <input type="submit" value="Add to Cart">
    </p>
</form>
<form action="controller-servlet" method="get">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="editName" value=<%=item.getName()%>>
    <input type="submit" value="Edit">
</form>
<%
    }
%>

</body>
</html>