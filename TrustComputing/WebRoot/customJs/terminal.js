$(document).ready(function(){
	$("#list_terminal_details").jqGrid({
		url: 'PlatformInfo',
		datatype: 'json',
		mtype: 'POST',
		colNames: ["用户ID", "终端类型", "终端名称", "IP地址/端口号", "操作系统类型", "CPU主频", "内存容量", "网卡", "权限"],
		colModel: [ {name: "userId", width: 25, sortable: true, align: "center", editable: false},
					{name: "termType", width: 25, sortable: true, align: "center", editable: true, editrules: {required: true}},
					{name: "termName", width: 40, sortable: false, align: "center", editable: true, editrules: {required: true}},
					{name: "termIP", width: 30, sortable: false, align: "center", editable: false},
					{name: "termOS", width: 20, sortable: false, align: "center", editable: false},
					{name: "CPUinfo", width: 30, sortable: false, align: "center", editable: true},
					{name: "MEMinfo", width: 30, sortable: false, align: "center", editable: false},
					{name: "NICinfo", width: 30, sortable: false, align: "center", editable: true},
					{name: "perm", width: 20, sortable: false, align: "center", editable: false}],
		jsonReader: {
			root: "realData",
			page: "curPage",
			total: "totalPages",
			root: "realData",
			repeatitems: false,
		},
		rowNum: 20,
		pager: $("#list_terminal_details_pager"),
		altRows: true,  //zebra-striped grid
		forceFit: true, //auto ajust width of adjacent columns
		height: 445,
		width: 1135,
		viewrecords: false,
		hidegrid: false, //show a hide/show button on caption layer
		loadtext: "正在加载数据...",  //loading status
		caption: "终端平台综合信息",
		onSelectRow: function(ids) {
				var rowId = $("#list_terminal_details").jqGrid('getGridParam','selrow');
				ids = $("#list_terminal_details").getCell(rowId, 'userId');
				$("#list_terminal_process").jqGrid('setGridParam',{url:"ProcessInfo?userId=" + ids, page:1});
				$("#list_terminal_process").jqGrid('setCaption',"终端: "+ ids + "的进程映像").trigger('reloadGrid');
		}
	}).navGrid('#list_terminal_details_pager',{add:false,edit:false,del:false});
	
	$("#list_terminal_process").jqGrid({
		url: 'ProcessInfo?userId=0',
		datatype: 'json',
		mtype: 'POST',
		colNames: ["进程ID", "进程名称", "占用内存", "占用CPU", "所属用户组"],
		colModel: [{name: "procId", width: 25, sortable: true, align: "center", editable: true, editrules: {required: true}},
					{name: "procName", width: 40, sortable: false, align: "center", editable: true, editrules: {required: true}},
					{name: "usedMem", width: 20, sortable: false, align: "center", editable: false},
					{name: "usedCPU", width: 20, sortable: false, align: "center", editable: false},
					{name: "procOwner", width: 30, sortable: false, align: "center", editable: false}],
		jsonReader: {
			root: "realData",
			page: "curPage",
			total: "totalPages",
			records: "totalRecords",
			repeatitems: false,
		},
		prmNames: {search: "search"},
		pager: $("#list_terminal_process_pager"),
		viewrecords: true,
		altRows: true,  //zebra-striped grid
		forceFit: true, //auto ajust width of adjacent columns
		height: 445,
		width: 1135,
		rowNum: 20,
		hidegrid: false, //show a hide/show button on caption layer
		loadtext: "正在加载数据...",  //loading status
		caption: "进程映像",
	}).navGrid('#list_terminal_process_pager',{add:false,edit:false,del:false});
	
	$("#list_terminal_remote").jqGrid({
		url: "ManagerInfo",
		mtype: "GET",
		datatype: "json",
		colNames: ["用户ID", "上次登录时间", "在线状态", "状态控制"],
		colModel: [{name: "userId", width: 15, sortable: true, align: "center", editable: true, editrules: {required: true}},
				   {name: "lastLogin", width: 40, sortable: false, align: "center", editable: false},
				   {name: "onlineState", width: 20, sortable: false, align: "center", editable: false},
				   {name: "stateControl", width: 35, sortable: false, align: "center", editable: false}],
		pager: $("#list_terminal_remote_pager"),
		rowNum: 20,
		height: 350,  //the height of the grid
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
		hidegrid: false, //show a hide/show button on caption layer
		loadtext: "正在努力加载数据...",  //loading status
		caption: "终端状态控制",
		gridComplete: function(){ //add button to see flow in real time for each row.
			var id_arr = $("#list_terminal_remote").jqGrid('getDataIDs');
			for(var i = 0; i < id_arr.length; i++){
				var id = id_arr[i];
				var btn = "<input type='button' class='btn-small btn-danger' value='强制下线' onclick='forceOffline()'>";
				$("#list_terminal_remote").jqGrid('setRowData', id, {stateControl: btn});
				var state = $("#list_terminal_remote").getCell(id, 'onlineState');
				if(state == 0){
					$("#list_terminal_remote").jqGrid('setRowData', id, {onlineState: "<span class='label'>离线</span>"});
				}
				else{
					$("#list_terminal_remote").jqGrid('setRowData', id, {onlineState: "<span class='label label-success'>在线</span>"});			
				}
			}
		},
		
	}).navGrid('#list_terminal_remote_pager',{add:false,edit:false,del:false});
});

function forceOffline(){
	var rowId = $("#list_terminal_remote").jqGrid('getGridParam','selrow');
	if(rowId == null){
		alert("请先选择特定行，并确保您选择的是新的一行！");
	}
	else{
		var id = $("#list_terminal_remote").getCell(rowId, 'userId');
		//window.location.href='reviewChart.html?userId='+userId;
		//send ajax request to session servlet
		$.post(
			"ChangeState",
			{userId: id},
			function(data){
				if(data == 0){ //the user is online currently.
					$("#list_terminal_remote").jqGrid('setRowData', rowId, {onlineState: "<span class='label'>离线</span>"});
				}
				else{
					alert("对不起，该用户已经处于离线状态");
				}
			}
		);
	}
}

$("#settings").submit(function(event){
	event.preventDefault();
	var type = $("#selectType").val();
	var capture = $("#selectCapture").val();
	var paste = $("#selectPaste").val();
	var login = $("#selectLogin").val();
	var ratio = $("#selectRatio").val();
	var ratioVal = $("#inputWarning").val();
	$.ajax({
		type: "POST",
		url: "TrustSettings",
		datatype: "json",
		cache: false,
		data: {stype: type, scapture: capture, spaste: paste, slogin: login, sratio: ratio, svalue: ratioVal},
		success: function(data){
			if(data==1){
				$("#tips").removeClass("alert-info").addClass("alert-success").html("提示：信任参数已经成功保存！");
			}else{
				$("#tips").removeClass("alert-info").addClass("alert-error").html("警告：参数保存失败，请稍后再尝试！");
			}
		}
	});
});