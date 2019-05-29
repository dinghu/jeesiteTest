<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp" %>--%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<html>
<head>
    <title>任务详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
    </script>
</head>
<body>


<div class="row" style="margin-left: 10px">
    <div class="span12" style="margin-top: 10px">
        <h3 style="color:#555555;font-size:20px;text-align:center;border-bottom:1px solid #ddd;margin-top:25px;margin-bottom: 25px">
            ${oaWorkTask.title}
        </h3>
        <div>${oaWorkTask.content}</div>
        <div style="border-top:1px solid #ddd;margin-top:25px; margin-bottom: 25px;padding-top: 15px">发布者：${oaWorkTask.createUid} &nbsp;
            发布时间：<fmt:formatDate value="${oaWorkTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;
            更新时间：<fmt:formatDate value="${oaWorkTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
    </div>
</div>
<div class="row" style="margin-left: 10px">
    <h4 class="span12" style="margin-top: 10px;margin-bottom: 10px">任务日志</h4>
    <div id="comment" class="span12">
        <c:forEach items="${oaWorkTask.oaWorkTaskLogList }" var="oaWorkTaskLog">
            <div class="col-md-2">
                <span class="name">杨荣</span>
                <span class="table" style="margin-left: 10px">${oaWorkTaskLog.content}</span>
                <span
                        class="time" style="margin-left: 10px;color: #999"><fmt:formatDate
                        value="${oaWorkTaskLog.opTime}"
                        pattern="yyyy-MM-dd HH:mm:ss"/>
                </span>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>