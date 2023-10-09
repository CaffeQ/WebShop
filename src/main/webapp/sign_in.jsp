<%--
  Created by IntelliJ IDEA.
  User: Test
  Date: 2023-10-02
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            margin-right: 25%;
            color: #333;
        }

        .container {
            max-width: 250px;
            margin: 0 auto;
        }

        .form-group {
            margin-bottom: 10px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #62bd52;
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
    </style>
</head>

<body>
<div class="container">
    <h1>Sign in</h1>
    <form action="controller-servlet" method="post">
        <div class="form-group">
            <label for="name">User Name</label>
            <input type="text" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password">
        </div>
        <div class="form-group">
            <input type="submit" value="Submit">
            <input type="hidden" name="action" value="processSign_in">
        </div>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error-message">
            <%= request.getAttribute("errorMessage") %>
        </div>
        <% } %>
    </form>
</div>
</body>
</html>
