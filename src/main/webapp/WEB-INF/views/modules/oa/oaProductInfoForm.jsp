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
    <li><a href="${ctx}/oa/productInfo/list">产品信息表</a></li>
    <li class="active"><a href="${ctx}/oa/productInfo/form">添加产品信息</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="productInfo" action="${ctx}/oa/productInfo/save" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">SKU：</label>
        <div class="controls">
            <form:input path="sku" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">品名：</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">关键词：</label>
        <div class="controls">
            <form:input path="keywords" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">图片：</label>
        <div class="controls">
            <form:hidden id="files" path="images" htmlEscape="false" maxlength="255" class="input-xlarge"/>
            <sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">颜色：</label>
        <div class="controls">
            <form:input path="color" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">样品：</label>
        <div class="controls">
            <form:input path="yangpin" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">样品和大货是否完全一致：</label>
        <div class="controls">
            <form:radiobuttons path="issametoyangpin" items="${fns:getDictList('oa_productInfo_issametoyangpin')}"
                               itemLabel="label"
                               itemValue="value" htmlEscape="false"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">厂家包装要求：</label>
        <div class="controls">
            <form:input path="packingRequirements" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">仓库包装要求：</label>
        <div class="controls">
            <form:input path="warehousePackagingRequirements" htmlEscape="false" maxlength="200"
                        class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">验货注意事项：</label>
        <div class="controls">
            <form:input path="checkPoints" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">单个包装方式：</label>
        <div class="controls">
            <form:input path="singlePackingway" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">销售组合方式：</label>
        <div class="controls">
            <form:input path="saleGroupway" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">产品特点及优缺点：</label>
        <div class="controls">
            <form:input path="strengthsWeaknesses" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">产品刊登建议：</label>
        <div class="controls">
            <form:input path="kandengAdvises" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">产品功能及参数：</label>
        <div class="controls">
            <form:input path="functionParams" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">产品配件：</label>
        <div class="controls">
            <form:input path="parts" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">产品主链接：</label>
        <div class="controls">
            <form:input path="link" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">备注：</label>
        <div class="controls">
            <form:input path="note" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">图片制作要求：</label>
        <div class="controls">
            <form:input path="imageProductionRequirements" htmlEscape="false" maxlength="200"
                        class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">附件：</label>
        <div class="controls">
            <form:hidden id="files" path="others" htmlEscape="false" maxlength="255" class="input-xlarge"/>
            <sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
        </div>
    </div>


    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>