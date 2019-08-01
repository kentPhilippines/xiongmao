var UserClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
		},
		initNode : function(){
			this.$UserAdd = $("[data-type='add']")
		},
		initData : function(){
			
		},
		bindEvent : function() {
			this.$UserAdd.on("click",this.UserAdd);
		},
		UserAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '360px';
			$title = '添加用户';
			UserClas.layuiOpen($url,$width,$higth,$title)
		},
		layuiOpen : function(url,width,higth,title){
			layer.open({
			      type: 2,
			      title:title,
			      shadeClose: true,
			      shade: false,
			      maxmin: true, //开启最大化最小化按钮
			      area: [width,higth],
			      content: url
			    });
		}
}
$(function(){
	UserClas.init()
})