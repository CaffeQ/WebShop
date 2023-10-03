<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webshop.bo.CartItem" %>
<%@ page import="com.example.webshop.ui.ItemInfo" %><%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 2023-10-02
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <h1>Cart</h1>

    <%
        ArrayList<CartItem<ItemInfo>> items = (ArrayList<CartItem<ItemInfo>>) request.getSession().getAttribute("cart");
        for(CartItem<ItemInfo> item : items){
    %>

        <p>
            <%= item.getItem().getName() %> <%= item.getItem().getPrice() %> <%= item.getItem().getDescription() %> <%= item.getQuantity()%> <%= item.getItem().getStatus() %>
        </p>
    <%
        }
    %>


</head>
<body>

</body>
</html>
