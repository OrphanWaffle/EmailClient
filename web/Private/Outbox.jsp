<%@ page import="java.util.List" %>
<%@ page import="com.emailclient.classes.InboxMail" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Outbox</title>
</head>
<% List<InboxMail> mail =(List<InboxMail>)request.getAttribute("outMail"); %>
<body>

<jsp:include page="/BaraNavigare.jsp"></jsp:include>
<table>
    <%
        for(InboxMail m : mail)
        {
    %>
    <tr>
        <td><%= m.getData()%></td>
        <td><%= m.getSubiect()%></td>
        <td> <form method="post" action="AccessMailOut">
            <input type="hidden" name="id_mail" value="<%= m.getId_mail()%>">
            <input type="submit" value="View Mail" name="viewname"> </form> </td>
        <td><form method="post" action="DeleteOutbox">
            <input type="hidden" name="id_mail" value="<%= m.getId_mail()%>">
            <input type="submit" value="Delete" name="decline"></form></td>

    </tr>
    <% } %>
</table>


</body>
</html>
