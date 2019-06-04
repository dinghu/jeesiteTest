<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html style="overflow: hidden">
<head>
    <title>指派任务</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
    <style type="text/css">
        .input-small {
            width: 120px;
        }

        body {
            overflow-x: hidden;
            overflow-y: hidden;
        }
    </style>
</head>
<body>
<form:form id="inputForm" modelAttribute="oaWorkTask" action="${ctx}/oa/oaWorkTask/save" method="post"
           class="form-horizontal" cssStyle="overflow-y: hidden">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group" style="margin-top: 25px;max-height: 200px">
        <label class="control-label">指派给：</label>
        <div class="controls">
            <form:select id="ownerUid" path="ownerUid" class="input-xlarge required" cssStyle="max-height: 120px;" size="100">
                <form:option value="" label=""/>
                <form:options items="${fns:getUsersByRoleId('6')}" itemLabel="name" itemValue="id"
                              htmlEscape="false"/>
            </form:select>

                <%--<select size="3" class="input-xlarge required">--%>
                    <%--<c:forEach items="${fns:getUsersByRoleId('6')}" var="user">--%>
                        <%--<option value="${user.id}">${user.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>


    <div class="control-group">
        <label class="control-label">备注:</label>
        <div class="controls">
            <input id="note" name="note" type="text" value="" maxlength="50" minlength="3" style="width: 256px"
                   class="required"/>
        </div>
    </div>

</form:form>
</body>
</html>