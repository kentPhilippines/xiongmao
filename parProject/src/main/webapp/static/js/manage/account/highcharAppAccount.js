var chart = null;
$.getJSON('myUserDealShowForAgent', function (data) {
	chart = Highcharts.chart('container', {
		chart: {
			zoomType: 'x'
		},
		title: {
			text: '每日交易趋势图'
		},
		subtitle: {
			text: document.ontouchstart === undefined ?
			'鼠标拖动可以进行缩放' : '手势操作进行缩放'
		},
		xAxis: {
			type: 'datetime',
			dateTimeLabelFormats: {
				millisecond:"%Y-%m-%d, %H:%M:%S",
				second:"%Y-%m-%d, %H:%M:%S",
				minute:"%Y-%m-%d, %H:%M",
				hour:"%Y-%m-%d, %H:%M",
				day:"%Y-%m-%d, %H",
				month:"%Y-%m",
				year:"%Y"
			}
		},
		tooltip: {
			dateTimeLabelFormats: {
				millisecond:"%Y-%m-%d, %H:%M:%S",
				second:"%Y-%m-%d, %H:%M:%S",
				minute:"%Y-%m-%d, %H:%M",
				hour:"%Y-%m-%d, %H:%M",
				day:"%Y-%m-%d, %H",
				month:"%Y-%m",
				year:"%Y"
			}
		},
		yAxis: {
			title: {
				text: '金额'
			}
		},
		legend: {
			enabled: false
		},
		plotOptions: {
			area: {
				fillColor: {
					linearGradient: {
						x1: 0,
						y1: 0,
						x2: 0,
						y2: 1
					},
					stops: [
						[0, Highcharts.getOptions().colors[7]],
					]
				},
				marker: {
					radius: 2
				},
				lineWidth: 1,
				states: {
					hover: {
						lineWidth: 1
					}
				},
				threshold: null
			}
		},
		credits: { 
			enabled: false //不显示LOGO 
			},
		exporting: {
			enabled: false//隐藏导出
			},
		series: [{
			type: 'area',
			name: '交易金额',
			data: data
		}]
	});
});
Highcharts.chart('container1',{
	chart: {
		type: 'column'
	},
	title: {
		text: '日交易次数汇总'
	},
	subtitle: {
		text: '数据来源: 未来支付'
	},
	xAxis: {
		categories:timeList,
		crosshair: true
	},
	yAxis: {
		min: 0,
		title: {
			text: '单次'
		}
	},
	tooltip: {
		// head + 每个 point + footer 拼接成完整的 table
		headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		'<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
		footerFormat: '</table>',
		shared: true,
		useHTML: true
	},
	plotOptions: {
		column: {
			borderWidth: 0
		}
	},
	series: [{
		name: '成功交易笔数（笔）',
		data:  dealDaySuList
	},  {
		name: '发起交易笔数（笔）',
		data: dealDayList
	} ]
});
 Highcharts.chart('container2',{
	 chart: {
		 type: 'column'
	 },
	 title: {
		 text: '日交易金额汇总'
	 },
	 subtitle: {
		 text: '数据来源: 未来支付'
	 },
	 xAxis: {
		 categories:timeList,
		 crosshair: true
	 },
	 yAxis: {
		 min: 0,
		 title: {
			 text: '金额'
		 }
	 },
	 tooltip: {
		 // head + 每个 point + footer 拼接成完整的 table
		 headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		 pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		 '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
		 footerFormat: '</table>',
		 shared: true,
		 useHTML: true
	 },
	 plotOptions: {
		 column: {
			 borderWidth: 0
		 }
	 },
	 series: [ {
		 name: '成功交易金额（元）',
		 data:  dealDayMoneySuList
	 },  {
		 name: '发起交易交易金额（元）',
		 data:  dealDayMoneyList
	 }]
 });
 var chart = Highcharts.chart('container3',{
		chart: {
			type: 'column'
		},
		title: {
			text: '交易增长率'
		},
		yAxis: {
			allowDecimals: false,
			title: {
				text: '百分比',
				rotation: 0
			}
		},
		xAxis: {
			categories: ['当前交易日同上一交易日金额同比增长', '今日交易笔数同比增长', '今日成功交易金额同比增长', '今日成功笔数同比增长']
		},
		series: [{
			name: '同比增长',
			data: sum
		} ]
	});