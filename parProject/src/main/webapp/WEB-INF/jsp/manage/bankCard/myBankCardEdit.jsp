<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>修改银行卡信息</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<%@include file="../../common/common.jsp"%>
<body layadmin-themealias="default">
<form id="UserForm" class="form-signin" action="javascript:0" method="post" novalidate="novalidate">
	<div class="layui-form" lay-filter="layuiadmin-form-admin"
		id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
		<input type="hidden" name="id" lay-verify="required"
					    autocomplete="off" class="layui-input" value="${bankCard.id}">
		<div class="layui-form-item">
			<label class="layui-form-label">银行卡号</label>
			<div class="layui-input-inline">
				<input type="text" name="bankCard" lay-verify="required"
					    autocomplete="off" class="layui-input" value="${bankCard.bankCard}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">银行名称</label>
			<div class="layui-input-inline">
				<input type="text" name="bankName" lay-verify="required"
					placeholder="请输入银行名称"    autocomplete="off" class="layui-input" value="${bankCard.bankName}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">持卡人姓名</label>
			<div class="layui-input-inline">
				<input type="text" name="cardholder" lay-verify="required"
					placeholder="请输入持卡人姓名"   autocomplete="off" class="layui-input" value="${bankCard.cardholder}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">财务主管</label>
			<div class="layui-input-inline">
				<input type="text" name="treasurer" lay-verify="required"
					placeholder="请输入财务主管"   autocomplete="off" class="layui-input"  value="${bankCard.treasurer}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">银行卡负责人</label>
			<div class="layui-input-inline">
				<input type="text" name="liabilities" lay-verify="required"
					placeholder="请输入银行卡负责人"   autocomplete="off" class="layui-input" value="${bankCard.liabilities}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">绑定手机</label>
			<div class="layui-input-inline">
				<input type="text" name="bankPhone" lay-verify="required"
					placeholder="请输入绑定手机"    autocomplete="off" class="layui-input" value = "${bankCard.bankPhone}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">持卡人身份证</label>
			<div class="layui-input-inline">
				<input type="text" name="cardholderId" lay-verify="email"
					placeholder="请输入用户所在城市" autocomplete="off" class="layui-input" value = "${bankCard.cardholderId}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">银行卡备注</label>
			<div class="layui-input-inline">
				<input type="text" name="bankNote" lay-verify="email"
					placeholder="请输入银行卡备注" autocomplete="off" class="layui-input" value = "${bankCard.bankNote}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">允许交易额度</label>
			<div class="layui-input-inline">
				<input type="text" name="cardholderId" lay-verify="email"
					placeholder="请输入用户所在城市" autocomplete="off" class="layui-input" value = "${bankCard.cardholderId}">
			</div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">银行卡类别</label>
		    <div class="layui-input-inline">
		      <select name="bankType" lay-filter="aihao">
		        <option value="0">收款卡</option>
		        <option value="1">中转卡</option>
		        <option value="2">出款卡</option>
		        <option value="3">冻结卡</option>
		        <option value="4">测试卡</option>
		      </select>
		    </div>
 		</div>
		<div class="layui-layer-btn layui-layer-btn-">
			<a class="layui-layer-btn0" url = "${ctx}/manage/bankCard/bankCardEdit">确定</a><a class="layui-layer-btn1">取消</a>
		</div>
	</div>
</form>
</body>
</html>
<script>
layui.use('form', function(){
  var form = layui.form;
});
</script>
<script type="text/javascript" src="${ctx}/static/js/manage/bankCard/bankCard.js" ></script>
<script>
$(function(){
	BankCardEditCls.init();
})
</script>