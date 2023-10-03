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
    <h1>Add items</h1>

    <div>
        <label for="name">Item name</label>
        <input type="name" id="name" name="name">
    </div>
    <div>
        <label for="description">Desc    </label>
        <input type="description" id="description" name="description">
    </div>
    <div>
        <label for="quantity">Quantity#</label>
        <input type="quantity" id="quantity" name="quantity">
    </div>
    <div>
        <input type ="submit" value="submit">
        <input type="hidden" name="action" value="processAdd">
    </div>

</head>
<body>

</body>
</html>
