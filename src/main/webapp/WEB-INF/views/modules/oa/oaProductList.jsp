<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>产品价格表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出产品信息数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/oa/product/export");
                        $("#searchForm").submit();
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
            $("#btnImport").click(function () {
                $.jBox($("#importBox").html(), {
                    title: "导入产品信息数据", buttons: {"关闭": true},
                    bottomText: "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
                });
            });
        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
    <style type="text/css">

        .wrap {
            width: 100px;
            max-height: 60px;
            overflow: hidden;
            display: inline-block;
            line-height: 1.5;
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            word-break: break-all;
        }
    </style>
</head>
<body>

<div id="importBox" class="hide">
    <form id="importForm" action="${ctx}/oa/product/import" method="post" enctype="multipart/form-data"
          class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
        <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
        <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
        <%--<a href="${ctx}/oa/productInfo/import/template">下载模板</a>--%>
    </form>
</div>

<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/product/list">产品价格表</a></li>
    <li><a href="${ctx}/oa/product/form">添加产品价格信息</a></li>
</ul>

<form:form id="searchForm" action="${ctx}/oa/product/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>关键字：</label>
            <input id="keywords" name="keywords" htmlEscape="false" maxlength="200" class="input-medium"/>
        </li>
        <%--<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>--%>
        <%--<li class="clearfix"></li>--%>

        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"
                                onclick="return page();"/>
            <input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
            <input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>

<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>提交人</th>
        <th>提交时间</th>
        <th>SKU</th>
        <th>品名</th>
        <th>装箱体积（m³）</th>
        <th>装箱数量</th>
        <th>毛重（g）</th>
        <th>单个包装尺寸（cm）</th>
        <th>单价</th>
        <th>国内运费</th>
        <th>包装费</th>
        <th>国际运费</th>
        <th>关税税率</th>
        <th>HS Code</th>
        <th>关税</th>
        <th>当地运费</th>
        <th>Ebay成本</th>
        <th>Ebay20%毛利定价</th>
        <th>Amazon成本</th>
        <th>Amazon20%毛利定价</th>
        <th>建议定价</th>
        <th>拟调价</th>
        <th>厂家链接</th>
        <th></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="product">
        <tr>
            <td>
                <span class="wrap" title="${product.id}">${product.id}</span>
            </td>
            <td>

                <span class="wrap"
                      title="${fns:abbr(product.createUid,10 )}">${fns:abbr(product.createUid,10 )}</span>
            </td>
            <td>
                <span class="wrap"
                      title="<fmt:formatDate value="${product.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
                    <fmt:formatDate value="${product.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </td>

            <td>
                <span class="wrap" title="${product.sku}">${product.sku}</span>
            </td>
            <td>
                <span class="wrap" title="${product.name}">${product.name}</span>
            </td>
            <td>
                <span class="wrap" title="${product.packingVolume}">${product.packingVolume}</span>
            </td>
            <td>
                <span class="wrap" title="${product.packingAmout}">${product.packingAmout}</span>
            </td>
            <td>
                <span class="wrap" title="${product.grossWeight}">${product.grossWeight}</span>
            </td>
            <td>
                <span class="wrap" title="${product.singlePackingSize}">${product.singlePackingSize}</span>
            </td>
            <td>
                <span class="wrap" title="${product.price}">${product.price}</span>
            </td>
            <td>
                <span class="wrap" title="${product.domesticReight}">${product.domesticReight}</span>
            </td>
            <td>
                <span class="wrap" title="${product.packingPrice}">${product.packingPrice}</span>
            </td>
            <td>
                <span class="wrap"
                      title="${product.internationalShipping}">${product.internationalShipping}</span>
            </td>
            <td>
                <span class="wrap" title="${product.rate}">${product.rate}</span>
            </td>
            <td>
                <span class="wrap" title="${product.hsCode}">${product.hsCode}</span>
            </td>
            <td>
                <span class="wrap" title="${product.tariff}">${product.tariff}</span>
            </td>
            <td>
                <span class="wrap" title="${product.localFreight}">${product.localFreight}</span>
            </td>
            <td>
                <span class="wrap" title="${product.ebayCost}">${product.ebayCost}</span>
            </td>
            <td>
                <span class="wrap" title="${product.ebay20pPrice}">${product.ebay20pPrice}</span>
            </td>
            <td>
                <span class="wrap" title="${product.amazonCost}">${product.amazonCost}</span>
            </td>
            <td>
                <span class="wrap" title="${product.amazon20pPrice}">${product.amazon20pPrice}</span>
            </td>
            <td>
                <span class="wrap" title="${product.advicePrice}">${product.advicePrice}</span>
            </td>
            <td>
                <span class="wrap" title="${product.nitiaojia}">${product.nitiaojia}</span>
            </td>
            <td>
                <span class="wrap"
                      title="${product.manufacturerLink}">${product.manufacturerLink}</span>
            </td>


            <td>
                <span class="wrap" title="">
                    <%--<a href="${ctx}/oa/product/detail?id=${product.id}">查看</a>--%>
                    <a href="${ctx}/oa/product/form?id=${product.id}">修改</a>
                    <a href="${ctx}/oa/product/delete?id=${product.id}"
                       onclick="return confirmx('确认要删除该商品信息吗？', this.href)">删除</a>
                </span>

            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>