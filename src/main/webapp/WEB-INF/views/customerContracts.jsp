<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Contracts</title>


</head>
<body>
<jsp:include page="navbarCustomer.jsp"/>
<div class="outer-wrapper container">
    <div align="center">
        <h2>Contracts</h2>
    </div>

    <p>
    <h2>Connected options</h2>

    <c:choose>
        <c:when test="${optionsList.size() != 0}">
            <c:if test="${contract.isBlocked != 1 && contract.isBlocked != 2 && contract.balance > 0}">
                <a title="Change tariff or options" href="#" onclick="document.forms['changeTariffForm'].submit()">
                    <button type="button" class="btn btn-lg btn-info">Change tariff</button>
                </a>
                <a title="Change tariff or options" href="#" onclick="document.forms['chooseOptionForm${contract.tariff.id}'].submit()">
                    <button type="button" class="btn btn-lg btn-dark">Change option</button>
                </a>

                <form:form id="chooseOptionForm${contract.tariff.id}"
                           method="POST"
                           action="/changeOnlyOptions"
                           enctype="application/x-www-form-urlencoded">
                    <input type="hidden" name="contractId" value=${contract.id}>
                    <input type="hidden" name="number" value="${contract.number}">
                    <input type="hidden" name="tariffId" value=${contract.tariff.id}>

                </form:form>

            </c:if>
            </p>
            <br>
            <table class="table">
                <tr>
                    <th>
                        Title
                    </th>
                    <th>
                        Price
                    </th>
                </tr>
                <c:forEach var="usedOptions" items="${optionsList}">
                    <tr>
                        <td>
                                ${usedOptions.name}
                        </td>
                        <td>
                                ${usedOptions.cost}
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h2>No connected option</h2>
            <c:if test="${contract.isBlocked != 1 && contract.isBlocked != 2 && contract.balance > 0}">
                <button type="button" class="btn btn-lg btn-info"><a title="Change tariff or options" href="#"
                                                                     onclick="document.forms['changeTariffForm'].submit()">Connect
                 options</a></button>
            </c:if>
            </p>
        </c:otherwise>
    </c:choose>

    <form:form id="changeTariffForm" method="POST"
               action="/changeTariff"
               enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="contractId" value=${contract.id}>
        <input type="hidden" name="tariffId" value=${contract.tariff.id}>
    </form:form>
</div>


</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>