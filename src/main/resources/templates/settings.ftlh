<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
    <style>
        table#table1 {
            width: 1100px;
            border-collapse: collapse;
            font-family: Arial, sans-serif;
        }
        table#table1 th {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        table#table1 td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
            font-size: 12px;
        }
        table#table1 th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        table#table1 tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table#table2 {
            width:377.952756px;
            height:566.929134px;
            margin-top: 7.559055;
            margin-bottom: 7.559055;
            margin-left: 7.559055;
            margin-right: 7.559055;
            border-collapse: collapse;
            font-family: Arial, sans-serif;
        }
        table#table2 th {
            border: 0.1px solid #ccc;
            padding: 0.1px;
            text-align: left;
        }

        table#table2 td {
            border: 0.1px solid #ccc;
            padding: 0.1px;
            text-align: left;
        }



        .overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 9999;
        }

        .popup {
            background-color: white;
            padding: 2rem;
            border-radius: 5px;
            width: 300px;
            text-align: center;
        }

        .close-btn {
            background-color: red;
            color: white;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>

</head>
<body>
<h1>Stock Number</h1>
<p>
    Model <input id="model">
    SKU Number <input id="skuNumber">
    Color <input id="color">
    Capacity <input id="capacity">
    <button id="add">Add</button>
    <button onclick="location.href='print-settings'"> 打印设置</button>
    <button onclick="location.href='/'" style="margin-left: 20px">返回</button>
</p>
<table id="table1">
    <thead>
    <tr>
        <th>Model</th>
        <th>SKU Number</th>
        <th>Description</th>
<#--        <th>Editor</th>-->
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <#list list as sn>
        <tr>
            <td>${sn.model}</td>
            <td>${sn.skuNumber}</td>
            <td>${sn.model} ${sn.capacity} ${sn.color} (Used)A+</td>
<#--            <td>Editor</td>-->
            <td><button onclick="remove('${sn.id}')">Delete</button></td>
        </tr>
    </#list>

    </tbody>
</table>
<#--model+capacity+color+(Used)A+。-->
</body>
</html>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
    $.ajaxSetup({ contentType: 'application/json'});
    $("#add").click(function(){
        var param =
                {
                  "model": $("#model").val(),
                  "skuNumber": $("#skuNumber").val(),
                  "capacity": $("#capacity").val(),
                  "color": $("#color").val()
                };
        $.post("stock-number/save",JSON.stringify(param),function(data){location.reload()},"json");
    });

    function remove(id){
        $.get("stock-number/remove/"+id,function(data){location.reload()});
    }
</script>