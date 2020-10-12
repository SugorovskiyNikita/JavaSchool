<%--
  Created by IntelliJ IDEA.
  User: Печалька
  Date: 19.09.2020
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Tariff List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/forlist.css"/>">
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container target">
<h1>Options list</h1>
<table class="table table-striped">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">Cost</th>
        <th scope="col">Cost connection</th>
        <th scope="col">Description</th>
    </tr>
    <c:forEach items="${options}" var="option">
        <tr>
            <td><a href="/option/${option.id}">${option.id}</a></td>
            <td>${option.name}</td>
            <td>${option.cost}</td>
            <td>${option.connectCost}</td>
            <td>${option.description}</td>
            <td><a href="/delete/${option.id}">Delete</a></td>
            <td><a href="/update/${option.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/addOption">Add new Option</a>
</div>
</body>
</html>
