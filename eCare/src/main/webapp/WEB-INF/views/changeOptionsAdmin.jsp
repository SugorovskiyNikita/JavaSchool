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
    <script src="/src/main/webapp/resources/js/showC.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="/src/main/webapp/resources/css/css1.css" type="text/css">
    <title>Customer Info</title>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div id="page-container">
    <div id="content-wrap">
        <div class="container target">
            <div class="row">
                <div class="col-sm-10">
                    <h1 class="">Customer information</h1>
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
                                class="">Customer id: </strong></span> ${contract.customer.id}</li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong
                                class="">Name: </strong></span> ${contract.customer.name}</li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong
                                class="">Surname: </strong></span> ${contract.customer.surname}</li>
                        <li class="list-group-item text-right"><span class="pull-left" dataformatas="yyyy-MM-dd"><strong
                                class="">Birthday: </strong></span><fmt:formatDate
                                value="${contract.customer.dateOfBirth}"
                                pattern="dd-MM-yyyy"/></li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong
                                class="">Passport data: </strong></span>${contract.customer.passportData}
                        </li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong
                                class="">Passport number: </strong></span>${contract.customer.passportNumber}</li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong
                                class="">Address: </strong></span>${contract.customer.address}</li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong
                                class="">Email: </strong></span>${contract.customer.email}</li>
                        <li class="list-group-item text-right"><span class="pull-left"><strong
                                class="">Id blocked: </strong></span>${contract.customer.isBlocked}</li>
                    </ul>
                </div>
                <div class="row">
                    <form name="customer" action="/admin/updateContract" method="post">

                        <label for="contract" class="col-sm">Contract</label>
                        <div class="col-sm">
                            <div class="controls">
                                <li id="contract" class="form-control">${contract.id}</li>
                                <input type="hidden" name="contractId" value="${contract.id}">
                            </div>
                        </div>
                        <br>
                        <label for="number" class="col-sm">Number</label>
                        <div class="col-sm">
                            <div class="controls">
                                <li id="number" class="form-control">${contract.number}</li>
                                <input type="hidden" name="number" value="${contract.number}">
                            </div>
                        </div>
                        <br>

                        <label for="tariff" class="col-sm">Tariff</label>
                        <div class="col-sm">
                            <div class="controls">
                                <li id="tariff" class="form-control">${contract.tariff.name}</li>
                                <input type="hidden" name="tariffId" value="${contract.tariff.id}">
                            </div>
                        </div>
                        <br>
                        <div class="col-sm">
                            <label for="option">Options</label>
                            <div class="controls" id="options-area">
                                <c:forEach items="${options}" var="option">
                                <c:choose>
                                <c:when test="${used.contains(option)}">
                                <label class="custom-control custom-checkbox" id="option">
                                    <span class="custom-control-indicator"></span>
                                    <input type="checkbox" id="box${option.id}" name="options" value="${option.id}"
                                           checked="checked">
                                    <span class="custom-control-description">${option.name}</span>
                                    </c:when>
                                    <c:otherwise>
                                    <label class="custom-control custom-checkbox" id="option">
                                        <span class="custom-control-indicator"></span>
                                        <input type="checkbox" id="box${option.id}" name="options" value="${option.id}">
                                        <span class="custom-control-description">${option.name}</span>
                                        </c:otherwise>
                                        </c:choose>

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

                            <br>
                            <button type="submit" class="btn btn-primary">
                                Update contract
                            </button>

                        </div>
                        <br>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
