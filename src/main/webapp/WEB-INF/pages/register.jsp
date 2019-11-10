<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags-->
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="au theme template">
  <meta name="author" content="Hau Nguyen">
  <meta name="keywords" content="au theme template">

  <!-- Title Page-->
  <title>Register</title>

  <!-- Fontfaces CSS-->
  <link href="assets/css/font-face.css" rel="stylesheet" media="all">
  <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
  <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
  <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

  <!-- Bootstrap CSS-->
  <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

  <!-- Vendor CSS-->
  <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
  <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
  <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
  <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
  <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
  <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
  <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

  <!-- Main CSS-->
  <link href="assets/css/theme.css" rel="stylesheet" media="all">

</head>

<body class="animsition">
  <div class="page-wrapper">
    <div class="page-content--bge5">
      <div class="container">
        <div class="login-wrap">
          <c:if test="${not empty error}">
            <div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
                ${error}
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
          </c:if>
          <div class="login-content">
            <div class="login-logo">
              <a href="#">
                <img src="assets/images/icon/logo.png" alt="CoolAdmin">
              </a>
            </div>
            <div class="login-form">
              <form data-toggle="validator" role="form" method="post">
                <div class="row">
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label" for="lastname">Lastname</label>
                      <input id="lastname"
                             class="au-input au-input--full form-control"
                             type="text"
                             name="lastname"
                             placeholder="Lastname"
                             required>
                      <div class="text-danger help-block with-errors"></div>
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label" for="firstname">Firstname</label>
                      <input id="firstname"
                             class="au-input au-input--full form-control"
                             type="text"
                             name="firstname"
                             placeholder="Firstname"
                             required>
                      <div class="text-danger help-block with-errors"></div>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="email">Email Address</label>
                  <input id="email"
                         class="au-input au-input--full form-control"
                         type="email"
                         name="email"
                         placeholder="Email"
                         required>
                  <div class="text-danger help-block with-errors"></div>
                </div>
                <div class="form-group">
                  <label class="control-label" for="password">Password</label>
                  <input id="password"
                         class="au-input au-input--full form-control"
                         type="password"
                         name="password"
                         placeholder="Password"
                         data-minlength="5"
                         data-error="Le mot de passe doit avoir au minimum 5 caractères"
                         required>
                  <div class="text-danger help-block with-errors"></div>
                </div>
                <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">register</button>
              </form>
              <div class="register-link">
                <p>
                  Already have account?
                  <a href="<%=request.getContextPath() + "/login"%>">Sign In</a>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Jquery JS-->
  <script src="vendor/jquery-3.2.1.min.js"></script>
  <!-- Bootstrap JS-->
  <script src="vendor/bootstrap-4.1/popper.min.js"></script>
  <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
  <!-- Vendor JS       -->
  <script src="vendor/slick/slick.min.js"></script>
  <script src="vendor/wow/wow.min.js"></script>
  <script src="vendor/animsition/animsition.min.js"></script>
  <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
  <script src="vendor/bootstrap-validate/validate.min.js"></script>
  <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
  <script src="vendor/counter-up/jquery.counterup.min.js"></script>
  <script src="vendor/circle-progress/circle-progress.min.js"></script>
  <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
  <script src="vendor/chartjs/Chart.bundle.min.js"></script>
  <script src="vendor/select2/select2.min.js"></script>

    <!-- Main JS-->
  <script src="assets/js/main.js"></script>
</body>

</html>
<!-- end document-->
