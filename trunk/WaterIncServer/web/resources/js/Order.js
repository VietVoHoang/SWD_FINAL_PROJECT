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
            table.empty();
            table.append('<thead>' +
                '<tr style="background-color: #00a65a; color: #FFF">' +
                '<th>Id</th>' +
                '<th>Customer name</th>' +
                '<th>Customer phone</th>' +
                '<th>Total</th>' +
                '<th>Create date</th>' +
                '<th>Status</th>' +
                '<th></th>' +
                '<th></th>' +
                '</tr>' +
                '</thead>' +
                '<tfoot>' +
                '<tr>' +
                '<th>Id</th>' +
                '<th>Customer name</th>' +
                '<th>Customer phone</th>' +
                '<th>Total</th>' +
                '<th>Create date</th>' +
                '<th>Status</th>' +
                '<th></th>' +
                '<th></th>' +
                '</tr>' +
                '</tfoot>');
            var tbody = $('<tbody id="data"/>')
            for (var i = 0; i < data.length; i++) {
                // if (data[i].orderStatus !== 1) {
                //     noNewOrder++;
                // }
                var row = $('<tr/>');
                row.append('<td>' + data[i].id + '</td>'
                    + '<td>' + data[i].customerName + '</td>'
                    + '<td>' + data[i].customerPhone + '</td>'
                    + '<td>' + data[i].orderTotal + '</td>'
                    + '<td>' + data[i].orderCreateDate + '</td>'
                    + '<td>' + data[i].orderStatus + '</td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-pencil"></i></button></td>');
                row.append('<td><button class="btn btn-default"><i class="fa fa-trash"></i></button></td>');
                tbody.append(row);
            }
            table.append(tbody);
            $('#noNewOrder').textContent = noNewOrder;
            table.DataTable();
        }
    });
};
