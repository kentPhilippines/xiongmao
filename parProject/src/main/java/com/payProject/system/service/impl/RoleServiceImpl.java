package com.payProject.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.system.entity.Role;
import com.payProject.system.entity.RoleExample;
import com.payProject.system.entity.RoleExample.Criteria;
import com.payProject.system.mapper.ResourcesMapper;
import com.payProject.system.mapper.RoleMapper;
import com.payProject.system.service.RoleService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class RoleServiceImpl implements RoleService {
	Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Resource
	ResourcesMapper resourcesDao;
	
	@Resource
	RoleMapper  roleDao;

	@Override
	public Boolean addRole(Role roleS) {
		int insertSelective = roleDao.insertSelective(roleS);
		if(0<insertSelective && insertSelective <2) {
			log.info("增加角色成功");
		}
		return 0<insertSelective && insertSelective <2;
	}

	@Override
	public Role findRoleByRoleId(Integer roleId) {
		RoleExample example = new RoleExample();
		Criteria create = example.createCriteria();
		create.andRoleIdEqualTo(roleId);
		List<Role> selectByExample = roleDao.selectByExample(example);
		Role firstRole = CollUtil.getFirst(selectByExample);
		return firstRole;
	}

	@Override
	public List<Role> findPageRoleByRser(Role role) {
		RoleExample example = new RoleExample();
		Criteria create = example.createCriteria();
		if(null != role.getRoleId())
			create.andRoleIdEqualTo(role.getRoleId());
		if(!StrUtil.isBlank(role.getRoleName()))
			create.andRoleNameLike(role.getRoleName());
		List<Role> selectByExample = roleDao.selectByExample(example);
		log.info("分页查询角色信息");
		return selectByExample;
	}

	@Override
	public boolean deleteRoleByRole(Role role) {
		RoleExample example = new RoleExample();
		Criteria create = example.createCriteria();
		create.andRoleIdEqualTo(role.getRoleId());
		int deleteByExample = roleDao.deleteByExample(example);
		return deleteByExample >0;
	}

	@Override
	public boolean UpdateRoleByRole(Role role) {
		RoleExample example = new RoleExample();
		Criteria create = example.createCriteria();
		create.andRoleIdEqualTo(role.getRoleId());
		int updateByExampleSelective = roleDao.updateByExampleSelective(role, example);
		return updateByExampleSelective >0;
	}

}
