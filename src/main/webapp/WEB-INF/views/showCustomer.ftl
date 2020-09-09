<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
        <td>Lastname</td>
        <td>${customer.lastname}</td>
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