$(document).ready(function(){
	$("#viewInfo").click(function(){
		var name = $("#adminName").text();
		$.ajax({
			type: "POST",
			url: "ManagerInfo",
			data: {userName: name, type: "single"},
			cache: false,
			datatype: "json"
		}).done(function(jsonStr){
			var data = eval("(function(){return " + jsonStr + ";})()");
			$("#name").text(data.userName);
			if(data.trustingState == 'trusted')
				$("#state").html('平台可信');
			else
				$("#state").html('平台不可信');
			$("#level").html(data.trustLevel);
			$("#value").html(data.trustValue);
			if(data.userType == '2')
				$("#type").html('管理员');
			else
				$("#type").html('超级用户');
			$("#last").html(data.lastLogin);
		});
	});
	
	$("#list_manager_log").jqGrid({
		url: "SystemLogger",
		mtype: "POST",
		datatype: "json",
		colNames: ["编号", "操作时间", "用户ID", "用户名", "操作内容"],
		colModel: [{name: "itemNumber", width: 10, sortable: true, editable: false, align: "center"},
				   {name: "operTime", width: 35, sortable: false, editable: false, align: "center"},
				   {name: "userId", width: 15, sortable: true, align: "center", editable: true, editrules: {required: true}},
				   {name: "userName", width: 25, sortable: false, align: "center", editable: true, editrules: {required: true}},
				   {name: "operDesc", width: 45, sortable: false, align: "left", editable: false}],
		pager: $("#list_manager_log_pager"),
		rowNum: 20,
		rowList: [20, 25, 30],
		height: 445,  //the height of the grid
		width: 1135,  //the width of the grid
		postData: {type: "single"},
		jsonReader: {
			root: "realData",
			page: "curPage",
			total: "totalPages",
			records: "totalRecords",
			repeatitems: false,
		},
		viewrecords: false,
		forceFit: true, //auto ajust width of adjacent columns
		hidegrid: false, //show a hide/show button on caption layer
		loadtext: "正在加载数据...",  //loading status
		caption: "系统日志详情"
	}).navGrid("#list_manager_log_pager", {search: false, add: false, edit: false, del: true});
});
