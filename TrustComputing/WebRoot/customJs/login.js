$(document).ready(function(){
	$("#userName, #userPasswd").addClass("text ui-widget-content ui-corner-all");
	$("#userType").buttonset().addClass("ui-state-hover");
				
	$("#login").button().click(function(){
		var name = $("#userName").val();
		var passwd = $("#userPasswd").val();
		var type = $('input:radio[name="userType"]:checked').val();
		$.post(
			'loginCheck',
			{ userName: name, userPasswd: passwd, userType: type }, 	
			function(data){
				if(data == 0) {
					location.href = "normaluser.jsp";
				}
				else if(data == 1) {
					location.href = "index.jsp";
				}
				else if(data == 2){
					$("#tips").html("<font color=red >用户名或密码或类型错误!</span>");
				}
				else {
					$("#tips").html("<font color=red>对不起，当前平台不可信！</span>");
				}
			});
		});
	$("#reset").button().click(function(){
		$("#userName").val("");
		$("#userPasswd").val("");
	});
				
	$("#userName").focus(function(){
		$(this).parent().find("font").remove();
	}).blur(function(){
		if($(this).val() == "") {
			$(this).parent().append("<font color='red'>注意：用户名不能为空!</font>");
		}
	});
	$("#userPasswd").focus(function(){
		$(this).parent().find("font").remove();
	}).blur(function(){
		if($(this).val() == "") {
			$(this).parent().append("<font color=red>注意：密码不能为空!</font>");
		}
	});
});
