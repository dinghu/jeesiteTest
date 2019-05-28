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

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 style="color:#555555;font-size:20px;text-align:center;border-bottom:1px solid #ddd;padding-bottom:15px;margin:25px 0;">
                ${oaWorkTask.title}
            </h3>
            <div>${oaWorkTask.content}</div>
            <div style="border-top:1px solid #ddd;padding:10px;margin:25px 0;">发布者：${oaWorkTask.createUid} &nbsp;
                发布时间：<fmt:formatDate value="${oaWorkTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp;
                更新时间：<fmt:formatDate value="${oaWorkTask.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
        </div>
    </div>
    <div class="row">
        <div id="comment" class="hide span10">
            正在加载任务记录...
        </div>
    </div>
</div>
</body>
</html>