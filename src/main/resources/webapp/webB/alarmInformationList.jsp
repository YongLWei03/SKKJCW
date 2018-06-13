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
	<script type="text/javascript" src="${path}/resource/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="${path}/resource/js/jquery.page.js"></script>
<script type="text/javascript" src="${path}/resource/js/layui/layui.js"></script>

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
						<span class="divider">/</span>报警信息
					</li>
				</ul>

				<div class="title_right">
					<strong>列表</strong>
				</div>
				<input type="hidden" value="${substationId}" id="substationId">
				<input type="hidden" value="${substationImage}" id="substationImage">
				<input type="hidden" value="${currentPageSb}" id="currentPageSb">
                <input type="hidden" id="deviceNumbers"value="${deviceNumber}">
				<div style="width: 1200px; margin: auto;">
					<table class="table table-bordered">
					  <tr>
						  <td width="5%">
						  	<input type="text" id="keyword"  value="${keyword }" style="width: 140px; height: 28px; font-family: 微软雅黑;" placeholder="变电站名称或开关柜编号"/>
						  </td>
					  	<td width="5%">
					  	<c:if test="${state == 1 || state == '1' }">
					  		<select id="state" style="height: 28px; width: 100px;">
					  			<option value="0">状态</option>
					  			<option value="1" selected="selected">已处理</option>
					  			<option value="2">未处理</option>
					  		</select>
					  	</c:if>
					  	<c:if test="${state == 2 || state == '2' }">
					  		<select id="state" style="height: 28px; width: 100px;">
					  			<option value="0">状态</option>
					  			<option value="1">已接收到短信</option>
					  			<option value="2" selected="selected">未接收到短信</option>
					  		</select>
					  	</c:if>
					  	<c:if test="${state == '' || state == null }">
					  		<select id="state" style="height: 28px; width: 100px;">
					  			<option value="0">状态</option>
					  			<option value="1">已处理</option>
					  			<option value="2">未处理</option>
					  		</select>
					  	</c:if>
					  	</td>
					  	<c:if test="${user.type == 1 || user.type == '1' }">
						  	<td width="5%">
							  	<c:if test="${type == 1 || type == '1' }">
							  		<select id="type" style="height: 28px; width: 100px;">
							  			<option value="0">报警状态</option>
							  			<option value="1" selected="selected">异常</option>
							  			<option value="2">报警</option>
							  		</select>
							  	</c:if>
							  	<c:if test="${type == 2 || type == '2' }">
							  		<select id="type" style="height: 28px; width: 100px;">
							  			<option value="0">报警状态</option>
							  			<option value="1">异常</option>
							  			<option value="2" selected="selected">报警</option>
							  		</select>
							  	</c:if>
							  	<c:if test="${type == '' || type == null}">
							  		<select id="type" style="height: 28px; width: 100px;">
							  			<option value="0">报警状态</option>
							  			<option value="1">异常</option>
							  			<option value="2">报警</option>
							  		</select>
							  	</c:if>
						  	</td>
					  	</c:if>
					<td width="20%">
						<div class="layui-inline">
					      <label class="layui-form-label" style="width: 50px;">开始时间</label>
					      <div class="layui-input-inline">
					        <input type="text" class="layui-input" value="${startTime }" id="startTime" placeholder="yyyy-MM-dd" style="height: 25px; width: 100px;margin-top: 6px;">
					      </div>
					    </div>
				    </td>
				    <td width="20%">
				    <div class="layui-inline">
					      <label class="layui-form-label" style="width: 50px;">结束时间</label>
					      <div class="layui-input-inline">
					        <input type="text" class="layui-input" value="${endTime }" id="endTime" placeholder="yyyy-MM-dd" style="height: 25px; width: 100px;margin-top: 6px;">
					      </div>
				     </div>
				     </td>
					  	<td>
					  		<input type="button" id = "selec"  value="查询" onclick="goback(1);"class="btn btn-primary"
								style="width: 94px; height: 28px; font-family: 微软雅黑;" />
					  	</td>
						  <c:if test="${substationId != null && substationId != '' || currentPageSb != null && currentPageSb != '' }">
							  <td>
								  <input type="button"  value="返回" onclick="returns();"class="btn btn-primary"
										 style="width: 94px; height: 28px; font-family: 微软雅黑;" />
							  </td>
						  </c:if>
					  </tr>
					</table>

					<table class="table table-bordered table-hover table-striped"
						style="font-family: 微软雅黑;">
						<tbody>
								<tr align="center" style="height: 35px;">
										<td width="10%">序号</td>
										<td width="10%">变电站</td>
										<td width="5%">开关柜</td>
										<td width="25%">对应位置</td>
										<td width="10%">报警类型</td>
										<td width="10%">时间</td>
										<td width="10%">报警前数据</td>
										<td width="10%">状态</td>
										<td width="10%">操作</td>
								</tr>

							<c:forEach var="alarmInformationList" items="${alarmInformationList}" varStatus="s">
								<input type="hidden" id="deviceNumber${alarmInformationList.afId}"value="${alarmInformationList.deviceNumber}">
								<tr align="center" style="height: 35px;">
									<td><input name="receiver" type="checkbox" value="${alarmInformationList.afId }"></td>
									<td>${alarmInformationList.substationName}</td>
									<td>${alarmInformationList.equipmentxi}</td>
									<td>
										<c:if test="${alarmInformationList.inA != null && alarmInformationList.inA != ''}">
											 A入${alarmInformationList.inA}
										</c:if>
										<c:if test="${alarmInformationList.inB != null && alarmInformationList.inB != ''}">
											,B入${alarmInformationList.inB}
										</c:if>
										<c:if test="${alarmInformationList.inC != null && alarmInformationList.inC != ''}">
											,C入${alarmInformationList.inC}
										</c:if>
										<c:if test="${alarmInformationList.outA != null && alarmInformationList.outA != ''}">
											,A出${alarmInformationList.outA}
										</c:if>
										<c:if test="${alarmInformationList.outB != null && alarmInformationList.outB != ''}">
											,B出${alarmInformationList.outB}
										</c:if>
										<c:if test="${alarmInformationList.outC != null && alarmInformationList.outC != ''}">
											,C出${alarmInformationList.outC}
										</c:if>
										<c:if test="${alarmInformationList.contactA != null && alarmInformationList.contactA != ''}">
											,A线${alarmInformationList.contactA}
										</c:if>
										<c:if test="${alarmInformationList.contactB != null && alarmInformationList.contactB != ''}">
											,B线${alarmInformationList.contactB}
										</c:if>
										<c:if test="${alarmInformationList.contactC != null && alarmInformationList.contactC != ''}">
											,C线${alarmInformationList.contactC}
										</c:if>
										<c:if test="${alarmInformationList.contactD != null && alarmInformationList.contactD != ''}">
											,D线${alarmInformationList.contactD}
										</c:if>
										<c:if test="${alarmInformationList.contactE != null && alarmInformationList.contactE != ''}">
											,E线${alarmInformationList.contactE}
										</c:if>
										<c:if test="${alarmInformationList.contactF != null && alarmInformationList.contactF != ''}">
											,F线${alarmInformationList.contactF}
										</c:if>
									</td>
									<td>${alarmInformationList.information}</td>
									<td id="day${alarmInformationList.afId}">${alarmInformationList.day}</td>
									<td><a href="javascript:void(0);" onclick="chakan(${alarmInformationList.afId})">查看</a></td>
									<td>
										<c:if test="${alarmInformationList.state == 1 || alarmInformationList.state == '1' }">
											 <span style="color: #00FF00">已接收到短信</span>
										</c:if>
										<c:if test="${alarmInformationList.state == 2 || alarmInformationList.state == '2' }">
											<span style="color: red;">未接收到短信</span>
										</c:if>
									</td>
									<td>
										<c:if test="${alarmInformationList.state == 1 || alarmInformationList.state == '1' }">
												<a href="javascript:void(0);" onclick="delet(${alarmInformationList.afId})">删除</a>
										</c:if>
										<c:if test="${alarmInformationList.state == 2 || alarmInformationList.state == '2' }">
											<c:if test="${alarmInformationList.type == 2 || alarmInformationList.type == '2' }">
												<a href="javascript:void(0);" onclick="update(${alarmInformationList.afId},${alarmInformationList.eqId},${alarmInformationList.type})"><img src="../resource/image/tsh.png"></a>
											</c:if>
											<c:if test="${alarmInformationList.type == 1 || alarmInformationList.type == '1' }">
												<a href="javascript:void(0);" onclick="update(${alarmInformationList.afId},${alarmInformationList.eqId},${alarmInformationList.type})"><img src="../resource/image/tsy.png"></a>
											</c:if>
										</c:if>
									</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
					<c:if test="${state == 1 || state == '1'}">
						<div style="margin-top: 30px;">
							<input type="button" value="全选/不选"  onclick="cbxAll();" class="btn btn-primary"style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
							<input type="button" value="批量删除"  onclick="deletAll();" class="btn btn-primary"style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
						</div>
					</c:if>
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

	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		//时间选择器
		  laydate.render({
		    elem: '#startTime'
		  });
		//时间选择器
		  laydate.render({
			    elem: '#endTime'
		  });
		  
	});
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
		var state = $("#state").val();
		var type = $("#type").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var keyword = $("#keyword").val();
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        var deviceNumber = $("#deviceNumbers").val();
		if(state == 0 || state == '0'){
			state='';
		}
		if(type == 0 || type == '0'){
			type='';
		}
		location.href="${path}/webAlarmInformation/selectAlarmInformation?currentPage="+p+"&state="+state+"&type="+type+"&startTime="+encodeURI(encodeURI(startTime))+"&endTime="+encodeURI(encodeURI(endTime))+"&keyword="+encodeURI(encodeURI(keyword))+"&substationId="+substationId+"&substationImage="+substationImage+"&deviceNumber="+deviceNumber;
	}
	

	
	function delet(afId){
		if(confirm("确定删除吗")){
            var deviceNumber = $("#deviceNumber"+afId).val();
            var time = $("#day"+afId).html();
			$.ajax({    
		    	url:'${path}/webAlarmInformation/deleteByAfId',   
		   		 data:{    
		    	'afId' : afId,
		    	'deviceNumber' : deviceNumber,
		    	'time' : time
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
	
	function deletAll(substationId){
		var swId =[]; 
		$('input[name="receiver"]:checked').each(function(){ 
			swId.push($(this).val()); 
		}); 
		if(swId.length==0){
			alert('请选择管理站点');
			return false;
		}
		if(confirm("确定删除吗")){
            layer.load(1);
			$.ajax({
		    	url:'${path}/webAlarmInformation/deleteByAfIdList',   
		   		 data:{    
		    	'afId' : swId
		    	},    
			  	type:'post',    
			   	cache:false,    
			   	dataType:'json', 
			   	traditional: true,
			    success:function(data) {
			    	if(data=="true"){
                        setTimeout(function(){
                            location.reload();
						}, 1000);

			    	}else if(data == "false"){
                        setTimeout(function(){
                            alert("删除失败");
                            layer.closeAll('loading');
                        }, 1000);

			    	}
			    }
			}); 
		}
	}
	
	function inser(){
		location.href="${path}/webSubstation/insert";
	}
	
	function cksb(substationId){
		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId;
	}
	
	
	function update(afId,eqId,type){
        var deviceNumber = $("#deviceNumber"+afId).val();
		if(confirm("是否已经处理")){
			$.ajax({    
		    	url:'${path}/webAlarmInformation/updateByafId',
		   		 data:{    
		    	'afId' : afId,
		    	'deviceNumber' : deviceNumber,
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
	
	function cbxAll() {
		 var receiver=document.getElementsByName("receiver");
		 for (var i=0;i<receiver.length;i++) {
		  var e=receiver[i];
		  e.checked=!e.checked;
		 }
		
	}

	function chakan(afId) {
       var deviceNumber = $("#deviceNumber"+afId).val();
       var time = $("#day"+afId).html();
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        var deviceNumbers = $("#deviceNumbers").val();
        var state = $("#state").val();
        var type = $("#type").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var keyword = $("#keyword").val();
        if(state == 0 || state == '0'){
            state='';
        }
        if(type == 0 || type == '0'){
            type='';
        }
        var ssty = "";
        if(deviceNumbers != "" && deviceNumbers != null){
            ssty = "1"
        }
        location.href="${path}/webAlarmHistory/selectAlarmHistory?deviceNumber="+deviceNumber+"&time="+time+"&substationId="+substationId+"&substationImage="+substationImage+"&ssty="+ssty+"&state="+state+"&type="+type+"&startTime="+encodeURI(encodeURI(startTime))+"&endTime="+encodeURI(encodeURI(endTime))+"&keyword="+encodeURI(encodeURI(keyword));
    }

    function returns() {
        var substationId = $("#substationId").val();
        var substationImage = $("#substationImage").val();
        var currentPageSb = $("#currentPageSb").val();
        if(substationId != null && substationId != ''){
            location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
		}else {
            location.href="${path}/webSkEquipment/selectEquipment?currentPage="+currentPageSb;
		}

    }
</script>

</body>

</html>
