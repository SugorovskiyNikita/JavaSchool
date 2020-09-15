<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <title>Customer Info</title>
</head>
<body>
<h1>Customer Info</h1>
<table>
    <tr>
        <td>Id</td>
        <td>${customer.id}</td>
    </tr>
    <tr>
        <td>name</td>
        <td>${customer.name}</td>
    </tr>
    <tr>
        <td>Surname</td>
        <td>${customer.surname}</td>
    </tr>
    <tr>
        <td>day of birth</td>
        <td>${customer.dateOfBirth}</td>
    </tr>
    <tr>
        <td>passport data</td>
        <td>${customer.passportData}</td>
    </tr>
    <tr>
        <td>Passport Number</td>
        <td>${customer.passportNumber}</td>
    </tr>
    <tr>
        <td>address</td>
        <td>${customer.address}</td>
    </tr>
    <tr>
        <td>email</td>
        <td>${customer.email}</td>
    </tr>
    <tr>
        <td>id Blocked</td>
        <td>${customer.isBlocked}</td>
    </tr>
</table>

<br>
<a href="/customers">Back</a>
</body>
</html>