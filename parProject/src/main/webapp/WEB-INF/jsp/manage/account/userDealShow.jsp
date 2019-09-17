<!DOCTYPE html>
<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html><head>
  <meta charset="utf-8">
  <title>Echarts集成 - 折线图</title>
   <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<%@include file="../../common/common.jsp"%>
<%@include file="../../common/highcharts.jsp"%>
<body layadmin-themealias="default">
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md6" style="margin-top: 31px;">
        <div class="layui-card">
          <div class="layui-card-header">月交易流量图</div>
          <div class="layui-card-body">
            <div style="min-width:400px;height:400px" class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-normline" lay-anim="fade" style="width: 100%; height: 280px;">
            <div id="container" style="min-width:400px;height:400px"></div>
            </div>
          </div>
        </div>
        <div class="layui-card">
          <div class="layui-card-header">交易金额关系图</div>
          <div class="layui-card-body">
            <div class="layui-carousel layadmin-carousel layadmin-dataview" data-anim="fade" lay-filter="LAY-index-heapline" lay-anim="fade" style="width: 100%; height: 280px;">
             <div id="container1" style="min-width:400px;height:400px"></div>
            </div>
          </div>
        </div>
      </div> 
      <div class="layui-col-md6" style="height: 800px;background-color: #a58f8f30;margin-top: 31px;">
        <div class="layui-card">
          <div class="layui-card-header"> 交易情况汇总 </div>
          <div class="layui-card-body">
           		<p>一月同期交易总量对比20%</p>
           		<div class="layui-progress layui-progress-big">
				  <div class="layui-progress-bar" lay-percent="100%"></div>
				</div>
           		<p>二月同期交易总量对比20%</p>
           		<div class="layui-progress layui-progress-big">
				  <div class="layui-progress-bar" lay-percent="20%"></div>
				</div>
           		<p>三月同期交易总量对比20%</p>
           		<div class="layui-progress layui-progress-big">
				  <div class="layui-progress-bar" lay-percent="20%"></div>
				</div>
           		<p>四月同期交易总量对比20%</p>
           		<div class="layui-progress layui-progress-big">
				  <div class="layui-progress-bar" lay-percent="20%"></div>
				</div>
           		<p>同期交易总量对比20%</p>
           		<div class="layui-progress layui-progress-big">
				  <div class="layui-progress-bar" lay-percent="20%"></div>
				</div>
           		<p>同期交易总量对比20%</p>
           		<div class="layui-progress layui-progress-big">
				  <div class="layui-progress-bar" lay-percent="20%"></div>
				</div>
          </div>
        </div>
      </div> 
      </div> 
      
    </div>
  <script type="text/javascript" src="${ctx}/static/js/manage/account/highchartsUserShow.js">
  </script>
<style id="LAY_layadmin_theme">
.layui-side-menu,.layadmin-pagetabs .layui-tab-title li:after,.layadmin-pagetabs .layui-tab-title li.layui-this:after,.layui-layer-admin .layui-layer-title,.layadmin-side-shrink .layui-side-menu .layui-nav>.layui-nav-item>.layui-nav-child{background-color:#20222A !important;}
.layui-nav-tree .layui-this,.layui-nav-tree .layui-this>a,.layui-nav-tree .layui-nav-child dd.layui-this,.layui-nav-tree .layui-nav-child dd.layui-this a{background-color:#009688 !important;}
.layui-layout-admin .layui-logo{background-color:#20222A !important;}
</style>
</body>
</html>
<<script type="text/javascript">
/* //注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作 */
layui.use('element', function(){
  var element = layui.element;
});
</script>

