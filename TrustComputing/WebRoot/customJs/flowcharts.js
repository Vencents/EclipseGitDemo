
//global variable to specify the user ID
var userId = 1;
//global chart variable 
var chart;

$(document).ready(function(){
	$("#list_users_flow").jqGrid({
		url: "ManagerInfo",
		mtype: "POST",
		postData: {type: "multi"},
		datatype: "json",
		colNames: ["用户ID", "用户名称", "用户类型", "在线状态", "实时流量"],
		colModel: [{name: "userId", index: "userId", width: 20, sortable: true, sorttype: "int", editable: false, align: "center"},
				   {name: "userName", width: 30, sortable: true, align: "center", editable: true, editrules: {required: true}},
				   {name: "userType", width: 20, sortable: false, align: "center", editable: true, editrules: {required: true}},
				   {name: "onlineState", width: 20, sortable: false, align: "center", editable: false},
				   {name: "realTimeFlow", width: 30, sortable: false, align: "center"}],
		pager: $("#list_users_flow_pager"),
		rowNum: 20,
		rowList: [20, 25, 30],
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
		caption: "终端用户流量详情",
		gridComplete: function(){ //add button to see flow in real time for each row.
			var id_arr = $("#list_users_flow").jqGrid('getDataIDs');
			for(var i = 0; i < id_arr.length; i++){
				var id = id_arr[i];
				var btn = "<input type='button' class='btn-small btn-success' value='查看' onclick='viewFlow()'>";
				$("#list_users_flow").jqGrid('setRowData', id, {realTimeFlow: btn});
				var state = $("#list_users_flow").getCell(id, 'onlineState');
				if(state == 0){
					$("#list_users_flow").jqGrid('setRowData', id, {onlineState: "<span class='label'>离线</span>"});
				}
				else{
					$("#list_users_flow").jqGrid('setRowData', id, {onlineState: "<span class='label label-success'>在线</span>"});			
				}
				var type = $("#list_users_flow").getCell(id, 'userType');
				if(type == 2){
					$("#list_users_flow").jqGrid('setRowData', id, {userType: "<span class='label label-important'>管理员</span>"});
				}
				else if(type == 1){
					$("#list_users_flow").jqGrid('setRowData', id, {userType: "<span class='label label-warning'>超级用户</span>"});
				}
				else{
					$("#list_users_flow").jqGrid('setRowData', id, {userType: "<span class='label label-success'>普通用户</span>"});
				}
			}
		},
	}).navGrid("#list_users_flow_pager", {search: false, add: false, edit: false, del: false});

	Highcharts.theme = {
		colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
		chart: {
			 backgroundColor: {
			     linearGradient: [0, 0, 500, 500],
			     stops: [
			            [0, 'rgb(255, 255, 255)'],
			            [1, 'rgb(240, 240, 255)']
			         ]
			      },
			      plotShadow: true,
			   },
		title: {
			 style: {
			     color: '#000',
			     font: 'bold 18px Verdana, sans-serif'
			   }
			 },
		xAxis: {
			 gridLineWidth: 1,
			 lineColor: '#000',
			 tickColor: '#000',
			 labels: {
			     style: {
			            color: '#000',
			            font: '14px Verdana, sans-serif'
			         }
			 },
			 title: {
			 	style: {
			 		color: '#333',
			        fontWeight: 'bold',
			        fontSize: '14px',
			        fontFamily: 'Verdana, sans-serif'
			    	}
			 	}
			},
		yAxis: {
			minorTickInterval: 'auto',
			lineColor: '#000',
			lineWidth: 1,
			tickWidth: 1,
			tickColor: '#000',
			labels: {
				style: {
				color: '#000',
				font: '14px Verdana, sans-serif'
				}
			}
		},
		legend: {
			itemStyle: {
				font: '9pt Trebuchet MS, Verdana, sans-serif',
				color: 'black'
			},
			itemHoverStyle: {
				color: '#039'
			},
			itemHiddenStyle: {
				color: 'gray'
			}
		},
		labels: {
			style: {
			color: '#99b'
			}
		}
	};
	// Apply the theme
	var highchartsOptions = Highcharts.setOptions(Highcharts.theme);
	Highcharts.setOptions({
		global: {
			useUTC: false
		}
	});
    chart = new Highcharts.Chart({
    	chart: {
    		renderTo: 'container',
    		type: 'spline',
    		marginRight: 10,
    		events:{
    			load: function (){
    				setInterval(function(){
    					var seriesBuisiness = chart.series[0];   /* Buisiness data flow */
						var seriesNonBuisiness = chart.series[1]; /* NonBuisiness data flow */
						var obj;
						var arr = [];
						var x = (new Date()).getTime();
    				   	$.ajax({
							url: 'FlowInfo?userId='+userId,
							type: 'POST',
							datatype: 'json',
							success: function(points){
								obj = JSON.parse(points);
								$.each(obj, function(i, v){
									arr.push(v);
								});
								seriesBuisiness.addPoint([x, arr[0]], false, true); 
								seriesNonBuisiness.addPoint([x, arr[1]], true, true);
							},
	    				});
					}, 1000);
    			}
    		}
    	},
    	title: {
    		text: '实时流量监控图'
    	},
    	subtitle: {
    		text: '当前用户编号: 0' + userId,
    		style: {
        		color: '#666666',
     	    	font: 'bold 12px Verdana, sans-serif'
     	    }
    	},
    	xAxis: {
    		type: 'datetime',
    		tickPixelInterval: 100,
    	},
    	yAxis: {
    		title: {
    			text: '流量刻度'
    		},
    		labels: {
    			formatter: function(){
    				return this.value + 'KB';
    			},
    			style: {
    				color: '#89A54E'
    			}
    		},
    		plotlines: [{
    			value: 0,
    			width: 1,
    			color: '#808080',
    		}]
    	},
    	tooltip: {
    		crosshairs: true,
    		shared: true,
    	},
    	legend: {
    		backgroundColor: '#FFFFFF',
    		floating: true,
    		verticalAlign: 'top',
    		x: 90,
    		y: 45,
    				
    		labelFormatter: function(){
    			return this.name;
    		}
    	},
    	credits: {
    		enabled: false
    	},
    	exporting: {
    		enabled: false,
    	},
    	series: 
    	[{	
	    	name: '业务流量',
	    	type: 'spline',
	    	data: (function() {
		    	// generate an array of random data
		        var data = [],
		        time = (new Date()).getTime(),
		        i;
		        for (i = -14; i <= 0; i++) {
		        	data.push({
		        		x: time + i * 1000,
		                y: Math.random(),
		            });
		        }
		        return data;
		     })()
    	},
    	{
    		name: '非业务流量',
    		type: 'spline',
    		data: (function() {
		    	// generate an array of random data
		        var data = [],
		            time = (new Date()).getTime(),
		            i;
		        for (i = -14; i <= 0; i++) {
		        	data.push({
		            	x: time + i * 1000,
		                y: Math.random()
		        });
		    }
		    return data;
		})()
    	}]
    });
});

function viewFlow(){
	var rowId = $("#list_users_flow").jqGrid('getGridParam','selrow');
	if(rowId == null){
		alert("请先选择特定行，并确保您选择的是新的一行！");
	}
	else{
		userId = $("#list_users_flow").getCell(rowId, 'userId');
		chart.setTitle({text: '实时流量监控图: ' + userId + '号终端'});
	}
}