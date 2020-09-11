<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add customer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<form name="customer" action="/addCustomer" method="post">
    Name: <input type="text" name="name" /><br>
    Lastname: <input type="text" name="lastname" /><br>
    Birthday: <input type="date" name="dateOfBirth" /><br>
    Passport number: <input type="text" name="passportNumber" /><br>
    Passport data: <input type="text" name="passportData" /><br>
    Address: <input type="text" name="address" /><br>
    Email: <input type="text" name="email" /><br>
    Password: <input type="text" name="password" /><br>
    Is Blocked: <input type="text" name="isBlocked" /><br>
    <input type="submit" name="submit" value="Add new customer" />
</form>

</div>
</body>
</html>