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

<body background="${substationImage }" style="background-repeat:no-repeat; background-attachment: fixed; background-size: 100% 100%;">
				<input type="hidden" value="${deviceNumber }" id = "deviceNumber">
				<input type="hidden" value="0" id = "cheke">
				<input type="hidden" value="${substationId }" id = "substationId">
				<input type="hidden" value="${substationImage }" id = "substationImage">
				<input type="hidden" value="${user.type }" id= "userType">
				<input type="hidden" value="${isRoot }" id= "isRoot">
				<input type="hidden" value="${deviceSbId }" id= "deviceSbId">
				<input type="hidden" value="${deviceType }" id= "deviceType">
				<input type="hidden" value="${numberDevices }" id= "numberDevices">
				<input type="hidden" value="${currentPageSb }" id= "currentPageSb">
				<input type="hidden" value="" id= "time">
				<input type="hidden" value="0" id = "real">
				<
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
    var outA =[];
    var inB =[];
    var outB =[];
    var inC =[];
    var outC =[];
	function ss(){
        var ljcj = $("#ljcj").val();
        var userType = $("#userType").val();
        var chaken = 0;
        if(ljcj != null && ljcj != ''){
            //此处演示关闭
            setTimeout(function(){
                var deviceNumber = $("#deviceNumber").val();
                var time = $("#time").val();
                $("#ljcj").val("1");
                $.ajax({
                    url:'${path}/webTemperatureDetection/realTime',
                    data:{
                        'deviceNumber' : deviceNumber,
                        'ljcj':ljcj,
						'time':time,
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
                                    str += "<td width='14%'align='center'>"+data.temp.time+"</td>";
                                    if(data.temp.inA != "EEEEEEEE" && data.temp.inA != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.temp.inAxh != null && data.temp.inAxh != ''){
                                                    str += "<td width='12%'align='center'>"+data.temp.inA+"\/"+data.temp.inAxh+"</td>";
                                                }else {
                                                    str += "<td width='12%'align='center'>"+data.temp.inA+"</td>";
                                                }
                                            }else {
                                                str += "<td width='12%'align='center'>"+data.temp.inA+"</td>";
                                            }
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.temp.inA+"</td>";
                                        }
                                    }else if(data.temp.inA == "EEEEEEEE"){
                                        str += "<td width='12%'align='center'>未校准</td>";
                                    }else if(data.temp.inA == "DDDDDDDD"){
                                        str += "<td width='12%'align='center'>有干扰</td>";
                                    }
                                    if(data.temp.inB != "EEEEEEEE" && data.temp.inB != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.temp.inBxh != null && data.temp.inBxh != ''){
                                                    str += "<td width='12%'align='center'>"+data.temp.inB+"\/"+data.temp.inBxh+"</td>";
                                                }else {
                                                    str += "<td width='12%'align='center'>"+data.temp.inB+"</td>";
                                                }
                                            }else {
                                                str += "<td width='12%'align='center'>"+data.temp.inB+"</td>";
                                            }
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.temp.inB+"</td>";
                                        }
                                    }else if(data.temp.inB == "EEEEEEEE"){
                                        str += "<td width='12%'align='center'>未校准</td>";
                                    }else if(data.temp.inB == "DDDDDDDD"){
                                        str += "<td width='12%'align='center'>有干扰</td>";
                                    }
                                    if(data.temp.inC != "EEEEEEEE" && data.temp.inC != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.temp.inCxh != null && data.temp.inCxh != ''){
                                                    str += "<td width='12%'align='center'>"+data.temp.inC+"\/"+data.temp.inCxh+"</td>";
                                                }else {
                                                    str += "<td width='12%'align='center'>"+data.temp.inC+"</td>";
                                                }
                                            }else {
                                                str += "<td width='12%'align='center'>"+data.temp.inC+"</td>";
                                            }
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.temp.inC+"</td>";
                                        }
                                    }else if(data.temp.inC == "EEEEEEEE"){
                                        str += "<td width='12%'align='center'>未校准</td>";
                                    }else if(data.temp.inC == "DDDDDDDD"){
                                        str += "<td width='12%'align='center'>有干扰</td>";
                                    }

                                    if(data.temp.outA != "EEEEEEEE" && data.temp.outA != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.temp.outAxh != null && data.temp.outAxh != ''){
                                                    str += "<td width='12%'align='center'>"+data.temp.outA+"\/"+data.temp.outAxh+"</td>";
                                                }else {
                                                    str += "<td width='12%'align='center'>"+data.temp.outA+"</td>";
                                                }
                                            }else {
                                                str += "<td width='12%'align='center'>"+data.temp.outA+"</td>";
                                            }
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.temp.outA+"</td>";
                                        }
                                    }else if(data.temp.outA == "EEEEEEEE"){
                                        str += "<td width='12%'align='center'>未校准</td>";
                                    }else if(data.temp.outA == "DDDDDDDD"){
                                        str += "<td width='12%'align='center'>有干扰</td>";
                                    }
                                    if(data.temp.outB != "EEEEEEEE" && data.temp.outB != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.temp.outBxh != null && data.temp.outBxh != ''){
                                                    str += "<td width='12%'align='center'>"+data.temp.outB+"\/"+data.temp.outBxh+"</td>";
                                                }else {
                                                    str += "<td width='12%'align='center'>"+data.temp.outB+"</td>";
                                                }
                                            }else {
                                                str += "<td width='12%'align='center'>"+data.temp.outB+"</td>";
                                            }
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.temp.outB+"</td>";
                                        }

                                    }else if(data.temp.outB == "EEEEEEEE"){
                                        str += "<td width='12%'align='center'>未校准</td>";
                                    }else if(data.temp.outB == "DDDDDDDD"){
                                        str += "<td width='12%'align='center'>有干扰</td>";
                                    }
                                    if(data.temp.outC != "EEEEEEEE" && data.temp.outC != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.temp.outCxh != null && data.temp.outCxh != ''){
                                                    str += "<td width='12%'align='center'>"+data.temp.outC+"\/"+data.temp.outCxh+"</td>";
                                                }else {
                                                    str += "<td width='12%'align='center'>"+data.temp.outC+"</td>";
                                                }
                                            }else {
                                                str += "<td width='12%'align='center'>"+data.temp.outC+"</td>";
                                            }
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.temp.outC+"</td>";
                                        }

                                    }else if(data.temp.outC == "EEEEEEEE"){
                                        str += "<td width='12%'align='center'>未校准</td>";
                                    }else if(data.temp.outC == "DDDDDDDD"){
                                        str += "<td width='12%'align='center'>有干扰</td>";
                                    }
                                    if(userType != null && userType != ''){
                                        if(userType == 1 || userType == '1'){
                                            if(data.temp.antSignal != null && data.temp.antSignal != ''){
                                                if(data.temp.type == 1 || data.temp.type == '1'){
                                                    str += "<td width='14%'align='center'>正常"+"("+data.temp.antSignal+"Dbm)"+"</td>";
                                                }
                                                if(data.temp.type == 2 || data.temp.type == '2'){
                                                    str += "<td width='14%'align='center'>报警"+"("+data.temp.antSignal+"Dbm)"+"</td>";
                                                }
                                                if(data.temp.type == 3 || data.temp.type == '3'){
                                                    str += "<td width='14%'align='center'>异常"+"("+data.temp.antSignal+"Dbm)"+"</td>";
                                                }
                                            }else {
                                                if(data.temp.type == 1 || data.temp.type == '1'){
                                                    str += "<td width='14%'align='center'>正常</td>";
                                                }
                                                if(data.temp.type == 2 || data.temp.type == '2'){
                                                    str += "<td width='14%'align='center'>报警</td>";
                                                }
                                                if(data.temp.type == 3 || data.temp.type == '3'){
                                                    str += "<td width='14%'align='center'>异常</td>";
                                                }
                                            }
                                        }else {
                                            if(data.temp.type == 1 || data.temp.type == '1'){
                                                str += "<td width='14%'align='center'>正常</td>";
                                            }
                                            if(data.temp.type == 2 || data.temp.type == '2'){
                                                str += "<td width='14%'align='center'>报警</td>";
                                            }
                                            if(data.temp.type == 3 || data.temp.type == '3'){
                                                str += "<td width='14%'align='center'>异常</td>";
                                            }
                                        }
                                    }else {
                                        if(data.temp.type == 1 || data.temp.type == '1'){
                                            str += "<td width='14%'align='center'>正常</td>";
                                        }
                                        if(data.temp.type == 2 || data.temp.type == '2'){
                                            str += "<td width='14%'align='center'>报警</td>";
                                        }
                                        if(data.temp.type == 3 || data.temp.type == '3'){
                                            str += "<td width='14%'align='center'>异常</td>";
                                        }
                                    }
                                    str += "</tr>";
                                    $("#time").val(data.temp.time);
                                    sj.push(data.temp.time);
                                    inA.push(data.temp.inA);
                                    outA.push(data.temp.outA);
                                    inB.push(data.temp.inB);
                                    outB.push(data.temp.outB);
                                    inC.push(data.temp.inC);
                                    outC.push(data.temp.outC);
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
                                            data:['A相入','A相出','B相入','B相出','C相入','C相出']
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
                                                name: 'A相出',
                                                type: 'line',
                                                smooth: true,
                                                data: outA
                                            },
                                            {
                                                name: 'B相入',
                                                type: 'line',
                                                smooth: true,
                                                data: inB
                                            },
                                            {
                                                name: 'B相出',
                                                type: 'line',
                                                smooth: true,
                                                data: outB
                                            },
                                            {
                                                name: 'C相入',
                                                type: 'line',
                                                smooth: true,
                                                data: inC
                                            },
                                            {
                                                name: 'C相出',
                                                type: 'line',
                                                smooth: true,
                                                data: outC
                                            }]
                                    });

                        }else if(dataTemp == "usernull"){
                            location.href="${path}/index.jsp"
						}
                        ss();
                    }, error: function (data, status, e)//服务器响应失败处理函数
                    {
                        location.reload();
                    }
                });
            }, 2000);
		}

}

    var sj2 =[];
    var inA2 =[];
    var outA2 =[];
    var inB2 =[];
    var outB2 =[];
    var inC2 =[];
    var outC2 =[];
    function selectHis(sort) {
        layer.load(1);
        setTimeout(function(){
            sj2 =[];
            inA2 =[];
            outA2 =[];
            inB2 =[];
            outB2 =[];
            inC2 =[];
            outC2 =[];
            var deviceNumber = $("#deviceNumber").val();
            var startTime = $("#test1").val();//开始时间
            var endTime = $("#test2").val();//结束时间
            var cheke = $("#cheke").val();//结束时间
            var userType = $("#userType").val();//结束时间
            var numberDevices = $("#numberDevices").val();//有几个传感器
            if(startTime == null || startTime == ''){
                layer.msg('请输入开始时间');
                layer.closeAll('loading');
                return false;
            }
            if(endTime == null || endTime == ''){
                layer.msg('请输入结束时间');
                layer.closeAll('loading');
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
                    if(data.tempList == "false"){
                        $("#LsContent").html('');
                        layer.msg('当前时间没有数据加载');
                        layer.closeAll('loading');
                        return false;
                    }
                    if(cheke != 0 || cheke != '0'){
                        sj2.splice(1,sj2.length);
                        inA2.splice(1,inA2.length);
                        outA2.splice(1,outA2.length);
                        inB2.splice(1,inB2.length);
                        outB2.splice(1,outB2.length);
                        inC2.splice(1,inC2.length);
                        outC2.splice(1,outC2.length);
                    }

                    if(data.tempList != null && data.tempList != ''){

                        var str = "";
                        for ( var i = 0; i < data.tempList.length; i++) {
                            str += "<tr id='qc'>";
                            str += "<td width='14%'align='center'>"+data.tempList[i].time+"</td>";
                            if(data.tempList[i].inA != "EEEEEEEE" && data.tempList[i].inA != "DDDDDDDD"){
                                if(userType != null && userType != ''){
                                    if(userType == 1 || userType == '1'){
                                        if(data.tempList[i].inAxh != null && data.tempList[i].inAxh != ''){
                                            str += "<td width='12%'align='center'>"+data.tempList[i].inA+"\/"+data.tempList[i].inAxh+"</td>";
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.tempList[i].inA+"</td>";
                                        }
									}else {
                                            str += "<td width='12%'align='center'>"+data.tempList[i].inA+"</td>";
									}

                                }else {
                                    str += "<td width='12%'align='center'>"+data.tempList[i].inA+"</td>";
                                }
                            }else if(data.tempList[i].inA == "EEEEEEEE"){
                                str += "<td width='12%'align='center'>未校准</td>";
                            }else if(data.tempList[i].inA == "DDDDDDDD"){
                                str += "<td width='12%'align='center'>有干扰</td>";
                            }
                            if(data.tempList[i].inB != "EEEEEEEE" && data.tempList[i].inB != "DDDDDDDD"){
                                if(userType != null && userType != ''){
                                    if(userType == 1 || userType == '1'){
                                        if(data.tempList[i].inBxh != null && data.tempList[i].inBxh != ''){
                                            str += "<td width='12%'align='center'>"+data.tempList[i].inB+"\/"+data.tempList[i].inBxh+"</td>";
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.tempList[i].inB+"</td>";
                                        }
									}else {
                                        str += "<td width='12%'align='center'>"+data.tempList[i].inB+"</td>";
                                    }
                                }else {
                                    str += "<td width='12%'align='center'>"+data.tempList[i].inB+"</td>";
                                }

                            }else if(data.tempList[i].inB == "EEEEEEEE"){
                                str += "<td width='12%'align='center'>未校准</td>";
                            }else if(data.tempList[i].inB == "DDDDDDDD"){
                                str += "<td width='12%'align='center'>有干扰</td>";
                            }
                            if(data.tempList[i].inC != "EEEEEEEE" && data.tempList[i].inC != "DDDDDDDD"){
                                if(userType != null && userType != ''){
                                    if(userType == 1 || userType == '1'){
                                        if(data.tempList[i].inCxh != null && data.tempList[i].inCxh != ''){
                                            str += "<td width='12%'align='center'>"+data.tempList[i].inC+"\/"+data.tempList[i].inCxh+"</td>";
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.tempList[i].inC+"</td>";
                                        }
									}else {
                                        str += "<td width='12%'align='center'>"+data.tempList[i].inC+"</td>";
                                    }
                                }else {
                                    str += "<td width='12%'align='center'>"+data.tempList[i].inC+"</td>";
                                }

                            }else if(data.tempList[i].inC == "EEEEEEEE"){
                                str += "<td width='12%'align='center'>未校准</td>";
                            }else if(data.tempList[i].inC == "DDDDDDDD"){
                                str += "<td width='12%'align='center'>有干扰</td>";
                            }
                            if(data.tempList[i].outA != "EEEEEEEE" && data.tempList[i].outA != "DDDDDDDD"){
                                if(userType != null && userType != ''){
                                    if(userType == 1 || userType == '1'){
                                        if(data.tempList[i].outAxh != null && data.tempList[i].outAxh != ''){
                                            str += "<td width='12%'align='center'>"+data.tempList[i].outA+"\/"+data.tempList[i].outAxh+"</td>";
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.tempList[i].outA+"</td>";
                                        }
									}else {
                                        str += "<td width='12%'align='center'>"+data.tempList[i].outA+"</td>";
                                    }
                                }else {
                                    str += "<td width='12%'align='center'>"+data.tempList[i].outA+"</td>";;
                                }
                            }else if(data.tempList[i].outA == "EEEEEEEE"){
                                str += "<td width='12%'align='center'>未校准</td>";
                            }else if(data.tempList[i].outA == "DDDDDDDD"){
                                str += "<td width='12%'align='center'>有干扰</td>";
                            }
                            if(data.tempList[i].outB != "EEEEEEEE" && data.tempList[i].outB != "DDDDDDDD"){
                                if(userType != null && userType != ''){
                                    if(userType == 1 || userType == '1'){
                                        if(data.tempList[i].outBxh != null && data.tempList[i].outBxh != ''){
                                            str += "<td width='12%'align='center'>"+data.tempList[i].outB+"\/"+data.tempList[i].outBxh+"</td>";
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.tempList[i].outB+"</td>";
                                        }
									}else {
                                        str += "<td width='12%'align='center'>"+data.tempList[i].outB+"</td>";
                                    }
                                }else {
                                    str += "<td width='12%'align='center'>"+data.tempList[i].outB+"</td>";
                                }
                            }else if(data.tempList[i].outB == "EEEEEEEE"){
                                str += "<td width='12%'align='center'>未校准</td>";
                            }else if(data.tempList[i].outB == "DDDDDDDD"){
                                str += "<td width='12%'align='center'>有干扰</td>";
                            }

                            if(data.tempList[i].outC != "EEEEEEEE" && data.tempList[i].outC != "DDDDDDDD"){
                                if(userType != null && userType != ''){
                                    if(userType == 1 || userType == '1'){
                                        if(data.tempList[i].outCxh != null && data.tempList[i].outCxh != ''){
                                            str += "<td width='12%'align='center'>"+data.tempList[i].outC+"\/"+data.tempList[i].outCxh+"</td>";
                                        }else {
                                            str += "<td width='12%'align='center'>"+data.tempList[i].outC+"</td>";
                                        }
									}else {
                                        str += "<td width='12%'align='center'>"+data.tempList[i].outC+"</td>";
                                    }
                                }else {
                                    str += "<td width='12%'align='center'>"+data.tempList[i].outC+"</td>";;
                                }
                            }else if(data.tempList[i].outC == "EEEEEEEE"){
                                str += "<td width='12%'align='center'>未校准</td>";
                            }else if(data.tempList[i].outC == "DDDDDDDD"){
                                str += "<td width='12%'align='center'>有干扰</td>";
                            }
                            if(userType != null && userType != ''){
                                if(userType == 1 || userType == '1'){
                                    if(data.tempList[i].antSignal != null && data.tempList[i].antSignal != ''){
                                        if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                            str += "<td width='14%'align='center'>正常"+"("+data.tempList[i].antSignal+"Dbm)"+"</td>";
                                        }
                                        if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                            str += "<td width='14%'align='center'>报警"+"("+data.tempList[i].antSignal+"Dbm)"+"</td>";
                                        }
                                        if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                            str += "<td width='14%'align='center'>异常"+"("+data.tempList[i].antSignal+"Dbm)"+"</td>";
                                        }
                                    }else {
                                        if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                            str += "<td width='14%'align='center'>正常</td>";
                                        }
                                        if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                            str += "<td width='14%'align='center'>报警</td>";
                                        }
                                        if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                            str += "<td width='14%'align='center'>异常</td>";
                                        }
									}
                                }else {
                                    if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                        str += "<td width='14%'align='center'>正常</td>";
                                    }
                                    if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                        str += "<td width='14%'align='center'>报警</td>";
                                    }
                                    if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                        str += "<td width='14%'align='center'>异常</td>";
                                    }
                                }
                            }else {
                                if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                    str += "<td width='14%'align='center'>正常</td>";
                                }
                                if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                    str += "<td width='14%'align='center'>报警</td>";
                                }
                                if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                    str += "<td width='14%'align='center'>异常</td>";
                                }
                            }
                            str += "</tr>";
                            sj2.push(data.tempList[i].time);
                            inA2.push(data.tempList[i].inA);
                            outA2.push(data.tempList[i].outA);
                            inB2.push(data.tempList[i].inB);
                            outB2.push(data.tempList[i].outB);
                            inC2.push(data.tempList[i].inC);
                            outC2.push(data.tempList[i].outC);
                        }
                        if(cheke == 0 || cheke == '0'){
                            $("#cheke").val(1);
                        }else{
                            $("#LsContent").html('');
                        }
                        $("#LsContent").html('');
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
                                data:['A相入','A相出','B相入','B相出','C相入','C相出']
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
                                    name: 'A相出',
                                    type: 'line',
                                    smooth: true,
                                    data: outA2
                                },
                                {
                                    name: 'B相入',
                                    type: 'line',
                                    smooth: true,
                                    data: inB2
                                },
                                {
                                    name: 'B相出',
                                    type: 'line',
                                    smooth: true,
                                    data: outB2
                                },
                                {
                                    name: 'C相入',
                                    type: 'line',
                                    smooth: true,
                                    data: inC2
                                },
                                {
                                    name: 'C相出',
                                    type: 'line',
                                    smooth: true,
                                    data: outC2
                                }]
                        });
                    }else{
                        layer.msg('当前时间没有数据加载');
                        layer.closeAll('loading');
                        $("#LsContent").html('');
                    }
                    layer.closeAll('loading');
                }
            });
		}, 2000);

    }
    function selectxz(sort) {
        var deviceNumber = $("#deviceNumber").val();
        var startTime = $("#test1").val();//开始时间
        var endTime = $("#test2").val();//结束时间
        var numberDevices = $("#numberDevices").val();//有几个传感器
        location.href="${path}/webTemperatureDetection/daochu?deviceNumber="+deviceNumber+"&startTime="+startTime+"&endTime="+endTime+"&numberDevices="+numberDevices+"&sort="+sort;
    }

	function lishi() {
        sj2 =[];
         inA2 =[];
         outA2 =[];
         inB2 =[];
         outB2 =[];
         inC2 =[];
         outC2 =[];
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
					'<td width="100%">'+
					'<div class="layui-inline">'+
				  	'<input type="hidden" value="0" id = "cishu">'+
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
						'<input type="button" id = "selectxz"  value="下载" onclick="selectxz(0);"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
						'<input id="file" name="file" type="file"  value="批量添加" onchange="inserAll(0);"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;" />'+
					'</td>'+
				'</tr>'+
			'</table>'+
			'<table class="table table-bordered table-hover table-striped" style="font-family: 微软雅黑;width:100%;height:200px;position:relative;" id = "wd">'+
					'<thead>'+
							'<tr>'+
								'<th width="14%">温度时间</th>'+
								'<th width="12%">A相入温度</th>'+
			     				'<th width="12%">B相入温度</th>'+
              				    '<th width="12%">C相入温度</th>'+
              					'<th width="12%">A相出温度</th>'+
              					'<th width="12%">B相出温度</th>'+
								'<th width="12%">C相出温度</th>'+
								'<th width="14%">状态</th>'+
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
		  '<div id="main2" style="width:1000px;height:400px;top:10px;"></div>'
		});
		 var  usertype = $("#userType").val();
		 if(usertype != null && usertype != ''){
		     if(usertype != '1' && usertype != 1){
				$("#selectxz").hide();
				$("#file").hide();
			 }
		 }
		ZX2();
		time();
	}



	function realtime() {
         sj =[];
         inA =[];
         outA =[];
         inB =[];
         outB =[];
         inC =[];
         outC =[];
        var userType = $("#userType").val();
        console.log(userType);
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
				  '<input type="button"   value="设置相位温度" id="xwwd" onclick="setupWd();"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
              '</td>'+
              '</tr>'+
              '</table>'+
			'<table class="table table-bordered table-hover table-striped" style="font-family: 微软雅黑;width:100%;height:200px;position:relative;" id = "wd">'+
					'<thead>'+
							'<tr>'+
								'<th width="14%">温度时间</th>'+
								'<th width="12%">A相入温度</th>'+
								'<th width="12%">B相入温度</th>'+
								'<th width="12%">C相入温度</th>'+
								'<th width="12%">A相出温度</th>'+
								'<th width="12%">B相出温度</th>'+
								'<th width="12%">C相出温度</th>'+
								'<th width="14%">状态</th>'+
							'</tr>'+
					'</thead>'+
				'</table>'+
		  '</div>'+
		  '<div style="width:100%;height:200px;overflow-y:scroll; border-bottom:1px solid #dddddd"  id="swiper">'+
		  '<table id="Template_listZ" class="footable foottable_tab" width="100%">'+
		  '<tbody id="tContent" style="width:100%;">'+
		  '</tbody>'+
		  '</table>'+
		  '</div>'+
		  '<div id="main" style="width: 1000px;height:400px;top:10px;"></div>'
		});
        if(userType !=null && userType != '' && userType != 1 && userType != "1"){
            $("#xwwd").hide();
        }
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

	function retur(){
		var substationId = $("#substationId").val();
		var substationImage = $("#substationImage").val();
		var currentPageSb = $("#currentPageSb").val();
		if(substationId != null && substationId != ''){
            location.href="${path}/webEquipment/selectEquipment?substationId="+substationId+"&substationImage="+encodeURI(encodeURI(substationImage));
		}else {
		    location.href="${path}/webSkEquipment/selectEquipment?currentPage="+currentPageSb;
		}
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
  		        data:['A相入','A相出','B相入','B相出','C相入','C相出']
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
  		        name: 'A相出',
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
  		    	 name: 'B相出',
  			     type: 'line',
  			     smooth: true,
  			     data:[0]
  		    },
  		    {
		        name: 'C相入',
		        type: 'line',
		        smooth: true,
		        data: [0]
		    },
		    {
		    	 name: 'C相出',
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
                data:['A相入','A相出','B相入','B相出','C相入','C相出']
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
                    name: 'A相出',
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
                    name: 'B相出',
                    type: 'line',
                    smooth: true,
                    data:[0]
                },
                {
                    name: 'C相入',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'C相出',
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

				}else if(data == "false"){
                    layer.msg('命令发送失败');
				}else {
                    layer.msg('命令正在执行中');
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
            area: ['60%', '40%'],
            skin: 'yourclass',
            btn: ['刷新', '取消'],//只是为了演示
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
            '<table class="table table-bordered" style="">'+
            '<tr>'+
            '<td width="50%">'+
            '<input type="button" id = "select"  value="撤销命令" onclick="cx();"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
            '</td>'+
            '</tr>'+
            '</table>'+
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
        zxqkma();
    }
    function cx() {
        var deviceNumber = $("#deviceNumber").val();
        layer.confirm('您确定要撤销发送命令吗？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                url:'${path}/webZnTest/scduixiang',
                data:{
                    'deviceNumber' : deviceNumber
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if(data=="true"){
                        layer.msg("清除成功")
                        location.reload();
                    }else if(data == "false"){
                        alert("删除失败");
                    }
                }
            });
        }, function(){
            layer.closeAll();
        });

    }

	function zxqkma() {
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

    function setupWd() {
        var deviceNumber = $("#deviceNumber").val();
        layer.open({
            type: 1,
            title: false,
            closeBtn: 1,
            shadeClose: false,
            area: ['50%', '40%'],
            skin: 'yourclass',
            btn: ['设置','取消'] //只是为了演示
            ,yes: function(){
                layer.load(1);
                var inA = $("#inAsfs").val();
                var inB = $("#inBsfs").val();
                var inC = $("#inCsfs").val();
                var outA = $("#outAsfs").val();
                var outB = $("#outBsfs").val();
                var outC = $("#outCsfs").val();
                var inAcs = $("#inAcs").val();
                var inBcs = $("#inBcs").val();
                var inCcs = $("#inCcs").val();
                var outAcs = $("#outAcs").val();
                var outBcs = $("#outBcs").val();
                var outCcs = $("#outCcs").val();
                <%--$.ajax({--%>
                    <%--url:'${path}/webCalculationWd/insert',--%>
                    <%--data:{--%>
                        <%--'inA' : inA,--%>
                        <%--'inB' : inB,--%>
                        <%--'inC' : inC,--%>
                        <%--'outA' : outA,--%>
                        <%--'outB' : outB,--%>
                        <%--'outC' : outC,--%>
                        <%--'inAcs' : inAcs,--%>
                        <%--'inBcs' : inBcs,--%>
                        <%--'inCcs' : inCcs,--%>
                        <%--'outAcs' : outAcs,--%>
                        <%--'outBcs' : outBcs,--%>
                        <%--'outCcs' : outCcs,--%>
                        <%--'deviceNumber' : deviceNumber,--%>
                    <%--},--%>
                    <%--type:'post',--%>
                    <%--cache:false,--%>
                    <%--dataType:'json',--%>
                    <%--traditional:true,--%>
                    <%--success:function(data) {--%>
                        <%--if(data != 'false'){--%>
                            algorithm();
                //             layer.msg("设置成功")
                            layer.closeAll('loading');
                //         }else {
                //             layer.msg("设置失败")
                //             layer.closeAll('loading');
                //         }
                //     }
                // });
            }
            ,btn2: function(){

            },
            content: '\t\t\t\t<div class="title_right">' +
            '<input type="hidden" value="0" id= "csu">'+
            '<strong>算法设置</strong>' +
            '</div>\n' +
            '<div style="width: 500px; margin: auto;"></div>\n' +
            '<table class="table table-bordered" style="font-family: 微软雅黑;">' +
            '<tr height="50px;">\n' +
            '<td width="20%" style="font-size: 15px; text-align: center;">' +
            'A入：</td>' +
            '<td width="80%" id="inAsf">' +
			'<select id="inAsfs" style="height: 32px; width: 180px;">' +
            '<option value="0">A入</option>' +
            '<option value="1">B入-随机数</option>' +
            '<option value="2">C入-随机数</option>' +
            '<option value="3">A出+(B入-B出)+随机数</option>' +
            '<option value="4">A出+(C入-C出)+随机数</option>' +
            '</select>' +
            '</td>' +
            '<td width="30%">' +
			'<input type="text" value="" style="height: 25px;" id="inAcs">'+
			'</td>' +
            '</tr>' +
            '<tr height="50px;">' +
            '<td width="20%" style="font-size: 15px; text-align: center;">' +
            'B入：</td>\n' +
            '<td width="80%" id="inBsf">' +
			'<select id="inBsfs" style="height: 32px; width: 180px;">' +
            '<option value="0">B入</option>' +
            '<option value="1">A入-随机数</option>' +
            '<option value="2">C入-随机数</option>' +
            '<option value="3">B出+(A入-A出)+随机数</option>' +
            '<option value="4">B出+(C入-C出)+随机数</option>' +
            '</select>' +
            '</td>' +
            '<td width="30%">' +
            '<input type="text" value="" style="height: 25px;" id="inBcs">'+
			'</td>' +
            '</tr>' +
            '<tr height="50px;">' +
            '<td width="20%" style="font-size: 15px; text-align: center;">' +
            'C入：</td>' +
            '<td width="80%" id = "inCsf">' +
            '<select id="inCsfs" style="height: 32px; width: 180px;">' +
            '<option value="0">C入</option>' +
            '<option value="1">A入-随机数</option>' +
            '<option value="2">B入-随机数</option>' +
            '<option value="3">C出+(B入-B出)+随机数</option>' +
            '<option value="4">C出+(A入-A出)+随机数</option>' +
            '</select>' +
            '</td>' +
            '<td width="30%">' +
            '<input type="text" value="" style="height: 25px;" id="inCcs">'+
            '</td>' +
            '</tr>' +
            '<tr height="50px;">' +
            '<td width="20%" style="font-size: 15px; text-align: center;">' +
            'A出：</td>' +
            '<td width="80%" id="outAsf">' +
            '<select id="outAsfs" style="height: 32px; width: 180px;">' +
            '<option value="0">A出</option>' +
            '<option value="1">B出-随机数</option>' +
            '<option value="2">C出-随机数</option>' +
            '<option value="3">A入-(B入-B出)-随机数</option>' +
            '<option value="4">A入-(C入-C出)-随机数</option>' +
            '</select>' +
            '</td>' +
            '<td width="30%">' +
            '<input type="text" value="" style="height: 25px;" id="outAcs">'+
			'</td>' +
            '</tr>' +
            '<tr height="50px;">\n' +
            '<td width="20%" style="font-size: 15px; text-align: center;">' +
            'B出：</td>' +
            '<td width="80%" id="outBsf">' +
            '<select id="outBsfs" style="height: 32px; width: 180px;">' +
            '<option value="0">B出</option>' +
            '<option value="1">A出-随机数</option>' +
            '<option value="2">C出-随机数</option>' +
            '<option value="3">B入-(A入-A出)-随机数</option>' +
            '<option value="4">B入-(C入-C出)-随机数</option>' +
            '</select>' +
            '</td>' +
            '<td width="30%">' +
            '<input type="text" value="" style="height: 25px;" id="outBcs">'+
			'</td>' +
            '</tr>' +
            '<tr height="50px;">' +
            '<td width="20%" style="font-size: 15px; text-align: center;">' +
            'C出：</td>' +
            '<td width="30%" id="outCsf">' +
            '<select id="outCsfs" style="height: 32px; width: 180px;">' +
            '<option value="0">C出</option>' +
            '<option value="1">A出-随机数</option>' +
            '<option value="2">B出-随机数</option>' +
            '<option value="3">C入-(A入-A出)-随机数</option>' +
            '<option value="4">C入-(B入-B出)-随机数</option>' +
            '</select>' +
            '</td>' +
            '<td width="30%">' +
            '<input type="text" value="" style="height: 25px;" id="outCcs">'+
            '</td>' +
            '</tr>' +
            '</table>'
        });
        $.ajax({
            url:'${path}/webCalculationWd/select',
            data:{
                'deviceNumber' : deviceNumber,
            },
            type:'post',
            cache:false,
            dataType:'json',
            traditional:true,
            success:function(data) {
                //点击按钮，让第二个选项选中
				if(data != null && data != ''){
                     var inafs = data.inA;
                     var inbfs = data.inB;
                     var incfs = data.inC;
                     var outafs = data.outA;
                     var outbfs = data.outB;
                     var outcfs = data.outC;
                     if(inafs != null && inafs != ''){
                         var options = document.getElementById('inAsfs').children;
                         options[inafs].selected=true;
                     }
                     if(inbfs != null && inbfs != ''){
                         var options = document.getElementById('inBsfs').children;
                         options[inbfs].selected=true;
                     }
                     if(incfs != null && incfs != ''){
                         var options = document.getElementById('inCsfs').children;
                         options[incfs].selected=true;
                     }
                     if(outafs != null && outafs != ''){
                         var options = document.getElementById('outAsfs').children;
                         options[outafs].selected=true;
                     }
                     if(outbfs != null && outbfs != ''){
                         var options = document.getElementById('outBsfs').children;
                         options[outbfs].selected=true;
                     }
                     if(outcfs != null && outcfs != ''){
                         var options = document.getElementById('outCsfs').children;
                         options[outcfs].selected=true;
                     }
                    $("#inAcs").val(data.inAcs);
                    $("#inBcs").val(data.inBcs);
                    $("#inCcs").val(data.inCcs);
                    $("#outAcs").val(data.outAcs);
                    $("#outBcs").val(data.outBcs);
                    $("#outCcs").val(data.outCcs);
				}
            }
        });

    }

    function algorithm() {
        var inA = $("#inAsfs").val();
        console.log(inA);
        var inB = $("#inBsfs").val();
        var inC = $("#inCsfs").val();
        var outA = $("#outAsfs").val();
        var outB = $("#outBsfs").val();
        var outC = $("#outCsfs").val();
        var inAcs = $("#inAcs").val();
        var inBcs = $("#inBcs").val();
        var inCcs = $("#inCcs").val();
        var outAcs = $("#outAcs").val();
        var outBcs = $("#outBcs").val();
        var outCcs = $("#outCcs").val();
        var deviceNumber = $("#deviceNumber").val();
        var deviceType = $("#deviceType").val();
        var deviceSbId = $("#deviceSbId").val();
        var isRoot = $("#isRoot").val();
        $.ajax({
            url:'${path}/webCommand/algorithm',
            data:{
                'deviceType' : deviceType,
                'isRoot' : isRoot,
                'deviceNumber' : deviceNumber,
                'deviceSbId' : deviceSbId,
                'inA' : inA,
                'inB' : inB,
                'inC' : inC,
                'outA' : outA,
                'outB' : outB,
                'outC' : outC,
                'inAcs' : inAcs,
                'inBcs' : inBcs,
                'inCcs' : inCcs,
                'outAcs' : outAcs,
                'outBcs' : outBcs,
                'outCcs' : outCcs,
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if(data == "true"){
                    layer.msg('命令发送成功');
                }else if(data == "false"){
                    layer.msg('命令发送失败');
                }else {
                    layer.msg('命令正在执行中');
                }
            }
        });
    }

    function inserAll() {
        ajaxFileUploadForType();
    }
    function ajaxFileUploadForType(){
        layer.load(1);
        var deviceNumber = $("#deviceNumber").val();

        if($('input[type="file"]').val()!=""){
            var extend=$('input[type="file"]').val().substr($('input[type="file"]').val().lastIndexOf(".")+1);
            if("xls|xlsx".indexOf(extend+"|")==-1){
                flagPic=false;
                $.messager.alert("提示信息","选择的文件必须是EXCEL文件,请确认！");
            }else{
                $.ajaxFileUpload
                (
                    {
                        url:'${path}/webTemperatureDetection/ImportByTempe?deviceNumber='+deviceNumber, //用于文件上传的服务器端请求地址
                        secureuri: false, //是否需要安全协议，一般设置为false
                        fileElementId: 'file', //文件上传域的ID
                        dataType: 'text', //返回值类型 一般设置为json
                        success: function (responseJSON)  //服务器成功响应处理函数
                        {
                            if(responseJSON.linke == "" || responseJSON.linke == null || responseJSON.linke.length < 0 || responseJSON.czlinke == "" || responseJSON.czlinke == null || responseJSON.czlinke.length < 0){
                                layer.msg("上传成功");
                                location.reload();
                            }else if(responseJSON.linke != "" && responseJSON.linke != null && responseJSON.linke.length < 0){
                                layer.msg("上传失败");
                                layer.closeAll('loading');
                            }

                        },
                        error: function (data, status, e)//服务器响应失败处理函数
                        {
                            layer.closeAll('loading');
                            layer.msg("上传失败请重新上传")

                        }
                    }
                )
            }
        }else{
            layer.closeAll('loading');
            layer.msg("请选择文件在上传")

        }
    }
</script>

</body>

</html>
