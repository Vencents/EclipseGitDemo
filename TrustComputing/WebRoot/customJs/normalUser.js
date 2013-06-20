/*
 * Executed upon as the completion of loading DOM tree
 */
$(document).ready(function(){
	$("#exitSystem").button().click(function(){
		exitSystem();
	});
	$("#alterPasswd").button().click(function(){
		alterPasswd();
	});
	$(".alterPasswdDialog").dialog({
		autoOpen: false,
		height: 320,
		width: 400,
		modal: true,
		resizable: false,
		show: 'fade',
		title: 'Alter Password',
		hide: 'fade',
		buttons: {
			'Reset': function(){
				$("#oldPasswd, #newPasswd, #repeatPasswd").removeClass("ui-state-highlight");
				/* consistency checking */
				var oldpw = $("#oldPasswd").val();
				var newpw = $("#newPasswd").val();
				var rnewpw = $("#repeatPasswd").val();
				if(oldpw == "" || newpw == "" || rnewpw == ""){
					highlightTips('所有字段都要填写!', $(".validateTips"));		
				}
				else {
					if(newpw == rnewpw){
						$.post(
							'AlterPasswd', 
							{oldp:$("#oldPasswd").val(), newp:newpw},
							function(data, textStatus){
								var code = parseInt(data);
								if(code == 1) {
									$(".alterPasswdDialog").dialog("close");
								}
								else {
									highlightTips('Old password is wrong!', $(".validateTips"));	
								}
							}
						);
					}
					else
					{
						highlightTips('新密码与旧密码不匹配!', $(".validateTips"));
					}
				}
			},
			'Cancel': function(){
				$(".alterPasswdDialog").dialog("close");
			}
		},
		open: function() {
			$("#oldPasswd").val("");
			$("#newPasswd").val("");
			$("#repeatPasswd").val("");
		},
	});
	$("#oldPasswd ~ #repeatPasswd").addClass("ui-corner-all");
	/* styles applied to tabs and list */
	$("#tabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
    $( "#tabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
});

/*
 * param: tips_t stands for a tips string needed to be highlight
 * param: object_t stands for a jquery object
 */
function highlightTips(tips_t, object_t)
{
	object_t.text(tips_t).addClass("ui-state-highlight focusStyle");
	setTimeout(function(){
		object_t.removeClass("ui-state-highlight");  
	}, 5000);
}
/*
* respond quiting on the system
*/
function exitSystem(){
    $.ajax({
        type: 'POST',
        url: 'SessionValidate',
        success:function(data){
            window.location.href = "login.html";
        }
    }); 
} 
/*
 * open altering user password dialog
 */
function alterPasswd(){
    $(".alterPasswdDialog").dialog("open");
}
