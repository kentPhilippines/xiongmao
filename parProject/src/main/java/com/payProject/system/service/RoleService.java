package com.payProject.system.service;

import java.util.List;

import com.payProject.system.entity.Role;

public interface RoleService {
	/**
	 * <p>根据角色id查询角色</p>
	 * @param roleId	角色id
	 * @return
	 */
	Role findRoleByRoleId(Integer roleId);
	/**
	 * <p>增加一个角色</p>
	 * @param roleS		角色具体信息  其实这里只有一个角色名
	 * @return
	 */
	Boolean addRole(Role roleS);
	/**
	 * <p>分页查询角色信息</p>
	 * @param role
	 * @return
	 */
	List<Role> findPageRoleByRser(Role role);
	/**
	 * <p>删除一个角色</p>
	 * @param role
	 * @return
	 */
	boolean deleteRoleByRole(Role role);
	/**
	 * <p>根据角色id,根据角色姓名和状态</p>
	 * @param role
	 * @return
	 */
	boolean UpdateRoleByRole(Role role);
}
