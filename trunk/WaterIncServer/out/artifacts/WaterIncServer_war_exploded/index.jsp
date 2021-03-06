<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/28/2017
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>NAOMI</title>
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/main.css">
    <link rel="stylesheet" href="resources/css/skin-blue-light.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="resources/font-awesome/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    <!-- iCheck -->
    <link rel="stylesheet" href="resources/plugins/iCheck/flat/blue.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="resources/plugins/morris/morris.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="resources/plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="resources/plugins/daterangepicker/daterangepicker.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- datatable -->
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/mycss.css">
    <link rel="shortcut icon" href="/resources/img/logo.png">
</head>

<body class="hold-transition skin-blue sidebar-mini" onload="loadAllOrder()">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>NMS</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>NAOMI</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="resources/img/male.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">Viet Vo Hoang</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="resources/img/male.jpg" class="img-circle" alt="User Image">

                                <p>
                                    Viet Vo Hoang - Administrator
                                    <small>Admin since June. 2017</small>
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="#" class="btn btn-default btn-flat">Profile</a>
                                </div>
                                <div class="pull-right">
                                    <a href="#" class="btn btn-default btn-flat" onclick="logout()">Sign out</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="resources/img/male.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Viet Vo Hoang</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="treeview active">
                    <a href="#" onclick="loadAllOrder()">
                        <i class="fa fa-newspaper-o"></i>
                        <span>Manage Oder</span>
                        <span class="pull-right-container">
                                <span class="pull-right-container"></span>
                            </span>
                    </a>
                </li>
                <li class="treeview">
                    <a href="#" onclick="loadAllUser()">
                        <i class="fa fa-user-secret"></i>
                        <span>Manage User</span>
                        <span class="pull-right-container">
                                <span class="pull-right-container"></span>
                            </span>
                    </a>
                </li>
                <li class="active treeview">
                    <a href="#" onclick="loadAllEmployee()">
                        <i class="fa fa-users"></i> <span>Manage Employee</span>
                        <span class="pull-right-container"></span>
                    </a>
                </li>
                <li class="treeview">
                    <a href="#" onclick="loadAllProduct()">
                        <i class="fa fa-cubes"></i>
                        <span>Manage Product</span>
                        <span class="pull-right-container">
                                <span class="pull-right-container"></span>
                            </span>
                    </a>
                </li>
                <li class="treeview">
                    <a href="#" onclick="loadAllSalaryAndBonuscoefficient()">
                        <i class="fa fa-cubes"></i>
                        <span>Manage Salary</span>
                        <span class="pull-right-container">
                                <span class="pull-right-container"></span>
                            </span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Dashboard
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- Main row -->
            <div class="row">
                <!-- Left col -->
                <section class="col-lg-8 connectedSortable">
                    <div class="col-lg-12 col-xs-12">
                        <!-- small box -->
                        <div class="small-box bg-green">
                            <div class="inner">
                                <h3 id="newOrderAmount">150</h3>

                                <p>New Orders</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-bag"></i>
                            </div>
                            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <h3 id="pageTitle"></h3>
                    </div>
                    <!-- ./col -->
                    <div class="col-md-12">
                        <!-- table -->
                        <table class="w3-table-all w3-hoverable" id="tableArea">
                            <thead>
                                <tr style="background-color: #00a65a; color: #FFF">
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="data">
                                <tr>
                                    <td id="status"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-12">
                        <!-- table -->
                        <table class="w3-table-all w3-hoverable" id="tableAreaBonus">
                            <thead>
                            <tr style="background-color: #00a65a; color: #FFF">
                                <th></th>
                            </tr>
                            </thead>
                            <tbody id="bonusData">
                            <tr>
                                <td id="bonus"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </section>

                <!-- /.Left col -->
                <!-- right col (We are only adding the ID to make the widgets sortable)-->
                <section class="col-lg-4 connectedSortable">

                    <!-- Custom tabs (Charts with tabs)-->
                    <div class="nav-tabs-custom">
                        <!-- Tabs within a box -->
                        <ul class="nav nav-tabs pull-right">
                            <li class="active"><a href="#revenue-chart" data-toggle="tab">Area</a></li>
                            <li><a href="#sales-chart" data-toggle="tab">Donut</a></li>
                            <li class="pull-left header"><i class="fa fa-inbox"></i> Sales</li>
                        </ul>
                        <div class="tab-content no-padding">
                            <!-- Morris chart - Sales -->
                            <div class="chart tab-pane active" id="revenue-chart"
                                 style="position: relative; height: 300px;"></div>
                            <div class="chart tab-pane" id="sales-chart"
                                 style="position: relative; height: 300px;"></div>
                        </div>
                    </div>
                    <!-- /.nav-tabs-custom -->


                    <!-- /.box -->

                    <!-- Calendar -->
                    <div class="box box-solid bg-green-gradient">
                        <div class="box-header">
                            <i class="fa fa-calendar"></i>

                            <h3 class="box-title">Calendar</h3>
                            <!-- tools box -->
                            <div class="pull-right box-tools">
                                <!-- button with a dropdown -->
                                <button type="button" class="btn btn-success btn-sm" data-widget="collapse"><i
                                        class="fa fa-minus"></i>
                                </button>
                            </div>
                            <!-- /. tools -->
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body no-padding">
                            <!--The calendar -->
                            <div id="calendar" style="width: 100%"></div>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->

                </section>
                <!-- right col -->
            </div>
            <!-- /.row (main row) -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
    immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<!-- Order Modal -->
<div id="addOrderModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <form id="addOrderForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">New Order</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group" style="display: none">
                        <label for="newOrderCusName"></label>
                        <input type="hidden" class="form-control" id="newOrderEmpId" placeholder="Name" name="empId"
                               value="1">
                    </div>
                    <div class="form-group">
                        <label for="newOrderCusName">Customer name</label>
                        <input type="text" class="form-control" id="newOrderCusName" placeholder="Name" name="cusName">
                    </div>
                    <div class="form-group">
                        <label for="newOrderCusPhone">Customer Phone</label>
                        <input type="text" class="form-control" id="newOrderCusPhone" placeholder="Phone"
                               name="cusPhone">
                    </div>
                    <div class="form-group">
                        <label for="newOrderCusAddress">Customer Address</label>
                        <input type="text" class="form-control" id="newOrderCusAddress" placeholder="Address"
                               name="cusAddress">
                    </div>
                    <div class="form-group">
                        <label for="newOrderCusAddress">Order status</label>
                        <select class="form-control" id="orderStatus" name="status">
                            <option value="0">Not confirm</option>
                            <option value="1">Confirmed</option>
                            <option value="2">Not delivered</option>
                            <option value="3">Finished</option>
                        </select>
                    </div>
                    <div style="border: 1px solid #cccccc; border-radius: 5px; padding: 7.5px;">
                    <form class="form-inline">
                        <div class="form-group">
                            <select class="form-control" id="newOrderProductList">
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="number" class="form-control" id="newOrderProductQuantity" placeholder="0">
                        </div>
                        <button type="button" onclick="addOrderItemToList()" class="btn btn-default">Add to cart
                        </button>
                    </form>
                    <table class="table table-responsive table-hover table-striped">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>ProductName</th>
                            <th>Quantity</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="cartData">

                        </tbody>
                    </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="addNewOrder()">Create
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>

    </div>
</div>

<!-- Add Product Modal -->
<div id="addProductModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <form id="addProductForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">New Product</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="newProductname">Name</label>
                        <input type="text" name="productName" class="form-control" id="newProductname" placeholder="Name">
                    </div>
                    <div class="form-group">
                        <label for="newProductQuantity">Quantity</label>
                        <input type="number" name="quantity" class="form-control" id="newProductQuantity" placeholder="Quantity">
                    </div>
                    <div class="form-group">
                        <label for="newProductPrice">Price</label>
                        <input type="number" name="price" class="form-control" id="newProductPrice" placeholder="Price">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="createProduct('#addProductModal')">Create</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>

    </div>
</div>

<!-- Add Employee Modal -->
<div id="addEmployeeModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <form id="addEmployeeForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">New Employee</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="newEmployeeName">Name</label>
                        <input type="text" name="name" class="form-control" id="newEmployeeName" placeholder="Name">
                    </div>
                    <div class="form-group">
                        <label for="newBaseSalary">Base salary</label>
                        <input type="number" name="baseSalary" class="form-control" id="newBaseSalary" placeholder="Base salary">
                    </div>
                    <div class="form-group">
                    <label for="updateEmployeeStatus">Title</label>
                    <select class="form-control" id="updateEmployeeStatus" name="titleName">
                        <option value="Manager">Manager</option>
                        <option value="Driver">Driver</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="createEmployee('#addEmployeeModal')">Create</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>

    </div>
</div>

<!-- Modal new User -->
<div id="addUserModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <form id="addUserForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">New User</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="newOrderCusName">Username</label>
                        <input type="text" class="form-control" id="newUserUsername" placeholder="Username"
                               name="username">
                    </div>
                    <div class="form-group">
                        <label for="newOrderCusPhone">Password</label>
                        <input type="password" class="form-control" id="newUserPassword" placeholder="Password" name="password">
                    </div>
                    <div class="form-group">
                        <label for="UserRole">User role</label>
                        <select class="form-control" id="UserRole" name="role">
                            <option value="ROLE_ADMIN">Admin</option>
                            <option value="ROLE_USER">Normal User</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="UserEnable">User enable</label>
                        <select class="form-control" id="UserEnable" name="enable">
                            <option value="1">Enable</option>
                            <option value="0">Disable</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="UserEmpId">User belong employee</label>
                        <select class="form-control" id="UserEmpId" name="empId">
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success" data-dismiss="modal" onclick="addNewUser()">Create
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div>

    </div>
</div>
<!-- ./Modal new User -->

<!-- jQuery 2.2.3 -->
<script src="resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!--<script src="js/jquery-3.1.1.min.js"></script>-->
<!-- jQuery UI 1.11.4 -->
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<!-- popup alert -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js"></script>

<!-- Morris.js charts -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="resources/plugins/morris/morris.min.js"></script>
<!-- Sparkline -->
<script src="resources/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jQuery Knob Chart -->
<script src="resources/plugins/knob/jquery.knob.js"></script>
<!-- daterangepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="resources/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="resources/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="resources/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="resources/js/app.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="resources/js/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="resources/js/demo.js"></script>
<!-- datatable -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.js"></script>


<script src="resources/js/Product.js"></script>
<script src="resources/js/Employee.js"></script>
<script src="resources/js/User.js"></script>
<script src="resources/js/SalaryAndBonuscoefficient.js"></script>
<script src="resources/js/Order.js"></script>
<script src="resources/js/OrderItem.js"></script>

</body>

</html>
