var ChannelFeeAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$AjaxErrFn = "暂无权限";
			this.$addChannelFeeAjaxSucFn = this.AjaxSucFn;
			this.$unselect = $(".layui-unselect");
			this.$requestType = "post";//请求方式
			this.$enter = $(".layui-layer-btn0");
			
		},
		initData : function(){
			this.$chanenlFeeForm = $("#ChanenlFeeForm").serialize();
		},
		bindEvent : function(){
			this.$enter.on("click",this.addChannelFee)
		},
		addChannelFee : function(){
			ChannelFeeAddCls.initData();
			if(ChannelFeeAddCls.checkParam()){
				$("[name='fee']").val("");
				$("[name='settle']").val("");
				return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),
					ChannelFeeAddCls.$chanenlFeeForm,
					ChannelFeeAddCls.$addChannelFeeAjaxSucFn,true,
					ChannelFeeAddCls.$AjaxErrFn,
					ChannelFeeAddCls.$requestType)
		},
		checkParam : function(){
			debugger;
			$pass1 = $("[name='channelNo']").val();
			$pass2 = $("[name='payType']").val();
			$pass3 = $("[name='fee']").val();
			$pass4 = $("[name='settle']").val();
			if(!$pass1 ||!$pass2 || !$pass3 || !$pass4){
				layer.msg("参数为空");
				return true;
			}
			return false;
			
		},
		AjaxSucFn : function(data){
			debugger;
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
var ChannelPayTypeAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$AjaxErrFn = "暂无权限";
			this.$addChannePayTypeAjaxSucFn = this.AjaxSucFn;
			this.$unselect = $(".layui-unselect");
			this.$requestType = "post";//请求方式
			this.$enter = $(".layui-layer-btn0");
			
		},
		initData : function(){
			this.$ChanenlPayTypeForm = $("#ChanenlPayTypeForm").serialize();
		},
		bindEvent : function(){
			this.$enter.on("click",this.addBankCard)
		},
		addBankCard : function(){
			ChannelPayTypeAddCls.initData();
			if(ChannelPayTypeAddCls.checkParam()){
				$("[name='payTypeNo']").val("");
				$("[name='payTypeName']").val("");
				return;
			};
			CommonUtil.ObjextAjax(
					$(this).attr("url"),
					ChannelPayTypeAddCls.$ChanenlPayTypeForm,
					ChannelPayTypeAddCls.$addChannePayTypeAjaxSucFn,true,
					ChannelPayTypeAddCls.$AjaxErrFn,
					ChannelPayTypeAddCls.$requestType)
		},
		checkParam : function(){
			debugger;
			$pass1 = $("[name='payTypeNo']").val();
			$pass2 = $("[name='payTypeName']").val();
			if(!$pass1 ||!$pass2  ){
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
var ChannelAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$AjaxErrFn = "暂无权限";
			this.$addChannelAjaxSucFn = this.AjaxSucFn;
			this.$unselect = $(".layui-unselect");
			this.$requestType = "post";//请求方式
			this.$enter = $(".layui-layer-btn0");
			
		},
		initData : function(){
			this.$ChanenlForm = $("#ChanenlForm").serialize();
		},
		bindEvent : function(){
			this.$enter.on("click",this.addBankCard)
		},
		addBankCard : function(){
			ChannelAddCls.initData();
			if(ChannelAddCls.checkParam()){
				 $("[name='channelAccount']").val("");
				 $("[name='channelName']").val("");
				 $("[name='channelStautus']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),ChannelAddCls.$ChanenlForm,ChannelAddCls.$addChannelAjaxSucFn,true,ChannelAddCls.$AjaxErrFn,ChannelAddCls.$requestType)
		},
		checkParam : function(){
			debugger;
			$pass1 = $("[name='channelAccount']").val();
			$pass2 = $("[name='channelName']").val();
			$pass3 = $("[name='channelStautus']").val();
			if(!$pass1 ||!$pass2 ||!$pass3 ){
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

var PayTypeClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this. payTypeShowInit();
			this.query();
		},
		initNode : function(){
			this.$PayTypeAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$PayTypeAdd.on("click",this.PayTypeAdd);
		},
		PayTypeAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加渠道';
			PayTypeClas.layuiOpen($url,$width,$higth,$title)
		},
		layuiOpen : function(url,width,higth,title){
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
						  },
					end : function(){
						lock = true
						PayTypeClas.reload({"":""});
					}
				});
		},
		payTypeShowInit : function(){
			layui.use('table', function(){
				PayTypeClas.$ObjectTable = layui.table;
				PayTypeClas.$ObjectTable.render({
				    elem: PayTypeClas.$table
				    ,url:PayTypeClas.$table.attr('url')
				    ,cols: [[
				    	   {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'payTypeNo', title: '产品编号', width:160}
					      ,{field: 'payTypeName', title: '产品名称', width:160}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				PayTypeClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {payTypeNo : obj.data.payTypeNo};
				    	CommonUtil.ObjextAjax(url,deta,PayTypeClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'payTypeNo='+ obj.data.payTypeNo
						$width = '630px';
						$higth = '500px';
						$title = '修改渠道费率';
						PayTypeClas.layuiOpen($url,$width,$higth,$title);
				    }  
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var payTypeNo = $('[name="payTypeNo"]').val();//获取输入框的值
				 var payTypeName = $('[name="payTypeName"]').val();//获取输入框的值
				PayTypeClas.reload({payTypeName:payTypeName,payTypeNo:payTypeNo})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				PayTypeClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			PayTypeClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'payTypeList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}

var ChannelFeeClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this. channelFeeShowInit();
			this.query();
		},
		initNode : function(){
			this.$ChannelFeeAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$ChannelFeeAdd.on("click",this.ChannelAdd);
		},
		ChannelAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加渠道';
			ChannelFeeClas.layuiOpen($url,$width,$higth,$title)
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
						lock = true
						ChannelFeeClas.reload({"":""});
					}
				});
		},
		channelFeeShowInit : function(){
			layui.use('table', function(){
				ChannelFeeClas.$ObjectTable = layui.table;
				ChannelFeeClas.$ObjectTable.render({
				    elem: ChannelFeeClas.$table
				    ,url:ChannelFeeClas.$table.attr('url')
				    ,cols: [[
				    	   {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'channelNo', title: '渠道本地编号', width:160}
					      ,{field: 'channelAccount', title: '渠道实际代号', width:160}
					      ,{field: 'channelName', title: '渠道名', width: 160}
					      ,{field: 'payType', title: '产品类型', width: 160}
					      ,{field: 'fee', title: '费率', width: 160}
					      ,{field: 'settle', title: '日结百分比', width: 160}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				ChannelFeeClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {id : obj.data.id};
				    	CommonUtil.ObjextAjax(url,deta,ChannelFeeClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'id='+ obj.data.id
						$width = '630px';
						$higth = '500px';
						$title = '修改渠道费率';
						ChannelFeeClas.layuiOpen($url,$width,$higth,$title);
				    }  
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var channelNo = $('[name="channelNo"]').val();//获取输入框的值
				 var channelName = $('[name="channelName"]').val();//获取输入框的值
				 var payType = $('[name="payType"]').val();//获取输入框的值
				ChannelFeeClas.reload({channelNo:channelNo,channelName:channelName,payType:payType})
			})
		},
		AjaxSucFn : function(data){
			debugger;
			if(data && data.success){
				layer.msg(""+data.message);
				ChannelFeeClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		},
		 reload:function(param){
			ChannelFeeClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'channelFeeList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}
var ChannelClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this. channelShowInit();
			this.query();
		},
		initNode : function(){
			this.$ChannelAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$ChannelAdd.on("click",this.ChannelAdd);
		},
		ChannelAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加渠道';
			ChannelClas.layuiOpen($url,$width,$higth,$title)
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
						lock = true
						ChannelClas.reload({"":""});
					}
				});
		},
		channelShowInit : function(){
			layui.use('table', function(){
				ChannelClas.$ObjectTable = layui.table;
				ChannelClas.$ObjectTable.render({
				    elem: ChannelClas.$table
				    ,url:ChannelClas.$table.attr('url')
				    ,cols: [[
				    	   {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'channelNo', title: '渠道本地编号', width:160}
					      ,{field: 'channelAccount', title: '渠道实际代号', width:160}
					      ,{field: 'channelName', title: '渠道名', width: 160}
					      ,{field: 'channelStautus', title: '渠道状态', width: 160,templet:'#channelStautus'}
					      ,{field: 'channelType', title: '渠道类型', width: 160,templet:'#channelType' }
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				ChannelClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {channelNo : obj.data.channelNo};
				    	CommonUtil.ObjextAjax(url,deta,ChannelClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'channelNo='+ obj.data.channelNo
						$width = '630px';
						$higth = '500px';
						$title = '修改渠道信息';
						ChannelClas.layuiOpen($url,$width,$higth,$title);
				    }  
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var channelNo = $('[name="channelNo"]').val();//获取输入框的值
				 var channelName = $('[name="channelName"]').val();//获取输入框的值
				ChannelClas.reload({channelNo:channelNo,channelName:channelName})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(""+data.message);
				ChannelClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(""+data.message)
			}
		},
		 reload:function(param){
			ChannelClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'channelList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}
