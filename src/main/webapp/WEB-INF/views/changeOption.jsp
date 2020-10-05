<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Step 2. Choose options</title>
</head>

<body>
<div class="outer-wrapper">

    <jsp:include page="navbarCustomer.jsp"/>

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



        <div class="container">
            <c:forEach var="option" items="${option}">

            </c:forEach>
        </div>
        <p>
            Available options list:
            <c:choose>
            <c:when test="${option.size() != 0}">
        </p>
        <br>
        <form:form method="POST" action="/changeOption"
                   enctype="application/x-www-form-urlencoded">
            <table class="table">
                <tr>
                    <th>
                        Option title
                    </th>
                    <th>
                        Price
                    </th>
                    <th>
                        Cost of connection
                    </th>
                    <th style="width: 0">
                        <img src='/resources/s.gif'>
                    </th>
                </tr>
                <c:forEach var="option" items="${option}">

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
                            <input type="checkbox" id="box${option.id}" name="options" value="${option.id}">
                        </td>
                        <td id="dep${option.id}" style="display: none;">
                            (will be enabled automatically)
                        </td>
                        <td id="inc${option.id}" style="display: none;">
                            (incompatible with chosen option)
                        </td>
                    </tr>

                    <SCRIPT language="JavaScript">
                        $(function(){
                            $("#box${option.id}").click(function(){

                                if($("#box${option.id}").is(":checked")) {

                                    <c:if test="${option.requiredFrom.size() != 0}">

                                    <c:forEach items="${option.requiredFrom}" var="requiredFrom">
                                    $("#box${requiredFrom.id}").attr("disabled", true);
                                    $("#dep${requiredFrom.id}").attr("style", "width: 200; background: rgba(216, 255, 213, 0.38); font-size: 12px; color: #008d47");
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
                                    $("#box${option.id}").removeAttr("disabled");
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
                    </SCRIPT>
                </c:forEach>
            </table>
            <br>
            <input type="hidden" name="contractId" value=${contract.id}>
            <input type="hidden" name="tariffId" value=${tariff.id}>
            <button type="submit" class="btn btn-outline-dark" >Save</button>
        </form:form>
        </c:when>
        <c:otherwise>
            empty.
            </p>
        </c:otherwise>
        </c:choose>
    </div>

</div>
</body>
</html>