<%@ page import="com.tradeblancco.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Prateek
  Date: 24-08-2022
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Buyer Dashboard</title>
</head>
<body>

<div>
    <h1>Dashboard</h1>
    <p>Welcome <%= ((User)request.getSession(false).getAttribute("userinfo")).getFullname() %></p>
</div>

</body>
</html>
