
var ChannelFeeEditCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			this.$but = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$data ={
					channelNo : $("[name=channelNo]").val(),
					channelName:$("[name=channelName]").val(),
					payType:$("[name=payType]").val(),
					fee:$("[name=fee]").val(),
					status:$("[name=status]").val(),
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.ChannelEdit)
		},
		ChannelEdit : function(){
			ChannelFeeEditCls.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),ChannelFeeEditCls.$data,ChannelFeeEditCls.AjaxSucFn,true,"暂无权限","post")
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				parent.layer.closeAll();
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		}
}


var Channel = {
		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			this.$but = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$data ={
					channelNo : $("[name=channelNo]").val(),
					channelName:$("[name=channelName]").val(),
					channelAccount:$("[name=channelAccount]").val(),
					channelStautus:$("[name=channelStautus]").val(),
					channelType:$("[name=channelType]").val()
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.ChannelEdit)
		},
		ChannelEdit : function(){
			Channel.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),Channel.$data,Channel.AjaxSucFn,true,"暂无权限","post")
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				parent.layer.closeAll();
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		}
}