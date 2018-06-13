<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <span class="divider">/</span> 设备命令情况
                </li>
            </ul>
            <div class="title_right">
                <strong>列表</strong>
            </div>
            <input type="hidden" id="substationId" value="${substationId}">
            <input type="hidden" id="substationImage" value="${substationImage}">
            <input type="hidden" id="deviceNumber" value="${deviceNumber}">
            <input type="hidden" id="currentPageSb" value="${currentPageSb}">
            <div style="width: 1200px; margin: auto;">
                <table class="table table-bordered">
                    <tr>
                        <td width="10%">
                            <input type="button"  value="返回" onclick="cksb();"class="btn btn-primary"
                                   style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                        </td>
                        <td width="10%">
                            <input type="button"  value="刷新" onclick="refresh(1);"class="btn btn-primary"
                                   style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                        </td>
                    <c:if test="${user.type == 1 || user.type == '1'}">
                        <td width="10%">
                            <input type="button"  value="撤销" onclick="colese();"class="btn btn-primary"
                                   style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                        </td>
                    </c:if>
                        <td width="70%">
                            <span style="font-size: 20px;">提示：</span>
                            <span>成功表示命令执行完成,失败表示命令执行失败,超时表示命令超过执行时间,已发送表示命令已经发送成功，送达表示命令已经送达到设备</span>
                        </td>
                    </tr>
                </table>
                <table class="table table-bordered table-hover table-striped"
                       style="font-family: 微软雅黑;">
                    <tbody>
                    <tr align="center" style="height: 35px;">
                        <td width="25%">序号</td>
                        <td width="25%">命令名称</td>
                        <td width="25%">发送时间</td>
                        <td width="25%">状态</td>
                        <%--<td width="15%">操作</td>--%>

                    </tr>

                    <c:forEach var="commandInformationList" items="${commandInformationList}" varStatus="s">
                        <input type="hidden" id="commandId${commandInformationList.id}" value="${commandInformationList.commandId}">
                        <tr align="center" style="height: 35px;">
                            <td>${s.index+1}</td>
                            <td>${commandInformationList.name}</td>
                            <td>${commandInformationList.time}</td>
                            <c:if test="${commandInformationList.type == 1 || commandInformationList.type == '1'}">
                                <c:if test="${commandInformationList.name == '效验设置'}">
                                <td>
                                    成功
                                    <a href="javascript:void(0);" onclick="cksbsx(${commandInformationList.id})">查看</a>
                                </td>
                            </c:if>
                                <c:if test="${commandInformationList.name != '效验设置'}">
                                    <td>
                                        成功
                                    </td>
                                </c:if>
                            </c:if>
                            <c:if test="${commandInformationList.type == 2 || commandInformationList.type == '2'}">
                                <td>送达</td>
                            </c:if>
                            <c:if test="${commandInformationList.type == 3 || commandInformationList.type == '3'}">
                                <td>已发送</td>
                            </c:if>
                            <c:if test="${commandInformationList.type == 4 || commandInformationList.type == '4'}">
                                <td>超时</td>
                            </c:if>
                            <c:if test="${commandInformationList.type == 5 || commandInformationList.type == '5'}">
                                <td>失败</td>
                            </c:if>

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
        location.href="${path}/webCommandInformation/select?currentPage="+p+"&substationId="+substationId+"&substationImage="+substationImage+"&deviceNumber="+deviceNumber;
    }

    function cksb(){
        var substationImage = $("#substationImage").val();
        var substationId = $("#substationId").val();
        var currentPageSb = $("#currentPageSb").val();
        if(substationId != null && substationId != ''){
            location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
        }else {
            location.href="${path}/webSkEquipment/selectEquipment?currentPage="+currentPageSb;
        }

    }

    function delet(id){
        if(confirm("确定删除吗")){
            $.ajax({
                url:'${path}/webCommandInformation/deleteById',
                data:{
                    'Id' : id
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if(data=="true"){
                        location.reload();
                    }else if(data == "false"){
                        alert("删除失败");
                    }
                }
            });
        }
    }

    function refresh(et) {
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        var deviceNumber = $("#deviceNumber").val();
        var currentPageSb = $("#currentPageSb").val();
        if (substationId != null && substationId != ''){
            location.href="${path}/webCommandInformation/select?deviceNumber="+deviceNumber+"&substationId="+substationId+"&substationImage="+substationImage;
        }else {
            location.href="${path}/webCommandInformation/select?deviceNumber="+deviceNumber+"&currentPageSb="+currentPageSb;
        }

    }

    function cksbsx(id) {
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        var deviceNumber = $("#deviceNumber").val();
        var commandId = $("#commandId"+id).val();
        location.href="${path}/webAdjust/selectByCommandId?deviceNumber="+deviceNumber+"&substationId="+substationId+"&substationImage="+substationImage+"&commandId="+commandId;
    }

    function colese() {
        var deviceNumber = $("#deviceNumber").val();
        layer.confirm('您确定要撤销发送命令吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                url:'${path}/webZnTest/scduixiang',
                data:{
                    'deviceNumber' : deviceNumber
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if(data=="true"){
                        layer.msg("清除成功")
                        location.reload();
                    }else if(data == "false"){
                        alert("删除失败");
                    }
                }
            });
        }, function(){
            layer.closeAll();
        });


    }


</script>

</body>

</html>
