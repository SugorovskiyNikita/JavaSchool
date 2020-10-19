<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <script src="/src/main/webapp/resources/js/search_line.js"></script>
    <link rel="stylesheet" href="/src/main/webapp/resources/css/forlist.css">
    <link rel="stylesheet" href="/src/main/webapp/resources/css/css1.css" type="text/css">

    <title>Contracts List</title>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div id="page-container">
    <div id="content-wrap">
        <div class="container target">
            <h1>Contract list</h1>
            <input id='myInput' onkeyup='searchTable()' type='text'>
            <table id="myTable" class="table table-striped">
                <tr>
                    <th scope="col">Contract Id</th>
                    <th scope="col">Number</th>
                    <th scope="col">Customer id</th>
                    <th scope="col">Tariff</th>
                    <th scope="col">Is Blocked</th>
                    <th scope="col">
                        <button type="button" class="btn btn-success"><a id="link" href="/admin/addCustomer">Add new
                            Customer</a></button>
                    </th>
                </tr>
                <c:forEach items="${contracts}" var="contract">
                    <tr>

                        <td>${contract.id}</td>
                        <td>${contract.number}</td>
                        <td>${contract.customer.id}</td>
                        <td>${contract.tariff.name}</td>
                        <td><c:choose>
                            <c:when test="${contract.isBlocked >= 1}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose></td>
                        <td>
                            <button type="button" class="btn btn-dark"><a id="link1"
                                                                          href="/admin/customerInfo/${contract.customer.id}">Show
                                details</a></button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
