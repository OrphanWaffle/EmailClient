<%@ page import="com.emailclient.classes.Mail" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mail</title>
</head>
<% Mail mail =(Mail)request.getAttribute("mail"); %>
<body>
<jsp:include page="/BaraNavigare.jsp"></jsp:include>
<br>

<form method="post" action="<%=request.getContextPath()%>/Private/ReplyAll" style="display:none;" name="f1" id="f1">
    <input type="hidden" name="Sendto" value="<%= mail.getSender()%>">
    <input type="hidden" name="Subiect" value="RE: <%= mail.getSubiect()%>">
</form>
<input type="submit" value="Reply" name="reply" form="f1">

<% if(mail.getReceiver().size() > 1) {
String send = mail.getReceivers((String)session.getAttribute("Adresa")); %>

<form method="post" action="<%=request.getContextPath()%>/Private/ReplyAll" style="display:none;" name="f2" id="f2">
    <input type="hidden" name="Sendto" value="<%= send%>">
    <input type="hidden" name="Subiect" value="RE: <%= mail.getSubiect()%>">
</form>
<input type="submit" value="Reply all" name="replyall" form="f2">
<% } %>

<form method="post" action="<%=request.getContextPath()%>/Private/Forward" style="display:none;" name="f3" id="f3">
    <input type="hidden" name="Subiect" value="FWD: <%= mail.getSubiect()%>">
    <input type="hidden" name="Mesaj" value="Original sender: <%= mail.getSender() + "\n" + mail.getMesaj()%>">
</form>
<input type="submit" value="Forward" name="forward" form="f3">

<% String Friends = mail.getFriends((String)session.getAttribute("Username")); %>

<form method="post" action="<%=request.getContextPath()%>/Private/ForwardFriends" style="display:none;" name="f4" id="f4">
    <input type="hidden" name="Sendto" value="<%= Friends%>">
    <input type="hidden" name="Subiect" value="FWD: <%= mail.getSubiect()%>">
    <input type="hidden" name="Mesaj" value="Original sender: <%= mail.getSender() + "\n" + mail.getMesaj()%>">
</form>
<input type="submit" value="ForwardFriends" name="forwardfriends" form="f4"> <br>

<span> From: <%= mail.getSender() %> </span> <br>
<span> To: </span>
<span> <%= mail.getReceiver().get(0)%></span>
    <% for(int i=1;i<mail.getReceiver().size();i++)
    {  %>
<span>, <%= mail.getReceiver().get(i)%></span>
    <% } %> <br>
<p> Subject: <%= mail.getSubiect() + " || " + mail.getData()%> </p>

<p><%= mail.getMesaj() %></p>

</body>
</html>
