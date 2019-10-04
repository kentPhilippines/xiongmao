<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>商户查询代付订单</title>
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
						<label class="layui-form-label">记录编号</label>
						<div class="layui-input-block">
							<input type="text" name="accountId" placeholder="记录编号" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">订单编号</label>
						<div class="layui-input-block">
							<input type="text" name="orderId" placeholder="订单编号" autocomplete="off" class="layui-input">
						</div>
					</div> 
					<div class="layui-inline">
						<label class="layui-form-label">提现卡号</label>
						<div class="layui-input-block">
							<input type="text" name="retain1" placeholder="卡号" autocomplete="off" class="layui-input">
						</div>
					</div> 
					<div class="layui-inline">
						<label class="layui-form-label">审批人</label>
						<div class="layui-input-block">
							<input type="text" name="approver" placeholder="审批人" autocomplete="off" class="layui-input">
						</div>
					</div> 
					 <div class="layui-inline">
				      <label class="layui-form-label">日期范围</label>
				      <div class="layui-input-inline">
				        <input type="text" class="layui-input" id="createTime"   name = "createTime"  placeholder=" - ">
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
				<div class="test-table-reload-btn" style="margin-bottom: 10px;">
		            	 可取现余额：
		            <div class="layui-inline">
		               	<input type="text" readonly = "unselectable='on'"  name="retain3" placeholder="余额" autocomplete="off" class="layui-input" value="${user.retain3}">
		            </div>
		          <button class="layui-btn layuiadmin-btn-admin" data-type="add" addUrl = "${ctx}/manage/agent/withdrawals">申请提现</button>
		        </div>		
				<table id="LAY-user-back-manage" url = "${ctx}/manage/agent/withdrawalsRecordList" lay-filter="LAY-user-back-manage"></table>
			</div>
		</div>
	</div>
	<script type="text/html" id="operation">
 	 <a class="layui-btn layui-btn-xs" lay-event="addAmount" url = "${ctx}/manage/account/addAmount">查看提现结果</a>
	</script>
</body>
</html>
<script type="text/javascript" src="${ctx}/static/js/manage/merchants/merchants.js" ></script>
<<script type="text/html" id="merchantsStatus">
	{{#  if(d.merchantsStatus == '1'){ }}
		<span class="label radius"style="background-color:red;" >成功</span>
	{{# }else if(d.merchantsStatus == '2'){ }}
		<span class="label label-success radius" style="background-color:#009688;">失败</span>
	{{# }else if(d.merchantsStatus == '3'){ }}
		<span class="label label-success radius" style="background-color:#009688;">处理中</span>
	{{# } }}
</script>
<script>
$(function(){
	WithdrawalsRecordClas.init();
})
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  laydate.render({
		    elem: '#createTime'
		    ,range: true
		  });
})
</script>