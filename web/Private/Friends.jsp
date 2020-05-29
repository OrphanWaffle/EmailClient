<%@ page import="java.util.List" %>
<%@ page import="com.emailclient.classes.HelloFriend" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Friends</title>
</head>
<% List<HelloFriend> Request =(List<HelloFriend>)request.getAttribute("Request");
    Request = Request==null?new ArrayList<>():Request;
   List<HelloFriend> Friend =(List<HelloFriend>)request.getAttribute("Friend");
    Friend = Friend==null?new ArrayList<>():Friend;%>

<body>
<jsp:include page="/BaraNavigare.jsp"></jsp:include>

<form method="post" action="<%=request.getContextPath()%>/Private/AddFriend">
    <input type="text" name="Sendto" placeholder="Add Friend:" size="50">

    <input type="submit" value="Add Friend" name="Friends">

</form> <br>
<% String mesaj =(String)request.getAttribute("mesaj"); %>
<p> <%= mesaj==null?"":mesaj %> </p>
<span> Friend Requests: </span> <br>
<table>
    <%
        for(HelloFriend re : Request)
        {
    %>
    <tr>
        <td><%= re.getUsername()%></td>
        <td> || <%= re.getNume()%></td>
        <td> || <%= re.getPrenume()%></td>
        <td> || <%= re.getData()%></td>
        <td><form method="post" action="<%=request.getContextPath()%>/Private/Accept">
            <input type="hidden" name="User" value="<%= re.getUsername()%>">
            <input type="submit" value="Accept" name="accept"></form></td>
        <td><form method="post" action="<%=request.getContextPath()%>/Private/Decline">
            <input type="hidden" name="User" value="<%= re.getUsername()%>">
            <input type="submit" value="Decline" name="decline"></form></td>
    </tr>
    <% } %>
</table> <br>
<span> Friends: </span> <br>
<table>
    <%
        for(HelloFriend fr : Friend)
        {
    %>
    <tr>
        <td><%= fr.getUsername()%></td>
        <td> || <%= fr.getNume()%></td>
        <td> || <%= fr.getPrenume()%></td>
        <td><form method="post" action="<%=request.getContextPath()%>/Private/Delete">
            <input type="hidden" name="User" value="<%= fr.getUsername()%>">
            <input type="submit" value="Delete" name="decline"></form></td>
    </tr>
    <% } %>
</table>

</body>
</html>
