<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="java">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
    <script src="/resources/js/showC.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="stylesheet" href="/resources/css/css1.css" type="text/css">
    <title>Edit tariff</title>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container target">
    <div class="row">
        <div class="col-sm-10">
            <h1 class="">Customer information</h1>
            <button type="button" class="btn btn-warning"><a style="color: #ebebeb" href="/admin/tariffs">Return to all
                tariffs</a></button>
        </div>
        <div class="col-sm-2"><img title="profile image" class="img-circle img-responsive"
                                   src="https://freevectorlogo.net/wp-content/uploads/2013/12/t-mobile-vector-logo-400x400.png">
        </div>
    </div>
    <br>
    <form name="customer" action="/admin/editTariff" method="post">
        <div class="row">
            <div class="col-sm-4">
                <!--left col-->
                <label> Tariff information</label>
                <ul class="list-group">
                    <input type="hidden" name="tariffId" value="${tariff.id}">
                    <input class="form-control" type="text" name="name" placeholder="Name of tariff"
                           value="${tariff.name}">
                    <input class="form-control" type="number" name="cost" placeholder="Cost" value="${tariff.cost}">
                    <input class="form-control" type="text" name="description" placeholder="Description"
                           value="${tariff.description}">
                </ul>
            </div>
            <div class="col-sm-4">
                <label for="option">Options</label>
                <div class="controls" id="options-area">
                    <c:forEach items="${options}" var="option">
                    <c:choose>
                    <c:when test="${possible.contains(option)}">
                    <label class="custom-control custom-checkbox" id="option">
                        <span class="custom-control-indicator"></span>
                        <input type="checkbox" id="box${option.id}" name="options" value="${option.id}"
                               checked="checked">
                        <span class="custom-control-description">${option.name}</span>
                        </c:when>
                        <c:otherwise>
                        <label class="custom-control custom-checkbox" id="option">
                            <span class="custom-control-indicator"></span>
                            <input type="checkbox" id="box${option.id}" name="options" value="${option.id}">
                            <span class="custom-control-description">${option.name}</span>
                            </c:otherwise>
                            </c:choose>

                        </label>
                        </c:forEach>
                </div>

                <br>
                <button type="submit" class="btn btn-primary">
                    Update tariff
                </button>

            </div>
        </div>
        <br>
    </form>
</div>

</body>
</html>
