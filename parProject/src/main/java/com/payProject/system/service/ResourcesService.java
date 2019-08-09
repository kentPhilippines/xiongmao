package com.payProject.system.service;

import java.util.List;

import com.payProject.system.entity.Resources;

public interface ResourcesService {
	/**
	 * <p>新增一个资源</p>
	 * @param resources	关于资源的实体类,该类的资源URL和资源名是必传参数
	 * @return		true 成功   false 失败
	 */
	Boolean addResources(Resources resources);

	/***
	 * <p>根据资源实体分页查询资源数据</p>
	 * @param resources    无必传参数
	 * @return			   资源分页信息
	 */
	List<Resources> findPageResourcesByResources(Resources resources);

	/**
	 * <p>删除资源,根据资源唯一识别号</p>
	 * @param resources
	 * @return
	 */
	boolean deleteResourcesByResources(Resources resources);

	/**
	 * <p>根据资源id查询资源</p>
	 * @param resourcesId		该资源唯一识别号
	 * @return					该资源具体数据
	 */
	Resources findResourcesByResourcesId(Integer resourcesId);

	/**
	 * <p></p>
	 * @param resources
	 * @return
	 */
	boolean UpdateResourcesByResources(Resources resources);

	/**
	 * <p>根据菜单级别查询级别高于自己的菜单</p>
	 * <p>比如自己的级别为1那么就只能查询到级别为0的菜单</p>
	 * @param level
	 * @return
	 */
	List<Resources> findParentMenuByLevel(Integer level);


	/**
	 * <p>根据角色id获取角色所对应的资源信息</p>
	 * @param roleId		角色id
	 * @return
	 */
	List<Integer> findRourcesByRoleId(Integer roleId);

	/**
	 * <p>查询所有的资源数据</p>
	 * @return
	 */
	List<Resources> findRourcesByAll();
	
	
	/**
	 * <p>根据用户名获取用户对应的资源信息</p>
	 * @param userId	用户id
	 * @return
	 */
	List<Resources> findRourcesIdByUserId(String id);

}
