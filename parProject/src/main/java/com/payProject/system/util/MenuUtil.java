package com.payProject.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.payProject.system.entity.Resources;

/**
 * <p>菜摊展示核心类</p>
 * @author K
 * 2019-08-09
 */
public class MenuUtil {
	/**
	 * <p>根据所有的菜单对菜单子父类进行分类</p>
	 * @param res		菜单资源集合
	 * @return			分好类的菜单资源集合
	 */
	public static List<Resources> getMenuList(List<Resources> res){
		Map<Integer ,Integer> relationship  = new HashMap<Integer ,Integer>();
		Map<Integer,Resources> beanMap = new HashMap<>();
		for(Resources resource : res) {
			if(null != resource.getParentId()) {
				relationship.put(resource.getResourcesId(), resource.getParentId());//这个Map不是一级菜单
			}
			beanMap.put(resource.getResourcesId(), resource);
		}
		return getSumResource(relationship,beanMap);
	}
	/**
	 * <p>关系转换类</p>
	 * @param relationship		所有子类对父类的映射
	 * @param beanMap			所有资源id对自己的映射
	 * @return
	 */
	private static List<Resources> getSumResource(Map<Integer, Integer> relationship, Map<Integer, Resources> beanMap) {
		Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		List<Resources> resultList = new ArrayList<Resources>();
		for(Integer id : relationship.keySet()) {
			Integer parentId = relationship.get(id);
			if(map.containsKey(parentId)) {
				List<Integer> idList = map.get(parentId);
				idList.add(id);
				map.put(parentId, idList);
			}else {
				List<Integer> idList = new ArrayList<Integer>();
				idList.add(id);
				map.put(parentId, idList);
			}
		}
		for (Integer j : map.keySet()) {//所有的父类
			if(!relationship.containsKey(j)) {//不存在父类
				List<Integer> list = map.get(j);
				List<Resources> gitMenu = gitMenu(list,map,beanMap);
				Resources resources = beanMap.get(j);
				resources.setSumList(gitMenu);
				resultList.add(resources);
			}
		}
		return resultList;
	}
	/**
	 * <p>核心递归类</p>
	 * @param list				子类集合
	 * @param relationship		子父类关系集合
	 * @param beanMap			所有实体类map
	 * @return
	 */
	private static List<Resources> gitMenu(List<Integer> list, Map<Integer, List<Integer>> relationship,Map<Integer, Resources> beanMap) {
		 List<Resources> listBean = new ArrayList<Resources>();
			 for(Integer i : list) {
				 Resources resources = beanMap.get(i);
				 if(relationship.containsKey(i)) {
					 List<Integer> list2 = relationship.get(i);
					 resources.setSumList(gitMenu(list2, relationship, beanMap));
				 } 
					 listBean.add(resources);
			 }
		return listBean;
	}
}
