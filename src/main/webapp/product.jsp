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
<%@ page import="com.example.webshop.bo.ItemHandler" %>
<%@ page import="com.example.webshop.ui.ItemInfo" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.io.PrintWriter" %>

<h1>Products</h1>
<h1>Add Items to Cart</h1>

<%
    Collection<ItemInfo> items = (Collection<ItemInfo>) request.getAttribute("itemInfo");
    for(ItemInfo item : items){
%>
<p>
    <%= item.getName() %> <%= item.getPrice() %> <%= item.getDescription() %> <%= item.getQuantity()%> <%= item.getStatus() %>
    <label for="quantity"> Quantity:</label>
    <input type="hidden" name="cartItemName" value=<%=item.getName()%>>
    <input type="number" id="quantity" name="cartItemQuantity" min="1" required>
    <input type="submit" value="Add to Cart">
</p>
<%
    }
%>

</body>
</html>