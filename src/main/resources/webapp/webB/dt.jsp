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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=0bcrcezCrEBGNzv13ohdTCcr4F2w52Tz"></script>
	<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	x{
  display:block;
  background:#f00;
  border-radius:50%;
  width:10px;
  height:10px;
  top:48px;
  right:200px;
  position:absolute;
}
	m{
  display:block;
  background:#28ff28;
  border-radius:50%;
  width:10px;
  height:10px;
  top:70px;
  right:200px;
  position:absolute;
}
	d{
  display:block;
  background:#FFFF00;
  border-radius:50%;
  width:10px;
  height:10px;
  top:89px;
  right:200px;
  position:absolute;
}
	</style>
	
</head>

<body>
	<!-- 顶部 -->
	<div id="allmap">
	<input onclick="" style="">
<%-- 		<input type="hidden" value='${substationList }' id="substationList"> --%>
	</div>

<script type="text/javascript">
$.ajax({
    url:'${path}/webBaiDu/dtl',
    type:'post',
    cache:false,
    dataType:'json',
    traditional: true,
    success:function(data) {
        // 百度地图API功能
        map = new BMap.Map("allmap");
    	if(data.sublist!= null && data.sublist != ''){
    		map.centerAndZoom(new BMap.Point(data.sublist[0].accuracy,data.sublist[0].latitude), 15);
            // 添加带有定位的导航控件
            var navigationControl = new BMap.NavigationControl({
                // 靠左上角位置
                anchor: BMAP_ANCHOR_TOP_LEFT,
                // LARGE类型
                type: BMAP_NAVIGATION_CONTROL_LARGE,
                // 启用显示定位
                enableGeolocation: true
            });
            map.addControl(navigationControl);
            // 添加定位控件
            var geolocationControl = new BMap.GeolocationControl();
            geolocationControl.addEventListener("locationSuccess", function(e){
                // 定位成功事件
                var address = '';
                address += e.addressComponent.province;
                address += e.addressComponent.city;
                address += e.addressComponent.district;
                address += e.addressComponent.street;
                address += e.addressComponent.streetNumber;

            });
            geolocationControl.addEventListener("locationError",function(e){
                // 定位失败事件
                alert(e.message);
            });
            map.addControl(geolocationControl);
            //定位控件
    		var data_info = [];
    		for ( var i = 0; i < data.sublist.length; i++) {
    				data_info.push([data.sublist[i].accuracy,data.sublist[i].latitude,"站点："+"<a href='javascript:void(0);' onclick='selec("+data.sublist[i].substationId+");'>"+data.sublist[i].substationName+"</a>"
    				                +"</br><x></x>"+data.sublist[i].count[1]+"<span style='margin-left: 71px;'>报警</span>"
    				                +"</br><m></m>"+data.sublist[i].count[2]+"<span style='margin-left: 71px;'>正常</span>"
    				                +"</br><d></d>"+data.sublist[i].count[0]+"<span style='margin-left: 71px;'>异常</span>"
    				                +"<input type='hidden' value='"+data.sublist[i].substationImage+"' id='substationImage'>"
    				                ]);
			}
    		var opts = {
    					width : 250,     // 信息窗口宽度
    					height: 100,     // 信息窗口高度
    					title : "变电站信息" , // 信息窗口标题
    					enableMessage:true//设置允许信息窗发送短息
    				   };
    		for(var i=0;i<data_info.length;i++){
    			var myIcon = new BMap.Icon("${path}/resource/image/bz.png", new BMap.Size(50,50));
    			var marker = new BMap.Marker(new BMap.Point(data_info[i][0],data_info[i][1]),{icon:myIcon});  // 创建标注
    			var content = data_info[i][2];
//     			map.addOverlay(marker);
    			// 将标注添加到地图中
//     			addClickHandler(content,marker);
    			map.addOverlay(marker);
    			addClickHandler(content,marker);// 将标注添加到地图中
    		}
    		function addClickHandler(content,marker){
    			marker.addEventListener("click",function(e){
    				openInfo(content,e);
    				}
    			);
    		}
    		function openInfo(content,e){
    			var p = e.target;
    			var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
//     			var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
    			var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
    			map.openInfoWindow(infoWindow,point); //开启信息窗口
    		}
            var size1 = new BMap.Size(30, 40);
    		 var cr = new BMap.CopyrightControl({anchor: BMAP_ANCHOR_TOP_RIGHT,offset:size1});   //设置版权控件位置
    			map.addControl(cr); //添加版权控件
    			var bs = map.getBounds();   //返回地图可视区域
    			cr.addCopyright({id: 2, content: "<a href='javascript:void(0);' style='font-size:24px;background:yellow;' onclick='liebiao();'>跳转到列表</a>", bounds: bs,offset:size1},new BMap.ScaleControl(size1));
    			//Copyright(id,content,bounds)类作为CopyrightControl.addCopyright()方法的参数

    			setTimeout(function(){
    				map.setZoom(14);
    			}, 2000);  //2秒后放大到14级
    			map.enableScrollWheelZoom(true);

            var size = new BMap.Size(70, 40);
            map.addControl(new BMap.CityListControl({
                anchor: BMAP_ANCHOR_TOP_LEFT,
                offset: size,
                // 切换城市之间事件
                // onChangeBefore: function(){
                //    alert('before');
                // },
                // 切换城市之后事件
                onChangeAfter:function(){
                   var substationArea = $("#cur_city_name").html();
                    $.ajax({
                        url:'${path}/webBaiDu/findByArer',
                        data:{
                            'substationArea' : substationArea,
                        },
                        type:'post',
                        cache:false,
                        dataType:'json',
                        success:function(data) {
                            if (data.subs != "false"){
                                map.centerAndZoom(new BMap.Point(data.subs.accuracy,data.subs.latitude), 15);
                            }
                            var cr = new BMap.CopyrightControl({anchor: BMAP_ANCHOR_TOP_RIGHT,offset:size1});   //设置版权控件位置
                            map.addControl(cr); //添加版权控件

                            var bs = map.getBounds();   //返回地图可视区域
                            cr.addCopyright({id: 2, content: "<a href='javascript:void(0);' style='font-size:25px;background:yellow;' onclick='liebiao();'>跳转到列表</a>", bounds: bs});
                        }
                    });
                    //Copyright(id,content,bounds)类作为CopyrightControl.addCopyright()方法的参数
                }
            }));

    	}else {
            //star自动定位
            var point = new BMap.Point(116.331398,39.897445);
            map.centerAndZoom(point,12);
            function myFun(result){
                var cityName = result.name;
                map.setCenter(cityName);
            }
            var myCity = new BMap.LocalCity();
            myCity.get(myFun);
            //结束自动定位

            // 添加带有定位的导航控件
            var navigationControl = new BMap.NavigationControl({
                // 靠左上角位置
                anchor: BMAP_ANCHOR_TOP_LEFT,
                // LARGE类型
                type: BMAP_NAVIGATION_CONTROL_LARGE,
                // 启用显示定位
                enableGeolocation: true
            });
            map.addControl(navigationControl);
            // 添加定位控件
            var geolocationControl = new BMap.GeolocationControl();
            geolocationControl.addEventListener("locationSuccess", function(e){
                // 定位成功事件
                var address = '';
                address += e.addressComponent.province;
                address += e.addressComponent.city;
                address += e.addressComponent.district;
                address += e.addressComponent.street;
                address += e.addressComponent.streetNumber;
            });
            geolocationControl.addEventListener("locationError",function(e){
                // 定位失败事件
                alert(e.message);
            });
            map.addControl(geolocationControl);
            //定位控件

            function myFun(result){
                var cityName = result.name;
                map.setCenter(cityName);
            }
            var myCity = new BMap.LocalCity();
            myCity.get(myFun);

            layer.msg("当前没有权限查看任何变电站信息")
            setTimeout(function(){
                location.href="${path}/webSubstation/selectSubstationByType";
            }, 1000);  //2秒后放大到14级

		}

    }

   });
	function selec(substationId){
		var substationImage = $("#substationImage").val();
		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+substationImage;
	}
	function liebiao(){
		location.href="${path}/webSubstation/selectSubstationByType";
	}
</script>

</body>

</html>
