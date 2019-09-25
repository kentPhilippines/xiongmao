<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../common/highcharts.jsp"%>
<html>
<head>
<meta charset="utf-8">
<title>layuiAdmin 控制台主页一</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<%@include file="../../common/common.jsp"%>
<body layadmin-themealias="default" style="  margin-top: 10px; ">
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md8">
				<div class="layui-row layui-col-space15">
					<div class="layui-col-md12">
						<div class="layui-card">
							<div class="layui-card-header"><strong>数据概览</strong></div>
							<div class="layui-card-body">
								<div class="layui-carousel layadmin-carousel layadmin-dataview"
									data-anim="fade" lay-filter="LAY-index-dataview"
									lay-anim="fade" lay-indicator="inside" lay-arrow="none"
									style="width: 100%; height:300px;">
										<div id="container" style="min-width:400px;height:300px"></div>
								</div>
							</div>
						</div>
						<div class="layui-card">
							<div class="layui-tab layui-tab-brief layadmin-latestData">
								<div class="layui-tab-content">
									<div class="layui-card">
									<div class="layui-form layui-card-header layuiadmin-card-header-auto"  >
										<div class="layui-form-item">
											<div class="layui-inline">
												<label class="layui-form-label">流水单号</label>
												<div class="layui-input-block">
													<input type="text" name="orderRunId" placeholder="请输入流水单号" autocomplete="off" class="layui-input">
												</div>
											</div>
											<div class="layui-inline">
											    <label class="layui-form-label">流水类型</label>
											    <div class="layui-input-block">
											      <select name="runType" lay-filter="aihao"><!-- 不能这么写,后期 优化  -->
											        <option value="" >全部</option>
											        <option value="1" >交易</option>
											        <option value="2" >系统加款</option> 
											        <option value="4" >系统扣款</option>
											        <option value="5" >代付</option> 
											        <option value="7" >冻结</option> 
											        <option value="8" >解冻</option> 
											        <option value="10" >代付冻结</option> 
											      </select>
											      </div>
											</div>
											<div class="layui-inline">
										      <label class="layui-form-label">日期范围</label>
										      <div class="layui-input-inline">
										        <input type="text" class="layui-input" id="createTime"  name = "createTime"  placeholder=" - ">
										      </div>
										    </div>
											<div class="layui-inline">
												<button class="layui-btn != " lay-submit=""
													lay-filter="LAY-user-back-search">
													<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
												</button>
											</div>
										</div>
									</div>
									<table id="LAY-user-back-manage" url = "${ctx}/manage/account/orderRunList" lay-filter="LAY-user-back-manage"></table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
			<div class="layui-col-md4">
				<div class="layui-card">
					<div class="layui-card-header">版本信息</div>
						<div class="layui-card-body layadmin-takerates" style="background-color: #e6e9e8;">
							<p>内测版：0.1V</p>
						</div>
				</div>
				<div class="layui-card">
					<div class="layui-card-header">资金报告</div>
						<div class="layui-card-body layadmin-takerates" style="background-color: #e6e9e8;">
								<p style=" margin-top: 5px; "><strong>可取现金额：${cash}</strong></p>
				           		<div class="layui-progress layui-progress-big">
								  <div class="layui-progress-bar layui-bg-red" lay-percent="${casePercent}%"></div>
								</div>
								<p style=" margin-top: 5px; "><strong>冻结总额：${freeze}</strong></p>
				           		<div class="layui-progress layui-progress-big">
								  <div class="layui-progress-bar layui-bg-blue" lay-percent="${freezePercent}%"></div>
								</div>
						</div>
				</div>

				<div class="layui-card">
					<div class="layui-card-header">实时监控</div>
						<div class="layui-card-body layadmin-takerates" style="background-color: #e6e9e8;">
						<p style=" margin-top: 5px; "><strong>订单未结算率${orderOhPercent}%</strong></p>
				           <div class="layui-progress layui-progress-big">
								  <div class="layui-progress-bar" lay-percent="${orderOhPercent}%"></div>
							</div>
						<c:forEach items="${dataPayType}" var="payType">
								<p  style=" margin-top: 5px; "><strong>${payType.payType}成功率${payTypeNumber}%</strong></p>
				           		<div class="layui-progress layui-progress-big">
								  <div class="layui-progress-bar" lay-percent="${payTypeNumber}%"></div>
								</div>
						</c:forEach>		
								<!-- <p>银联扫码20%</p>
				           		<div class="layui-progress layui-progress-big">
								  <div class="layui-progress-bar" lay-percent="50%"></div>
								</div>
								<p>微信20%</p>
				           		<div class="layui-progress layui-progress-big">
								  <div class="layui-progress-bar" lay-percent="50%"></div>
								</div> -->
								
								
						</div>
				</div>

				<div class="layui-card">
					<div class="layui-card-header">产品动态</div>
					<div class="layui-card-body" style="background-color: #e6e9e8;">
							<div>
								<p>这里展示产品的相关信息</p>
							</div>
					</div>
				</div>

				<div class="layui-card">
					<div class="layui-card-header">
						大家一起来赚钱<i class="layui-icon layui-icon-tips" lay-tips="要支持的噢"
							lay-offset="5"></i>
					</div>
					<div class="layui-card-body layui-text layadmin-text">
					</div>
				</div>
			</div>
	</div>
</body>
</html>
<script type="text/javascript" >
var t1 = '${t1Percent}'
var d1 = '${d1Percent}'
var casePercent = '${casePercent}'
layui.use('element', function(){
	  var element = layui.element;
	});
Highcharts.chart('container', {
	chart: {
		plotBackgroundColor: null,
		plotBorderWidth: null,
		plotShadow: false,
		type: 'pie'
	},
	title: {
		text: '账户交易金额分布图'
	},
	tooltip: {
		pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	},
	plotOptions: {
		pie: {
			allowPointSelect: true,
			cursor: 'pointer',
			dataLabels: {
				enabled: true,
				format: '<b>{point.name}</b>: {point.percentage:.1f} %',
				style: {
					color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
				}
			}
		}
	},
	series: [{
		name: '金额占比',
		colorByPoint: true,
		data: [ {
			name: 'T1冻结',
			y: parseInt(t1)
		}, {
			name: 'D1冻结',
			y: parseInt(d1)
		},  {
			name: '可取现余额',
			y:  parseInt(casePercent),
			sliced: true,
			selected: true
			
		}]
	}]
});
$(function(){
	RunOrderClas.init();
})
layui.use('table', function(){
	  var table = layui.table;
})
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  laydate.render({
		    elem: '#createTime'
		    ,range: true
		  });
})
</script>
<script type="text/html" id="runType">
			{{#  if(d.runType == '1'){ }}
				<span class="label radius" >交易</span>
			{{# }else if(d.runType == '2'){ }}
				<span class="label label-success radius" style="background-color:red;">系统加款</span>
			{{# }else if(d.runType == '3'){ }}
				<span class="label label-success radius"style="background-color:red;" >交易手续费</span>
			{{# }else if(d.runType == '4'){ }}
				<span class="label label-success radius" style="background-color:red;">系统扣款</span>
			{{# }else if(d.runType == '5'){ }}
				<span class="label label-success radius" >代付</span>
			{{# }else if(d.runType == '6'){ }}
				<span class="label label-success radius"style="background-color:red;">代付手续费</span>
			{{# }else if(d.runType == '7'){ }}
				<span class="label label-success radius" >冻结</span>
			{{# }else if(d.runType == '8'){ }}
				<span class="label label-success radius" >解冻</span>
			{{# }else if(d.runType == '9'){ }}
				<span class="label label-success radius" >代付手续费冻结</span>
			{{# }else if(d.runType == '10'){ }}
				<span class="label label-success radius" >代付冻结</span>
			{{# } }}
</script>
<script type="text/html" id="runStatus">
			{{#  if(d.runStatus == '1'){ }}
				<span class="label radius" >自然处理</span>
			{{# }else{  }}
				<span class="label label-danger radius" style="background-color: #61f54f;color: #fff">人工操作</span>
			{{# } }}
</script>
<script type="text/javascript" src="${ctx}/static/js/manage/run/runOrder.js" ></script>
<script type="text/javascript" src="${ctx}/static/js/manage/account/highcharAppAccount.js">
