<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>通知管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/oa/oaWorkTask/self">任务列表</a></li>
    <li class="active"><a href="${ctx}/oa/oaWorkTask/form?id=${oaWorkTask.id}">任务<shiro:hasPermission
            name="oa:oaWorkTask:edit">${oaWorkTask.status eq '1' ? '查看' : not empty oaWorkTask.id ? '修改' : '添加'}</shiro:hasPermission><shiro:lacksPermission
            name="oa:oaWorkTask:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="oaWorkTask" action="${ctx}/oa/oaWorkTask/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">类型：</label>
        <div class="controls">
            <form:select path="type" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('oa_task_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">标题：</label>
        <div class="controls">
            <form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">内容：</label>
        <div class="controls">
            <form:textarea path="content" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <%--<c:if test="${oaWorkTask.status ne '1'}">--%>
        <div class="control-group">
            <label class="control-label">附件：</label>
            <div class="controls">
                <form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                <sys:ckfinder input="files" type="files" uploadPath="/oa/oaWorkTask" selectMultiple="true"/>
            </div>
        </div>
    <%--</c:if>--%>
    <div class="form-actions">
        <c:if test="${oaWorkTask.status ne '1'}">
            <shiro:hasPermission name="oa:oaWorkTask:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                                  value="保 存"/>&nbsp;</shiro:hasPermission>
        </c:if>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>