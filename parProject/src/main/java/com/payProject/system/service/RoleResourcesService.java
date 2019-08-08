package com.payProject.system.service;

import java.util.List;

import com.payProject.system.entity.RoleResources;

public interface RoleResourcesService {
	/**
	 * <p>增加角色和资源的绑定关系</p>
	 * @param list		RoleResources 集合
	 * @return			true 绑定成功	false  绑定失败
	 * 2019-08-07
	 */
	Boolean addRoleResource(List<RoleResources> list,String roleId);

}
