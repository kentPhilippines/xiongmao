<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>layuiAdmin 后台管理员</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link href="${ctx}/static/css/system/user.css" rel="stylesheet" type="text/css" />
<%@include file="../../common/common.jsp"%>
<body layadmin-themealias="default" style=" background-color: #c4c0c7;">
	<div class="layui-fluid" style="padding: 15px;">
		<div class="layui-card"  style="padding: 15px;">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">登录名</label>
						<div class="layui-input-block">
							<input type="text" name="loginname" placeholder="请输入"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">手机</label>
						<div class="layui-input-block">
							<input type="text" name="telphone" placeholder="请输入"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">邮箱</label>
						<div class="layui-input-block">
							<input type="text" name="email" placeholder="请输入"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-admin" lay-submit=""
							lay-filter="LAY-user-back-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="layui-card-body">
				<div style="padding-bottom: 10px;">
					<button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
					<button class="layui-btn layuiadmin-btn-admin" data-type="add" addUrl = "${ctx}/system/user/userAdd">添加</button>
				</div>
				<table id="LAY-user-back-manage" lay-filter="LAY-user-back-manage"></table>
			</div>
		</div>
	</div>
	<script>
	layui.use('table', function(){
		  var table = layui.table;
		  table.render({
		    elem: '#LAY-user-back-manage'
		    ,height: 312
		    ,url: '/system/user/userList' //数据接口
		    ,page: true //开启分页
		    ,cols: [[ //表头
		      {field: 'id', title: 'ID', hide :true, width:80,   fixed: 'left'}
		      ,{field: 'userId', title: '用户ID', width:80}
		      ,{field: 'userName', title: '姓名', width:80, sort: true}
		      ,{field: 'userMail', title: '邮箱', width: 80}
		      ,{field: 'userPhone', title: '手机', width: 80, sort: true}
		      ,{field: 'userQQ', title: '用户QQ', width: 135, sort: true}
		      ,{field: 'userWechar', title: '用户微信', width: 135}
		      ,{field: 'userType', title: '用户类型', width: 135, sort: true}
		      ,{field: 'userAddress', title: '用户地址', width: 135, sort: true}
		      ,{field: 'userCity', title: '用户所在城市', width: 135, sort: true}
		      ,{field: 'submitTime', title: '创建时间', width: 135, sort: true}
		    ]]
		  });
		});
	</script>
</body>
</html>
<script type="text/javascript" src="${ctx}/static/js/system/user/user.js" ></script>