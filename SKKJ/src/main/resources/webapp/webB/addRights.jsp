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
        <%-- 			<input type="hidden" id="hname" value="${user.name }"> --%>
        <div class="right_cont">
            <ul class="breadcrumb" style="font-family: 微软雅黑;">
                <li>当前位置： <a href="javascript:void(0);"
                             style="text-decoration: none;">首页</a> <span class="divider">/</span>
                    <a href="javascript:void(0);" style="text-decoration: none;">管理</a>
                    <span class="divider">/</span> 用户
                </li>
            </ul>
            <input type="hidden" value="${userRigh.provinceId }" id="provinceId">
            <input type="hidden" value="${userRigh.cityId }" id="cityId">
            <input type="hidden" value="${userRigh.areaId }" id="areaId">
            <input type="hidden" value="${user.type }" id="type">
            <input type="hidden" value="${userId }" id="userId">
            <input type="hidden" value="${user.cityId }" id="userCityId">
            <input type="hidden" value="${user.areaId }" id="userAreaId">
            <input type="hidden" value="${flog }" id="flog">
            <div class="title_right">
                <strong>用户权限设定</strong>
            </div>
            <div style="width: 1200px; margin: auto;"></div>
            <table class="table table-bordered" style="font-family: 微软雅黑;">
                <tr height="50px;">
                    <td width="20%" style="font-size: 15px; text-align: center;">
                        管理区域：</td>
                    <td width="80%" id = "dq">
                        <select id="province" style="height: 25px; width: 100px;" onchange="provinceC()">
                            <c:if test="${user.type == 1 || user.type =='1' || userRigh.provinceId == 0|| userRigh.provinceId =='0' || userRigh.provinceId == '' || userRigh.provinceId == null || user.provinceId == '0' || user.provinceId == 0 ||user.provinceId == null || user.provinceId == ''}">
                                <option value="0">全省</option>
                            </c:if>
                            <c:forEach items="${region }" var="region" varStatus="s">
                                <c:if test="${userRigh.provinceId == region.regionId}">
                                    <option value="${region.regionId }" selected="selected">${region.regionName }</option>
                                </c:if>
                                <c:if test="${userRigh.provinceId != region.regionId && user.type == 1 ||userRigh.provinceId != region.regionId && user.type == '1' || user.provinceId == '0' || user.provinceId == 0}">
                                    <option value="${region.regionId }">${region.regionName }</option>
                                </c:if>
                                <c:if test="${userRigh.provinceId == null || userRigh.provinceId == ''}">
                                    <c:if test="${user.provinceId == region.regionId}">
                                        <option value="${region.regionId }">${region.regionName }</option>
                                    </c:if>
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
                <tr height="50px;" id="gld">
                    <td width="20%" style="font-size: 15px; text-align: center;">
                        管理站点(可选填)：</td>
                    <td width="80%" id="substation">
                        <c:forEach items="${substationpacList }" var="substationpacList" varStatus="s">
                            <input type="checkbox" name="receiver" value="${substationpacList.substationId }" > ${substationpacList.substationName }
                        </c:forEach>
                    </td>
                </tr>
            </table>
            <div style="margin-top: 30px;">
                <input type="button" value="全选/不选"  onclick="cbxAll();" class="btn btn-primary"style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
            </div>
            <div style="text-align: center; margin-top: 30px;">
                <c:if test="${userRigh.region != null}">
                    <input type="button" value="修改" onclick="upda(${userRigh.userId})"
                           class="btn btn-primary"
                           style="width: 80px; height: 28px; font-family: 微软雅黑; margin-right: 10px;" />
                </c:if>
                <c:if test="${userRigh.region == null}">
                    <input type="button" value="添加" onclick="upda(${userRigh.userId})"
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
<!-- 底部 -->
<%@include file="/resource/common/footer.jsp"%>

<script type="text/javascript">

    $(function(){
        provinceC();
    });

    function provinceC(){
        var provinceId = $('#province').val();
        var cityId = $('#cityId').val();
        var areaId = $('#areaId').val();
        var type = $('#type').val();
        var userCityId = $('#userCityId').val();
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
                    if(cityId == 0 || cityId =='0'|| type == 1 || type =='1'){
                        str += '<option value="0">全市</option>';
                    }
                    for ( var i = 0; i < data.regionsList.length; i++) {
                        if(cityId == data.regionsList[i].regionId){
                            str += '<option value="'+data.regionsList[i].regionId+'" selected="selected">'+data.regionsList[i].regionName+'</option>';
                        }else if(userCityId == 0 || userCityId =='0' || type == 1 || type == '1'){
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
        selectSubstationByDqMap();
    }

    function city(){
        var cityId = $('#city').val();
        var areaId = $('#areaId').val();
        var userAreaId = $('#userAreaId').val();
        var type = $('#type').val();
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
                    if(areaId == 0 || areaId =='0' || type == 1 || type =='1'){
                        str += '<option value="0">全区</option>';
                    }
                    for ( var i = 0; i < data.regionxList.length; i++) {
                        if(areaId == data.regionxList[i].regionId){
                            str += '<option value="'+data.regionxList[i].regionId+'" selected="selected">'+data.regionxList[i].regionName+'</option>';
                        }else if(userAreaId == 0 || userAreaId =='0'|| type == 1 || type =='1'){
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
        selectSubstationByDqMap();
    }



    function remo(value){
        location.href="${path}/webUser/userRights";
    }

    function upda(userId){
        var provinceId = $('#province').val();
        var cityId = $('#city').val();
        var areaId = $('#area').val();
        var flog = $('#flog').val();
        var swId =[];
        $('input[name="receiver"]:checked').each(function(){
            swId.push($(this).val());
        });
        if(swId.length==0){
            alert('请选择管理站点');
            return false;
        }
        $.ajax({
            url:'${path}/webUser/setUserRights',
            data:{
                'userId' : userId,
                'provinceId' : provinceId,
                'cityId' : cityId,
                'areaId' : areaId,
                'substationId' : swId,
            },
            type:'post',
            cache:false,
            dataType:'json',
            traditional: true,
            success:function(data) {
                if(data=="true"){
                    if(flog == 2 || flog == '2'){
                        location.href="${path}/webUser/userRightsApp";
                    }else{
                        location.href="${path}/webUser/userRights";
                    }
                }else if(data == "false"){
                    layer.msg('添加失败');
                }
            }
        });
    }
    function cbxAll() {
        var receiver=document.getElementsByName("receiver");
        for (var i=0;i<receiver.length;i++) {
            var e=receiver[i];
            e.checked=!e.checked;
        }

    }

    function ff(){

    }

    function area() {
        selectSubstationByDqMap();
    }

    function selectSubstationByDqMap() {
        var provinceId = $('#province').val();
        var cityId = $('#city').val();
        var areaId = $('#area').val();
        var userId = $('#userId').val();
        var type = $('#type').val();
        $.ajax({
            url:'${path}/webUser/selectSubstationByDqMap',
            data:{
                'provinceId' : provinceId,
                'cityId' : cityId,
                'areaId' : areaId,
                'userId' : userId,
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                var str ='';
                str += '<td width="80%" id="substation">';
                for ( var i = 0; i < data.substationList.length; i++) {
                    var chenk = 0;
                    if(data.userRegionsub.length > 0){
                        for ( var j = 0; j < data.userRegionsub.length; j++) {
                            if(data.userRegionsub[j] == data.substationList[i].substationId){
                                str += '<input type="checkbox" name="receiver" value="'+data.substationList[i].substationId+'" checked="checked"> '+data.substationList[i].substationName;
                                break;
                            }else{
                                if(data.userRegionsub.length-1 !=  chenk){
                                    chenk ++;
                                    continue;
                                }
                                if(chenk == data.userRegionsub.length-1){
                                    str += '<input type="checkbox" name="receiver" value="'+data.substationList[i].substationId+'" > '+data.substationList[i].substationName;
                                }
                            }
                        }
                    }else {
                        str += '<input type="checkbox" name="receiver" value="'+data.substationList[i].substationId+'" > '+data.substationList[i].substationName;
                    }

                }
                str += '</td>';
                $("#substation").remove();
                $("#gld").append(str);
            }

        });
    }
</script>

</body>

</html>
