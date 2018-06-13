$(function(){
		var userType = $("#userType").val();
		if(userType  == '3' || userType == 3){
			$.ajax({    
		    	url:'../webAlarmInformation/selectByType',   
			  	type:'post',    
			   	cache:false,    
			   	dataType:'json',    
			    success:function(data) {
			    	if(data=="true"){
			    		layer.open({
			    			  type: 2,
			    			  title: false,
			    			  closeBtn: 1, //不显示关闭按钮
			    			  shade: 0,
			    			  shadeClose:true,
			    			  area: ['340px', '215px'],
			    			  offset: 'rb', //右下角弹出
			    			  time: 50000, //2秒后自动关闭
			    			  anim: 2,
			    			  content: ['../webB/tank.jsp', 'no'], //iframe的url，no代表不显示滚动条
			    			  btn: ['详情->>'] //可以无限个按钮
				    		,yes: function(index, layero){
				    		    //按钮【按钮一】的回调
				    			location.href="../webAlarmInformation/selectAlarmInformation";
				    		  }
			    		});
			    	}
			    }
		   }); 
		}
	});