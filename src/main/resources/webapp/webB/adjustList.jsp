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
						<span class="divider">/</span>效验信息
					</li>
				</ul>

				<div class="title_right">
					<strong>列表</strong>
				</div>
				<input type="hidden" value="${substationId}" id="substationId">
				<input type="hidden" value="${substationImage}" id="substationImage">
                <input type="hidden" id="deviceNumber"value="${deviceNumber}">
				<div style="width: 1200px; margin: auto;">
					<table class="table table-bordered">
						<tr>
							<td width="10%">
								<input type="button"  value="返回" onclick="cksb();"class="btn btn-primary"
									   style="width: 94px; height: 28px; font-family: 微软雅黑;" />
							</td>
						</tr>
					</table>

					<table class="table table-bordered table-hover table-striped"
						style="font-family: 微软雅黑;">
						<tbody>
								<tr align="center" style="height: 35px;">
										<td width="5%">序号</td>
										<td width="9%">频点名称</td>
										<td width="9%">最大频率</td>
										<td width="9%">最小频率</td>
										<td width="9%">平均频率</td>
										<td width="9%">最大强度</td>
										<td width="9%">最小强度</td>
										<td width="9%">平均强度</td>
										<td width="8%">有效次数</td>
										<td width="8%">无效次数</td>
										<td width="8%">频点打开</td>
										<td width="8%">频点校准</td>
								</tr>

							<c:forEach var="adjusLists" items="${adjusLists}" varStatus="s">
								<tr align="center" style="height: 35px;">
									<td>${s.index+1}</td>
									<td>${adjusLists.sensorName}</td>
									<td>${adjusLists.maxFre}</td>
									<td>
											${adjusLists.minFre}
									</td>
									<td>${adjusLists.meanFre}</td>
									<td>${adjusLists.maxPower}</td>
									<td>${adjusLists.minPower}</td>
									<td>
											${adjusLists.meanPower}
									</td>
									<td>
                                            ${adjusLists.validCount}
									</td>
                                    <td>
                                            ${adjusLists.invalidCount}
									</td>
                                    <td>

                                        <c:if test="${adjusLists.flag == true}">
											<span style="color: #00FF00">已打开</span>
                                        </c:if>
                                        <c:if test="${adjusLists.flag == false}">
                                            <span style="color: red">未打开</span>
                                        </c:if>
									</td>
                                    <td>
                                        <c:if test="${adjusLists.adjust == true}">
											<span style="color: #00FF00">已校准</span>
                                        </c:if>
                                        <c:if test="${adjusLists.adjust == false}">
											<span style="color: red">未校准</span>
                                        </c:if>
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

    function cksb() {
        var deviceNumber = $("#deviceNumber").val();
        var substationImage = $("#substationImage").val();
        var substationId = $("#substationId").val();
        location.href="${path}/webCommandInformation/select?deviceNumber="+deviceNumber+"&substationId="+substationId+"&substationImage="+substationImage;
    }
</script>

</body>

</html>
