var UpdataPayPasswordShow = {
		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			this.$but = $("[data-type='updataPassword']");
		},
		bindEvent : function(){
			this.$but.on("click",this.open)
		},
		open : function(){
			$url = $(this).attr("Url");
			$width = '430px';
			$higth = '500px';
			$title = '修改密码';
		layer.open({
			type: 2,
			title:$title,
			shadeClose: true,
			shade: false,
			shade: 0.5,
			id:'LAY_layuipro',
			maxmin: true, //开启最大化最小化按钮
			area: [$width,$higth],
			content: $url,
			success: function(){
			},
			end : function(){
				window.location.reload();
			}
		});
		}
}
var UpdataPassWord = {
		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			this.$but = $("[data-type='updataPassword']");
		},
		bindEvent : function(){
			this.$but.on("click",this.open)
		},
		open : function(){
			$url = $(this).attr("Url");
			$width = '430px';
			$higth = '500px';
			$title = '修改密码';
		layer.open({
			type: 2,
			title:$title,
			shadeClose: true,
			shade: false,
			shade: 0.5,
			id:'LAY_layuipro',
			maxmin: true, //开启最大化最小化按钮
			area: [$width,$higth],
			content: $url,
			success: function(){
			},
			end : function(){
				window.location.reload();
			}
		});
		}
}
var AccountlAmountDel = {
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
			AccountlAmountDel.initData();
			if(AccountlAmountDel.checkParam()){
				 $("[name='dealDescribe']").val("");
				 $("[name='amount']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),AccountlAmountDel.$data,AccountlAmountDel.AjaxSucFn,true,"暂无权限","post")
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				parent.layer.closeAll();
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		},
		checkParam:function(){
			$pass1 = $("[name='amount']").val();
			$pass2 = $("[name='dealDescribe']").val();
			if(!$pass1 ||!$pass2){
				layer.msg("必填参数为空");
				return true;
			}
			return false;
		}
}


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
			if(AccountlAmountAdd.checkParam()){
				 $("[name='dealDescribe']").val("");
				 $("[name='amount']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),AccountlAmountAdd.$data,AccountlAmountAdd.AjaxSucFn,true,"暂无权限","post")
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				parent.layer.closeAll();
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		},
		checkParam:function(){
			$pass1 = $("[name='amount']").val();
			$pass2 = $("[name='dealDescribe']").val();
			if(!$pass1 ||!$pass2){
				layer.msg("必填参数为空");
				return true;
			}
			return false;
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
					havaInterface:$("[name=havaInterface]").val(),
					isDeal:$("[name=isDeal]").val(),
					bankCard:$("[name=bankCard]").val()
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