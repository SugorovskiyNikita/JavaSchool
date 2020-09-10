<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <title>Customers List</title>
</head>
<body>
    <h1>Customer list</h1>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Lastname</th>
            <th>Day Of Birth</th>
            <th>Passport Number</th>
            <th>Passport Data</th>
            <th>Address</th>
            <th>Email</th>
            <th>Password</th>
            <th>Is Blocked</th>
        </tr>
<c:forEach items="${customers}" var="customer">
    <tr>
        <td><a href="/customer/${customer.id}">${customer.id}</a></td>
        <td>${customer.name}</td>
        <td>${customer.lastname}</td>
        <td>${customer.dateOfBirth}</td>
        <td>${customer.passportNumber}</td>
        <td>${customer.passportData}</td>
        <td>${customer.address}</td>
        <td>${customer.email}</td>
        <td>${customer.password}</td>
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
