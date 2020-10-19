<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <title>Add customer</title>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<form name="customer" action="/updateCustomer" method="post">
    Id: <input type="text" name="id" value="${customer.id}"/><br>
    Name: <input type="text" name="name" value="${customer.name}"/><br>
    Lastname: <input type="text" name="surname" value="${customer.surname}"/><br>
    Birthday: <input type="date" name="dateOfBirth" value="${customer.dateOfBirth}"/><br>
    Passport number: <input type="text" name="passportNumber" value="${customer.passportNumber}"/><br>
    Passport data: <input type="text" name="passportData" value="${customer.passportData}"/><br>
    Address: <input type="text" name="address" value="${customer.address}"/><br>
    Email: <input type="text" name="email" value="${customer.email}"/><br>
    Password: <input type="text" name="password" value="${customer.password}"/><br>
    Is Blocked: <input type="text" name="isBlocked" value="${customer.isBlocked}"/><br>
    <input type="submit" name="submit" value="Save edit"/>
</form>


</body>
</html>