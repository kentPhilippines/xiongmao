<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>银行卡流水</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<%@include file="../../common/common.jsp"%>
<body layadmin-themealias="default" style=" background-color: #c4c0c7;">
	<div class="layui-fluid" style="padding: 15px;">
		<div class="layui-card"  style="padding: 15px;">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="height:80px">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">出账卡号</label>
						<div class="layui-input-block">
							<input type="text" name="bankD" placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">入账卡号</label>
						<div class="layui-input-block">
							<input type="text" name="bankR" placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
				<div class="layui-inline">
					    <label class="layui-form-label">回款状态</label>
					    <div class="layui-input-block">
					      <select name="bankStatus" lay-filter="aihao"><!-- 不能这么写,后期 优化  -->
					        <option value="" >全部</option> 
					        <option value="1" >成功</option>
					        <option value="2" >失败</option> 
					        <option value="3" >处理中</option> 
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
			  <div class="layui-card-body">
			  	<div style="padding-bottom: 10px;">
					<button class="layui-btn layuiadmin-btn-admin" data-type="add" addUrl = "${ctx}/manage/bankCard/backbankAdd">回款</button>
				</div>
				<table id="LAY-user-back-manage" url = "${ctx}/manage/bankCard/MyBackbankAmountList" lay-filter="LAY-user-back-manage"></table>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/static/js/manage/bankCard/bankCard.js" ></script>
	<script type="text/html" id="bankStatus">
			{{#  if(d.bankStatus == '1'){ }}
				<span class="label label-danger radius">成功</span>
			{{# }else if(d.bankStatus == '2'){ }}
				<span class="label label-danger radius" style="background-color: #b0a8a6">失败</span>
			{{# }else if(d.bankStatus == '3'){ }}
				<span class="label label-danger radius" style="background-color: #c0b431">处理中</span>
			{{# } }}
</script>
<script>
layui.use('table', function(){
	  var table = layui.table;
})
$(function(){
	MyBackBankAmountClas.init();
})
</script>