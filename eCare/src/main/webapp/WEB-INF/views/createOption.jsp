<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add option</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
    <link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <link rel="stylesheet" href="/src/main/webapp/resources/css/css1.css" type="text/css">


    <style>
        input:invalid:not(:placeholder-shown) {
            border-color: #ff0000;
        }

        input:valid:not(:placeholder-shown) {
            border: 2px solid #000000;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#forTariffs').multiselect();
        });
    </script>
    <title>New option</title>
</head>
<body>
<jsp:include page="navbarAdmin.jsp"/>
<div id="page-container">
    <div id="content-wrap">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Add tariff</div>
                        <div class="card-body">
                            <form name="option" action="/admin/addOption" method="post">
                                <fieldset class="form-group">
                                    <label for="name">Name</label>
                                    <input type="text" class="form-control" name="name" placeholder="Name option"
                                           id="name">
                                </fieldset>
                                <fieldset class="form-group">
                                    <label for="cost">Cost</label>
                                    <input type="text" class="form-control" name="cost" placeholder="Cost option"
                                           id="cost"
                                           required pattern=\d+(\.\d{2})?>
                                </fieldset>
                                <fieldset class="form-group">
                                    <label for="connectCost">Cost connection</label>
                                    <input type="text" class="form-control" name="connectCost"
                                           placeholder="Cost connection for option"
                                           id="connectCost"
                                           required pattern=\d+(\.\d{2})?>
                                </fieldset>
                                <fieldset class="form-group">
                                    <label for="description">Description</label>
                                    <input type="text" class="form-control" name="description" placeholder="Description"
                                           id="description">
                                </fieldset>
                                <br>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="panel-heading">Available for there tariffs</div>
                                        <div class="panel-body boxes">
                                            <div class="controls">
                                                <select required id="forTariffs" size="4" style="width: 100%" name="forTariffs"
                                                        multiple="multiple"
                                                        onchange="this.value">
                                                    <c:forEach var="tariff" items="${tariff}">
                                                        <option name="forTariffs"
                                                                value="${tariff.id}">${tariff.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="panel-heading">Required from</div>
                                        <div class="panel-body boxes">
                                            <div class="controls">
                                                <select id="requiredFrom" size="8" style="width: 100%" name="requiredFrom"
                                                        class="js-example-basic-multiple" multiple="multiple"
                                                        onchange="this.value">
                                                    <c:forEach var="option" items="${option}">
                                                        <option name="requiredFrom"
                                                                value="${option.id}">${option.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="panel-heading">Incompatible with</div>
                                        <div class="panel-body boxes">
                                            <div class="controls">
                                                <select id="forbiddenWith" size="8" style="width: 100%" name="forbiddenWith"
                                                        class="js-example-basic-multiple" multiple="multiple"
                                                        onchange="this.value">
                                                    <c:forEach var="option" items="${option}">
                                                        <option name="forbiddenWith"
                                                                value="${option.id}">${option.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>


                                            <div class="panel-body">
                                                <div class="form-group">
                                                    <!-- Button -->
                                                    <div class="controls">
                                                        <input type="submit" name="submit" value="Add new Option"
                                                               class="btn btn-success">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>
