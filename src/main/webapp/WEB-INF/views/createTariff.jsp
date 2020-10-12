<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add tariff</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <style>
        input:invalid:not(:placeholder-shown) {border-color: #ff0000;}
        input:valid:not(:placeholder-shown) {border: 2px solid #000000;}
    </style>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div class="container-fluid">
    <form name="tariff" action="/admin/addNewTariff" method="post">
        <fieldset class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" name="name" placeholder="Name tariff" id="name"
                   required pattern=[A-Z][a-z]+>
        </fieldset>
        <fieldset class="form-group">
            <label for="cost">Cost</label>
            <input type="text" class="form-control" name="cost" placeholder="Cost tariff" id="cost"
                   required pattern=\d+(\.\d{2})?>
        </fieldset>
        <fieldset class="form-group">
            <label for="description">Description</label>
            <input type="text" class="form-control" name="Description" placeholder="Description" id="description">
        </fieldset>
        <br>


<label for="option" class="col-sm">Options</label>
<div class="col-sm">
<div class="controls" id="options-area">
<c:forEach items="${options}" var="option">
    <label class="custom-control custom-checkbox" id="option">
    <input type="checkbox" id="box${option.id}" name="options" value="${option.id}">
    <span class="custom-control-indicator"></span>
    <span class="custom-control-description">${option.name}</span>

    </label>
</c:forEach>
    <input type="submit" name="submit" value="Add tariff" class="btn btn-success">
</div>
</div>
    </form>
</div>
</body>
</html>

