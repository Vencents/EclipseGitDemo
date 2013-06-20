var chart_column;
$(document).ready(function(){

	Highcharts.theme = {
	   colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4'],
	   chart: {
	      backgroundColor: {
	         linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
	         stops: [
	            [0, 'rgb(255, 255, 255)'],
	            [1, 'rgb(240, 240, 255)']
	         ]
	      },
	      borderWidth: 2,
	      plotBackgroundColor: 'rgba(255, 255, 255, .9)',
	      plotShadow: true,
	      plotBorderWidth: 1
	   },
	   title: {
	      style: {
	         color: '#000',
	         font: 'bold 16px "Trebuchet MS", Verdana, sans-serif'
	      }
	   },
	   subtitle: {
	      style: {
	         color: '#666666',
	         font: 'bold 12px "Trebuchet MS", Verdana, sans-serif'
	      }
	   },
	   xAxis: {
	      gridLineWidth: 1,
	      lineColor: '#000',
	      tickColor: '#000',
	      labels: {
	         style: {
	            color: '#000',
	            font: '11px Trebuchet MS, Verdana, sans-serif'
	         }
	      },
	      title: {
	         style: {
	            color: '#333',
	            fontWeight: 'bold',
	            fontSize: '12px',
	            fontFamily: 'Trebuchet MS, Verdana, sans-serif'
	
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
	            font: '11px Trebuchet MS, Verdana, sans-serif'
	         }
	      },
	      title: {
	         style: {
	            color: '#333',
	            fontWeight: 'bold',
	            fontSize: '12px',
	            fontFamily: 'Trebuchet MS, Verdana, sans-serif'
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
	   },
	
	   navigation: {
	      buttonOptions: {
	         theme: {
	            stroke: '#CCCCCC'
	         }
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
    chart_column = new Highcharts.Chart({
    	chart: {
                renderTo: 'column_container',
                type: 'column',
                events:{
	    			load: function(event){
	    				$.ajax({
							url: 'FlowRank?count=10',
							type: 'POST',
							datatype: 'json',
							success: function(data){
								
								var category = [];
								obj = JSON.parse(data);
								$.each(obj, function(index, item){
									category[index] = item.name;
									chart_column.series[0].data[index].update(item.b);
									chart_column.series[0].data[index].update(item.nb);								
								});
								chart_column.setCategories(category);
							},
		    			});
	    			}
	    		}
        },
    	title: {
    		text: '流量排名前十一览'
    	},
    	subtitle:{
    		text: '注：以累计流量为统计标准'
    	},
    	xAxis: {
    		categories: [
    			'top1',
    			'top2',
    			'top3',
    			'top4',
    			'top5',
    			'top6',
    			'top7',
    			'top8',
    			'top9',
    			'top10'
    		]
    	},
    	yAxis: {
    		min: 0,
    		title: {
    			text: "总流量(MB)"
    		}
    	},
    	tooltip: {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y} MB</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
    	},
    	plotOptions: {
    		column: {
    			pointPadding: 0.2,
    			borderWidth: 0,
    		}
    	},
    	series: [{
    		name: "业务流量",
    		data: [1,1,2,2,1,2,6,1,2,6]
    	},
    	{
    		name: "非业务流量",
    		data: [2,12,21,6,12,21,4,1,2,5]
    	}]
    });
});