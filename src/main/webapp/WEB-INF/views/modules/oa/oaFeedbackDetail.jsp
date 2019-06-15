<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>反馈详情</title>
    <meta name="decorator" content="default"/>
    <link href="jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet"/>
    <script src="jquery-jbox/2.3/jquery-1.4.2.min.js"></script>
    <script src="jquery-jbox/2.3/jquery.jBox-2.3.src.js"></script>


    <style type="text/css">

        h5 {
            color: black;
        }

        p {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="row" style="margin-left: 10px">
    <div class="span12" style="margin-top: 10px">
        <h5>
            SKU：
        </h5>

        <p>${oaFeedback.sku}</p>

        <h5>
            反馈内容：
        </h5>

        <p>${oaFeedback.content}</p>


        <c:if test="${not empty oaFeedback.images}">
            <h5>
                可放图片：
            </h5>

            <p>
                <c:if test="${fns:endsWith(oaFeedback.images,'jpg' )}">
                    <img src='${fns:replace(oaFeedback.images,"|" ,"" )}'
                         style="width: 60px;height: 60px">
                </c:if>
                <c:if test="${fns:endsWith(oaFeedback.images,'png' )}">
                    <img src='${fns:replace(oaFeedback.images,"|" ,"" )}'
                         style="width: 60px;height: 60px">
                </c:if>


            </p>
        </c:if>

        <h5>
            产品链接：
        </h5>

        <p><a href="${oaFeedback.link}" target="_blank">${oaFeedback.link}</a></p>


        <h5>
            处理部门：
        </h5>

        <p>${oaFeedback.officeInfo.name}</p>


        <h5>
            处理办法：
        </h5>

        <p>${oaFeedback.dealWay}</p>

        <h5>
            处理结果：
        </h5>

        <p>
            <c:choose>
                <c:when test="${oaFeedback.dealResult eq 1}">
                    <span style="color: red">进行中</span>
                </c:when>
                <c:when test="${oaFeedback.dealResult eq 2}">
                    <span style="color: green">已完成</span>
                </c:when>
                <c:otherwise>
                    未开始
                </c:otherwise>
            </c:choose>
        </p>


        <h5>
            反馈人：
        </h5>

        <p>${oaFeedback.solveBy.name}</p>

    </div>
</div>

<div class="form-actions">
    <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
</div>


</body>
</html>
