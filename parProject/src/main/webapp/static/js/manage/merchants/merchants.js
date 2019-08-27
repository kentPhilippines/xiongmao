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