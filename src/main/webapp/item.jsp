<%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 2023-10-03
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
            color: #333;
        }

        .container {
            max-width: 300px;
            margin: auto;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding: 15px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="radio"] {
            width: calc(100% - 20px);
            padding: 10px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #007BFF;
            color: white;
            border: none;
            text-decoration: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
        }

        .radio-label {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Add Items</h1>
    <a href="controller-servlet?action=welcome">Welcome</a>
    <a href="controller-servlet?action=login">Login</a>
    <a href="controller-servlet?action=cart">Cart</a>
    <a href="controller-servlet?action=product">Product</a>
    <a href="controller-servlet?action=order">Order</a>
    <a href="controller-servlet?action=user">Users</a>

    <form action="controller-servlet" method="post">
        <div class="form-group">
            <label for="name">Item Name</label>
            <input type="text" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="price">Price $</label>
            <input type="text" id="price" name="price">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" id="description" name="description">
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="text" id="quantity" name="quantity">
        </div>
        <div class="form-group">
            <label for="category">Category</label>
            <input type="text" id="category" name="category">
        </div>
        <div class="form-group">
            <label>Status:</label>
            <div class="radio-label">
                <input type="radio" id="in_stock" name="status" value="in_stock">
                <label for="in_stock">In stock</label>
            </div>
            <div class="radio-label">
                <input type="radio" id="out_of_stock" name="status" value="out_of_stock">
                <label for="out_of_stock">Out of stock</label>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="createItem">
        </div>
    </form>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <div class="error-message">
        <%= request.getAttribute("errorMessage") %>
    </div>
    <% } %>
    <a href="controller-servlet?action=welcome">Welcome</a>
</div>
</body>
</html>
