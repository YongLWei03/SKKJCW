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
				<input type="hidden" value="${numberDevices }" id= "numberDevices">
				<input type="hidden" value="${isRoot }" id= "isRoot">
				<input type="hidden" value="${deviceSbId }" id= "deviceSbId">
				<input type="hidden" value="${deviceType }" id= "deviceType">
				<input type="hidden" value="0" id = "real">

				<input type="hidden" value="" id = "time">
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
	var sj =['0'];
	var inA =['0']; 
	var outA =['0']; 
	var inB =['0']; 
	var outB =['0']; 
	var inC =['0']; 
	var outC =['0']; 
	var contactA =['0'];
	var contactB =['0'];
	var contactC=['0'];
	var contactD =['0'];
	var contactE=['0'];
	var contactF =['0'];
	function ss(){
        var ljcj = $("#ljcj").val();

        if(ljcj != null && ljcj != ''){
            //此处演示关闭
            setTimeout(function(){
                var deviceNumber = $("#deviceNumber").val();
                var time = $("#time").val();
                var userType = $("#userType").val();
                $("#ljcj").val("1");
                $.ajax({
                    url:'${path}/webTemperatureDetection/realTime',
                    data:{
                        'deviceNumber' : deviceNumber,
                        'ljcj' : ljcj,
                        'time' : time,
                    },
                    type:'post',
                    cache:false,
                    async:false,
                    dataType:'json',
                    success:function(data) {
                        var dataTemp = data.temp;
                        if(dataTemp != "false"){
                            $("#time").val(data.temp.time);
                            var str = "";
                            str += "<tr>";
                            str += "<td width='10%'align='center'>"+data.temp.time+"</td>";
                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.inAxh != null && data.temp.inAxh != ''){
                                        if(data.temp.inA != "EEEEEEEE" && data.temp.inA != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.inA+"\/"+data.temp.inAxh+"</td>";
                                        }else if(data.temp.inA == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.inA == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.inA+"</td>";
									}
								}else {
                                    str += "<td width='7%'align='center'>"+data.temp.inA+"</td>";
								}
							}else {
                                str += "<td width='7%'align='center'>"+data.temp.inA+"</td>";
							}
                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.inBxh != null && data.temp.inBxh != ''){
                                        if(data.temp.inB != "EEEEEEEE" && data.temp.inB != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.inB+"\/"+data.temp.inBxh+"</td>";
                                        }else if(data.temp.inB == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.inB == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.inB+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.inB+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.inB+"</td>";
                            }

                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.inCxh != null && data.temp.inCxh != ''){
                                        if(data.temp.inC != "EEEEEEEE" && data.temp.inC != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.inC+"\/"+data.temp.inCxh+"</td>";
                                        }else if(data.temp.inC == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.inC == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.inC+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.inC+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.inC+"</td>";
                            }
                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.outAxh != null && data.temp.outAxh != ''){
                                        if(data.temp.outA != "EEEEEEEE" && data.temp.outA != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.outA+"\/"+data.temp.outAxh+"</td>";
                                        }else if(data.temp.outA == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.outA == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.outA+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.outA+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.outA+"</td>";
                            }

                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.outBxh != null && data.temp.outBxh != ''){
                                        if(data.temp.outB != "EEEEEEEE" && data.temp.outB != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.outB+"\/"+data.temp.outBxh+"</td>";
                                        }else if(data.temp.outB == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.outB == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.outB+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.outB+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.outB+"</td>";
                            }

                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.outCxh != null && data.temp.outCxh != ''){
                                        if(data.temp.outC != "EEEEEEEE" && data.temp.outC != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.outC+"\/"+data.temp.outCxh+"</td>";
                                        }else if(data.temp.outC == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.outC == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.outC+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.outC+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.outC+"</td>";
                            }

                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.contactAxh != null && data.temp.contactAxh != ''){
                                        if(data.temp.contactA != "EEEEEEEE" && data.temp.contactA != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.contactA+"\/"+data.temp.contactAxh+"</td>";
                                        }else if(data.temp.contactA == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.contactA == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.contactA+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.contactA+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.contactA+"</td>";
                            }

                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.contactBxh != null && data.temp.contactBxh != ''){
                                        if(data.temp.contactB != "EEEEEEEE" && data.temp.contactB != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.contactB+"\/"+data.temp.contactBxh+"</td>";
                                        }else if(data.temp.contactB == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.contactB == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.contactB+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.contactB+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.contactB+"</td>";
                            }
                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.contactCxh != null && data.temp.contactCxh != ''){
                                        if(data.temp.contactC != "EEEEEEEE" && data.temp.contactC != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.contactC+"\/"+data.temp.contactCxh+"</td>";
                                        }else if(data.temp.contactC == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.contactC == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.contactC+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.contactC+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.contactC+"</td>";
                            }
                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.contactDxh != null && data.temp.contactDxh != ''){
                                        if(data.temp.contactD != "EEEEEEEE" && data.temp.contactD != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.contactD+"\/"+data.temp.contactDxh+"</td>";
                                        }else if(data.temp.contactD == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.contactD == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.contactD+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.contactD+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.contactD+"</td>";
                            }
                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.contactExh != null && data.temp.contactExh != ''){
                                        if(data.temp.contactE != "EEEEEEEE" && data.temp.contactE != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.contactE+"\/"+data.temp.contactExh+"</td>";
                                        }else if(data.temp.contactE == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.contactE == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.contactE+"</td>";
									}
                                }else {
                                      str += "<td width='7%'align='center'>"+data.temp.contactE+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.contactE+"</td>";
                            }


                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.contactFxh != null && data.temp.contactFxh != ''){
                                        if(data.temp.contactF != "EEEEEEEE" && data.temp.contactF != "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>"+data.temp.contactF+"\/"+data.temp.contactFxh+"</td>";
                                        }else if(data.temp.contactF == "EEEEEEEE"){
                                            str += "<td width='7%'align='center'>未校准</td>";
                                        }else if(data.temp.contactF == "DDDDDDDD"){
                                            str += "<td width='7%'align='center'>有干扰</td>";
                                        }
                                    }else {
                                        str += "<td width='7%'align='center'>"+data.temp.contactF+"</td>";
									}
                                }else {
                                    str += "<td width='7%'align='center'>"+data.temp.contactF+"</td>";
								}
                            }else {
                                str += "<td width='7%'align='center'>"+data.temp.contactF+"</td>";
                            }
                            if(userType != null && userType != ''){
                                if(userType == 1|| userType == '1'){
                                    if(data.temp.antSignal != null && data.temp.antSignal != ''){
                                        console.log(data.temp.antSignal);
                                        if(data.temp.type == 1 || data.temp.type == '1'){
                                            str += "<td width='6%'align='center'>正常"+"("+data.temp.antSignal+")"+"</td>";
                                        }
                                        if(data.temp.type == 2 || data.temp.type == '2'){
                                            str += "<td width='6%'align='center'>报警"+"("+data.temp.antSignal+")"+"</td>";
                                        }
                                        if(data.temp.type == 3 || data.temp.type == '3'){
                                            str += "<td width='6%'align='center'>异常"+"("+data.temp.antSignal+")"+"</td>";
                                        }
                                    }else {
                                        if(data.temp.type == 1 || data.temp.type == '1'){
                                            str += "<td width='6%'align='center'>正常</td>";
                                        }
                                        if(data.temp.type == 2 || data.temp.type == '2'){
                                            str += "<td width='6%'align='center'>报警</td>";
                                        }
                                        if(data.temp.type == 3 || data.temp.type == '3'){
                                            str += "<td width='6%'align='center'>异常</td>";
                                        }
									}
                                }else {
                                    if(data.temp.type == 1 || data.temp.type == '1'){
                                        str += "<td width='6%'align='center'>正常</td>";
                                    }
                                    if(data.temp.type == 2 || data.temp.type == '2'){
                                        str += "<td width='6%'align='center'>报警</td>";
                                    }
                                    if(data.temp.type == 3 || data.temp.type == '3'){
                                        str += "<td width='6%'align='center'>异常</td>";
                                    }
								}
                            }else {
                                if(data.temp.type == 1 || data.temp.type == '1'){
                                    str += "<td width='6%'align='center'>正常</td>";
                                }
                                if(data.temp.type == 2 || data.temp.type == '2'){
                                    str += "<td width='6%'align='center'>报警</td>";
                                }
                                if(data.temp.type == 3 || data.temp.type == '3'){
                                    str += "<td width='6%'align='center'>异常</td>";
                                }
                            }

                            str += "</tr>";
                            sj.push(data.temp.time);
                            inA.push(data.temp.inA);
                            outA.push(data.temp.outA);
                            inB.push(data.temp.inB);
                            outB.push(data.temp.outB);
                            inC.push(data.temp.inC);
                            outC.push(data.temp.outC);
                            contactA.push(data.temp.contactA);
                            contactB.push(data.temp.contactB);
                            contactC.push(data.temp.contactC);
                            contactD.push(data.temp.contactD);
                            contactE.push(data.temp.contactE);
                            contactF.push(data.temp.contactF);

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
                                    data:['A相入','A相出','B相入','B相出','C相入','C相出','A线盘入','A线盘出','B线盘人','B线盘出','C线盘入','C线盘出']
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
                                    },
                                    {
                                        name: 'A线盘入',
                                        type: 'line',
                                        smooth: true,
                                        data: contactA
                                    },
                                    {
                                        name: 'A线盘出',
                                        type: 'line',
                                        smooth: true,
                                        data: contactB
                                    },
                                    {
                                        name: 'B线盘入',
                                        type: 'line',
                                        smooth: true,
                                        data: contactC
                                    },
                                    {
                                        name: 'B线盘出',
                                        type: 'line',
                                        smooth: true,
                                        data: contactD
                                    },
                                    {
                                        name: 'C线盘入',
                                        type: 'line',
                                        smooth: true,
                                        data: contactE
                                    },
                                    {
                                        name: 'C线盘出',
                                        type: 'line',
                                        smooth: true,
                                        data: contactF
                                    }]
                            });
                        }

                    }
                });
                ss();
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
    var contactA2 =[];
    var contactB2 =[];
    var contactC2 =[];
    var contactD2 =[];
    var contactE2 =[];
    var contactF2 =[];
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
        contactA2 =[];
        contactB2 =[];
        contactC2 =[];
        contactD2 =[];
        contactE2 =[];
        contactF2 =[];
        var deviceNumber = $("#deviceNumber").val();
        var startTime = $("#test1").val();//开始时间
        var endTime = $("#test2").val();//结束时间
        var userType = $("#userType").val();//结束时间
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
                if (data.filel != null && data.filel != '') {
                    layer.msg('当前时间段没有温度');
                }
                if (data.tempList != null && data.tempList != '') {
                    var str = "";
                    for (var i = 0; i < data.tempList.length; i++) {
                        if (data.tempList != null && data.tempList != '') {
                            var str = "";
                            for (var i = 0; i < data.tempList.length; i++) {
                                str += "<tr id='qc'>";
                                str += "<td width='10%'align='center'>" + data.tempList[i].time + "</td>";
                                if (data.tempList[i].inA != "EEEEEEEE" && data.tempList[i].inA != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].inAxh != null && data.tempList[i].inAxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].inA + "\/" + data.tempList[i].inAxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].inA + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].inA + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].inA + "</td>";
                                    }
                                } else if (data.tempList[i].inA == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].inA == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].inB != "EEEEEEEE" && data.tempList[i].inB != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].inBxh != null && data.tempList[i].inBxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].inB + "\/" + data.tempList[i].inBxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].inB + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].inB + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].inB + "</td>";
                                    }
                                } else if (data.tempList[i].inB == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].inB == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }

                                if (data.tempList[i].inC != "EEEEEEEE" && data.tempList[i].inC != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].inCxh != null && data.tempList[i].inCxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].inC + "\/" + data.tempList[i].inCxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].inC + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].inC + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].inC + "</td>";
                                    }
                                } else if (data.tempList[i].inC == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].inC == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].outA != "EEEEEEEE" && data.tempList[i].outA != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].outAxh != null && data.tempList[i].outAxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].outA + "\/" + data.tempList[i].outAxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].outA + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].outA + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].outA + "</td>";
                                    }
                                } else if (data.tempList[i].outA == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].outA == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }

                                if (data.tempList[i].outB != "EEEEEEEE" && data.tempList[i].outB != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].outBxh != null && data.tempList[i].outBxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].outB + "\/" + data.tempList[i].outBxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].outB + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].outB + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].outB + "</td>";
                                    }
                                } else if (data.tempList[i].outB == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].outB == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].outC != "EEEEEEEE" && data.tempList[i].outC != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].outCxh != null && data.tempList[i].outCxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].outC + "\/" + data.tempList[i].outCxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].outC + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].outC + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].outC + "</td>";
                                    }
                                } else if (data.tempList[i].outC == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].outC == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].contactA != "EEEEEEEE" && data.tempList[i].contactA != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].contactAxh != null && data.tempList[i].contactAxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactA + "\/" + data.tempList[i].contactAxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactA + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].contactA + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].contactA + "</td>";
                                    }
                                } else if (data.tempList[i].contactA == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].contactA == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].contactB != "EEEEEEEE" && data.tempList[i].contactB != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].contactBxh != null && data.tempList[i].contactBxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactB + "\/" + data.tempList[i].contactBxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactB + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].contactB + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].contactB + "</td>";
                                    }
                                } else if (data.tempList[i].contactB == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].contactB == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].contactC != "EEEEEEEE" && data.tempList[i].contactC != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].contactCxh != null && data.tempList[i].contactCxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactC + "\/" + data.tempList[i].contactBxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactC + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].contactC + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].contactC + "</td>";
                                    }
                                } else if (data.tempList[i].contactC == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].contactC == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].contactD != "EEEEEEEE" && data.tempList[i].contactD != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].contactDxh != null && data.tempList[i].contactDxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactD + "\/" + data.tempList[i].contactBxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactD + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].contactD + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].contactD + "</td>";
                                    }
                                } else if (data.tempList[i].contactD == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].contactD == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].contactE != "EEEEEEEE" && data.tempList[i].contactE != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].contactExh != null && data.tempList[i].contactExh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactE + "\/" + data.tempList[i].contactExh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactE + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].contactE + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].contactE + "</td>";
                                    }
                                } else if (data.tempList[i].contactE == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].contactE == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (data.tempList[i].contactF != "EEEEEEEE" && data.tempList[i].contactF != "DDDDDDDD") {
                                    if (userType != null && userType != '') {
                                        if (userType == 1 || userType == '1') {
                                            if (data.tempList[i].contactFxh != null && data.tempList[i].contactFxh != '') {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactF + "\/" + data.tempList[i].contactFxh + "</td>";
                                            } else {
                                                str += "<td width='7%'align='center'>" + data.tempList[i].contactF + "</td>";
                                            }
                                        } else {
                                            str += "<td width='7%'align='center'>" + data.tempList[i].contactF + "</td>";
                                        }
                                    } else {
                                        str += "<td width='7%'align='center'>" + data.tempList[i].contactF + "</td>";
                                    }
                                } else if (data.tempList[i].contactF == "EEEEEEEE") {
                                    str += "<td width='7%'align='center'>未校准</td>";
                                } else if (data.tempList[i].contactF == "DDDDDDDD") {
                                    str += "<td width='7%'align='center'>有干扰</td>";
                                }
                                if (userType != null && userType != '') {
                                    if (userType == 1 || userType == '1') {
                                        if (data.tempList[i].antSignal != null && data.tempList[i].antSignal != '') {
                                            if (data.tempList[i].type == 1 || data.tempList[i].type == '1') {
                                                str += "<td width='6%'align='center'>正常"+"("+data.tempList[i].antSignal+")"+"</td>";
                                            }
                                            if (data.tempList[i].type == 2 || data.tempList[i].type == '2') {
                                                str += "<td width='6%'align='center'>报警"+"("+data.tempList[i].antSignal+")"+"</td>";
                                            }
                                            if (data.tempList[i].type == 3 || data.tempList[i].type == '3') {
                                                str += "<td width='6%'align='center'>异常"+"("+data.tempList[i].antSignal+")"+"</td>";
                                            }
                                        } else {
                                            if (data.tempList[i].type == 1 || data.tempList[i].type == '1') {
                                                str += "<td width='6%'align='center'>正常</td>";
                                            }
                                            if (data.tempList[i].type == 2 || data.tempList[i].type == '2') {
                                                str += "<td width='6%'align='center'>报警</td>";
                                            }
                                            if (data.tempList[i].type == 3 || data.tempList[i].type == '3') {
                                                str += "<td width='6%'align='center'>异常</td>";
                                            }
                                        }
                                    } else {
                                        if (data.tempList[i].type == 1 || data.tempList[i].type == '1') {
                                            str += "<td width='6%'align='center'>正常</td>";
                                        }
                                        if (data.tempList[i].type == 2 || data.tempList[i].type == '2') {
                                            str += "<td width='6%'align='center'>报警</td>";
                                        }
                                        if (data.tempList[i].type == 3 || data.tempList[i].type == '3') {
                                            str += "<td width='6%'align='center'>异常</td>";
                                        }
                                    }
                                } else {
                                    if (data.tempList[i].type == 1 || data.tempList[i].type == '1') {
                                        str += "<td width='6%'align='center'>正常</td>";
                                    }
                                    if (data.tempList[i].type == 2 || data.tempList[i].type == '2') {
                                        str += "<td width='6%'align='center'>报警</td>";
                                    }
                                    if (data.tempList[i].type == 3 || data.tempList[i].type == '3') {
                                        str += "<td width='6%'align='center'>异常</td>";
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
                                contactA2.push(data.tempList[i].contactA);
                                contactB2.push(data.tempList[i].contactB);
                                contactC2.push(data.tempList[i].contactC);
                                contactD2.push(data.tempList[i].contactD);
                                contactE2.push(data.tempList[i].contactE);
                                contactF2.push(data.tempList[i].contactF);
                            }
                            if (cheke == 0 || cheke == '0') {
                                $("#cheke").val(1);
                            } else {
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
                                    data: ['A相入', 'A相出', 'B相入', 'B相出', 'C相入', 'C相出', 'A线盘入', 'A线盘出', 'B线盘入', 'B线盘出', 'C线盘入', 'C线盘出']
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
                                    },
                                    {
                                        name: 'A线盘入',
                                        type: 'line',
                                        smooth: true,
                                        data: contactA2
                                    },
                                    {
                                        name: 'A线盘出',
                                        type: 'line',
                                        smooth: true,
                                        data: contactB2
                                    },
                                    {
                                        name: 'B线盘入',
                                        type: 'line',
                                        smooth: true,
                                        data: contactC2
                                    },
                                    {
                                        name: 'B线盘出',
                                        type: 'line',
                                        smooth: true,
                                        data: contactD2
                                    },
                                    {
                                        name: 'C线盘入',
                                        type: 'line',
                                        smooth: true,
                                        data: contactE2
                                    },
                                    {
                                        name: 'C线盘出',
                                        type: 'line',
                                        smooth: true,
                                        data: contactF2
                                    }]
                            });
                        } else {
                            layer.msg('当前时间没有数据加载');
                            $("#LsContent").html('');
                        }
                        layer.closeAll('loading');
                    }
                }
            }
        });
        }, 2000);
    }
	
	function lishi() {
         sj2 =[];
         inA2 =[];
         outA2 =[];
         inB2 =[];
         outB2 =[];
         inC2 =[];
         outC2 =[];
         contactA2 =[];
         contactB2 =[];
         contactC2 =[];
         contactD2 =[];
         contactE2 =[];
         contactF2 =[];
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
             		 '<input type="hidden" value="0" id = "cishu">'+
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
				  '<input type="button" id = "selectxz"  value="下载" onclick="selectxz(0);"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;margin-left: 40px;margin-top: -7px;"/>'+
				  '<input id="file" name="file" type="file"  value="批量添加" onchange="inserAll(0);"class="btn btn-primary" style="width: 94px; height: 28px; font-family: 微软雅黑;" />'+
					'</td>'+
				'</tr>'+
			'</table>'+
			'<table class="table table-bordered table-hover table-striped" style="font-family: 微软雅黑;width:100%;height:200px;position:relative;" id = "wd">'+
					'<thead>'+
							'<tr>'+
								'<th width="10%">温度时间</th>'+
								'<th width="7%">A相入温度</th>'+
			     				'<th width="7%">B相入温度</th>'+
              					'<th width="7%">C相入温度</th>'+
              					'<th width="7%">A相出温度</th>'+
              					'<th width="7%">B相出温度</th>'+
								'<th width="7%">C相出温度</th>'+
								'<th width="7%">A线盘入</th>'+
								'<th width="7%">A线盘出</th>'+
			  					'<th width="7%">B线盘入</th>'+
								'<th width="7%">B线盘出</th>'+
								'<th width="7%">C线盘入</th>'+
								'<th width="7%">C线盘出</th>'+
								'<th width="6%">状态</th>'+
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
		  '<div id="main2" style="width: 1080px;height:400px;top:10px;"></div>'
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
         contactA =[];
         contactB =[];
         contactC=[];
         contactD =[];
         contactE=[];
         contactF =[];
		layer.open({
			  type: 1,
			  title: false,
			  closeBtn: 1,
			  shadeClose: false,
			  area: ['70%', '80%'],
			  skin: 'yourclass',
			  content:  '<div style="padding-right:17px;">'+
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
								  '<th width="10%">温度时间</th>'+
								  '<th width="7%">A相入温度</th>'+
								  '<th width="7%">B相入温度</th>'+
								  '<th width="7%">C相入温度</th>'+
								  '<th width="7%">A相出温度</th>'+
								  '<th width="7%">B相出温度</th>'+
								  '<th width="7%">C相出温度</th>'+
								  '<th width="7%">A线盘入</th>'+
								  '<th width="7%">A线盘出</th>'+
								  '<th width="7%">B线盘入</th>'+
								  '<th width="7%">B线盘出</th>'+
								  '<th width="7%">C线盘入</th>'+
								  '<th width="7%">C线盘出</th>'+
								  '<th width="6%">状态</th>'+
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
		  '<div id="main" style="width: 1080px;height:400px;top:10px;"></div>'
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
        layer.load(1);
        setTimeout(function(){
            var deviceNumber = $("#deviceNumber").val();
            var startTime = $("#test1").val();//开始时间
            var endTime = $("#test2").val();//结束时间
            var userType = $("#userType").val();//结束时间
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
                            if(data.tempList != null && data.tempList != ''){
                                var str = "";
                                for ( var i = 0; i < data.tempList.length; i++) {
                                    str += "<tr id='qc'>";
                                    str += "<td width='10%'align='center'>"+data.tempList[i].time+"</td>";
                                    if(data.tempList[i].inA != "EEEEEEEE" && data.tempList[i].inA != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].inAxh != null && data.tempList[i].inAxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inA+"\/"+data.tempList[i].inAxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inA+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].inA+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].inA+"</td>";
                                        }
                                    }else if(data.tempList[i].inA == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].inA == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].inB != "EEEEEEEE" && data.tempList[i].inB != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].inBxh != null && data.tempList[i].inBxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inB+"\/"+data.tempList[i].inBxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inB+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].inB+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].inB+"</td>";
                                        }
                                    }else if(data.tempList[i].inB == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].inB == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].inB != "EEEEEEEE" && data.tempList[i].inB != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].inBxh != null && data.tempList[i].inBxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inB+"\/"+data.tempList[i].inBxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inB+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].inB+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].inB+"</td>";
                                        }
                                    }else if(data.tempList[i].inB == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].inB == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].inC != "EEEEEEEE" && data.tempList[i].inC != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].inCxh != null && data.tempList[i].inCxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inC+"\/"+data.tempList[i].inCxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].inC+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].inC+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].inC+"</td>";
                                        }
                                    }else if(data.tempList[i].inC == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].inC == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].outA != "EEEEEEEE" && data.tempList[i].outA != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].outAxh != null && data.tempList[i].outAxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].outA+"\/"+data.tempList[i].outAxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].outA+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].outA+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].outA+"</td>";
                                        }
                                    }else if(data.tempList[i].outA == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].outA == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }

                                    if(data.tempList[i].outB != "EEEEEEEE" && data.tempList[i].outB != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].outBxh != null && data.tempList[i].outBxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].outB+"\/"+data.tempList[i].outBxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].outB+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].outB+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].outB+"</td>";
                                        }
                                    }else if(data.tempList[i].outB == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].outB == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].outC != "EEEEEEEE" && data.tempList[i].outC != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].outCxh != null && data.tempList[i].outCxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].outC+"\/"+data.tempList[i].outCxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].outC+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].outC+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].outC+"</td>";
                                        }
                                    }else if(data.tempList[i].outC == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].outC == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].contactA != "EEEEEEEE" && data.tempList[i].contactA != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].contactAxh != null && data.tempList[i].contactAxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactA+"\/"+data.tempList[i].contactAxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactA+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].contactA+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].contactA+"</td>";
                                        }
                                    }else if(data.tempList[i].contactA == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].contactA == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].contactB != "EEEEEEEE" && data.tempList[i].contactB != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].contactBxh != null && data.tempList[i].contactBxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactB+"\/"+data.tempList[i].contactBxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactB+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].contactB+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].contactB+"</td>";
                                        }
                                    }else if(data.tempList[i].contactB == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].contactB == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].contactC != "EEEEEEEE" && data.tempList[i].contactC != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].contactCxh != null && data.tempList[i].contactCxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactC+"\/"+data.tempList[i].contactBxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactC+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].contactC+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].contactC+"</td>";
                                        }
                                    }else if(data.tempList[i].contactC == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].contactC == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].contactD != "EEEEEEEE" && data.tempList[i].contactD != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].contactDxh != null && data.tempList[i].contactDxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactD+"\/"+data.tempList[i].contactBxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactD+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].contactD+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].contactD+"</td>";
                                        }
                                    }else if(data.tempList[i].contactD == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].contactD == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].contactE != "EEEEEEEE" && data.tempList[i].contactE != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].contactExh != null && data.tempList[i].contactExh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactE+"\/"+data.tempList[i].contactExh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactE+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].contactE+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].contactE+"</td>";
                                        }
                                    }else if(data.tempList[i].contactE == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].contactE == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(data.tempList[i].contactF != "EEEEEEEE" && data.tempList[i].contactF != "DDDDDDDD"){
                                        if(userType != null && userType != ''){
                                            if(userType == 1 || userType == '1'){
                                                if(data.tempList[i].contactFxh != null && data.tempList[i].contactFxh != ''){
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactF+"\/"+data.tempList[i].contactFxh+"</td>";
                                                }else {
                                                    str += "<td width='7%'align='center'>"+data.tempList[i].contactF+"</td>";
                                                }
                                            }else {
                                                str += "<td width='7%'align='center'>"+data.tempList[i].contactF+"</td>";
                                            }
                                        }else {
                                            str += "<td width='7%'align='center'>"+data.tempList[i].contactF+"</td>";
                                        }
                                    }else if(data.tempList[i].contactF == "EEEEEEEE"){
                                        str += "<td width='7%'align='center'>未校准</td>";
                                    }else if(data.tempList[i].contactF == "DDDDDDDD"){
                                        str += "<td width='7%'align='center'>有干扰</td>";
                                    }
                                    if(userType != null && userType != ''){
                                        if(userType == 1 || userType == '1'){
                                            if(data.temp.antSignal != null && data.temp.antSignal != ''){
                                                if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                                    str += "<td width='6%'align='center'>正常</td>";
                                                }
                                                if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                                    str += "<td width='6%'align='center'>报警</td>";
                                                }
                                                if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                                    str += "<td width='6%'align='center'>异常</td>";
                                                }
                                            }else {
                                                if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                                    str += "<td width='6%'align='center'>正常</td>";
                                                }
                                                if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                                    str += "<td width='6%'align='center'>报警</td>";
                                                }
                                                if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                                    str += "<td width='6%'align='center'>异常</td>";
                                                }
                                            }
                                        }else {
                                            if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                                str += "<td width='6%'align='center'>正常</td>";
                                            }
                                            if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                                str += "<td width='6%'align='center'>报警</td>";
                                            }
                                            if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                                str += "<td width='6%'align='center'>异常</td>";
                                            }
                                        }
                                    }else {
                                        if(data.tempList[i].type == 1 || data.tempList[i].type == '1'){
                                            str += "<td width='6%'align='center'>正常</td>";
                                        }
                                        if(data.tempList[i].type == 2 || data.tempList[i].type == '2'){
                                            str += "<td width='6%'align='center'>报警</td>";
                                        }
                                        if(data.tempList[i].type == 3 || data.tempList[i].type == '3'){
                                            str += "<td width='6%'align='center'>异常</td>";
                                        }
                                    }


                                    str += "</tr>";
// 						    	sj2.push(data.tempList[i].time);
                                    inA2.push(data.tempList[i].inA);
                                    outA2.push(data.tempList[i].outA);
                                    inB2.push(data.tempList[i].inB);
                                    outB2.push(data.tempList[i].outB);
                                    inC2.push(data.tempList[i].inC);
                                    outC2.push(data.tempList[i].outC);
                                    contactA2.push(data.tempList[i].contactA);
                                    contactB2.push(data.tempList[i].contactB);
                                    contactC2.push(data.tempList[i].contactC);
                                    contactD2.push(data.tempList[i].contactD);
                                    contactE2.push(data.tempList[i].contactE);
                                    contactF2.push(data.tempList[i].contactF);
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
                                        data:['A相入','A相出','B相入','B相出','C相入','C相出','A线盘入','A线盘出','B线盘入','B线盘出','C线盘入','C线盘出']
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
                                        },
                                        {
                                            name: 'A线盘入',
                                            type: 'line',
                                            smooth: true,
                                            data: contactA2
                                        },
                                        {
                                            name: 'A线盘出',
                                            type: 'line',
                                            smooth: true,
                                            data: contactB2
                                        },
                                        {
                                            name: 'B线盘入',
                                            type: 'line',
                                            smooth: true,
                                            data: contactC2
                                        },
                                        {
                                            name: 'B线盘出',
                                            type: 'line',
                                            smooth: true,
                                            data: contactD2
                                        },
                                        {
                                            name: 'C线盘入',
                                            type: 'line',
                                            smooth: true,
                                            data: contactE2
                                        },
                                        {
                                            name: 'C线盘出',
                                            type: 'line',
                                            smooth: true,
                                            data: contactF2
                                        }]
                                });
                            }


                        }
                    });
        }, 2000);

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
  		        data:['A相入','A相出','B相入','B相出','C相入','C相出','A线盘入','A线盘出','B线盘入','B线盘出','C线盘入','C线盘出']
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
		    },
                {
                    name: 'A线盘入',
                    type: 'line',
                    smooth: true,
                    data:[0]
                },
                {
                    name: 'A线盘出',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'B线盘入',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'B线盘出',
                    type: 'line',
                    smooth: true,
                    data:[0]
                },
                {
                    name: 'C线盘入',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'C线盘出',
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
                data:['A相入','A相出','B相入','B相出','C相入','C相出','A线盘入','A线盘出','B线盘入','B线盘出','C线盘入','C线盘出']
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
                },
                {
                    name: 'A线盘入',
                    type: 'line',
                    smooth: true,
                    data:[0]
                },
                {
                    name: 'A线盘出',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'B线盘入',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'B线盘出',
                    type: 'line',
                    smooth: true,
                    data:[0]
                },
                {
                    name: 'C线盘入',
                    type: 'line',
                    smooth: true,
                    data: [0]
                },
                {
                    name: 'C线盘出',
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
    function selectxz(sort) {
        var deviceNumber = $("#deviceNumber").val();
        var startTime = $("#test1").val();//开始时间
        var endTime = $("#test2").val();//结束时间
        var numberDevices = $("#numberDevices").val();//有几个传感器
        location.href="${path}/webTemperatureDetection/daochu?deviceNumber="+deviceNumber+"&startTime="+startTime+"&endTime="+endTime+"&numberDevices="+numberDevices+"&sort="+sort;
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
                        url:'${path}/webTemperatureDetection/ImportByTempeSJ?deviceNumber='+deviceNumber, //用于文件上传的服务器端请求地址
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
