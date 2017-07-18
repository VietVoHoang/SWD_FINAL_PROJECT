/**
 * Created by hongducphan on 7/17/17.
 */

$('#tableAreaBonus').hide();

var loadAllSalaryAndBonuscoefficient = function () {
    $('#tableAreaBonus').show();
    $.ajax({
        url: '/getAllSalary',
        method: 'GET',
        success: function (data) {
            console.log(data);
            var table = $('#tableArea');
            /* reset table to reinitialize */
            if ($.fn.DataTable.isDataTable("#tableArea")) {
                $('#tableArea').DataTable().clear().destroy();
            }
            /**/
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th>Salary by day</th>' +
                '<th>Salary by hour</th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="data"/>');
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                row.append('<td>' + data[i].salaryByDay + '</td>'
                    + '<td>' + data[i].salaryByHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateSalaryAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
        }
    });

    $.ajax({
        url: '/getAllBonuscoefficient',
        method: 'GET',
        success: function (data) {
            console.log(data);
            var table = $('#tableAreaBonus');
            table.empty();

            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th>Coefficient by day</th>' +
                '<th>Coefficient by hour</th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="bonusData"/>');
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                row.append('<td>' + data[i].coefficientByDay + '</td>'
                    + '<td>' + data[i].coefficientByHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateBonusAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
        }
    });
};

var updateBonuscoefficient = function (id, modalId) {
    $.ajax({
        url: '/updateBonuscoefficient',
        method: 'POST',
        data: $('#updateBonusForm').serialize(),
        success: function (data) {
            console.log(data);
            var table = $('#tableAreaBonus');
            table.empty();

            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th>Coefficient by day</th>' +
                '<th>Coefficient by hour</th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="bonusData"/>');
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                row.append('<td>' + data[i].coefficientByDay + '</td>'
                    + '<td>' + data[i].coefficientByHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateBonusAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
        }
    });
    closeUpdateModal(modalId);
};

var updateBonusAppendModal = function (id) {
    var updateModal = $('<div id="updateBonusModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<form id="updateBonusForm">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeUpdateModal(\'#updateBonusModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Update bonus coefficient</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="form-group">' +
        '<input type="hidden" name="id" class="form-control" id="updateId" value="' + id + '"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateCoefficientByDay">Coefficient by day</label>' +
        '<input type="number" name="coefficientByDay" class="form-control" id="updateCoefficientByDay" placeholder="Coefficient by day"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateCoefficientByHour">Coefficient by hour</label>' +
        '<input type="number" name="coefficientByHour" class="form-control" id="updateCoefficientByHour" placeholder="Coefficient by hour"/>' +
        '</div>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal" onclick="updateBonuscoefficient(' + id + ', \'#updateBonusModal' + id + '\')">Update</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeUpdateModal(\'#updateBonusModal' + id + '\')">Close</button>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(updateModal);
};

var updateSalary = function (id, modalId) {
    $.ajax({
        url: '/updateSalary',
        method: 'POST',
        data: $('#updateSalaryForm').serialize(),
        success: function (data) {
            console.log(data);
            var table = $('#tableAreaBonus');
            table.empty();

            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th>Salary by day</th>' +
                '<th>Salary by hour</th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="data"/>');
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                row.append('<td>' + data[i].salaryByDay + '</td>'
                    + '<td>' + data[i].salaryByHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateSalaryAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
        }
    });
    closeUpdateModal(modalId);
};

var updateSalaryAppendModal = function (id) {
    var updateModal = $('<div id="updateSalaryModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<form id="updateSalaryForm">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeUpdateModal(\'#updateSalaryModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Update salary</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="form-group">' +
        '<input type="hidden" name="id" class="form-control" id="updateId" value="' + id + '"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateSalaryByDay">Salary by day</label>' +
        '<input type="number" name="salaryByDay" class="form-control" id="updateSalaryByDay" placeholder="Salary by day"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateSalaryByHour">Salary by hour</label>' +
        '<input type="number" name="salaryByHour" class="form-control" id="updateSalaryByHour" placeholder="Salary by hour"/>' +
        '</div>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal" onclick="updateSalary(' + id + ', \'#updateSalaryModal' + id + '\')">Update</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeUpdateModal(\'#updateSalaryModal' + id + '\')">Close</button>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(updateModal);
};

var closeUpdateModal = function (modalId) {
    $(modalId).remove();
};