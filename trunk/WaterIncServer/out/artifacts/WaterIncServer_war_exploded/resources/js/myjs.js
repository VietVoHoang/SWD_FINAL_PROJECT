/**
 * Created by hongducphan on 7/12/17.
 */
var init = function () {
    $.ajax({
        url: '/findAllProduct',
        method: 'GET',
        success: function (data) {
            console.log(data);
            $('#data').empty();
            for (var i = 0; i < data.length; i++) {
                var row = $('<tr/>');
                row.append('<td>' + data[i].productName + '</td>'
                    + '<td>' + data[i].productQuantity + '</td>'
                    + '<td>' + data[i].productPrice + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default" onclick="removeProduct(' + data[i].id + ')"><i class="fa fa-trash"></i></button></td>');
                $('#data').append(row);
            }
        }
    })
};
init();

var removeProduct = function (id) {
    $.ajax({
        url: '/removeProduct',
        method: 'POST',
        data: {
            "id": id,
        },
        success: function () {
            init();
        }
    })
};