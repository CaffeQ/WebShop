<%@ page import="com.example.webshop.ui.UserInfo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>WebShop</title>
</head>
<body>
<h1>
  <%  UserInfo userInfo = (UserInfo) session.getAttribute("user");
      String name = "";
      if(userInfo!=null)
        name = userInfo.getName();
  %>
</h1>
<h1><%= "Welcome "+ name + " to the silk road!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="controller-servlet?action=login">Login</a>
<a href="controller-servlet?action=cart">Cart</a>
<a href="controller-servlet?action=product">Product</a>
</body>
</html>