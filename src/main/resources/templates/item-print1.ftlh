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
        margin-bottom: 5px;
    }
    .itemTable td
    {
        border: solid #000 1px;
        padding-top: 5px;
    }
    img{
        width: ${print.barcodeWidth};
        height: ${print.barcodeHeight};
    }
</style>

<body>
<#list 1..page as i>
<table class="itemTable">
    <tr><td colspan="2" style="font-size: 18px; height: ${print.sn};">Serial Number</td></tr>
    <tr>
        <td><img src="/barcode/${sn.a0}"></td><td><img src="/barcode/${sn.a1}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a2}"></td><td><img src="/barcode/${sn.a3}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a4}"></td><td><img src="/barcode/${sn.a5}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a6}"></td><td><img src="/barcode/${sn.a7}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a8}"></td><td><img src="/barcode/${sn.a9}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a10}"></td><td><img src="/barcode/${sn.a11}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a12}"></td><td><img src="/barcode/${sn.a13}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a14}"></td><td><img src="/barcode/${sn.a15}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a16}"></td><td><img src="/barcode/${sn.a17}"></td>
    </tr>
    <tr>
        <td><img src="/barcode/${sn.a18}"></td><td><img src="/barcode/${sn.a19}"></td>
    </tr>
</table>
<#if table2 >
    <table class="itemTable">
        <tr><td colspan="2" style="font-size: 18px; height: ${print.sn};">Serial Number</td></tr>
        <tr>
            <td><img src="/barcode/${sn.b0}"></td><td><img src="/barcode/${sn.b1}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b2}"></td><td><img src="/barcode/${sn.b3}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b4}"></td><td><img src="/barcode/${sn.b5}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b6}"></td><td><img src="/barcode/${sn.b7}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b8}"></td><td><img src="/barcode/${sn.b9}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b10}"></td><td><img src="/barcode/${sn.b11}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b12}"></td><td><img src="/barcode/${sn.b13}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b14}"></td><td><img src="/barcode/${sn.b15}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b16}"></td><td><img src="/barcode/${sn.b17}"></td>
        </tr>
        <tr>
            <td><img src="/barcode/${sn.b18}"></td><td><img src="/barcode/${sn.b19}"></td>
        </tr>
    </table>
</#if>
</#list>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>
    $(function(){
        window.print();
    });
</script>
</body>
</html>