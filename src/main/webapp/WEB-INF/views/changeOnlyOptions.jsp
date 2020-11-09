<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="java">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/css1.css" type="text/css">

    <title>Step 2. Choose options</title>
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
                <p>
                    Chosen tariff title: ${tariff.name}
                </p>
                <p>
                    Chosen tariff price: ${tariff.cost}
                </p>


            </div>

            <div class="container">
                <p>
                    Step 2. Choose options for tariff.
                </p>
                <br>
                <p>
                    Available options list:

                    <c:choose>
                    <c:when test="${options.size() != 0}">

                </p>
                <br>
                <form:form method="POST" action="/updateContractCustomer"
                           enctype="application/x-www-form-urlencoded">
                    <table class="table">
                        <tr>
                            <th scope="col">
                                Option title
                            </th>
                            <th scope="col">
                                Price
                            </th>
                            <th scope="col">
                                Cost of connection
                            </th>
                            <th scope="col" style="width: 0">
                                <img src="https://img.icons8.com/ios/452/checked-checkbox.png" height="19">
                            </th>
                        </tr>
                        <c:forEach var="option" items="${options}">

                            <tr>
                                <td style="width: 200px">
                                        ${option.name}
                                </td>
                                <td>
                                        ${option.cost}
                                </td>
                                <td>
                                        ${option.connectCost}
                                </td>
                                <td style="width: 0">
                                    <c:choose>
                                        <c:when test="${used.contains(option)}">
                                            <input type="checkbox" id="box${option.id}" name="options"
                                                   value="${option.id}"
                                                   checked="checked">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" id="box${option.id}" name="options"
                                                   value="${option.id}">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
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
                        </c:forEach>
                    </table>
                    <br>
                    <input type="hidden" name="number" value="${contract.number}">
                    <input type="hidden" name="contractId" value=${contract.id}>
                    <input type="hidden" name="tariffId" value=${tariff.id}>
                    <button type="submit" class="btn btn-success">Save</button>
                </form:form>
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
</body>
<jsp:include page="footer.jsp"/>
</html>