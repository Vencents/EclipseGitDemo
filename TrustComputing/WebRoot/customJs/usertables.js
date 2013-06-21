$(document).ready(function(){
	$("#list_users_details").jqGrid({
				url: "ManagerInfo",
				mtype: "POST",
				datatype: "json",
				colNames: ["用户ID", "用户名", "密码", "用户信任级别", "信任值", "在线状态", "平台状态", "用户类型", "上次登录"],
				colModel: [{name: "userId", width: 10, sortable: true, sorttype: "int", editable: false, align: "center"},
						   {name: "userName", width: 30, sortable: false, align: "center", editable: true, editrules: {required: true}},
						   {name: "userPasswd", width: 30, sortable: false, align: "center", editable: true, editrules: {required: true}},
						   {name: "trustLevel", width: 20, align: "center", editable: true, editrules: {required: true, number: true, minValue: 0, maxValue: 3}},
						   {name: "trustValue", width: 20, align: "center", editable: true, editrules: {required: true, number: true, minValue: 0, maxValue: 100}},
						   {name: "onlineState", width: 20, align: "center", editable: true, edittype: "select", editoptions: {value: {0: "离线", 1:"在线"}}, editrules: {required: true}},
						   {name: "trustingState", width: 25, align: "center", editable: true, editrules:{required: true}},
						   {name: "userType", width: 20, align: "center", edittype: "select", editoptions: {value: {0:"普通用户", 1:"超级用户", 2:"管理员"}}, editable: true, editrules: {required: true}},
						   {name: "lastLogin", width: 25, align: "center", editable: true, editrules: {date: true, required: true}}
						   ],
				pager: $("#list_users_details_pager"),
				rowNum: 20,
				height: 445,  //the height of the grid
				width: 1135,  //the width of the grid
				jsonReader: {
					root: "realData",
					page: "curPage",
					total: "totalPages",
					records: "totalRecords",
					repeatitems: false,
				},
				postData: {type: "multi"},
				sortOrder: "desc",
				viewrecords: false,
				forceFit: true, //auto ajust width of adjacent columns
				hidegrid: false, //show a hide/show button on caption layer
				loadtext: "正在加载数据...",  //loading status
				caption: "终端用户基本信息",
				gridComplete: function(){ //add button to see flow in real time for each row.
					var online = ""
					var id_arr = $("#list_users_details").jqGrid('getDataIDs');
					for(var i = 0; i < id_arr.length; i++){
						var id = id_arr[i];
						var state = $("#list_users_details").getCell(id, 'onlineState');
						if(state == 0){
							$("#list_users_details").jqGrid('setRowData', id, {onlineState: "<span class='label'>离线</span>"});
						}
						else{
							$("#list_users_details").jqGrid('setRowData', id, {onlineState: "<span class='label label-success'>在线</span>"});			
						}
						var type = $("#list_users_details").getCell(id, 'userType');
						if(type == 2){
							$("#list_users_details").jqGrid('setRowData', id, {userType: "<span class='label label-important'>管理员</span>"});
						}
						else if(type == 1){
							$("#list_users_details").jqGrid('setRowData', id, {userType: "<span class='label label-warning'>超级用户</span>"});
						}
						else{
							$("#list_users_details").jqGrid('setRowData', id, {userType: "<span class='label label-success'>普通用户</span>"});
						}
						var tState = $("#list_users_details").getCell(id, 'trustingState');
						if(tState == "trusted"){
							$("#list_users_details").jqGrid('setRowData', id, {trustingState: "<span class='label label-success'>可信任</span>"});
						}
						else{
							$("#list_users_details").jqGrid('setRowData', id, {trustingState: "<span class='label'>不可信任</span>"});
						}
						var tLevel = $("#list_users_details").getCell(id, 'trustLevel');
						if(tLevel == 0){
							$("#list_users_details").jqGrid('setRowData', id, {trustLevel: "<span class='label label-success'>正常</span>"});
						}
						else if(tLevel == 1){
							$("#list_users_details").jqGrid('setRowData', id, {trustLevel: "<span class='label label-warning'>可疑</span>"});
						}
						else if(tLevel == 2){
							$("#list_users_details").jqGrid('setRowData', id, {trustLevel: "<span class='label label-important'>危险</span>"});
						}
						else{
							$("#list_users_details").jqGrid('setRowData', id, {trustLevel: "<span class='label'>禁用</span>"});
						}
					}
				},
			}).navGrid("#list_users_details_pager", {search: false, add: false, edit: false, del: true},
												  {},{},
												  {url: 'ModifyTables?userId=' + setPostData(),
												   mtype: "POST", reloadAfterSubmit: true,
												   height:120, top: 180, left: 200, modal:true});

	$("#list_users_violence").jqGrid({
		url: "ViolenceHistory",
		mtype: "POST",
		datatype: "json",
		colNames: ["用户名称", "操作类型", "操作发生时间", "操作内容值", "行为描述"],
		colModel: [{name: "userName", index: "userName", width: 20, sortable: false, editable: false, align: "right"},
				   {name: "operType", width: 15, sortable: true, align: "center", editable: true, editrules: {required: true}},
				   {name: "operTime", width: 40, sortable: false, align: "center", editable: true, editrules: {required: true}},
				   {name: "operContent", width: 20, sortable: false, align: "center", editable: false},
				   {name: "operDesc", width: 60, sortable: false, align: "left", editable: false}],
		pager: $("#list_users_violence_pager"),
		rowNum: 20,
		height: 340,  //the height of the grid
		width: 545,  //the width of the grid
		jsonReader: {
			root: "realData",
			page: "curPage",
			total: "totalPages",
			records: "totalRecords",
			repeatitems: false,
		},
		viewrecords: false,
		altRows: true,  //zebra-striped grid
		forceFit: true, //auto ajust width of adjacent columns
		loadtext: "正在努力加载数据...",  //loading status
		caption: "终端用户违规历史记录",
		gridComplete: function(){ //add button to see flow in real time for each row.
			var id_arr = $("#list_users_violence").jqGrid('getDataIDs');
			for(var i = 0; i < id_arr.length; i++){
				var id = id_arr[i];
				var type = $("#list_users_violence").getCell(id, 'operType');
				if(type == 0){
					$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label label-success'>0</span>"});
				}else if(type == 1){
						$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label label-warning'>1</span>"});
				}else if(type == 2){
					$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label label-important'>2</span>"});
				}else{
					$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label'>3</span>"});
				}
				var des = $("#list_users_details").getCell(id, 'operContent');
				if(des == 'capture'){
					$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label label-success'>使用截屏</span>"});					
				}else if(des == 'copy'){
					$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label label-warning'>使用剪贴板</span>"});
				}else if(des == 'login'){
					$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label label-important'>登陆次数过多</span>"});
				}else {
					$("#list_users_violence").jqGrid('setRowData', id, {trustLevel: "<span class='label'>流量超标</span>"});
				}
			}
		}
	}).navGrid("#list_users_violence_pager", {search: true, add: false, edit: false, del: false});
	
	$("#list_users_logs").jqGrid({
		sortable: true,
		url: "SystemLogger",
		mtype: "POST",
		postData: {type: "multi"},
		datatype: "json",
		colNames: ["编号", "操作时间", "用户ID", "用户名", "操作内容"],
		colModel: [{name: "itemNumber", width: 10, sortable: true, editable: false, align: "center"},
				   {name: "operTime", width: 35, sortable: false, editable: false, align: "center"},
				   {name: "userId", width: 15, sortable: true, align: "center", editable: true, editrules: {required: true}},
				   {name: "userName", width: 25, sortable: false, align: "center", editable: true, editrules: {required: true}},
				   {name: "operDesc", width: 45, sortable: false, align: "left", editable: false}],
		pager: $("#list_users_logs_pager"),
		rowNum: 20,
		height: 445,  //the height of the grid
		width: 1135,  //the width of the grid
		jsonReader: {
			root: "realData",
			page: "curPage",
			total: "totalPages",
			records: "totalRecords",
			repeatitems: false,
		},
		viewrecords: false,
		altRows: true,  //zebra-striped grid
		forceFit: true, //auto ajust width of adjacent columns
		hidegrid: false, //show a hide/show button on caption layer
		loadtext: "正在加载数据...",  //loading status
		caption: "系统日志详情"
	}).navGrid("#list_users_logs_pager", {search: true, add: false, edit: false, del: false});
});

$("#addUser").submit(function(event){
	event.preventDefault();
	
	var name = $("#inputname").val();
	var passwd = $("#inputpasswd").val();
	var type = $("#inputtype").val();
	var state = $("#inputstate").val();
	var level = $("#inputlevel").val();
	var value = $("#inputvalue").val();
	$.ajax({
		type: "POST",
		url: "AddUser",
		data: { userName: name, 
				userPasswd: passwd, 
				userType: type, 
				trustingState: state, 
				trustLevel: level, 
				trustValue: value},
		datatype: "json",
		cache: false,
		success: function(data){
			if(data == 1){
				$("#tips").text("温馨提示：用户信息添加成功！");
			}
			else{
				$("#tips").removeClass("alert-info").addClass("alert-warning").text("警告：操作失败，稍后再试！");
			}
		}
	});
})

$("#cancel_submit").click(function(){
	$("#inputname").empty();
	$("#inputpasswd").empty();
	$("#inputtype").val(0);
	$("#inputstate").val('untrusted');
	$("#inputlevel").val(0);
	$("#inputvalue").val(100);	
})

function setPostData() {
	var rowId = $("#list_users_details").jqGrid('getGridParam','selrow');
	var userId = $("#list_users_details").getCell(rowId, 'userId');
	return userId;
}
