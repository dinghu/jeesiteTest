<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>产品信息表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/sys/user/export");
                        $("#searchForm").submit();
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
            $("#btnImport").click(function () {
                $.jBox($("#importBox").html(), {
                    title: "导入数据", buttons: {"关闭": true},
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
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/productInfo/list">产品信息表</a></li>
    <li><a href="${ctx}/oa/productInfo/form">添加产品信息</a></li>
</ul>

<form:form id="searchForm" action="${ctx}/oa/productInfo/list" method="post" class="breadcrumb form-search">
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
        <th>关键词</th>
        <th>图片</th>
        <th>颜色</th>
        <th>样品</th>
        <th>样品和大货是否一致</th>
        <th>厂家包装要求</th>
        <th>来货整件数量</th>
        <th>仓库包装要求</th>
        <th>验货注意事项</th>
        <th>单个包装方式</th>
        <th>销售组合方式</th>
        <th>产品特点及优缺点</th>
        <th>产品刊登建议</th>
        <th>产品功能及参数</th>
        <th>产品配件</th>
        <th>产品主链接</th>
        <th>备注</th>
        <th>参考链接信息</th>
        <th>图片制作要求</th>
        <th>附件</th>
        <th></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="productInfo">
        <tr>
            <td>
                <span class="wrap" title="${productInfo.id}">${productInfo.id}</span>
            </td>
            <td>

                <span class="wrap"
                      title="${fns:abbr(productInfo.createUid,10 )}">${fns:abbr(productInfo.createUid,10 )}</span>
            </td>
            <td>
                <span class="wrap"
                      title="<fmt:formatDate value="${productInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
                    <fmt:formatDate value="${productInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </td>

            <td>
                <span class="wrap" title="${productInfo.sku}">${productInfo.sku}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.name}">${productInfo.name}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.keywords}">${productInfo.keywords}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.images}">${productInfo.images}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.color}">${productInfo.color}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.yangpin}">${productInfo.yangpin}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.issametoyangpin}">${productInfo.issametoyangpin}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.packingRequirements}">${productInfo.packingRequirements}</span>
            </td>
            <td>
                <span class="wrap" title=""></span>
            </td>
            <td>
                <span class="wrap"
                      title="${productInfo.warehousePackagingRequirements}">${productInfo.warehousePackagingRequirements}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.checkPoints}">${productInfo.checkPoints}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.singlePackingway}">${productInfo.singlePackingway}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.saleGroupway}">${productInfo.saleGroupway}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.strengthsWeaknesses}">${productInfo.strengthsWeaknesses}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.kandengAdvises}">${productInfo.kandengAdvises}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.functionParams}">${productInfo.functionParams}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.parts}">${productInfo.parts}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.link}">${productInfo.link}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.note}">${productInfo.note}</span>
            </td>
            <td>
                <span class="wrap" title=""></span>
            </td>
            <td>
                <span class="wrap"
                      title="${productInfo.imageProductionRequirements}">${productInfo.imageProductionRequirements}</span>
            </td>
            <td>
                <span class="wrap" title="${productInfo.others}">${productInfo.others}</span>
            </td>

            <td>
                <span class="wrap" title="">
                    <%--<a href="${ctx}/oa/productInfo/detail?id=${productInfo.id}">查看</a>--%>
                    <a href="${ctx}/oa/productInfo/form?id=${productInfo.id}">修改</a>
                    <a href="${ctx}/oa/productInfo/delete?id=${productInfo.id}"
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