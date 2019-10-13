var DpayShow = {
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
				DpayShow.$ObjectTable = layui.table;
				DpayShow.$ObjectTable.render({
				    elem: DpayShow.$table
				    ,url:DpayShow.$table.attr('url')
				    ,cols: [[
				    	  {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'orderId', title: '代付订单编号', width:120}
					      ,{field: 'associatedId', title: '全局关联订单', width:130}
					      ,{field: 'orderStatus', title: '状态', width: 100,templet:'#orderStatus' , style:'background-color: #009688; color: #fff;'}
					      ,{field: 'withdrawalsAmount', title: '订单代付金额', width: 120}
					      ,{field: 'withdrawalsFee', title: '订单代付手续费', width: 120}
					      ,{field: 'actualAmount', title: '订单代付实际到账金额', width: 150}
					      ,{field: 'orderAccount', title: '订单关联商户号', width: 135 }
					      ,{field: 'bankCard', title: '代付银行卡', width: 135}
					      ,{field: 'cardholder', title: '代付银行卡持卡人', width: 135}
					      ,{field: 'createTime', title: '交易时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
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
				DpayShow.reload({
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
				DpayShow.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			DpayShow.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'dPayList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}
var IsCleck = {
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
			accountId : $("[name=accountId]").val(),
			accountName : $("[name=accountName]").val(),
			backCard1 : $("[name=bankCard3]").val(),
			backCard4 : $("[name=bankCard2]").val(),
			orderId : $("[name=orderId]").val(),
			amount: $("[name=amount]").val(),
			payPassword :$("[name=payPassword]").val()
			}
			var url = $(this).attr("url")
			if(IsCleck.checkParam()){
				 $("[name='backCard1']").val("");
				 $("[name='backCard2']").val("");
				 $("[name='payPassword']").val("");
				 return;
			};
			CommonUtil.ObjextAjax(url,deta,IsCleck.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
		},
		checkParam : function(){
			debugger;
			$pass1 = $("[name='bankCard1']").val();
			$pass2 = $("[name='bankCard3']").val();
			$pass3 = $("[name='payPassword']").val();
			$pass4 = $("[name='bankCard2']").val();
			if($pass1 != $pass2){
				layer.msg("到账银行卡错误，请和下游商户仔细确认");
				return true;
			}
			if(!$pass1  || !$pass2 || !$pass3|| !$pass4){
				layer.msg("参数为空");
				return true;
			}
			return false;
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
				parent.layer.closeAll();
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
					      ,{field: 'orderId', title: '代付记录编号', width:120}
					      ,{field: 'associatedId', title: '全局关联订单', width:130}
					      ,{field: 'merchantsStatus', title: '状态', width: 100,templet:'#merchantsStatus',style:'background-color: #009688; color: #fff;'}
					      ,{field: 'amount', title: '订单代付金额', width: 120}
					      ,{field: 'approver', title: '审核人', width: 120}
					      ,{field: 'ip', title: '代付IP', width: 150}
					      ,{field: 'note', title: '代付备注', width: 230}
					      ,{field: 'createTime', title: '代付时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
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
					      ,{field: 'orderStatus', title: '状态', width: 100,templet:'#orderStatus' , style:'background-color: #009688; color: #fff;'}
					      ,{field: 'withdrawalsAmount', title: '订单代付金额', width: 120}
					      ,{field: 'withdrawalsFee', title: '订单代付手续费', width: 120}
					      ,{field: 'actualAmount', title: '订单代付实际到账金额', width: 150}
					      ,{field: 'orderType', title: '订单类型', width: 115,templet:'#orderType' , style:'background-color: #009688; color: #fff;'}
					      ,{field: 'orderAccount', title: '订单关联商户号', width: 135 }
					      ,{field: 'externalOrderId', title: '外部订单号', width: 135 }
					      ,{field: 'bankCard', title: '代付银行卡', width: 135}
					      ,{field: 'cardholder', title: '代付银行卡持卡人', width: 135}
					      ,{field: 'createTime', title: '交易时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:230}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				MerchantsClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'isCheck'){
				    	var url = $("[lay-event='isCheck']").attr("url");
				    	 url  += "?id="+obj.data.id
				    	 $width = '630px';
							$higth = '500px';
							$title = '申请提现';
							MerchantsClas.layuiOpen(url,$width,$higth,$title);
				    }else if(obj.event === 'isDie'){
					      layer.confirm('确定这么做', function(index){
					    	var url = $("[lay-event='isDie']").attr("url");
					    	 var deta =  {id : obj.data.id};
					    	CommonUtil.ObjextAjax(url,deta,MerchantsClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
					      });
					    
				    }
				    
				  });
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
					MerchantsClas.reload({"":""});
				}
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
function Format (datetime,fmt) {
	  if (parseInt(datetime)==datetime) {
	    if (datetime.length==10) {
	      datetime=parseInt(datetime)*1000;
	    } else if(datetime.length==13) {
	      datetime=parseInt(datetime);
	    }
	  }
	  datetime=new Date(datetime);
	  var o = {
	  "M+" : datetime.getMonth()+1,                 //月份   
	  "d+" : datetime.getDate(),                    //日   
	  "h+" : datetime.getHours(),                   //小时   
	  "m+" : datetime.getMinutes(),                 //分   
	  "s+" : datetime.getSeconds(),                 //秒   
	  "q+" : Math.floor((datetime.getMonth()+3)/3), //季度   
	  "S"  : datetime.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	  fmt=fmt.replace(RegExp.$1, (datetime.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	  if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;
	}