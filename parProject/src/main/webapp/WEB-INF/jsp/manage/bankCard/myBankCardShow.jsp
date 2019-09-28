<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html><head>
  <meta charset="utf-8">
  <title>layuiAdmin 主页示例模板一</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  	<%@include file="../../common/common.jsp"%>
  	<style type="text/css">
  	strong{
  	 color: #ff500e;
  	}
  	</style>
<body layadmin-themealias="default" style="  margin-top: 14px;">
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md8">    
        <div class="layui-card">
          <div class="layui-card-header">
           				数据略览
          </div>
          <div class="layui-card-body">
            <div class="layui-row layui-col-space10">
	              <div class="layui-col-xs12 layui-col-sm4">
	                <div class="layuiadmin-card-text">
	                  <div class="layui-text-top" style=" -webkit-text-stroke-width: thick;">
	                  <i class="layui-icon layui-icon-water"></i>
	                  <a lay-href="https://www.layui.com/doc/modules/flow.html" style="font-size:20px">卡片</a></div>
	                  <p class="layui-text-center"style=" -webkit-text-stroke: thin; ">当前卡片<span><strong>【${bankCount}】</strong></span>张</p>
	                  <p class="layui-text-bottom">  </p>
	                </div>
	              </div>
              <div class="layui-col-xs12 layui-col-sm4">
                <div class="layuiadmin-card-text">
                  <div class="layui-text-top" style=" -webkit-text-stroke-width: thick;">
                  <i class="layui-icon layui-icon-upload-circle"></i>
                  <a lay-href="https://www.layui.com/doc/modules/upload.html" style="font-size:20px">余额</a></div>
                  <p class="layui-text-center" style=" -webkit-text-stroke: thin; ">所有卡片余额 <span><strong>【${sumAmount}】</strong></span> 元</p>
                  <p class="layui-text-bottom"> </p>
                </div>
              </div>
              <div class="layui-col-xs12 layui-col-sm4">
                <div class="layuiadmin-card-text">
                  <div class="layui-text-top" style=" -webkit-text-stroke-width: thick;">
                  <i class="layui-icon layui-icon-form"></i>
                  <a lay-href="https://www.layui.com/doc/modules/form.html#val" style="font-size:20px">盈利</a></div>
                  <p class="layui-text-center"style=" -webkit-text-stroke: thin; ">盈利<span><strong>【${myMoney}】</strong></span>元</p>
                  <p class="layui-text-bottom"> </p>
                </div>
              </div>
              <div class="layui-col-xs12 layui-col-sm4">
                <div class="layuiadmin-card-text">
                  <div class="layui-text-top" style=" -webkit-text-stroke-width: thick;">
                  <i class="layui-icon layui-icon-form" ></i>
                  <a lay-href="https://www.layui.com/doc/modules/form.html" style="font-size:20px">额度</a></div>
                  <p class="layui-text-center"style=" -webkit-text-stroke: thin; ">额度<span><strong>【 ${sumIsDeal}】</strong></span>元</p>
                  <p class="layui-text-bottom"> </p>
                </div>
              </div>
              <div class="layui-col-xs12 layui-col-sm4">
                <div class="layuiadmin-card-text">
                  <div class="layui-text-top" style=" -webkit-text-stroke-width: thick;">
                  <i class="layui-icon layui-icon-form"></i>
                  <a lay-href="https://www.layui.com/doc/modules/form.html" style="font-size:20px">需回款</a></div>
                  <p class="layui-text-center"style=" -webkit-text-stroke: thin; ">当前需回款<span><strong>【 ${toDayAmount}】</strong></span>元</p>
                  <p class="layui-text-bottom"></p>
                </div>
              </div>
              <div class="layui-col-xs12 layui-col-sm4">
                <div class="layuiadmin-card-text">
                  <div class="layui-text-top" style=" -webkit-text-stroke-width: thick;">
                  <i class="layui-icon layui-icon-form"></i>
                  <a lay-href="https://www.layui.com/doc/modules/form.html" style="font-size:20px">银行卡满额</a></div>
                  <p class="layui-text-center"style=" -webkit-text-stroke: thin; ">满额银行卡<span><strong>【 ${number}】</strong></span> 张</p>
                  <p class="layui-text-bottom"> </p>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">银行卡流水动态</div>
          <div class="layui-card-body">
				<table id="LAY-user-back-manage" url = "${ctx}/manage/bankCard/MyBankCardRunList" lay-filter="LAY-user-back-manage"></table>
			</div>
        </div>   
      </div>
      <div class="layui-col-md4">
        <div class="layui-card">
          <div class="layui-card-header">便捷导航</div>
          <div class="layui-card-body">
            <div class="layuiadmin-card-link">
              <a href="javascript:;">回款</a>
            </div>        
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">卡商须知</div>
          <div class="layui-card-body">
               <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-pageone" lay-anim="fade" style="width: 100%; height: 280px;">
                <div carousel-item="" id="LAY-index-pageone">
                </div>
              </div> 

          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">重点通告</div>
          <div class="layui-card-body">
            <ul class="layui-row layuiadmin-card-team">
              <li class="layui-col-xs6">
                <a lay-href="https://www.layui.com/doc/modules/table.html">
                  <span class="layui-team-img"><img src="../../layuiadmin/style/res/logo.png"></span> 
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
<style id="LAY_layadmin_theme">
	.layui-side-menu,.layadmin-pagetabs .layui-tab-title li:after,
	.layadmin-pagetabs .layui-tab-title li.layui-this:after,
	.layui-layer-admin .layui-layer-title,.layadmin-side-shrink 
	.layui-side-menu .layui-nav>.layui-nav-item>
	.layui-nav-child{
	background-color:#20222A !important;}
		.layui-nav-tree .layui-this,.layui-nav-tree .layui-this>a,
		.layui-nav-tree .layui-nav-child dd.layui-this,.layui-nav-tree 
		.layui-nav-child dd.layui-this a{background-color:#009688 !important;}
		.layui-layout-admin .layui-logo{background-color:#20222A !important;}
	</style>
</body>
</html>
<script type="text/javascript" src="${ctx}/static/js/manage/bankCard/bankCard.js" ></script>
<script type="text/html" id="runType"> 
			{{#  if(d.runType == '1'){ }}
				<span class="label radius" style="background-color:#f9c248;">商户交易</span> 
			{{# }else if(d.runType == '2'){ }}
				<span class="label label-success radius"  >卡商回款</span>
			{{# }else if(d.runType == '3'){ }}
				<span class="label label-success radius" style="background-color:red;color：#fff">账户入款分润</span>
			{{# } }}
</script>
<script>
layui.use('table', function(){
	  var table = layui.table;
})
$(function(){
	MyBankCardRun1Clas.init();
})
</script>