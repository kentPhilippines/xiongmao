var xtree = null;
var TreeCls = {
		init : function(){
			this.layuiXtree();
			this.initNode();
			this.bindEvent();
		},
		initNode:function(){
			this.$btnEnter = $("#btnEnter");
			this.$btnEsc = $("#btnEsc");
		},
		bindEvent : function(){
			this.$btnEnter.on("click",TreeCls.save);
			this.$btnEsc.on("click",TreeCls.exit);
		},
		save : function(){
			var oCks = xtree.GetChecked(); //获取末级且选中的checkbox原dom对象，返回的类型:Array
			var arr = new Array();
            for (var i = 0; i < oCks.length; i++) {
            	 arr.push(oCks[i].value);
            	 var parentId  =  xtree.GetParent(oCks[i].value);
            	 arr.push(parentId.value);
            }
            var newArr = new Array();
              let isRepeat;
              for (let i = 0; i < arr.length; i++) {
                isRepeat = false;
                for (let j = 0; j < newArr.length; j++) {
                  if (newArr[j] === arr[i]) {
                    isRepeat = true;
                    break;
                  }
                }
                if (!isRepeat) {
                  newArr.push(arr[i])
                }
              }
              var resource  = '';
              for (var i = 0; i < newArr.length; i++) {
            	  resource += newArr[i]+','
             }
              var data = {
            		  resource : resource,
            		  roleId : roleId
              }
              CommonUtil.ObjextAjax("roleAddResource",data,TreeCls.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
		},
		exit : function(){
			
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message)
				parent.layer.closeAll();
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		},
		layuiXtree : function(){
			layui.use(['form'], function () {
	            var form = layui.form;
	          //创建tree
		         xtree = new layuiXtree({
		            elem: 'layui-xtree-demo1'           //放xtree的容器，id，不要带#号（必填）
		             , form: form                       //layui form对象 （必填）
		             , data: "getTree?roleId="+roleId    //服务端地址（必填）
		             , ckall: true    //启用全选功能，默认值：false
		             , ckallback: function () { } //全选框状态改变后执行的回调函数
		             , isopen: false                     //初次加载时全部展开，默认true
		             , icon: {                          //图标样式 （必填，不填会出点问题）
		                 open: "&#xe7a0;"               //节点打开的图标
		                 , close: "&#xe622;"            //节点关闭的图标
		                 , end: "&#xe621;"              //末尾节点的图标
		             }
		             , color: {       //三种图标颜色，独立配色，更改几个都可以
		                 open: "#EE9A00"        //节点图标打开的颜色
		                 , close: "#EEC591"     //节点图标关闭的颜色
		                 , end: "#828282"       //末级节点图标的颜色
		             }
		        });
		    });
		}
}
$(function(){
	TreeCls.init();
})