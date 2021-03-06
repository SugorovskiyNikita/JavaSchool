<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/css/css1.css" type="text/css">

    <title>Welcome</title>
</head>

<c:choose>
    <c:when test="${role.id != 2}">
        <jsp:include page="navbarCustomer.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="navbarAdmin.jsp"/>
    </c:otherwise>
</c:choose>

<body>
<div id="page-container">
    <div id="content-wrap">
        <div id="wel" class="text-center">
            <h1 id="first" class="alert">Welcome, <c:out value="${customer.name}" default="guest"/> !</h1>
        </div>
        <div class="container">
            <div class="row" style="display: flex; background-size: contain; flex-wrap: wrap;">
                <div class="col-sm-6">
                    <img src="https://i.ytimg.com/vi/a84UlLe6zDg/maxresdefault.jpg" width="480" height="320">
                </div>
                <div class="col-sm-6">
                    <img src="https://www.androidcentral.com/sites/androidcentral.com/files/styles/large/public/article_images/2020/09/t-mobile-project-10million.jpg"
                         width="480" height="320">
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
