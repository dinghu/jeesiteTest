<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp" %>--%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<html>
<head>
    <title>任务详情</title>
    <meta name="decorator" content="default"/>
    <link href="jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet"/>
    <script src="jquery-jbox/2.3/jquery-1.4.2.min.js"></script>
    <script src="jquery-jbox/2.3/jquery.jBox-2.3.src.js"></script>
    <script type="text/javascript">
        function assignTo(title) {
            var html = "<div style='padding:10px;'><span style='width: 60px;float: left'> 指派给：</span> <input type='text' id='yourname' name='yourname' /></div>"
                + "<div style='padding:10px;'><span style='width: 60px;float: left'> 备 注：</span><input type='text' id='note' name='note' /></div>"
            var content = {
                state1: {
                    content: html,
                    buttons: {'确定': 1, '取消': 0},
                    buttonsFocus: 0,
                    submit: function (v, h, f) {
                        if (v == 0) {
                            return true; // close the window
                        } else {
                            if (f.yourname == '') {
                                $.jBox.tip("请输入您的姓名。", 'error', {focusId: "yourname"}); // 关闭设置 yourname 为焦点
                                return false;
                            }

                            $.jBox.tip("你叫：" + f.yourname);//ajax请求
                            $.jBox("iframe:" + '${ctx}/oa/oaWorkTask/list', {
                                width: 900,
                                height: 300,
                                title: "交易明细:编号" + $(this).text(),
                                buttons: {'关闭': true}
                            });
                        }
                        return false;
                    }
                }
            };

            $.jBox(content);
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

        <c:if test="${!fns:endsWith(oaWorkTask.files,'jpg' )}">
            <%--<form:hidden id="files" path="${oaWorkTask.files}" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
            <a href="${oaWorkTask.files}">${fns:jsGetFileNameFromUrl(oaWorkTask.files)}</a>
        </c:if>
    </div>
</div>
<div class="row" style="margin-left: 10px">
    <h4 class="span12" style="margin-top: 10px;margin-bottom: 10px">任务日志</h4>
    <div id="comment" class="span12">
        <c:forEach items="${oaWorkTask.oaWorkTaskLogList }" var="oaWorkTaskLog">
            <div class="col-md-2">
                <span class="name">${oaWorkTaskLog.fromUser.name}</span>
                <span class="table" style="margin-left: 10px">${oaWorkTaskLog.content}</span>
                <span
                        class="time" style="margin-left: 10px;color: #999"><fmt:formatDate
                        value="${oaWorkTaskLog.opTime}"
                        pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
            </div>
        </c:forEach>
    </div>

    <div class="span12"
         style="border-top:1px solid #ddd;margin-top:25px; margin-bottom: 25px;padding-top: 15px;color: #2fa4e7">

        <%--<button class="btn btn-default btn-xs btn-info" onClick="location.href='/admin/editStudent?id=${item.userid}'">--%>
        <%--修改--%>
        <%--</button>--%>
        <i class="icon-circle-arrow-right">&nbsp;<a href="${ctx}/oa/oaNotify/form?id=${oaNotify.id}"
                                                    style="margin-left: 2px"
                                                    onclick="return assignTo('${oaWorkTask.title}')">指派</a></i>
        &nbsp;
        &nbsp;
        <i class="icon-pencil">&nbsp;<a href="${ctx}/oa/oaNotify/delete?id=${oaNotify.id}"
                                        style="margin-left: 2px"
                                        onclick="return confirmx('确认要完成该任务吗？', this.href)">完成</a></i>

        <%--<select name="schoolId" id="schoolId" style="width: 95%">--%>
        <%--<option value="0">==请选择==</option>--%>

        <%--<c:forEach items="${oaWorkTask.oaWorkTaskLogList}" var="oaWorkTaskLog" varStatus="vs">--%>
        <%--<option value="${oaWorkTask.id}"--%>
        <%--&lt;%&ndash;<c:if test="${var.name_code==schoolid}">selected</c:if> > ${var.name}</option>&ndash;%&gt;--%>
        <%--</c:forEach>--%>
        <%--</select>--%>
    </div>
</div>

<div class="form-actions">
    <%--<shiro:hasPermission name="oa:leave:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"--%>
    <%--value="保 存"/>&nbsp;</shiro:hasPermission>--%>
    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
</div>

</body>
</html>