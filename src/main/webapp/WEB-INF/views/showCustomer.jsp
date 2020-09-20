<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/forlist.css"/>" >
    <title>Customer Info</title>
</head>
<body>
<div class="container target">
    <div class="row">
        <div class="col-sm-10">
            <h1 class="">Customer information</h1>
            <button type="button" class="btn btn-warning"> <a id="link" href="/customers">Return to all customers</a></button>
        </div>
        <div class="col-sm-2"><img title="profile image" class="img-circle img-responsive" src="https://cooltv.info/img/user.png">
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-6">
            <!--left col-->
            <ul class="list-group">
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Id: </strong></span> ${customer.id}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Name: </strong></span> ${customer.name}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Surname: </strong></span> ${customer.surname}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Birthday: </strong></span> ${customer.dateOfBirth}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Passport data: </strong></span>${customer.passportData}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Passport number: </strong></span>${customer.passportNumber}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Address: </strong></span>${customer.address}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Email: </strong></span>${customer.email}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Id blocked: </strong></span>${customer.isBlocked}</li>

            </ul>

        </div>
    </div>

<br>
</div>
</body>
</html>