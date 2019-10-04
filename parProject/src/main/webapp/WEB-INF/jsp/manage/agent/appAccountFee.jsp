<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>账户费率</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<%@include file="../../common/common.jsp"%>
<body layadmin-themealias="default" style=" background-color: #c4c0c7;">
	<div class="layui-fluid" style="padding: 15px;">
		<div class="layui-card"  style="padding: 15px;">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label" style="width: 100px">子账户编号</label>
						<div class="layui-input-block">
							<input type="text" style="width: 85%;" name="userId" placeholder="商户编号" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="width: 100px">商户渠道编号</label>
						<div class="layui-input-block">
							<input type="text" style="width: 85%;" name="accountChannel" placeholder="渠道编号" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="width: 100px">商户产品编号</label>
						<div class="layui-input-block">
							<input type="text" style="width: 85%;" name="channelProduct" placeholder="产品编号" autocomplete="off" class="layui-input">
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
			<div class="layui-card-body">
				<table id="LAY-user-back-manage" url = "${ctx}/manage/agent/accountFeeList" lay-filter="LAY-user-back-manage"></table>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/static/js/manage/account/account.js" ></script>
<script type="text/html" id="feeStautus">
			{{#  if(d.feeStautus == '1'){ }}
				<span class="label radius" >可使用</span>
			{{# }else{  }}
				<span class="label label-danger radius" style="background-color: red">不可使用</span>
			{{# } }}
</script>
<script>
layui.use('table', function(){
	  var table = layui.table;
})
$(function(){
	AccountFeeClas.init();
})
</script>