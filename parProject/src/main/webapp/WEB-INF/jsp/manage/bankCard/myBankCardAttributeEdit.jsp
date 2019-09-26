<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>设置银行卡属性</title>
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
			<label class="layui-form-label">银行卡ID(支付宝获取)</label>
			<div class="layui-input-inline">
				<input type="text" name="retain4" lay-verify="required"
					placeholder="请输入银行卡ID"    autocomplete="off" class="layui-input" value="${bankCard.retain4}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">银行卡简写字母</label>
			<div class="layui-input-inline">
				<input type="text" name="retain3" lay-verify="required"
					placeholder="请输入银行卡简写字母"   autocomplete="off" class="layui-input" value="${bankCard.retain3}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">隐藏卡号(支付宝获取)</label>
			<div class="layui-input-inline">
				<input type="text" name="retain5" lay-verify="required"
					placeholder="请输入隐藏卡号"   autocomplete="off" class="layui-input"  value="${bankCard.retain5}">
			</div>
		</div>
		<div class="layui-layer-btn layui-layer-btn-" style="text-align:center">
			<a class="layui-layer-btn0" url = "${ctx}/manage/bankCard/myBankCardAttributeEdit">确定</a><a class="layui-layer-btn1">取消</a>
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
	BankCardAttributeEditCls.init();
})
</script>