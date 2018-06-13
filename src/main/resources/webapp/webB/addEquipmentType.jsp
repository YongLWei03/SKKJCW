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
    <script type="text/javascript"
            src="${path}/resource/js/ajaxfileupload.js"></script>
</head>

<body>
<!-- 顶部 -->
<%@include file="/resource/common/header.jsp"%>

<div id="middle">
    <%@include file="/resource/common/left.jsp"%>

    <div class="right" id="mainFrame">
        <%-- 			<input type="hidden" id="hname" value="${user.name }"> --%>
        <div class="right_cont">
            <ul class="breadcrumb" style="font-family: 微软雅黑;">
                <li>当前位置： <a href="javascript:void(0);"
                             style="text-decoration: none;">首页</a> <span class="divider">/</span>
                    <a href="javascript:void(0);" style="text-decoration: none;">管理</a>
                    <span class="divider">/</span> 设备类型
                </li>
            </ul>

            <div class="title_right">
                <strong>设备类型添加</strong>
            </div>
            <div style="width: 1200px; margin: auto;"></div>
            <table class="table table-bordered" style="font-family: 微软雅黑;">
                <tr height="50px;">
                    <td width="20%" style="font-size: 15px; text-align: center;">
                        设备类型：</td>
                    <td width="80%" id = "dq">
                        <input type="text" value="${equipt.deviceType }"
                               style="height: 25px;" id="deviceType">
                    </td>
                </tr>
                <tr height="50px;">
                    <td width="20%" style="font-size: 15px; text-align: center;">
                        设备型号：</td>
                    <td width="80%"><input type="text" value="${equipt.model }"
                                           style="height: 25px;" id="model"></td>
                </tr>
                <tr height="50px;">
                    <td width="20%" style="font-size: 15px; text-align: center;">
                        设备协议：</td>
                    <td width="80%"><input type="text" value="${equipt.protocolType }" style="height: 25px;" id="protocolType"></td>
                </tr>
                <tr height="50px;">
                    <td width="20%" style="font-size: 15px; text-align: center;">
                        厂商名称：</td>
                    <td width="80%"><input type="text" value="${equipt.manufacturerName }" style="height: 25px;" id="manufacturerName"></td>
                </tr>
                <tr height="50px;">
                    <td width="20%" style="font-size: 15px; text-align: center;">
                        厂商ID：</td>
                    <td width="80%"><input type="text" value="${equipt.manufacturerId }" style="height: 25px;" id="manufacturerId"></td>
                </tr>
            </table>
            <div style="text-align: center; margin-top: 30px;">
                <c:if test="${equipt!=null}">
                    <input type="button" value="修改" onclick="upda(${equipt.eptId})"
                           class="btn btn-primary"
                           style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
                </c:if>
                <c:if test="${equipt==null}">
                    <input type="button" value="添加" onclick="insert()"
                           class="btn btn-primary"
                           style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
                </c:if>
                <input type="button" value="取消" onclick="remo()"
                       class="btn btn-primary"
                       style="width: 80px; height: 28px; font-family: 微软雅黑;" />
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->
<%@include file="/resource/common/footer.jsp"%>

<script type="text/javascript">

    function insert(){
        var deviceType = $("#deviceType").val();
        var model = $("#model").val();
        var protocolType = $("#protocolType").val();
        var manufacturerName = $("#manufacturerName").val();
        var manufacturerId = $("#manufacturerId").val();
        if(deviceType == null || deviceType==""){
            layer.msg(' 设备类型不能为空');
            return false;
        }
        if(model==null || model==""){
            layer.msg(' 设备型号不能为空');
            return false;
        }
        if(protocolType==null || protocolType==""){
            layer.msg('设备协议不能为空');
            return false;
        }
        if(manufacturerName==null || manufacturerName==""){
            layer.msg('厂商名称不能为空');
            return false;
        }
        if(manufacturerId==null || manufacturerId==""){
            layer.msg(' 厂商名称不能为空');
            return false;
        }
        $.ajax({
            url:'${path}/webEquipmentType/insert',
            data:{
                'deviceType' : deviceType,
                'model' : model,
                'protocolType':protocolType,
                'manufacturerName' : manufacturerName,
                'manufacturerId':manufacturerId,
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if(data=="true"){
                    location.href="${path}/webEquipmentType/selectById";
                }else if(data == "false"){
                    layer.msg('添加失败');
                }
            }
        });
    }

    function remo(){

        location.href="${path}/webEquipmentType/selectById";
    }

    function upda(eptId){
        var deviceType = $("#deviceType").val();
        var model = $("#model").val();
        var protocolType = $("#protocolType").val();
        var manufacturerName = $("#manufacturerName").val();
        var manufacturerId = $("#manufacturerId").val();
        if(deviceType == null || deviceType==""){
            layer.msg(' 设备类型不能为空');
            return false;
        }
        if(model==null || model==""){
            layer.msg(' 设备型号不能为空');
            return false;
        }
        if(protocolType==null || protocolType==""){
            layer.msg('设备协议不能为空');
            return false;
        }
        if(manufacturerName==null || manufacturerName==""){
            layer.msg('厂商名称不能为空');
            return false;
        }
        if(manufacturerId==null || manufacturerId==""){
            layer.msg(' 厂商名称不能为空');
            return false;
        }
        $.ajax({
            url:'${path}/webEquipmentType/update',
            data:{
                'deviceType' : deviceType,
                'model' : model,
                'protocolType' : protocolType,
                'manufacturerName' : manufacturerName,
                'manufacturerId':manufacturerId,
                'eptId': eptId
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if(data=="true"){
                    location.href="${path}/webEquipmentType/selectById";
                }else if(data == "false"){
                    layer.msg('添加失败');
                }
            }
        });
    }
</script>

</body>

</html>
