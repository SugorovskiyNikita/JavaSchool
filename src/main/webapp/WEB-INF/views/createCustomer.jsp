<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add customer</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style>
        input:invalid:not(:placeholder-shown) {border-color: #ff0000;}
        input:valid:not(:placeholder-shown) {border: 3px solid #000000;}
    </style>
<body>
<div class="container-fluid">
        <fieldset class="form-group">
        <label for="name">Name</label>
            <input type="text" class="form-control" name="name" placeholder="Your name" id="name"
                        required pattern=[A-Z][a-z]+>
        </fieldset>
        <fieldset class="form-group">
            <label for="surname">Surname</label>
            <input type="text" class="form-control" name="surname" placeholder="Your surname" id="surname"
                        required pattern=[A-Z][a-z]+>
        </fieldset>
        <fieldset class="form-group">
            <label for="dateOfBirth">Birthday</label>
            <input type="date" class="form-control" name="dateOfBirth" placeholder="Day Month Year" id="dateOfBirth">
        </fieldset>
        <fieldset class="form-group">
            <label for="passportNumber">Passport Number</label>
            <input type="text" class="form-control" name="passportNumber" placeholder="Passport number" id="passportNumber"
                        required pattern=[0-9]+>
        </fieldset>
        <fieldset class="form-group">
            <label for="passportData">Passport Data</label>
            <input type="text" class="form-control" name="passportData" placeholder="Passport data" id="passportData">
        </fieldset>
        <fieldset class="control-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" name="address" placeholder="Address" id="address">
        </fieldset>
        <fieldset class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" name="email" placeholder="Email" id="email">
        </fieldset>
        <fieldset class="form-group">
            <label for="password">Password</label>
            <input type="text" class="form-control" name="password" placeholder="Password" id="password">
        </fieldset><br>
        <input type="submit" name="submit" value="Add new customer" class="btn btn-success">
    </form>
</div>
</body>
</html>
