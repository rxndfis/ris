<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Studying</title>
    <link href="styles.css" rel="stylesheet" type="text/css">
</head>

<body>
<form method="Get">
    <h1>Students search</h1>
    <button type="submit" formaction="getAll">GET ALL STUDENTS</button>
    <label>
        Get students bu surname:
        <input type="text" name="surname" placeholder="Surname">
    </label>
    <button type="submit" formaction="getBySurname">Find</button>
    <label>
        Get students bu score:
        <input type="number" max="10" min="0" step="0.01" name="score" placeholder="Score">
    </label>
    <button type="submit" formaction="getByScore">Find</button>
</form>
</body>
</html>
