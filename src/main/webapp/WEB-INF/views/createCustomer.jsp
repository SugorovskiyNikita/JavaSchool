<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <style>
        input:invalid:not(:placeholder-shown) {border-color: #ff0000;}
        input:valid:not(:placeholder-shown) {border: 3px solid #000000;}
    </style>
<body>
<div class="cotainer">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Add customer</div>
                    <div class="card-body">
                        <form name="customer" action="/addCustomer" method="post">
                            <div class="form-group row">
                                <label for="Name" class="col-md-4 col-form-label text-md-right">Name</label>
                                <div class="col-md-6">
                                    <input type="text" id="name" class="form-control" name="name"
                                           placeholder="Your name" required pattern=[A-Z][a-z]+>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="surname" class="col-md-4 col-form-label text-md-right">Surname</label>
                                <div class="col-md-6">
                                    <input type="text" id="surname" class="form-control" name="surname"
                                           required pattern=[A-Z][a-z]+ placeholder="Your surname">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="dateOfBirth" class="col-md-4 col-form-label text-md-right">Birthday</label>
                                <div class="col-md-6">
                                    <input type="date" dataformatas="yyyy-MM-dd" id="dateOfBirth" class="form-control" name="dateOfBirth">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="passportNumber" class="col-md-4 col-form-label text-md-right">Passport number</label>
                                <div class="col-md-6">
                                    <input type="text" id="passportNumber" class="form-control" name="passportNumber"
                                           placeholder="Passport Number">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="passportData" class="col-md-4 col-form-label text-md-right">Passport data</label>
                                <div class="col-md-6">
                                    <input type="text" id="passportData" class="form-control" name="passportData"
                                           placeholder="Passport Data">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="address" class="col-md-4 col-form-label text-md-right">Address</label>
                                <div class="col-md-6">
                                    <input type="text" id="address" class="form-control" name="address"
                                           placeholder="Address">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email" class="col-md-4 col-form-label text-md-right">Email</label>
                                <div class="col-md-6">
                                    <input type="email" id="email" class="form-control" name="email"
                                           placeholder="Your email">
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Add customer
                                </button>
                            </div>
                        </form>
                    </div>

                </div>
        </div>
    </div>
</div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
