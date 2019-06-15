<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户售后反馈管理</title>
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
    <li><a href="${ctx}/oa/oaFeedback/">反馈列表</a></li>
    <li class="active"><a href="${ctx}/oa/oaFeedback/form?id=${oaFeedback.id}">反馈<shiro:hasPermission
            name="oa:oaFeedback:edit">${not empty oaFeedback.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="oa:oaFeedback:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="oaFeedback" action="${ctx}/oa/oaFeedback/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">部门：</label>
        <div class="controls">
            <sys:treeselect id="officeInfo" name="officeInfo.id" value="${oaFeedback.officeInfo.id}" labelName=""
                            labelValue="${oaFeedback.officeInfo.name}"
                            title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true"
                            notAllowSelectParent="true" isAll="true"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">SKU：</label>
        <div class="controls">
            <form:input path="sku" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">反馈内容：</label>
        <div class="controls">
            <form:textarea path="content" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge required"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">图片：</label>
        <div class="controls">
            <form:hidden id="images" path="images" htmlEscape="false" maxlength="128" class="input-xlarge"/>
            <sys:ckfinder input="images" type="files" uploadPath="/oa/oaFeedback" selectMultiple="true"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">产品链接：</label>
        <div class="controls">
            <form:textarea path="link" htmlEscape="false" rows="4" maxlength="128" class="input-xxlarge "/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">买家信息：</label>
        <div class="controls">
            <form:textarea path="buyerinfo" htmlEscape="false" rows="4" maxlength="128" class="input-xxlarge "/>
        </div>
    </div>

    <%--<div class="control-group">--%>
        <%--<label class="control-label">原因：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:textarea path="reson" htmlEscape="false" rows="4" maxlength="128" class="input-xxlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
        <%--<label class="control-label">可放链接：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:textarea path="linkmaybe" htmlEscape="false" rows="4" maxlength="128" class="input-xxlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>





    <%--<div class="control-group">--%>
        <%--<label class="control-label">处理结果：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:input path="dealResult" htmlEscape="false" maxlength="11" class="input-xlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="control-group">--%>
        <%--<label class="control-label">信息处理人：</label>--%>
        <%--<div class="controls">--%>
            <%--<form:input path="solveBy.id" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="control-group">
        <label class="control-label">信息处理人：</label>
        <div class="controls">
            <sys:treeselect id="solveBy" name="solveBy.id" value="${oaFeedback.solveBy.id}"
                            labelName="" labelValue="${oaFeedback.solveBy.name}"
                            title="用户" url="/sys/office/treeData?type=3" notAllowSelectParent="true" checked="true" isAll="true" cssClass="required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">处理办法：</label>
        <div class="controls">
            <form:textarea path="dealWay" htmlEscape="false" rows="4" maxlength="128" class="input-xxlarge "/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">备注：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="32" class="input-xxlarge "/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="oa:oaFeedback:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"
                                                              value="保 存"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>