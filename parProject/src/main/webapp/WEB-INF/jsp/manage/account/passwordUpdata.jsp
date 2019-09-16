<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>修改密码</title>
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
<form id="AccountFeeForm" class="form-signin" action="javascript:0" method="post" novalidate="novalidate">
	<div class="layui-form" lay-filter="layuiadmin-form-admin"
		id="layuiadmin-form-admin" style="padding: 20px 30px 0 0;">
		<div class="layui-form-item">
			<label class="layui-form-label">账号</label>
			<div class="layui-input-inline">
				<input type="text"  readonly = "unselectable='on'" name="userId" lay-verify="required"
					value="${userId}"
					placeholder="请输入渠道编号" id="LAY-user-login-password"  autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">老<if test="${isPay ne null and isPay ne ''}">支付</if>密码</label>
			<div class="layui-input-inline">
				<input type="password" name="userPassword" lay-verify="required" 
					placeholder="请输入密码"  id="LAY-user-login-password" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">请输入新<if test="${isPay ne null and isPay ne ''}">支付</if>密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password" lay-verify="required"
					placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">请验证新<if test="${isPay ne null and isPay ne ''}">支付</if>密码</label>
			<div class="layui-input-inline">
				<input type="password" name="password1" lay-verify="required"
					placeholder="请输入密码" autocomplete="off" class="layui-input" onBlur="clickpassword()">
			</div>
		</div>
		<div class="layui-layer-btn layui-layer-btn-">
			<a class="layui-layer-btn0" url = "${ctx}/manage/account/updata<if test='${isPay ne null and isPay ne ""}'>Pay</if>Password" >确定</a>
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
</script>
<script>
function clickpassword(){
	var pass1 = $("[name=password1]").val();
	var pass2 = $("[name=password]").val();
	if(pass1 != pass2){
		layer.msg("两次密码输入不一致")
		$("[name=password1]").val("");
		$("[name=password]").val("");
	}
}
$(function(){
	$(".layui-layer-btn0").on("click",function(){
		var url = $(this).attr("url");
		var data = {
			password : $("[name=password]").val(),
			userId : $("[name='userId']").val(),
			userPassword : $("[name='userPassword']").val()
		};
		ajax(url,data);
	})
})
function ajax(url,data){
	 $.ajax({
         url: url,
         data: data,
         contentType: "application/x-www-form-urlencoded; charset=utf-8",
         type: 'post',
         dataType:"json",
         success: function (res) {
     			if(res && res.success){
     				layer.msg(""+res.message);
     				parent.layer.closeAll();
     			}else if(res && !res.success){
     				layer.msg(""+res.message)
     				$("[name=password1]").val("");
     				$("[name=password]").val("");
     			}
         },
         error: function (err) {
     			layer.msg(err)
         }
     })
}
</script>