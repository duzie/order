<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Packing List</title>
<style>
    table{
        border-spacing: 0px;
    }
    .page{
        height:297mm;
        width:210mm;
        position: relative;
        page-break-before: always;
    }
    .so
    {
        border-collapse: collapse;
        border: none;
        width: 100%;
        height: 170px;
    }
    .so td
    {
        border: solid #000 1px;
    }
    .so .content td
    {
        border: none;
        font-size: 12px;
        height: 20px;
        padding: 0px;
    }
    .so .content{
        vertical-align: top;
    }
    .pla{
        width: 40px;
        font-weight: bold;
        font-size: 12px;
        display: inline-block;
    }
    .plb{
        width: 50px;
        text-decoration:underline;
        font-size: 12px;
    }
    .cus{
        font-weight: bold;
        font-size: 13px;
    }
    .data-list
    {
        margin-top: 15px;
        border-collapse: collapse;
        border: none;
        width: 100%;
    }
    .data-list .title
    {
        font-weight: bold;
    }
    .data-list td
    {
        border: solid #000 1px;
        font-size: 12px;
        text-align: center;
    }
    .data-list .l
    {
        text-align: left;
    }
    .remark{
        font-weight: bold;
        font-size: 13px;
        margin-top: 20px;
        margin-bottom: 30px;
    }
</style>
</head>
<body>
<#list list as l>
<div class="page">
<div style="position: absolute;top: 20px"><img src="/logo.jpg" style="width: 15%;float: left"></div>
<table style="width: 100%">
    <tr>
        <td colspan="2" style="text-align: center; font-weight: bold;font-size: 20px;height: 60px;">
            DGM(HK) LTD</td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center; font-weight: bold;font-size: 12px;height: 20px;">
            ${data.lookUp}
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center; font-weight: bold;font-size: 12px;height: 20px;">
            ${data.other}
        </td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center; ">
            <div style="font-weight: bold;float: left;text-align: right;width: 57%;font-size: 20px;">Packing List</div>
            <div style="float: right;text-align: left;line-height: 15px">
                <label class="pla">PL </label> :  <span class="plb">${data.plNo}</span><br>
                <label class="pla">Date</label> :  <span class="plb">${data.date}</span><br>
                <label class="pla">Page</label> :  <span class="plb">${l_index + 1} of ${list?size}</span>
            </div>
        </td>
    </tr>
    <tr>
        <td style="width: 50%;vertical-align: top">
            <table class="so" style="width: 98%">
                <tr class="cus">
                    <td class="cus">Customer: <span style="margin-left: 30px">CODE: ${data.code!''}</span></td>
                </tr>
                <tr>
                    <td class="content">
                        <table>
                            <tr>
                                <td style="width: 70px">Company:</td>
                                <td>${data.company}</td>
                            </tr>
                            <tr>
                                <td>Address:</td>
                                <td>${data.address1}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>${data.address2}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>${data.address3}</td>
                            </tr>
                            <tr>
                                <td>Contact:</td>
                                <td>${data.contact}</td>
                            </tr>
                            <tr>
                                <td>Tel:</td>
                                <td>${data.tel}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
        <td style="vertical-align: top;">
            <table class="so" style="width: 100%" >
                <tr class="cus">
                    <td class="cus">DELIVERY TO: </td>
                </tr>
                <tr>
                    <td class="content">
                        <table>
                            <tr>
                                <td style="width: 70px">Company:</td>
                                <td>${data.dcompany}</td>
                            </tr>
                            <tr>
                                <td>Address:</td>
                                <td>${data.daddress1}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>${data.daddress2}</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>${data.daddress3}</td>
                            </tr>
                            <tr>
                                <td>Contact:</td>
                                <td>${data.dcontact}</td>
                            </tr>
                            <tr>
                                <td>Tel:</td>
                                <td>${data.dtel}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<table class="data-list">
    <tr class="title">
        <td>Carton No.</td>
        <td>Product Code & Description</td>
        <td style="text-align: center">Total Qty (Pcs)</td>
        <td style="text-align: center">N.W. (Kgs)</td>
        <td style="text-align: center">G.W. (Kgs)</td>
        <td style="text-align: center">Carton Size (cm)</td>
    </tr>
    <#list l as d>
        <tr>
            <#if !d.prevSame>
                <td rowspan="${d.rowspan}">${d.a} </td>
            </#if>
            <td class="l">${d.b}<br>${d.c}</td>
            <td>${d.d}</td>
            <#if !d.prevSame>
                <td rowspan="${d.rowspan}">${d.e}</td>
                <td rowspan="${d.rowspan}">${d.f}</td>
                <td rowspan="${d.rowspan}">${d.g}</td>
            </#if>
        </tr>
    </#list>
    <#if !l_has_next>
        <tr style="font-weight: bold;">
            <td colspan="2" ><div style="float:left;">Total (${data.totalCartons} Cartons)</div> <div style="float: right">Total:</div></td>
            <td>${data.totalQty}</td>
            <td>${data.totalNw}</td>
            <td>${data.totalGw}</td>
            <td>${data.totalSize}</td>
        </tr>
    </#if>
</table>
<#if !l_has_next>
    <table class="remark">
        <tr>
            <td>Remarks: </td>
            <td>${data.remark1}</td>
        </tr>
        <tr>
            <td></td>
            <td>${data.remark2}</td>
        </tr>
        <tr>
            <td></td>
            <td>${data.remark3}</td>
        </tr>
    </table>

    <div style="font-weight: bold;font-size: 22px">DGM(HK) LTD</div>
    <div style="font-weight: bold;font-size: 12px;text-decoration: overline;margin-top: 40px">Authorized Signature&Stamp</div>
</#if>
</div>
</#list>


</body>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script>
    $(function(){
        window.print();
    });

</script>
</html>