<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <style>

        .btn {
            margin: 5px;
        }

        .btn-circle {
            width: 72px;
            height: 72px;
            border-radius: 48px;
            text-align: center;
            padding-left: 0;
            padding-right: 0;
            font-size: 16px;
            white-space: normal;
        }
    </style>
    <style>
    .btn {
    margin: 0 5px;
    }
    .lead {
    margin-bottom: 0;
    margin-top: 10px;
    }
    </style>

    <title>Profile</title>
</head>
<body>
<jsp:include page="navbarCustomer.jsp" />
<div class="container target">
    <div class="row">
        <div class="col-sm-10">
            <h1 class="">My Profile</h1>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-2"><img title="profile image" class="img-circle img-responsive" src="https://searchusers.xyz/img/user.png">
        </div>
        <div class="col-sm-5">
            <!--left col-->
            <ul class="list-group">
                <!--<li class="list-group-item text-right"><span class="pull-left"><strong class="">Id: </strong></span> ${customer.id}</li>-->
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Name: </strong></span> ${customer.name}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Surname: </strong></span> ${customer.surname}</li>
                <!--<li class="list-group-item text-right"><span class="pull-left"><strong class="">Birthday: </strong></span> ${customer.dateOfBirth}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Passport data: </strong></span>${customer.passportData}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Passport number: </strong></span>${customer.passportNumber}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Address: </strong></span>${customer.address}</li>-->
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Email: </strong></span>${customer.email}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Id blocked: </strong></span>${customer.isBlocked}</li>
            </ul>
        </div>
        <div class="col-sm-5">
            <!--left col-->
            <ul class="list-group">
                <c:forEach var="contracts" items="${customer.contracts}">
                <!--<li class="list-group-item text-right"><span class="pull-left"><strong class="">Id: </strong></span> ${customer.contracts}</li>-->
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Number contract: </strong></span> ${contracts.id}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Number Phone: </strong></span> ${contracts.number}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Balance: </strong></span>${contracts.balance}</li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong class="">Tariff: </strong></span>${contracts.tariff.name}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <div class="container text-center">
                <button type="button" class="btn btn-success btn-lg">Tariffs</button>
                <button type="button" class="btn btn-warning btn-lg">Options</button>
                <button type="button" class="btn btn-danger btn-lg">Block</button>
                <button type="button" class="btn btn-info btn-lg">Unblock</button>
            </div>
            </div>
    </div>

</div>
</body>
</html>
