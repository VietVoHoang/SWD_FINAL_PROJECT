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
                row.append('<td><button class="btn btn-default" onclick="updateAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
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
                '<th>Salary by day</th>' +
                '<th>Salary by hour</th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="bonusData"/>');
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                row.append('<td>' + data[i].coefficientByDay + '</td>'
                    + '<td>' + data[i].coefficientByHour + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
        }
    });
};

var updateAppendModal = function (id) {
    var updateModal = $('<div id="updateBonusModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<form id="updateBonusForm">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeUpdateModal(\'#updateBonusModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Update Product</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="form-group">' +
        '<input type="hidden" name="id" class="form-control" id="updateId" value="' + id + '"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateProductname">Name</label>' +
        '<input type="text" name="productName" class="form-control" id="updateProductname" placeholder="Name"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateProductQuantity">Quantity</label>' +
        '<input type="number" name="quantity" class="form-control" id="updateProductQuantity" placeholder="Quantity"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateProductPrice">Price</label>' +
        '<input type="number" name="price" class="form-control" id="updateProductPrice" placeholder="Price"/>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="updateProductStatus">Status</label>' +
        '<select class="form-control" id="updateProductStatus" name="status">' +
        '<option value="0">Not for sale</option>' +
        '<option value="1">Stocking</option>' +
        '</select>' +
        '</div>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal" onclick="updateProduct(' + id + ', \'#updateBonusModal' + id + '\')">Update</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeUpdateModal(\'#updateBonusModal' + id + '\')">Close</button>' +
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