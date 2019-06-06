<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>产品价格表</title>
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
    <li><a href="${ctx}/oa/product/list">产品价格表</a></li>
    <li class="active"><a href="${ctx}/oa/product/form">添加产品价格</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="product" action="${ctx}/oa/product/save" method="post"
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
        <label class="control-label">装箱体积（m³）：</label>
        <div class="controls">
            <form:input path="packingVolume" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">装箱数量：</label>
        <div class="controls">
            <form:input path="packingAmout" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">毛重（g）：</label>
        <div class="controls">
            <form:input path="grossWeight" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">单个包装尺寸（cm）：</label>
        <div class="controls">
            <form:input path="singlePackingSize" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <<label class="control-label">单价：</label>
        <div class="controls">
            <form:input path="price" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">国内运费：</label>
        <div class="controls">
            <form:input path="domesticReight" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">包装费：</label>
        <div class="controls">
            <form:input path="packingPrice" htmlEscape="false" maxlength="200"
                        class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">国际运费：</label>
        <div class="controls">
            <form:input path="internationalShipping" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">关税税率：</label>
        <div class="controls">
            <form:input path="rate" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">HS Code：</label>
        <div class="controls">
            <form:input path="hsCode" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">关税：</label>
        <div class="controls">
            <form:input path="tariff" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">当地运费：</label>
        <div class="controls">
            <form:input path="localFreight" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Ebay成本：</label>
        <div class="controls">
            <form:input path="ebayCost" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Ebay20%毛利定价：</label>
        <div class="controls">
            <form:input path="ebay20pPrice" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Amazon成本：</label>
        <div class="controls">
            <form:input path="amazonCost" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">Amazon20%毛利定价：</label>
        <div class="controls">
            <form:input path="amazon20pPrice" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">建议定价：</label>
        <div class="controls">
            <form:input path="advicePrice" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>


    <div class="control-group">
        <label class="control-label">拟调价：</label>
        <div class="controls">
            <form:input path="nitiaojia" htmlEscape="false" maxlength="200"
                        class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">厂家链接：</label>
        <div class="controls">
            <form:input path="manufacturerLink" htmlEscape="false" maxlength="200"
                        class="input-xlarge"/>
        </div>
    </div>


    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>