<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/resource/common/common.jsp" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开关柜综合在线监测系统</title>

<link rel="stylesheet" href="${path}/resource/cs/bootstrap.css" />
<link rel="stylesheet" href="${path}/resource/js/layer/skin/layer.css" />
  <link rel="shortcut icon" href="${path}/resource/image/sk.ico" type="image/x-icon" />
<script type="text/javascript" src="${path}/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/resource/js/layer/layer.js"></script>
<style type="text/css">


body{ background:#0066A8 url(${path}/resource/image/login_bg.png) no-repeat center 0px;font-family:微软雅黑;}
.tit{ margin:auto; margin-top:170px; text-align:center; width:350px; padding-bottom:20px;}
<%--.login-wrap{ width:200px; padding:30px 50px 0 330px; height:220px; background:#9D9D9D url(${path}/resource/image/skkj4.png) no-repeat 45px 95px; margin:auto; overflow: hidden;font-size:14px;}--%>
.login-wrap{ width:200px; padding:30px 200px; height:220px; background:#9D9D9D url(${path}/resource/image/skkj4.png) no-repeat 45px 95px; margin:auto; overflow: hidden;font-size:14px;}
.login_input{ display:block;width:210px;}
.login_user{ background: url(${path}/resource/image/input_icon_1.png) no-repeat 200px center; font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif}
.login_password{ background: url(${path}/resource/image/input_icon_2.png) no-repeat 200px center; font-family:"Courier New", Courier, monospace}
.btn-login{ background:#40454B; box-shadow:none; text-shadow:none; color:#fff; border:none;height:35px; line-height:26px; font-size:14px; font-family:"microsoft yahei";}
.btn-login:hover{ background:#333; color:#fff;}
.copyright{ margin:auto; margin-top:10px; text-align:center; width:370px; color:#CCC}
@media (max-height: 700px) {.tit{ margin:auto; margin-top:100px; }}
@media (max-height: 500px) {.tit{ margin:auto; margin-top:50px; }}
</style>
</head>

<body>
<form action="">
<%--<div class="tit"><img src="${path}/resource/image/tit2.png" alt="" /></div>--%>
  <div class="tit"></div>
<div class="login-wrap" style="border-radius:5px 5px 5px 5px;box-shadow: 10px 10px 5px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="25" valign="bottom">用户名：</td>
    </tr>
    <tr>
      <td><input type="text" class="login_input login_user" id="name" value="" style="width:220px;height:30px;"/></td>
    </tr>
    <tr>
      <td height="35" valign="bottom" >密  码：</td>
    </tr>
    <tr>
      <td><input type="password" id="password"  class="login_input login_password" value="" style="width:220px;height:30px;"/></td>
    </tr>
    <tr>
      <td height="60" valign="bottom"><a href="javascript:void(0);" class="btn btn-block btn-login" onclick="login()">登录</a></td>
    </tr>
   
  </table>
  <div style="width: 100%;text-align: center;margin-top: 9px;color: #FFFFFF;">当前版本 VER 1.0.1</div>
  <%--<div style="width: 100%;text-align: center;margin-top: 9px;color: blue;">电话：028-85158894</div>--%>
</div>
</form>
<div class="copyright">建议使用谷歌浏览器</div>


<script type="text/javascript">
	function login(){
		var name = $("#name").val();
		var password = $("#password").val();
		if(name==null || name==""){
			layer.msg('用户名不能为空');
			return false;
		}

		if(password==null || password==""){
			layer.msg('密码不能为空');
			return false;
		}

		$.ajax({
		    type:'post',
		    dataType:'json',
		    url:'${path}/webUser/findByUserName',
		    data:{
		    	'userName' : name,
		    	'password' : password
		    },
		    cache:false,
		    success:function(data) {
                if(data == "true"){
                location.href="${path}/webUser/selectUser";
                // 		    		location.href="${path}/webBaiDu/dtol";
                }else if(data == "false"){
                layer.msg('用户名或密码错误');
                }else if (data == "fzr") {
                location.href="${path}/webUser/userRights";
                }else{
                location.href="${path}/webSubstation/selectSubstationByType";
                }
                }

		});
	}
</script>




</body>
</html>
