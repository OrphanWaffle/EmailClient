<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Rapoarte</title>
</head>
<body>
<jsp:include page="/BaraNavigare.jsp"></jsp:include>
<% String mostMailsSent = (String) request.getAttribute("rap_1");
    LocalDate day = (LocalDate)request.getAttribute("rap_2");
if(mostMailsSent.equals("")) { %>
<span>Nu ai trimis nici un mail</span> <% }
else { %>
<span>Cele mai multe mailuri au fost trimise la: <%= mostMailsSent%></span> <% } %>
<br>
<% if(day == null) { %>
<span>Nu ai activitate</span> <% }
    else { %>
<span>Cea mai activa zi a fost: <%= day%></span> <% } %> <br>
<span>ETC</span>

</body>
</html>
