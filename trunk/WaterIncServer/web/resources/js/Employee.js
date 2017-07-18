/**
 * Created by hongducphan on 7/16/17.
 */

var salaryByDay, salaryByHour, coefficientByDay, coefficientByHour;

var calculateSalary = function (baseSalary, bonusDay, dayOff, hourOff, bonusHour) {
    return baseSalary - (salaryByDay * dayOff) - (salaryByHour * hourOff) + (salaryByDay * bonusDay) + (salaryByHour * bonusHour);
};

var loadAllEmployee = function () {
    $('#pageTitle').text("Employee List");
    $('#tableAreaBonus').hide();
    $.ajax({
        url: '/getAllSalary',
        method: 'GET',
        success: function (data) {
            console.log(data);
            salaryByDay = data[0].salaryByDay;
            salaryByHour = data[0].salaryByHour;
        }
    });

    $.ajax({
        url: '/getAllBonuscoefficient',
        method: 'GET',
        success: function (data) {
            console.log(data);
            coefficientByDay = data[0].coefficientByDay;
            coefficientByHour = data[0].coefficientByHour;
        }
    });

    $.ajax({
        url: '/getAllEmployee',
        method: 'GET',
        success: function (data) {
            console.log(data);
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
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th>Name</th>' +
                '<th>Status</th>' +
                '<th>Title</th>' +
                '<th>Day Off</th>' +
                '<th>Bonus day</th>' +
                '<th>Hour off</th>' +
                '<th>Bonus hour</th>' +
                '<th>Salary</th>' +
                '<th></th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="data"/>');
            for (var i = 0; i < data.length; i++) {
                var salary = calculateSalary(data[i].baseSalary, data[i].bonusDay, data[i].dayOff, data[i].hourOff, data[i].bonusHour);
                var status = data[i].status;
                if (status == '1') {
                    status = "Working";
                } else {
                    status = "Inactivity";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].name + '</td>'
                    + '<td>' + status + '</td>'
                    + '<td>' + data[i].titleName + '</td>'
                    + '<td>' + data[i].dayOff + '</td>'
                    + '<td>' + data[i].bonusDay + '</td>'
                    + '<td>' + data[i].hourOff + '</td>'
                    + '<td>' + data[i].bonusHour + '</td>'
                    + '<td>' + salary + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addEmployeeModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;">Add new employee&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            table.prev().remove();
            table.prev().after(div);
        }
    })
};

var deleteEmployeeAppendModal = function (id) {
    var confirmModal = $('<div id="deleteModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeDeleteModal(\'#deleteModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Delete</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>Are you sure?</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="removeEmployee(' + id + ', \'#deleteModal' + id + '\')">Yes</button>' +
        '<button type="button" class="btn btn-danger" data-dismiss="modal" onclick="closeDeleteModal(\'#deleteModal' + id + '\')">No</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(confirmModal);
};

var closeDeleteEmployeeModal = function (modalId) {
    $(modalId).remove();
};

var updateEmployeeAppendModal = function (id) {
    var updateModal = $('<div id="updateEmployeeModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<form id="updateEmployeeForm">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeUpdateModal(\'#updateEmployeeModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Update Employee</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="form-group">' +
        '<input type="hidden" name="id" class="form-control" id="updateId" value="' + id + '"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeName">Name</label>' +
        '<input type="text" name="name" class="form-control" id="updateEmployeeName" placeholder="Name"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeStatus">Status</label>' +
        '<select class="form-control" id="updateEmployeeStatus" name="status">' +
        '<option value="0">Inactivity</option>' +
        '<option value="1">Working</option>' +
        '</select>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeDayOff">Day off</label>' +
        '<input type="number" name="dayOff" class="form-control" id="updateEmployeeDayOff" placeholder="Day off"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeBaseSalary">Base salary</label>' +
        '<input type="number" name="baseSalary" class="form-control" id="updateEmployeeBaseSalary" placeholder="Base salary"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeTitle">Title</label>' +
        '<select class="form-control" id="updateEmployeeStatus" name="titleName">' +
        '<option value="Manager">Manager</option>' +
        '<option value="Driver">Driver</option>' +
        '</select>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeBonusDay">Bonus day</label>' +
        '<input type="number" name="bonusDay" class="form-control" id="updateEmployeeBonusDay" placeholder="Bonus day"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeHourOff">Hour off</label>' +
        '<input type="number" name="hourOff" class="form-control" id="updateEmployeeHourOff" placeholder="Hour off"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateEmployeeBonusHour">Bonus hour</label>' +
        '<input type="number" name="bonusHour" class="form-control" id="updateEmployeeBonusHour" placeholder="Bonus hour"/>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal" onclick="updateEmployee(' + id + ', \'#updateEmployeeModal' + id + '\')">Update</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeUpdateModal(\'#updateEmployeeModal' + id + '\')">Close</button>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(updateModal);
    $.ajax({
        url: "/findEmpById",
        method: "POST",
        data: {"id": id},
        success: function (data) {
            $('#updateEmployeeName').val(data.name);
            $('#updateEmployeeDayOff').val(data.dayOff);
            $('#updateEmployeeBaseSalary').val(data.baseSalary);
            $('#updateEmployeeBonusDay').val(data.bonusDay);
            $('#updateEmployeeHourOff').val(data.hourOff);
            $('#updateEmployeeBonusHour').val(data.bonusHour);
        }
    });
};

var closeUpdateEmployeeModal = function (modalId) {
    $(modalId).remove();
};

var updateEmployee = function (id, modalId) {
    $.ajax({
        url: '/updateEmployee',
        method: 'POST',
        data: $('#updateEmployeeForm').serialize(),
        success: function (data) {
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableArea")) {
                $('#tableArea').DataTable().clear().destroy();
            }
            /**/
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                var status = data[i].status;
                if (status == '1') {
                    status = "Working";
                } else {
                    status = "Inactivity";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].name + '</td>'
                    + '<td>' + status + '</td>'
                    + '<td>' + data[i].dayOff + '</td>'
                    + '<td>' + data[i].baseSalary + '</td>'
                    + '<td>' + data[i].titleName + '</td>'
                    + '<td>' + data[i].bonusDay + '</td>'
                    + '<td>' + data[i].hourOff + '</td>'
                    + '<td>' + data[i].bonusHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
        }
    });
    closeUpdateEmployeeModal(modalId);
};

var removeEmployee = function (id, modalId) {
    $.ajax({
        url: '/removeEmployee',
        method: 'POST',
        data: {
            "id": id
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
                var status = data[i].status;
                if (status == '1') {
                    status = "Working";
                } else {
                    status = "Inactivity";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].name + '</td>'
                    + '<td>' + status + '</td>'
                    + '<td>' + data[i].dayOff + '</td>'
                    + '<td>' + data[i].baseSalary + '</td>'
                    + '<td>' + data[i].titleName + '</td>'
                    + '<td>' + data[i].bonusDay + '</td>'
                    + '<td>' + data[i].hourOff + '</td>'
                    + '<td>' + data[i].bonusHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
        }
    });
    closeDeleteEmployeeModal(modalId);
};

var createEmployee = function () {
    $.ajax({
        url: '/addEmployee',
        method: 'POST',
        data: $('#addEmployeeForm').serialize(),
        success: function (data) {
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableArea")) {
                $('#tableArea').DataTable().clear().destroy();
            }
            /**/
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                var status = data[i].status;
                if (status == '1') {
                    status = "Working";
                } else {
                    status = "Inactivity";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].name + '</td>'
                    + '<td>' + status + '</td>'
                    + '<td>' + data[i].dayOff + '</td>'
                    + '<td>' + data[i].baseSalary + '</td>'
                    + '<td>' + data[i].titleName + '</td>'
                    + '<td>' + data[i].bonusDay + '</td>'
                    + '<td>' + data[i].hourOff + '</td>'
                    + '<td>' + data[i].bonusHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteEmployeeAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
        }
    });
};