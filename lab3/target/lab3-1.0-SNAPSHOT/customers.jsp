<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
    <style><%@include file="/WEB-INF/css/customers.css" %></style>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>Full Name</th>
        <th>Country</th>
        <th>State</th>
        <th>Address</th>
        <th>Telephone</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.fullName}</td>
            <td>${customer.country}</td>
            <td>${customer.state}</td>
            <td>${customer.address}</td>
            <td>${customer.telephone}</td>
        </tr>
    </c:forEach>
</table>
<hr />
<h2>Filter Customers by country</h2>
<form action="customers" method="get">
    <p>Country:</p>
    <input type="text" name="country">
    <input type="submit" value="Filter">
</form>
<hr />
<hr />
<h2>Search customers by id</h2>
<form action="customers" method="get">
    <p>ID:</p>
    <input type="text" name="id">
    <input type="submit" value="Search">
</form>
<hr />
<table>
    <tr>
        <th>id</th>
        <th>Full Name</th>
        <th>Country</th>
        <th>State</th>
        <th>Address</th>
        <th>Telephone</th>
    </tr>
    <c:forEach var="searchResult" items="${searchResult}">
        <tr>
            <td>${searchResult.id}</td>
            <td>${searchResult.fullName}</td>
            <td>${searchResult.country}</td>
            <td>${searchResult.state}</td>
            <td>${searchResult.address}</td>
            <td>${searchResult.telephone}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>