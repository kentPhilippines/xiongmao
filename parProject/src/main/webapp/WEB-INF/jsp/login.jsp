<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="${ctx}/static/css/system/login.css" rel="stylesheet" type="text/css"/> 
<title>新的支付系统</title>
</head>
<%@include file="common/common.jsp"%>
<style>
.back{
  bottom: 70px;
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    overflow: hidden;
}
.items{
position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: hidden;
}
</style>
<body id = "backgroundImg" style="background-image: url(&quot;http://www.logoquan.com/upload/list/20130721/logoquan13746570316.PNG&quot;); display: block;">

<form id="loginForm" class="form-signin" action="javascript:0" method="post" novalidate="novalidate">
<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>熊猫</h2>
        <p>支付后台管理系统</p>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
          <input type="text" name="userId" id="LAY-user-login-username"  placeholder="用户名" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
          <input type="password" name="userPassword" id="LAY-user-login-password"  placeholder="密码" class="layui-input">
        </div>
        <!-- <div class="layui-form-item">   这里可以换成为  google动态验证码
          <div class="layui-row">
            <div class="layui-col-xs7">
              <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
              <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
            </div>
            <div class="layui-col-xs5">
              <div style="margin-left: 10px;">
                <img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
              </div>
            </div>
          </div>
        </div> -->
        <div class="layui-form-item" style="margin-bottom: 20px;">
          <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">注册账号</a>
        </div>
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="LAY-user-login-submit">登 入</button>
        </div>
      </div>
    </div>
  </div>
  </form>
</body>
<script type="text/javascript">
var ctx = '${ctx}';
var ii = null;
$(function(){
		console.log(1)
		setTimeout(function () {
			agan();
		}, 300);
		
})
	function agan(){
	var i1 = "background-image: url(&quot;https://img.alicdn.com/tps/TB1h9xxIFXXXXbKXXXXXXXXXXXX.jpg&quot;);"
	var i2 = "background-image: url(&quot;https://img.alicdn.com/tps/TB1pfG4IFXXXXc6XXXXXXXXXXXX.jpg&quot;);"
	var i3 = "background-image: url(&quot;https://img.alicdn.com/tps/TB1h9xxIFXXXXbKXXXXXXXXXXXX.jpg&quot;);"
	for(var i = 1 ; i <4 ; i ++){
		a = 'i'+ i;
		$("backgroundImg").attr("style",a)
	}
	
	
	
		
		}
</script>
<script type="text/javascript" src="${ctx}/static/js/system/index/login.js" ></script>