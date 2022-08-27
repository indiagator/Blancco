<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<head>
    <title>JSP - Hello World</title>

    <script type="text/javascript" src="scripts/landingpage.js"></script>

</head>

<body onload="init()">

<%
    String errorMessage = (String)request.getAttribute("errormessage");
     if(errorMessage == null)
     {
         errorMessage = "";
     }

     String errorMessage2 = (String)request.getAttribute("errormessage2");
    if(errorMessage2 == null)
    {
        errorMessage2 = "";
    }
%>

<h1>Welcome to Trade Blancco
</h1>

<h2>The World Trades Here</h2>
<br/>

<div>
    <form action="LoginServlet" method="post">

        <label for="username">USERNAME:</label>
        <input type="text" id="username" name="username" ><br>

        <label for="password">PASSWORD:</label>
        <input type="password" id="password" name="password" ><br><br>

        <input type="submit" value="LOGIN">

    </form>

    <div><span><%= errorMessage  %></span></div>

</div>

<div>
    <form action="SignupServlet" method="post">

        <label for="usernameSignup">USERNAME:</label>
        <input type="text" id="usernameSignup" name="username" ><br>

        <label for="passwordSignup">PASSWORD:</label>
        <input type="password" id="passwordSignup" name="password" ><br><br>

        <input type="submit" value="SIGNUP">

    </form>

    <div><span><%= errorMessage2  %></span></div>

</div>


</body>
</html>