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
</style>
</head>

<body>
<input type="hidden" id="basePath" value="${basePath}">
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
						<span class="divider">/</span> 变电站设备
					</li>
				</ul>

				<div class="title_right">
					<strong>变电站设备添加</strong>
				</div>
				<input type="hidden" value="${substationId }" id="substationId">
				<input type="hidden" value="${substationImage }" id="substationImage">
				<input type="hidden" value="${equipment.eptId }" id="eptId">
				<input type="hidden" value="${equipment.deviceName }" id="deviceNames">
				<input type="hidden" value="${equipment.deviceNumber }" id="deviceNumber">
				<div style="width: 1200px; margin: auto;"></div>
				<table class="table table-bordered" style="font-family: 微软雅黑;">
					<c:if test="${equipment.deviceName != null && equipment.deviceName != ''}">
						<tr height="50px;">
							<td width="20%" style="font-size: 15px; text-align: center;">
								设备名称：</td>
							<td width="80%"><input type="text" value="${equipment.deviceName }" style="height: 25px;" id="deviceName"></td>
						</tr>
					</c:if>
						<tr height="50px;">
							<td width="20%" style="font-size: 15px; text-align: center;">
								设备标识码(IMEI)：</td>
							<td width="80%">
							<c:if test="${equipment != null && equipment !=''}">
								<input type="text" value="${equipment.code }" style="height: 25px;" id="code" readonly="readonly">
							</c:if>
								<c:if test="${equipment == null || equipment ==''}">
								<input type="text" value="" style="height: 25px;" id="code">
							</c:if>
							</td>
						</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							设备负责人：</td>
						<td width="80%" id = "dq">
							<input type="text" value="${equipment.headOfEquipment }"
							style="height: 25px;" id="headOfEquipment">
						</td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							设备出厂ID：</td>
						<td width="80%">
							<input type="text" value="${equipment.deviceSbId }"
							style="height: 25px;" id="deviceSbId" maxlength="10" size="10" onchange="deviId(this);">
						</td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							设备负责人手机号码：</td>
						<td width="80%"><input type="text" value="${equipment.phone }"
							style="height: 25px;" id="phone"></td>
					</tr>

					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							对应开关柜编号：</td>
						<td width="80%"><input type="text" value="${equipment.equipmentxi }" style="height: 25px;" id="equipmentxi"></td>
					</tr>

					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							传感器个数：</td>
						<td width="80%"><input type="text" value="${equipment.numberDevices }" style="height: 25px;" id="numberDevices"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							设备类型选择：</td>
						<td width="80%" id = "sblx">
							<select id="eptIds" style="height: 25px; width: 100px;">
							</select>
						</td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							设备服务类型选择：</td>
						<td width="80%">
							<select id="deviceType" style="height: 25px; width: 100px;">
								<c:forEach var="deviceIdList" items="${deviceIdList}">
									<c:if test="${deviceIdList.deviceNum == equipment.deviceType}">
											<option value="${deviceIdList.deviceNum}" selected="selected">${deviceIdList.sencondDeviceType}</option>
									</c:if>
									<c:if test="${deviceIdList.deviceNum != equipment.deviceType}">
										<option value="${deviceIdList.deviceNum}">${deviceIdList.sencondDeviceType}</option>
									</c:if>
								</c:forEach>

							</select>
						</td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							设备节点选择：</td>
						<td width="80%">
							<select id="isRoot" style="height: 25px; width: 100px;">
                                <option value="">请选择</option>
								<c:if test="${equipment.isRoot == 1 || equipment.isRoot == '1'}">
									<option value="1" selected="selected">根节点</option>
									<option value="0">子节点</option>
								</c:if>
								<c:if test="${equipment.isRoot == 0 || equipment.isRoot == '0'}">
									<option value="1">根节点</option>
									<option value="0" selected="selected">子节点</option>
								</c:if>
								<c:if test="${equipment.isRoot == null || equipment.isRoot == ''}">
									<option value="1" selected="selected">根节点</option>
									<option value="0">子节点</option>
								</c:if>
							</select>
						</td>
					</tr>

				</table>
				<div style="text-align: center; margin-top: 30px;">
					<c:if test="${equipment!=null}">
						<input type="button" value="修改" onclick="upda(${equipment.eqId})"
							class="btn btn-primary"
							style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
					</c:if>
					<c:if test="${equipment==null}">
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
	$(function () {
		var deviceName = $("#deviceName").val();
		var eptId = $("#eptId").val();
            $.ajax({
                url:'${path}/webEquipmentType/finde',
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    var str ='';
							for ( var i = 0; i < data.equiTyepList.length; i++) {
								if(eptId == data.equiTyepList[i].eptId){
									str += '<option value="'+data.equiTyepList[i].eptId+'" selected="selected">'+data.equiTyepList[i].deviceType+'</option>';
								}else{
									str += '<option value="'+data.equiTyepList[i].eptId+'">'+data.equiTyepList[i].deviceType+'</option>';
								}
							}
                    $("#eptIds").append(str);
                }
            });
    });

	function deviId(obj) {
        var  ac = obj.value;
        var reg = /^[0-9]+.?[0-9]*$/;
        if (!reg.test(ac)) {
            layer.msg('设备ID应为纯数字');
            return false;
        }else {
            var num1 = "4294967295";
            if(parseInt(ac)>parseInt(num1))
            {
                layer.msg('设备ID数字超过允许的最大数字');
                return  false;
            }
        }
    }

	function insert(){
        layer.load(1);
        // var deviceName = $("#deviceName").val();
		var headOfEquipment = $("#headOfEquipment").val();
		var phone = $("#phone").val();
		var equipmentxi = $("#equipmentxi").val();
		var substationId = $("#substationId").val();
		var eptId = $("#eptIds").val();
		var substationImage = $("#substationImage").val();
		var numberDevices = $("#numberDevices").val();
		var code = $("#code").val();
		var deviceSbId = $("#deviceSbId").val();
		var deviceType = $("#deviceType").val();
		var isRoot = $("#isRoot").val();
		if(headOfEquipment == null || headOfEquipment==""){
			layer.msg('设备负责人不能为空');
            layer.closeAll('loading');
			return false;
		}

		if(phone !=null && phone!=""){
            if(!(/^1[34578]\d{9}$/.test(phone))){
                layer.msg("手机号码有误，请重填");
                layer.closeAll('loading');
                return false;
            }
		}
		if(equipmentxi==null || equipmentxi==""){
			layer.msg('对应开关柜编号不能为空');
            layer.closeAll('loading');
			return false;
		}
		if(numberDevices==null || numberDevices==""){
			layer.msg('传感器个数不能为空');
            layer.closeAll('loading');
			return false;
		}
		if(eptId==null || eptId==""){
			layer.msg('设备类型不能为空');
            layer.closeAll('loading');
			return false;
		}
		if(deviceSbId==null || deviceSbId==""){
			layer.msg('设备出厂ID不能为空');
            layer.closeAll('loading');
			return false;
		}else {
            var reg = /^[0-9]+.?[0-9]*$/;
            if (!reg.test(deviceSbId)) {
                layer.msg('设备ID应为纯数字');
                layer.closeAll('loading');
                return false;
            }else {
                var num1 = "4294967295";
                if(parseInt(deviceSbId)>parseInt(num1))
                {
                    layer.msg('设备ID数字超过允许的最大数字');
                    layer.closeAll('loading');
                	return  false;
           		 }
            }
        }
        if(deviceType==null || deviceType==""){
            layer.msg('设备服务类型不能为空');
            layer.closeAll('loading');
            return false;
        }
        if(isRoot==null || isRoot==""){
            layer.msg('节点不能为空');
            layer.closeAll('loading');
            return false;
        }

		$.ajax({    
		    url:'${path}/webEquipment/EquiQy',
		    data:{
		    	'headOfEquipment' : headOfEquipment,
		    	'phone':phone,
		    	'equipmentxi' : equipmentxi,
		    	'substationId':substationId,
		    	'eptId':eptId,
                'numberDevices':numberDevices,
				'deviceSbId':deviceSbId,
                'deviceType': deviceType,
                'isRoot': isRoot,
                'code': code,
		    },
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data=="true"){
		    		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
		    	}else if(data == "false"){
		    		layer.msg('添加失败');
                    layer.closeAll('loading');
		    	}else if(data == "bdsb"){
                    layer.msg('设备已经绑定');
                    layer.closeAll('loading');
                }
		    }
		}); 
	}
	
	function remo(value){
		var substationId = $("#substationId").val();
		var substationImage = $("#substationImage").val();
		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
	}
	
	function upda(eqId){
        layer.load(1);
        var deviceName = $("#deviceName").val();
        var headOfEquipment = $("#headOfEquipment").val();
        var phone = $("#phone").val();
        var equipmentxi = $("#equipmentxi").val();
        var substationId = $("#substationId").val();
        var eptId = $("#eptIds").val();
        var eptIds = $("#eptId").val();
        var substationImage = $("#substationImage").val();
        var deviceNames = $("#deviceNames").val();
        var deviceNumber = $("#deviceNumber").val();
        var numberDevices = $("#numberDevices").val();
        var deviceSbId = $("#deviceSbId").val();
        var deviceType = $("#deviceType").val();
        var isRoot = $("#isRoot").val();




        if(phone!=null && phone!=""){
            if(!(/^1[34578]\d{9}$/.test(phone))){
                layer.msg("手机号码有误，请重填");
                layer.closeAll('loading');
                return false;
            }
        }
        if(equipmentxi==null || equipmentxi==""){
            layer.msg('对应开关柜编号不能为空');
            layer.closeAll('loading');
            return false;
        }
        if(eptId==null || eptId==""){
            layer.msg('设备类型不能为空');
            layer.closeAll('loading');
            return false;
        }
        if(deviceSbId==null || deviceSbId==""){
            layer.msg('设备出厂ID不能为空');
            layer.closeAll('loading');
            return false;
        }else {
            var reg = /^[0-9]+.?[0-9]*$/;
            if (!reg.test(deviceSbId)) {
                layer.msg('设备ID应为纯数字');
                layer.closeAll('loading');
                return false;
            }else {
                var num1 = "4294967295";
                if(parseInt(deviceSbId)>parseInt(num1))
                {
                    layer.msg('设备ID数字超过允许的最大数字');
                    layer.closeAll('loading');
                    return  false;
                }
            }
        }
        if(deviceType==null || deviceType==""){
            layer.msg('设备服务类型不能为空');
            layer.closeAll('loading');
            return false;
        }
        if(isRoot==null || isRoot==""){
            layer.msg('节点不能为空');
            layer.closeAll('loading');
            return false;
        }
		$.ajax({
		    url:'${path}/webEquipment/updateByeqId',   
		    data:{
                'deviceName' : deviceName,
                'headOfEquipment' : headOfEquipment,
                'phone':phone,
                'equipmentxi' : equipmentxi,
                'substationId':substationId,
                'eptId':eptId,
				'eqId' : eqId,
				'deviceNames': deviceNames,
				'eptIds': eptIds,
				'deviceNumber': deviceNumber,
				'numberDevices':numberDevices,
				'deviceSbId':deviceSbId,
				'deviceType':deviceType,
				'isRoot':isRoot,
		    },
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		        alert(data);
		    	if(data=="true"){
		    		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
		    	}else if(data == "false"){
		    		layer.msg('添加失败');
                    layer.closeAll('loading');
		    	}
		    }
		}); 
	}
</script>

</body>

</html>
