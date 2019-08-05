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
					userType:$("[name=userType]").val()
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.userEdit)
		},
		userEdit : function(){
			UserEditCls.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),UserEditCls.$data,UserAddCls.$addUserAjaxSucFn,true,UserAddCls.$accountAjaxErrFn,UserAddCls.$requestType)
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
			if(!$pass1){
				layer.msg("参数为空");
				return true;
			}
			if(!$pass2){
				layer.msg("参数为空");
				return true;
			}
			if($pass1 != $pass2){
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
					maxmin: true, //开启最大化最小化按钮
					area: [width,higth],
					content: url,
					success: function(){
						lock  = false;
						  },
					end : function(){
						lock = true
						location.reload();
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
					      ,{field: 'userName', title: '姓名', width:150, sort: true}
					      ,{field: 'userMail', title: '邮箱', width: 150}
					      ,{field: 'userPhone', title: '手机', width: 150, sort: true}
					      ,{field: 'userQQ', title: '用户QQ', width: 135, sort: true}
					      ,{field: 'userWechar', title: '用户微信', width: 135}
					      ,{field: 'userType', title: '用户类型', width: 135, sort: true}
					      ,{field: 'userAddress', title: '用户地址', width: 135, sort: true}
					      ,{field: 'userCity', title: '用户所在城市', width: 135, sort: true}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true}
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
				    	debugger;
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'userId='+ obj.data.userId
						$width = '630px';
						$higth = '500px';
						$title = '修改用户信息';
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
$(function(){
	UserClas.init();
	UserAddCls.init();
	UserEditCls.init();
})