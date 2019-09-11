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
<body layadmin-themealias="default" style=" background-color: #ffffff00;" >
	<form class="layui-form layui-form-pane" action=""  style="padding: 15px;">
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">账户编号</label>
	      <div class="layui-input-inline">
	        <input type="text" style="background-color: rgb(228, 232, 234);" readonly = "unselectable='on'"  name="accountId"  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" value = "${account.accountId}">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">账户昵称</label>
	      <div class="layui-input-inline">
	        <input type="text" name="accountName" style="background-color: rgb(228, 232, 234);" readonly = "unselectable='on'"  autocomplete="off" class="layui-input" value = "${account.accountName}">
	      </div>
	    </div>
	  </div>
	<div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">可取现总额</label>
	      <div class="layui-input-inline">
	        <input type="text" style="background-color: rgb(228, 232, 234);" readonly = "unselectable='on'"  name="cashBalance"  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" value = "${account.cashBalance}/元">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">冻结总额</label>
	      <div class="layui-input-inline">
	        <input type="text" style="background-color: rgb(228, 232, 234);" name="freezeBalance"  readonly = "unselectable='on'"  autocomplete="off" class="layui-input" value = "${account.freezeBalance}/元"">
	      </div>
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">账户余额</label>
	      <div class="layui-input-inline">
	        <input type="text" style="background-color: rgb(228, 232, 234);" readonly = "unselectable='on'"  name="accountBalance"  lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input" value = "${account.accountBalance}/元"">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">加款金额</label>
	      <div class="layui-input-inline">
	        <input type="text"  name="amount"     autocomplete="off" class="layui-input" value = "">
	      </div>
	    </div>
	  </div>
	  <div class="layui-form-item layui-form-text">
	    <label class="layui-form-label">冻结理由</label>
	    <div class="layui-input-block">
	      <textarea placeholder="请输入内容（必填）" class="layui-textarea" name = "dealDescribe" ></textarea>
	    </div>
	  </div>
	  <div class="layui-layer-btn layui-layer-btn-">
			<a class="layui-layer-btn0" url = "${ctx}/manage/account/accountAmountDel">确定</a>
		</div>
	</form>
</body>
</html>
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
});
</script>
<script type="text/javascript" src="${ctx}/static/js/manage/account/accountlUpdata.js" >
</script>
<script type="text/javascript">
$(function(){
	AccountlAmountDel.init();
})
</script>
