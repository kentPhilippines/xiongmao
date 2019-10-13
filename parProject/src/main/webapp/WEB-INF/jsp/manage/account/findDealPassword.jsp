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
	 				<div class="layui-inline">
						<label class="layui-form-label">交易商户号</label>
						<div class="layui-input-block">
							<input type="text" name="accountId" placeholder="商户号" autocomplete="off" class="layui-input">
						</div>
					</div> 
					<div class="layui-inline">
						<button class="layui-btn != " id = "search" lay-submit=""
							lay-filter="LAY-user-back-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
					</div>
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">交易密码</label>
					    <div class="layui-input-block">
					      <textarea   class="layui-textarea" name = "havaInterface" > </textarea>
					    </div>
					  </div>
</body>
</form>
</div>
</html>
<script>
layui.use('form', function(){
  var form = layui.form;
});
$(function(){
	$("#search").on("click",function(){
		console.log("测试点击")
		 $.ajax({
             url: '${ctx}/manage/account/findappdealpassword',
             data: {accountId : $("[name=accountId]").val()},
             type: 'post',
             dataType:"json",
             success: function (data) {
             	if(data && data.success){//数据
             		$("[name=havaInterface]").val(data.result)
	  				}else if(data && !data.success){//数据
	  				}
             }
         })
	})
})
</script>
