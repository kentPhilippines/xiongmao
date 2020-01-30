 Highcharts.chart('container1',{
	chart: {
		type: 'column'
	},
	title: {
		text: '交易笔数交易汇总'
	},
	subtitle: {
		text: '数据来源: 虹·支付'
	},
	xAxis: {
		categories:accountCountTimeList,
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
	series: accountCountDate
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
		 categories:accountAmountTimeList,
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
	 series:  accountAmountDate 
 });