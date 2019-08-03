var UserAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$accountAjaxSucFn = this.SucFn;
			this.$accountAjaxErrFn = "暂无权限";
			this.$addUserAjaxSucFn = this.AjaxSucFn;
			this.$UserName = $("[name = userId]");
			this.$unselect = $(".layui-unselect");
			this.$requestType = "post";//请求方式
			this.$but = $("#Account");
			this.$enter = $(".layui-layer-btn0");
			
		},
		initData : function(){
			this.$user = $("#UserForm").serialize();
		},
		bindEvent : function(){
			this.$but.on("click",this.account);
			this.$unselect.on("click",this.addClass)
			this.$enter.on("click",this.addUser)
		},
		addUser : function(){
			UserAddCls.initData();
			if(UserAddCls.checkParam()){
				 $("[name='userPassword']").val("");
				 $("[name='password']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),UserAddCls.$user,UserAddCls.$addUserAjaxSucFn,true,UserAddCls.$accountAjaxErrFn,UserAddCls.$requestType)
		},
		addClass:function(){
			if($(this).hasClass("layui-form-onswitch")){
				$(this).removeClass("layui-form-onswitch");
				$("[em='em']").html("无效")
			} else {
				$(this).addClass("layui-form-onswitch");
				$("[em='em']").html("有效")
			};
		},
		account : function(){
			/**
	         * ajax封装
	         * @param url	JS连接
	         * @param data	数据对象
	         * @param sucFn	成功的方法
	         * @param isAsync	是否数据同步 true  or false    默认为true
	         * @param errFn	错误的方法
	         * @param requestType	请求类型    get post  delete put ...
	         */
  			CommonUtil.ObjextAjax($(this).attr("url"),"account",UserAddCls.$accountAjaxSucFn,true,UserAddCls.$accountAjaxErrFn,UserAddCls.$requestType)
		},
		SucFn :function(data){
			if(data && data.success){
				UserAddCls.$UserName.val(data.result)
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		},
		checkParam : function(){
			debugger;
			$pass1 = $("[name='userPassword']").val();
			$pass2 = $("[name='password']").val();
			if(!$pass1){
				layer.msg("参数为空");
				return true;
			}
			if(!$pass2){
				layer.msg("参数为空");
				return true;
			}
			if($pass1 != $pass2){
				 layer.msg("密码不一致");
				return true;
			}
			return false;
			
		},
		AjaxSucFn : function(data){
			debugger;
			if(data && data.success){
				layer.msg(data.message)
				parent.layer.closeAll();
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		}
}
var UserClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
		},
		initNode : function(){
			this.$UserAdd = $("[data-type='add']")
		},
		bindEvent : function() {
			this.$UserAdd.on("click",this.UserAdd);
		},
		UserAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加用户';
			UserClas.layuiOpen($url,$width,$higth,$title)
		},
		layuiOpen : function(url,width,higth,title){
			
			debugger;
		var lock  = true;//防止重复弹出,加锁
			if(lock){
				layer.open({
					type: 2,
					title:title,
					shadeClose: true,
					shade: false,
					maxmin: true, //开启最大化最小化按钮
					area: [width,higth],
					content: url,
					success: function(){
						lock  = false;
						  },
					end : function(){
						lock = true
						location.reload();
					}
				});
			}
		}
}
$(function(){
	UserClas.init()
	UserAddCls.init();
})