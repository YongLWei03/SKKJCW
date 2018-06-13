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
				<input type="hidden" value="${equipment.deviceNumber }" id="deviceNumberyc">
				<input type="hidden" value="${equipment.serialNumber }" id="serialNumberyc">
				<div style="width: 1200px; margin: auto;"></div>
				<table class="table table-bordered" style="font-family: 微软雅黑;">
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							设备号：</td>
						<td width="80%"><input type="text" value="${equipment.deviceNumber }"
							style="height: 25px;" id="deviceNumber"></td>
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
							安装的顺序：</td>
						<td width="80%"><input type="text" value="${equipment.serialNumber }" style="height: 25px;" id="serialNumber"></td>
					</tr>

					<tr height="50px;">
 						<td width="20%" style="font-size: 15px; text-align: center;">
 							接收方式(必选)：</td>
 						<td width="80%">
							<c:if test="${equipment.connect == 1 || equipment.connect == '1' || equipment.connect == null || equipment.connect == ''}">
								<select id="connect" style="height: 25px; width: 100px;">
									<option value="1" SELECTED="selected">UDP连接</option>
									<option value="2">TCP连接</option>
								</select>
							</c:if>
							<c:if test="${equipment.connect == 2 || equipment.connect == '2'}">
								<select id="connect" style="height: 25px; width: 100px;">
									<option value="1">UDP连接</option>
									<option value="2" SELECTED="selected">TCP连接</option>
								</select>
							</c:if>
 						</td>
 					</tr>
<!-- 					<tr height="50px;"> -->
<!-- 						<td width="20%" style="font-size: 15px; text-align: center;"> -->
<!-- 							y坐标：</td> -->
<!-- 						<td width="80%"> -->
<%-- 							<input type="text" value="${equipment.ordinate }" style="height: 25px;" id="ordinate"> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
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
	
	function insert(){
		var deviceNumber = $("#deviceNumber").val();
		var headOfEquipment = $("#headOfEquipment").val();
		var phone = $("#phone").val();
		var equipmentxi = $("#equipmentxi").val();
		var substationId = $("#substationId").val();
		var serialNumber = $("#serialNumber").val();
		var substationImage = $("#substationImage").val();
		var connect = $("#connect").val();
// 		var abscissa = $("#abscissa").val();
// 		var ordinate = $("#ordinate").val();
		if(deviceNumber==null || deviceNumber==""){
			layer.msg('设备号不能为空!');
			return false;
		}
		
		if(headOfEquipment == null || headOfEquipment==""){
			layer.msg('设备负责人不能为空');
			return false;
		}
		if(phone==null || phone==""){
			layer.msg('设备负责人手机号码不能为空');
			return false;
		}else {
            if(!(/^1[34578]\d{9}$/.test(phone))){
                layer.msg("手机号码有误，请重填");
                return false;
            }
        }
		if(equipmentxi==null || equipmentxi==""){
			layer.msg('对应开关柜编号不能为空');
			return false;
		}
		if(serialNumber==null || serialNumber==""){
			layer.msg('安装顺序不能为空');
			return false;
		}
		if(connect==null || connect==""){
			layer.msg('请选择连接方式');
			return false;
		}
// 		if(abscissa==null || abscissa==""){
// 			layer.msg('x坐标不能为空');
// 			return false;
// 		}
// 		if(ordinate==null || ordinate==""){
// 			layer.msg('y坐标不能为空');
// 			return false;
// 		}
		$.ajax({    
		    url:'${path}/webEquipment/insetEquipment',   
		    data:{    
		    	'deviceNumber' : deviceNumber,
		    	'headOfEquipment' : headOfEquipment,
		    	'phone':phone,
		    	'equipmentxi' : equipmentxi,
		    	'substationId':substationId,
		    	'serialNumber':serialNumber,
				'connect' : connect
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data=="true"){
		    		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
		    	}else if(data == "false"){
		    		layer.msg('添加失败');
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
		var deviceNumber = $("#deviceNumber").val();
		var headOfEquipment = $("#headOfEquipment").val();
		var phone = $("#phone").val();
		var equipmentxi = $("#equipmentxi").val();
		var substationId = $("#substationId").val();
		var serialNumber = $("#serialNumber").val();
		var deviceNumberyc = $("#deviceNumberyc").val();
		var serialNumberyc = $("#serialNumberyc").val();
		var substationImage = $("#substationImage").val();
		var connect = $("#connect").val();
// 		var abscissa = $("#abscissa").val();
// 		var ordinate = $("#ordinate").val();
		if(deviceNumber==null || deviceNumber==""){
			layer.msg('设备号不能为空!');
			return false;
		}
		
		if(headOfEquipment == null || headOfEquipment==""){
			layer.msg('设备负责人不能为空');
			return false;
		}
		if(phone==null || phone==""){
			layer.msg('设备负责人手机号码不能为空');
			return false;
		}
		if(equipmentxi==null || equipmentxi==""){
			layer.msg('对应开关柜编号不能为空');
			return false;
		}else {
            if(!(/^1[34578]\d{9}$/.test(phone))){
                layer.msg("手机号码有误，请重填");
                return false;
            }
        }
		if(serialNumber==null || serialNumber==""){
			layer.msg('安装顺序不能为空');
			return false;
		}
        if(connect==null || connect==""){
            layer.msg('请选择连接方式');
            return false;
        }
		$.ajax({
		    url:'${path}/webEquipment/updateByeqId',   
		    data:{    
		    	'deviceNumber' : deviceNumber,
		    	'headOfEquipment' : headOfEquipment,
		    	'phone':phone,
		    	'equipmentxi' : equipmentxi,
		    	'eqId' : eqId,
		    	'serialNumber':serialNumber,
		    	'deviceNumberyc':deviceNumberyc,
		    	'serialNumberyc':serialNumberyc,
                'connect': connect
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data=="true"){
		    		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
		    	}else if(data == "false"){
		    		layer.msg('添加失败');
		    	}
		    }
		}); 
	}
</script>

</body>

</html>
