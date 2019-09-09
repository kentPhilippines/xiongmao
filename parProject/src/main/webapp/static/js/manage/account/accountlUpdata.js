var AccountlAmountAdd = {
		init : function(){
			this.initNode();
			this.bindEvent();
		},
		initNode : function(){
			this.$but = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$data ={
					accountId:$("[name=accountId]").val(),
					dealDescribe:$("[name=dealDescribe]").val(),
					amount:$("[name=amount]").val()
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.amountAdd)
		},
		amountAdd : function(){
			AccountlAmountAdd.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),AccountlAmountAdd.$data,AccountlAmountAdd.AjaxSucFn,true,"暂无权限","post")
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
var AccountlEdit = {
		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			this.$but = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$data ={
					accountId:$("[name=accountId]").val(),
					accountName:$("[name=accountName]").val(),
					dayDealAmountMin:$("[name=dayDealAmountMin]").val(),
					dayDealAmountMax:$("[name=dayDealAmountMax]").val(),
					accountType:$("[name=accountType]").val(),
					havaInterface:$("[name=havaInterface]").val()
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.AccountEdit)
		},
		AccountEdit : function(){
			AccountlEdit.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),AccountlEdit.$data,AccountlEdit.AjaxSucFn,true,"暂无权限","post")
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				 history.back();
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		}
}