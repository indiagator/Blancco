<%@ page import="com.tradeblancco.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Prateek
  Date: 24-08-2022
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Profile</title>
</head>
<body>


<h1>Please Update Your Profile</h1>

<div>
    <form action="UpdateProfileServlet" method="post">

        <label for="fullname">USERNAME:</label>
        <input type="text" id="fullname" name="fullname" ><br>

        <label for="phonenumber">PASSWORD:</label>
        <input type="text" id="phonenumber" name="phonenumber" ><br><br>

        <label for="type">PASSWORD:</label>
        <input type="text" id="type" name="type" ><br><br>

        <input type="submit" value="SAVE">

    </form>



</div>

<div>
    <form action="LogoutServlet" method="get">
        <input type="submit" value="LOGOUT">
    </form>
</div>



</body>
</html>
