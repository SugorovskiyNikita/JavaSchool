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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Tariff List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="/resources/js/search_line.js"></script>
    <link rel="stylesheet" href="/resources/css/forlist.css">
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container target">
    <h1>Tariff list</h1>
    <input id='myInput' onkeyup='searchTable()' type='text'>

    <table id="myTable" class="table table-striped">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Cost</th>
            <th scope="col">Description</th>
            <th>
                <button class="btn-success btn"><a href="/admin/addTariff" style="color: #ebebeb">Add new Tariff</a>
                </button>
            </th>
        </tr>
        <c:forEach items="${tariffs}" var="tariff">
            <tr>
                <td>${tariff.id}</td>
                <td>${tariff.name}</td>
                <td>${tariff.cost}</td>
                <td>${tariff.description}</td>
                <td>
                    <button class="btn btn-primary"><a style="color: #ebebeb"
                                                       href="/admin/editTariff/${tariff.id}">Edit</a></button>
                </td>
                <td>
                    <button class="btn btn-danger"><a style="color: #ebebeb"
                                                      href="/deleteTariff/${tariff.id}">Delete</a></button>
                </td>

            </tr>
        </c:forEach>
    </table>
    <br>

</div>
</body>
</html>

