Highcharts.chart('container',{
	chart: {
		type: 'column'
	},
	title: {
		text: '月交易汇总'
	},
	subtitle: {
		text: '数据来源: 虹·支付'
	},
	xAxis: {
		categories:time,
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
	series: sumDate
});
 Highcharts.chart('container1',{
	chart: {
		type: 'column'
	},
	title: {
		text: '产品交易汇总'
	},
	subtitle: {
		text: '数据来源: 虹·支付'
	},
	xAxis: {
		categories:productTimeList,
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
	series: productList
});
 Highcharts.chart('container2',{
	 chart: {
		 type: 'column'
	 },
	 title: {
		 text: '渠道交易情况汇总'
	 },
	 subtitle: {
		 text: '数据来源: 虹·支付'
	 },
	 xAxis: {
		 categories:channelTimeList,
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
	 series: channnelDate 
 });
 Highcharts.chart('container3',{
	 chart: {
		 type: 'column'
	 },
	 title: {
		 text: '用户交易情况汇总'
	 },
	 subtitle: {
		 text: '数据来源: 虹·支付'
	 },
	 xAxis: {
		 categories:accounttimeList,
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
	 series:  accountDate 
 });