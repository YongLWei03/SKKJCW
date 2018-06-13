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
<script type="text/javascript" src="${path}/resource/js/echarts.js"></script>
<script type="text/javascript" src="${path}/resource/js/vintage.js"></script>
<script type="text/javascript" src="${path}/resource/js/news.js"></script>
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

<body background="${substationImage }" style="background-repeat:no-repeat; background-attachment: fixed; background-size: 100% 100%;">
				<input type="hidden" value="${deviceNumber }" id = "deviceNumber">
				<input type="hidden" value="0" id = "cheke">
				<input type="hidden" value="${substationId }" id = "substationId">
				<input type="hidden" value="${substationImage }" id = "substationImage">
				<input type="hidden" value="${user.type }" id= "userType">
				<input type="hidden" value="${numberDevices }" id= "numberDevices">
				<input type="hidden" value="${isRoot }" id= "isRoot">
				<input type="hidden" value="${deviceSbId }" id= "deviceSbId">
				<input type="hidden" value="${deviceType }" id= "deviceType">
				<input type="hidden" value="0" id = "real">
				<input type="hidden" value="0" id = "cishu">
				<div style="width: 100%; margin: auto;">
					<table class="table table-bordered" style="">
						<tr>
							<td width="5%">
								<input
								type="button"   value="实时数据" onclick="realtime();"class="btn btn-primary"
								style="width: 94px; height: 28px; font-family: 微软雅黑;" />
							</td>
							<td width="5%">
								<input
								type="button"  value="历史数据" onclick="lishi();"class="btn btn-primary"
								style="width: 94px; height: 28px; font-family: 微软雅黑;"/>
							</td>
							<td width="5%">
								<input
								type="button"   value="返回" onclick="retur();"class="btn btn-primary"
								style="width: 94px; height: 28px; font-family: 微软雅黑;"/>
							</td>
							<td width="20%">
							</td>
						</tr>
					</table>
				</div>
				<div id="route">
					
				</div>
	
<script type="text/javascript">
	var sj =[];
	var inA =[];
	var inB =[];
	var inC =[];
	function ss(){
        var ljcj = $("#ljcj").val();
        if(ljcj == 0 || ljcj == "0"){
            $("#ljcj").val("1");
        }

            var chaken = 0;
        if(ljcj != null && ljcj != ''){
            //此处演示关闭
            setTimeout(function(){
                var deviceNumber = $("#deviceNumber").val();
                $.ajax({
                    url:'${path}/webTemperatureDetection/realTime',
                    data:{
                        'deviceNumber' : deviceNumber,
                        'ljcj' : ljcj,
                    },
                    type:'post',
                    cache:false,
                    async:false,
                    dataType:'json',
                    success:function(data) {
                        var dataTemp = data.temp;
                        if(dataTemp != "false"){
                            var str = "";
                            str += "<tr>";
                            str += "<td width='20%'align='center'>"+data.temp.time+"</td>";
                            if(data.temp.inA != "EEEEEEEE" && data.temp.inA != "DDDDDDDD"){
                                str += "<td width='20%'align='center'>"+data.temp.inA+"</td>";
                            }else if(data.temp.inA == "EEEEEEEE"){
                                str += "<td width='20%'align='center'>未校准</td>";
                            }else if(data.temp.inA == "DDDDDDDD"){
                                str += "<td width='20%'align='center'>有干扰</td>";
                            }
                            if(data.temp.inB != "EEEEEEEE" && data.temp.inB != "DDDDDDDD"){
                                str += "<td width='20%'align='center'>"+data.temp.inB+"</td>";
                            }else if(data.temp.inB == "EEEEEEEE"){
                                str += "<td width='20%'align='center'>未校准</td>";
                            }else if(data.temp.inB == "DDDDDDDD"){
                                str += "<td width='20%'align='center'>有干扰</td>";
                            }
                            if(data.temp.inC != "EEEEEEEE" && data.temp.inC != "DDDDDDDD"){
                                str += "<td width='20%'align='center'>"+data.temp.inC+"</td>";
                            }else if(data.temp.inC == "EEEEEEEE"){
                                str += "<td width='20%'align='center'>未校准</td>";
                            }else if(data.temp.inC == "DDDDDDDD"){
                                str += "<td width='20%'align='center'>有干扰</td>";
                            }
                            if(data.temp.type == 1 || data.temp.type == '1'){
                                str += "<td width='20%'align='center'>正常</td>";
                            }
                            if(data.temp.type == 2 || data.temp.type == '2'){
                                str += "<td width='20%'align='center'>报警</td>";
                            }
                            if(data.temp.type == 3 || data.temp.type == '3'){
                                str += "<td width='20%'align='center'>异常</td>";
                            }
                            str += "</tr>";
                            sj.push(data.temp.time);
                            inA.push(data.temp.inA);
                            inB.push(data.temp.inB);
                            inC.push(data.temp.inC);

// 				    	inC.push(data.temp.inC);
                            $("#tContent").append(str);
                            $('#swiper').scrollTop($('#tContent').find('tr').innerHeight() * $('#tContent').find('tr').length);
                            var chart = echarts.init(document.getElementById('main'));
                            chart.setOption({
                                title: {
                                    text: '温度采集',
                                    subtext: ''
                                },
                                tooltip: {
                                    trigger: 'axis'
                                },
                                legend: {
                                    data:['A相入','B相入','C相入']
                                },
                                toolbox: {
                                    show: true,
                                    feature: {
                                        magicType: {show: true, type: ['tiled']},
                                        saveAsImage: {show: true}
                                    }
                                },
                                xAxis: {
                                    type: 'category',
                                    boundaryGap: false,
                                    data: sj
                                },
                                yAxis: {
                                    type: 'value'
                                },
                                series: [{
                                    name: 'A相入',
                                    type: 'line',
                                    smooth: true,
                                    data: inA
                                },
                                    {
                                        name: 'B相入',
                                        type: 'line',
                                        smooth: true,
                                        data: inB
                                    },
                                    {
                                        name: 'C相入',
                                        type: 'line',
                                        smooth: true,
                                        data: inC
                                    }]
                            });
                        }
                        ss();
                    }
                });

            }, 2000);
		}
}
    var sj2 =[];
    var inA2 =[];
    var inB2 =[];
    var inC2 =[];
    function selectHis(sort) {
        layer.load(1);
        setTimeout(function(){
        sj2 =[];
        inA2 =[];
        inB2 =[];
        inC2 =[];
        var deviceNumber = $("#deviceNumber").val();
        var startTime = $("#test1").val();//开始时间
        var endTime = $("#test2").val();//结束时间
        var cheke = $("#cheke").val();//结束时间
        var numberDevices = $("#numberDevices").val();//有几个传感器
        if(startTime == null || startTime == ''){
            layer.msg('请输入开始时间');
            return false;
        }
        if(endTime == null || endTime == ''){
            layer.msg('请输入结束时间');
            return false;
        }
        $("#cishu").val(1);
        $.ajax({
            url:'${path}/webTemperatureDetection/historyTemp',
            data:{
                'deviceNumber' : deviceNumber,
                'startTime' : startTime,
                'endTime' : endTime,
                'sort':sort,
                'numberDevices':numberDevices
            },
            type:'post',
            cache:false,
            async:false,
            dataType:'json',
            success:function(data) {
                if(data.filel != null && data.filel != ''){
                    var str = "";
                    for ( var i = 0; i < data.filel.length-1; i++) {
                        var c = i+1;
                        str += '<input type="hidden" value="'+data.filel[c]+'" id = "fdId'+c+'">';
                    }
                    $("#route").html('');
                    $("#route").append(str);
                }else{
                    $("#route").html('');
                    layer.msg('当前时间没有数据加载');
                }
                if(cheke != 0 || cheke != '0'){
                    sj2.splice(1,sj2.length);
                    inA2.splice(1,inA2.length);
                    inB2.splice(1,inB2.length);
                    inC2.splice(1,inC2.length);
                }
                if(data.tempList != null && data.tempList != ''){

                    var str = "";
                    for ( var i = 0; i < data.tempList.length; i++) {

                        str += "<tr id='qc'>";
                        str += "<td width='20%'align='center'>"+data.tempList[i].time+"</td>";
                        if(data.tempList[i].inA != "EEEEEEEE" && data.tempList[i].inA != "DDDDDDDD"){
                            str += "<td width='12%'align='center'>"+data.tempList[i].inA+"</td>";
                        }else if(data.tempList[i].inA == "EEEEEEEE"){
                            str += "<td width='12%'align='center'>未校准</td>";
                        }else if(data.tempList[i].inA == "DDDDDDDD"){
                            str += "<td width='12%'align='center'>有干扰</td>";
                        }
                        if(data.tempList[i].inB != "EEEEEEEE" && data.tempList[i].inB != "DDDDDDDD"){
                            str += "<td width='12%'align='center'>"+data.tempList[i].inB+"</td>";
                        }else if(data.tempList[i].inB == "EEEEEEEE"){
                            str += "<td width='12%'align='center'>未校准</td>";
                        }else if(data.tempList[i].inB == "DDDDDDDD"){
                            str += "<td width='12%'align='center'>有干扰</td>";
                        }
                        if(data.tempList[i].inC != "EEEEEEEE" && data.tempList[i].inC != "DDDDDDDD"){
                            str += "<td width='12%'align='center'>"+data.tempList[i].inC+"</td>";
                        }else if(data.tempList[i].inC == "EEEEEEEE"){
                            str += "<td width='12%'align='center'>未校准</td>";
                        }else if(data.tempList[i].inC == "DDDDDDDD"){
                            str += "<td width='12%'align='center'>有干扰</td>";
                        }
                        if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                            str += "<td width='20%'align='center'>正常</td>";
                        }
                        if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                            str += "<td width='20%'align='center'>报警</td>";
                        }
                        if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                            str += "<td width='20%'align='center'>异常</td>";
                        }
                        str += "</tr>";
                        sj2.push(data.tempList[i].time);
                        inA2.push(data.tempList[i].inA);
                        inB2.push(data.tempList[i].inB);
                        inC2.push(data.tempList[i].inC);
                    }
                    if(cheke == 0 || cheke == '0'){
                        $("#cheke").val(1);
                    }else{
                        $("#LsContent").html('');
                    }
                    $("#LsContent").append(str);

                    var chart = echarts.init(document.getElementById('main2'));
                    chart.setOption({
                        title: {
                            text: '温度采集',
                            subtext: ''
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['A相入','B相入','C相入']
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                magicType: {show: true, type: ['tiled']},
                                saveAsImage: {show: true}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            maxInterval: 3600 * 24 * 1000,
                            data: sj2
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [{
                            name: 'A相入',
                            type: 'line',
                            smooth: true,
                            data: inA2
                        },
                            {
                                name: 'B相入',
                                type: 'line',
                                smooth: true,
                                data: inB2
                            },
                            {
                                name: 'C相入',
                                type: 'line',
                                smooth: true,
                                data: inC2
                            }]
                    });
                }else{
                    layer.msg('当前时间没有数据加载');
                    $("#LsContent").html('');
                }
                layer.closeAll('loading');
            }
        });
        }, 2000);
    }
	
	function lishi() {
         sj2 =[];
         inA2 =[];
        inB2 =[];
        inC2 =[];
		layer.open({
			  type: 1,
			  title: false,
			  closeBtn: 1,
			  shadeClose: false,
			  area: ['70%', '80%'],
			  skin: 'yourclass',
			  content: '<div style="padding-right:17px;">'+
			  '<table class="table table-bordered" style="">'+
				'<tr>'+
					'<td width="50%">'+
					'<div class="layui-inline">'+
				      '<label class="layui-form-label">开始时间</label>'+
				      '<div class="layui-input-inline">'+
				        '<input type="text" class="layui-input" id="test1" placeholder="年/月/日 时/分/秒" style="height:35px;">'+
				      '</div>'+
				    '</div>'+
				    '<div class="layui-inline">'+
				      '<label class="layui-form-label" style="margin-left: 10px;">结束时间</label>'+
				      '<div class="layui-input-inline">'+
				        '<input type="text" class="layui-input" id="test2" placeholder="年/月/日 时/分/秒" style="height:35px;">'+
				      '</div>'+
				    '</div>'+
						'<input type="button" id = "select"  value="搜索" onclick="selectHis(0);"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
						'<input type="button" id = "selectjx"  value="继续加载" onclick="selectHisjx();"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
					'</td>'+
				'</tr>'+
			'</table>'+
			'<table class="table table-bordered table-hover table-striped" style="font-family: 微软雅黑;width:100%;height:200px;position:relative;" id = "wd">'+
					'<thead>'+
							'<tr>'+
								'<th width="20%">温度时间</th>'+
								'<th width="20%">A相入温度</th>'+
			     				'<th width="20%">B相入温度</th>'+
								'<th width="20%">C相入温度</th>'+
								'<th width="20%">状态</th>'+
							'</tr>'+
					'</thead>'+
				'</table>'+
		  '</div>'+
		  '<div style="width:100%;height:200px;overflow-y:scroll; border-bottom:1px solid #dddddd">'+
		  '<table id="Template_listZ" class="footable foottable_tab" width="100%">'+
		  '<tbody id="LsContent" style="width:100%;">'+
		  '</tbody>'+
		  '</table>'+
		  '</div>'+
		  '<div id="main2" style="width: 1000px;height:400px;top:10px;"></div>'
		});
		ZX2();
		time();
	}
	function realtime() {
         sj =[];
        inA =[];
         inB =[];
         inC =[];
		layer.open({
			  type: 1,
			  title: false,
			  closeBtn: 1,
			  shadeClose: false,
			  area: ['70%', '80%'],
			  skin: 'yourclass',
			  content: '<div style="padding-right:17px;">'+
              '<table class="table table-bordered" style="">'+
              '<tr>'+
              '<td width="50%">'+
              '<input type="hidden" value="0" id = "ljcj">'+
              '<input type="button" id = "select"  value="立即查看" onclick="ljck();"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
              '<input type="button" id = "selectjx"  value="命令执行情况" onclick="zxqk();"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
              '</td>'+
              '</tr>'+
              '</table>'+
			'<table class="table table-bordered table-hover table-striped" style="font-family: 微软雅黑;width:100%;height:200px;position:relative;" id = "wd">'+
					'<thead>'+
							'<tr>'+
								  '<th width="20%">温度时间</th>'+
								  '<th width="20%">A相入温度</th>'+
								  '<th width="20%">B相入温度</th>'+
								  '<th width="20%">C相入温度</th>'+
								  '<th width="20%">状态</th>'+
							'</tr>'+
					'</thead>'+
				'</table>'+
		  '</div>'+
		  '<div style="width:100%;height:200px;overflow-y:scroll; border-bottom:1px solid #dddddd" id="swiper">'+
		  '<table id="Template_listZ" class="footable foottable_tab" width="100%">'+
		  '<tbody id="tContent" style="width:100%;">'+
		  '</tbody>'+
		  '</table>'+
		  '</div>'+
		  '<div id="main" style="width: 1000px;height:400px;top:10px;"></div>'
		});
		ZX();
		ss();
	}

	function time() {
		layui.use('laydate', function(){
					  var laydate = layui.laydate;
					//时间选择器
					  laydate.render({
					    elem: '#test1'
                          ,type: 'datetime'
					  });
					//时间选择器
					  laydate.render({
						    elem: '#test2'
                          ,type: 'datetime'
					  });
					  
			});
	}
	

	function selectHisjx() {
		var deviceNumber = $("#deviceNumber").val();
		var startTime = $("#test1").val();//开始时间
		var endTime = $("#test2").val();//结束时间
		var cishu = $("#cishu").val();
        var numberDevices = $("#numberDevices").val();//有几个传感器
        if(startTime == null || startTime == ''){
            layer.msg('请输入开始时间');
            return false;
        }
        if(endTime == null || endTime == ''){
            layer.msg('请输入结束时间');
            return false;
        }
		if(cishu != '0' && cishu != 0){
			var sort = $("#fdId"+cishu).val();
			if(sort != null && sort != ''){
				$.ajax({    
				    url:'${path}/webTemperatureDetection/historyTemp',   
				    data:{    
				    	'deviceNumber' : deviceNumber,
				    	'startTime' : startTime,
				    	'endTime' : endTime,
				    	'sort':sort,
                        'numberDevices':numberDevices
				    },    
				    type:'post',    
				    cache:false,
				    async:false,
				    dataType:'json',    
				    success:function(data) {
				    	if(data.tempList != null && data.tempList != ''){
						    	var str = "";
				    		for ( var i = 0; i < data.tempList.length; i++) {
						    	str += "<tr id='qc'>";
						    	str += "<td width='20%'align='center'>"+data.tempList[i].time+"</td>";
						    	str += "<td width='20%'align='center'>"+data.tempList[i].inA+"</td>";
						    	str += "<td width='20%'align='center'>"+data.tempList[i].inB+"</td>";
						    	str += "<td width='20%'align='center'>"+data.tempList[i].inC+"</td>";
						    	if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
						    		str += "<td width='20%'align='center'>正常</td>";
						    	}
						    	if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
						    		str += "<td width='20%'align='center'>报警</td>";
						    	}
						    	str += "</tr>";
// 						    	sj2.push(data.tempList[i].time); 
					    		inA2.push(data.tempList[i].inA);
						    	inB2.push(data.tempList[i].inB);
					    		inC2.push(data.tempList[i].inC);
							}
					    		$("#LsContent").append(str);
					    		var chart = echarts.init(document.getElementById('main2'));
						    	chart.setOption({
						    		  title: {
						    		        text: '温度采集',
						    		        subtext: ''
						    		    },
						    		    tooltip: {
						    		        trigger: 'axis'
						    		    },
						    		    legend: {
						    		        data:['A相入','B相入','C相入']
						    		    },
						    		    toolbox: {
						    		        show: true,
						    		        feature: {
						    		            magicType: {show: true, type: ['tiled']},
						    		            saveAsImage: {show: true}
						    		        }
						    		    },
						    		    xAxis: {
						    		        type: 'category',
						    		        boundaryGap: false,
						    		        data: sj2
						    		    },
						    		    yAxis: {
						    		        type: 'value'
						    		    },
						    		    series: [{
						    		        name: 'A相入',
						    		        type: 'line',
						    		        smooth: true,
						    	            data: inA2
						    		    },
						    		    {
						    		        name: 'B相入',
						    		        type: 'line',
						    		        smooth: true,
						    		        data: inB2
						    		    },
						    		    {
						    		        name: 'C相入',
						    		        type: 'line',
						    		        smooth: true,
						    		        data: inC2
						    		    }]
						    	});
						}
                        $("#cishu").val(parseInt(cishu)+1);
						if(data.tempList == null || data.tempList == ''){
                            selectHisjx();
						}

				    }
				});
			}else {
			  layer.msg('没有更多数据加载了');
			}
		}else {
			layer.msg('请搜索后再在加载');
		}
	}
	function retur(){
		var substationId = $("#substationId").val();
		var substationImage = $("#substationImage").val();
		location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+encodeURI(encodeURI(substationImage));
	}
	function ZX() {
		var chart = echarts.init(document.getElementById('main'));
    	chart.setOption({
  		  title: {
  		        text: '温度采集',
  		        subtext: ''
  		    },
  		    tooltip: {
  		        trigger: 'axis'
  		    },
  		    legend: {
  		        data:['A相入','B相入','C相入']
  		    },
  		    toolbox: {
  		        show: true,
  		        feature: {
  		            magicType: {show: true, type: ['tiled']},
  		            saveAsImage: {show: true}
  		        }
  		    },
  		    xAxis: {
  		        type: 'category',
  		        boundaryGap: false,
  		        data: [0]
  		    },
  		    yAxis: {
  		        type: 'value'
  		    },
  		    series: [{
  		        name: 'A相入',
  		        type: 'line',
  		        smooth: true,
  	            data: [0]
  		    },
  		    {
  		        name: 'B相入',
  		        type: 'line',
  		        smooth: true,
  		        data: [0]
  		    },
  		    {
		        name: 'C相入',
		        type: 'line',
		        smooth: true,
		        data: [0]
		    }]
  		});
	}

    function ZX2() {
        var chart = echarts.init(document.getElementById('main2'));
        chart.setOption({
            title: {
                text: '温度采集',
                subtext: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['A相入','B相入','C相入']
            },
            toolbox: {
                show: true,
                feature: {
                    magicType: {show: true, type: ['tiled']},
                    saveAsImage: {show: true}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: [0]
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                name: 'A相入',
                type: 'line',
                smooth: true,
                data: [0]
            },
                {
                    name: 'B相入',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'C相入',
                    type: 'line',
                    smooth: true,
                    data: [0]
                }]
        });
    }
    function ljck() {
        layer.load(1);
        setTimeout(function(){
            layer.closeAll('loading');
        }, 5000);
        var deviceNumber = $("#deviceNumber").val();
        var deviceType = $("#deviceType").val();
        var deviceSbId = $("#deviceSbId").val();
        var isRoot = $("#isRoot").val();
        $.ajax({
            url:'${path}/webCommand/immediateSampling',
            data:{
                'DeviceType' : deviceType,
                'isRoot' : isRoot,
                'deviceNumber' : deviceNumber,
                'deviceSbId' : deviceSbId,
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if(data == "true"){
                    layer.msg('命令发送成功');

                }else {
                    layer.msg('正在处理其他用户命令请查看命令执行情况');
                }
            }
        });
    }

    function zxqk() {
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: false,
            area: ['60%','40%'],
            skin: 'yourclass',
            btn: ['查询命令', '取消'],//只是为了演示
            yes: function(){
                var deviceNumber = $("#deviceNumber").val();
                $.ajax({
                    url:'${path}/webCommandInformation/selectByDeviceNumber',
                    data:{
                        'deviceNumber' : deviceNumber,
                    },
                    type:'post',
                    cache:false,
                    dataType:'json',
                    success:function(data) {
                        var str = "";
                        for (var i = 0; i < data.commandInfList.length; i++) {
                            str += "<tr>";
                            str += "<td width='25%'align='center'>"+data.commandInfList[i].time+"</td>";
                            str += "<td width='25%'align='center'>"+data.commandInfList[i].userName+"</td>";
                            str += "<td width='25%'align='center'>"+data.commandInfList[i].name+"</td>";

                            if(data.commandInfList[i].type == 1 || data.commandInfList[i].type == '1'){
                                str += "<td width='25%'align='center'>成功</td>";
                            }
                            if(data.commandInfList[i].type == 2 || data.commandInfList[i].type == '2'){
                                str += "<td width='25%'align='center'>送达</td>";
                            }
                            if(data.commandInfList[i].type == 3 || data.commandInfList[i].type == '3'){
                                str += "<td width='25%'align='center'>发送成功</td>";
                            }
                            if(data.commandInfList[i].type == 4 || data.commandInfList[i].type == '4'){
                                str += "<td width='25%'align='center'>超时</td>";
                            }
                            if(data.commandInfList[i].type == 5 || data.commandInfList[i].type == '5'){
                                str += "<td width='25%'align='center'>失败</td>";
                            }
                            str += "</tr>";
                        }
                        $("#mlzt").html('');
                        $("#mlzt").append(str);
                    }
                });
            }
            ,btn2: function(){
                layer.closeAll(this);
            },
            content: '<div style="">'+
            '<table class="table table-bordered table-hover table-striped" style="font-family: 微软雅黑;width:100%;height:200px;position:relative;" id = "wd">'+
            '<thead>'+
            '<tr>'+
            '<th width="25%" align=\'center\'>时间</th>'+
            '<th width="25%" align=\'center\'>用户</th>'+
            '<th width="25%" align=\'center\'>命令名称</th>'+
            '<th width="25%" align=\'center\'>状态</th>'+
            '</tr>'+
            '</thead>'+
            '</table>'+
            '</div>'+
            '<div style="width:100%;height:200px;overflow-y:scroll; border-bottom:1px solid #dddddd">'+
            '<table id="Template_listZ" class="footable foottable_tab" width="100%">'+
            '<tbody id="mlzt" style="width:100%;">'+
            '</tbody>'+
            '</table>'+
            '</div>'
        });
    }
</script>

</body>

</html>
