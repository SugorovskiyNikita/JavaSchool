<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add customer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style>
        input:invalid:not(:placeholder-shown) {border-color: #ff0000;}
        input:valid:not(:placeholder-shown) {border: 2px solid #000000;}
    </style>
<body>
<div class="container">
    <form name="customer" action="/addCustomer" method="post">
        <div>
            <label for="name">Name</label>
            <div><input type="text" name="name" placeholder="Your name" id="name"
                        required pattern=[A-Z][a-z]+></div>
        </div>
        <div>
            <label for="surname">Lastname</label>
            <div><input type="text" name="surname" placeholder="Your surname" id="surname"
                        required pattern=[A-Z][a-z]+></div>
        </div>
        <div>
            <label for="dateOfBirth">Birthday</label>
            <div><input type="date" name="dateOfBirth" placeholder="Day Month Year" id="dateOfBirth"></div>
        </div>
        <div>
            <label for="passportNumber">Passport Number</label>
            <div><input type="text" name="passportNumber" placeholder="Passport number" id="passportNumber"
                        required pattern=[0-9]+></div>
        </div>
        <div>
            <label for="passportData">Passport Data</label>
            <div><input type="text" name="passportData" placeholder="Passport data" id="passportData"></div>
        </div>
        <div>
            <label for="address">Address</label>
            <div><input type="text" name="address" placeholder="Address" id="address"></div>
        </div>
        <div>
            <label for="email">Email</label>
            <div><input type="email" name="email" placeholder="Email" id="email"></div>
        </div>
        <div>
            <label for="password">Password</label>
            <div><input type="text" name="password" placeholder="Password" id="password"></div>
        </div><br>
        <div><input type="submit" name="submit" value="Add new customer" class="btn btn-success"></div>
    </form>
</div>
</body>
</html>
