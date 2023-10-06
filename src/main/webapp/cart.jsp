<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.webshop.ui.CartItemInfo" %><%--
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

    <form action="controller-servlet" method="post">
        <input type="hidden" name="action" value="placeOrder">
        <input type="submit" value="Place Order">
    </form>


    <%
        ArrayList<CartItemInfo> cartList = (ArrayList<CartItemInfo>) request.getSession().getAttribute("cartList");
        for(CartItemInfo item : cartList){
    %>
        <p>
            <%= item.getItem().getName() %> <%= item.getItem().getPrice() %> <%= item.getItem().getDescription() %> <%= item.getQuantity()%> <%= item.getItem().getCategory()%><%= item.getItem().getStatus() %>
        </p>
    <%
        }
    %>


</head>
<body>

</body>
</html>
