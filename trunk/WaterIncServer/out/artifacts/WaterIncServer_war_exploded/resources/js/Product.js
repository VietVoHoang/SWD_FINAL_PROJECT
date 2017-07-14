/**
 * Created by hongducphan on 7/12/17.
 */

var idToDelete;

var loadAllProduct = function () {
    $.ajax({
        url: '/findAllProduct',
        method: 'GET',
        success: function (data) {
            console.log(data);
            var table = $('#tableArea');
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th>Product name</th>' +
                '<th>Product quantity</th>' +
                '<th>Price</th>' +
                '<th>Status</th>' +
                '<th></th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>');
            var tbody = $('<tbody id="data"/>');
            for (var i = 0; i < data.length; i++) {
                var status = data[i].status;
                if(status == '1') {
                    status = "Stocking";
                } else {
                    status = "Not for sale";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].productName + '</td>'
                    + '<td>' + data[i].productQuantity + '</td>'
                    + '<td>' + data[i].productPrice + '</td>'
                    + '<td>' + status + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
        }
    })
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
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="removeProduct(' + id + ', \'#deleteModal' + id + '\')">Yes</button>' +
        '<button type="button" class="btn btn-danger" data-dismiss="modal" onclick="closeDeleteModal(\'#deleteModal' + id + '\')">No</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(confirmModal);
};
var closeDeleteModal = function (modalId) {
    $(modalId).remove();
}

var removeProduct = function (id, modalId) {
    $.ajax({
        url: '/removeProduct',
        method: 'POST',
        data: {
            "id": id,
        },
        success: function (data) {
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                var status = data[i].status;
                if(status == '1') {
                    status = "Stocking";
                } else {
                    status = "Not for sale";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].productName + '</td>'
                    + '<td>' + data[i].productQuantity + '</td>'
                    + '<td>' + data[i].productPrice + '</td>'
                    + '<td>' + status + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
        }
    })
    closeDeleteModal(modalId);
};