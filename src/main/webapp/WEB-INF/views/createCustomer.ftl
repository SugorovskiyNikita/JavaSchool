<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add customer</title>
</head>
<body>
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


</body>
</html>