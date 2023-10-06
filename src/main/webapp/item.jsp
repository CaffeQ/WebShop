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
    <title>Add item page</title>
</head>
<body>
<h1>Add items</h1>
<form action ="controller-servlet" method="post">
    <div>
        <label for="name">Item name</label>
        <input type="text" id="name" name="name">
    </div>
    <div>
        <label for="price">Price $</label>
        <input type="text" id="price" name="price">
    </div>
    <div>
        <label for="description">Description</label>
        <input type="text" id="description" name="description">
    </div>
    <div>
        <label for="quantity">Quantity#</label>
        <input type="text" id="quantity" name="quantity">
    </div>
    <div>
        <label for="category">Category</label>
        <input type="text" id="category" name="category">
    </div>
    <div>
        <label>Status:</label><br>
        <input type="radio" id="in_stock" name="status" value="in_stock">
        <label for="in_stock">In stock</label><br>
        <input type="radio" id="out_of_stock" name="status" value="out_of_stock">
        <label for="out_of_stock">Out of stock</label>
    </div>
    <div>
        <input type ="submit" value="submit">
        <input type="hidden" name="action" value="processAdd">
    </div>
</form>
<% if (request.getAttribute("errorMessage") != null) { %>
<div style="color: red;">
    <%= request.getAttribute("errorMessage") %>
</div>
<% } %>
<a href="controller-servlet?action=welcome">Welcome</a>
</body>
</html>
