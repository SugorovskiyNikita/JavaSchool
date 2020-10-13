<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
    <script src="/resources/js/showC.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="/resources/css/css1.css" type="text/css">
    <title>Customer Info</title>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container target">
    <div class="row">
        <div class="col-sm-10">
            <h1 class="">Customer information</h1>
            <button type="button" class="btn btn-warning"><a id="link" href="/admin/customers">Return to all
                customers</a></button>
        </div>
        <div class="col-sm-2"><img title="profile image" class="img-circle img-responsive"
                                   src="https://searchusers.xyz/img/user.png">
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-4">
            <!--left col-->
            <ul class="list-group">
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Id: </strong></span> ${customer.id}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Name: </strong></span> ${customer.name}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Surname: </strong></span> ${customer.surname}</li>
                <li class="list-group-item text-right"><span class="pull-left" dataformatas="yyyy-MM-dd"><strong
                        class="">Birthday: </strong></span><fmt:formatDate value="${customer.dateOfBirth}"
                                                                           pattern="dd-MM-yyyy"/></li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Passport data: </strong></span>${customer.passportData}
                </li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Passport number: </strong></span>${customer.passportNumber}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Address: </strong></span>${customer.address}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Email: </strong></span>${customer.email}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Id blocked: </strong></span>${customer.isBlocked}</li>
                <c:forEach var="contracts" items="${customer.contracts}">
                    <li class="list-group-item text-right"><span class="pull-left"><strong class="" name="contract">Contracts: </strong></span>${contracts.id}
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="row">
            <form name="customer" action="/admin/updateContract" method="post">

                <label for="contract" class="col-sm">Contract</label>
                <div class="col-sm">
                    <div class="controls">
                        <select required id="contract" size="1" name="contractId" class="form-control"
                                onchange="this.value">
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
                        <select required id="tariff" size="1" name="tariffId" class="form-control"
                                onchange="this.value">
                            <c:forEach var="tariff" items="${tariff}">
                            <option value="${tariff.id}">${tariff.name}
                                </c:forEach>
                        </select>
                    </div>
                </div>
                <br>
                <label for="option" class="col-sm">Options</label>
                <div class="col-sm">
                    <div class="controls" id="options-area">
                        <c:forEach items="${options}" var="option">
                            <label class="custom-control custom-checkbox" id="option">
                                <input type="checkbox" id="box${option.id}" name="options" value="${option.id}">
                                <span class="custom-control-indicator"></span>
                                <span class="custom-control-description">${option.name}</span>
                                <script>
                                    $(function () {
                                        $("#box${option.id}").click(function () {

                                            if ($("#box${option.id}").is(":checked")) {

                                                <c:if test="${option.requiredFrom.size() != 0}">

                                                <c:forEach items="${option.requiredFrom}" var="req">
                                                $("#box${req.id}").attr("checked", true);
                                                $("#dep${req.id}").attr("style", "width: 100; background: rgba(216, 255, 213, 0.38); font-size: 12px; color: #008d47");
                                                </c:forEach>

                                                </c:if>

                                                <c:if test="${option.forbiddenWith.size() != 0}">

                                                <c:forEach items="${option.forbiddenWith}" var="incompatibleOption">
                                                $("#box${incompatibleOption.id}").attr("disabled", true);
                                                $("#inc${incompatibleOption.id}").attr("style", "width: 200; background: rgba(255, 232, 232, 0.52); font-size: 12px; color: #C90000");
                                                </c:forEach>

                                                </c:if>

                                            } else {

                                                <c:if test="${option.requiredFrom.size() != 0}">

                                                <c:forEach items="${option.requiredFrom}" var="dependentOption">
                                                $("#box${dependentOption.id}").removeAttr("disabled");
                                                $("#dep${dependentOption.id}").attr("style", "display: none;");
                                                </c:forEach>

                                                </c:if>

                                                <c:if test="${option.forbiddenWith.size() != 0}">

                                                <c:forEach items="${option.forbiddenWith}" var="incompatibleOption">
                                                $("#box${incompatibleOption.id}").removeAttr("disabled");
                                                $("#inc${incompatibleOption.id}").attr("style", "display: none;");
                                                </c:forEach>

                                                </c:if>
                                            }
                                        });
                                    });
                                </script>
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
