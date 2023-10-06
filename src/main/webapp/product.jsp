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

        .heading {
            color: #333;
        }

        .product-form, .edit-form {
            margin-bottom: 15px;
        }

        .product-info {
            margin-bottom: 5px;
        }

        .btn {
            padding: 10px 20px;
            background-color: #62bd52;
            color: white;
            border: none;
            text-decoration: none;
            cursor: pointer;
            display: inline-block; /* Align buttons horizontally */
            margin-right: 10px; /* Space between buttons */
            margin-bottom: 15px;
        }

        .btn:hover {
            background-color: #75b06b;
        }
    </style>
</head>

<body>
<%@ page import="com.example.webshop.ui.ItemInfo" %>
<%@ page import="java.util.Collection" %>

<div class="container">
    <h1 class="heading">Products</h1>
    <h2 class="heading">Add Items to Cart</h2>
    <a href="controller-servlet?action=item" class="btn">Add items</a>

    <%
        Collection<ItemInfo> items = (Collection<ItemInfo>) request.getSession().getAttribute("itemInfo");
        for (ItemInfo item : items) {
    %>
    <form action="controller-servlet" method="post" class="product-form">
        <div class="product-info">
            <%= item.getName() %> - <%= item.getDescription() %> - Price: <%= item.getPrice() %> - Quantity: <%= item.getQuantity() %> - Category: <%= item.getCategory() %> - Status: <%= item.getStatus() %>
        </div>
        <label for="quantity"> Quantity:</label>
        <input type="hidden" name="action" value="addItemToCart">
        <input type="hidden" name="cartItemName" value="<%= item.getName() %>">
        <input type="number" id="quantity" name="cartItemQuantity" min="1" required>
        <input type="submit" value="Add to Cart" class="btn">
        <form action="controller-servlet" method="get" class="edit-form">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="editName" value="<%= item.getName() %>">
            <input type="submit" value="Edit" class="btn">
        </form>
    </form>
    <%
        }
    %>
</div>
</body>
</html>
