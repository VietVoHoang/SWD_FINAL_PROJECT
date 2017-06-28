<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/28/2017
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="resources/js/jquery.min.js"></script>
</head>
<body>
<div id="result">dasdas</div>

<script>

    var getAll = function () {
        $.ajax({
            url: '/findAll',
            method: 'GET',
            success: function (data) {
                console.log(data);
                $('#result').empty();
                for (var i = 0; i < data.length; i++) {
                    var div = $('<div/>');
                    div.append('<span>' + data[i].agencyName + '</span>');
                    $('#result').append(div);
                }
            }
        })
    };

    getAll();
</script>
</body>

</html>
