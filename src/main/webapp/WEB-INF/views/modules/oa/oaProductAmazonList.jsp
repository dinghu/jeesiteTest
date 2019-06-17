<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>亚马逊销售产品价格管理</title>
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
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/oaProductAmazon/">亚马逊销售产品价格列表</a></li>
    <shiro:hasPermission name="oa:oaProductAmazon:edit">
        <li><a href="${ctx}/oa/oaProductAmazon/form">亚马逊销售产品价格添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="oaProductAmazon" action="${ctx}/oa/oaProductAmazon/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>SKU：</label>
            <form:input path="sku" htmlEscape="false" maxlength="255" class="input-medium"/>
        </li>
        <li><label>品名：</label>
            <form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
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

        <th>SKU</th>
        <th>品名</th>
        <th>成本</th>
        <th>20%毛利</th>
        <th>建议定价</th>
        <th>拟调价</th>
        <th>备注</th>
        <shiro:hasPermission name="oa:oaProductAmazon:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="oaProductAmazon">
        <tr>
            <td><a href="${ctx}/oa/oaProductAmazon/form?id=${oaProductAmazon.id}">
                    ${oaProductAmazon.sku}
            </a></td>
            <td>
                    ${oaProductAmazon.name}
            </td>
            <td>
                    ${oaProductAmazon.chengben}
            </td>
            <td>
                    ${oaProductAmazon.maoli20}
            </td>
            <td>
                    ${oaProductAmazon.advise_price}
            </td>
            <td>
                    ${oaProductAmazon.nitiaojia}
            </td>
            <td>${oaProductAmazon.remarks}</td>
            <shiro:hasPermission name="oa:oaProductAmazon:edit">
                <td>
                    <a href="${ctx}/oa/oaProductAmazon/form?id=${oaProductAmazon.id}">修改</a>
                    <a href="${ctx}/oa/oaProductAmazon/delete?id=${oaProductAmazon.id}"
                       onclick="return confirmx('确认要删除该亚马逊销售产品价格吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>