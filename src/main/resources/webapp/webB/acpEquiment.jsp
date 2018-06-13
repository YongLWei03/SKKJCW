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
    <script type="text/javascript"
            src="${path}/resource/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path}/resource/js/sdmenu.js"></script>
    <script type="text/javascript"
            src="${path}/resource/js/laydate/laydate.js"></script>
    <link href="${path}/resource/cs/pages.css" rel="stylesheet"
          type="text/css" />
    <script type="text/javascript" src="${path}/resource/js/layer/layer.js"></script>
    <script type="text/javascript" src="${path}/resource/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${path}/resource/js/jquery.page.js"></script>
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

<body>

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
                    <span class="divider">/</span> 设备及采集器状态信号强度
                </li>
            </ul>
            <div class="title_right">
                <strong>列表</strong>
            </div>
            <input type="hidden" id="substationId" value="${substationId}">
            <input type="hidden" id="substationImage" value="${substationImage}">
            <input type="hidden" id="deviceNumber" value="${deviceNumber}">
            <div style="width: 1200px; margin: auto;">
                <table class="table table-bordered">
                    <tr>
                        <td width="10%">
                            <input type="button"  value="返回" onclick="cksb();"class="btn btn-primary"
                                   style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                        </td>
                    </tr>
                </table>
                <table class="table table-bordered table-hover table-striped"
                       style="font-family: 微软雅黑;">
                    <tbody>
                    <tr align="center" style="height: 35px;">
                        <td width="20%">序号</td>
                        <td width="20%">采集器状态</td>
                        <td width="20%">设备状态</td>
                        <td width="20%">信号强度</td>
                        <td width="20%">时间</td>
                    </tr>

                    <c:forEach var="acpequi" items="${acpequi}" varStatus="s">
                        <tr align="center" style="height: 35px;">
                            <td>${s.index+1}</td>
                            <c:if test="${acpequi.harvesterStatus == 0 || acpequi.harvesterStatus == '0'}">
                                <td>采集器正常</td>
                            </c:if>
                            <c:if test="${acpequi.harvesterStatus == 1 || acpequi.harvesterStatus == '1'}">
                                <td>采集器使能关闭</td>
                            </c:if>
                            <c:if test="${acpequi.harvesterStatus == 2 || acpequi.harvesterStatus == '2'}">
                                <td>采集器初始化失败</td>
                            </c:if>
                            <c:if test="${acpequi.harvesterStatus == 3 || acpequi.harvesterStatus == '3'}">
                                <td>采集超时</td>
                            </c:if>
                            <c:if test="${acpequi.harvesterStatus == 4 || acpequi.harvesterStatus == '4'}">
                                <td>采集器接收数据为0</td>
                            </c:if>
                            <c:if test="${acpequi.harvesterStatus == 5 || acpequi.harvesterStatus == '5'}">
                                <td>采集器crc校验失败</td>
                            </c:if>
                            <c:if test="${acpequi.harvesterStatus == 6 || acpequi.harvesterStatus == '6'}">
                                <td>采集器异常</td>
                            </c:if>

                            <c:if test="${acpequi.boardStatus == 0 || acpequi.boardStatus == '0'}">
                                <td>设备正常</td>
                            </c:if>
                            <c:if test="${acpequi.boardStatus == 1 || acpequi.boardStatus == '1'}">
                                <td>析构失败</td>
                            </c:if>
                            <c:if test="${acpequi.boardStatus == 2 || acpequi.boardStatus == '2'}">
                                <td>已初始化</td>
                            </c:if>
                            <c:if test="${acpequi.boardStatus == 3 || acpequi.boardStatus == '3'}">
                                <td>内存超限</td>
                            </c:if>
                            <c:if test="${acpequi.boardStatus == 4 || acpequi.boardStatus == '4'}">
                                <td>未知设备</td>
                            </c:if>
                            <c:if test="${acpequi.boardStatus == 5 || acpequi.boardStatus == '5'}">
                                <td>参数异常</td>
                            </c:if>
                            <td>${acpequi.informationNumber}</td>
                            <td>${acpequi.time}</td>
                                <%--<td>--%>
                                    <%--<a href="javascript:void(0);" onclick="delet(${commandInformationList.id})">删除</a>--%>
                                <%--</td>--%>
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
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        var deviceNumber = $("#deviceNumber").val();
        location.href="${path}/webAcpEquipment/selectByDeverId?currentPage="+p+"&substationId="+substationId+"&substationImage="+substationImage+"&deviceNumber="+deviceNumber;
    }

    function cksb(){
        var substationImage = $("#substationImage").val();
        var substationId = $("#substationId").val();
        location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
    }



</script>

</body>

</html>
