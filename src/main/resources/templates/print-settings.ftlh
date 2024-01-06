<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
    <style>

    </style>

</head>
<body>
<h1>打印设置</h1>
    Serial Number Height <input id="sn" value="${print.sn}"><br>
    Barcode Height <input id="barcodeHeight" value="${print.barcodeHeight}"><br>
    Barcode Width <input id="barcodeWidth" value="${print.barcodeWidth}"><br>
<p></p><button id="view1">保存/预览</button>
<p></p>
    Model Width <input id="modelWidth" value="${print.modelWidth}"><br>
    Color Width <input id="colorWidth" value="${print.colorWidth}"><br>
    Capacity Width <input id="capacityWidth" value="${print.capacityWidth}"><br>
    QTY Width <input id="qtyWidth" value="${print.qtyWidth}"><br>
    Bottom Width <input id="btmWidth" value="${print.btmWidth}"><br>
    Line Height <input id="lineHeight" value="${print.lineHeight}"><br>
    Font Size <input id="fontSize" value="${print.fontSize}"><br>
<p></p>
    <button id="view2">保存/预览</button>
    <button onclick="location.href='/'">返回</button>
</body>
</html>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
    $.ajaxSetup({ contentType: 'application/json'});

     $("#view1").click(function(){
      var param = [
            {"item":"sn",value:$("#sn").val()},
            {"item":"barcodeHeight",value:$("#barcodeHeight").val()},
            {"item":"barcodeWidth",value:$("#barcodeWidth").val()},
            {"item":"modelWidth",value:$("#modelWidth").val()},
            {"item":"colorWidth",value:$("#colorWidth").val()},
            {"item":"capacityWidth",value:$("#capacityWidth").val()},
            {"item":"qtyWidth",value:$("#qtyWidth").val()},
            {"item":"btmWidth",value:$("#btmWidth").val()},
            {"item":"lineHeight",value:$("#lineHeight").val()},
            {"item":"fontSize",value:$("#fontSize").val()}
        ];
      $.post("print-settings/save",JSON.stringify(param),function(data){
        location.href = "/item-detail/print/test/1/1";
      },"json");

     });
    $("#view2").click(function(){
      var param = [
            {"item":"sn",value:$("#sn").val()},
            {"item":"barcodeHeight",value:$("#barcodeHeight").val()},
            {"item":"barcodeWidth",value:$("#barcodeWidth").val()},
            {"item":"modelWidth",value:$("#modelWidth").val()},
            {"item":"colorWidth",value:$("#colorWidth").val()},
            {"item":"capacityWidth",value:$("#capacityWidth").val()},
            {"item":"qtyWidth",value:$("#qtyWidth").val()},
            {"item":"btmWidth",value:$("#btmWidth").val()},
            {"item":"lineHeight",value:$("#lineHeight").val()},
            {"item":"fontSize",value:$("#fontSize").val()}
        ];
      $.post("print-settings/save",JSON.stringify(param),function(data){
        location.href = "/item-detail/print/test/2/1";
      },"json");

     });

</script>