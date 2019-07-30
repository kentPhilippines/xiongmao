/**
 * 首页JS
 * @author K
 * @date 2019-07-29
 * 请以后所有JS都按照此格式要求撰写
 */
var removeClassTopShow = false;//是否删除topShow展示
var Index = {
		init : function() {// 入口
			this.initNode();
			this.initData();
			this.bindEvent();
		},
		initNode :function(){
			this.$mainFrame = $("#mainFrame");
			this.$item = $(".layui-bg-black li a");
			this.$layuiShow = $(".upbit");
			this.$layuiLayoutLeft = $(".layui-layout-left li");
			this.$topLayuiShow = $(".topLayuiShow");
		},
		initData : function() {//初始化数据
		},
		bindEvent : function() {// 事件绑定
			this.$item.on("click",this.itemClick);
			this.$item.on("mouseover",this.move);
			this.$layuiShow.on("mouseover",this.toplayuiShow);
			this.$layuiShow.on("mouseout",this.toplayuiOut);
			this.$layuiLayoutLeft.on("mouseover",this.here);
			this.$layuiLayoutLeft.on("mouseout",this.exit);
		},
		/**
		 * 菜单点击事件
		 */
		itemClick:function(){
			 if($(this).parent().is(".layui-nav-itemed")){ 
					$(".layui-bg-black li a").css("background","")
					$(this).parent().removeClass("layui-nav-itemed")
					$(this).css("background","#009688")
				}else{
					$(".layui-bg-black li a").attr("thisMe","")
					$(".layui-bg-black li a").css("background","")
					$(this).parent().addClass("layui-nav-itemed")
					$(this).css("background","#009688")
					$(this).attr("thisMe","thisMe")
				} 
		},
		/**
		 * 菜单鼠标移动事件
		 */
		move:function(){
			var background = $("[thisMe='thisMe']")
			$(".layui-bg-black li a").css("background","")
			background.css("background","#009688")
			$(this).css("background","#909090")
		},
		toplayuiShow:function(){
			Index.$topLayuiShow.addClass("layui-show");
			$(".upbit-more").addClass("layui-nav-mored");
		},
		toplayuiOut:function(event){
			Index.sleep(200)
			Index.$topLayuiShow.on("mouseover",function(){
				return
			})
			Index.$topLayuiShow.on("mouseout",function(){
				Index.$topLayuiShow.removeClass("layui-show");
				$(".upbit-more").removeClass("layui-nav-mored");
			})
			Index.$topLayuiShow.removeClass("layui-show");
			$(".upbit-more").removeClass("layui-nav-mored");
		},
		here:function(){
			$(this).addClass("layui-this")
		},
		exit:function(){
			$(this).removeClass("layui-this")
		},
		sleep :function(n) {
		    var start = new Date().getTime();
		    while (true) {
		        if (new Date().getTime() - start > n) {
		            break;
		        }
		    }
		},
}
$(function(){
	Index.init();
})