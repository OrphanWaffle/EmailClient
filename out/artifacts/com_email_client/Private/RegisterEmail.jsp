<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Email</title>
</head>
<body>
    <% String mesaj =(String)request.getAttribute("mesaj"); %>

    <jsp:include page="/BaraNavigare.jsp"></jsp:include>
<form method="post" action="AddEmail">

    <input type="text" name="Adresa" placeholder="Adresa"> <br>

    <input type="password" name="Parola" placeholder="Parola"> <br>

    <input type="submit" value="Register" name="Register"> <br>

    <br>
    <p> <%= mesaj==null?"":mesaj %> </p>
</form>
</body>
</html>
