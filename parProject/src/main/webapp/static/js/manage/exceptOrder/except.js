var ExceptClas = {
		init : function(){
			this.initNode();
			this.dealExceptInit();
			this.query();
		},
		initNode : function(){
			this.$table = $("#LAY-user-back-manage");
		},
		dealExceptInit : function(){
			layui.use('table', function(){
				ExceptClas.$ObjectTable = layui.table;
				ExceptClas.$ObjectTable.render({
				    elem: ExceptClas.$table
				    ,url:ExceptClas.$table.attr('url')
				    ,cols: [[
				    	 {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'orderExceptId', title: '异常订单号', width:120}
					      ,{field: 'orderId', title: '关联订单号', width:130}
					      ,{field: 'exceptType', title: '异常类型', width: 100,templet:'#exceptType',style:'background-color: #009688; color: #fff;' }
					      ,{field: 'exceptStatus', title: '异常状态', width: 100,templet:'#exceptStatus',style:'background-color: #009688; color: #fff;' }
					      ,{field: 'orderAccount', title: '订单关联商户号', width: 160}
					      ,{field: 'exceptOrderAmount', title: '异常订单预处理金额', width: 160}
					      ,{field: 'explains', title: '异常订单说明', width: 230}
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
				 var orderExceptId = $('[name="orderExceptId"]').val();//获取输入框的值
				 var orderId = $('[name="orderId"]').val();//获取输入框的值
				 var orderAccount = $('[name="orderAccount"]').val();//获取输入框的值
				 var exceptStatus = $('[name="exceptStatus"]').val();//获取输入框的值
				 var exceptType = $('[name="exceptType"]').val();//获取输入框的值
				ExceptClas.reload({
					exceptType:exceptType,
					exceptStatus:exceptStatus,
					orderAccount:orderAccount,
					orderId:orderId,
					orderExceptId:orderExceptId
				})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				ExceptClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			ExceptClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'dealOrderList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}