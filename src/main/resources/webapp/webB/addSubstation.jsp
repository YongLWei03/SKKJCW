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
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0bcrcezCrEBGNzv13ohdTCcr4F2w52Tz"></script>
	<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript" src="${path}/resource/js/layer/layer.js"></script>
<script type="text/javascript" src="${path}/resource/js/jquery-3.2.1.js"></script>
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
						<span class="divider">/</span> 变电站
					</li>
				</ul>

				<div class="title_right">
					<strong>变电站添加</strong>
				</div>
				<input type="hidden" id="provinceId" value="${sustation.provinceId }">
				<input type="hidden" id="cityId" value="${sustation.cityId }">
				<input type="hidden" id="areaId" value="${sustation.areaId }">
				<input type="hidden" value="${sustation.substationImage }" id="tPic">
				<div style="width: 1200px; margin: auto;"></div>
				<table class="table table-bordered" style="font-family: 微软雅黑;">
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							变电站名称：</td>
						<td width="80%"><input type="text" value="${sustation.substationName }"
							style="height: 25px;" id="substationName"></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							变电站所属地区：</td>
						<td width="80%" id = "dq">
							<select id="province" style="height: 25px; width: 100px;" onchange="provinceC()">
									<option value="0">全省</option>
								<c:forEach items="${regionList }" var="regionList" varStatus="s">
									<c:if test="${regionList.regionId == sustation.provinceId }">
										<option value="${regionList.regionId }" selected="selected">${regionList.regionName }</option>
									</c:if>
									<c:if test="${regionList.regionId != sustation.provinceId || sustation.provinceId == '' || sustation.provinceId == null}">
										<option value="${regionList.regionId }">${regionList.regionName }</option>
									</c:if>
								</c:forEach>
								</select>
								<select id="city" style="height: 25px; width: 100px;" onchange="city();">
									<option value="0">全市</option>
								</select>
								<select id="area" onchange="area();" style="height: 25px; width: 100px;">
									<option value="0">全区</option>
								</select>
						</td>
					</tr>

					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							经度：</td>
						<td width="80%"><input type="text" value="${sustation.accuracy }"
							style="height: 25px;" id="accuracy" ></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							纬度：</td>
						<td width="80%"><input type="text" value="${sustation.latitude }" style="height: 25px;" id="latitude"></td>
					</tr>
                    <tr height="50px;">
                        <td width="20%" style="font-size: 15px; text-align: center;">
                            地址检索：</td>
                        <td width="80%">
                            <div id="r-result">请输入:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;height: 30px" /></div>
                            <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
                        </td>
                    </tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							坐标拾取(可以通过检索或者单击地图位置获取坐标)：</td>
						<td width="80%"><div style="width:100%;height:200px;border:#ccc solid 1px;font-size:12px" id="map"></div></td>
					</tr>
					<tr height="50px;">
						<td width="20%" style="font-size: 15px; text-align: center;">
							变电站图纸：</td>
						<td width="80%">
							<c:if test="${sustation.substationImage!=null && sustation.substationImage!= ''}">
								<img id="imaget"
								src="${sustation.substationImage }"
								style="width: 100px; height: 100px; margin-left: 40px; margin-top: 10px;" />
						   </c:if>
							<c:if test="${sustation.substationImage==null || sustation.substationImage == ''}">
								<img id="imaget"
								src=""
								style="width: 100px; height: 100px; margin-left: 40px; margin-top: 10px;" />
						   </c:if>
							<br /> <input type="file" onchange="selectImaget(this);"
							id="fileengineerpict" name="fileengineerpict"
							style="width: 68px; margin-left: 40px;" /> <input type="button"
							onclick="uploadengineerpict()" value="上传" /> <span
							style="color: green; visibility: hidden;" id="suc">上传成功</span>
						</td>
					</tr>
				</table>
				<div style="text-align: center; margin-top: 30px;">
					<c:if test="${sustation!=null}">
						<input type="button" value="修改" onclick="upda(${sustation.substationId})"
							class="btn btn-primary"
							style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
					</c:if>
					<c:if test="${sustation==null}">
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
<div id="zb">

</div>
	<!-- 底部 -->
	<%@include file="/resource/common/footer.jsp"%>

	<script type="text/javascript">
        //创建和初始化地图函数：
        function initMap(){
            createMap();//创建地图
            setMapEvent();//设置地图事件
            addMapControl();//向地图添加控件
            addMapOverlay();//向地图添加覆盖物
        }
        var accuracy = $("#accuracy").val();
        var latitude = $("#latitude").val();
        if(accuracy == null || accuracy == ''){
            function myFun(result){
                var cityName = result.name;
                map.setCenter(cityName);
                // alert("当前定位城市:"+cityName);
            }
            var myCity = new BMap.LocalCity();
            myCity.get(myFun);
        }
        function createMap(){
            map = new BMap.Map("map");

            // 百度地图API功能
            // var map = new BMap.Map("l-map");
            if(accuracy != null && accuracy != ''){
                var point = new BMap.Point(accuracy,latitude);
                map.centerAndZoom(point, 18);
                var marker = new BMap.Marker(point);// 创建标注
                map.addOverlay(marker);             // 将标注添加到地图中
                marker.disableDragging();           // 不可拖拽
            }else {
                map.centerAndZoom(new BMap.Point(117.228303,40.075409),18);
            }


        }
        function setMapEvent(){
            map.enableScrollWheelZoom();
            map.enableKeyboard();
            map.enableDragging();
            map.enableDoubleClickZoom()
        }
        function addClickHandler(target,window){
            target.addEventListener("click",function(){
                target.openInfoWindow(window);
            });
        }
        function addMapOverlay(){
        }
        //向地图添加控件
        function addMapControl(){
            var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
            scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
            map.addControl(scaleControl);
            var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
            map.addControl(navControl);
            var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
            map.addControl(overviewControl);
        }
        var map;
        initMap();
        //单击获取点击的经纬度
        map.addEventListener("click",function(e){
            $("#accuracy").val(e.point.lng);
            $("#latitude").val(e.point.lat);
            map.clearOverlays();
            var point = new BMap.Point(e.point.lng,e.point.lat);
            map.centerAndZoom(point, 18);
            var marker = new BMap.Marker(point);// 创建标注
            map.addOverlay(marker);             // 将标注添加到地图中
            marker.disableDragging();           // 不可拖拽
        });
        //输入选择框
        function G(id) {
            return document.getElementById(id);
        }
        var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
            {"input" : "suggestId"
                ,"location" : map
            });

        ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
            var str = "";
            var _value = e.fromitem.value;
            var value = "";
            if (e.fromitem.index > -1) {
                value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            }
            str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

            value = "";
            if (e.toitem.index > -1) {
                _value = e.toitem.value;
                value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            }
            str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
            G("searchResultPanel").innerHTML = str;
        });

        var myValue;
        ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
            var _value = e.item.value;
            myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

            setPlace();
        });

        function setPlace(){
            map.clearOverlays();    //清除地图上所有覆盖物
            function myFun(){
                var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
                map.centerAndZoom(pp, 18);
                map.addOverlay(new BMap.Marker(pp));    //添加标注
            }
            var local = new BMap.LocalSearch(map, { //智能搜索
                onSearchComplete: myFun
            });
            local.search(myValue);
        }

	$(function(){
		var provinceId = $("#provinceId").val();
		if(provinceId != null || provinceId != ''|| provinceId != 0 || provinceId != '0'){
			provinceC();
		}
	});
	
	
	function insert(){
		var substationName = $("#substationName").val();
		var accuracy = $("#accuracy").val();
		var latitude = $("#latitude").val();
		var tPic = $("#tPic").val();
		var provinceId = $("#province").val();
		var cityId = $("#city").val();
		var areaId = $("#area").val();
		var substationPY = $("#substationPY").val();
		if(substationName==null || substationName==""){
			layer.msg('变电站名称不能为空');
			return false;
		}

		$.ajax({    
		    url:'${path}/webSubstation/insertSubstation',   
		    data:{    
		    	'substationName' : substationName,
		    	'accuracy' : accuracy,
		    	'latitude':latitude,
		    	'substationImage' : tPic,
		    	'provinceId' : provinceId,
		    	'cityId' : cityId,
		    	'areaId' : areaId,
                'substationPY':substationPY
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	if(data=="true"){
		    		location.href="${path}/webSubstation/selectSubstationByType";
		    	}else if(data == "false"){
		    		layer.msg('添加失败');
		    	}
		    }
		}); 
	}
	
	function remo(value){
		location.href="${path}/webSubstation/selectSubstationByType";
	}
	
	function upda(substationId){
		var substationName = $("#substationName").val();
		var accuracy = $("#accuracy").val();
		var latitude = $("#latitude").val();
		var tPic = $("#tPic").val();
		var provinceId = $("#province").val();
		var cityId = $("#city").val();
		var areaId = $("#area").val();
        var substationPY = $("#substationPY").val();
		if(substationName==null || substationName==""){
			layer.msg('变电站名称!');
			return false;
		}

		$.ajax({    
		    url:'${path}/webSubstation/updateBySubstationId',   
		    data:{    
		    	'substationName' : substationName,
		    	'accuracy' : accuracy,
		    	'latitude':latitude,
		    	'substationImage' : tPic,
		    	'provinceId' : provinceId,
		    	'cityId' : cityId,
		    	'areaId' : areaId,
		    	'substationId' : substationId,
				'substationPY':substationPY
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
                console.log(data)
		    	if(data=="true"){
		    		location.href="${path}/webSubstation/selectSubstationByType";
		    	}else if(data == "false"){
		    		layer.msg('添加失败');
		    	}
		    }
		}); 
	}
	
	
	function provinceC(){
		var provinceId = $('#province').val();
		var cityId = $('#cityId').val();
		var areaId = $('#areaId').val();
		if(provinceId != '0' || provinceId != 0){
			$.ajax({    
			    url:'${path}/webRegion/findeByRegionIdS',   
			    data:{    
			    	'regionId' : provinceId,
			    },    
			    type:'post',    
			    cache:false,
			    async:false,
			    dataType:'json',    
			    success:function(data) {
						var str =''; 
						str += '<select id="city" style="height: 25px; width: 100px;" onchange="city();">';
							str += '<option value="0">全市</option>';
			    	for ( var i = 0; i < data.regionsList.length; i++) {
			    		if(data.regionsList[i].regionId == cityId){
							str += '<option value="'+data.regionsList[i].regionId+'" selected="selected">'+data.regionsList[i].regionName+'</option>';
			    		}else {
			    			str += '<option value="'+data.regionsList[i].regionId+'">'+data.regionsList[i].regionName+'</option>';
						}
					}
			    	str += '</select> ';
			    	str += '<select id="area" onchange="area();" style="height: 25px; width: 100px;">';
			    	str += '<option value="0">全区</option>';
			    	str += '</select>';
			    	$("#city").remove();
			    	$("#area").remove();
			    	$("#dq").append(str);
			    	if(cityId != '0' || cityId != 0){
			    		city();
			    	}
			    }
			    
			}); 
		}else {
			var str =''; 
			str += '<select id="city" style="height: 25px; width: 100px;">';
	    	str += '<option value="0">全市</option>';
	    	str += '</select> ';
			str += '<select id="area" style="height: 25px; width: 100px;">';
	    	str += '<option value="0">全区</option>';
	    	str += '</select>';
	    	$("#city").remove();
	    	$("#area").remove();
	    	$("#dq").append(str);
		}
	}
	
	function city(){
		var cityId = $('#city').val();
		var areaId = $('#areaId').val();
		if(cityId != '0' || cityId != 0){
			$.ajax({    
			    url:'${path}/webRegion/findeByRegionIdX',   
			    data:{    
			    	'regionId' : cityId,
			    },    
			    type:'post',    
			    cache:false,
			    async:false,
			    dataType:'json',    
			    success:function(data) {
						var str =''; 
						str += '<select id="area" onchange="area();" style="height: 25px; width: 100px;">';
				    		str += '<option value="0">全区</option>';
			    	for ( var i = 0; i < data.regionxList.length; i++) {
			    		if(data.regionxList[i].regionId == areaId){
							str += '<option value="'+data.regionxList[i].regionId+'" selected="selected">'+data.regionxList[i].regionName+'</option>';
			    		}else {
			    			str += '<option value="'+data.regionxList[i].regionId+'">'+data.regionxList[i].regionName+'</option>';
						}
					}
			    	str += '</select>';
			    	$("#area").remove();
			    	$("#dq").append(str);
			    }
			    
			}); 
		}else {
			var str =''; 
			str += '<select id="area" style="height: 25px; width: 100px;">';
	    	str += '<option value="0">全区</option>';
	    	str += '</select>';
	    	$("#area").remove();
	    	$("#dq").append(str);
		}
	}
	
	//上传图片
	function uploadengineerpic() {
		var filevalue = $("#fileengineerpic").val();
		if (!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(filevalue)) {
			alert("请选择图片格式的文件！");
			return false;
		}
		$.ajaxFileUpload({
			url : '${path}/webB/imageUp.jsp',
			secureuri : false,
			fileElementId : 'fileengineerpic',// 上传控件的id  
			dataType : 'json',
			success : function(data, status) {
				var basepath = $("#basePath").val();//全路径全球唯一
				document.getElementById("image").src = basepath
						+ data.url;
				$("#dPic").val(basepath + data.url);
				document.getElementById('succ').style.visibility= "visible";
//					document.getElementById("subtitcpic").src = basepath
//							+ data.url;
			},
			error : function(data, status, e) {
				alert('上传出错');
			}
		});
		return false;

	}

	function uploadengineerpict() {
        layer.load(1);
		var filevalue = $("#fileengineerpict").val();
		if (!/.(gif|jpg|jpeg|png|gif|jpg|png)$/.test(filevalue)) {
			alert("请选择图片格式的文件！");
            layer.closeAll('loading');
			return false;
		}
		$.ajaxFileUpload({
			url : '${path}/webB/imageUp.jsp',
			<%--url : '${path}/webImage/imagecc',--%>
			secureuri : false,
			fileElementId : 'fileengineerpict',// 上传控件的id  
			dataType : 'json',
			success : function(data, status) {
				var basepath = $("#basePath").val();//全路径全球唯一
				document.getElementById("imaget").src = basepath
						+ data.url;
				$("#tPic").val(basepath + data.url);
				document.getElementById('suc').style.visibility= "visible";
                layer.closeAll('loading');
//					$("#suc").setAttribute("visibility:","visibility");
//					alert("上传成功");
				
			},
			error : function(data, status, e) {
				alert('上传出错');
                layer.closeAll('loading');
			}
		});
		return false;

	}
	//选择图片并回显
	var image = '';
	function selectImage(file) {
		if (!file.files || !file.files[0]) {
			return;
		}
		var reader = new FileReader();
		reader.onload = function(evt) {
			document.getElementById('image').src = evt.target.result;

			image = evt.target.result;
		}
		reader.readAsDataURL(file.files[0]);
	}
	function selectImaget(file) {
		if (!file.files || !file.files[0]) {
			return;
		}
		var reader = new FileReader();
		reader.onload = function(evt) {
			document.getElementById('imaget').src = evt.target.result;

			image = evt.target.result;
		}
		reader.readAsDataURL(file.files[0]);
	}
	function search() {
		var query = $("#query").val();
		var region = $("#region").val();
		if(query != null && query != '' && region != null && region != ''){
			$.ajax({    
			    url:'${path}/webBaiDu/serche',   
			    data:{    
			    	'query' : query,
			    	'region' : region,
			    },
			    type:'post',    
			    cache:false,
			    async:false,
			    dataType:'json',    
			    success:function(data) {
			    	if(data.baduList == 'false'){
			    		layer.msg('地址输入错误');
			    	}else {
                        str = "";
                        str2 = "";
                        for (var i = 0; i < data.baduList.length; i++) {
                            str += '<option value='+i+'>'+data.baduList[i].name+'('+data.baduList[i].address+')</option>'
                        }
                        for (var i = 0; i < data.baduList.length; i++) {
                            str2 += '<input type="hidden" id = "lng'+i+'"  value="'+data.baduList[i].lng+'">'
                            str2 += '<input type="hidden" id="lat'+i+'" value="'+data.baduList[i].lat+'">'
                        }
                        $("#zb").html('');
                        $("#zb").append(str2);
                        $("#ssdz").html('');
                        $("#ssdz").append(str);
                    }
			    }
			    
			}); 
		}else {
            layer.msg('请输入地址');
		}
	}
	function addrxz() {
     	var ssdz = $("#ssdz").val();
     	var lng = $("#lng"+ssdz).val();
     	var lat = $("#lat"+ssdz).val();
     	 $("#accuracy").val(lng);
     	 $("#latitude").val(lat);

    }
</script>

</body>

</html>
