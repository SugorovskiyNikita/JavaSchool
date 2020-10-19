<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        .btn {
            margin: 0 5px;
        }

        a {
            color: #ebebeb;
        }

    </style>
    <link rel="stylesheet" href="/src/main/webapp/resources/css/css1.css" type="text/css">


    <title>Profile</title>
</head>
<body>
<jsp:include page="navbarCustomer.jsp"/>
<div id="page-container">
    <div id="content-wrap">
<div class="container-fluid h-100">
    <div class="container target">
        <div class="row">
            <div class="col-sm-10">
                <h1 class="">${customer.surname} ${customer.name}</h1>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-2"><img title="profile image" class="img-circle img-responsive"
                                       src="https://searchusers.xyz/img/user.png">
            </div>
            <div class="col-sm-5">
                <!--left col-->
                <ul class="list-group">

                    <li class="list-group-item text-right"><span class="pull-left"><strong
                            class="">Number of contracts: </strong></span> ${customer.contracts.size()}</li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong
                            class="">Email: </strong></span>${customer.email}</li>
                    <li class="list-group-item text-right"><span class="pull-left"><strong
                            class="">Me blocked?: </strong></span>
                        <c:choose>
                            <c:when test="${customer.isBlocked == 1}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
            <div class="col-sm-4">
                <img src="https://i.ytimg.com/vi/dMTWsWPPZ8w/maxresdefault.jpg" height="200">
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
                                               action="/viewContract"
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
                                    <c:when test="${contract.isBlocked  >= 1  == true}">
                                    <form:form id="unblock${contract.id}" method="POST"
                                               action="/unblock"
                                               enctype="application/x-www-form-urlencoded">
                                    <input type="text" hidden name="contractId" value=${contract.id}>

                                    <button type="button" class="btn btn-success btn-sm"><a style="color: #ebebeb"
                                                                                            class="inline-link-unlock"
                                                                                            title="Unblock contract"
                                                                                            href="#"
                                                                                            onclick="document.forms['unblock${contract.id}'].submit()">Unblock</a>
                                        </form:form>
                                        </c:when>
                                        <c:otherwise>

                                        <form:form id="block${contract.id}" method="POST"
                                                   action="/block"
                                                   enctype="application/x-www-form-urlencoded">
                                        <input type="text" hidden name="contractId" value=${contract.id}>

                                        <button type="button" class="btn btn-danger btn-sm"><a style="color: #ebebeb"
                                                                                               class="inline-link-lock"
                                                                                               title="Block contract"
                                                                                               href="#"
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
        </div>
    </div>
</div>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
