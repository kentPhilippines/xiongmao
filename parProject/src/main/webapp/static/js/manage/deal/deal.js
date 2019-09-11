var DealOrderClas = {
		init : function(){
			this.initNode();
			this.dealOrderShowInit();
			this.query();
		},
		initNode : function(){
			this.$table = $("#LAY-user-back-manage");
		},
		dealOrderShowInit : function(){
			layui.use('table', function(){
				DealOrderClas.$ObjectTable = layui.table;
				DealOrderClas.$ObjectTable.render({
				    elem: DealOrderClas.$table
				    ,url:DealOrderClas.$table.attr('url')
				    ,cols: [[
				    	 {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'orderId', title: '交易订单编号', width:120}
					      ,{field: 'associatedId', title: '全局关联订单', width:130}
					      ,{field: 'orderStatus', title: '状态', width: 100,templet:'#orderStatus' , style:'background-color: #009688; color: #fff;'}
					      ,{field: 'dealAmount', title: '订单交易金额', width: 120}
					      ,{field: 'dealFee', title: '订单手续费', width: 120}
					      ,{field: 'actualAmount', title: '订单交易实际到账金额', width: 150}
					      ,{field: 'orderType', title: '订单类型', width: 115 ,templet:'#orderType'}
					      ,{field: 'orderAccount', title: '订单关联商户号', width: 135 }
					      ,{field: 'externalOrderId', title: '外部订单号', width: 135 }
					      ,{field: 'dealCardId', title: '入款账号', width: 135}
					      ,{field: 'dealChannel', title: '交易渠道', width: 135}
					      ,{field: 'dealDescribe', title: '交易备注', width: 135}
					      ,{field: 'createTime', title: '交易时间', width: 135, sort: true}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:230}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				DealOrderClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'notify'){
				      layer.confirm('确定这么做', function(index){
				    	var url = $("[lay-event='notify']").attr("url");
				    	 var deta =  {orderId : obj.data.orderId};
				    	CommonUtil.ObjextAjax(url,deta,DealOrderClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    }  
				    
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var orderId = $('[name="orderId"]').val();//获取输入框的值
				 var associatedId = $('[name="associatedId"]').val();//获取输入框的值
				 var dealCardId = $('[name="dealCardId"]').val();//获取输入框的值
				 var dealChannel = $('[name="dealChannel"]').val();//获取输入框的值
				 var orderStatus = $('[name="orderStatus"]').val();//获取输入框的值
				 var createTime = $('[name="createTime"]').val();//获取输入框的值
				DealOrderClas.reload({
					orderId:orderId,
					associatedId:associatedId,
					dealCardId:dealCardId,
					dealChannel:dealChannel,
					dealCardId:dealCardId,
					orderStatus:orderStatus,
					Time:createTime
				})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				DealOrderClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			DealOrderClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'dealOrderList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}
