<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>

	<title>电力网络终端监控系统-后台首页</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
	<meta name="author" content="Muhammad Usman">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- The styles -->
	<link id="bs-css" href="css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/charisma-app.css" rel="stylesheet">
	<link href="css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='css/fullcalendar.css' rel='stylesheet'>
	<link href='css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='css/chosen.css' rel='stylesheet'>
	<link href='css/uniform.default.css' rel='stylesheet'>
	<link href='css/colorbox.css' rel='stylesheet'>
	<link href='css/jquery.cleditor.css' rel='stylesheet'>
	<link href='css/jquery.noty.css' rel='stylesheet'>
	<link href='css/noty_theme_default.css' rel='stylesheet'>
	<link href='css/elfinder.min.css' rel='stylesheet'>
	<link href='css/elfinder.theme.css' rel='stylesheet'>
	<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='css/opa-icons.css' rel='stylesheet'>
	<link href='css/uploadify.css' rel='stylesheet'>
	<link href='jqGrid/css/ui.jqgrid.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="img/favicon.ico">
		
</head>

<body>
		<!-- topbar starts -->
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="index.jsp"> <img alt="Charisma Logo" src="img/logo20.png" /> <span class="span4">State Grid System</span></a>								
				<!-- theme selector starts -->
				<div class="btn-group pull-right theme-container" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-tint"></i><span class="hidden-phone"> 变更主题 /皮肤</span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="themes">
						<li><a data-value="classic" href="#"><i class="icon-blank"></i> Classic</a></li>
						<li><a data-value="cerulean" href="#"><i class="icon-blank"></i> Cerulean</a></li>
						<li><a data-value="cyborg" href="#"><i class="icon-blank"></i> Cyborg</a></li>
						<li><a data-value="redy" href="#"><i class="icon-blank"></i> Redy</a></li>
						<li><a data-value="journal" href="#"><i class="icon-blank"></i> Journal</a></li>
						<li><a data-value="simplex" href="#"><i class="icon-blank"></i> Simplex</a></li>
						<li><a data-value="slate" href="#"><i class="icon-blank"></i> Slate</a></li>
						<li><a data-value="spacelab" href="#"><i class="icon-blank"></i> Spacelab</a></li>
						<li><a data-value="united" href="#"><i class="icon-blank"></i> United</a></li>
					</ul>
				</div>
				<!-- theme selector ends -->
				
				<!-- user dropdown starts -->
				<div class="btn-group pull-right" >
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i><span class="hidden-phone"> <%= session.getAttribute("userName") %></span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">个人档案</a></li>
						<li class="divider"></li>
						<li><a href="#">退出系统</a></li>
					</ul>
				</div>
				<!-- user dropdown ends -->			
			</div>
		</div>
	</div>
	<!-- topbar ends -->
		<div class="container-fluid">
		<div class="row-fluid">
				
			<!-- left menu starts -->
			<div class="span2 main-menu-span">
				<div class="well nav-collapse sidebar-nav">
					<ul class="nav nav-tabs nav-stacked main-menu">
						<li class="nav-header hidden-tablet">系统功能</li>
						<li><a class="ajax-link" id="item1" href="usertables.jsp"><i class="icon-align-justify"></i><span class="hidden-tablet"> 用户详情</span></a></li>
						<li><a class="ajax-link" id="item2" href="terminals.jsp"><i class="icon-picture"></i><span class="hidden-tablet"> 终端详情</span></a></li>
						<li><a class="ajax-link" id="item3" href="flowcharts.jsp"><i class="icon-list-alt"></i><span class="hidden-tablet"> 流量监控</span></a></li>
						<li class="nav-header hidden-tablet">主面板</li>
						<li><a class="ajax-link" id="item4" href="index.jsp"><i class="icon-home"></i><span class="hidden-tablet"> 信息概览</span></a></li>
						<li><a href="tour.jsp"  id="item5"><i class="icon-globe"></i><span class="hidden-tablet"> 系统导航</span></a></li>
						<li><a class="ajax-link" id="item6" href="calendar.jsp"><i class="icon-calendar"></i><span class="hidden-tablet"> 日历安排</span></a></li>
						<li><a class="ajax-link" id="item7" href="companyrules.jsp"><i class="icon-font"></i><span class="hidden-tablet"> 规章制度</span></a></li>
						<li><a href="#"  id="item8"><i class="icon-lock"></i><span class="hidden-tablet"> 登出系统</span></a></li>
					</ul>				
				</div><!--/.well -->
			</div><!--/span-->
			<!-- left menu ends -->
			
			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">警告!</h4>
					<p>你需要启用 <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a> 以正常访问本系统.</p>
				</div>
			</noscript>
			
			<div id="content" class="span10">
			<!-- content starts -->
			<div>
				<ul class="breadcrumb">
					<li>
						<a href="#">主面板</a> <span class="divider">/</span>
					</li>
					<li>
						<a href="#">系统导航</a>
					</li>
				</ul>
			</div>

			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i>终端详情</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table id="list_terminal_details"></table> 
  	  					<div id="list_terminal_details_pager" style="margin: 0 auto;" class="scroll"></div>
					</div>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i>终端进程映像</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table id="list_terminal_process"></table> 
  	  					<div id="list_terminal_process_pager" style="margin: 0 auto;" class="scroll"></div>						
					</div>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid sortable">
				<div class="box span6">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i>远程状态控制</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<table id="list_terminal_remote"></table> 
  	  					<div id="list_terminal_remote_pager" style="margin: 0 auto;" class="scroll"></div>						
					</div>
				</div><!--/span-->
				<div class="box span6">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i>信任参数配置</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<form class="form-horizontal" id="settings">
							<fieldset>
								<div class="alert alert-info" id="tips">
									<button type="button" class="close" data-dismiss="alert">×</button>
									<strong>温馨提示：</strong> 所有配置将保存到数据库！
								</div>
								<div class="control-group">
									<label class="control-label" for="selectType">目标用户类型</label>
									<div class="controls">
									  <select id="selectType">
										<option value='0'>普通用户</option>
										<option value='1'>超级用户</option>
										<option value='2'>管理员</option>
									  </select>
									</div>
								  </div>
								  
								 <div class="control-group">
									<label class="control-label" for="selectCapture">屏幕截取扣罚</label>
									<div class="controls">
									  <select id="selectCapture" data-rel="chosen">
										<option value='1'>1个信任度/次</option>
										<option value='2'>2个信任度/次</option>
										<option value='3'>3个信任度/次</option>
										<option value='4'>4个信任度/次</option>
										<option value='5'>5个信任度/次</option>
									  </select>
									  
									</div>
								  </div>
							  
								   <div class="control-group">
										<label class="control-label" for="selectPaste">剪贴板使用扣罚</label>
										<div class="controls">
										  <select id="selectPaste">
											<option value='1'>1个信任度/次</option>
											<option value='2'>2个信任度/次</option>
											<option value='3'>3个信任度/次</option>
											<option value='4'>4个信任度/次</option>
											<option value='5'>5个信任度/次</option>
										  </select>
										 
										</div>
									</div>
								
								 <div class="control-group">
									<label class="control-label" for="selectLogin">登陆失败扣罚</label>
									<div class="controls">
									  <select id="selectLogin" data-rel="chosen">
										<option value='1'>1个信任度/次</option>
										<option value='2'>2个信任度/次</option>
										<option value='3'>3个信任度/次</option>
										<option value='4'>4个信任度/次</option>
										<option value='5'>5个信任度/次</option>
									  </select>
									  <span class="help-inline">建议1个信任度/次</span>
									</div>
								  </div>
								    <div class="control-group">
										<label class="control-label" for="selectRatio">流量超标扣罚</label>
										<div class="controls">
										  <select id="selectRatio">
											<option value='1'>1个信任度/次</option>
											<option value='2'>2个信任度/次</option>
											<option value='3'>3个信任度/次</option>
											<option value='4'>4个信任度/次</option>
											<option value='5'>5个信任度/次</option>
										  </select>
										  <span class="help-inline">建议1个信任度/次</span>
										</div>
									</div>							  
								  	<div class="control-group warning">
										<label class="control-label" for="inputWarning">实时流量阀值</label>
										<div class="controls">
										  <input type="text" id="inputWarning">
										  <span class="help-inline">*慎重填写</span>
										</div>
								  	</div>							  
									<div class="form-actions">
										<button type="submit" class="btn btn-primary" id="submit_settings">提交更改</button>
										<button class="btn btn-warning" id="cancel_submit">放弃修改</button>
									</div>							
							</fieldset>
						</form>
					</div>
				</div><!--/span-->
			</div><!--/row-->
		<!-- content ends -->
		</div><!--/#content.span10-->
		</div><!--/fluid-row-->
				
		<hr>

		<div class="modal hide fade" id="myModal">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h3>设置</h3>
			</div>
			<div class="modal-body">
				<p>浮动暂时不开放配置！</p>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">关闭</a>
				<a href="#" class="btn btn-primary">提交更改</a>
			</div>
		</div>

		<footer>
			<p class="pull-left">&copy; <a href="http://www.cnblogs.com/csuftzzk" target="_blank">ZhangZhongke@24K纯开源</a> 2012-2013</p>
			<p class="pull-right">Powered by: <a href="http://blog.sina.com.cn/hustsecurity">华中科技大学信息安全与保密研究所</a></p>
		</footer>
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/jquery-ui-1.8.21.custom.min.js"></script>

	<script type="text/javascript" src="jqGrid/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="customJs/terminal.js"></script>	
	
	<!-- transition / effect library -->
	<script src="js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='js/jquery.dataTables.min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="js/charisma.js"></script>

</body>
</html>
