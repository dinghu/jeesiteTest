<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>任务管理</title>
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
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/oaWorkTask/${oaWorkTask.self?'self':''}">任务列表</a></li>
    <c:if test="${oaWorkTask.self}">
        <shiro:hasPermission name="oa:oaWorkTask:edit">
            <li><a href="${ctx}/oa/oaWorkTask/form">任务添加</a></li>
        </shiro:hasPermission>
    </c:if>
</ul>
<form:form id="searchForm" modelAttribute="oaWorkTask" action="${ctx}/oa/oaWorkTask/${oaWorkTask.self?'self':'list'}"
           method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>标题</th>
        <th>类型</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>更新时间</th>
        <c:if test="${!oaWorkTask.self}"><shiro:hasPermission name="oa:oaWorkTask:edit">
            <th>操作</th>
        </shiro:hasPermission></c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="oaWorkTask">
        <tr>
            <td><a href="${ctx}/oa/oaWorkTask/${requestScope.oaWorkTask.self?'view':'form'}?id=${oaWorkTask.id}">
                    ${fns:abbr(oaWorkTask.title,50)}
            </a></td>
            <td>
                工作任务
            </td>
            <td>
                进行中
            </td>
            <td>
                <fmt:formatDate value="${oaWorkTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${oaWorkTask.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <c:if test="${!requestScope.oaWorkTask.self}"><shiro:hasPermission name="oa:oaWorkTask:edit">
                <td>
                    <a href="${ctx}/oa/oaWorkTask/form?id=${oaWorkTask.id}">修改</a>
                    <a href="${ctx}/oa/oaWorkTask/delete?id=${oaWorkTask.id}"
                       onclick="return confirmx('确认要删除该任务吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission></c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>