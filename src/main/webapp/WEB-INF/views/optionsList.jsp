<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Tariff List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="/resources/js/search_line.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/forlist.css"/>">
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container target">
    <h1>Options list</h1>
    <input id='myInput' onkeyup='searchTable()' type='text'>
    <table id="myTable" class="table table-striped">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Cost</th>
            <th scope="col">Cost connection</th>
            <th scope="col">Description</th>
            <th scope="col">
                <button class="btn-success btn"><a style="color: #ebebeb" href="/admin/addOption">Add new Option</a>
                </button>
            </th>
        </tr>
        <c:forEach items="${options}" var="option">
            <tr>
                <td>${option.id}</td>
                <td>${option.name}</td>
                <td>${option.cost}</td>
                <td>${option.connectCost}</td>
                <td>${option.description}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
