<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户售后反馈管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

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
            /*line-height: 60px;*/
            text-overflow: ellipsis;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            /*word-break: break-all;*/
        }
    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/oaFeedback/">反馈列表</a></li>
    <shiro:hasPermission name="oa:oaFeedback:edit">
        <li><a href="${ctx}/oa/oaFeedback/form">反馈添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="oaFeedback" action="${ctx}/oa/oaFeedback/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>反馈人：</label>
            <form:input path="createBy.id" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>部门：</label>
            <sys:treeselect id="officeInfo" name="officeInfo" value="${oaFeedback.officeInfo.id}" labelName=""
                            labelValue="${oaFeedback.officeInfo.name}"
                            title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true"
                            notAllowSelectParent="true"/>
        </li>
        <li><label>SKU：</label>
            <form:input path="sku" htmlEscape="false" maxlength="32" class="input-medium"/>
        </li>
        <li><label>反馈内容：</label>
            <form:input path="content" htmlEscape="false" maxlength="512" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>部门</th>
        <th>SKU</th>
        <th>反馈内容</th>
        <th>图片</th>
        <th>产品链接</th>
        <th>买家信息</th>
        <%--<th>可放连接</th>--%>
        <th>处理人</th>
        <th>处理办法</th>
        <th>处理状态</th>
        <th>反馈人</th>
        <th>反馈时间</th>
        <th>备注</th>

        <shiro:hasPermission name="oa:oaFeedback:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="oaFeedback">
        <tr>

            <td>
                <span class="wrap" title="${oaFeedback.officeInfo.name}">${oaFeedback.officeInfo.name}</span>
            </td>
            <td>
                <span class="wrap" title="${oaFeedback.sku}">${oaFeedback.sku}</span>
            </td>
            <td>
                <a href="${ctx}/oa/oaFeedback/detail?id=${oaFeedback.id}">
                    <span class="wrap" title="${oaFeedback.content}"> ${oaFeedback.content}</span>
                </a>
            </td>
            <td>
                    <%--${oaFeedback.images}--%>
                <c:if test="${fns:endsWith(oaFeedback.images,'jpg' )}">
                    <img src='${fns:replace(oaFeedback.images,"|" ,"" )}'
                         style="width: 60px;height: 60px">
                </c:if>
                <c:if test="${fns:endsWith(oaFeedback.images,'png' )}">
                    <img src='${fns:replace(oaFeedback.images,"|" ,"" )}'
                         style="width: 60px;height: 60px">
                </c:if>
                <c:if test="${empty oaFeedback.images}">
                    <img style="width: 60px;height: 60px">
                </c:if>
            </td>
            <td>
                <span class="wrap" title="${oaFeedback.link}">${oaFeedback.link}</span>
            </td>
            <td>
                <span class="wrap" title="${oaFeedback.buyerinfo}">${oaFeedback.buyerinfo}</span>
            </td>


                <%--<td>--%>
                <%--<span class="wrap" title="${oaFeedback.linkmaybe}">${oaFeedback.linkmaybe}</span>--%>
                <%--</td>--%>
            <td>
                <span class="wrap" title="${oaFeedback.solveBy.name}">${oaFeedback.solveBy.name}</span>
            </td>
            <td>
                <span class="wrap" title="${oaFeedback.dealWay}">${oaFeedback.dealWay}</span>
            </td>


            <td>
                <span class="wrap">
                    <c:choose>
                        <c:when test="${oaFeedback.dealResult eq 1}">
                            <span style="color: red">进行中</span>
                        </c:when>
                        <c:when test="${oaFeedback.dealResult eq 2}">
                            <span style="color: green">已完成</span>
                        </c:when>
                        <c:otherwise>
                            未开始
                        </c:otherwise>
                    </c:choose></span>
            </td>
            <td>
                <span class="wrap" title="${oaFeedback.createBy.name}"> ${oaFeedback.createBy.name}</span>
            </td>
            <td>
                <span class="wrap"
                      title="<fmt:formatDate value="${oaFeedback.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"><fmt:formatDate
                        value="${oaFeedback.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            </td>


            <td>
                <span class="wrap" title="${oaFeedback.remarks}">${oaFeedback.remarks}</span>
            </td>


            <shiro:hasPermission name="oa:oaFeedback:edit">
                <td>
                    <a href="${ctx}/oa/oaFeedback/form?id=${oaFeedback.id}">修改</a>
                    <a href="${ctx}/oa/oaFeedback/delete?id=${oaFeedback.id}"
                       onclick="return confirmx('确认要删除该用户售后反馈吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>