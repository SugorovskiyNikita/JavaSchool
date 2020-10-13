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
    <script src="/resources/js/search_line.js"></script>
    <link rel="stylesheet" href="/resources/css/forlist.css">
    <title>Customers List</title>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container target">
    <h1>Customer list</h1>
    <input id='myInput' onkeyup='searchTable()' type='text'>


    <table id="myTable" class="table table-striped">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Surname</th>
            <th scope="col">Email</th>
            <th scope="col">Is Blocked</th>
            <th scope="col">Address</th>
            <th scope="col">
                <button type="button" class="btn btn-success"><a id="link" href="/admin/addCustomer">Add new
                    Customer</a></button>
            </th>


        </tr>
        <c:forEach items="${customers}" var="customer">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.surname}</td>
                <td>${customer.email}</td>
                <td>
                    <c:choose>
                        <c:when test="${customer.isBlocked >= 1}">Yes</c:when>
                        <c:otherwise>No</c:otherwise>
                    </c:choose>
                </td>
                <td>${customer.address}</td>
                <td>
                    <button type="button" class="btn btn-dark"><a id="link1" href="/admin/customerInfo/${customer.id}">Show
                        details</a></button>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
