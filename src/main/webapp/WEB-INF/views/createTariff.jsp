<%--
  Created by IntelliJ IDEA.
  User: Печалька
  Date: 13.09.2020
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
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
    <form name="tariff" action="/addTariff" method="post">
        <div>
            <label for="name">Name</label>
            <div><input type="text" name="name" placeholder="" id="name"></div>
        </div>
        <div>
            <label for="cost">Cost</label>
            <div><input type="text" name="cost" placeholder="" id="cost"
                        required pattern=[0-9]+></div>
        </div>
        <div>
            <label for="description">Description</label>
            <div><input type="text" name="description" placeholder="" id="description"></div>
        </div><br>
        <div><input type="submit" name="submit" value="Save tariff" class="btn btn-success"></div>
    </form>
</div>
</body>
</html>

