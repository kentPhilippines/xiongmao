<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>修改渠道信息</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<%@include file="../../common/common.jsp"%>
<body layadmin-themealias="default">
<form id="UserForm" class="form-signin" action="javascript:0" method="post" novalidate="novalidate">
	<div class="layui-form" lay-filter="layuiadmin-form-admin"
		id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
		<div class="layui-form-item ">
			<label class="layui-form-label">渠道编号</label>
			<div class="layui-input-inline">
				<input type="text" name="channelNo" lay-verify="required"
					placeholder="渠道编号" autocomplete="off" class="layui-input" value="${channel.channelNo}" >
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">渠道名称</label>
			<div class="layui-input-inline">
				<input type="text" name="channelName" lay-verify="required"
					placeholder="请输入渠道渠道名称"    autocomplete="off" class="layui-input" value="${channel.channelName}">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">上游渠道实际代号</label>
			<div class="layui-input-inline">
				<input type="text" name="channelAccount" lay-verify="required"
					placeholder="请输入渠道代号"    autocomplete="off" class="layui-input" value="${channel.channelAccount}">
			</div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">渠道状态</label>
		    <div class="layui-input-inline">
		      <select name="channelStautus" lay-filter="aihao">
		        <option value="1"<c:if test="${channel.channelStautus eq 1}"> selected : "selected"</c:if> >有效</option>
		        <option value="2"<c:if test="${channel.channelStautus eq 2}"> selected : "selected"</c:if> >停用</option>
		      </select>
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">渠道类别</label>
		    <div class="layui-input-inline">
		      <select name="channelType" lay-filter="aihao">
		        <option value="1"<c:if test="${channel.channelStautus eq 1}"> selected : "selected"</c:if>>本渠道</option>
		        <option value="0"<c:if test="${channel.channelStautus eq 0}"> selected : "selected"</c:if>>外渠道</option>
		      </select>
		    </div>
		</div>
		<div class="layui-layer-btn layui-layer-btn-">
			<a class="layui-layer-btn0" url = "${ctx}/manage/channel/channelEdit">确定</a><a class="layui-layer-btn1">取消</a>
		</div>
	</div>
</form>
</body>
</html>
<script>
layui.use('table', function(){
	  var table = layui.table;
})
</script>
<script type="text/javascript" src="${ctx}/static/js/manage/channel/channelUpdate.js" ></script>
<script>
$(function(){
	Channel.init();
})
</script>