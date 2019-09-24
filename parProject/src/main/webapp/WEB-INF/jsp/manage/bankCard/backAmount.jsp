<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8">
<title>码商回款</title>
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
<body layadmin-themealias="default" style=" background-color: #ffffff00;" >
	<form class="layui-form layui-form-pane" action=""  style="padding: 15px;">
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">账户编号</label>
	      <div class="layui-input-inline">
	        <input type="text" readonly = "unselectable='on'"  name="accountId"  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" value = "${user.userId}">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">账户昵称</label>
	      <div class="layui-input-inline">
	        <input type="text" name="accountIdName" autocomplete="off" class="layui-input" value = "${user.userName}">
	      </div>
	    </div>
	  </div>
	    <div class="layui-inline">
	    <label class="layui-form-label">到账银行卡</label>
	    <div class="layui-input-inline">
	  	  <input type="text" name="bankR" autocomplete="off" class="layui-input" value = "">
	    </div>
	  </div>
	    <div class="layui-inline">
	    <label class="layui-form-label">出款银行卡</label>
	    <div class="layui-input-inline">
	      <input type="text" name="bankD" autocomplete="off" class="layui-input" value = "">
	    </div>
	  </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">回款金额</label>
	      <div class="layui-input-inline">
	        <input type="text" name="amount" autocomplete="off" class="layui-input"  >
	      </div>
	    </div>
	  </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">回款密码</label>
	      <div class="layui-input-inline">
	        <input type="password" name="payPassword" autocomplete="off" class="layui-input"  >
	      </div>
	    </div>
	   
	  </div>
	  <div class="layui-layer-btn layui-layer-btn-">
			<a class="layui-layer-btn0" url = "${ctx}/manage/bankCard/addAmount">确定</a>
		</div>
	</form>
</body>
</html>
<script type="text/javascript" src="${ctx}/static/js/manage/bankCard/bankCard.js" >
</script>
<script type="text/javascript">
layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	});
$(function(){
	BackbankAdd.init();
})
</script>
