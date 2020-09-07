<%--
  Created by IntelliJ IDEA.
  User: sugor
  Date: 07.09.2020
  Time: 1:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Reg</title>
</head>
<body>
<form id="addCustomerForm" action="addCustomer" method="post">
  Name: <input type="text" name="name" /><br>
  Surname: <input type="text" name="surname" /><br>
  Birthday: <input type="date" name="birthday" /><br>
  Passport data: <input type="text" name="passport" /><br>
  Address: <input type="text" name="address" /><br>
  Email: <input type="text" name="email" /><br>
  Password: <input type="text" name="passA" /><br>
  <input type="submit" name="submit" value="Add new customer" />
</form>
<a href="/hello"> Hello page</a>
</body>
</html>
