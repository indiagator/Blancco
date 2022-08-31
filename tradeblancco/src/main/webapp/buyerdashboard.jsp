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

    <script type="text/javascript" src="scripts/buyerdashboard.js"></script>

</head>
<body onload="init()">

<%  boolean authorized =  ((User)request.getSession(false).getAttribute("userinfo")).getType().equals("buyer");
    String username = ((User) request.getSession(false).getAttribute("userinfo")).getUsername();

%>


<div>
    <h1>Dashboard</h1>
    <p>Welcome <%= ((User)request.getSession(false).getAttribute("userinfo")).getFullname() %><span><%= authorized%></span></p>
</div>

<div>
    <form action="LogoutServlet" method="get">
        <input type="submit" value="LOGOUT">
    </form>
</div>

<div>
    <form action="http://localhost:8082/api/0.1/test-service-3/saveProduct" method="post">

        <input type="text" id="username" name="username" value="<%= username%>" style="display: none"><br>

        <label for="hscode">H S Code:</label>
        <input type="text" id="hscode" name="hscode" ><br>

        <label for="productname">Product Name:</label>
        <input type="text" id="productname" name="productname" ><br><br>

        <label for="unit">Unit:</label>
        <input type="text" id="unit" name="unit" ><br><br>

        <input type="submit" value="SAVE">

    </form>



</div>

</body>
</html>
