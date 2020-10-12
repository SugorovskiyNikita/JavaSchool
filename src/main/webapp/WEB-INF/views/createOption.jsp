<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add option</title>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <style>
        input:invalid:not(:placeholder-shown) {border-color: #ff0000;}
        input:valid:not(:placeholder-shown) {border: 2px solid #000000;}
    </style>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container-fluid">
    <form name="option" action="/addOption" method="post">
            <fieldset class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" name="name" placeholder="Name option" id="name">
            </fieldset>
            <fieldset class="form-group">
                <label for="cost">Cost</label>
                <input type="text" class="form-control" name="cost" placeholder="Cost option" id="cost"
                       required pattern=\d+(\.\d{2})?>
            </fieldset>
            <fieldset class="form-group">
                <label for="connectCost">Cost connection</label>
                <input type="text" class="form-control" name="connectCost" placeholder="Cost connection for option" id="connectCost"
                       required pattern=\d+(\.\d{2})?>
            </fieldset>
            <fieldset class="form-group">
                <label for="description">Description</label>
                <input type="text" class="form-control" name="description" placeholder="Description" id="description">
            </fieldset><br>
    <div class="row">
        <div class="col-lg-12">
                <div class="panel-heading">Available for there tariffs</div>
                <div class="panel-body boxes">
                    <div class="controls">
                        <select required id="forTariffs" style="width: 100%" name="forTariffs" class="js-example-basic-multiple" multiple="multiple" onchange="this.value">
                            <c:forEach var="tariff" items="${tariff}">
                                <option name="forTariffs" value="${tariff.id}">${tariff.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="panel-heading">Required from</div>
                <div class="panel-body boxes">
                    <div class="controls">
                        <select id="requiredFrom" style="width: 100%" name="requiredFrom" class="js-example-basic-multiple" multiple="multiple" onchange="this.value">
                            <c:forEach var="option" items="${option}">
                                <option name="requiredFrom" value="${option.id}">${option.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="panel-heading">Incompatible with</div>
                <div class="panel-body boxes">
                    <div class="controls">
                        <select id="forbiddenWith" style="width: 100%" name="forbiddenWith" class="js-example-basic-multiple" multiple="multiple" onchange="this.value">
                            <c:forEach var="option" items="${option}">
                                <option name="forbiddenWith" value="${option.id}">${option.name}</option>
                            </c:forEach>
                        </select>
                    </div>


            <div class="panel-body">
                <div class="form-group">
                    <!-- Button -->
                    <div class="controls">
                        <input type="submit" name="submit" value="Add new Option" class="btn btn-success">
                    </div>
                </div>
            </div>
</div>
</form>
</div>
</body>
</html>
