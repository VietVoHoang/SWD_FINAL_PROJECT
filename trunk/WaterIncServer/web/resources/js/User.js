/**
 * Created by Asus on 7/14/2017.
 */
var loadAllUser = function () {
    $.ajax({
        url: '/findAllUser',
        method: 'GET',
        success: function (data) {
            var table = $('#tableArea');
            $('#addNewDiv').remove();
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th data-field="id">Id</th>' +
                '<th data-field="username">Username</th>' +
                '<th data-field="password">Password</th>' +
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
                row.append('<td>' + data[i].id + '</td>'
                    + '<td>' + data[i].username + '</td>'
                    + '<td>' + data[i].password + '</td>'
                    + '<td>' + data[i].role + '</td>'
                    + '<td>' + isEnable + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.name + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.titleName + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
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
        url: "/findAllEmp",
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
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="disableUder(' + id + ', \'#deleteModal' + id + '\')">Oke</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeDeleteModal(\'#deleteModal' + id + '\')">Close</button>' +
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
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                var isEnable = "Enable";
                if (data[i].enable === 0) {
                    isEnable = "Disable";
                }
                row.append('<td>' + data[i].id + '</td>'
                    + '<td>' + data[i].username + '</td>'
                    + '<td>' + data[i].password + '</td>'
                    + '<td>' + data[i].role + '</td>'
                    + '<td>' + isEnable + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.name + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.titleName + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
        }
    });
    closeDeleteModal(modalId)
};

var addNewUser = function () {
    $.ajax({
        url: "/addUser",
        method: "POST",
        data: $('#addUserForm').serialize(),
        success: function (data) {
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                var isEnable = "Enable";
                if (data[i].enable === 0) {
                    isEnable = "Disable";
                }
                row.append('<td>' + data[i].id + '</td>'
                    + '<td>' + data[i].username + '</td>'
                    + '<td>' + data[i].password + '</td>'
                    + '<td>' + data[i].role + '</td>'
                    + '<td>' + isEnable + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.name + '</td>'
                    + '<td>' + data[i].employeesByEmployeeId.titleName + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
        }
    })
}

