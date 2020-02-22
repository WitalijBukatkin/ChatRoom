<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h3>You is ${userId}</h3><a href="/login">Unlogin</a>
<h4>Chats:</h4>

<form method="post">
    <input name="withUserId" id="newChat" type="text" placeholder="Add new chat"/>
    <input type="submit"/>
</form>

<c:forEach items="${chats}" var="chat">
    <br/>-->
    <a href="/chats/${chat.id}?userId=${userId}">${chat.name}</a>
    <a href="#">Remove chat</a>
</c:forEach>
</body>
</html>