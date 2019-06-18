<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%--<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>--%>
<html>
<head>
    <title>任务详情</title>
    <meta name="decorator" content="default"/>
    <link href="jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet"/>
    <script src="jquery-jbox/2.3/jquery-1.4.2.min.js"></script>
    <script src="jquery-jbox/2.3/jquery.jBox-2.3.src.js"></script>
    <script type="text/javascript">
        function assignTo(title, id) {
            var assigntoUrl = '${ctx}/oa/oaWorkTask/assignto?id=' + id;
            var tarUrl = "iframe:" + assigntoUrl;
            console.log(tarUrl);
            top.$.jBox(tarUrl, {
                width: 550,
                height: 300,
                title: title,
                buttons: {'确定': true},
                submit: function (v, h, f) {
                    var data = h.find("iframe")[0].contentWindow.$("#inputForm").serialize();
                    console.log(data);
                    var url = "${ctx}/oa/oaWorkTask/assigntoSave";
                    $.ajax({
                        type: 'POST',
                        url: url,
                        data: data,             //获取表单数据
                        success: function (data) {
                            var result = JSON.parse(data); //可以将json字符串转换成json对象
                            if (result.code == 0) {
                                top.$.jBox.tip('保存成功');
                                location = '${ctx}/oa/oaWorkTask/self?repage';
                                // history.go(-1);
                            } else {
                                top.$.jBox.tip("保存失败:" + data.message);
                            }
                        }
                    });
                },
                loaded: function (h) {
                    $(".jbox-content", top.document).css("overflow-y", "hidden");
                }
            });
            return false;
        }
    </script>
</head>
<body>


<div class="row" style="margin-left: 10px">
    <div class="span12" style="margin-top: 10px">
        <h3 style="color:#555555;font-size:20px;text-align:center;border-bottom:1px solid #ddd;margin-top:25px;margin-bottom: 25px">
            ${oaWorkTask.title}
        </h3>
        <div>${oaWorkTask.content}</div>
        <div style="border-top:1px solid #ddd;margin-top:25px; margin-bottom: 25px;padding-top: 15px">
            发布者：${oaWorkTask.createUser.name} &nbsp;
            发布时间：<fmt:formatDate value="${oaWorkTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;
            更新时间：<fmt:formatDate value="${oaWorkTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>

        <c:if test="${not empty oaWorkTask.files}">
            <div style="margin-top: 25px">
                <span style="display: block">附件：</span>
            </div>
        </c:if>

        <c:if test="${fns:endsWith(oaWorkTask.files,'jpg' )}">
            <img src='${oaWorkTask.files}' style="margin-top: 10px">
        </c:if>
        <c:if test="${fns:endsWith(oaWorkTask.files,'png' )}">
            <img src='${oaWorkTask.files}' style="margin-top: 10px">
        </c:if>

        <%--<c:if test="${!fns:endsWith(oaWorkTask.files,'jpg' )}">--%>
        <%--&lt;%&ndash;<form:hidden id="files" path="${oaWorkTask.files}" htmlEscape="false" maxlength="255" class="input-xlarge"/>&ndash;%&gt;--%>
        <%--<a href="${oaWorkTask.files}">${fns:jsGetFileNameFromUrl(oaWorkTask.files)}</a>--%>
        <%--</c:if>--%>
    </div>
</div>
<div class="row" style="margin-left: 10px">
    <h4 class="span12" style="margin-top: 10px;margin-bottom: 10px">任务日志</h4>
    <div id="comment" class="span12">
        <c:forEach items="${oaWorkTask.oaWorkTaskLogList }" var="oaWorkTaskLog">
            <div class="col-md-2" style="margin-top: 6px">
                <span class="name">${oaWorkTaskLog.fromUser.name}</span>
                <span class="table" style="margin-left: 10px">${oaWorkTaskLog.content}</span>
                <span class="time" style="margin-left: 10px;color: #999">
                    <fmt:formatDate
                            value="${oaWorkTaskLog.opTime}"
                            pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
                <c:if test="${not empty oaWorkTaskLog.mark}">
                    <span class="mark" style="margin-left: 15px;color: red">备注：&nbsp; ${oaWorkTaskLog.mark}</span>
                </c:if>
            </div>
        </c:forEach>
    </div>

    <div class="span12"
         style="border-top:1px solid #ddd;margin-top:25px; margin-bottom: 25px;padding-top: 15px;color: #2fa4e7">

        <%--<button class="btn btn-default btn-xs btn-info" onClick="location.href='/admin/editStudent?id=${item.userid}'">--%>
        <%--修改--%>
        <%--</button>--%>
        <i class="icon-circle-arrow-right">&nbsp;<a style="margin-left: 2px"
                                                    onclick="return assignTo('${oaWorkTask.title}','${oaWorkTask.id}')">指派</a></i>

        <c:if test="${oaWorkTask.status eq 0}">
            &nbsp;
            &nbsp;
            <i class="icon-pencil">&nbsp;<a href="${ctx}/oa/oaWorkTask/start?id=${oaWorkTask.id}"
                                            style="margin-left: 2px"
                                            onclick="return confirmx('要开始该任务吗？', this.href)">开始</a></i>
        </c:if>
        <c:if test="${oaWorkTask.status eq 2}">
            &nbsp;
            &nbsp;
            <i class="icon-pencil">&nbsp;<a href="${ctx}/oa/oaWorkTask/start?id=${oaWorkTask.id}"
                                            style="margin-left: 2px"
                                            onclick="return confirmx('要激活该任务吗？', this.href)">激活</a></i>
        </c:if>
        <c:if test="${oaWorkTask.status ne 2}">
            &nbsp;
            &nbsp;
            <i class="icon-pencil">&nbsp;<a href="${ctx}/oa/oaWorkTask/finish?id=${oaWorkTask.id}"
                                            style="margin-left: 2px"
                                            onclick="return confirmx('确认完成该任务吗？', this.href)">完成</a></i>
        </c:if>
    </div>
</div>

<div class="form-actions">
    <%--<shiro:hasPermission name="oa:leave:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"--%>
    <%--value="保 存"/>&nbsp;</shiro:hasPermission>--%>
    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
</div>

</body>
</html>