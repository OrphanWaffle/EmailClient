<%@ page import="java.util.Map" %>
<span><%= (String)session.getAttribute("Username") %> || </span>

<select name="forma" onchange="location = this.value;">
    <%  Map<String,Integer> emails = ((Map<String,Integer>)session.getAttribute("ListaEmail"));
        String selectedEmail = (String)session.getAttribute("Adresa");
        for(Map.Entry<String,Integer> it : emails.entrySet()) { %>
            <option <%=selectedEmail.equals(it.getKey())?"selected":""%> value="<%=request.getContextPath() + "/Private/SelectEmail?id_email=" + it.getValue()%>"><%=it.getKey()%></option>
    <% }
        if(emails.isEmpty()) { %>
    <option value = "" selected disabled>Introdu o adresa de email</option> <% } %>
</select>

<a href="RegisterEmail.jsp">Add Email</a> <span> || </span>

<a href="Inbox">Inbox</a> <span> || </span>

<a href="Outbox">Outbox</a> <span> || </span>

<a href="<%=request.getContextPath()%>/Private/ComposeRedirect">Compose</a> <span> || </span>

<a href="Friends">Friends</a> <span> || </span>

<a href="Rapoarte">Rapoarte</a> <span> || </span>

<a href="<%= request.getContextPath() + "/Logout" %>">Log Out</a>

<br> <br>


