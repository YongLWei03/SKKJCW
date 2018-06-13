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

	<script type="text/javascript" src="${path}/resource/js/layui/layui.js"></script>
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
						<span class="divider">/</span>app
					</li>
				</ul>

				<div class="title_right">
					<strong>列表</strong>
				</div>

				<div style="width: 1200px; margin: auto;">
					<table class="table table-bordered">
						<tr>
							<td width="10%">
								<input type="button"  value="上传" onclick="sc();"class="btn btn-primary"
									   style="width: 94px; height: 28px; font-family: 微软雅黑;" />
							</td>
						</tr>
					</table>

					<table class="table table-bordered table-hover table-striped"
						style="font-family: 微软雅黑;">
						<tbody>
								<tr align="center" style="height: 35px;">
										<td width="20%">序号</td>
										<td width="20%">更新内容</td>
										<td width="20%">更新时间</td>
										<td width="20%">版本号</td>
										<td width="20%">下载地址</td>
								</tr>

							<c:forEach var="appApkList" items="${appApkList}" varStatus="s">
								<tr align="center" style="height: 35px;">
									<td>${s.index+1}</td>
									<td>${appApkList.appName}</td>
									<td>${appApkList.time}</td>
									<td>
											${appApkList.vesion}
									</td>
									<td>
											${appApkList.appApkUrl}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
	<!-- 底部 -->
	<%@include file="/resource/common/footer.jsp"%>

	<script type="text/javascript">
	
	//分页
	function goback(p){
		location.href="${path}/webAlarmInformation/selectAlarmInformation?currentPage="+p;
	}

    function sc(data) {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: false,
            area: ['40%', '50%'],
            skin: 'yourclass',
            btn: [] //只是为了演示
            , yes: function () {

            }
            , btn2: function () {
            },
            content: '<div class="title_right">\n' +
            '<input type="hidden" value="0" id= "csu">'+
            '<strong>app上传</strong>\n' +
            '</div>\n' +
            '<div style="width: 500px; margin: auto;"></div>\n' +
            '<table class="table table-bordered" style="font-family: 微软雅黑;">\n' +
            '<tr height="50px;">\n' +
            '<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '更新内容：</td>\n' +
            '<td width="80%">' +
			'<textarea rows="3" cols="20" id="appName" >\n' +
            '</textarea>'+
			'</td>\n' +
            '</tr>\n' +
			'<tr height="50px;">\n' +
            '<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            '版本号：</td>\n' +
            '<td width="80%">' +
			'<input  type="text" value="" id="vesion" style="height: 25px;">\n' +
			'</td>\n' +
            '</tr>\n' +
            '<tr height="50px;">\n' +
            '<td width="20%" style="font-size: 15px; text-align: center;">\n' +
            'app上传：</td>\n' +
            '<td width="80%">' +
			'<button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传文件</button>'+
			'</td>\n' +
			'<tr height="50px;">\n' +
            '<td width="20%" style="font-size: 15px; text-align: center;" id="jd">\n' +
            '上传进度：</td>\n' +
            '<td width="80%">' +
			'<div class="layui-progress">\n' +
            ' <div class="layui-progress-bar layui-bg-red" lay-percent="20%"></div>\n' +
            ' </div>'+
			'</td>\n' +
            '</tr>\n' +
            '</table>\n'
        });
        layui.use('upload', function(){
            var $ = layui.jquery
                ,upload = layui.upload;
            //指定允许上传的文件类型
            upload.render({
                 elem: '#test3'
                ,url: '${path}/webAppApk/doGet'
                ,accept: 'file' //普通文件,
                ,type:'post'
                ,before: function(obj){
                    //预读本地文件示例，不支持ie8
                    obj.preview(function(){
                        layer.load(1);
                    });
                }
                ,done: function(res){
                    var rests = res.rest
					if(rests != "SUSSCES" ){
						layer.msg("上传失败");
					}else {
                        var url = res.url;
                        ccwj(url);
					}
                }
            });

        });
    }

    function ccwj(url) {
      var  appName = $("#appName").val();
      var  vesion = $("#vesion").val();
        $.ajax({
            url:'${path}/webAppApk/insert',
            type:'post',
            cache:false,
            dataType:'json',
			data:{
                "appName":appName ,
				"appApkUrl":url,
				"vesion":vesion,
			},
            success:function(data) {
                if(data != "false"){
                    layer.msg("上传成功");
                    location.reload();
				}else {
                    layer.msg("上传失败");
                    layer.closeAll('loading');
				}
            }
        });
    }

</script>

</body>

</html>
