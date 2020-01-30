var UserManageAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$accountAjaxSucFn = this.SucFn;
			this.$accountAjaxErrFn = "暂无权限";
			this.$addUserAjaxSucFn = this.AjaxSucFn;
			this.$UserName = $("[name = userId]");
			this.$requestType = "post";//请求方式
			this.$but = $("#Account");
			this.$enter = $(".layui-layer-btn0");
			
		},
		initData : function(){
			this.$user = $("#UserForm").serialize();
		},
		bindEvent : function(){
			this.$but.on("click",this.account);
			this.$enter.on("click",this.addUser)
		},
		addUser : function(){
			UserManageAddCls.initData();
			if(UserManageAddCls.checkParam()){
				 $("[name='userPassword']").val("");
				 $("[name='password']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),UserManageAddCls.$user,UserManageAddCls.$addUserAjaxSucFn,true,UserManageAddCls.$accountAjaxErrFn,UserManageAddCls.$requestType)
		},
		addClass:function(){
			if($(this).hasClass("layui-form-onswitch")){
				$(this).removeClass("layui-form-onswitch");
				$("[em='em']").html("无效")
			} else {
				$(this).addClass("layui-form-onswitch");
				$("[em='em']").html("有效")
			};
		},
		account : function(){
			/**
	         * ajax封装
	         * @param url	JS连接
	         * @param data	数据对象
	         * @param sucFn	成功的方法
	         * @param isAsync	是否数据同步 true  or false    默认为true
	         * @param errFn	错误的方法
	         * @param requestType	请求类型    get post  delete put ...
	         */
  			CommonUtil.ObjextAjax($(this).attr("url"),"account",UserManageAddCls.$accountAjaxSucFn,true,UserManageAddCls.$accountAjaxErrFn,UserManageAddCls.$requestType)
		},
		SucFn :function(data){
			if(data && data.success){
				UserManageAddCls.$UserName.val(data.result)
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		},
		checkParam : function(){
			$pass1 = $("[name='userPassword']").val();
			$pass2 = $("[name='password']").val();
			$pass3 = $("[name='payPassword']").val();
			$pass4 = $("[name='payPassword1']").val();
			if(!$pass1 || !$pass2 || !$pass3 || !$pass4){
				layer.msg("参数为空");
				return true;
			}
			if($pass1 != $pass2){
				 layer.msg("密码不一致");
				return true;
			}
			if($pass3 != $pass4){
				layer.msg("密码不一致");
				return true;
			}
			return false;
			
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message)
				parent.layer.closeAll();
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		}

}

var UserManageEditCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			this.$but = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$data ={
					id:$("[name=id]").val(),
					userId:$("[name=userId]").val(),
					userName:$("[name=userName]").val(),
					userPhone:$("[name=userPhone]").val(),
					userMail:$("[name=userMail]").val(),
					userQQ:$("[name=userQQ]").val(),
					userWechar:$("[name=userWechar]").val(),
					userAddress:$("[name=userAddress]").val(),
					userCity:$("[name=userCity]").val(),
					status:$("[name=status]").val(),
					retain1:$("[name=retain1]").val(),
					retain2:$("[name=retain2]").val(),
					retain5:$("[name=retain5]").val()
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.userEdit)
		},
		userEdit : function(){
			UserManageEditCls.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),UserManageEditCls.$data,UserAddCls.AjaxSucFn,true,"暂无权限","post")
		}
}

var UserManageClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this.userShowInit();
			this.query();
		},
		initNode : function(){
			this.$UserAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$UserAdd.on("click",this.UserAdd);
		},
		UserAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加用户';
			UserManageClas.layuiOpen($url,$width,$higth,$title)
		},
		layuiOpen : function(url,width,higth,title){
		var lock  = true;//防止重复弹出,加锁
			if(lock){
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
						lock  = false;
						  },
					end : function(){
						lock = true
						UserManageClas.reload({"":""});
					}
				});
			}
		},
		userShowInit : function(){
			layui.use('table', function(){
				UserManageClas.$ObjectTable = layui.table;
				UserManageClas.$ObjectTable.render({
				    elem: UserManageClas.$table
				    ,url:UserManageClas.$table.attr('url')
				    ,cols: [[
				    	 {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'userId', title: '用户ID（登录账号）', width:180}
					      ,{field: 'userName', title: '姓名', width:130, sort: true}
					      ,{field: 'userMail', title: '邮箱', width: 110}
					      ,{field: 'userPhone', title: '手机', width: 110, sort: true}
					      ,{field: 'userQQ', title: '用户QQ', width: 115, sort: true}
					      ,{field: 'userWechar', title: '用户微信', width: 115}
					      ,{field: 'userType', title: '用户类型', width: 115, sort: true,templet:'#userType' }
					      ,{field: 'retain2', title: '账号类型', width: 115, sort: true,templet:'#retain2' }
					      ,{field: 'userAddress', title: '用户地址', width: 115, sort: true}
					      ,{field: 'userCity', title: '用户所在城市', width: 135, sort: true}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				UserManageClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {userId : obj.data.userId};
				    	CommonUtil.ObjextAjax(url,deta,UserManageClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'userId='+ obj.data.userId
						$width = '630px';
						$higth = '500px';
						$title = '修改用户信息';
						UserManageClas.layuiOpen($url,$width,$higth,$title);
				    } else if(obj.event === 'roleAndR'){
				    	$url = $("[lay-event='roleAndR']").attr("url");
				    	$url = $url + '?' + 'userId='+ obj.data.userId
						$width = '630px';
						$higth = '500px';
						$title = '角色资源详情';
						UserManageClas.layuiOpen($url,$width,$higth,$title);
				    }else if (obj.event === 'agent'){
				    	debugger;
				    	$url = $("[lay-event='agent']").attr("url");
				    	var deta =  {userId : obj.data.id};
				    	CommonUtil.ObjextAjax($url,deta,UserManageClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				    }
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var userId = $('[name="userId"]').val();//获取输入框的值
				UserManageClas.reload({userId:userId})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				UserManageClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			UserManageClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'appAccountManageList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}
var UserEditCls = {

		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			this.$but = $(".layui-layer-btn0");
		},
		initData : function(){
			debugger;
			this.$data ={
					id:$("[name=id]").val(),
					userId:$("[name=userId]").val(),
					userName:$("[name=userName]").val(),
					userPhone:$("[name=userPhone]").val(),
					userMail:$("[name=userMail]").val(),
					userQQ:$("[name=userQQ]").val(),
					userWechar:$("[name=userWechar]").val(),
					userAddress:$("[name=userAddress]").val(),
					userCity:$("[name=userCity]").val(),
					userType:$("[name=userType]").val(),
					status:$("[name=status]").val(),
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.userEdit)
		},
		userEdit : function(){
			UserEditCls.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),UserEditCls.$data,UserAddCls.AjaxSucFn,true,"暂无权限","post")
		}
}

var UserAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$accountAjaxSucFn = this.SucFn;
			this.$accountAjaxErrFn = "暂无权限";
			this.$addUserAjaxSucFn = this.AjaxSucFn;
			this.$UserName = $("[name = userId]");
			this.$unselect = $(".layui-unselect");
			this.$requestType = "post";//请求方式
			this.$but = $("#Account");
			this.$enter = $(".layui-layer-btn0");
			
		},
		initData : function(){
			this.$user = $("#UserForm").serialize();
		},
		bindEvent : function(){
			this.$but.on("click",this.account);
			this.$unselect.on("click",this.addClass)
			this.$enter.on("click",this.addUser)
		},
		addUser : function(){
			UserAddCls.initData();
			if(UserAddCls.checkParam()){
				 $("[name='userPassword']").val("");
				 $("[name='password']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),UserAddCls.$user,UserAddCls.$addUserAjaxSucFn,true,UserAddCls.$accountAjaxErrFn,UserAddCls.$requestType)
		},
		addClass:function(){
			if($(this).hasClass("layui-form-onswitch")){
				$(this).removeClass("layui-form-onswitch");
				$("[em='em']").html("无效")
			} else {
				$(this).addClass("layui-form-onswitch");
				$("[em='em']").html("有效")
			};
		},
		account : function(){
			/**
	         * ajax封装
	         * @param url	JS连接
	         * @param data	数据对象
	         * @param sucFn	成功的方法
	         * @param isAsync	是否数据同步 true  or false    默认为true
	         * @param errFn	错误的方法
	         * @param requestType	请求类型    get post  delete put ...
	         */
  			CommonUtil.ObjextAjax($(this).attr("url"),"account",UserAddCls.$accountAjaxSucFn,true,UserAddCls.$accountAjaxErrFn,UserAddCls.$requestType)
		},
		SucFn :function(data){
			if(data && data.success){
				UserAddCls.$UserName.val(data.result)
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		},
		checkParam : function(){
			$pass1 = $("[name='userPassword']").val();
			$pass2 = $("[name='password']").val();
			$pass3 = $("[name='payPassword']").val();
			$pass4 = $("[name='payPassword1']").val();
			if(!$pass1 || !$pass2 || !$pass3 || !$pass4){
				layer.msg("参数为空");
				return true;
			}
			if($pass1 != $pass2){
				 layer.msg("密码不一致");
				return true;
			}
			if($pass3 != $pass4){
				layer.msg("密码不一致");
				return true;
			}
			return false;
			
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message)
				parent.layer.closeAll();
				return;
			}else if(data && !data.success){
				layer.msg(data.message)
				return;
			}
		}
}
var UserClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this.userShowInit();
			this.query();
		},
		initNode : function(){
			this.$UserAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$UserAdd.on("click",this.UserAdd);
		},
		UserAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加用户';
			UserClas.layuiOpen($url,$width,$higth,$title)
		},
		layuiOpen : function(url,width,higth,title){
		var lock  = true;//防止重复弹出,加锁
			if(lock){
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
						lock  = false;
						  },
					end : function(){
						lock = true
						UserClas.reload({"":""});
					}
				});
			}
		},
		userShowInit : function(){
			layui.use('table', function(){
				UserClas.$ObjectTable = layui.table;
				UserClas.$ObjectTable.render({
				    elem: UserClas.$table
				    ,url:UserClas.$table.attr('url')
				    ,cols: [[
				    	 {field: 'id', title: 'ID', hide :true, width:150,   fixed: 'left'}
					      ,{field: 'userId', title: '用户ID', width:150}
					      ,{field: 'userName', title: '姓名', width:130, sort: true}
					      ,{field: 'userMail', title: '邮箱', width: 110}
					      ,{field: 'userPhone', title: '手机', width: 110, sort: true}
					      ,{field: 'userQQ', title: '用户QQ', width: 115, sort: true}
					      ,{field: 'userWechar', title: '用户微信', width: 115}
					      ,{field: 'userType', title: '用户类型', width: 115, sort: true}
					      ,{field: 'userAddress', title: '用户地址', width: 115, sort: true}
					      ,{field: 'userCity', title: '用户所在城市', width: 135, sort: true}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
					      ,{field: 'right1', title: '角色和资源', toolbar: '#roleAndRShow', width: 135}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				UserClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {userId : obj.data.userId};
				    	CommonUtil.ObjextAjax(url,deta,UserClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'userId='+ obj.data.userId
						$width = '630px';
						$higth = '500px';
						$title = '修改用户信息';
				    	UserClas.layuiOpen($url,$width,$higth,$title);
				    } else if(obj.event === 'roleAndR'){
				    	$url = $("[lay-event='roleAndR']").attr("url");
				    	$url = $url + '?' + 'userId='+ obj.data.userId
						$width = '630px';
						$higth = '500px';
						$title = '角色资源详情';
				    	UserClas.layuiOpen($url,$width,$higth,$title);
				    }
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var userId = $('[name="userId"]').val();//获取输入框的值
				UserClas.reload({userId:userId})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				UserClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			UserClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'userList'//后台做模糊搜索接口路径
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
