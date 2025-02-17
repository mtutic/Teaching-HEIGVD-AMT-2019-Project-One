<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
  <title>Movie history</title>

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
  <link href="vendor/datatables/datatables.min.css" rel="stylesheet" media="all">

  <!-- Main CSS-->
  <link href="assets/css/theme.css" rel="stylesheet" media="all">
</head>

<body>
  <div class="page-wrapper">
    <!-- HEADER MOBILE-->
    <header class="header-mobile d-block d-lg-none">
      <div class="header-mobile__bar">
        <div class="container-fluid">
          <div class="header-mobile-inner">
            <a class="logo" href="index.jsp">
              <img src="assets/images/icon/logo.png" alt="CoolAdmin" />
            </a>
            <button class="hamburger hamburger--slider" type="button">
              <span class="hamburger-box">
                <span class="hamburger-inner"></span>
              </span>
            </button>
          </div>
        </div>
      </div>
      <nav class="navbar-mobile">
        <div class="container-fluid">
          <ul class="navbar-mobile__list list-unstyled">
            <li class="active has-sub">
              <a class="js-arrow" href="#">
                <i class="fas fa-home"></i>Movies you've seen</a>
            </li>
            <li>
              <a href="/project-one/movies">
                <i class="fas fa-video"></i>All movies</a>
            </li>
          </ul>
        </div>
      </nav>
    </header>
    <!-- END HEADER MOBILE-->

    <!-- MENU SIDEBAR-->
    <aside class="menu-sidebar d-none d-lg-block">
      <div class="logo">
        <a href="#">
          <img src="assets/images/icon/logo.png" alt="Cool Admin" />
        </a>
      </div>
      <div class="menu-sidebar__content js-scrollbar1">
        <nav class="navbar-sidebar">
          <ul class="list-unstyled navbar__list">
            <li class="active has-sub">
              <a class="js-arrow" href="#">
                <i class="fas fa-home"></i>Movies you've seen</a>
            </li>
            <li>
              <a href="/project-one/movies">
                <i class="fas fa-video"></i>All movies</a>
            </li>
          </ul>
        </nav>
      </div>
    </aside>
    <!-- END MENU SIDEBAR-->

    <!-- PAGE CONTAINER-->
    <div class="page-container">
      <!-- HEADER DESKTOP-->
      <header class="header-desktop">
        <div class="section__content section__content--p30">
          <div class="container-fluid">
            <div class="header-wrap">
              <form class="form-header" action="" method="POST">
              </form>
              <div class="header-button">
                <div class="account-wrap">
                  <div class="account-item clearfix js-item-menu">
                    <div class="image">
                      <img src="assets/images/icon/avatar.png" alt="${user.firstName}" />
                    </div>
                    <div class="content">
                      <a class="js-acc-btn" href="#">${user.name()}</a>
                    </div>
                    <div class="account-dropdown js-dropdown">
                      <div class="info clearfix">
                        <div class="image">
                          <a href="#">
                            <img src="assets/images/icon/avatar.png" alt="${user.firstName}" />
                          </a>
                        </div>
                        <div class="content">
                          <h5 class="name">
                            <a href="#">${user.name()}</a>
                          </h5>
                          <span class="email">${user.email}</span>
                        </div>
                      </div>
                      <div class="account-dropdown__footer">
                        <a href="<%=request.getContextPath() + "/profile"%>">
                          <i class="zmdi zmdi-edit"></i>Edit profile</a>
                      </div>
                      <div class="account-dropdown__footer">
                        <a href="<%=request.getContextPath() + "/logout"%>">
                          <i class="zmdi zmdi-power"></i>Logout</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>
      <!-- HEADER DESKTOP-->

      <!-- MAIN CONTENT-->
      <div class="main-content">
        <div class="section__content section__content--p30">
          <div class="container-fluid">
            <c:if test="${not empty error}">
              <div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
                  ${error}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            </c:if>
            <c:if test="${not empty success}">
              <div class="sufee-alert alert with-close alert-success alert-dismissible fade show">
                  ${success}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            </c:if>
            <%
              request.getSession().removeAttribute("success");
              request.getSession().removeAttribute("error");
            %>
            <div class="row">
              <div class="col-lg-12">
                <div class="card">
                  <div class="card-header">
                    Movies you've seen
                  </div>
                  <div class="card-body card-block" style="background-color: #ebebeb;">
                    <div class="table-responsive m-b-40">
                      <table id="seenMovies" class="table table-borderless table-striped table-earning">
                        <thead>
                          <tr>
                            <th>movie ID</th>
                            <th>title</th>
                            <th>year</th>
                            <th>Action</th>
                          </tr>
                        </thead>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="copyright">
                  <p>Copyright © 2019 Movie history. All rights reserved.</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- END MAIN CONTENT-->
      <!-- END PAGE CONTAINER-->
    </div>
  </div>

  <!-- Jquery JS -->
  <script src="vendor/jquery-3.2.1.min.js"></script>

  <!-- Bootstrap JS -->
  <script src="vendor/bootstrap-4.1/popper.min.js"></script>
  <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>

  <!-- Vendor JS -->
  <script src="vendor/slick/slick.min.js"></script>
  <script src="vendor/wow/wow.min.js"></script>
  <script src="vendor/animsition/animsition.min.js"></script>
  <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
  <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
  <script src="vendor/counter-up/jquery.counterup.min.js"></script>
  <script src="vendor/circle-progress/circle-progress.min.js"></script>
  <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
  <script src="vendor/chartjs/Chart.bundle.min.js"></script>
  <script src="vendor/select2/select2.min.js"></script>
  <script src="vendor/datatables/datatables.min.js"></script>

  <!-- Main JS -->
  <script src="assets/js/main.js"></script>

  <!-- Custom JS -->
  <script>
    $(document).ready(function () {
      $('#seenMovies').DataTable({
        processing: true,
        serverSide: true,
        ajax: {
          url: '<%=request.getContextPath() + "/"%>',
          type: 'POST'
        },
        columns: [{
          searchable: false,
          data: "id",
          mData: "id"
        }, {
          data: "title",
          mData: "title"
        }, {
          searchable: false,
          data: "year",
          mdata: "year"
        }, {
          data: null,
          searchable: false,
          render: function(data, type, row) {
            var url = '<%=request.getContextPath()%>' + "/delete?id=" + row.id;
            return '<button ' +
              'type="button" ' +
              'class="btn btn-danger btn-sm" ' +
              'style="padding: 0 5px 0 5px"' +
              'onclick="location.href=\'' + url + '\'">delete</button>';
          }
        }]
      });
    });
  </script>
</body>

</html>
<!-- end document-->
