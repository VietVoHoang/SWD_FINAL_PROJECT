/**
 * Created by Asus on 7/15/2017.
 */
var listOrderItem = [];

var listPrice = [];

var viewListOrderItem = function () {
    var orderItemPopup = $('<div id="orderItemModal" class="modal fade" role="dialog">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal" onclick="closeDeleteModal(\'#orderItemModal\')">&times;</button>' +
        '<h4 class="modal-title">Modal Header</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>Some text in the modal.</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeDeleteModal(\'#orderItemModal\')">Close</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>');
    $('body').append(orderItemPopup);
};

var loadAllProduct = function (selectId) {
    var selectTag = $(selectId);
    $.ajax({
        url: "/findAllProduct",
        method: "GET",
        success: function (data) {
            listPrice = [];
            for (var i = 0; i < data.length; i++) {
                var optionTag = $('<option value="' + data[i].id + '">' + data[i].productName + '</option>');
                listPrice.push({id: data[i].id, price: data[i].productPrice});
                selectTag.append(optionTag);
            }
        }
    })
}

var addOrderItemToList = function () {
    var productId = $('#newOrderProductList').val();
    var productName = $('#newOrderProductList option[value="' + productId + '"]').text();
    var quantity = $('#newOrderProductQuantity').val();


    var flag = false;
    for (var i = 0; i < listOrderItem.length; i++) {
        if (listOrderItem[i].id == productId) {
            var newQuan = 0;
            newQuan = parseInt(listOrderItem[i].quan) + parseInt(quantity);
            listOrderItem[i].quan = newQuan;
            flag = true;
        }
    }

    if (!flag) {
        var orderItem = {id: productId, name: productName, quan: quantity};
        console.log(orderItem);
        listOrderItem.push(orderItem);
    }

    var cartTbl = $('#cartData');
    cartTbl.empty();
    var total = 0;
    for (var i = 0; i < listOrderItem.length; i++) {
        var price = 0;
        for (var j = 0; j < listPrice.length; j++) {
            if (listPrice[j].id == listOrderItem[i].id) {
                price = listPrice[j].price;
            }
        }
        total += (parseFloat(listOrderItem[i].quan) * parseFloat(price));
        var tr = $('<tr><td>' + (i + 1) + '</td>' +
            '<td>' + listOrderItem[i].name + '</td>' +
            '<td>' + listOrderItem[i].quan + '</td>' +
            '<td><button class="btn btn-box-tool" onclick="removeFromList(' + listOrderItem[i].id + ', \'#cartData\')" ><i class="fa fa-trash"/> </button></td></tr>');
        cartTbl.append(tr);
    }
    cartTbl.append($('<tr><td><b>Total</b></td><td></td><td></td><td id="newOrderTotal">' + total + '</td></tr>'));
};

var removeFromList = function (id, cartAreaId) {
    for (var i = 0; i < listOrderItem.length; i++) {
        if (listOrderItem[i].id == id) {
            listOrderItem.splice(i, 1);
        }
    }
    var cartTbl = $(cartAreaId);
    cartTbl.empty();
    var total = 0;
    for (var i = 0; i < listOrderItem.length; i++) {
        var price = 0;
        for (var j = 0; j < listPrice.length; j++) {
            if (listPrice[j].id == listOrderItem[i].id) {
                price = listPrice[j].price;
            }
        }
        total += (parseFloat(listOrderItem[i].quan) * parseFloat(price));
        var tr = $('<tr><td>' + (i + 1) + '</td>' +
            '<td>' + listOrderItem[i].name + '</td>' +
            '<td>' + listOrderItem[i].quan + '</td>' +
            '<td><button class="btn btn-box-tool" onclick="removeFromList(' + listOrderItem[i].id + ',' + cartAreaId + ')" ><i class="fa fa-trash"/></button></td></tr>');
        cartTbl.append(tr);
    }
    cartTbl.append($('<tr><td><b>Total</b></td><td></td><td></td><td id="newOrderTotal">' + total + '</td></tr>'));
};

var saveOrderItemToDB = function (orderId) {
    for (var i = 0; i < listOrderItem.length; i++) {
        $.ajax({
            url: "/addOrderItem",
            method: "POST",
            data: {
                "orderId": orderId,
                "productId": parseInt(listOrderItem[i].id),
                "quantity": parseInt(listOrderItem[i].quan)
            },
            success: function (data) {
                console.log(data);
            }
        })
    }
    listOrderItem = [];
};

var addItemToListUpdate = function (productId, productName, quantity) {
    var orderItem = {id: productId, name: productName, quan: quantity};
    console.log(orderItem);
    listOrderItem.push(orderItem);
};

var viewOrderItemTableUpdate = function (cartAreaUpdateId) {
    var cartArea = $(cartAreaUpdateId);
    cartArea.empty();
    var total = 0;
    for (var i = 0; i < listOrderItem.length; i++) {
        var price = 0;
        for (var j = 0; j < listPrice.length; j++) {
            if (listPrice[j].id == listOrderItem[i].id) {
                price = listPrice[j].price;
            }
        }
        total += (parseFloat(listOrderItem[i].quan) * parseFloat(price));
        var tr = $('<tr><td>' + (i + 1) + '</td>' +
            '<td>' + listOrderItem[i].name + '</td>' +
            '<td><input type="number" value="' + listOrderItem[i].quan + '" id="updateItemQuan' + listOrderItem[i].id + '"></td>' +
            '<td><button class="btn btn-box-tool" onclick="removeFromList(' + listOrderItem[i].id + ', \'#cartDataUpdate\')" ><i class="fa fa-trash"/> </button></td></tr>');
        cartArea.append(tr);
    }
    cartArea.append($('<tr><td><b>Total</b></td><td></td><td></td><td id="updateOrderTotal">' + total + '</td></tr>'));
};


var addItemToListUpdateClick = function () {
    var productId = $('#updateOrderProductList').val();
    var productName = $('#updateOrderProductList option[value="' + productId + '"]').text();
    var quantity = $('#updateOrderProductQuantity').val();


    var flag = false;
    for (var i = 0; i < listOrderItem.length; i++) {
        if (listOrderItem[i].id == productId) {
            var newQuan = 0;
            newQuan = parseInt(listOrderItem[i].quan) + parseInt(quantity);
            listOrderItem[i].quan = newQuan;
            flag = true;
        }
    }

    if (!flag) {
        var orderItem = {id: productId, name: productName, quan: quantity};
        console.log(orderItem);
        listOrderItem.push(orderItem);
    }

    var cartTbl = $('#cartDataUpdate');
    cartTbl.empty();
    var total = 0;
    for (var i = 0; i < listOrderItem.length; i++) {
        var price = 0;
        for (var j = 0; j < listPrice.length; j++) {
            if (listPrice[j].id == listOrderItem[i].id) {
                price = listPrice[j].price;
            }
        }
        total += (parseFloat(listOrderItem[i].quan) * parseFloat(price));
        var tr = $('<tr><td>' + (i + 1) + '</td>' +
            '<td>' + listOrderItem[i].name + '</td>' +
            '<td>' + listOrderItem[i].quan + '</td>' +
            '<td><button class="btn btn-box-tool" onclick="removeFromList(' + listOrderItem[i].id + ', \'#cartDataUpdate\')" >&times;</button></td></tr>');
        cartTbl.append(tr);
    }
    cartTbl.append($('<tr><td><b>Total</b></td><td></td><td></td><td id="updateOrderTotal">' + total + '</td></tr>'));

};