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
    <title>Seller Dashboard</title>
</head>
<body>

<%  boolean authorized =  ((User)request.getSession(false).getAttribute("userinfo")).getType().equals("seller");%>


<div>
    <h1>Dashboard</h1>
    <p>Welcome <%= ((User)request.getSession(false).getAttribute("userinfo")).getFullname() %><span><%= authorized%></span></p>
</div>

<div>
    <form action="LogoutServlet" method="get">
        <input type="submit" value="LOGOUT">
    </form>
</div>

</body>
</html>
