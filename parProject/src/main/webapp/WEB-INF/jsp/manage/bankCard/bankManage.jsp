<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>银行卡账户管理</title>
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
						<label class="layui-form-label">银行卡号</label>
						<div class="layui-input-block">
							<input type="text" name="bankCard" placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">卡管理员</label>
						<div class="layui-input-block">
							<input type="text" name="liabilities" placeholder="请输入" autocomplete="off" class="layui-input">
						</div>
					</div> 
					<div class="layui-inline">
						<label class="layui-form-label">银行卡别名</label>
						<div class="layui-input-block">
							<input type="text" name="bankId" placeholder="请输入" autocomplete="off" class="layui-input">
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
					<button class="layui-btn layuiadmin-btn-admin" data-type="add" addUrl = "${ctx}/manage/bankCard/bankCardAdd">添加银行卡</button>
				</div>
				<table id="LAY-user-back-manage" url = "${ctx}/manage/bankCard/bankCardList" lay-filter="LAY-user-back-manage"></table>
			</div>
		</div>
	</div>
	<script type="text/html" id="operation">
 	 <a class="layui-btn layui-btn-xs" lay-event="edit" url = "${ctx}/manage/bankCard/bankCardEditShow">编辑</a>
 	 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" url = "${ctx}/manage/bankCard/bankCardDel">删除</a>
	</script>
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
<script type="text/html" id="status">
			{{#  if(d.status == '0'){ }}
				<span class="label radius" style="background-color:#f9c248;">可使用</span> 
			{{# }else if(d.status == '1'){ }}
				<span class="label label-success radius" style="background-color:red;">不可使用</span>
			{{# } }}
</script>
<script type="text/html" id="retain2">
			{{#  if(d.retain2 == '1'){ }}
				<span class="label radius" style="background-color:red;">已删除</span> 
			{{# }else if(d.retain2 == '2'){ }}
				<span class="label label-success radius" style="background-color:#f9c248;">未删除</span>
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