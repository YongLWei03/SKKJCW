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
    <script type="text/javascript" src="${path}/resource/js/jquery.page.js"></script>
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
                    <span class="divider">/</span> 设备类型
                </li>
            </ul>
            <div class="title_right">
                <strong>列表</strong>
            </div>
            <div style="width: 1200px; margin: auto;">
                <table class="table table-bordered">
                    <tr>
                        <td width="5%">
                                <input
                                        type="button" id = "inuser"  value="添加设备型号" onclick="inser();"class="btn btn-primary"
                                        style="width: 94px; height: 28px; font-family: 微软雅黑;" />
                        </td>
                    </tr>
                </table>
                <table class="table table-bordered table-hover table-striped"
                       style="font-family: 微软雅黑;">
                    <tbody>
                    <tr align="center" style="height: 35px;">
                        <td width="10%">序号</td>
                        <td width="15%">设备类型</td>
                        <td width="15%">设备型号</td>
                        <td width="15%">设备协议</td>
                        <td width="15%">厂商名称</td>
                        <td width="15%">厂商ID</td>
                        <td width="15%">操作</td>
                    </tr>

                    <c:forEach var="equipmentTypeList" items="${equipmentTypeList}" varStatus="s">
                        <tr align="center" style="height: 35px;">
                            <td>${s.index+1}</td>
                            <td>${equipmentTypeList.deviceType}</td>
                            <td>${equipmentTypeList.model}</td>
                            <td>${equipmentTypeList.protocolType}</td>
                            <td>${equipmentTypeList.manufacturerName}</td>
                            <td>${equipmentTypeList.manufacturerId}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="update(${equipmentTypeList.eptId})">修改</a>
                                    <a href="javascript:void(0);" onclick="delet(${equipmentTypeList.eptId})">删除</a>
                                </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <table>
                    <tr>
                        <td class="text-center">
                            <div class="tcdPageCode"></div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->
<%@include file="/resource/common/footer.jsp"%>

<script type="text/javascript">


    //分页初始化
    $(".tcdPageCode").createPage({
        pageCount:<%=request.getAttribute("pageCount") %>,
        current:<%=request.getAttribute("currentPage") %>,
        backFn:function(p){
            goback(p);
        }
    });

    //分页
    function goback(p){
        location.href="${path}/webEquipmentType/selectById?currentPage="+p;
    }



    function delet(eptId){
        if(confirm("确定删除吗")){
            $.ajax({
                url:'${path}/webEquipmentType/delete',
                data:{
                    'eptId' : eptId
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if(data=="true"){
                        location.reload();
                    }else if(data == "false"){
                        alert("删除失败");
                    }
                }
            });
        }
    }

    function inser(){
        location.href="${path}/webEquipmentType/addinsert";
    }


    function update(eptId){
        location.href="${path}/webEquipmentType/jrUpadate?eptId="+eptId;
    }


</script>

</body>

</html>
