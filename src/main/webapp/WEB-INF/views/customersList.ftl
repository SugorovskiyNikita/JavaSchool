
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
            <th>Date of Birth</th>
            <th>Passport Number</th>
            <th>Passport Date</th>
            <th>Address</th>
            <th>Email</th>
            <th>Password</th>
            <th>Is Blocked</th>
        </tr>
<#list customers as customer>
    <tr>
        <td>${customer.id}</td>
        <td>${customer.name}</td>
        <td>${customer.lastname}</td>
        <td>${customer.date_of_birth}</td>
        <td>${customers.passport_number}</td>
        <td>${customers.passpor_date}</td>
        <td>${customers.address}</td>
        <td>${customers.email}</td>
        <td>${customers.password}</td>
        <td>${customers.is_blocked}</td>
    </tr>
</#list>
    </table>


</body>
</html>
