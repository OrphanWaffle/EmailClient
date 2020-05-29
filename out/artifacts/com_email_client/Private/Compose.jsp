<html>
<head>
    <title>Compose</title>
</head>
<jsp:include page="/BaraNavigare.jsp"></jsp:include>
<body>

<% String error =(String)request.getAttribute("mesaj");
    String Sendto =(String)request.getAttribute("Sendto");
    String Subiect =(String)request.getAttribute("Subiect");
    String Mesaj =(String)request.getAttribute("Mesaj");%>

<form method="post" action="Compose">

    <textarea name="Sendto" placeholder="Catre:" rows="1" cols="100"><%= Sendto==null?"":Sendto %></textarea> <br>

    <textarea name="Subiect" placeholder="Subiect:" rows="1" cols="100"><%= Subiect==null?"":Subiect %></textarea> <br>

    <textarea name="Mesaj" placeholder="Mesaj:" rows="10" cols="100"><%= Mesaj==null?"":Mesaj %></textarea> <br>

    <input type="submit" name="Submit" value="Send">

    <p> <%= error==null?"":error %> </p>

</form>
</body>
</html>
