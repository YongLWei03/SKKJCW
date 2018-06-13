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
						<a href="javascript:void(0);" style="text-decoration: none;">管理</a>
						<span class="divider">/</span> 用户
					</li>
				</ul>

				<div class="title_right">
					<strong>列表</strong>
				</div>
				<div style="width: 1200px; margin: auto;">
					<table class="table table-bordered">
						<tr>
							<c:if test="${user.type == 1 || user.type == '1' || user.type == 2 || user.type == '2'}">
								<td width="5%">
									<input
									type="button" id = "inuser"  value="添加用户" onclick="inser();"class="btn btn-primary"
									style="width: 94px; height: 28px; font-family: 微软雅黑;" />
								</td>
							</c:if>
							<td width="20%">
							<c:if test="${type == 1 || type == '1' }">
								<select id="type" style="height: 25px; width: 100px;">
									<option value="0">全部成员</option>
									<option value="1" selected="selected">超级管理员</option>
									<option value="2">管理员</option>
									<option value="3">负责人</option>
									<option value="4">普通用户</option>
								</select>
							</c:if>
							<c:if test="${type == 2 || type == '2' }">
								<select id="type" style="height: 25px; width: 100px;">
									<option value="0">全部成员</option>
									<option value="1">超级管理员</option>
									<option value="2" selected="selected">管理员</option>
									<option value="3">负责人</option>
									<option value="4">普通用户</option>
								</select>
							</c:if>
							<c:if test="${type == 3 || type == '3' }">
								<select id="type" style="height: 25px; width: 100px;">
									<option value="0">全部成员</option>
									<option value="1">超级管理员</option>
									<option value="2">管理员</option>
									<option value="3" selected="selected">负责人</option>
									<option value="4">普通用户</option>
								</select>
							</c:if>
							<c:if test="${type == 4 || type == '4'}">
								<select id="type" style="height: 25px; width: 100px;">
									<option value="0">全部成员</option>
									<option value="1">超级管理员</option>
									<option value="2">管理员</option>
									<option value="3">负责人</option>
									<option value="4" selected="selected">普通用户</option>
								</select>
							</c:if>
							<c:if test="${type == null || type == '' }">
								<select id="type" style="height: 25px; width: 100px;">
									<option value="0" selected="selected">全部成员</option>
									<option value="1">超级管理员</option>
									<option value="2">管理员</option>
									<option value="3">负责人</option>
									<option value="4">普通用户</option>
								</select>
							</c:if>
								<select id="department" style="height: 25px; width: 100px;">
									<option value="0" selected="selected">所属部门</option>
									<c:forEach items="${departmengtList }" var="departmengtList" varStatus="s">
										<c:if test="${department == departmengtList }">
											<option value="${departmengtList }" selected="selected">${departmengtList }</option>
										</c:if>
										<c:if test="${department != departmengtList }">
											<option value="${departmengtList }">${departmengtList }</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="text" id="keyword"  value="${keyword }" style="width: 140px; height: 28px; font-family: 微软雅黑;" placeholder="手机/座机号/名字"/>
								<input
								type="button" id = "selec"  value="查询" onclick="goback(1);"class="btn btn-primary"
								style="width: 94px; height: 28px; font-family: 微软雅黑;" />
							</td>
						</tr>
					</table>

					<table class="table table-bordered table-hover table-striped"
						style="font-family: 微软雅黑;">
						<tbody>
								<tr align="center" style="height: 35px;">
									<td width="5%">序号</td>
									<td width="5%">名字</td>
									<td width="5%">管理员</td>
									<td width="5%">用户名</td>
									<td width="10%">联系电话</td>
									<td width="10%">员工编号</td>
									<td width="5%">职称</td>
									<td width="5%">职务</td>
									<td width="10%">座机号</td>
									<td width="10%">手机号码</td>
									<td width="10%">所属部门</td>
									<td width="10%">备注</td>
									<td width="10%">操作</td>
								</tr>
							<c:forEach var="buser" items="${userlist}" varStatus="s">
								<tr align="center" style="height: 35px;">
									<td>${s.index+1}</td>
									<td>${buser.name}</td>
									<td>
									<c:if test="${buser.type == 1 || buser.type == '1' }">
											超级管理员
									</c:if>
									<c:if test="${buser.type == 2 || buser.type == '2' }">
											管理员
									</c:if>
									<c:if test="${buser.type == 3 || buser.type == '3' }">
											负责人
									</c:if>
									<c:if test="${buser.type == 4 || buser.type == '4' }">
											普通用户
									</c:if>
									</td>
									<td>${buser.userName}</td>
									<td>${buser.telephone}</td>
									<td>${buser.userNumber}</td>
									<td>${buser.title}</td>
									<td>${buser.post}</td>
									<td>${buser.planeNumber}</td>
									<td>${buser.phoneNumber}</td>
									<td>${buser.department}</td>
									<td>${buser.remarks}</td>
									<td>
										<a href="javascript:void(0);" onclick="update(${buser.userId})">修改</a>
										<a href="javascript:void(0);" onclick="delet(${buser.userId})">删除</a>
									</td>
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
		var keyword = $("#keyword").val();
		var type = $("#type").val();
		var department = $("#department").val();
		if(type == '0' || type == 0){
			type = '';
		}
		if(department == '0' || department == 0){
			department = '';
		}
		location.href="${path}/webUser/selectUser?currentPage="+p+"&keyword="+encodeURI(encodeURI(keyword))+"&type="+type+"&department="+encodeURI(encodeURI(department));
	}

	
	function delet(userId){
		if(confirm("确定删除吗")){
			$.ajax({    
		    	url:'${path}/webUser/deletByuserId',   
		   		 data:{    
		    	'userId' : userId
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
		location.href="${path}/webUser/inserUser";
	}
	
	function update(userId){
		location.href="${path}/webUser/selectByUserId?userId="+userId;
	}
	

</script>

</body>

</html>
