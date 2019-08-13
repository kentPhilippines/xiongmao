var RoleEditCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
		}, 
		initNode : function(){
			debugger;
			this.$but = $(".layui-layer-btn0");
		},
		initData : function(){
			/**
			 * 这里后期还需要修改角色所对应的资源问题,所以现在暂时不做
			 */
			this.$data ={
					roleId:$("[name=roleId]").val(),
					roleName:$("[name=roleName]").val(),
					remark:$("[name=remark]").val(),
					status:$("[name=status]").val(),
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.roleEdit)
		},
		roleEdit : function(){
			RoleEditCls.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),RoleEditCls.$data,RoleAddCls.AjaxSucFn,true,"暂无权限",RoleAddCls.$requestType)
		}
}
var RoleAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$accountAjaxSucFn = this.SucFn;
			this.$accountAjaxErrFn = "暂无权限";
			this.$addRoleAjaxSucFn = this.AjaxSucFn;
			this.$UserName = $("[name = roleId]");
			this.$requestType = "post";//请求方式
			this.$enter = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$role = $("#RoleForm").serialize();
		},
		bindEvent : function(){
			this.$enter.on("click",this.addRole)
		},
		addRole : function(){
			debugger;
			RoleAddCls.initData();
			if(RoleAddCls.checkParam()){
				 $("[name='roleName']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),RoleAddCls.$role,RoleAddCls.$addRoleAjaxSucFn,true,RoleAddCls.$accountAjaxErrFn,RoleAddCls.$requestType)
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
			$pass1 = $("[name='roleName']").val();
			if(!$pass1){
				layer.msg("参数为空");
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
var RoleClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this.RoleShowInit();
			this.query();
		},
		initNode : function(){
			this.$RoleAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$RoleAdd.on("click",this.RoleAdd);
		},
		RoleAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加用户';
			RoleClas.layuiOpen($url,$width,$higth,$title)
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
		RoleShowInit : function(){
			layui.use('table', function(){
				RoleClas.$ObjectTable = layui.table;
				RoleClas.$ObjectTable.render({
				    elem: RoleClas.$table
				    ,url:RoleClas.$table.attr('url')
				    ,cols: [[
					      {field: 'roleId', title: '角色ID', width:150,sort: true, fixed: 'left'}
					      ,{field: 'roleName', title: '角色姓名', width:150}
					      ,{field: 'remark', title: '说明', width: 250}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true}
					      ,{field: 'status', title: '状态', width: 135, sort: true}
					      ,{field: 'resource', title: '资源信息',toolbar: '#resourceShow', width: 135}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				RoleClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {roleId : obj.data.roleId};
				    	CommonUtil.ObjextAjax(url,deta,RoleClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'roleId='+ obj.data.roleId
						$width = '630px';
						$higth = '500px';
						$title = '修改角色信息';
						RoleClas.layuiOpen($url,$width,$higth,$title);
				    }else if(obj.event === 'resourcesShow'){
				    	$url = $("[lay-event='resourcesShow']").attr("url");
				    	$url = $url + '?' + 'roleId='+ obj.data.roleId
						$width = '830px';
						$higth = '600px';
						$title = '选择角色资源';
						RoleClas.layuiOpen($url,$width,$higth,$title);
				    }
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var roleName = $('[name="roleName"]').val();//获取输入框的值
				RoleClas.reload({roleName:roleName})
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				RoleClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			RoleClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'roleList'//后台做模糊搜索接口路径
				                        , method: 'post'
				                          });
		} 
		
}
