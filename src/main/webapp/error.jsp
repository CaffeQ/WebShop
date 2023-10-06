<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 15px;
            color: #333;
        }

        .container {
            max-width: 300px;
            margin: 0 auto;
        }

        h1 {
            margin-bottom: 10px;
        }

        p {
            margin-bottom: 15px;
        }

        .error-message {
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Error Page</h1>
    <p>An error occurred. Please try again later.</p>
    <p>Error message: <span class="error-message"><%= request.getAttribute("errorMessage") %></span></p>
</div>
</body>
</html>
