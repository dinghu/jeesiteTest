<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>工作记录</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
    <style type="text/css">

        /*td {*/
        /*border: 1px solid #ff4136;*/
        /*}*/

        .name {
            width: 40%;
        }

        .mark {
            width: 15%;
        }

        .content {
            width: 15%;
        }

        .username {
            width: 15%;
        }

        .time {
            width: 15%;
        }

        .oaLogTitlecls > a:link {
            color: #2fa4e7;
            text-decoration: none;
        }

        .oaLogTitlecls > a:visited {
            color: #551A8B;
            text-decoration: none;
        }

        .oaLogTitlecls > a:hover {
            color: #ff0000;
            text-decoration: none;
        }

        .oaLogTitlecls > a:active {
            color: #2fa4e7;
            text-decoration: none;
        }

    </style>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/oa/oaWorkLog/list">工作记录</a></li>
</ul>

<table id="contentTable" class="table table-striped table-bordered table-condensed" style="margin-top: 25px">
    <thead>
    <tr>
        <th>日期</th>
        <th>操作者</th>
        <th>内容</th>
        <th>对象</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="oaWorkLog">
        <tr>

            <td class="time">
                <fmt:formatDate value="${oaWorkLog.opTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td class="username">
                    ${oaWorkLog.fromUser.name}
            </td>

            <td class="content">
                    ${oaWorkLog.content}
            </td>

            <td class="name oaLogTitlecls">
                    <%--public static final int WORKTASK = 1;--%>
                    <%--public static final int PRODUCTINFO = 2;--%>
                    <%--public static final int PRODUCTPRICE = 3;--%>
                    <%--public static final int LOGIN = 4;--%>

                            <%--${oaWorkLog.type}--%>
                <c:choose>

                    <c:when test="${oaWorkLog.type == 1}">
                        <a href="${ctx}/oa/oaWorkTask/view?id=${oaWorkLog.oaTaskId}">${fns:abbr(oaWorkLog.name,50)}</a>
                    </c:when>
                    <c:when test="${oaWorkLog.type == 2}">
                        <a href="${ctx}/oa/productInfo/form?id=${oaWorkLog.oaTaskId}">${fns:abbr(oaWorkLog.name,50)}</a>
                    </c:when>
                    <c:when test="${oaWorkLog.type == 3}">
                        <a href="${ctx}/oa/product/form?id=${oaWorkLog.oaTaskId}">${fns:abbr(oaWorkLog.name,50)}</a>
                    </c:when>

                    <c:when test="${oaWorkLog.type == 5}">
                        <a href="${ctx}/oa/oaFeedback/detail?id=${oaWorkLog.oaTaskId}">${fns:abbr(oaWorkLog.name,50)}</a>
                    </c:when>

                </c:choose>


            </td>
            <td class="mark">
                    ${oaWorkLog.mark}
            </td>

        </tr>

    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>