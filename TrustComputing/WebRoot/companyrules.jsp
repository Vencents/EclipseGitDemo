<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="zh-CN">
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
						<li><a href="#" id="item8"><i class="icon-lock"></i><span class="hidden-tablet"> 登出系统</span></a></li>
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
						<h2><i class="icon-picture"></i>企业文化</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<div class="page-header">
							<h1>企业精神和文化理念<small>你用电，我用心</small></h1>
						</div>
						<div class="row-fluid ">            
							  <div class="span4">
								<h3>核心价值观</h3>
								<p>
									<strong>奉献清洁能源 建设和谐社会</strong>“奉献清洁能源，建设和谐社会”的企业使命是公司生存发展的根本意义，是公司事业的战略定位，是公司工作的深刻内涵和价
									值体现。作为国家能源战略布局的重要组成部分和能源产业链的重要环节，国家电网公司在中国能源的优化配置中扮演着重要角色。坚强的智能电网不仅是
									连接电源和用户的电力输送载体，更是具有网络市场功能的能源资源优化配置载体。充分发挥电网功能，保障更安全、更经济、更清洁、可持续的电力供应
									，促使发展更加健康、社会更加和谐、生活更加美好是国家电网公司的神圣使命。
								</p>	
							  </div>
							  <div class="span4">
							  	<h3>公司愿景</h3>
							  	<p>
							  		<strong>建设世界一流电网 建设国际一流企业</strong>
							  		“两个一流”的企业愿景是公司的奋斗方向，是国家电网人的远大理想，是公司一切工作的目标追求。建设世界一流电网：从我国国情、能源资源状况和电
									网发展规律的实际出发，坚持以科学发展观为指导，坚持自主创新，赶超世界先进水平，充分利用先进的技术和设备，按照统一规划、统一标准、统一建设
									的原则，建设以特高压电网为骨干网架、各级电网协调发展、具有信息化、自动化、互动化特征的坚强智能电网。建设国际一流企业：坚持以国际先进水平
									为导向，以同业对标为手段，推进集团化运作、集约化发展、精益化管理、标准化建设，把公司建设成为具有科学发展理念、持续创新活力、优秀企业文化
									、强烈社会责任感和国际一流竞争力的现代企业。
							  	</p>
							  </div>
							  <div class="span4">
							  	<h3>企业精神</h3>
							  	<p>
							  	<strong>努力超越 追求卓越</strong>“两越”精神是公司和员工勇于超越过去、超越自我、超越他人，永不停步，追求企业价值实现的精神境界。“两越”精神的本质是与时俱进、开拓创新、
								科学发展。 公司立足于发展壮大国家电网事业，奋勇拼搏，永不停顿地向新的更高的目标攀登，实现创新、跨越和突破。公司及员工以党和国家利益为重，
								以强烈的事业心和责任感，不断向更高标准看齐，向更高目标迈进。
							  	</p>
							  </div>
						</div>
					</div>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid sortable">
				<div class="box span8">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i>电力科普</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<h2>同步电机的工作原理</h2>
						<div class="tooltip-demo well">
							<p class="muted" style="margin-bottom: 0;">
							同步电机和感应电机一样是一种常用的交流电机。特点是：稳态运行时，转子的转速
							和电网频率之间又不变得关系n=ns=60f/p，ns成为同步转速。若电网的
							频率不变，则稳态时同步电机的转速恒为常数而与负载的大小无关。同步电机分为
							同步发电机和同步电动机。现代发电厂中的交流机以同步电机为主。
							</p>  	
						</div>
						<h2>电气设备为什么会发生火灾</h2>
						<div class="tooltip-demo well">
							<p class="muted" style="margin-bottom: 0;">
							大家知道，各种电气设备中的绝缘物质，大部分都是不同程度的易燃品制成的。如油开关、变压器中的绝缘油，各种绝缘物体和电缆绝缘部分的纸、橡皮、
							油漆、绝缘外皮以及各种配电盘构架外壳的木料、塑料等也都是可燃的。这些易燃的物质都有着一定的额定温度要求，如果当电气设备运行中温度超过其极
							限时，就有发生火灾的可能，而温度的超高往往又都是由于各种电气事故引起的。比如以低压线路来说吧，它们广泛分布在各用电单位中，是传送电能的主
							要设备，在运行中时常由于线路检修恢复供电时接错线或对线路检查监视不够引起短路，过负荷运行，保险丝过大造成发热或运行维护不当，致使电气设备
							和线路绝缘损坏或绝缘老化等，而产生电火花或引起电弧造成过热发生火灾。电动机在运行中由于忽视安全，不遵守操作规程和对设备维修保养不够，致使
							电刷发生火花，接触电阻过大，轴承过热或严重过负荷，造成匝间、相间发生短路或接地，断相运行，开启式电动机吸入县纤维，粉尘过多，堵塞风道，接
							线端子接触不良，电阻过大产生高温等均可造成火灾。变压器绝缘油油质不良，漏渗油严重，套管破裂进水，长期过负荷绕组发热，层间匝间相间短路等原
							因，也是发生火灾的重要因素之一。
							</p>
						</div>
						<h2>什么是烟气再循环调温</h2>
						<div class="tooltip-demo well">
							<p class="muted" style="margin-bottom: 0;">
							烟气再循环调温的原理是利用省煤器后烟气(温度为250℃—350℃)的一部分，通过再循环风机从炉膛下部送入，以降低炉膛的辐射换热量，改变锅炉辐射与对
							流受热面的吸热量比例，达到调节汽温的目的。炉膛温度随再循环烟气量增加而降低，使辐射吸热量减少，但炉膛出口烟气温度变化不大。
							</p>
						</div>   
					</div>
				</div>
				<div class="box span4">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i>募股细则</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<ol>
						  <li>材料受理、分发环节。发行监管部综合处收到申请文件后将其分发审核一处、审核二处，同时送国家发改委征求意见。审核一处、审核二处根据发行人的行业、公务回避的有关要求以及审核人员的工作量等确定审核人员。</li>
						  <li>见面会环节。见面会旨在建立发行人与发行监管部的初步沟通机制。</li>
						  <li>问核环节。问核机制旨在督促、提醒保荐机构及其保荐代表人做好尽职调查工作，安排在反馈会前后进行，参加人员包括问核项目的审核一处和审核二处的审核人员、两名签字保荐代表人和保荐机构的相关负责人。</li>
						  <li>反馈会环节。审核一处、审核二处审核人员审阅发行人申请文件后，从非财务和财务两个角度撰写审核报告，提交反馈会讨论。</li>
						  <li>预先披露环节。反馈意见落实完毕、国家发改委意见等相关政府部门意见齐备、财务资料未过有效期的将安排预先披露。</li>
						  <li>初审会环节。初审会由审核人员汇报发行人的基本情况、初步审核中发现的主要问题及其落实情况。</li>
						  <li>发审会环节。发审委制度是发行审核中的专家决策机制。</li>
						  <li>封卷环节。发行人的首发申请通过发审会审核后，需要进行封卷工作，即将申请文件原件重新归类后存档备查。</li>
						  <li>会后事项环节。会后事项是指发行人首发申请通过发审会审核后，招股说明书刊登前发生的可能影响本次发行及对投资者作出投资决策有重大影响的应予披露的事项。</li>
						  <li>核准发行环节。封卷并履行内部程序后，将进行核准批文的下发工作。</li>
						</ol>  
					</div>
				</div>
			
			<div class="row-fluid sortable">
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-picture"></i>集团大事件</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-setting btn-round"><i class="icon-cog"></i></a>
							<a href="#" class="btn btn-minimize btn-round"><i class="icon-chevron-up"></i></a>
							<a href="#" class="btn btn-close btn-round"><i class="icon-remove"></i></a>
						</div>
					</div>
					<div class="box-content">
						<h2>2010年</h2>
						<div class="tooltip-demo well">
							<ul>
								<li>
								1月11日国家科学技术奖励大会在北京召开。“电力系统全数字实时仿真关键技术研究、装置研制和应用”项目获2009年度国家科技进步奖一等奖。这是公司
								自2007年起连续第三次获国家科技进步奖一等奖。同时，公司还有5个项目获二等奖。
								</li>
								<li>
								4月14日7时49分青海省玉树藏族自治州玉树县发生7.1级地震。地震发生后,公司立即开展玉树地方电网抢险救援支援工作，成立抢险领导小组，迅速启动救
								援抢险应急预案，并在第一时间组织人力、物力赶赴受灾现场。
								</li>
								<li>
								7月16日中共中央政治局委员、国务院副总理张德江，国务院国资委主任李荣融，上海市委副书记、市长韩正参观了上海世博会国家电网馆。
								</li>
								<li>
								8月21日由人民网主办的首届“低碳中国创新论坛”在北京隆重举行，论坛同时揭晓了2010低碳中国奖。公司特高压输电技术当选2010年度“低碳中国·十大
								创新技术产品奖”。
								</li>
								<li>
								10月16日向家坝—上海±800kV特高压直流输电示范工程已安全稳定运行100天，每天输送电力约200万kW，在电网迎峰度夏和在更大范围内优化配置能源资源
								发挥了显著作用。
								</li>
								<li>
								12月23日公司1000kV晋东南—南阳—荆门特高压交流试验示范工程荣获2010年国家优质工程金奖，国家优质工程奖是1981年经国务院批准设立的我国工程建
								设质量方面的最高荣誉奖励。
								</li>
							</ul>
						</div>
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
				<p>抱歉，板块配置暂不开放！</p>
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
