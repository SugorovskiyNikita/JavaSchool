<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/style.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/css1.css" type="text/css">
    <title>Step 1. Choose tariff</title>
</head>
<body>
<jsp:include page="navbarCustomer.jsp"/>
<div id="page-container">
    <div id="content-wrap">
        <div class="outer-wrapper">


            <div class="container">

                <header>
                    Basket
                </header>
                <p>
                    Contract phone number: ${contract.number}
                </p>
                <p>
                    Client balance: ${contract.balance}
                </p>
            </div>

            <div class="container">
                <p>
                    Step 1. Choose new tariff.
                </p>
                <br>
                <p>
                    Available tariffs list:
                    <c:choose>
                    <c:when test="${tariffs.size() != 0}">
                </p>
                <br>
                <table class="table">
                    <tr>
                        <th scope="col">
                            Title
                        </th>
                        <th scope="col">
                            Cost
                        </th>
                        <th scope="col">
                            Description
                        </th>
                    </tr>
                    <c:forEach var="tariff" items="${tariffs}">
                        <tr>
                            <td>
                                <form:form id="chooseTariffForm${tariff.id}"
                                           method="POST"
                                           action="/changeOption"
                                           enctype="application/x-www-form-urlencoded">
                                    <input type="hidden" name="contractId" value=${contract.id}>
                                    <input type="hidden" name="tariffId" value=${tariff.id}>
                                    <a href="#" id="chosenTariff"
                                       onclick="document.forms['chooseTariffForm${tariff.id}'].submit()">${tariff.name}</a>

                                </form:form>
                            </td>
                            <td>
                                    ${tariff.cost}
                            </td>
                            <td>
                                    ${tariff.description}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </c:when>
                <c:otherwise>
                    empty.
                    </p>
                </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
<jsp:include page="footer.jsp"/>
</html>