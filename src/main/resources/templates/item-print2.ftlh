<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
</head>
<style>
    .itemTable
    {
        border-collapse: collapse;
        border: none;
    }
    .itemTable td
    {
        border: solid #000 1px;
        height: ${print.sn};
    }
    img{
        width: ${print.barcodeWidth};
        height: ${print.barcodeHeight};
    }

    .itemTable2
    {
        border-collapse: collapse;
        page-break-before: always;
    }
    .itemTable2 td
    {
        border: solid #000 1px;
        text-align: center;
        height: ${print.lineHeight};
        font-size: ${print.fontSize};
    }
</style>

<body>
<#list 1..page as i>
<table class="itemTable2">
    <tr>
        <td style="width: ${print.modelWidth}">Model</td>
        <td style="width: ${print.colorWidth}">Color</td>
        <td style="width: ${print.capacityWidth}">Capacity</td>
        <td style="width: ${print.qtyWidth}">QTY</td>
    </tr>
    <#list list as d>
        <tr>
            <td>${d.model!''}</td>
            <td>${d.color!''}</td>
            <td>${d.capacity!''}</td>
            <td>${d.qty!''}</td>
        </tr>
    </#list>
</table>
<table class="itemTable2" style="border-top: none">
    <tr>
        <td colspan="2" style="width: ${print.btmWidth};border-top: none">Country of Origin:</td>
        <td colspan="2" style="width: ${print.btmWidth};border-top: none">China</td>
    </tr>
    <tr>
        <td colspan="2">Quantity:</td>
        <td colspan="2">${quantity}</td>
    </tr>
    <tr>
        <td colspan="2">Carton no:</td>
        <td colspan="2">${cartonno}</td>
    </tr>
</table>
</#list>
</body>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>
    $(function(){
        window.print();
    });
</script>
</html>