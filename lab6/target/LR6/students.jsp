<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link href="styles.css" rel="stylesheet" type="text/css">
    <title>Students</title>
</head>
<body>
<h1>Students</h1>
<jsp:useBean id="students" scope="request" type="java.util.List<LR6.entity.Student>"/>
<c:if test="${students != null}">
    <table>
        <tr>
            <th><c:out value="ID"/></th>
            <th><c:out value="NAME"/></th>
            <th><c:out value="SURNAME"/></th>
            <th><c:out value="SCORE"/></th>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.surname}</td>
                <td>${student.score}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/LR6">To main page</a>
</body>
</html>
