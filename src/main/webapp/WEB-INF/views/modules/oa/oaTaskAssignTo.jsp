<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>指派任务</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
</head>
<body>
<form:form id="inputForm" modelAttribute="oaWorkTask" action="${ctx}/oa/assignto/assignto" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">指派给：</label>
        <div class="controls">

            <form:select path="ownerUid" class="input-xlarge required">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('oa_notify_type')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>

        <div class="control-group">
            <label class="control-label">备注:</label>
            <div class="controls">
                <input id="note" name="note" type="text" value="" maxlength="50" minlength="3"
                       class="required"/>
            </div>
        </div>
    </div>

    <div class="form-actions">
        <c:if test="${oaNotify.status ne '1'}">
            <shiro:hasPermission name="oa:oaNotify:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                                value="保 存"/>&nbsp;</shiro:hasPermission>
        </c:if>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>