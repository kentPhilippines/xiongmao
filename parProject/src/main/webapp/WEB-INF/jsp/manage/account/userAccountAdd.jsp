<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>添加商户手续费</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<%@include file="../../common/common.jsp"%>
<style type="text/css">
.layui-form-label{
	float: left;
    display: block;
    padding: 9px 15px;
    width: 107px;
    font-weight: 400;
    line-height: 20px;
    text-align: right;
}
</style>
<body layadmin-themealias="default">
<form id="AccountUserForm" class="form-signin" action="javascript:0" method="post" novalidate="novalidate">
	<div class="layui-form" lay-filter="layuiadmin-form-admin"
		id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
		<div class="layui-form-item">
			<label class="layui-form-label">商户号</label>
			<div class="layui-input-inline">
				<input type="text" name="accountId" lay-verify="required"
					placeholder="请输入商户号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">账户号</label>
			<div class="layui-input-inline">
				<input type="text" name="userId" lay-verify="required"
					placeholder="请输入渠道编号" id="LAY-user-login-password"  autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-layer-btn layui-layer-btn-">
			<a class="layui-layer-btn0" url = "${ctx}/manage/account/addUserAccount">确定</a>
			<a class="layui-layer-btn1">取消</a>
		</div>
	</div>
</form>
</body>
</html>
<script>
layui.use('form', function(){
  var form = layui.form;
});
layui.config({
    base: '/static/layAutoComplete/' //假设这是test.js所在的目录
}).extend({ //设定模块别名
    autocomplete: 'layAutoComplete'
});
layui.use(['autocomplete'], function () {
    var departmentList = ${accountList};
    layui.autocomplete({
        element: '[name="accountId"]',
        array: departmentList,
        num: 1,
        count: 5,
        done: function (item) {//选中
        	$("[name='accountId']").val(item.pinyin)
        }
    });
    var departmentList1 = ${userList};
    layui.autocomplete({
        element: '[name="userId"]',
        array: departmentList1,
        num: 1,
        count: 5,
        done: function (item) {//选中
        	$("[name='userId']").val(item.pinyin)
        }
    })
})
</script>
<script type="text/javascript" src="${ctx}/static/js/manage/account/account.js" ></script>
<script>
$(function(){
	AccountUserAddCls.init();
})
</script>