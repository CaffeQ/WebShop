<%@ page import="com.example.webshop.ui.ItemInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Item Page</title>
</head>
<body>
<h1>Edit Item</h1>
<%
    ItemInfo item = (ItemInfo) request.getSession().getAttribute("item");
%>
<form action="controller-servlet" method="post">
    <div>
        <input type="hidden" name="previousName" value="<%=item.getName()%>">
    </div>
    <div>
        <label for="name">Edit Item Name</label>
        <input type="text" id="name" name="name" value="<%=item.getName()%>">
    </div>
    <div>
        <label for="price">Edit Price $</label>
        <input type="text" id="price" name="price" value="<%=item.getPrice()%>">
    </div>
    <div>
        <label for="description">Edit Description</label>
        <input type="text" id="description" name="description" value="<%=item.getDescription()%>">
    </div>
    <div>
        <label for="quantity">Edit Quantity#</label>
        <input type="text" id="quantity" name="quantity" value="<%=item.getQuantity()%>">
    </div>
    <div>
        <label for="category">Edit Quantity#</label>
        <input type="text" id="category" name="category" value="<%=item.getCategory()%>">
    </div>
    <div>
        <label>Edit Status:</label><br>
        <input type="radio" id="in_stock" name="status" value="<%=item.getStatus()%>" checked>
        <label for="in_stock">In stock</label><br>
        <input type="radio" id="out_of_stock" name="status" value="out_of_stock">
        <label for="out_of_stock">Out of stock</label>
    </div>
    <div>
        <input type="submit" value="Submit Edit">
        <input type="hidden" name="action" value="processEdit">
    </div>
</form>
<a href="controller-servlet?action=welcome">Welcome</a>
</body>
</html>
