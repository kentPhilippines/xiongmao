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
			<div class="layui-form layui-card-header layuiadmin-card-header-auto" style="height:120px">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">出账卡号</label>
						<div class="layui-input-block">
							<input type="text" name="bankCard" placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">入账卡号</label>
						<div class="layui-input-block">
							<input type="text" name="bankCard" placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">出账账号</label>
						<div class="layui-input-block">
							<input type="text" name="cardRunD" placeholder="请输入出账账号" autocomplete="off" class="layui-input">
						</div>
					</div> 
					<div class="layui-inline">
						<label class="layui-form-label">入账账号</label>
						<div class="layui-input-block">
							<input type="text" name="cardRunW" placeholder="请输入入帐账号" autocomplete="off" class="layui-input">
						</div>
					</div> 
					<div class="layui-inline">
						<button class="layui-btn != " lay-submit=""
							lay-filter="LAY-user-back-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
					</div>
				</div>
				<div class="layui-form-item">
				<div class="layui-inline">
					    <label class="layui-form-label">流水类型</label>
					    <div class="layui-input-block">
					      <select name="runType" lay-filter="aihao"><!-- 不能这么写,后期 优化  -->
					        <option value="" >全部</option> 
					        <option value="1" >商户交易</option>
					        <option value="2" >卡商回款</option> 
					        <option value="3" >账户入款分润</option> 
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
				<table id="LAY-user-back-manage" url = "${ctx}/manage/bankCard/bankCardRunList" lay-filter="LAY-user-back-manage"></table>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="${ctx}/static/js/manage/bankCard/bankCard.js" ></script>
<script type="text/html" id="bankType">
			{{#  if(d.bankType == '0'){ }}
				<span class="label radius" style="background-color:#f9c248;">收款卡</span> 
			{{# }else if(d.bankType == '1'){ }}
				<span class="label label-success radius"  >中转卡</span>
			{{# }else if(d.bankType == '2'){ }}
				<span class="label label-success radius" style="background-color:red;color：#fff">出款卡</span>
			{{# }else if(d.bankType == '3'){ }}
				<span class="label label-success radius"  >冻结卡</span>
			{{# }else if(d.bankType == '4'){ }}
				<span class="label label-success radius" >测试卡</span>
			{{# } }}
</script>
<script>
layui.use('table', function(){
	  var table = layui.table;
})
$(function(){
	BankCardClas.init();
})
</script>