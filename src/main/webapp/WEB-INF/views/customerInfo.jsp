<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div id="page-container">
    <div id="content-wrap">
<div class="container target">
    <div class="row">
        <div class="col-sm-12">
            <h1 class="">Customer information</h1>
            <button type="button" class="btn btn-info"><a style="color: #ebebeb;" href="/admin/customers">Return to all
                customers</a></button>
        </div>

    </div>
    <br>
    <div class="row">
        <div class="col-sm-3">
            <!--left col-->
            <ul class="list-group">
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Id: </strong></span> ${customer.id}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Name: </strong></span> ${customer.name}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Surname: </strong></span> ${customer.surname}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong
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
                <!--<li class="list-group-item text-right"><span class="pull-left"><strong
                        class="">Id blocked: </strong></span>${customer.isBlocked}</li>-->
            </ul>
        </div>
        <div class="col-sm-9">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Contract</th>
                        <th>Number</th>
                        <th>Tariff</th>
                        <th>Used options</th>
                        <th></th>
                        <th></th>

                    </tr>
                    </thead>
                    <c:forEach var="contract" items="${customer.contracts}">
                        <tbody>
                        <tr>
                            <td>${contract.id}</td>
                            <td>${contract.number}</td>
                            <td>${contract.tariff.name}</td>
                            <c:forEach var="option" items="${contract.usedOptions}">
                                <td>${option.name}</td>
                            </c:forEach>


                            <td>
                                <c:choose>
                                <c:when test="${contract.isBlocked  >= 1  == true}">
                                <form:form id="unblock${contract.id}" method="POST"
                                           action="/admin/unblock"
                                           enctype="application/x-www-form-urlencoded">
                                <input type="text" hidden name="contractId" value=${contract.id}>

                                <button type="button" class="btn btn-success btn-sm"><a class="inline-link-unlock"
                                                                                        title="Unblock contract"
                                                                                        style="color: #ebebeb;" href="#"
                                                                                        onclick="document.forms['unblock${contract.id}'].submit()">Unblock</a>
                                    </form:form>
                                    </c:when>
                                    <c:otherwise>

                                    <form:form id="block${contract.id}" method="POST"
                                               action="/admin/block"
                                               enctype="application/x-www-form-urlencoded">
                                    <input type="text" hidden name="contractId" value=${contract.id}>

                                    <button type="button" class="btn btn-danger btn-sm"><a class="inline-link-lock"
                                                                                           title="Block contract"
                                                                                           style="color: #ebebeb;"
                                                                                           href="#"
                                                                                           onclick="document.forms['block${contract.id}'].submit()">Block</a>
                                        </form:form>

                                        </c:otherwise>
                                        </c:choose>
                            </td>
                            <td>
                                <button type="button" class="btn btn-dark btn-sm"><a style="color: #F4EEE8;"
                                                                                     href="/admin/changeOption/${contract.id}">Options</a>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                    <br>
                </table>
            </div>
            <br>
            <button type="button" class="btn btn-success"><a style="color: #F4EEE8;"
                                                             onclick="document.forms['addContract'].submit()">Add
                contract</a></button>
            <button type="button" class="btn btn-primary"><a style="color: #F4EEE8;"
                                                             href="/admin/customer/${customer.id}">Change tariff</a>
            </button>

            <form:form id="addContract" method="POST"
                       action="/admin/addContractForCustomer"
                       enctype="application/x-www-form-urlencoded">
                <input type="hidden" name="customerId" value=${customer.id}>
            </form:form>
        </div>
    </div>
    <br>

</div>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
