var RunOrderClas = {
		init : function(){
			this.initNode();
			this.runOrderClasShowInit();
			this.query();
		},
		initNode : function(){
			this.$table = $("#LAY-user-back-manage");
		},
		runOrderClasShowInit : function(){
			layui.use('table', function(){
				RunOrderClas.$ObjectTable = layui.table;
				RunOrderClas.$ObjectTable.render({
				    elem: RunOrderClas.$table
				    ,url:RunOrderClas.$table.attr('url')
				    ,cols: [[
				    	 {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'orderRunId', title: '流水订单号', width:120}
					      ,{field: 'runType', title: '流水类型', width: 100,templet:'#runType', style:'background-color: #009688; color: #fff;'}
					      ,{field: 'runStatus', title: '流水状态', width: 100,templet:'#runStatus'}
					      ,{field: 'orderAccount', title: '关联商户号', width: 120}
					      ,{field: 'runOrderAmount', title: '流水金额', width: 115}
					     ,{field: 'cardRunD', title: '出账账号',templet:'#cardRunD', width: 135}
					      ,{field: 'cardRunW', title: '入账账号', templet:'#cardRunW',width: 135}
					      ,{field: 'dealDescribe', title: '流水描述', width: 135}
					      ,{field: 'createTime', title: '生成流水时间', width: 135, sort: true}
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
				 var orderRunId = $('[name="orderRunId"]').val();//获取输入框的值
				 var cardRunD = $('[name="cardRunD"]').val();//获取输入框的值
				 var cardRunW = $('[name="cardRunW"]').val();//获取输入框的值
				 var orderAccount = $('[name="orderAccount"]').val();//获取输入框的值
				 var runType = $('[name="runType"]').val();//获取输入框的值
				 var createTime = $('[name="createTime"]').val();//获取输入框的值
				RunOrderClas.reload({
					orderRunId:orderRunId,
					cardRunD:cardRunD,
					cardRunW:cardRunW,
					orderAccount:orderAccount,
					runType:runType,
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
			RunOrderClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'orderRunList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}