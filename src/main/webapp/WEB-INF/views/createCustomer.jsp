<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add customer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<body>
<div class="container">
    <form name="customer" action="/addCustomer" method="post">
        <div>
            <label for="name">Name</label>
            <div><input type="text" name="name" id="name"></div>
        </div>
        <div>
            <label for="lastname">Lastname</label>
            <div><input type="text" name="lastname" id="lastname"></div>
        </div>
        <div>
            <label for="dateOfBirth">Birthday</label>
            <div><input type="date" name="dateOfBirth" id="dateOfBirth"></div>
        </div>
        <div>
            <label for="passportNumber">Passport Number</label>
            <div><input type="text" name="passportNumber" id="passportNumber"></div>
        </div>
        <div>
            <label for="passportData">Passport Data</label>
            <div><input type="text" name="passportData" id="passportData"></div>
        </div>
        <div>
            <label for="address">Address</label>
            <div><input type="text" name="address" id="address"></div>
        </div>
        <div>
            <label for="email">Email</label>
            <div><input type="text" name="email" id="email"></div>
        </div>
        <div>
            <label for="password">Password</label>
            <div><input type="text" name="password" id="password"></div>
        </div><br>
        <div><input type="submit" name="submit" value="Add new customer"></div>
    </form>
</div>
</body>
</html>
