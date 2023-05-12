<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Send message</title>
</head>
<body>
<form action="messages" method="post">
    <p>Enter message:</p>
    <input type="text" name="message">
    <input type="submit" value="Отправить">
</form>
<c:choose>
    <c:when test="${not empty messageSent}">
        <p>Sent message: ${messageSent}</p>
    </c:when>
    <c:otherwise>
        <p>No message was sent as message body was not provided.</p>
    </c:otherwise>
</c:choose>
</body>
</html>