<%@ page import="com.example.webshop.ui.ItemInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Item Page</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
            color: #333;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .container {
            max-width: 300px;
            padding: 15px;
            box-sizing: border-box;
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
    <h1>Edit Item</h1>
    <a href="controller-servlet?action=welcome">Welcome</a>
    <a href="controller-servlet?action=login">Login</a>
    <a href="controller-servlet?action=cart">Cart</a>
    <a href="controller-servlet?action=product">Product</a>
    <a href="controller-servlet?action=order">Order</a>
    <%
        ItemInfo item = (ItemInfo) request.getSession().getAttribute("item");
    %>
    <form action="controller-servlet" method="post">
        <div class="form-group">
            <input type="hidden" name="previousName" value="<%=item.getName()%>">
        </div>
        <div class="form-group">
            <label for="name">Edit Item Name</label>
            <input type="text" id="name" name="name" value="<%=item.getName()%>">
        </div>
        <div class="form-group">
            <label for="price">Edit Price $</label>
            <input type="text" id="price" name="price" value="<%=item.getPrice()%>">
        </div>
        <div class="form-group">
            <label for="description">Edit Description</label>
            <input type="text" id="description" name="description" value="<%=item.getDescription()%>">
        </div>
        <div class="form-group">
            <label for="quantity">Edit Quantity#</label>
            <input type="text" id="quantity" name="quantity" value="<%=item.getQuantity()%>">
        </div>
        <div class="form-group">
            <label for="category">Edit category</label>
            <input type="text" id="category" name="category" value="<%=item.getCategory()%>">
        </div>
        <div class="form-group">
            <input type="radio" id="in_stock" name="status" value="<%=item.getStatus()%>" checked>
            <label for="in_stock">In stock</label><br>
            <input type="radio" id="out_of_stock" name="status" value="out_of_stock">
            <label for="out_of_stock">Out of stock</label>
        </div>
        <div class="form-group">
            <input type="submit" value="Submit Edit">
            <input type="hidden" name="action" value="editItem">
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
