<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item upbit1">
      	<span class="layui-nav-more upbit-more"></span>
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img"><!-- 图片要替换 -->
          ${user.userName}
        </a>
        <dl class="layui-nav-child layui-anim topUserShow layui-anim-upbit">
          <dd><a rank = "1" url="manage/account/show">基本资料</a></dd>
          <dd><a rank = "1" url="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="${ctx}/logout">退了</a></li>
    </ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
      <c:forEach var="menu" items="${menuList}">
       <li class="layui-nav-item ">
          <a class="" url="${menu.resourcesUrl}" ><span class="layui-nav-more"></span>${menu.resourcesName}</a>
	         <c:if test="${menu.sumList.size() >0}">
	            <dl class="layui-nav-child">
	           <c:forEach var="sumMenu" items="${menu.sumList}" >
		        <c:choose>
						<c:when test="${sumMenu.sumList.size() >0 }">
							 <dd data-name="content" >
									 <a url="${sumMenu.resourcesUrl }" rank ="2">${sumMenu.resourcesName}
									 <span class="layui-nav-more"></span>
				                  	</a>
										<c:forEach var = "sccmu" items="${sumMenu.sumList}">
							                  	 <dl class="layui-nav-child">
									                    <dd data-name="list">
									                    	<a url="${sccmu.resourcesUrl}" rank ="3">${sccmu.resourcesName}</a>
									                    </dd>
									             </dl>
										</c:forEach>
					              </dd>
						</c:when>
						<c:otherwise>
						  <dd><a url="${sumMenu.resourcesUrl }" rank ="2" >${sumMenu.resourcesName }</a></dd>
						</c:otherwise>	        
		        </c:choose>
		        </c:forEach>
		          </dl>
	         </c:if>
        </li> 
      </c:forEach>
      </ul>
    </div>
  </div>
  <div style="padding: 15px;">
  <div class="layui-body" id="LAY_app_body" style="background-color: #dadbdb;">
     <iframe id="mainFrame" name="mainFrame"
						src="${ctx}/${higcharhs}"
						style="overflow: visible; height: 100%; scrolling="yes"
						frameborder="no" width="100%" height="650"
						marginheight = "50px"
						marginwidth ="10px"
						>
	</iframe>
    </div> 
  </div>  
  <div class="layui-footer">
    © 支付创造未来
  </div>
</div>
</body>
</html>
<script >
layui.use('element', function(){
  var element = layui.element;
});
var mainFrame = $("#mainFrame");
var menu = $("[rank]");
menu.on("click",function(){
	var url = $(this).attr("url");
	if(url){
		mainFrame.attr("src",'${ctx}'+url);
	}
});
</script>
