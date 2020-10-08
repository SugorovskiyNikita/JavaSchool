<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    a {
        color: #ebebeb;
    }

    </style>

    <title>Profile</title>
</head>
<body>
<jsp:include page="navbarCustomer.jsp" />
<div class="container target">
    <div class="row">
        <div class="col-sm-10">
            <h1 class="">${customer.surname} ${customer.name}</h1>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-2"><img title="profile image" class="img-circle img-responsive" src="https://searchusers.xyz/img/user.png">
        </div>
        <div class="col-sm-5">
            <!--left col-->
            <ul class="list-group">

                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Number of contracts: </strong></span> ${customer.contracts.size()}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Email: </strong></span>${customer.email}</li>
                <li class="list-group-item text-right"><span class="pull-left"><strong class="">Me blocked?: </strong></span>
                    <c:choose>
                        <c:when test="${customer.isBlocked == 1}">Yes</c:when>
                        <c:otherwise>No</c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
        <div class="col-sm-4">
            <!--left col-->
            <ul class="list-group">
                <button type="button" class="btn btn-info btn-lg"><a  href="/contractsCustomer">Contracts</a></button>
                <button type="button" class="btn btn-success btn-lg"><a  href="/changeTariff">Tariffs</a></button>
                <button type="button" class="btn btn-warning btn-lg"><a  href="/changeOption">Options</a></button>
            </ul>
        </div>
    </div>
    <br>
    <div class="content-center">
        <div class="row">
        <div class="col-8">

                <table class="table">
                    <tr>
                        <th>ID</th>
                        <th>Number</th>
                        <th>Balance</th>
                        <th>Tariff</th>
                        <th>Blocked by customer?</th>
                        <th>Blocked by operator?</th>
                        <th></th>
                    </tr>
                    <c:forEach var="contract" items="${customer.contracts}">
                        <tr>
                            <td>${contract.id}</td>
                            <td>
                                <form:form id="viewContractForm${contract.id}" method="POST"
                                           action="${contextPath}/client/viewContract/"
                                           enctype="application/x-www-form-urlencoded">
                                    <input type="hidden" name="contractId" value=${contract.id}>
                                    <a href="#"
                                       onclick="document.forms['viewContractForm${contract.id}'].submit()">${contract.number}</a>
                                </form:form>
                            </td>
                            <td> ${contract.balance}</td>
                            <td>${contract.tariff.name}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${contract.isBlocked == 1}">Yes</c:when>
                                    <c:otherwise>No</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${contract.isBlocked == 2}">Yes</c:when>
                                    <c:otherwise>No</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${contract.isBlocked == 2  == true}">
                                        <form:form id="unblock${contract.id}" method="POST"
                                                   action="/unblock"
                                                   enctype="application/x-www-form-urlencoded">
                                            <input type="text" hidden name="contractId" value=${contract.id}>
                                            <c:forEach var="role" items="${customer.roles}">
                                                <input type="hidden" name="sessionRole" value="${role.roleName}">
                                            </c:forEach>
                                    <button type="button" class="btn btn-success btn-sm"><a class="inline-link-unlock" title="Unblock contract" href="#"
                                               onclick="document.forms['unblock${contract.id}'].submit()">Unblock</a>
                                        </form:form>
                                    </c:when>
                                    <c:otherwise>

                                        <form:form id="block${contract.id}" method="POST"
                                                   action="/block"
                                                   enctype="application/x-www-form-urlencoded">
                                            <input type="text" hidden name="contractId" value=${contract.id}>
                                    <c:forEach var="role" items="${customer.roles}">
                                        <input type="hidden" name="sessionRole" value="${role.roleName}">
                                    </c:forEach>
                                    <button type="button" class="btn btn-danger btn-sm"><a class="inline-link-lock" title="Block contract" href="#"
                                               onclick="document.forms['block${contract.id}'].submit()">Block</a>
                                        </form:form>

                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

        </div>
        </div>
    <div class="row">
        <div class="col-sm-3">
            <div class="container text-center">
                <!--<button type="button" class="btn btn-success btn-lg"><a id="link" href="/changeTariff">Tariffs</a></button>
                <button type="button" class="btn btn-warning btn-lg"><a id="link1" href="/changeOption">Options</a></button>
                <button type="button" class="btn btn-danger btn-lg">Block</button>
                <button type="button" class="btn btn-info btn-lg">Unblock</button>-->
            </div>
            </div>
    </div>
    </div>
</div>
</body>
</html>
