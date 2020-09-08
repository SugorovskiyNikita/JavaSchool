
<!DOCTYPE html>
<html lang="en">
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
<#list customers as customer>
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
    </tr>
</#list>
    </table>


</body>
</html>
