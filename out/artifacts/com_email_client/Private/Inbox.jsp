<%@ page import="java.util.List" %>
<%@ page import="com.emailclient.classes.InboxMail" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .dotblue {
            height: 10px;
            width: 10px;
            background-color: #3333ff;
            border-radius: 50%;
            display: inline-block;
        }
    </style>
    <style>
        .dotwhite {
            height: 10px;
            width: 10px;
            background-color: #ffffff;
            border-radius: 50%;
            display: inline-block;
        }
    </style>
</head>

<title>Inbox</title>
</head>
<% List<InboxMail> mail =(List<InboxMail>)request.getAttribute("inMail"); %>
<body>

<jsp:include page="/BaraNavigare.jsp"></jsp:include>
    <table>
    <%
        for(InboxMail m : mail)
        {
    %>
        <tr>
            <% if(m.getViewed() == false)  { %> <td class="dotblue"></td>
            <% } else { %> <td class="dotwhite"></td> <% } %>
                <td><%= m.getData()%></td>
                <td><%= m.getAdresa()%></td>
                <td><%= m.getSubiect()%></td>

                <td> <form method="post" action="AccessMailIn">
                    <input type="hidden" name="id_mail" value="<%= m.getId_mail()%>">
                    <input type="submit" value="View Mail" name="viewname"> </form> </td>

                <td><form method="post" action="DeleteInbox">
                     <input type="hidden" name="id_mail" value="<%= m.getId_mail()%>">
                     <input type="submit" value="Delete" name="decline"></form></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
