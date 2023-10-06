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

        .cart-item {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }

        .item-info {
            margin-bottom: 5px;
        }

        .btn-place-order {
            padding: 10px 20px;
            background-color: #62bd52;
            color: white;
            border: none;
            text-decoration: none;
            cursor: pointer;
        }

        .btn-place-order:hover {
            background-color: #75b06b;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Your Cart</h1>

    <form action="controller-servlet" method="post">
        <input type="hidden" name="action" value="placeOrder">
        <input type="submit" class="btn-place-order" value="Place Order">
    </form>


    <%
        ArrayList<CartItemInfo> cartList = (ArrayList<CartItemInfo>) request.getSession().getAttribute("cartList");
        for(CartItemInfo item : cartList){
    %>
    <div class="cart-item">
        <div class="item-info">
            <%= item.getItem().getName() %> - <%= item.getItem().getDescription() %> - Price: <%= item.getItem().getPrice() %> - Quantity: <%= item.getQuantity() %> - Category: <%= item.getItem().getCategory() %> - Status: <%= item.getItem().getStatus() %>
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
