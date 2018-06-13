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
	<link rel="stylesheet" href="${path}/resource/js/layui/css/layui.css"  media="all">
	<script type="text/javascript" src="${path}/resource/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${path}/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/resource/js/sdmenu.js"></script>
<script type="text/javascript"
	src="${path}/resource/js/laydate/laydate.js"></script>
<link href="${path}/resource/cs/pages.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${path}/resource/js/layer/layer.js"></script>
	<script type="text/javascript" src="${path}/resource/js/layui/layui.js"></script>
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
					<strong>app上传</strong>
				</div>

				<div style="width: 1200px; margin: auto;"></div>
				<table class="table table-bordered" style="font-family: 微软雅黑;">
					<c:if test="${equipment.deviceName != null && equipment.deviceName != ''}">
						<tr height="50px;">
							<td width="20%" style="font-size: 15px; text-align: center;">
								更新内容：</td>
							<td width="80%"><input type="text" value="${equipment.deviceName }" style="height: 25px;" id="deviceName"></td>
						</tr>
					</c:if>
						<tr height="50px;">
							<td width="20%" style="font-size: 15px; text-align: center;">
								文件上传：</td>
							<td width="80%">
								<button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传文件</button>
							</td>
						</tr>

				</table>
				<div style="text-align: center; margin-top: 30px;">
						<input type="button" value="确定" onclick="insert()"
							class="btn btn-primary"
							style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
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
        layui.use('upload', function(){
            var $ = layui.jquery
                ,upload = layui.upload;

            //指定允许上传的文件类型
            upload.render({
                elem: '#test3'
                ,url: '${path}/webAppApk/doGet'
                ,accept: 'file' //普通文件,
                ,type:'post'
                ,data:{
                    "appName":"aaa"
				}
                ,done: function(res){
                    console.log(res)
                }
            });



        });
</script>

</body>

</html>
