/**
 * 登录JS
 * @author K
 * @date 2019-07-29
 * 请以后所有JS都按照此格式要求撰写
 */
var LoginCls = {
		init: function(){
			this.initNode();
			this.bindEvent();
		},
		initNode: function(){
			this.$LoginAjaxSucFn = this.SucFn;
			this.$LoginAjaxErrFn = this.errFn;
			this.$requestType = "post";//请求方式
			this.loginBut = $("[lay-filter = LAY-user-login-submit]");
		},
		bindEvent : function() {
			this.loginBut.on("click",this.login);
		},
		initData:function(){
			this.$LoginAjaxurl = ""+ctx+"/loginOnline";
			this.$loginForm = $("#loginForm").serialize();
		},
		login:function(event){
			LoginCls.initData();//获取最新数据
  			event.preventDefault();
  			if(!LoginCls.checkIsNull(LoginCls.$loginForm))
  				return;
			/**
	         * ajax封装
	         * @param url	JS连接
	         * @param data	数据对象
	         * @param sucFn	成功的方法
	         * @param isAsync	是否数据同步 true  or false    默认为true
	         * @param errFn	错误的方法
	         * @param requestType	请求类型    get post  delete put ...
	         */
  			CommonUtil.ObjextAjax(LoginCls.$LoginAjaxurl,LoginCls.$loginForm,LoginCls.$LoginAjaxSucFn,true,LoginCls.$LoginAjaxErrFn,LoginCls.$requestType)
		},
		/**
		 * 异步请求成功
		 */
		SucFn:function(data){
			if(data && data.success){
				window.location.href="index";
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		},
		/**
		 * 异步请求失败
		 * @param data 回调结果集
		 */
		errFn:function(data){
			layer.msg("服务器故障")
		},
		checkIsNull:function(object){
			$arr=object.split('&');
			for(var i=0;i<$arr.length;i++) {
				$length = $arr[i].split('=')
				if($length.length != 2 || $length[1] == null || $length[1] == ''){
					layer.msg("必传参数为空")
					return false;
				}
			}
			return true;
		}
}
$(function(){
	LoginCls.init()
})