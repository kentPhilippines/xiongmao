<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>账户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<%@include file="../../common/common.jsp"%>
<style type="text/css">
.layui-form-pane .layui-input-block{
    margin-left: 197px;
    left: -1px;
}
.layui-form-pane .layui-form-label{
	width: 192px;
    padding: 8px 15px;
    height: 38px;
    line-height: 20px;
    border-width: 1px;
    border-style: solid;
    border-radius: 2px 0 0 2px;
    text-align: center;
    background-color: #FBFBFB;
    overflow: hidden;
    box-sizing: border-box;
}

</style>
<div class="layui-fluid" style="padding: 15px;">
<body layadmin-themealias="default" style=" background-color: #c4c0c7;">
		<div class="layui-card"  style="padding: 15px;">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">商户账号</label>
						<div class="layui-input-block" style="margin-right: 120px;">
							<input type="text" name="userId"
							readonly = "unselectable='on'"
							 placeholder="用于登录的账号" value="${user.userId }" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">商户名称</label>
						<div class="layui-input-block">
							<input type="text" name="userName"
							 placeholder="商户名称"
							 value="${user.userName }" autocomplete="off" class="layui-input">
						</div>
					</div> 
				</div>
			</div>
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">联系QQ</label>
						<div class="layui-input-block" style="margin-right: 120px;">
							<input type="text" name="userQQ" 
							placeholder="联系QQ"  value="${user.userQQ }" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">联系微信</label>
						<div class="layui-input-block">
							<input type="text" name="userWechar" 
							placeholder="联系微信"  value="${user.userWechar}" autocomplete="off" class="layui-input">
						</div>
					</div> 
				</div>
			</div>
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">联系电话</label>
						<div class="layui-input-block" style="margin-right: 120px;">
							<input type="text" name="userPhone" 
							placeholder="联系电话"  value="${user.userPhone }" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">联系邮箱</label>
						<div class="layui-input-block">
							<input type="text" name="userMail" 
							placeholder="联系邮箱"  
							value="${user.userMail }" autocomplete="off" class="layui-input">
						</div>
					</div> 
				</div>
			</div>
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">支付密码</label>
						<div class="layui-input-block" >
							<input type="text" name="payPassword" 
							readonly = "unselectable='on'"
							placeholder="支付密码"  value="******" autocomplete="off" class="layui-input">
						</div>
					</div>
					<button class="layui-btn layuiadmin-btn-admin" data-type="updataPassword" Url = "${ctx}/manage/account/updataPayPasswordShow?userId=${user.userId}">修改支付密码</button>
					<div class="layui-inline">
						<label class="layui-form-label">登录密码</label>
						<div class="layui-input-block">
							<input type="text" name="userPassword" 
							readonly = "unselectable='on'"
							readonly = "unselectable='on'"placeholder="登录密码"  value="******" autocomplete="off" class="layui-input">
						</div>
					</div> 
					<button class="layui-btn layuiadmin-btn-admin" data-type="updataPassword"  Url = "${ctx}/manage/account/updataPasswordShow?userId=${user.userId}">修改登录密码</button>
				</div>
			</div>
		</div>
		<div class="layui-layer-btn layui-layer-btn-">
	    	<a class="layui-layer-btn0" href = "${ctx}/homePage">确定</a>
	    	<a class="layui-layer-btn0" href = "${ctx}/homePage">返回首页</a>
	    </div>
</body>
</form>
</div>
</html>
<script type="text/javascript" src="${ctx}/static/js/manage/account/accountlUpdata.js"></script>
<script>
UpdataPassWord.init();
layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(formDemo)', function(data){
    layer.msg(JSON.stringify(data.field));
    return false;
  });
});
</script>
