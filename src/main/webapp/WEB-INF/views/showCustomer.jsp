<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
    <script src="/resources/js/showC.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/resources/css/css1.css" type="text/css">
    <title>Customer Info</title>
</head>
<body>
    <jsp:include page="navbarCustomer.jsp" />
<div class="container target">
    <div class="row">
        <div class="col-sm-10">
            <h1 class="">Customer information</h1>
            <button type="button" class="btn btn-warning"> <a id="link" href="/customers">Return to all customers</a></button>
        </div>
        <div class="col-sm-2"><img title="profile image" class="img-circle img-responsive" src="https://searchusers.xyz/img/user.png">
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-4">
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
                <c:forEach var="contracts" items="${customer.contracts}">
                    <li class="list-group-item text-right"><span class="pull-left"><strong class="" name="contract">Contracts: </strong></span>${contracts.id}</li>
                </c:forEach>
            </ul>
        </div>
        <div class="row">
            <form name="customer" action="/updateContract" method="post">

                <label for="contract" class="col-sm">Contract</label>
                    <div class="col-sm">
                        <div class="controls">
                            <select required id="contract" name="contract" class="form-control" onchange="this.value">
                                <c:forEach var="contracts" items="${customer.contracts}">
                                    <option value="${contracts.id}">${contracts.id}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                <br>
                <label class="col-sm">Phone number</label>
                    <div class="col-sm">
                        <input id="number" name="number">
                        <script>
                            function getRandomTel() {
                                var m = "+7999";
                                var x = m + (Math.floor(Math.random() * 10000000));
                                return x;
                            }
                                document.getElementById('number').value = getRandomTel()

                        </script>
                    </div>
                <br>
                <label for="tariff" class="col-sm">Tariff</label>
                    <div class="col-sm">
                        <div class="controls">
                            <select required id="tariff" size="1" name="tariff" class="form-control" onchange="this.value">
                                <c:forEach var="tariff" items="${tariff}">
                                    <option value="${tariff.id}">${tariff.name}
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                <br>
                <br>
                <label for="option" class="col-sm">Options</label>
                    <div class="col-sm">
                        <div class="controls">
                            <c:forEach items="${option}" var="options">
                            <label class="custom-control custom-checkbox" id="option">
                                <input type="checkbox" class="custom-control-input" value="${options.id}">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">${options.name}</span>
                            </label>
                            </c:forEach>
                            </div>
                        </div>
                    <br>
                            <button type="submit" class="btn btn-primary">
                                Update contract
                            </button>

            </form>
        </div>
    </div>
    <br>
</div>

</body>
</html>
