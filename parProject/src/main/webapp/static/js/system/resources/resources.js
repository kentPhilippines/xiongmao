var ResourcesEditCls = {
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
					resourcesId:$("[name=resourcesId]").val(),
					resourcesName:$("[name=resourcesName]").val(),
					description:$("[name=description]").val(),
					resourcesKey:$("[name=resourcesKey]").val(),
					resourcesUrl:$("[name=resourcesUrl]").val(),
					rank:$("[name=rank]").val(),
					level:$("[name=level]").val(),
					parentId:$("[name=parentId]").val(),
					status:$("[name=status]").val(),
			}
		},
		bindEvent : function(){
			this.$but.on("click",this.ResourcesEdit)
		},
		ResourcesEdit : function(){
			ResourcesEditCls.initData();
			CommonUtil.ObjextAjax($(this).attr("url"),ResourcesEditCls.$data,ResourcesAddCls.AjaxSucFn,true,"暂无权限",ResourcesAddCls.$requestType)
		}
}
var ResourcesAddCls = {
		init : function(){
			this.initNode();
			this.bindEvent();
			this.initData();
		},
		initNode: function(){
			this.$accountAjaxErrFn = "暂无权限";
			this.$addResourcesAjaxSucFn = this.AjaxSucFn;
			this.$requestType = "post";//请求方式
			this.$enter = $(".layui-layer-btn0");
		},
		initData : function(){
			this.$Resources = $("#ResourcesForm").serialize();
		},
		bindEvent : function(){
			this.$enter.on("click",this.addResources)
		},
		addResources : function(){
			ResourcesAddCls.initData();
			if(ResourcesAddCls.checkParam()){
				 $("[name='resourcesName']").val("");
				 return;
			};
			CommonUtil.ObjextAjax($(this).attr("url"),ResourcesAddCls.$Resources,ResourcesAddCls.$addResourcesAjaxSucFn,true,ResourcesAddCls.$accountAjaxErrFn,ResourcesAddCls.$requestType)
		},
		checkParam : function(){
			$pass1 = $("[name='resourcesName']").val();
			$pass = $("[name='level']").val();
			$pass2 = $("[name='resourcesKey']").val();
			if(!$pass1){
				layer.msg("资源姓名为必传参数");
				return true;
			};
			if(!$pass1){
				layer.msg("资源等级为必传参数");
				return true;
			};
			if(!$pass2){
				layer.msg("资源分类Key为必传参数");
				return true;
			};
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
var ResourcesClas = {
		init : function(){
			this.initNode();
			this.bindEvent()
			this.ResourcesShowInit();
			this.query();
		},
		initNode : function(){
			this.$ResourcesAdd = $("[data-type='add']");
			this.$table = $("#LAY-user-back-manage");
		},
		bindEvent : function() {
			this.$ResourcesAdd.on("click",this.ResourcesAdd);
		},
		ResourcesAdd : function(){
			$url = $(this).attr("addUrl");
			$width = '630px';
			$higth = '500px';
			$title = '添加用户';
			ResourcesClas.layuiOpen($url,$width,$higth,$title)
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
						ResourcesClas.reload({"":""});
					}
				});
			}
		},
		ResourcesShowInit : function(){
			layui.use('table', function(){
				ResourcesClas.$ObjectTable = layui.table;
				ResourcesClas.$ObjectTable.render({
				    elem: ResourcesClas.$table
				    ,url:ResourcesClas.$table.attr('url')
				    ,cols: [[
					      {field: 'resourcesId', title: '资源ID', width:150,sort: true, fixed: 'left'}
					      ,{field: 'resourcesName', title: '资源名称', width:150}
					      ,{field: 'description', title: '资源备注', width: 250}
					      ,{field: 'resourcesKey', title: '资源Key', width: 135}
					      ,{field: 'resourcesUrl', title: '资源Url', width: 135, sort: true}
					      ,{field: 'rank', title: '资源排名', width: 135, sort: true}
					      ,{field: 'level', title: '资源等级', width: 135, sort: true}
					      ,{field: 'createTime', title: '创建时间', width: 135, sort: true,templet:'<div>{{ Format(d.createTime,"yyyy-MM-dd hh:mm:ss")}}</div>'}
					      ,{field: 'status', title: '状态', width: 135, sort: true}
					      ,{fixed: 'right', title:'操作', toolbar: '#operation', width:150}
				    ]]
				    , id: 'mytable'
				    ,page: true
				  });
				  //监听行工具事件
				ResourcesClas.$ObjectTable.on('tool(LAY-user-back-manage)', function(obj){
				    var data = obj.data;
				    if(obj.event === 'del'){
				      layer.confirm('真的删除行么', function(index){
				    	var url = $("[lay-event='del']").attr("url");
				    	 var deta =  {resourcesId : obj.data.resourcesId};
				    	CommonUtil.ObjextAjax(url,deta,ResourcesClas.AjaxSucFn,true,'无权限或网络错误，请联系开发人员处理','post');
				      });
				    } else if(obj.event === 'edit'){
				    	$url = $("[lay-event='edit']").attr("url");
				    	$url = $url + '?' + 'resourcesId='+ obj.data.resourcesId
						$width = '630px';
						$higth = '500px';
						$title = '修改资源信息';
						ResourcesClas.layuiOpen($url,$width,$higth,$title);
				    }
				  });
				});
		},
		query : function(){
			$("[lay-filter='LAY-user-back-search']").on("click",function(){
				 var resourcesId = $('[name="resourcesId"]').val();//获取输入框的值
				 var resourcesName = $('[name="resourcesName"]').val();//获取输入框的值
				 var resourcesKey = $('[name="resourcesKey"]').val();//获取输入框的值
				 var description = $('[name="description"]').val();//获取输入框的值
				 var data = {
						 resourcesId : resourcesId,
						 resourcesName : resourcesName,
						 resourcesKey : resourcesKey,
						 description : description,
				 }
				ResourcesClas.reload(data)
			})
		},
		AjaxSucFn : function(data){
			if(data && data.success){
				layer.msg(data.message);
				ResourcesClas.reload({"":""})
			}else if(data && !data.success){
				layer.msg(data.message)
			}
		},
		 reload:function(param){
			ResourcesClas.$ObjectTable.reload('mytable',{ page:{  curr: 1} //重新从第 1 页开始
				                        , where: param//这里传参  向后台
				                        , url: 'resourcesList'//后台做模糊搜索接口路径
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
