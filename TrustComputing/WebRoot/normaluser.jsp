<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>电力网络终端监控系统-普通用户首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
	<link rel="stylesheet" type="text/css" href="css/jquery-ui-1.8.21.custom.css"/>
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
	<script type="text/javascript" src="customJs/normalUser.js"></script>
	<link rel="stylesheet" type="text/css" href="customCss/userPage.css">
	<link rel="stylesheet" type="text/css" href="customCss/coommon.css">
	<style type="text/css">
		.item {float: left;}
    	.item_content, strong {float: right; color: gray;}
		#tab6, #tab6 div {border: none;}
		#info {float: right;}
	</style>
  </head>
  
  <body>
   	<div id="container">
   		<div id="header">
   			<a href="/" class="logo"></a>
   			<input type="button" id="exitSystem" value="安全退出"/>
   			<input type="button" id="alterPasswd" value="修改密码"/>
   		</div>
   		<div id="main">
   			<div id="tabs">
   			  <ul>
   				<li><a href="#tab1">公司简介</a></li>
   				<li><a href="#tab2">公司服务宗旨</a></li>
   				<li><a href="#tab3">发展路线</a></li>
   				<li><a href="#tab4">机构组织</a></li>
   				<li><a href="#tab5">个人信息设置</a></li>
   				<li><a href="#tab6">版权说明</a></li>
   			  </ul>
   			  <div id="tab1">
   			  		<h3>公司简介</h3>
   					<b>您好, <%= session.getAttribute("userName")%></b>
   					<b>您上次登录于：<%= session.getAttribute("lastLogin") %></b><hr>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国家电网公司成立于2002年12月29日，是经国务院同意进行国家授权投资的机构和国家控股公司的试点单位，
   					以建设和运营电网为核心业务，承担着保障更安全、更经济、更清洁、可持续的电力供应的基本使命，经营区域覆盖全国26个省（自治区、直辖市），
   					覆盖国土面积的88%，供电人口超过11亿人，公司用工总量超过186万人。</p>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公司在菲律宾、巴西、葡萄牙、澳大利亚等国家和地区开展业务。2012年，公司名列《财富》世界企业500强第7位，是全球最大的公用事业企业。</p>
   					<img alt="销售业绩" src="img/home_image.jpg">
   				</div>
   				<div id="tab2">
   					<h3>服务宗旨</h3><hr>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务电力客户：公司作为经营范围遍及全国26个省（自治区、直辖市），供电人口超过10亿的供电企业，承担着为电力客户提供安全、可靠、清洁的电力供应
					和优质服务的基本职责。公司坚持服务至上，以客户为中心，不断深化优质服务，持续为客户创造价值。</p> 
　　					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务发电企业：公司作为电力行业中落实国家能源政策、联系发电企业和客户、发挥桥梁作用的经营性企业，承担着开放透明、依法经营的责任。公司遵循电
					力工业发展规律，科学规划建设电网，严格执行“公开、公平、公正”调度，与合作伙伴共同创造广阔发展空间。</p> 
　　					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务经济社会发展：公司作为国家能源战略的实施主体之一，承担着优化能源资源配置，满足经济社会快速增长对电力需求的责任。公司坚持经济责任与社会
					责任相统一，保障电力安全可靠供应，服务清洁能源开发，推进节能降耗，保护生态环境，履行社会责任，服务社会主义和谐社会建设。</p> 
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;党的十八大明确提出，确保到2020年实现全面建成小康社会的宏伟目标，国内生产总值和城乡居民人均收入比2010年翻一番；</p>		
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务党和国家工作大局：公司作为关系国家能源安全、国民经济命脉的国有重要骨干企业，承担着确保国有资产保值增值，增强国家经济实力和产业竞争力的重要责任。公司坚持局部利益服从全局利益，把维护党和国家的利益作为检验工作成效和企业业绩的根本标准。</p> 
   				</div>
   				<div id="tab3">
   					<h3>发展路线</h3><hr>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;建设世界一流电网：从我国国情、能源资源状况和电网发展规律的实际出发，坚持以科学发展观为指导，坚持自主创新，
   					赶超世界先进水平，充分利用先进的技术和设备，按照统一规划、统一标准、统一建设的原则，建设以特高压电网为骨干网架、各级电网协调发展、
   					具有信息化、自动化、互动化特征的坚强智能电网。</p>
　　					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;建设国际一流企业：坚持以国际先进水平为导向，以同业对标为手段，推进集团化运作、集约化发展、
					精益化管理、标准化建设，把公司建设成为具有科学发展理念、持续创新活力、优秀企业文化、强烈社会责任感和国际一流竞争力的现代企业。</p>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积极支持和服务清洁能源发展，服务建设美丽中国，依托坚强智能电网，加强统一调度和管理，保证了清洁能源的大规模接入。</p>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2012年底，我国水电装机达到2.5亿千瓦，位列世界首位，其中公司经营区域1.68亿千瓦；我国并网风电达到6083万千瓦，
   					其中公司经营区域5676万千瓦，成为全球风电规模最大、发展最快的电网；公司累计建成太阳能汇集站容量621万千伏安，并网线路1000公里，公司经营区域并网光伏发电装机达333万千瓦。</p>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积极构建清洁能源开发利用、高效配置、安全运营的坚强平台，支持大型可再生能源基地建设和分布式能源创新发展。</p>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积极配合大型水电项目建设，构建联接我国西南水电基地和东中部负荷中心的能源大通道，提升电网配置和消纳水电的能力。 加强风电并网标准、规范和关键技术研究，加快风电场配套工程建设力度，促进风电快速发展。</p>
   					<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持分布式光伏发电， 提供优惠并网条件、完善并网服务管理、健全技术标准体系、简化接入技术要求、加快配套电网建设，确保光伏发电及时并网、可靠输送和全额收购。</p>
   				</div>
   				<div id="tab4">
   					<h3>机构设置</h3><hr/>
   					<img alt="机构设置图" src="img/organization.jpg">
   				</div>
   				<div id="tab5">
   					<h3>个人信息修改</h3><hr/>
   				</div>
   				<div id="tab6">
   					<div id="container_inner">
				  		<h3>版权说明</h3><hr/>
				  		<div id="content">			
							<p><b>条例一：</b>本系统采用B/S架构，处于业界领先地位。页面部署合理，策略配置灵活，具有上手快，操作简单、安全稳定等特点。</p>				
							<p><b>条例二：</b>主要用于对电力系统中的终端进行管理和监控，保证行业信息安全。</p>
							<p><b>条例三：</b>浏览器端的用户请在操作完成之后，点击安全退出来保证退出时会话被安全删除。</p>
							<p><b>条例四：</b>本系统需要配合客户端一起使用。客户端登陆时采集信息主动传送到服务器进行登记。处于当前页面中的用户，在点击特点查看选项之后，后台将命令传送给客户端。</p>
							<p><b>条例五：</b>本系统UI部分尚处于初级美化阶段，但是相应的功能基本实现。不影响模型验证。</p>
							<p><b>条例六：</b>系统页面采用了jQuery和jqGrid等插件进行数据呈现，用户体验已经有一定程度的提升。</p>
						    <p><b>条例七：</b>本系统属于基本原型系统，只能在小范围内进行功能演示和原理阐述。如果需要具有工业应用强度的系统，请自行联系开发商进行研发。</p>
							<p><b>条例八：</b>系统在开发初期进行了初等架构设计，基本实现了行为监控、动态信任管理及远程证明等功能。较好的证明了在电力系统中部署可信计算平台的可行性。</p>
							<p><b>条例九：</b>在客户端使用的一个独立的应用监控软件，该软件负责数据采集、命令执行和行为监控等的实际工作。</p>
							<p><b>条例十：</b>本系统一切解释权归华中科技大学信息安全与保密实验室所有。</p>
				  		</div>
				  		<div id="copyright">
				  			<div id="info">
				  				<span><b>开发单位：</b>信息安全与保密实验室</span><br/>
				  				<span><b>开发时间：</b>2012-9至2013-5</span>
				  			</div>
				  		</div>
				  	</div>
   				</div>
   			</div>
   		</div>
   		<div style="clear: both;"></div>
   		<div id="footer">
   			<span>湖北省-武汉市-华中科技大学南一楼-信息安全与保密实验室</span><br/>
   			<span>邮编：430074         技术支持:524298891</span><br>
   			<span>All Rights Reserved.&nbsp;2012-2015&copy;</span>
   		</div>
   		<div class="alterPasswdDialog">
  			<p class="validateTips" style="text-align: center">所有字段都需要填充.</p>
  			<label for="oldPasswd">旧密码:</label>
  			<input type="password" id="oldPasswd" name="oldPasswd" class="text ui-widget-content ui-corner-all"><br/>
  			<label for="newPasswd">新密码:</label>
  			<input type="password" id="newPasswd" name="newPasswd" class="text ui-widget-content ui-corner-all"><br/>
  			<label for="repeatPasswd">确认新密码:</label>
  			<input type="password" id="repeatPasswd" name="repeatPasswd" class="text ui-widget-content ui-corner-all">
  		</div>
   	</div>
  </body>
</html>
