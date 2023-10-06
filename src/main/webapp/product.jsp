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

        .product {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .product-info {
            flex: 1;
            margin-right: 20px;
        }

        .product-actions {
            display: flex;
            align-items: center;
        }

        .product-actions button {
            margin-left: 10px;
        }

        .btn {
            padding: 10px 20px;
            background-color: #62bd52;
            color: white;
            border: none;
            text-decoration: none;
            cursor: pointer;
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

    <%
        Collection<ItemInfo> items = (Collection<ItemInfo>) request.getSession().getAttribute("itemInfo");
        for (ItemInfo item : items) {
    %>
    <div class="product">
        <div class="product-info">
            <%= item.getName() %> - <%= item.getDescription() %> - Price: <%= item.getPrice() %> - Quantity: <%= item.getQuantity() %> - Category: <%= item.getCategory() %> - Status: <%= item.getStatus() %>
        </div>
        <div class="product-actions">
            <form action="controller-servlet" method="post">
                <label for="quantity">Quantity:</label>
                <input type="hidden" name="action" value="addItemToCart">
                <input type="hidden" name="cartItemName" value="<%= item.getName() %>">
                <input type="number" id="quantity" name="cartItemQuantity" min="1" required>
                <button type="submit" class="btn">Add to Cart</button>
            </form>

            <form action="controller-servlet" method="get">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="editName" value="<%= item.getName() %>">
                <a href="controller-servlet?action=edit&editName=<%= item.getName() %>" class="btn">Edit</a>
            </form>

            <form action="controller-servlet" method="post">
                <input type="hidden" name="action" value="removeItem">
                <input type="hidden" name="removeItem" value="<%= item.getName() %>">
                <button type="submit" class="btn">Remove</button>
            </form>
        </div>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
