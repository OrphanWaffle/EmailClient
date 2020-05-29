<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body background="BackRicardo.png">
     <% String mesaj =(String)request.getAttribute("mesaj"); %>
<form method="post" action="Login">

    <input type="text" name="Username" placeholder="Username"> <br>

    <input type="password" name="Parola" placeholder="Parola"> <br>

    <input type="submit" value="Login" name="Login"> <br>

    <p style="color: #f6f6f6"> <%= mesaj==null?"":mesaj %> </p>

    <br>
    <a href="Register.jsp" >Go To Register </a>
</form>
</body>
</html>
