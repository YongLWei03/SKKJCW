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
    <script type="text/javascript" src="${path}/resource/js/news.js"></script>
    <script type="text/javascript" src="${path}/resource/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${path}/resource/js/jquery.page.js"></script>
    <script type="text/javascript"
            src="${path}/resource/js/ajaxfileupload.js"></script>

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
        .load {
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            display: none;
        }

        .load .model {
            background: #fff;
            width: 100%;
            height: 100%;
            opacity: 0.5;
        }

        .load .move {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background: url(/resource/image/timg.gif) no-repeat center;
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
                    <a href="javascript:void(0);" style="text-decoration: none;">管理</a>
                    <span class="divider">/</span> 设备成品库
                </li>
            </ul>
            <div class="title_right">
                <strong>列表</strong>
            </div>
            <input type="hidden" value="${user.type }" id= "userType">
            <input type="hidden" value="0" id= "ck">
            <input type="hidden" value="0" id= "kqsn">
            <input type="hidden" value="${currentPage}" id= "currentPageSb">
            <div style="width: 1200px; margin: auto;">
                <table class="table table-bordered">
                    <tr>
                            <td width="10%">
                                <c:if test="${user.type == 1 || user.type == '1'}">
                                    <input
                                            type="button" id = "inuser"  value="添加开关柜" onclick="inser();"class="btn btn-primary"
                                            style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                                </c:if>
                            </td>
                            <td width="10%">
                                <input type="text" id="code"  value="${code }" style="width: 140px; height: 28px; font-family: 微软雅黑;" placeholder="IMEI码号"/>
                            </td>
                            <td width="10%">
                                <input type="button" id = "selec"  value="查询" onclick="goback(1);"class="btn btn-primary"
                                       style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                            </td>

                            <td width="10%">
                                <input type="button" id = "selec1"  value="批量添加" onclick="inserAll();"class="btn btn-primary"
                                       style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                            </td>
                        <td></td>
                    </tr>
                </table>

                <table class="table table-bordered table-hover table-striped"
                       style="font-family: 微软雅黑;">
                    <tbody>
                    <tr align="center" style="height: 35px;">
                        <td width="20%">序号</td>
                        <%--<td width="5%">开关柜</td>--%>
                        <%--<td width="5%">负责人</td>--%>
                        <%--<td width="5%">联系方式</td>--%>
                        <%--<td width="5%">实时数据</td>--%>
                        <%--<td width="8%">告警信息</td>--%>
                        <%--<c:if test="${user.type == 1 || user.type == '1'}">--%>
                            <td width="20%">设备名称</td>
                            <td width="20%">设备ID</td>
                            <td width="20%">设备迁移</td>
                            <%--<td width="8%">设备状态</td>--%>
                            <%--<td width="10%">设备参数</td>--%>
                            <%--<td width="5%">采样模式</td>--%>
                            <%--<td width="7%">在线校准</td>--%>

                        <%--</c:if>--%>

                        <c:if test="${user.type == 1 || user.type == '1' || user.type == 2 || user.type == '2'}">
                            <td width="20%">操作</td>
                        </c:if>
                    </tr>
                    <c:forEach var="equilist" items="${equilist}" varStatus="s">
                        <input type="hidden" value="${equilist.deviceNumber}" id= "deviceNumber${equilist.eqId}">
                        <input type="hidden" value="${equilist.numberDevices}" id= "numberDevices${equilist.eqId}">
                        <input type="hidden" value="${equilist.deviceSbId}" id= "deviceSbId${equilist.eqId}">
                        <input type="hidden" value="${equilist.deviceType}" id= "deviceType${equilist.eqId}">
                        <input type="hidden" value="${equilist.isRoot}" id= "isRoot${equilist.eqId}">
                        <tr align="center" style="height: 35px;">
                            <td>${s.index+1}</td>
                            <%--<td style="font-weight:bold;">${equilist.equipmentxi}</td>--%>
                            <%--<td style="font-weight:bold;">${equilist.headOfEquipment}</td>--%>
                            <%--<td style="font-weight:bold;">${equilist.phone}</td>--%>
                            <%--<td><a href="javascript:void(0);" onclick="selectEqui(${equilist.eqId})">查看</a></td>--%>
                            <%--<td>--%>

                                    <%--<a href="javascript:void(0);" style="" onclick="police(${equilist.eqId});">查看</a>--%>

                            <%--</td>--%>
                            <%--<c:if test="${user.type == 1 || user.type == '1'}">--%>
                                <td>${equilist.deviceName}</td>
                                <td>${equilist.deviceSbId}</td>
                                <td>
                                    <select id = "subid${equilist.eqId}" style="height: 28px; width: 200px;" onchange="updateBysubIdAndkgg(${equilist.eqId})">
                                        <option value="0">
                                                请选择
                                        </option>
                                    <c:forEach var="subsList" items="${subsList}">
                                        <c:if test="${equilist.substationId != null && equilist.substationId != '' && equilist.substationId == subsList.substationId}">
                                            <option value="${subsList.substationId}" selected="selected">
                                                    ${subsList.substationName}
                                            </option>
                                        </c:if>
                                        <c:if test="${equilist.substationId == null || equilist.substationId == '' || equilist.substationId != subsList.substationId}">
                                            <option value="${subsList.substationId}">
                                                    ${subsList.substationName}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                    </select>
                                </td>
                                <%--<td width="5%">--%>
                                        <%--&lt;%&ndash;<c:if test="${captureEnable == null || captureEnable == '' || captureEnable == '0' || captureEnable == 0}">&ndash;%&gt;--%>
                                    <%--<a href="javascript:void(0);" onclick="sbcjq(${equilist.eqId})">查看</a>--%>
                                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                    <%--<a href="javascript:void(0);" onclick="setEqui(${equilist.eqId})">设置</a>--%>
                                    <%--<a href="javascript:void(0);" onclick="sercysn(${equilist.eqId})">查看</a>--%>
                                <%--</td>--%>
                                <%--<td width="10%">--%>
                                        <%--&lt;%&ndash;<c:if test="${captureEnable == null || captureEnable == '' || captureEnable == '0' || captureEnable == 0}">&ndash;%&gt;--%>
                                    <%--<a href="javascript:void(0);" onclick="cxmlfs(${equilist.eqId})">设置</a>--%>
                                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                <%--</td>--%>

                                <%--<td width="5%">--%>
                                        <%--&lt;%&ndash;<c:if test="${captureEnable == null || captureEnable == '' || captureEnable == '0' || captureEnable == 0}">&ndash;%&gt;--%>
                                    <%--<a href="javascript:void(0);" onclick="calibration(${equilist.eqId})">设置</a>--%>
                                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                <%--</td>--%>
                            <%--</c:if>--%>

                            <c:if test="${user.type == 1 || user.type == '1' || user.type == 2 || user.type == '2'}">
                                <td >
                                    <a href="javascript:void(0);" onclick="update(${equilist.eqId})">修改</a>
                                    <a href="javascript:void(0);" onclick="delet(${equilist.eqId})">删除</a>
                                    <%--<a href="javascript:void(0);" onclick="selectml(${equilist.eqId})">命令</a>--%>
                                </td>
                            </c:if>
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
    var swId =[];
    var swIdpd ="";

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
        var code = $("#code").val();
        location.href="${path}/webSkEquipment/selectEquipment?currentPage="+p+"&code="+code;
    }




    function delet(eqId){
        var deviceNumber = $("#deviceNumber"+eqId).val();
        if(confirm("确定删除吗")){
            $.ajax({
                url:'${path}/webSkEquipment/deletByeqId',
                data:{
                    'eqId' : eqId,
                    'deviceNumber' : deviceNumber
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

    function inser(){
        var currentPageSb = $("#currentPageSb").val();
        location.href="${path}/webSkEquipment/insert?currentPageSb="+currentPageSb;
    }

    function update(eqId){
        var currentPageSb = $("#currentPageSb").val();
        location.href="${path}/webSkEquipment/findByeqId?eqId="+eqId+"&currentPageSb="+currentPageSb;
    }

    function selectEqui(eqId){
        var deviceNumber = $('#deviceNumber'+eqId).val();
        var numberDevices = $("#numberDevices"+eqId).val();
        var isRoot = $("#isRoot"+eqId).val();
        var deviceType = $("#deviceType"+eqId).val();
        var deviceSbId = $("#deviceSbId"+eqId).val();
        var currentPageSb = $("#currentPageSb").val();
        if(numberDevices != null && numberDevices != ""){
            location.href="${path}/webTemperatureDetection/realTimeReditect?deviceNumber="+deviceNumber+"&numberDevices="+numberDevices+"&isRoot="+isRoot+"&deviceSbId="+deviceSbId+"&deviceType="+deviceType+"&currentPageSb="+currentPageSb;
        }else {
            layer.msg('信息填写不完整无法查看');
        }

    }

    function setEqui(eqId) {
        var deviceSbIda = $("#deviceSbId"+eqId).val();
        var DeviceType = $("#deviceType"+eqId).val();
        if(deviceSbIda != null && deviceSbIda != ''){
            layer.open({
                type: 1,
                title: false,
                closeBtn: 1,
                shadeClose: false,
                area: ['75%', '80%'],
                skin: 'yourclass',
                btn: ['设置', '取消'] //只是为了演示
                ,yes: function(){
                    var csa = $("#csu").val();
                    if (csa == "0" || csa == 0){
                        $("#csu").val(1);
                        layer.load(1);
                        setTimeout(function(){
                            var sensorAddr = $("#sensorAddr").val();
                            var sensorNum = $("#sensorNum").val();
                            var capturePeriod = $("#capturePeriod").val();
                            // var eqIds = $("#eqId").val();
                            var deviceSbId = $("#deviceSbId"+eqId).val();
                            var deviceNumber = $("#deviceNumber"+eqId).val();
                            var underTemp = $("#underTemp").val();
                            var overTemp = $("#overTemp").val();
                            var overSignal = $("#overSignal").val();
                            var underSignal = $("#underSignal").val();
                            var modifyDeviceType = $("#modifyDeviceType").val();
                            var reportPeriod = $("#reportPeriod").val();
                            var captureMode = $("#captureMode").val();
                            var isRoot = $("#isRoot"+eqId).val();
                            var isAutoMode = $("#isAutoMode").val();
                            var zoneInTatol = $("#zoneInTatol").val();
                            var zoneInNum = $("#zoneInNum").val();
                            var southIP = $("#southIP").val();
                            var southPort = $("#southPort").val();
                            var isModifyDeviceType="";
                            if(sensorAddr == null || sensorAddr == ''){
                                $("#csu").val(0);
                                layer.msg('传感器地址不能为空')
                                layer.closeAll('loading');
                                return false;
                            }else {
                                if(sensorAddr > 255){
                                    $("#csu").val(0);
                                    layer.msg('传感器地址不能超过255')
                                    layer.closeAll('loading');
                                    return false;
                                }
                            }
                            if(DeviceType == null || DeviceType == ''){
                                $("#csu").val(0);
                                layer.msg('设备类型不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(sensorNum == null || sensorNum == ''){
                                $("#csu").val(0);
                                layer.msg('传感器个数不能为空')
                                layer.closeAll('loading');
                                return false;
                            }

                            if(swId.length==0){
                                $("#csu").val(0);
                                layer.msg('传感器不能为空')
                                layer.closeAll('loading');
                                return false;
                            }else if (swId.length < sensorNum){
                                $("#csu").val(0);
                                layer.msg('传感器个数不正确')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(underTemp == null || underTemp == ''){
                                $("#csu").val(0);
                                layer.msg('温度上限值个数不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(overTemp == null || overTemp == ''){
                                $("#csu").val(0);
                                layer.msg('温度下限值个数不能为空')
                                return false;
                            }
                            if(overSignal == null || overSignal == ''){
                                $("#csu").val(0);
                                layer.msg('温度信号下限值个数不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(underSignal == null || underSignal == ''){
                                $("#csu").val(0);
                                layer.msg('温度信号上限值个数不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(reportPeriod == null || reportPeriod == ''){
                                $("#csu").val(0);
                                layer.msg('读取时间不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(isRoot == null || isRoot == ''){
                                $("#csu").val(0);
                                layer.msg('节点不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(zoneInTatol == null || zoneInTatol == ''){
                                if(captureMode == '1' || captureMode == 1 || captureMode == '0' || captureMode == 0){
                                    zoneInTatol='0';
                                }else {
                                    $("#csu").val(0);
                                    layer.msg('区域内总个数不能为空')
                                    layer.closeAll('loading');
                                    return false;
                                }

                            }
                            if(zoneInNum == null || zoneInNum == ''){
                                if(captureMode == '1' || captureMode == 1 || captureMode == '0' || captureMode == 0){
                                    zoneInNum = '0';
                                }else {
                                    $("#csu").val(0);
                                    layer.msg('区域内编号不能为空')
                                    layer.closeAll('loading');
                                    return false;
                                }
                            }

                            if(capturePeriod == null || capturePeriod == ''){
                                $("#csu").val(0);
                                layer.msg('采集器采集周期不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(captureMode == null || captureMode == ''){
                                $("#csu").val(0);
                                layer.msg('使能采样不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(isAutoMode == null || isAutoMode == ''){
                                $("#csu").val(0);
                                layer.msg('是否为自动配置不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(southIP == null || southIP == ''){
                                $("#csu").val(0);
                                layer.msg('IP不能为空')
                                layer.closeAll('loading');
                                return false;
                            }else {
                                if(southIP.length > 17){
                                    layer.msg('IP长度不正确');
                                    layer.closeAll('loading');
                                    return false;
                                }
                            }
                            if(southPort == null || southPort == ''){
                                $("#csu").val(0);
                                layer.msg('端口号不能为空')
                                layer.closeAll('loading');
                                return false;
                            }else {
                                if(southPort.length > 6){
                                    layer.msg('端口长度不正确');
                                    layer.closeAll('loading');
                                    return false;
                                }
                            }
                            if(modifyDeviceType == DeviceType){
                                isModifyDeviceType = 0;
                            }else {
                                isModifyDeviceType = 1;
                            }
                            $.ajax({
                                url:'${path}/webCommand/boardLevel',
                                data:{
                                    'sensorAddr' : sensorAddr,
                                    'DeviceType' : modifyDeviceType,
                                    'sensorNum' : sensorNum,
                                    'swId' : swId,
                                    'underTemp' : underTemp,
                                    'overTemp' : overTemp,
                                    'overSignal' : overSignal,
                                    'underSignal' : underSignal,
                                    'reportPeriod' : reportPeriod,
                                    'zoneInTatol' : zoneInTatol,
                                    'zoneInNum' : zoneInNum,
                                    'isRoot' : isRoot,
                                    'deviceNumber' : deviceNumber,
                                    'deviceSbId' : deviceSbId,
                                    'deviceId' : eqId,
                                    'captureMode' : captureMode,
                                    'capturePeriod' : capturePeriod,
                                    'isAutoMode' : isAutoMode,
                                    'southIP' : southIP,
                                    'southPort' : southPort,
                                    'isModifyDeviceType' : isModifyDeviceType,
                                    'modifyDeviceType' : modifyDeviceType,
                                },
                                type:'post',
                                cache:false,
                                dataType:'json',
                                traditional:true,
                                success:function(data) {
                                    if(data=="true"){
                                        layer.msg("命令发送成功请点击命令查看当前命令执行情况");
                                        swId = [];
                                        swIdpd = "";
                                       $("#deviceType"+eqId).val(modifyDeviceType);
                                        setTimeout(function(){
                                            layer.closeAll();
                                        }, 2000);
                                    }else if(data == "false"){
                                        $("#csu").val(0);
                                        layer.msg('数据上报发生错误')
                                        layer.closeAll('loading');
                                        return false;
                                    }else {
                                        layer.msg('命令正在执行中请查看命令执行情况')
                                        setTimeout(function(){
                                            layer.closeAll();
                                        }, 2000);
                                        return false;
                                    }
                                    layer.closeAll('loading');
                                }
                            });
                        }, 1000);
                    }else {
                        layer.msg('正在设置请不要重复点击');
                        layer.closeAll('loading');
                    }
                }
                ,btn2: function(){
                    layer.closeAll();
                },
                content: '\t\t\t\t<div class="title_right">\n' +
                '<input type="hidden" value="0" id= "csu">'+
                '\t\t\t\t\t<strong>板级参数设置</strong>\n' +
                '\t\t\t\t</div>\n' +
                '\t\t\t\t<div style="width: 500px; margin: auto;"></div>\n' +
                '\t\t\t\t<table class="table table-bordered" style="font-family: 微软雅黑;">\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t传感器地址：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="6" style="height: 25px;" id="sensorAddr"></td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t\t传感器个数：</td>\n' +
                '\t\t\t\t\t\t\t<td width="80%"><input type="text" value="6" style="height: 25px;" id="sensorNum"></td>\n' +
                '\t\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t\t设备服务类型选择：</td>\n' +
                '\t\t\t\t\t\t\t<td width="80%"><select id="modifyDeviceType" style="height: 25px; width: 100px;" onchange="modifyDeviceType('+eqId+');">\' +\n' +
                '<option value="0">威客创</option>'+
                '<option value="1">26S采集器</option>' +
                '</select>' +
                '</td>' +
                '\t\t\t\t\t\t</tr>\n' +
                '\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t传感器(按顺序填写/如要修改请重新选择)：</td>\n' +
                '\t\t\t\t\t\t<td width="80%" id = "jzcgq">\n' +
                '\t\t\t\t\t\t</td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t温度上限值：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="80"\n' +
                '\t\t\t\t\t\t\tstyle="height: 25px;" id="overTemp"></td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t温度下限值：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="10" style="height: 25px;" id="underTemp"></td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t温度信号上限值：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="80" style="height: 25px;" id="overSignal"></td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t温度信号下限值：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="10" style="height: 25px;" id="underSignal"></td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t上报周期(单位/s)：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="600" style="height: 25px;" id="reportPeriod">' +
                '</td>\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t采样模式：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><select id="captureMode" style="height: 25px; width: 100px;" onchange="clilcy();">' +
                '<option value="1">定时采样</option>\n' +
                '<option value="0">关闭采样</option>' +
                '<option value="2">分时采样</option>' +
                '</select>' +
                '</td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;" id = "zoneInTatoltr">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t区域内总个数：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="zoneInTatol">' +
                '</td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;" id="zoneInNumtr">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t区域内编号：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="zoneInNum">' +
                '</td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;" id= "capturePeriodtr">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t采集周期(单位/s)：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="600" style="height: 25px;" id="capturePeriod">' +
                '</td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t否自动配置模式：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><select id="isAutoMode" style="height: 25px; width: 100px;">' +
                '<option value="0">远程配置</option>' +
                '<option value="1">自动配置</option>\n' +
                '</select>' +
                '</td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t ip：</td>\n' +
                // '\t\t\t\t\t\t<td width="80%"><input type="text" value="180.101.147.115" style="height: 25px;" maxlength="15" size="15" id="southIP"></td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="117.60.157.137" style="height: 25px;" maxlength="15" size="15" id="southIP"></td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t\t<tr height="50px;">\n' +
                '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                '\t\t\t\t\t\t\t 端口：</td>\n' +
                '\t\t\t\t\t\t<td width="80%"><input type="text" value="5683" style="height: 25px;" id="southPort" maxlength="4" size="4"></td>\n' +
                '\t\t\t\t\t</tr>\n' +
                '\t\t\t\t</table>\n'
            });
            if(DeviceType != 1 && DeviceType != "1"){
                $("#sensorAddr").val(1);
            }
            jzcgq(eqId);
        }else {
            layer.msg('信息修改完整后在进行设置');
        }

    }
    function getEqui(eqId) {
        var deviceSbIda = $("#deviceSbId"+eqId).val();
        if(deviceSbIda != null && deviceSbIda != ''){
            var DeviceType = $("#deviceType"+eqId).val();
            var deviceSbId = $("#deviceSbId"+eqId).val();
            var deviceNumber = $("#deviceNumber"+eqId).val();
            var isRoot = $("#isRoot"+eqId).val();
            $.ajax({
                url:'${path}/webCommand/selectBoar',
                data:{
                    'DeviceType' : DeviceType,
                    'isRoot' : isRoot,
                    'deviceNumber' : deviceNumber,
                    'deviceSbId' : deviceSbId,
                    'deviceId' : eqId,
                },
                type:'post',
                cache:false,
                dataType:'json',
                traditional:true,
                success:function(data) {
                    if(data=="true"){
                        layer.msg("点击操作里面的命令查看执行情况");
                        layer.closeAll();
                    }else if(data == "false"){
                        layer.msg('数据上报发生错误')
                        return false;
                    }else {
                        layer.msg('命令正在执行请不要重复发送')
                        return false;
                    }
                }
            });
        }else{
            layer.msg('信息修改完整后并确定已经设置完成后在进行查看');
        }
    }

    function Echo(eqId) {
        var deviceNumber = $("#deviceNumber"+eqId).val();
        var deviceType = $("#deviceType"+eqId).val();
        $.ajax({
            url:'${path}/webParameter/selectByDeviceNumber',
            data:{
                'deviceNumber' : deviceNumber,
            },
            type:'post',
            cache:false,
            dataType:'json',
            traditional:true,
            success:function(data) {
                if(data.parament != null && data.parament != ''){
                    console.log(data.parament.modifyDeviceType);
                    $("#sensorAddr").val(data.parament.sensorAddr);
                    $("#sensorNum").val(data.parament.sensorNum);
                    $("#capturePeriod").val(data.parament.capturePeriod);
                    $("#underTemp").val(data.parament.underTemp);
                    $("#overTemp").val(data.parament.overTemp);
                    $("#overSignal").val(data.parament.overSignal);
                    $("#underSignal").val(data.parament.underSignal);
                    $("#reportPeriod").val(data.parament.reportPeriod);
                    $("#captureMode").val(data.parament.captureMode);
                    $("#isAutoMode").val(data.parament.isAutoMode);
                    $("#modifyDeviceType").val(data.parament.modifyDeviceType);
                    $("#zoneInTatol").val(data.parament.zoneInTatol);
                    $("#zoneInNum").val(data.parament.zoneInNum);
                    $("#southIP").val(data.parament.southIP);
                    $("#southPort").val(data.parament.southPort);
                    var ssw = data.parament.swId;
                    swIdpd=ssw;
                    var boxObj = $("input:checkbox[name='sensor']"); //获取所有的复选框值
                    var express = ssw.split(',');
                    $.each(express, function(index, ssw){
                        boxObj.each(function () {
                            if($(this).val() == ssw) {
                                $(this).attr("checked",true);
                            }
                        });
                    });
                    $("input:checkbox[name='sensor']").attr("disabled",true);
                    for (var i = 0; i < express.length; i++) {
                        swId.push(express[i]);
                    }
                    if(data.parament.captureMode == 0 || data.parament.captureMode == '0'){
                        $("#zoneInNumtr").hide();
                        $("#zoneInTatoltr").hide();
                        $("#capturePeriodtr").hide();
                    }else if(data.parament.captureMode == 2 || data.parament.captureMode == '2'){
                        $("#capturePeriodtr").hide();
                        $("#zoneInNumtr").show();
                        $("#zoneInTatoltr").show();
                    }else {
                        $("#zoneInNumtr").hide();
                        $("#zoneInTatoltr").hide();
                    }
                }else {
                    $("#zoneInNumtr").hide();
                    $("#zoneInTatoltr").hide();
                    $("#modifyDeviceType").val(deviceType);
                }
            }
        });
    }
    
    function cgqgs() {
        var  sensorNum = $("#sensorNum").val();
        $.ajax({
            url:'${path}/webEquipment/updateByMust',
            data:{
                'mute' : 1,
                'deviceNumber' : deviceNumber
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if(data=="true"){
                    //loading层
                    var index = layer.load(1, {
                        shade: [0.1,'#fff'] //0.1透明度的白色背景
                    });
                    location.reload();
                }else if(data == "false"){
                    layer.msg('数据上报发生错误')
                    return false;
                }
            }
        });
    }

    function jzsb() {
        $.ajax({
            url:'${path}/webDeviceType/select',
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                str = "";
                str += "<select id=\"DeviceType\" style=\"height: 25px; width: 100px;\">";
                str += '<option value="">请选择</option>';
                for (var i = 0; i < data.deviceTypeList.length; i++) {
                    str += '<option value='+data.deviceTypeList[i].deviceNum+'>'+data.deviceTypeList[i].sencondDeviceType+'</option>'
                }
                str += '</select>';
                $("#jzsblx").html('');
                $("#jzsblx").append(str);
            }
        });
    }

    function jzcgq(eqId) {
        var deviceType = $("#deviceType"+eqId).val();
        console.log("deviceType:"+deviceType)
        $.ajax({
            url:'${path}/webSensor/selectBySensor',
            data:{
                'deviceType': deviceType
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                str = "";
                str2 = "";
                str+='<table  style="font-family: 微软雅黑;" class="table table-bordered">';
                str+='<tr>';
                str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                str += '<input type="button"  value="撤销" onclick="removefx();" class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;" />';
                str+='</td>';
                str += '</tr>';
                str += '</table>';
                if(deviceType != 0 && deviceType != "0"){
                    for (var i = 0; i < data.sensorList.length; i++) {
                        if(i == 0 || i == '0' || i == 6 || i == '6' || i ==12 ||i == '12' || i ==18 ||i == '18'){
                            str+='<table  style="font-family: 微软雅黑;">';
                            str+='<tr>';
                        }
                        str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                        str += ' <input type="checkbox" name="sensor"   value="' + data.sensorList[i].sensorNum + '" onclick="cheg(' + data.sensorList[i].sensorNum + ')">';
                        str+='</td>';
                        str+='<td width="11%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;">';
                        str += data.sensorList[i].sensorName;
                        str+='</td>';
                        if(i == 5 || i == '5' || i == 11 || i == '11' || i ==17 ||i == '17' || i ==23 ||i == '23') {
                            str += '</tr>';
                            str += '</table>';
                        }
                    }
                }else {
                    for (var i = 0; i < data.sensorList.length; i++) {
                        if(i == 0 || i == '0' || i == 6 || i == '6' || i ==12 ||i == '12' || i ==18 ||i == '18'|| i ==24 ||i == '24'|| i ==30 ||i == '30'){
                            str+='<table  style="font-family: 微软雅黑;">';
                            str+='<tr>';
                        }
                        str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                        str += ' <input type="checkbox" name="sensor"   value="' + data.sensorList[i].sensorNum + '" onclick="cheg(' + data.sensorList[i].sensorNum + ')">';
                        str+='</td>';
                        str+='<td width="11%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;">';
                        str += data.sensorList[i].sensorName;
                        str+='</td>';
                        if(i == 5 || i == '5' || i == 11 || i == '11' || i ==17 ||i == '17' || i ==23 ||i == '23'|| i == 29 ||i == '29'|| i == 35 ||i == '35') {
                            str += '</tr>';
                            str += '</table>';
                        }
                    }
                }

                $("#jzcgq").html('');
                $("#jzcgq").append(str);
                var sw = [];
                $('input[name="sensor"]:checked').each(function(){
                    sw.push($(this).val());
                });
                if(sw.length==0){
                    swId=[];
                    swIdpd = "";
                }
                Echo(eqId);
            }
        });

    }

    function cheg(num) {
        var c = 0;
        for (var i = 0; i < swId.length; i++) {
            var obj = swId[i];
            if(obj == num){
                swId.splice(i,1);
                c = 1;
                break;
            }
        }
        if(c == 0 || c == '0'){
            swId.push(num);
        }
    }

    function sercysn(eqId) {
        var kqsn = $("#kqsn").val();
        if(kqsn == 0 || kqsn == '0'){
            var deviceNumber = $("#deviceNumber"+eqId).val();
            $.ajax({
                url:'${path}/webCommandInformation/selectByCssb',
                data:{
                    'deviceNumber' : deviceNumber,
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if (data.leviList != "false"){
                          cxmlhq(data,eqId);
                    }else if(data.leviList == "false"){
                        // layer.msg('正在发送命令请耐心等待');
                        bjcsck(eqId);
                    }else {
                        layer.msg('请不要重复发送命令');
                    }
                }
            });


        }else {
            layer.msg('请不要重复发送命令');
        }

    }
    function  cxmlfs(eqId) {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: false,
            area: ['35%', '40%'],
            skin: 'yourclass',
            btn: ['设置', '取消'] //只是为了演示
            ,yes: function(){
                layer.load(1);
                setTimeout(function(){
                    var DeviceType = $("#deviceType"+eqId).val();
                    var deviceSbId = $("#deviceSbId"+eqId).val();
                    var deviceNumber = $("#deviceNumber"+eqId).val();
                    var captureMode = $("#captureMode").val();
                    var isRoot = $("#isRoot"+eqId).val();
                    var reportPeriod = $("#reportPeriod").val();
                    var capturePeriod = $("#capturePeriod").val();
                    var zoneInTatol = $("#zoneInTatol").val();
                    var zoneInNum = $("#zoneInNum").val();
                    if(reportPeriod == null || reportPeriod == ""){
                        layer.msg('使能采样时间不能为空')
                        layer.closeAll('loading');
                        return false;
                    }
                    if(captureMode == null || captureMode == ""){
                        layer.msg('是否开启采样不能为空')
                        layer.closeAll('loading');
                        return false;
                    }

                    if(capturePeriod == null || capturePeriod == ""){
                        if(captureMode == '2' || captureMode == 2 || captureMode == '0' || captureMode == 0){
                            capturePeriod='0';
                        }else {
                            layer.msg('采集器时间不能为空')
                            layer.closeAll('loading');
                            return false;
                        }

                    }
                    if(zoneInTatol == null || zoneInTatol == ''){
                        if(captureMode == '1' || captureMode == 1 || captureMode == '0' || captureMode == 0){
                            zoneInTatol='0';
                        }else {
                            $("#csu").val(0);
                            layer.msg('区域内总个数不能为空')
                            layer.closeAll('loading');
                            return false;
                        }

                    }
                    if(zoneInNum == null || zoneInNum == ''){
                        if(captureMode == '1' || captureMode == 1 || captureMode == '0' || captureMode == 0){
                            zoneInNum = '0';
                        }else {
                            $("#csu").val(0);
                            layer.msg('区域内编号不能为空')
                            layer.closeAll('loading');
                            return false;
                        }

                    }
                    $.ajax({
                        url:'${path}/webCommand/samplingEnable',
                        data:{
                            'DeviceType' : DeviceType,
                            'isRoot' : isRoot,
                            'deviceNumber' : deviceNumber,
                            'zoneInTatol' : zoneInTatol,
                            'zoneInNum' : zoneInNum,
                            'deviceSbId' : deviceSbId,
                            'captureMode' : captureMode,
                            'reportPeriod': reportPeriod,
                            'capturePeriod': capturePeriod,
                        },
                        type:'post',
                        cache:false,
                        dataType:'json',
                        success:function(data) {
                            if (data == "true"){
                                layer.msg('命令发送成功请在设置页面查看命令发送情况');
                                setTimeout(function(){
                                    layer.closeAll();
                                }, 2000);
                            }else if(data == "false"){
                                layer.msg('命令发送失败');
                                layer.closeAll('loading');
                            }else {
                                layer.msg('请不要重复发送命令');
                                layer.closeAll('loading');
                            }
                        }
                    });
                }, 1000);

            }
            ,btn2: function(){
                layer.closeAll();
            },
            content: '\t\t\t\t<div class="title_right">\n' +
            '<input type="hidden" value="0" id= "csu">'+
            '\t\t\t\t\t<strong>采样使能</strong>\n' +
            '\t\t\t\t</div>\n' +
            '\t\t\t\t<div style="width: 500px; margin: auto;"></div>\n' +
            '\t\t\t\t<table class="table table-bordered" style="font-family: 微软雅黑;">\n'+
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t上报周期(单位/s)：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="reportPeriod"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t采样模式：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><select id="captureMode" style="height: 25px; width: 100px;" onchange="clilcy();">' +
            '<option value="1">定时采样</option>\n' +
            '<option value="0">关闭采样</option>' +
            '<option value="2">分时采样</option>' +
            '</select>' +
            '</td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;" id="capturePeriodtr">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t采集周期(单位/s)：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="capturePeriod"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;" id = "zoneInTatoltr">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t区域内总个数：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="zoneInTatol">' +
            '</td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;" id="zoneInNumtr">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t区域内编号：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="zoneInNum">' +
            '</td>\n' +

            '\t\t\t\t</table>\n'
        });
        Echocy(eqId);
    }

    function bjcsck(eqId) {
        layer.load(1);
        setTimeout(function(){
            layer.closeAll('loading');
        }, 5000);
        var DeviceType = $("#deviceType"+eqId).val();
        var deviceSbId = $("#deviceSbId"+eqId).val();
        var deviceNumber = $("#deviceNumber"+eqId).val();
        var isRoot = $("#isRoot"+eqId).val();
        $.ajax({
            url:'${path}/webCommand/selectBoar',
            data:{
                'DeviceType' : DeviceType,
                'isRoot' : isRoot,
                'deviceNumber' : deviceNumber,
                'deviceSbId' : deviceSbId,
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if (data == "true"){
                    layer.msg('命令发送成功请在设置页面查看命令发送情况');
                }else if(data == "false"){
                    layer.msg('命令发送失败');
                }else {
                    layer.msg('请不要重复发送命令请在操作栏点击页面查看命了执行情况');
                }
            }
        });
    }

    function  cxmlhq(data,eqId) {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: false,
            area: ['600px', '600px'],
            skin: 'yourclass',
            btn: '清除缓存' //只是为了演示
            ,yes: function(){
                var deviceNumber = $("#deviceNumber"+eqId).val();
                $.ajax({
                    url:'${path}/webCommandInformation/deleteBydeviceNumber',
                    data:{
                        'deviceNumber' : deviceNumber,
                    },
                    type:'post',
                    cache:false,
                    dataType:'json',
                    success:function(data) {
                        if (data == "true"){
                            layer.msg('清除成功');
                            setTimeout(function(){
                                layer.closeAll();
                            }, 2000);
                        }
                    }
                });
            },
            content: '\t\t\t\t<div class="title_right">\n' +
            '<input type="hidden" value="0" id= "csu">'+
            '\t\t\t\t\t<strong>采样使能</strong>\n' +
            '\t\t\t\t</div>\n' +
            '\t\t\t\t<div style="width: 500px; margin: auto;"></div>\n' +
            '\t\t\t\t<table class="table table-bordered" style="font-family: 微软雅黑;">\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 设备类型：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="deviceType" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 设备节点：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="isRoot" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 采样时间(单位/s)：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="reportPeriod" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;" id="capturePeriodtr">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 采样周期(单位/s)：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="capturePeriod" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;" id = "zoneInTatoltr">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t区域内总个数：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="zoneInTatol" readonly="readonly">' +
            '</td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;" id="zoneInNumtr">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t区域内编号：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="zoneInNum" readonly="readonly">' +
            '</td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 传感器地址：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="sensorAddr" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 传感器个数：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="sensorNum" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 传感器名称：</td>\n' +
            '\t\t\t\t\t\t<td width="80%" id="sensorName"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 上限温度：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="overTemp" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 下限温度：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="underTemp" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 异常上限温度：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="overSignal" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 异常下限温度：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="underSignal" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 采样模式：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="captureMode" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 是否自动配置模式：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="isAutoMode" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t ip：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="southIP" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 端口：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="southPort" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t sim卡号：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="nccId" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t</table>\n'
        });
        if(data.leviList.deviceType == 0 || data.leviList.deviceType == '0'){
            $("#deviceType").val("微星采集器");
        }
        if(data.leviList.deviceType == 1 || data.leviList.deviceType == '1'){
            $("#deviceType").val("26s采集器");
        }
        if(data.leviList.isRoot == 1 || data.leviList.isRoot == '1'){
            $("#isRoot").val('根节点');
        }
        if(data.leviList.isRoot == 0 || data.leviList.isRoot == '0'){
            $("#isRoot").val('主节点');
        }
        if(data.leviList.captureMode == 1 || data.leviList.captureMode == '1'){
            $("#captureMode").val('定时采样');
            $("#zoneInNumtr").hide();
            $("#zoneInTatoltr").hide();
            $("#capturePeriod").val(data.leviList.capturePeriod);

        }
        if(data.leviList.captureMode == 0 || data.leviList.captureMode == '0'){
            $("#captureMode").val('关闭采样');
            $("#zoneInNumtr").hide();
            $("#zoneInTatoltr").hide();
            $("#capturePeriodtr").hide();
        }
        if(data.leviList.captureMode == 2 || data.leviList.captureMode == '2'){
            $("#captureMode").val('分时采样');
            $("#capturePeriodtr").hide();
            $("#zoneInNum").val(data.leviList.zoneInNum);
            $("#zoneInTatol").val(data.leviList.zoneInTatol);
        }
        $("#reportPeriod").val(data.leviList.reportPeriod);
        $("#sensorAddr").val(data.leviList.sensorAddr);
        $("#sensorNum").val(data.leviList.sensorNum);
        if(data.leviList.isAutoMode == 1 || data.leviList.isAutoMode == '1'){
            $("#isAutoMode").val('自动配置 ');
        }else if(data.leviList.isAutoMode == 0 || data.leviList.isAutoMode == '0'){
            $("#isAutoMode").val('远程配置');
        }
        var obj="";
        for (var i = 0; i < data.leviList.sensorName.length; i++) {
            var objc = data.leviList.sensorName[i];
            if(i == data.leviList.sensorName.length-1){
                obj += objc;
            }else {
                obj+=objc+",";
            }
        }
        $("#sensorName").html(obj);
        $("#underTemp").val(data.leviList.underTemp);
        $("#overTemp").val(data.leviList.overTemp);
        $("#underSignal").val(data.leviList.underSignal);
        $("#overSignal").val(data.leviList.overSignal);
        $("#southIP").val(data.leviList.southIP);
        $("#southPort").val(data.leviList.southPort);
        $("#nccId").val(data.leviList.nccId);
    }

    function selectml(eqId) {
        var deviceNumber = $("#deviceNumber"+eqId).val();
        var currentPageSb = $("#currentPageSb").val();
        location.href="${path}/webCommandInformation/select?deviceNumber="+deviceNumber+"&currentPageSb="+currentPageSb;
    }

    function sbcjq(eqId) {
        var deviceNumber = $("#deviceNumber"+eqId).val();

        var currentPageSb = $("#currentPageSb").val();
        location.href="${path}/webAcpEquipment/selectByDeverId?deviceNumber="+deviceNumber+"&currentPageSb="+currentPageSb;
    }

    function zt(boardStatus,harvesterStatus) {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: false,
            area: ['600px', '300px'],
            skin: 'yourclass',
            content: '\t\t\t\t<div class="title_right">\n' +
            '<input type="hidden" value="0" id= "csu">'+
            '\t\t\t\t\t<strong>设备及采集器状态</strong>\n' +
            '\t\t\t\t</div>\n' +
            '\t\t\t\t<div style="width: 500px; margin: auto;"></div>\n' +
            '\t\t\t\t<table class="table table-bordered" style="font-family: 微软雅黑;">\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 设备状态：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="'+boardStatus+'" style="height: 25px;" id="boardStatus" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t\t<tr height="50px;">\n' +
            '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '\t\t\t\t\t\t\t 采集器状态：</td>\n' +
            '\t\t\t\t\t\t<td width="80%"><input type="text" value="'+harvesterStatus+'" style="height: 25px;" id="harvesterStatus" readonly="readonly"></td>\n' +
            '\t\t\t\t\t</tr>\n' +
            '\t\t\t\t</table>\n'
        });
    }

    function police(eqId) {
        var deviceNumber = $("#deviceNumber"+eqId).val();
        var currentPageSb = $("#currentPageSb").val();
        location.href="${path}/webAlarmInformation/selectAlarmInformation?deviceNumber="+deviceNumber+"&currentPageSb="+currentPageSb;
    }

    function inserAll() {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: false,
            area: ['20%', '30%'],
            skin: 'yourclass',
            btn: ['上传', '取消'] //只是为了演示
            ,yes: function(){
                ajaxFileUploadForType();

            }
            ,btn2: function(){
                layer.closeAll();
            },
            content: ' <form id="uploadform" action="" method="post" enctype="multipart/form-data">                          \n' +
            '        <table>  \n' +
            '            <tr>                                    \n' +
            '                <td width="10%">                                  \n' +
            '                   <input id="file" name="file" type="file" style="margin-top: 10px;margin-left: 10px;">  \n' +
            '                </td>  \n' +
            '<td width="10%"> \n' +
            '                   <a href="javascript:void(0);" onclick="xz();">下载模板</a>  \n' +
            '                </td>  \n' +
            '            </tr>  \n' +
            '              \n' +
            '        </table>  \n' +
            '    </form>  \n'
        });
    }
    function ajaxFileUploadForType(){
        layer.load(1);
        if($('input[type="file"]').val()!=""){
            var extend=$('input[type="file"]').val().substr($('input[type="file"]').val().lastIndexOf(".")+1);
            if("xls|xlsx".indexOf(extend+"|")==-1){
                flagPic=false;
                $.messager.alert("提示信息","选择的文件必须是EXCEL文件,请确认！");
            }else{
                $.ajaxFileUpload
                (
                    {
                        url:'${path}/webSkEquipment/skImport', //用于文件上传的服务器端请求地址
                        secureuri: false, //是否需要安全协议，一般设置为false
                        fileElementId: 'file', //文件上传域的ID
                        dataType: 'JSON', //返回值类型 一般设置为json
                        success: function (data)  //服务器成功响应处理函数
                        {
                            console.log(data)
                            if(data.message == "wjgd" ){
                                layer.msg("excle文件大于1M");
                            }else {

                            }
                             location.reload();
                        },
                        error: function (data, status, e)//服务器响应失败处理函数
                        {
                            layer.closeAll('loading');
                            layer.msg("上传失败请重新上传")

                        }
                    }
                )
            }
        }else{
            layer.closeAll('loading');
            layer.msg("请选择文件在上传")

        }
    }

    function xz() {
        location.href="${path}/webEquipment/export";
    }


    function calibration(eqId) {
        var deviceType = $("#deviceType"+eqId).val();
        if(deviceType != 0 && deviceType != "0"){
            layer.confirm('确定进行效验设置吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    shadeClose: false,
                    area: ['70%', '40%'],
                    skin: 'yourclass',
                    btn: ['设置', '取消'] //只是为了演示
                    ,yes: function(){
                        layer.load(1);
                        setTimeout(function(){
                            var swId =[];
                            $('input[name="sensor"]:checked').each(function(){
                                swId.push($(this).val());
                            });
                            if(swId.length==0){
                                layer.closeAll('loading');
                                layer.msg('请选择传感器');
                                return false;
                            }
                            var DeviceType = $("#deviceType"+eqId).val();
                            var deviceSbId = $("#deviceSbId"+eqId).val();
                            var deviceNumber = $("#deviceNumber"+eqId).val();
                            var isRoot = $("#isRoot"+eqId).val();
                            var adjustValues = $("#adjustValues").val();
                            var adjustTimes = $("#adjustTimes").val();

                            if(adjustValues == null || adjustValues == ""){
                                layer.msg('效验温度不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            if(adjustTimes == null || adjustTimes == ""){
                                layer.msg('效验次数不能为空')
                                layer.closeAll('loading');
                                return false;
                            }
                            $.ajax({
                                url:'${path}/webCommand/calibration',
                                data:{
                                    'DeviceType' : DeviceType,
                                    'isRoot' : isRoot,
                                    'deviceNumber' : deviceNumber,
                                    'deviceSbId' : deviceSbId,
                                    'adjustValues' : adjustValues,
                                    'switchFlag':swId,
                                    'adjustTimes':adjustTimes,
                                },
                                type:'post',
                                cache:false,
                                dataType:'json',
                                traditional: true,
                                success:function(data) {
                                    if (data == "true"){
                                        layer.msg('命令发送成功请在设置页面查看命令发送情况');
                                        setTimeout(function(){
                                            layer.closeAll();
                                        }, 2000);
                                    }else if(data == "false"){
                                        layer.msg('命令发送失败');
                                        layer.closeAll('loading');
                                    }else {
                                        layer.msg('请不要重复发送命令');
                                        layer.closeAll('loading');
                                    }
                                }
                            });
                        }, 1000);
                    }
                    ,btn2: function(){
                        layer.closeAll();
                    },
                    content: '\t\t\t\t<div class="title_right">\n' +
                    '<input type="hidden" value="0" id= "csu">'+
                    '\t\t\t\t\t<strong>校准设置</strong>\n' +
                    '\t\t\t\t</div>\n' +
                    '\t\t\t\t<div style="width: 500px; margin: auto;"></div>\n' +
                    '\t\t\t\t<table class="table table-bordered" style="font-family: 微软雅黑;">\n'+
                    '\t\t\t\t\t<tr height="50px;">\n' +
                    '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                    '\t\t\t\t\t\t\t校准温度：</td>\n' +
                    '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="adjustValues" onchange="gbje(this)"></td>\n' +
                    '</td>\n' +
                    '\t\t\t\t\t</tr>\n' +
                    '\t\t\t\t\t<tr height="50px;">\n' +
                    '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                    '\t\t\t\t\t\t\t校准次数：</td>\n' +
                    '\t\t\t\t\t\t<td width="80%"><input type="text" value="" style="height: 25px;" id="adjustTimes"></td>\n' +
                    '</td>\n' +
                    '\t\t\t\t\t<tr height="50px;">\n' +
                    '\t\t\t\t\t\t<td width="20%" style="font-size: 15px; text-align: center;">\n' +
                    '\t\t\t\t\t\t\t传感器：</td>\n' +
                    '\t\t\t\t\t\t<td width="80%" id = "jzcgq" >\n' +
                    '\t\t\t\t\t\t</td>\n' +
                    '\t\t\t\t\t</tr>\n' +
                    '\t\t\t\t\t</tr>\n' +
                    '\t\t\t\t</table>\n'
                });
                jzcgq(eqId);
            }, function(){
                layer.closeAll();
            });
        }else {
            layer.msg('当前设备不能进行效验设置');
        }

    }
    // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
    function gbje (obj) {
        var  ac = obj.value;
        // var exam = Math.round(ac * 10) / 10;
        if(ac != null && ac != ''){
            var exam = parseFloat(ac).toFixed(1);
            $("#adjustValues").val(exam);
        }
    }

    // Internet Explorer
    function OnPropChanged (event) {
        if (event.propertyName.toLowerCase () == "value") {
            var  ac = event.target.value;
            // var exam = Math.round(ac * 10) / 10;
            if(ac != null && ac != ''){
                var exam = parseFloat(ac).toFixed(1);
                $("#adjustValues").val(exam);
            }
        }
    }
    function clilcy() {
        var captureMode = $("#captureMode").val();
        if(captureMode == 1 || captureMode == "1"){
            $("#zoneInNumtr").hide();
            $("#zoneInTatoltr").hide();
            $("#capturePeriodtr").show();
        }else if(captureMode == 0 || captureMode == "0"){
            $("#zoneInNumtr").hide();
            $("#zoneInTatoltr").hide();
            $("#capturePeriodtr").hide();
        }else {
            $("#zoneInNumtr").show();
            $("#zoneInTatoltr").show();
            $("#capturePeriodtr").hide();
        }
    }

    function modifyDeviceType(eqId) {
       var modifyDeviceType = $("#modifyDeviceType").val();
        var deviceType = $("#deviceType"+eqId).val();
        if(modifyDeviceType == deviceType){
            $.ajax({
                url:'${path}/webSensor/selectBySensor',
                data:{
                    'deviceType': modifyDeviceType
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    str = "";
                    str2 = "";
                    str+='<table  style="font-family: 微软雅黑;" class="table table-bordered">';
                    str+='<tr>';
                    str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                    str += '<input type="button"  value="撤销" onclick="removefx();" class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;" />';
                    str+='</td>';
                    str += '</tr>';
                    str += '</table>';
                    if(modifyDeviceType != 0 && modifyDeviceType != "0"){
                        for (var i = 0; i < data.sensorList.length; i++) {
                            if(i == 0 || i == '0' || i == 6 || i == '6' || i ==12 ||i == '12' || i ==18 ||i == '18'){
                                str+='<table  style="font-family: 微软雅黑;">';
                                str+='<tr>';
                            }
                            str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                            str += ' <input type="checkbox" name="sensor"   value="' + data.sensorList[i].sensorNum + '" onclick="cheg(' + data.sensorList[i].sensorNum + ')">';
                            str+='</td>';
                            str+='<td width="11%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;">';
                            str += data.sensorList[i].sensorName;
                            str+='</td>';
                            if(i == 5 || i == '5' || i == 11 || i == '11' || i ==17 ||i == '17' || i ==23 ||i == '23') {
                                str += '</tr>';
                                str += '</table>';
                            }
                        }
                    }else {
                        for (var i = 0; i < data.sensorList.length; i++) {
                            if(i == 0 || i == '0' || i == 6 || i == '6' || i ==12 ||i == '12' || i ==18 ||i == '18'|| i ==24 ||i == '24'|| i ==30 ||i == '30'){
                                str+='<table  style="font-family: 微软雅黑;">';
                                str+='<tr>';
                            }
                            str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                            str += ' <input type="checkbox" name="sensor"   value="' + data.sensorList[i].sensorNum + '" onclick="cheg(' + data.sensorList[i].sensorNum + ')">';
                            str+='</td>';
                            str+='<td width="11%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;">';
                            str += data.sensorList[i].sensorName;
                            str+='</td>';
                            if(i == 5 || i == '5' || i == 11 || i == '11' || i ==17 ||i == '17' || i ==23 ||i == '23'|| i == 29 ||i == '29'|| i == 35 ||i == '35') {
                                str += '</tr>';
                                str += '</table>';
                            }
                        }
                    }
                    $("#jzcgq").html('');
                    $("#jzcgq").append(str);
                      if(swIdpd != null && swIdpd != ""){
                          var boxObj = $("input:checkbox[name='sensor']"); //获取所有的复选框值
                          var express = swIdpd.split(',');
                          $.each(express, function(index, swIdpd){
                              boxObj.each(function () {
                                  if($(this).val() == swIdpd) {
                                      $(this).attr("checked",true);
                                  }
                              });
                          });
                          for (var i = 0; i < express.length; i++) {
                              swId.push(express[i]);
                          }
                          $("input:checkbox[name='sensor']").attr("disabled",true);
                      }
                    var sw = [];
                    $('input[name="sensor"]:checked').each(function(){
                        sw.push($(this).val());
                    });
                    if(sw.length==0){
                        swId=[];
                    }
                    if(swIdpd == null || swIdpd == ""){
                        if(modifyDeviceType != 1 && modifyDeviceType != "1"){
                            $("#sensorAddr").val(1);
                        }else {
                            $("#sensorAddr").val(6);
                        }
                    }
                }
            });
        }else {
            $.ajax({
                url:'${path}/webSensor/selectBySensor',
                data:{
                    'deviceType': modifyDeviceType
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    str = "";
                    str+='<table  style="font-family: 微软雅黑;" class="table table-bordered">';
                    str+='<tr>';
                    str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                    str += '<input type="button"  value="撤销" onclick="removefx();" class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;" />';
                    str+='</td>';
                    str += '</tr>';
                    str += '</table>';
                    if(modifyDeviceType != 0 && modifyDeviceType != "0"){
                        for (var i = 0; i < data.sensorList.length; i++) {
                            if(i == 0 || i == '0' || i == 6 || i == '6' || i ==12 ||i == '12' || i ==18 ||i == '18'){
                                str+='<table  style="font-family: 微软雅黑;">';
                                str+='<tr>';
                            }
                            str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                            str += ' <input type="checkbox" name="sensor"   value="' + data.sensorList[i].sensorNum + '" onclick="cheg(' + data.sensorList[i].sensorNum + ')">';
                            str+='</td>';
                            str+='<td width="11%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;">';
                            str += data.sensorList[i].sensorName;
                            str+='</td>';
                            if(i == 5 || i == '5' || i == 11 || i == '11' || i ==17 ||i == '17' || i ==23 ||i == '23') {
                                str += '</tr>';
                                str += '</table>';
                            }
                        }
                    }else {
                        for (var i = 0; i < data.sensorList.length; i++) {
                            if(i == 0 || i == '0' || i == 6 || i == '6' || i ==12 ||i == '12' || i ==18 ||i == '18'|| i ==24 ||i == '24'|| i ==30 ||i == '30'){
                                str+='<table  style="font-family: 微软雅黑;">';
                                str+='<tr>';
                            }
                            str+='<td width="1%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;border:none;">';
                            str += ' <input type="checkbox" name="sensor"   value="' + data.sensorList[i].sensorNum + '" onclick="cheg(' + data.sensorList[i].sensorNum + ')">';
                            str+='</td>';
                            str+='<td width="11%" style="border-right-style:none;border-left-style:none;border-top-style:none;border-bottom-style:none;">';
                            str += data.sensorList[i].sensorName;
                            str+='</td>';
                            if(i == 5 || i == '5' || i == 11 || i == '11' || i ==17 ||i == '17' || i ==23 ||i == '23'|| i == 29 ||i == '29'|| i == 35 ||i == '35') {
                                str += '</tr>';
                                str += '</table>';
                            }
                        }
                    }
                    $("#jzcgq").html('');
                    $("#jzcgq").append(str);
                    var sw = [];
                    $('input[name="sensor"]:checked').each(function(){
                        sw.push($(this).val());
                    });
                    if(sw.length==0){
                        swId=[];
                    }
                    if(swIdpd == null || swIdpd == ""){
                        if(modifyDeviceType != 1 && modifyDeviceType != "1"){
                            $("#sensorAddr").val(1);
                        }else {
                            $("#sensorAddr").val(6);
                        }
                    }
                }
            });
        }
    }

    function removefx() {
        var all=document.getElementById('jzcgq');//获取到点击全选的那个复选框的id
        var one=document.getElementsByName('sensor');//获取到复选框的名称
            for(var j=0;j<one.length;j++){
                one[j].checked=false;
            }
        $("input:checkbox[name='sensor']").attr("disabled",false);
        swId=[];
    }

    function Echocy(eqId) {
        var deviceNumber = $("#deviceNumber"+eqId).val();
        var deviceType = $("#deviceType"+eqId).val();
        $.ajax({
            url:'${path}/webParameter/selectByDeviceNumber',
            data:{
                'deviceNumber' : deviceNumber,
            },
            type:'post',
            cache:false,
            dataType:'json',
            traditional:true,
            success:function(data) {
                if(data.parament != null && data.parament != ''){

                    $("#capturePeriod").val(data.parament.capturePeriod);
                    $("#reportPeriod").val(data.parament.reportPeriod);
                    $("#captureMode").val(data.parament.captureMode);
                    $("#zoneInTatol").val(data.parament.zoneInTatol);
                    $("#zoneInNum").val(data.parament.zoneInNum);
                    if(data.parament.captureMode == 0 || data.parament.captureMode == '0'){
                        $("#zoneInNumtr").hide();
                        $("#zoneInTatoltr").hide();
                        $("#capturePeriodtr").hide();
                    }else if(data.parament.captureMode == 2 || data.parament.captureMode == '2'){
                        $("#capturePeriodtr").hide();
                        $("#zoneInNumtr").show();
                        $("#zoneInTatoltr").show();
                    }else {
                        $("#zoneInNumtr").hide();
                        $("#zoneInTatoltr").hide();
                    }
                }else {
                    $("#zoneInNumtr").hide();
                    $("#zoneInTatoltr").hide();
                    $("#modifyDeviceType").val(deviceType);
                }
            }
        });
    }

    function updateBysubIdAndkgg(eqId) {
        layer.load(1);
       var substationId = $("#subid"+eqId).val();
        $.ajax({
            url:'${path}/webSkEquipment/updateBysubIdAndkgg',
            data:{
                'eqId' : eqId,
                'substationId' : substationId,
            },
            type:'post',
            cache:false,
            dataType:'json',
            traditional:true,
            success:function(data) {
                if(data != "false"){
                    layer.msg("授权成功")
                    location.reload();
                }else {
                    layer.msg("授权失败")
                    layer.closeAll('loading');
                }
            }
        });
    }
</script>

</body>

</html>
