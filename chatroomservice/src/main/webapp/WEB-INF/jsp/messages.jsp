<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
</head>
<body>
<h3>You is ${userId} </h3> <a href="/login">Unlogin</a>
<h4>Messages:</h4>
<div id="messages">
    <c:forEach items="${messages}" var="message">
        <br/>--> ${message.senderId} ${message.data}
    </c:forEach>
</div>
<br/>

<form id="message" method="post">
    <input id="message_text" type="text"/>
    <a onclick="sendMessage()">Send</a>
</form>
<script>
    let userId = '${userId}';
    let chatId = '${chatId}'
</script>
<script src="${pageContext.request.contextPath}/app.js"></script>
</body>
</html>