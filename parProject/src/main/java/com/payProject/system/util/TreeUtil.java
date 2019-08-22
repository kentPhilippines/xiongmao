package com.payProject.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.payProject.system.entity.Resources;

import cn.hutool.core.collection.CollUtil;

/**
 * <p>layui树形组件工具类转换</p>
 * @author K
 * 2019-08-07
 */
public class TreeUtil {
	/**
	 * <p>从所有资源选取自己关注的资源</p>
	 * @param resources				所有资源列表
	 * @param checkedList			自己关注资源的id集合
	 * @return     					返回关于前端需要的tree数据结构
	 */
	public static List<LayuiTreeBean> getLayuiTreeBeanList(List<Resources> resources , List<Integer> checkedList) {
		List<LayuiTreeBean> list = new ArrayList<LayuiTreeBean> ();
		Map<Integer,Integer> mapNumBer = new HashMap<Integer,Integer>();
		Map<Integer,LayuiTreeBean> mapObj = new HashMap<Integer,LayuiTreeBean>();
		for(int i = 0 ; i< resources.size() ; i++) {
			if(null != resources.get(i).getParentId()) {
				mapNumBer.put( resources.get(i).getResourcesId(),resources.get(i).getParentId());
			}
			LayuiTreeBean treeBean = new LayuiTreeBean();
			Resources bean = resources.get(i);
			if(checkedList.contains(bean.getResourcesId()))
				treeBean.setChecked(true);
			else
				treeBean.setChecked(false);
			treeBean.setTitle(bean.getResourcesName());
			treeBean.setValue(String.valueOf(bean.getResourcesId()));
			treeBean.setData(new ArrayList<LayuiTreeBean>());
			mapObj.put(resources.get(i).getResourcesId(),treeBean);
			list.add(treeBean);
			treeBean = null ;
		}
		List<LayuiTreeBean> layuiTreeBeanList = getLayuiTreeBeanList(mapNumBer,mapObj);
		return layuiTreeBeanList;
	}
	/**
	 * <p>根据绑定信息获取关于当前用户最终资源信息</p>
	 * @param mapNumBer			Key:子类Id   value:父类Id
	 * @param mapObj			Key:当前id	value:当前id对应的资源信息
	 * @return					当前所需要的数据格式
	 */
	private static List<LayuiTreeBean> getLayuiTreeBeanList(Map<Integer,Integer> mapNumBer,Map<Integer,LayuiTreeBean> mapObj){
		Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		List<LayuiTreeBean> listBean = new ArrayList<LayuiTreeBean>();
		for (Integer i : mapObj.keySet()) {
			if(mapNumBer.containsKey(i)) {//存在父类id
				Integer parentId = mapNumBer.get(i);
				if(!CollUtil.isEmpty(map.get(parentId))) {
					List<Integer> list = map.get(parentId);
					list.add(i);
					map.put(parentId, list);
				}else {
					List<Integer> list1 = new ArrayList<Integer>();
					list1.add(i);
					map.put(parentId, list1);
				}
			}
		}
		for (Integer j : map.keySet()) {
			if(!mapNumBer.containsKey(j)) {//当他不存在父类的时候
				List<Integer> list = map.get(j);
				    List<LayuiTreeBean> layuiTree = getLayuiTree(list,mapObj,map);
				    LayuiTreeBean layuiTreeBean = mapObj.get(j);
				    layuiTreeBean.setData(layuiTree);
				    listBean.add(layuiTreeBean);
			}
		}
		return listBean;
	}
	/**
	 * <p>设置自己的子类菜单</p>
	 * @param list			子类菜单id集合
	 * @param mapObj		所有的菜单map
	 * @param map		 	子父类菜单Idmap集合
	 * @return
	 */
	private static List<LayuiTreeBean> getLayuiTree(List<Integer> list, Map<Integer, LayuiTreeBean> mapObj, Map<Integer, List<Integer>> map) {
		 List<LayuiTreeBean> listBean = new ArrayList<LayuiTreeBean>();
		for(Integer o  : list) {
			LayuiTreeBean layuiTreeBean = mapObj.get(o);
			if(map.containsKey(Integer.valueOf(layuiTreeBean.getValue()))) {
				List<Integer> list2 = map.get(Integer.valueOf(layuiTreeBean.getValue()));
				layuiTreeBean.setData(getLayuiTree(list2,mapObj,map));
			}
				listBean.add(layuiTreeBean);
		}
 		return listBean;
	}


	
	
	
	
	
	
	
	
	
	
	

}
