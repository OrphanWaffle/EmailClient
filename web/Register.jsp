<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body  background="RegRicardo.png">

    <% String mesaj =(String)request.getAttribute("mesaj"); %>

<form method="post" action="Register">

    <input type="text" name="Username" placeholder="Username"> <br>

    <input type="text" name="Nume" placeholder="Nume"> <br>

    <input type="text" name="Prenume" placeholder="Prenume"> <br>

    <input type="password" name="Parola" placeholder="Parola"> <br>

    <input type="password" name="ConfirmParola" placeholder="ConfirmParola"> <br>

    <input type="date" name="DataN"> <br>

    <input type="submit" value="Register" name="Register"> <br>

    <p style="color: #f6f6f6"> <%= mesaj==null?"":mesaj %> </p>

    <br>
    <a href="Login.jsp" >Go To Login </a>

</form>


</body>
</html>
