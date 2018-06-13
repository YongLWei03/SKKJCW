<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<div class="header">
	 	<div class="logo"></div>
			<div class="header-right">
				<a href="javascript:void(0);" style="text-decoration:none;font-family:微软雅黑;">欢迎:<span style="color: white;font-weight: bold;">${user.userName}</span>进入</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<%--<a id="modal-973558" href="#modal-container-973558" role="button" data-toggle="modal" style="text-decoration:none;font-family:微软雅黑;">注销</a> --%>
				&nbsp;&nbsp;&nbsp;&nbsp;
				
                <div id="modal-container-973558" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:300px; margin-left:-150px; top:30%">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<%--<h3 id="myModalLabel">--%>
						<%--注销系统--%>
					<%--</h3>--%>
				</div>
				<div class="modal-body">
					<%--<p>--%>
						<%--您确定要注销退出系统吗？--%>
					<%--</p>--%>
				</div>
				<div class="modal-footer">
					 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> <a class="btn btn-primary" style="line-height:20px;" href="${path}/user/exit" >确定退出</a>
				</div>
			</div>
				</div>
				
				<input type="hidden" value="" id="test">
</div>

