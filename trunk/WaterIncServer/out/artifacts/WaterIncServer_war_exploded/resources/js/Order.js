/**
 * Created by Asus on 7/12/2017.
 */

var loadAllOrder = function () {
    $('#pageTitle').text("Order List");
    $('#tableAreaBonus').hide();
    listOrderItem = [];
    $.ajax({
        url: '/findAllOrder',
        method: 'GET',
        success: function (data) {
            var noNewOrder = 0;
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
                if (data[i].orderStatus == 0) {
                    noNewOrder = parseInt(noNewOrder) + 1;
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].id + '</td>'
                    + '<td>' + data[i].customerName + '</td>'
                    + '<td>' + data[i].customerPhone + '</td>'
                    + '<td>' + data[i].orderTotal + '</td>'
                    + '<td>' + moment(data[i].orderCreateDate).format("YYYY-MM-DD HH:mm:ss") + '</td>'
                    + '<td>' + data[i].orderStatus + '</td>');
                row.append('<td><button class="btn btn-default" onclick="appendUpdateModal(' + data[i].id + ')"><i class="fa fa-pencil" ></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="appendDeleteOrderModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            $('#newOrderAmount').text(noNewOrder);
            table.DataTable({
                "aaSorting": []
            });
            var div = $('<div id="addNewDiv" class="col-md-12 dataTables_length" style="' +
                'text-align: -webkit-right;' +
                '"><button data-toggle="modal" data-target="#addOrderModal" class="btn btn-default" style="' +
                'background-color: #5084be;' +
                'color: #fff;' +
                '">Add new order&nbsp;&nbsp;&nbsp;<i class="fa fa-plus"></i></button></div>');
            table.prev().after(div);
        }
    });
    loadAllProductForOrder("#newOrderProductList");
};

var appendDeleteOrderModal = function (id) {
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
    listOrderItem = [];
    $('#cartData').empty();
    $('#updateOrderProductList').empty();
};
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
                var row = $('<tr/>');
                row.append('<td>' + data[i].id + '</td>'
                    + '<td>' + data[i].customerName + '</td>'
                    + '<td>' + data[i].customerPhone + '</td>'
                    + '<td>' + data[i].orderTotal + '</td>'
                    + '<td>' + moment(data[i].orderCreateDate).format("YYYY-MM-DD HH:mm:ss") + '</td>'
                    + '<td>' + data[i].orderStatus + '</td>');
                row.append('<td><button class="btn btn-default" onclick="appendUpdateModal(' + data[i].id + ')"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
        }
    });
    closeDeleteModal(modalId);
};

var reloadOrderData = function () {
    $.ajax({
        url: '/findAllOrder',
        method: 'GET',
        success: function (data) {
            // console.log(data);
            var noNewOrder = 0;
            var tbody = $('#data');
            tbody.empty();
            for (var i = 0; i < data.length; i++) {
                if (data[i].orderStatus == 0) {
                    noNewOrder = parseInt(noNewOrder) + 1;
                }
                var row = $('<tr/>');
                row.append('<td>' + data[i].id + '</td>'
                    + '<td>' + data[i].customerName + '</td>'
                    + '<td>' + data[i].customerPhone + '</td>'
                    + '<td>' + data[i].orderTotal + '</td>'
                    + '<td>' + moment(data[i].orderCreateDate).format("YYYY-MM-DD HH:mm:ss") + '</td>'
                    + '<td>' + data[i].orderStatus + '</td>');
                row.append('<td><button class="btn btn-default" onclick="appendUpdateModal(' + data[i].id + ')"><i class="fa fa-pencil" ></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="testAppendModal(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
                $('#newOrderAmount').text(noNewOrder);
            }
        }
    });
}

var addNewOrder = function () {
    console.log(moment(new Date().getTime()).format("YYYY-MM-DD hh:mm:ss"));
    $.ajax({
        url: "/addOrder",
        method: "POST",
        data: {
            'createDate': moment(new Date().getTime()).format("YYYY-MM-DD hh:mm:ss").valueOf(),
            'total': $('#newOrderTotal').text(),
            'status': $('#orderStatus').val(),
            'cusName': $('#newOrderCusName').val(),
            'cusPhone': $('#newOrderCusPhone').val(),
            'cusAddress': $('#newOrderCusAddress').val(),
            'empId': 1
        },
        // data: $('#addOrderForm').serialize(),
        success: function (data) {
            console.log(data);
            saveOrderItemToDB(data.id);
            reloadOrderData();
        }
    })

}

var appendUpdateModal = function (id) {
    var updateModal = $('<div id="updateOrderModal' + id + '" class="modal fade in" role="dialog" style="display: block;">' +
        '<div class="modal-dialog">' +

        '<!-- Modal content-->' +
        '<div class="modal-content">' +
        '<form id="addOrderForm">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeDeleteModal(\'#updateOrderModal' + id + '\')">&times;</button>' +
        '<h4 class="modal-title">Update Order</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<div class="form-group" style="display: none">' +
        '<label for="newOrderCusName"></label>' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="newOrderCusName">Customer name</label>' +
        '<input type="text" class="form-control" id="updateOrderCusName" placeholder="Name" name="cusName">' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="newOrderCusPhone">Customer Phone</label>' +
        '<input type="text" class="form-control" id="updateOrderCusPhone" placeholder="Phone"' +
        'name="cusPhone">' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="newOrderCusAddress">Customer Address</label>' +
        '<input type="text" class="form-control" id="updateOrderCusAddress" placeholder="Address"' +
        'name="cusAddress">' +
        '</div>' +
        '<div class="form-group">' +
        '<label for="newOrderCusAddress">Order status</label>' +
        '<select class="form-control" id="updateOrderStatus" name="status">' +
        '<option value="0">Not confirm</option>' +
        '<option value="1">Confirmed</option>' +
        '<option value="2">Not delivered</option>' +
        '<option value="3">Finished</option>' +
        '</select>' +
        '</div>' +
        '<div style="border: 1px solid #cccccc; border-radius: 5px; padding: 7.5px;">' +
        '<form class="form-inline">' +
        '<div class="form-group">' +
        '<select class="form-control" id="updateOrderProductList">' +
        '</select>' +
        '</div>' +
        '<div class="form-group">' +
        '<input type="number" class="form-control" id="updateOrderProductQuantity" placeholder="0">' +
        '</div>' +
        '<button type="button" onclick="addItemToListUpdateClick()" class="btn btn-default">Add to cart</button>' +
        '</form>' +
        '<table class="table table-responsive table-hover table-striped">' +
        '<thead>' +
        '<tr>' +
        '<th>#</th>' +
        '<th>ProductName</th>' +
        '<th>Quantity</th>' +
        '<th></th>' +
        '</tr>' +
        '</thead>' +
        '<tbody id="cartDataUpdate">' +

        '</tbody>' +
        '</table>' +
        '</div>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-success" data-dismiss="modal" onclick="updateOrder(' + id + ')">Update' +
        '</button>' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeDeleteModal(\'#updateOrderModal' + id + '\')">Close</button>' +
        '</div>' +
        '</form>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(updateModal);
    loadAllProductForOrder('#updateOrderProductList');
    loadOrderItemByOrderId(id, "#cartDataUpdate");
    getOrderByIdUpdate(id);
};

var loadOrderItemByOrderId = function (id, cartAreaId) {
    $.ajax({
        url: "/getOrderItemByOrderId",
        method: "POST",
        data: {
            "id": id
        },
        success: function (data) {
            listOrderItem = [];
            for (var i = 0; i < data.length; i++) {
                addItemToListUpdate(data[i].productId, data[i].productsByProductId.productName, data[i].itemQuantity);
            }
            viewOrderItemTableUpdate(cartAreaId);
        }
    })
};

var getOrderByIdUpdate = function (id) {
    $.ajax({
        url: "/findOrderById",
        method: "POST",
        data: {
            "id": id
        },
        success: function (data) {
            $('#updateOrderCusName').val(data.customerName);
            $('#updateOrderCusName').prop('disabled', true);
            $('#updateOrderCusPhone').val(data.customerPhone);
            $('#updateOrderCusPhone').prop('disabled', true);
            $('#updateOrderCusAddress').val(data.customerAddress);
            $('#updateOrderCusAddress').prop('disabled', true);
            $('#updateOrderStatus').val(data.orderStatus);
        }
    });
};

var updateOrder = function (id) {
    saveOrderItemToDB(id);
    $.ajax({
        url: "/updateOrder",
        method: "POST",
        data: {
            "id": id,
            "total": parseFloat($('#updateOrderTotal').text()),
            "status": parseInt($('#updateOrderStatus').val())
        },
        success: function (data) {
            $('#updateOrderModal' + id).remove();
            listOrderItem = [];
            $('#cartData').empty();
            $('#updateOrderProductList').empty();
            reloadOrderData();

        }
    })
};
