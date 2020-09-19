<%--
  Created by IntelliJ IDEA.
  User: Печалька
  Date: 15.09.2020
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <title>Tariff List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="<c:url value="/resources/forlist.css"/>" />
</head>
<body>
<h1>Tariff list</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Cost</th>
        <th>Description</th>
    </tr>
    <c:forEach items="${tariffs}" var="tariff">
        <tr>
            <td><a href="/customer/${tariff.id}">${tariff.id}</a></td>
            <td>${tariff.name}</td>
            <td>${tariff.cost}</td>
            <td>${tariff.description}</td>
            <td><a href="/delete/${tariff.id}">Delete</a></td>
            <td><a href="/update/${tariff.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/addTariff">Add new Tariff</a>

</body>
</html>

