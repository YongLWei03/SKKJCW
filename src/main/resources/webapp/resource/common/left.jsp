<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <div class="left">
     
     <script type="text/javascript">
		var myMenu;
		window.onload = function() {
			myMenu = new SDMenu("my_menu");
			myMenu.init();
		};
	</script>

<div id="my_menu" class="sdmenu" style="font-family:微软雅黑;">
	<c:if test="${user.type != 4 || user.type != '4'}">
			<div >
				<span>管理</span>
				<c:if test="${user.type == 1 || user.type == '1' || user.type == 2 || user.type == '2'}">
					<a href="${path}/webUser/selectUser">用户</a>
				</c:if>
					<a href="${path}/webUser/userRights">用户授权</a>
<%-- 				<c:if test="${user.type == 1 || user.type == '1' || user.type == '2' || user.type == 2  }"> --%>
<%-- 					<a href="${path}/webUser/userRightsApp">APP用户授权</a> --%>
<%-- 				</c:if> --%>
			</div>
	</c:if>
	<div >
		<span>设备</span>
			<a href="${path}/webSubstation/selectSubstationByType">变电站管理</a>
			<a href="${path}/webBaiDu/dtol">变电站地图</a>
		<c:if test="${user.type == 1 || user.type == '1'}">
				<a href="${path}/webEquipmentType/selectById">设备类型</a>
				<a href="${path}/webSkEquipment/selectEquipment">测温设备成品库</a>
		</c:if>
	</div>
	<%--<c:if test="${user.type != 4 || user.type != '4'}">--%>
		<div >
			<span>信息</span>
	<%-- 			<a href="${path}/webTemperatureDetection/voUDP">网络管理</a> --%>
				<a href="${path}/webAlarmInformation/selectAlarmInformation">报警信息</a>
		</div>
	<c:if test="${user.type == 1 || user.type == '1'}">
	<div >
		<span>app上传</span>
		<%-- 			<a href="${path}/webTemperatureDetection/voUDP">网络管理</a> --%>
		<%--<c:if test="${user.type != 4 || user.type != '4'}">--%>
		<a href="${path}/webAppApk/select">app上传</a>
	</div>
	</c:if>
</div>

     </div>
     <div class="Switch"></div>
<script type="text/javascript">
$(document).ready(function(e) {
    $(".Switch").click(function(){
	    $(".left").toggle();
	});
});


</script>

