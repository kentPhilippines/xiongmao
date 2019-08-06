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
  <title>新的支付系统</title>
</head>
<%@include file="common/common.jsp"%>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo"><strong>新的支付系统</strong></div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item">
	    <a href="javascript:;">解决方案</a>
	    <dl class="layui-nav-child">
	      <dd><a href="">移动模块</a></dd>
	      <dd><a href="">后台模版</a></dd>
	      <dd class="layui-this"><a href="">选中项</a></dd>
	      <dd><a href="">电商平台</a></dd>
	    </dl>
  	</li>
      <li class="layui-nav-item"><a href="">用户</a></li>
	  <li class="layui-nav-item ">
	    <a href="javascript:;">产品</a>
	    <dl class="layui-nav-child">
	      <dd><a href="">选项1</a></dd>
	      <dd><a href="">选项2</a></dd>
	      <dd><a href="">选项3</a></dd>
	    </dl>
	  </li>
      <li class="layui-nav-item upbit">
        <a href="javascript:;" ><span class="layui-nav-more upbit-more"></span>其它系统</a>
        <dl class="layui-nav-child layui-anim topLayuiShow layui-anim-upbit">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul>
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item upbit1">
      	<span class="layui-nav-more upbit-more"></span>
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child layui-anim topUserShow layui-anim-upbit">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        <li class="layui-nav-item ">
          <a class="" href="javascript:;" rank ="1"><span class="layui-nav-more"></span>所有商品</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" rank ="2">列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">列表三</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item ">
          <a class="" href="javascript:;" rank ="1"><span class="layui-nav-more"></span>所有商品</a>
          <dl class="layui-nav-child">
             <dd data-name="content"   >
                  <a href="javascript:;" rank ="2">
                  	内容系统
                  	<span class="layui-nav-more"></span>
                  </a>
                  <dl class="layui-nav-child">
                    <dd data-name="list">
                    	<a lay-href="app/content/list.html" rank ="3">文章列表</a>
                    </dd>
                  </dl>
                </dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="javascript:;">列表三</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li> 
        <li class="layui-nav-item  ">
          <a href="javascript:;"><span class="layui-nav-more"></span>解决方案</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" >列表一</a></dd>
            <dd><a href="javascript:;">列表二</a></dd>
            <dd><a href="">超链接</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="">云市场</a></li>
        <li class="layui-nav-item"><a href="">发布商品</a></li>
      </ul>
    </div>
  </div>
  <div class="layui-body" id="LAY_app_body">
     <iframe id="mainFrame" name="mainFrame"
						src="${ctx}/system/role/roleShow"
						style="overflow: visible; height: 100%; scrolling="yes"
						frameborder="no" width="100%" height="650"
						marginheight = "50px"
						marginwidth ="10px"
						>
	</iframe>
    <div style="padding: 15px;">内容主体区域
    </div> 
  </div>  
  <div class="layui-footer">
    © layui.com - 底部固定区域
  </div>
</div>
</body>
</html>
<script>
layui.use('element', function(){
  var element = layui.element;
});
</script>
<script type="text/javascript" src="${ctx}/static/js/system/index/index.js"/>
