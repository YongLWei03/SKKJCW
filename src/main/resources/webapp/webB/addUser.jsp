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
<input type="p" value="${adduser.password }" style="height: 25px;"id="p">
	<div id="middle">
		<%@include file="/resource/common/left.jsp"%>

		<div class="right" id="mainFrame">
<%-- 			<input type="hidden" id="hname" value="${user.name }"> --%>
			<div class="right_cont">
				<ul class="breadcrumb" style="font-family: 微软雅黑;">
					<li>当前位置： <a href="javascript:void(0);"
						style="text-decoration: none;">首页</a> <span class="divider">/</span>
						<a href="javascript:void(0);" style="text-decoration: none;">管理</a>
						<span class="divider">/</span> 用户
					</li>
				</ul>

				<div class="title_right">
					<strong>用户添加</strong>
				</div>
				<div style="width: 1200px; margin: auto;"></div>
				<table class="table table-bordered" style="font-family: 微软雅黑;">
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							名字：</td>
						<td width="80%"><input type="text" value="${adduser.name }"
							style="height: 25px;" id="name" placeholder="XXXX姓名"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							管理员：</td>
						<td width="80%">
							<c:if test="${user.type == 1 || user.type == '1'}">
								<c:if test="${adduser.type == 1 || adduser.type == '1' }">
									<select id="type" style="height: 25px; width: 100px;">
										<option value="1" selected="selected">超级管理员</option>
										<option value="2">管理员</option>
										<option value="3">负责人</option>
										<option value="4">普通用户</option>
									</select>
								</c:if>
								<c:if test="${adduser.type == 2 || adduser.type == '2' }">
									<select id="type" style="height: 25px; width: 100px;">
										<option value="1">超级管理员</option>
										<option value="2" selected="selected">管理员</option>
										<option value="3">负责人</option>
										<option value="4">普通用户</option>
									</select>
								</c:if>
								<c:if test="${adduser.type == 3 || adduser.type == '3' }">
									<select id="type" style="height: 25px; width: 100px;">
										<option value="1">超级管理员</option>
										<option value="2">管理员</option>
										<option value="3" selected="selected">负责人</option>
										<option value="4">普通用户</option>
									</select>
								</c:if>
								<c:if test="${adduser.type == 4 || adduser.type == '4' || adduser.type == null || adduser.type == ''}">
									<select id="type" style="height: 25px; width: 100px;">
										<option value="1">超级管理员</option>
										<option value="2">管理员</option>
										<option value="3">负责人</option>
										<option value="4" selected="selected">普通用户</option>
									</select>
								</c:if>
							</c:if>
							<c:if test="${user.type == 2 || user.type == '2'}">
								<c:if test="${adduser.type == 3 || adduser.type == '3' }">
									<select id="type" style="height: 25px; width: 100px;">
										<option value="3" selected="selected">负责人</option>
										<option value="4">普通用户</option>
									</select>
								</c:if>
								<c:if test="${adduser.type == 4 || adduser.type == '4' || adduser.type == null || adduser.type == ''}">
									<select id="type" style="height: 25px; width: 100px;">
										<option value="3">负责人</option>
										<option value="4" selected="selected">普通用户</option>
									</select>
								</c:if>
							</c:if>
						</td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							联系电话：</td>
						<td width="80%"><input type="text" value="${adduser.telephone }"
							style="height: 25px;" id="telephone" placeholder="手机号码如:13950236587"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							员工编号：</td>
						<td width="80%"><input type="text" value="${adduser.userNumber }" style="height: 25px;" id="userNumber" placeholder="员工编号"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							职称：</td>
						<td width="80%"><input type="text" value="${adduser.title }" style="height: 25px;"id="title" placeholder="员工的职称"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							职务：</td>
						<td width="80%"><input type="text" value="${adduser.post }" style="height: 25px;"id="post" placeholder="员工所属职务"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							座机号：</td>
						<td width="80%"><input type="text" value="${adduser.planeNumber }" style="height: 25px;"id="planeNumber" placeholder="座机号入0813-560351"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							手机号：</td>
						<td width="80%"><input type="text" value="${adduser.phoneNumber }" style="height: 25px;"id="phoneNumber" placeholder="手机号码如:13950236587"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							登录名：</td>
						<td width="80%"><input type="text" value="${adduser.userName }" style="height: 25px;"id="userName" placeholder="用户名如:zz123"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							密码：</td>
						<td width="80%"><input type="password" value="${adduser.password }" style="height: 25px;"id="password" placeholder="密码如:zz123"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							所属部门：</td>
						<td width="80%"><input type="text" value="${adduser.department }" style="height: 25px;"id="department" placeholder="员工所在部门如:技术部"></td>
					</tr>
					<%--<c:if test="${user.userType==1 || user.userType=='1'}">--%>
						<%--<tr height="50px;">--%>
							<%--<td width="20%" style="font-size: 15px; text-align: center;">--%>
								<%--访问权限：</td>--%>
							<%--<td width="80%">--%>
								<%--<select id="jurisdictionl">--%>
									<%--<option value="1">测温</option>--%>
									<%--<option value="2">电缆</option>--%>
								<%--</select>--%>
							<%--</td>--%>
						<%--</tr>--%>
					<%--</c:if>--%>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							备注：</td>
						<td width="80%"><input type="text" value="${adduser.remarks }" style="height: 25px;"id="remarks" placeholder=""></td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 30px;">
					<c:if test="${adduser!=null}">
						<input type="button" value="修改" onclick="upda(${adduser.userId})"
							class="btn btn-primary"
							style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
					</c:if>
					<c:if test="${adduser==null}">
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
		var name = $("#name").val();
		var type = $("#type").val();
		var telephone = $("#telephone").val();
		var userNumber = $("#userNumber").val();
		var title = $("#title").val();
		var post = $("#post").val();
		var planeNumber = $("#planeNumber").val();
		var phoneNumber = $("#phoneNumber").val();
		var userName = $("#userName").val();
		var password = $("#password").val();
		var department = $("#department").val();
		var remarks = $("#remarks").val();
		var check=1;
		if(name==null || name==""){
			layer.msg('账户为空!');
			return false;
		}

		if(phoneNumber==null || phoneNumber==""){
			layer.msg('手机号不能为空');
			return false;
		}else {
            if(!(/^1[34578]\d{9}$/.test(phoneNumber))){
                layer.msg("手机号码有误，请重填");
                return false;
            }
        }
		if(userName == null || userName==""){
			layer.msg('用户名不为空');
			return false;
		}else {
			$.ajax({    
			    url:'${path}/webUser/selectByUserName',   
			    data:{    
			    	'userName' : userName,
			    },    
			    type:'post',    
			    cache:false,
			    async:false,
			    dataType:'json',    
			    success:function(data) {
			    	
			    	if(data=="false"){
			    		check = 2;
			    	}
			    }
			}); 
		}
		if(check==2){
			layer.msg('用户名存在');
			return false;
		}

		$.ajax({    
		    url:'${path}/webUser/insert',   
		    data:{    
		    	'name' : name,
		    	'type' : type,
		    	'telephone':telephone,
		    	'userNumber' : userNumber,
		    	'title' : title,
		    	'post' : post,
		    	'planeNumber' : planeNumber,
		    	'phoneNumber' : phoneNumber,
		    	'userName' : userName,
		    	'password' : password,
		    	'department' : department,
		    	'remarks' : remarks,
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data=="true"){
		    		location.href="${path}/webUser/selectUser";
		    	}else if(data == "false"){
		    		layer.msg('添加失败');
		    	}
		    }
		}); 
	}
	
	function remo(value){
		location.href="${path}/webUser/selectUser";
	}
	
	function upda(userId){
		var name = $("#name").val();
		var type = $("#type").val();
		var telephone = $("#telephone").val();
		var userNumber = $("#userNumber").val();
		var title = $("#title").val();
		var post = $("#post").val();
		var planeNumber = $("#planeNumber").val();
		var phoneNumber = $("#phoneNumber").val();
		var userName = $("#userName").val();
		var password = $("#password").val();
		var department = $("#department").val();
		var remarks = $("#remarks").val();
		var p = $("#p").val();
		var check=1;
		if(name==null || name==""){
			layer.msg('账户为空!');
			return false;
		}



		if(phoneNumber==null || phoneNumber==""){
			layer.msg('手机号不能为空');
			return false;
		}else {
            if(!(/^1[34578]\d{9}$/.test(phoneNumber))){
                layer.msg("手机号码有误，请重填");
                return false;
            }
        }
		if(userName==null || userName==""){
			layer.msg('用户名不为空');
			return false;
		}else {
			$.ajax({    
			    url:'${path}/webUser/selectByUserName',   
			    data:{    
			    	'userName' : userName,
			    },    
			    type:'post',    
			    cache:false,    
			    dataType:'json',    
			    success:function(data) {
			    	if(data=="false"){
			    		check = 2;
			    	}
			    }
			}); 
		}
		if(check==2){
			layer.msg('用户名存在');
			return false;
		}
		$.ajax({    
		    url:'${path}/webUser/updateByUserId',   
		    data:{    
		    	'name' : name,
		    	'type' : type,
		    	'telephone':telephone,
		    	'userNumber' : userNumber,
		    	'title' : title,
		    	'post' : post,
		    	'planeNumber' : planeNumber,
		    	'phoneNumber' : phoneNumber,
		    	'userName' : userName,
		    	'password' : password,
		    	'department' : department,
		    	'remarks' : remarks,
		    	'userId' : userId,
		    	'p' : p
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data=="true"){
		    		location.href="${path}/webUser/selectUser";
		    	}else if(data == "false"){
		    		layer.msg('修改');
		    	}
		    }
		}); 
	}
	
</script>

</body>

</html>
