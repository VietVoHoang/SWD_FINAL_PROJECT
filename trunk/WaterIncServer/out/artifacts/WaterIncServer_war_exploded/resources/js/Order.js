/**
 * Created by Asus on 7/12/2017.
 */

var loadAllOrder = function () {
    $.ajax({
        url: '/findAllOrder',
        method: 'GET',
        success: function (data) {
            var noNewOrder = 0;
            var table = $('#tableArea');
            $('#addNewDiv').remove();
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th data-field="id">Id</th>' +
                '<th data-field="cusName">Customer name</th>' +
                '<th data-field="cusPhone">Customer phone</th>' +
                '<th data-field="total">Total</th>' +
                '<th data-field="date">Create date</th>' +
                '<th data-field="status">Status</th>' +
                '<th id="editCol"></th>' +
                '<th id="removeCol"></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="data"/>')
            for (var i = 0; i < data.length; i++) {
                // if (data[i].orderStatus !== 1) {
                //     noNewOrder++;
                // }
                if (data[i].orderStatus != 0) {
                    var row = $('<tr/>');
                    row.append('<td>' + data[i].id + '</td>'
                        + '<td>' + data[i].customerName + '</td>'
                        + '<td>' + data[i].customerPhone + '</td>'
                        + '<td>' + data[i].orderTotal + '</td>'
                        + '<td>' + data[i].orderCreateDate + '</td>'
                        + '<td>' + data[i].orderStatus + '</td>');
                    row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                    row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                    tbody.append(row);
                }
            }
            table.append(tbody);
            $('#noNewOrder').textContent = noNewOrder;
            table.DataTable();
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addOrderModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;' +
                '">Add new order&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            table.prev().after(div);
        }
    });
};

var testAppendModal = function (id) {
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
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="deleteOrder(' + id + ', \'#deleteModal' + id + '\')">Oke</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeDeleteModal(\'#deleteModal' + id + '\')">Close</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(confirmModal);
};
var closeDeleteModal = function (modalId) {
    $(modalId).remove();
}
var deleteOrder = function (id, modalId) {
    $.ajax({
        url: '/removeOrder',
        method: 'POST',
        data: {
            'id': id,
        },
        success: function (data) {
            // console.log(data);
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                if (data[i].orderStatus != 0) {
                    var row = $('<tr/>');
                    row.append('<td>' + data[i].id + '</td>'
                        + '<td>' + data[i].customerName + '</td>'
                        + '<td>' + data[i].customerPhone + '</td>'
                        + '<td>' + data[i].orderTotal + '</td>'
                        + '<td>' + data[i].orderCreateDate + '</td>'
                        + '<td>' + data[i].orderStatus + '</td>');
                    row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                    row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                    tbody.append(row);
                }
            }
        }
    });
    closeDeleteModal(modalId)
}
