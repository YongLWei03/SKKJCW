<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/resource/common/common.jsp"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>信息管理平台</title>

    <link rel="stylesheet" href="${path}/resource/cs/bootstrap.css" />
    <link rel="shortcut icon" href="${path}/resource/image/sk.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${path}/resource/cs/css.css" />
    <link rel="stylesheet" href="${path}/resource/js/layer/skin/layer.css" />
    <link rel="stylesheet" href="${path}/resource/js/layui/css/layui.css" />
    <script type="text/javascript"
            src="${path}/resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path}/resource/js/sdmenu.js"></script>
    <script type="text/javascript"
            src="${path}/resource/js/laydate/laydate.js"></script>
    <link href="${path}/resource/cs/pages.css" rel="stylesheet"
          type="text/css" />
    <script type="text/javascript" src="${path}/resource/js/layer/layer.js"></script>
    <script type="text/javascript" src="${path}/resource/js/layui/layui.js"></script>
    <script type="text/javascript" src="${path}/resource/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${path}/resource/js/jquery.page.js"></script>
    <script type="text/javascript" src="${path}/resource/js/echarts.js"></script>
    <script type="text/javascript" src="${path}/resource/js/vintage.js"></script>
    <script type="text/javascript" src="${path}/resource/js/news.js"></script>
    <style type="text/css">
        .dialog { /* 	background-color: white; */
            height: 100%;
            left: 30%;
            margin: -200px 0 0 -200px;
            padding: 1px;
            position: fixed !important; /* 浮动对话框 */
            position: absolute;
            top: 20%;
            width: 100%;
            z-index: 5;
            border-radius: 5px;
            display: none;
        }
    </style>
</head>

<body background="${substationImage }" style="background-repeat:no-repeat; background-attachment: fixed; background-size: 100% 100%;">
<!-- 顶部 -->
<%@include file="/resource/common/header.jsp"%>
<div id="middle">
    <%@include file="/resource/common/left.jsp"%>

    <div class="right" id="mainFrame">

        <div class="right_cont">
            <ul class="breadcrumb" style="font-family: 微软雅黑;">
                <li>当前位置： <a href="javascript:void(0);"
                             style="text-decoration: none;">首页</a> <span class="divider">/</span>
                    <a href="javascript:void(0);" style="text-decoration: none;">设备</a>
                    <span class="divider">/</span> 报警前数据情况
                </li>
            </ul>
            <div class="title_right">
                <strong>列表</strong>
            </div>
            <div style="width: 1200px; margin: auto;">
                <input type="hidden" value="${time}" id="time">
                <input type="hidden" value="${deviceNumber}" id="deviceNumber">
                <input type="hidden" value="${substationId}" id="substationId">
                <input type="hidden" value="${substationImage}" id="substationImage">
                <input type="hidden" value="${state}" id="state">
                <input type="hidden" value="${type}" id="type">
                <input type="hidden" value="${startTime}" id="startTime">
                <input type="hidden" value="${endTime}" id="endTime">
                <input type="hidden" value="${keyword}" id="keyword">
                <input type="hidden" value="${ssty}" id="ssty">
                <table class="table table-bordered">
                    <tr>
                        <td width="5%" style="text-align: center;" ><input
                                type="button"   value="返回" onclick="returns();"class="btn btn-primary"
                                style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                        </td>
                        <td></td>
                    </tr>
                </table>
                <table class="table table-bordered table-hover table-striped"
                       style="font-family: 微软雅黑;">
                    <tbody>
                    <tr align="center" style="height: 35px;">
                        <td width="6%">序号</td>
                        <td width="6%">A相入</td>
                        <td width="6%">B相入</td>
                        <td width="6%">C相入</td>
                        <c:if test="${number == 6 || number == '6' || number == 9 || number == '9' || number == 12 || number == '12'}">
                            <td width="6%">A相出</td>
                            <td width="6%">B相出</td>
                            <td width="6%">C相出</td>
                        </c:if>
                    <c:if test="${number == 9 || number == '9' || number == 12 || number == '12'}">
                        <td width="7%">A线盘入</td>
                        <td width="7%">B线盘入</td>
                        <td width="7%">C线盘入</td>
                    </c:if>
                    <c:if test="${number == 12 || number == '12'}">
                        <td width="7%">A线盘出</td>
                        <td width="7%">B线盘出</td>
                        <td width="7%">C线盘出</td>
                    </c:if>
                        <td width="16%">时间</td>
                        <%--<td width="15%">操作</td>--%>
                    </tr>

                    <c:forEach var="alarmHistoryList" items="${alarmHistoryList}" varStatus="s">
                        <tr align="center" style="height: 35px;">
                            <td>${s.index+1}</td>
                            <td>${alarmHistoryList.inA}</td>
                            <td>${alarmHistoryList.inB}</td>
                            <td>${alarmHistoryList.inC}</td>
                            <c:if test="${number == 6 || number == '6' || number == 9 || number == '9' || number == 12 || number == '12'}">
                                <td>${alarmHistoryList.outA}</td>
                                <td>${alarmHistoryList.outB}</td>
                                <td>${alarmHistoryList.outC}</td>
                            </c:if>
                            <c:if test="${number == 9 || number == '9' || number == 12 || number == '12'}">
                                <td>${alarmHistoryList.contactA}</td>
                                <td>${alarmHistoryList.contactB}</td>
                                <td>${alarmHistoryList.contactC}</td>
                            </c:if>
                            <c:if test="${number == 12 || number == '12'}">
                                <td>${alarmHistoryList.contactD}</td>
                                <td>${alarmHistoryList.contactE}</td>
                                <td>${alarmHistoryList.contactF}</td>
                            </c:if>
                            <td>${alarmHistoryList.time}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <table>
                    <tr>
                        <td class="text-center">
                            <div class="tcdPageCode"></div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->
<%@include file="/resource/common/footer.jsp"%>

<script type="text/javascript">
    //分页初始化
    $(".tcdPageCode").createPage({
        pageCount:<%=request.getAttribute("pageCount") %>,
        current:<%=request.getAttribute("currentPage") %>,
        backFn:function(p){
            goback(p);
        }
    });

    //分页
    function goback(p){
        var time = $("#time").val();
        var deviceNumber = $("#deviceNumber").val();
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        location.href="${path}/webAlarmHistory/selectAlarmHistory?currentPage="+p+"&time="+time+"&deviceNumber="+deviceNumber+"&substationId="+substationId+"&substationImage="+substationImage;
    }

    function returns() {
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        var state = $("#state").val();
        var type = $("#type").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var keyword = $("#keyword").val();
        var ssty = $("#ssty").val();
        if(ssty != null && ssty != ''){
            var deviceNumber = $("#deviceNumber").val();
            location.href="${path}/webAlarmInformation/selectAlarmInformation?deviceNumber="+deviceNumber+"&substationId="+substationId+"&substationImage="+substationImage+"&state="+state+"&type="+type+"&startTime="+encodeURI(encodeURI(startTime))+"&endTime="+encodeURI(encodeURI(endTime))+"&keyword="+encodeURI(encodeURI(keyword));
        }else {
            location.href="${path}/webAlarmInformation/selectAlarmInformation?substationId="+substationId+"&substationImage="+substationImage+"&state="+state+"&type="+type+"&startTime="+encodeURI(encodeURI(startTime))+"&endTime="+encodeURI(encodeURI(endTime))+"&keyword="+encodeURI(encodeURI(keyword));
        }


    }
</script>

</body>

</html>
