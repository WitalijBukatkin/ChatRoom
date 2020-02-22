<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h3>You is ${userId} </h3> <a href="/login">Unlogin</a>
<h4>Messages:</h4>

<c:forEach items="${messages}" var="message">
    <br/>--> ${message.senderId} ${message.data}
</c:forEach>
<br/>

<form method="post">
    <input name="senderId" type="hidden" value="${userId}">
    <input name="data" type="text"/>
    <input type="submit" value="Send">
</form>
</body>
</html>