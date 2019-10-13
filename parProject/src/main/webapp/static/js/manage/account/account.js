var AccountUserAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$AjaxErrFn = "暂无权限";
			this.$addAccountUserAjaxSucFn = this.AjaxSucFn;
			this.$unselect = $(".layui-unselect");
			this.$requestType = "post";//请求方式
			this.$enter = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$AccountUserForm = $("#AccountUserForm").serialize();
		},
		bindEvent : function(){
			this.$enter.on("click",this.addAccountUser)
		},
		addAccountUser : function(){
			AccountUserAddCls.initData();
			if(AccountUserAddCls.checkParam()){
				 $("[name='accountId']").val("");
				 $("[name='userId']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),
					AccountUserAddCls.$AccountUserForm,
					AccountUserAddCls.$addAccountUserAjaxSucFn, true,
					AccountUserAddCls.$AjaxErrFn,
					AccountUserAddCls.$requestType)
		},
		checkParam : function(){
			$pass1 = $("[name='accountId']").val();
			$pass8 = $("[name='userId']").val();
			if(!$pass1  || !$pass8){
				layer.msg("参数为空");
				return true;
			}
			return false;
			
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message)
				parent.layer.closeAll();
				return;
			}else if(data && !data.success){
				layer.msg(""+data.message)
				return;
			}
		}
}




var AccountUserClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this. AccountShowInit();
			this.query();
		},
		initNode : function(){
			this.$AccountUserAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$AccountUserAdd.on("click",this.AccountUserAdd);
		},
		AccountUserAdd : function(){
			var url = $(this).attr("addUrl")
			$width = '630px';
			$higth = '500px';
			$title = '关联账户';
			AccountUserClas.layuiOpen(url,$width,$higth,$title);
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
						AccountUserClas.reload({"":""});
					}
				});
		},
		layuiOpen : function(url,width,higth,title){
			var lock  = true;//防止重复弹出,加锁
				if(lock){
					layer.open({
						type: 2,
						title:title,
						shadeClose: true,
						shade: 0.5,
						id:'LAY_layuipro',
						shade: false,
						maxmin: true, //开启最大化最小化按钮
						area: [width,higth],
						content: url,
						success: function(){
							lock  = false;
							  },
						end : function(){
							AccountUserClas.reload({"":""});
						}
					});
				}
			},
		AccountShowInit : function(){
			layui.use('table', function(){
				AccountUserClas.$ObjectTable = layui.table;
				AccountUserClas.$ObjectTable.render({
				    elem: AccountUserClas.$table
				    ,url:AccountUserClas.$table.attr('url')
				    ,cols: [[
				    	   {field: 'id', title: 'id', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'accountId', title: '商户编号', width:130}
					      ,{field: 'accountName', title: '商户名称', width:160}
					      ,{field: 'userId', title: '账户id', width: 130}
					      ,{field: 'userName', title: '账户昵称', width: 130}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:230}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				AccountUserClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {id : obj.data.id};
				    	CommonUtil.ObjextAjax(url,deta,AccountUserClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    }      
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var accountId = $('[name="accountId"]').val();//获取输入框的值
				 var userId = $('[name="userId"]').val();//获取输入框的值
				AccountUserClas.reload({accountId:accountId,userId:userId})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				AccountUserClas.reload({"":""});
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		},
		 reload:function(param){
			AccountUserClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'userAccountList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}













var AccountFeeEditCls = {
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
					id:$("[name=id]").val(),
					accountChannel:$("[name=accountChannel]").val(),
					channelProduct:$("[name=channelProduct]").val(),
					accountFee:$("[name=accountFee]").val(),
					accountCost:$("[name=accountCost]").val(),
					withdrawal:$("[name=withdrawal]").val(),
					withdrawalCost:$("[name=withdrawalCost]").val(),
					feeStautus:$("[name=feeStautus]").val(),
					settlementType:$("[name=settlementType]").val(),
					accountSette:$("[name=accountSette]").val()
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.AccountFeeEdit)
		},
		AccountFeeEdit : function(){
			AccountFeeEditCls.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),
					AccountFeeEditCls.$data,
					AccountFeeEditCls.AjaxSucFn,true,"暂无权限","post")
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

var AccountFeeAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$AjaxErrFn = "暂无权限";
			this.$addAccountFeeAjaxSucFn = this.AjaxSucFn;
			this.$unselect = $(".layui-unselect");
			this.$requestType = "post";//请求方式
			this.$enter = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$AccountFeeForm = $("#AccountFeeForm").serialize();
		},
		bindEvent : function(){
			this.$enter.on("click",this.addAccountFee)
		},
		addAccountFee : function(){
			AccountFeeAddCls.initData();
			if(AccountFeeAddCls.checkParam()){
				 $("[name='accountId']").val("");
				 $("[name='accountChannel']").val("");
				 $("[name='channelProduct']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),
					AccountFeeAddCls.$AccountFeeForm,
					AccountFeeAddCls.$addAccountFeeAjaxSucFn, true,
					AccountFeeAddCls.$AjaxErrFn,
					AccountFeeAddCls.$requestType)
		},
		checkParam : function(){
			$pass1 = $("[name='accountId']").val();
			$pass2 = $("[name='accountChannel']").val();
			$pass3 = $("[name='channelProduct']").val();
			$pass4 = $("[name='accountFee']").val();
			$pass5 = $("[name='accountCost']").val();
			$pass6 = $("[name='withdrawal']").val();
			$pass7 = $("[name='withdrawalCost']").val();
			$pass8 = $("[name='feeStautus']").val();
			if(!$pass1 ||!$pass2 ||!$pass3 ||!$pass4 ||!$pass5 ||!$pass6 ||!$pass7 ||!$pass8){
				layer.msg("参数为空");
				return true;
			}
			return false;
			
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message)
				parent.layer.closeAll();
				return;
			}else if(data && !data.success){
				layer.msg(""+data.message)
				return;
			}
		}
}

var AccountFeeClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this. accountFeeShowInit();
			this.query();
		},
		initNode : function(){
			this.$AccountFeeAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$AccountFeeAdd.on("click",this.AccountFeeAdd);
		},
		AccountFeeAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '800px';
			$title = '添加用户';
			AccountFeeClas.layuiOpen($url,$width,$higth,$title)
		},
		layuiOpen : function(url,width,higth,title){
			layer.open({
				type: 2,
				title:title,
				shadeClose: true,
				shade: false,
				shade: 0.5,
				id:'LAY_layuipro',
				maxmin: true, //开启最大化最小化按钮
				area: [width,higth],
				content: url,
				success: function(){
				},
				end : function(){
					AccountFeeClas.reload({"":""});
				}
			});
		},
		accountFeeShowInit : function(){
			layui.use('table', function(){
				AccountFeeClas.$ObjectTable = layui.table;
				AccountFeeClas.$ObjectTable.render({
					elem: AccountFeeClas.$table
					,url:AccountFeeClas.$table.attr('url')
					,cols: [[
						{field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
						,{field: 'accountId', title: '商户编号', width:160}
						,{field: 'accountChannel', title: '账户渠道', width:160}
						,{field: 'channelProduct', title: '产品类型', width: 160}
						,{field: 'accountFee', title: '费率', width: 130}
						,{field: 'accountCost', title: '费率成本', width: 130}
						,{field: 'withdrawal', title: '取款手续费', width: 130}
						,{field: 'accountSette', title: '冻结百分比', width: 130}
						,{field: 'settlementType', title: '冻结类型', width: 130}
						,{field: 'withdrawalCost', title: '取款手续费成本', width: 160}
						,{field: 'feeStautus', title: '费率状态', width: 130,templet:'#feeStautus'}
						,{field: 'createTime', title: '创建时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
						,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
						]]
				, id: 'mytable'
					,page: true
				});
				//监听行工具事件
				AccountFeeClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
					var data = obj.data;
					if(obj.event === 'del'){
						layer.confirm('真的删除行么', function(index){
							var url = $("[lay-event='del']").attr("url");
							var deta =  {
									id:obj.data.id,
							};
							CommonUtil.ObjextAjax(
									url,deta,AccountFeeClas.AjaxSucFn,
									true,'无权限或网络错误，请联系开发人员处理','post');
						});
					} else if(obj.event === 'edit'){
						var url = $("[lay-event='edit']").attr("url");
						url = url +   '?id='+ obj.data.id 
						$width = '630px';
						$higth = '500px';
						$title = '商户费率修改';
						AccountFeeClas.layuiOpen(url,$width,$higth,$title);
					}  
				});
			});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var accountId = $('[name="accountId"]').val();//获取输入框的值
				 var accountChannel = $('[name="accountChannel"]').val();//获取输入框的值
				 var channelProduct = $('[name="channelProduct"]').val();//获取输入框的值
				AccountFeeClas.reload({
					accountId:accountId,
					accountChannel:accountChannel,
					channelProduct:channelProduct
				})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				AccountClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		},
		 reload:function(param){
			AccountFeeClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'accountFeeList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}





var AccountClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this. AccountShowInit();
			this.query();
		},
		initNode : function(){
			this.$AccountAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$AccountAdd.on("click",this.AccountAdd);
		},
		AccountAdd : function(){
			var deta = {};
			var url = $(this).attr("addUrl")
			CommonUtil.ObjextAjax(url,deta,AccountClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
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
						AccountClas.reload({"":""});
					}
				});
		},
		layuiOpen : function(url,width,higth,title){
			var lock  = true;//防止重复弹出,加锁
				if(lock){
					layer.open({
						type: 2,
						title:title,
						shadeClose: true,
						shade: 0.5,
						id:'LAY_layuipro',
						shade: false,
						maxmin: true, //开启最大化最小化按钮
						area: [width,higth],
						content: url,
						success: function(){
							lock  = false;
							  },
						end : function(){
							lock = true
							AccountClas.reload({"":""});
						}
					});
				}
			},
		AccountShowInit : function(){
			layui.use('table', function(){
				AccountClas.$ObjectTable = layui.table;
				AccountClas.$ObjectTable.render({
				    elem: AccountClas.$table
				    ,url:AccountClas.$table.attr('url')
				    ,cols: [[
				    	   {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'accountId', title: '商户编号', width:130}
					      ,{field: 'accountName', title: '商户名称', width:160}
					      ,{field: 'cashBalance', title: '可提现余额', width: 130}
					      ,{field: 'freezeBalance', title: '冻结资金', width: 130}
					      ,{field: 'accountBalance', title: '总余额', width: 130}
					      ,{field: 'dayDealAmountMax', title: '商户日交易额度', width: 130}
					      ,{field: 'dayDealAmountMin', title: '交易最低限额', width: 130}
					      ,{field: 'sumDealAmount', title: '累计交易额', width: 130}
					      ,{field: 'sumDealToDayAmount', title: '当天累计交易额', width: 130}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:230}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				AccountClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {accountId : obj.data.accountId};
				    	CommonUtil.ObjextAjax(url,deta,AccountClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	var url = $("[lay-event='edit']").attr("url");
				    	url = url +   '?accountId='+obj.data.accountId
				    	window.location.href=url;
				    }   else if(obj.event === 'addAmount'){//加钱
				    	var url = $("[lay-event='addAmount']").attr("url");
				    	url = url +   '?accountId='+obj.data.accountId
						$width = '630px';
						$higth = '500px';
						$title = '给用户加钱';
						AccountClas.layuiOpen(url,$width,$higth,$title);
				    } else if(obj.event === 'amountDel'){//减钱
				    	var url = $("[lay-event='amountDel']").attr("url");
				    	url = url +   '?accountId='+obj.data.accountId
						$width = '630px';
						$higth = '500px';
						$title = '冻结';
						AccountClas.layuiOpen(url,$width,$higth,$title);
				    } else if(obj.event === 'amountFre'){//冻结
						var url = $("[lay-event='amountFre']").attr("url");
						url = url +   '?accountId='+obj.data.accountId
						$width = '630px';
						$higth = '500px';
						$title = '冻结';
						AccountClas.layuiOpen(url,$width,$higth,$title);
				}
				    
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var accountId = $('[name="accountId"]').val();//获取输入框的值
				 var accountName = $('[name="accountName"]').val();//获取输入框的值
				AccountClas.reload({accountId:accountId,accountName:accountName})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				AccountClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		},
		 reload:function(param){
			AccountClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'accountList'//后台做模糊搜索接口路径
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
