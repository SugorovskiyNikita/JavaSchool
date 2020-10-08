<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">

    <title>Contracts</title>

    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">
</head>
<body>
<jsp:include page="navbarCustomer.jsp"/>
<div class="outer-wrapper container">
    <div align="center">
        <h2>Contracts</h2>
    </div>

    <table class="table">
        <tr>
            <th>ID</th>
            <th>Number</th>
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
                            <form:form id="unblockByClientForm${contract.id}" method="POST"
                                       action="${contextPath}/client/unblockByClient"
                                       enctype="application/x-www-form-urlencoded">
                                <input type="hidden" name="contractId" value=${contract.id}>
                                <input type="hidden" name="sessionRole" value=${role}>
                                <a class="inline-link-unlock" title="Unblock contract" href="#"
                                   onclick="document.forms['unblockByClientForm${contract.id}'].submit()">Unblock</a>
                            </form:form>
                        </c:when>
                        <c:otherwise>

                            <form:form id="blockByClientForm${contract.id}" method="POST"
                                       action="${contextPath}/client/blockByClient"
                                       enctype="application/x-www-form-urlencoded">
                                <input type="hidden" name="contractId" value=${contract.id}>
                                <input type="hidden" name="sessionRole" value=${role}>
                                <a class="inline-link-lock" title="Block contract" href="#"
                                   onclick="document.forms['blockByClientForm${contract.id}'].submit()">Block</a>
                            </form:form>

                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>
</body>
</html>