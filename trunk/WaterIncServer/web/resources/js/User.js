/**
 * Created by Asus on 7/14/2017.
 */

var logout = function () {
  $.ajax({
      url: '/logout',
      method: 'GET',
      success: function (data) {
          window.location.href = "/login.jsp";
      }
  });
};

var loadAllUser = function () {
    $('#pageTitle').text("User List");
    $.ajax({
        url: '/findAllUser',
        method: 'GET',
        success: function (data) {
            var table = $('#tableArea');
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableArea")) {
                $('#tableArea').DataTable().clear().destroy();
            }
            /**/
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableAreaBonus")) {
                $('#tableAreaBonus').DataTable().clear().destroy();
            }
            /**/
            $('#addNewDiv').remove();
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th data-field="username">Username</th>' +
                '<th data-field="role">Role</th>' +
                '<th data-field="enable">Enable</th>' +
                '<th data-field="name">Employee Name</th>' +
                '<th data-field="title">Employee Title</th>' +
                '<th id="editCol"></th>' +
                '<th id="removeCol"></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="data"/>')
            for (var i = 0; i < data.length; i++) {
                var isEnable = "Enable";
                if (data[i].enable === 0) {
                    isEnable = "Disable";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].username + '</td>'
                    + '<td>' + data[i].role + '</td>'
                    + '<td>' + isEnable + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.name + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.titleName + '</td>');
                row.append('<td><button class="btn btn-default" onclick="appendUpdateUserModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="appendDeleteUserModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addUserModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;' +
                '">Add new user&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            table.prev().after(div);
        }
    });
    $.ajax({
        url: "/getAllEmployee",
        method: "GET",
        success: function (data) {
            var selectEmp = $('#UserEmpId');
            selectEmp.empty();
            for (var i = 0; i < data.length; i++) {
                var option = $('<option value="' + data[i].id + '">' + data[i].name + ' - ' + data[i].titleName + '</option>');
                selectEmp.append(option);
            }
        }
    });
};

var appendDeleteUserModal = function (id) {
    var confirmModal = $('<div id="deleteModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeDeleteModal(\'#deleteModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Disable User</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>Are you sure?</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="disableUder(' + id + ', \'#deleteModal' + id + '\')">OK</button>' +
        '<button type="button" class="btn btn-danger" data-dismiss="modal" onclick="closeDeleteModal(\'#deleteModal' + id + '\')">Close</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(confirmModal);
};

var disableUder = function (id, modalId) {
    $.ajax({
        url: '/removeUser',
        method: 'POST',
        data: {
            'id': id,
        },
        success: function (data) {
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableArea")) {
                $('#tableArea').DataTable().clear().destroy();
            }
            /**/
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                var isEnable = "Enable";
                if (data[i].enable === 0) {
                    isEnable = "Disable";
                }
                row.append('<td>' + data[i].username + '</td>'
                    + '<td>' + data[i].role + '</td>'
                    + '<td>' + isEnable + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.name + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.titleName + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addUserModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;' +
                '">Add new user&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            $('#tableArea').prev().after(div);
        }
    });
    closeDeleteModal(modalId);
    //alert success
    $.bootstrapGrowl('Disable successful!',{
        type: 'success',
        delay: 2000,
    });
};

var addNewUser = function () {
    $.ajax({
        url: "/addUser",
        method: "POST",
        data: $('#addUserForm').serialize(),
        success: function (data) {
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableArea")) {
                $('#tableArea').DataTable().clear().destroy();
            }
            /**/
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                var isEnable = "Enable";
                if (data[i].enable === 0) {
                    isEnable = "Disable";
                }
                row.append('<td>' + data[i].username + '</td>'
                    + '<td>' + data[i].role + '</td>'
                    + '<td>' + isEnable + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.name + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.titleName + '</td>');
                row.append('<td><button class="btn btn-default" onclick="appendUpdateUserModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="appendDeleteUserModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addUserModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;' +
                '">Add new user&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            $('#tableArea').prev().after(div);
        }
    });
    //alert success
    $.bootstrapGrowl('Create successful!',{
        type: 'success',
        delay: 2000,
    });
};

var updateUser = function (id, modalId) {
    $.ajax({
        url: '/updateUser',
        method: 'POST',
        data: $('#updateUserForm').serialize(),
        success: function (data) {
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableArea")) {
                $('#tableArea').DataTable().clear().destroy();
            }
            /**/
            var table = $('#tableArea');
            $('#addNewDiv').remove();
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th data-field="username">Username</th>' +
                '<th data-field="role">Role</th>' +
                '<th data-field="enable">Enable</th>' +
                '<th data-field="name">Employee Name</th>' +
                '<th data-field="title">Employee Title</th>' +
                '<th id="editCol"></th>' +
                '<th id="removeCol"></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="data"/>')
            for (var i = 0; i < data.length; i++) {
                var isEnable = "Enable";
                if (data[i].enable === 0) {
                    isEnable = "Disable";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].username + '</td>'
                    + '<td>' + data[i].role + '</td>'
                    + '<td>' + isEnable + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.name + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.titleName + '</td>');
                row.append('<td><button class="btn btn-default" onclick="appendUpdateUserModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="appendDeleteUserModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addUserModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;' +
                '">Add new user&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            table.prev().after(div);
        }
    });
    closeUpdateModal(modalId);
    //alert success
    $.bootstrapGrowl('Update successful!',{
        type: 'success',
        delay: 2000,
    });
};


var appendUpdateUserModal = function (id) {
    var updateModal = $('<div id="updateUserModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<form id="updateUserForm">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeUpdateModal(\'#updateUserModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Update User</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="form-group">' +
        '<input type="hidden" name="id" class="form-control" id="updateId" value="' + id + '"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateUserName">Name</label>' +
        '<input type="text" name="userName" class="form-control" id="updateUserName" placeholder="Name"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateUserPassword">Password</label>' +
        '<input type="password" name="password" class="form-control" id="updateUserPassword" placeholder="Password"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="UserEnable">User enable</label>' +
        '<select class="form-control" id="UserEnable" name="enable">' +
        '<option value="1">Enable</option>' +
        '<option value="0">Disable</option>' +
        '</select>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateUserRole">Role</label>' +
        '<select class="form-control" id="updateUserRole" name="role">' +
        '<option value="ROLE_ADMIN">Admin</option>' +
        '<option value="ROLE_USER">Normal user</option>' +
        '</select>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="UserEmpId">User belong employee</label>' +
        '<select class="form-control" id="UserEmpIdUpdate" name="empId">' +
        '</select>' +
        '</div>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal" onclick="updateUser(' + id + ', \'#updateUserModal' + id + '\')">Update</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeUpdateModal(\'#updateUserModal' + id + '\')">Close</button>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(updateModal);
    $.ajax({
        url: "/getAllEmployee",
        method: "GET",
        success: function (data) {
            var selectEmp = $('#UserEmpIdUpdate');
            selectEmp.empty();
            for (var i = 0; i < data.length; i++) {
                var option = $('<option value="' + data[i].id + '">' + data[i].name + ' - ' + data[i].titleName + '</option>');
                selectEmp.append(option);
            }
        }
    });
    $.ajax({
        url: "/findUserById",
        method: "POST",
        data: {"id": id},
        success: function (data) {
            $('#updateUserName').val(data.username);
            $('#updateUserPassword').val(data.password);
        }
    });
};

var closeUpdateModal = function (modalId) {
    $(modalId).remove();
};