<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <title>Customers List</title>
</head>
<body>
    <h1>Customer list</h1>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Is Blocked</th>
        </tr>
<c:forEach items="${customers}" var="customer">
    <tr>
        <td><a href="/customer/${customer.id}">${customer.id}</a></td>
        <td>${customer.name}</td>
        <td>${customer.surname}</td>
        <td>${customer.email}</td>
        <td>${customer.isBlocked}</td>
        <td><a href="/delete/${customer.id}">Delete</a></td>
        <td><a href="/update/${customer.id}">Edit</a></td>
    </tr>
</c:forEach>
    </table>
    <br>
    <a href="/addCustomer">Add new Customer</a>

</body>
</html>
