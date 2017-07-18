/**
 * Created by hongducphan on 7/12/17.
 */

var loadAllProduct = function () {
    $('#pageTitle').text("Product List");
    $('#tableAreaBonus').hide();
    $.ajax({
        url: '/findAllProduct',
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
                if (status == '1') {
                    status = "Stocking";
                } else {
                    status = "Not for sale";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].productName + '</td>'
                    + '<td>' + data[i].productQuantity + '</td>'
                    + '<td>' + data[i].productPrice + '</td>'
                    + '<td>' + status + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            table.DataTable();
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addProductModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;">Add new product&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            table.prev().remove();
            table.prev().after(div);
        }
    })
};

var deleteAppendModal = function (id) {
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
};

var updateAppendModal = function (id) {
    var updateModal = $('<div id="updateProductModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<form id="updateProductForm">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeUpdateModal(\'#updateProductModal' + id + '\')">&times;</button>' +
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
        '<button type="button" class="btn btn-success" data-dismiss="modal" onclick="updateProduct(' + id + ', \'#updateProductModal' + id + '\')">Update</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeUpdateModal(\'#updateProductModal' + id + '\')">Close</button>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(updateModal);
    $.ajax({
        url: "/findProductById",
        method: "POST",
        data: {"id": id},
        success: function (data) {
            $('#updateProductname').val(data.productName);
            $('#updateProductQuantity').val(data.productQuantity);
            $('#updateProductPrice').val(data.productPrice);
        }
    });
};

var closeUpdateModal = function (modalId) {
    $(modalId).remove();
};

var updateProduct = function (id, modalId) {
    $.ajax({
        url: '/updateProduct',
        method: 'POST',
        data: $('#updateProductForm').serialize(),
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
                    status = "Stocking";
                } else {
                    status = "Not for sale";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].productName + '</td>'
                    + '<td>' + data[i].productQuantity + '</td>'
                    + '<td>' + data[i].productPrice + '</td>'
                    + '<td>' + status + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
        }
    });
    closeUpdateModal(modalId);
};

var removeProduct = function (id, modalId) {
    $.ajax({
        url: '/removeProduct',
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
                    status = "Stocking";
                } else {
                    status = "Not for sale";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].productName + '</td>'
                    + '<td>' + data[i].productQuantity + '</td>'
                    + '<td>' + data[i].productPrice + '</td>'
                    + '<td>' + status + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
        }
    });
    closeDeleteModal(modalId);
};

var createProduct = function () {
    $.ajax({
        url: '/addProduct',
        method: 'POST',
        data: $('#addProductForm').serialize(),
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
                    status = "Stocking";
                } else {
                    status = "Not for sale";
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].productName + '</td>'
                    + '<td>' + data[i].productQuantity + '</td>'
                    + '<td>' + data[i].productPrice + '</td>'
                    + '<td>' + status + '</td>');
                row.append('<td><button class="btn btn-default" onclick="updateAppendModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="deleteAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            $('#tableArea').DataTable();
        }
    });
};