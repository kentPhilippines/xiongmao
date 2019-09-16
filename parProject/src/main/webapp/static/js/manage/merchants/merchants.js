var Amount = {
		init : function(){
			this.initNode();
			this.bindEvent();
		},
		initNode : function(){
			this.$but = $(".layui-layer-btn0")
		},
		bindEvent : function() {
			this.$but.on("click",this.addAmount)
		},
		addAmount : function(){
			var deta = {
			userId : $("[name=userId]").val(),
			userName : $("[name=userName]").val(),
			accountId : $("[name=accountId]").val(),
			backCard : $("[name=backCard]").val(),
			amount: $("[name=amount]").val(),
			payPassword :$("[name=payPassword]").val()
			}
			var url = $(this).attr("url")
			CommonUtil.ObjextAjax(url,deta,Amount.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				parent.layer.closeAll();
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		}
}
var WithdrawalsRecordClas = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.WithdrawalsRecordClasShowInit();
			this.query();
		},
		initNode : function(){
			this.$table = $("#LAY-user-back-manage");
			this.$WithdrawalsAdd= $("[data-type='add']");
		},
		bindEvent : function() {
			this.$WithdrawalsAdd.on("click",this.WithdrawalsAdd);
		},
		WithdrawalsAdd : function(){
			var url = $(this).attr("addUrl")
			$width = '630px';
			$higth = '500px';
			$title = '申请提现';
			WithdrawalsRecordClas.layuiOpen(url,$width,$higth,$title);
		},
		WithdrawalsRecordClasShowInit : function(){
			layui.use('table', function(){
				WithdrawalsRecordClas.$ObjectTable = layui.table;
				WithdrawalsRecordClas.$ObjectTable.render({
				    elem: WithdrawalsRecordClas.$table
				    ,url:WithdrawalsRecordClas.$table.attr('url')
				    ,cols: [[
				    	  {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'orderId', title: '代付订单编号', width:120}
					      ,{field: 'associatedId', title: '全局关联订单', width:130}
					      ,{field: 'merchantsStatus', title: '状态', width: 100}
					      ,{field: 'amount', title: '订单代付金额', width: 120}
					      ,{field: 'approver', title: '审核人', width: 120}
					      ,{field: 'ip', title: '代付IP', width: 150}
					      ,{field: 'note', title: '代付备注', width: 115}
					      ,{field: 'createTime', title: '代付时间', width: 135, sort: true}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				});
		},
		layuiOpen : function(url,width,higth,title){
			layer.open({
				type: 2,
				title:title,
				shade: 0.5,
				id:'LAY_layuipro',
				shadeClose: true,
				shade: false,
				maxmin: true, //开启最大化最小化按钮
				area: [width,higth],
				content: url,
				success: function(){
					  },
				end : function(){
					lock = true
					WithdrawalsRecordClas.reload({"":""});
				}
			});
	},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var orderId = $('[name="orderId"]').val();//获取输入框的值
				 var associatedId = $('[name="associatedId"]').val();//获取输入框的值
				 var accountId = $('[name="accountId"]').val();//获取输入框的值
				 var retain1 = $('[name="retain1"]').val();//获取输入框的值
				 var approver = $('[name="approver"]').val();//获取输入框的值
				 var createTime = $('[name="createTime"]').val();//获取输入框的值
				WithdrawalsRecordClas.reload({
					orderId:orderId,
					associatedId:associatedId,
					retain1:retain1,
					approver:approver,
					Time:createTime
				})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				WithdrawalsRecordClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			WithdrawalsRecordClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'withdrawalsRecordList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}
var MerchantsClas = {
		init : function(){
			this.initNode();
			this.merchantsClasShowInit();
			this.query();
		},
		initNode : function(){
			this.$table = $("#LAY-user-back-manage");
		},
		merchantsClasShowInit : function(){
			layui.use('table', function(){
				MerchantsClas.$ObjectTable = layui.table;
				MerchantsClas.$ObjectTable.render({
				    elem: MerchantsClas.$table
				    ,url:MerchantsClas.$table.attr('url')
				    ,cols: [[
				    	  {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'orderId', title: '代付订单编号', width:120}
					      ,{field: 'associatedId', title: '全局关联订单', width:130}
					      ,{field: 'orderStatus', title: '状态', width: 100}
					      ,{field: 'withdrawalsAmount', title: '订单代付金额', width: 120}
					      ,{field: 'withdrawalsFee', title: '订单代付手续费', width: 120}
					      ,{field: 'actualAmount', title: '订单代付实际到账金额', width: 150}
					      ,{field: 'orderType', title: '订单类型', width: 115}
					      ,{field: 'orderAccount', title: '订单关联商户号', width: 135 }
					      ,{field: 'externalOrderId', title: '外部订单号', width: 135 }
					      ,{field: 'bankCard', title: '代付银行卡', width: 135}
					      ,{field: 'cardholder', title: '代付银行卡持卡人', width: 135}
					      ,{field: 'createTime', title: '交易时间', width: 135, sort: true}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var orderId = $('[name="orderId"]').val();//获取输入框的值
				 var associatedId = $('[name="associatedId"]').val();//获取输入框的值
				 var bankCard = $('[name="bankCard"]').val();//获取输入框的值
				 var orderAccount = $('[name="orderAccount"]').val();//获取输入框的值
				 var orderStatus = $('[name="orderStatus"]').val();//获取输入框的值
				 var createTime = $('[name="createTime"]').val();//获取输入框的值
				MerchantsClas.reload({
					orderId:orderId,
					associatedId:associatedId,
					bankCard:bankCard,
					orderAccount:orderAccount,
					orderStatus:orderStatus,
					Time:createTime
				})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				MerchantsClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			MerchantsClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'merchantsList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}