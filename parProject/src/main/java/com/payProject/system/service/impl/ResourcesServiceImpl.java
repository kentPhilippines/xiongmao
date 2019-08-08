package com.payProject.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.config.exception.ParamException;
import com.payProject.system.entity.Resources;
import com.payProject.system.entity.ResourcesExample;
import com.payProject.system.entity.ResourcesExample.Criteria;
import com.payProject.system.mapper.ResourcesMapper;
import com.payProject.system.service.ResourcesService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
@Service
public class ResourcesServiceImpl implements ResourcesService{
	
	@Autowired
	ResourcesMapper resourcesDao;

	@Override
	public Boolean addResources(Resources resources) {
		if(StrUtil.isBlank(resources.getResourcesName()) || StrUtil.isBlank(resources.getResourcesKey())) 
			throw new ParamException("必传参数为空");
		int insertSelective = resourcesDao.insertSelective(resources);
		return insertSelective >0;
	}

	@Override
	public List<Resources> findPageResourcesByResources(Resources resources) {
		ResourcesExample example = new ResourcesExample();
		Criteria criteria = example.createCriteria();
		if(null != resources.getResourcesId())//精准查询
			criteria.andResourcesIdEqualTo(resources.getResourcesId());
		if(!StrUtil.isBlank(resources.getResourcesName()))//如果存在存在模糊查询
			criteria.andResourcesNameLike("%"+resources.getResourcesName()+"%");
		if(!StrUtil.isBlank(resources.getResourcesKey()))//同类别查询
			criteria.andResourcesNameLike("%"+resources.getResourcesKey()+"%");
		if(!StrUtil.isBlank(resources.getDescription()))//资源描述
			criteria.andResourcesNameLike("%"+resources.getDescription()+"%");
		if(null != resources.getLevel())//精准查询
			criteria.andLevelEqualTo(resources.getLevel());
		List<Resources> selectByExample = resourcesDao.selectByExample(example);
		return selectByExample;
	}


	/**
	 * 这里应该添加一个事务,如果返回为false则回滚事务,让它不能删除,
	 */
	@Override
	public boolean deleteResourcesByResources(Resources resources) {
		ResourcesExample example = new ResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andResourcesIdEqualTo(resources.getResourcesId());
		int deleteByExample = resourcesDao.deleteByExample(example);
		return deleteByExample > 0 &&  deleteByExample < 2;
	}

	@Override
	public Resources findResourcesByResourcesId(Integer resourcesId) {
		ResourcesExample example = new ResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andResourcesIdEqualTo(resourcesId);
		List<Resources> selectByExample = resourcesDao.selectByExample(example);
		Resources first = CollUtil.getFirst(selectByExample);
		return first;
	}

	@Override
	public boolean UpdateResourcesByResources(Resources resources) {
		ResourcesExample example = new ResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andResourcesIdEqualTo(resources.getResourcesId());
		int updateByExampleSelective = resourcesDao.updateByExampleSelective(resources, example);
		return updateByExampleSelective >0;
	}

	@Override
	public List<Resources> findParentMenuByLevel(Integer level) {
		ResourcesExample example = new ResourcesExample();
		Criteria criteria = example.createCriteria();
		criteria.andLevelLessThan(level);
		List<Resources> selectByExample = resourcesDao.selectByExample(example);
		return selectByExample;
	}

	@Override
	public List<Integer> findRourcesIdByUserId(String userId) {
		List<Resources> findResourceByUserId = resourcesDao.findResourceByUserId(userId);
		List<Integer> list = new ArrayList<Integer>();
		for(Resources bean : findResourceByUserId) {
			list.add(bean.getResourcesId());
		}
		return list;
	}

	@Override
	public List<Integer> findRourcesByRoleId(Integer roleId) {
		List<Resources> list1  =  resourcesDao.findRourcesByRoleId(roleId);
		List<Integer> list = new ArrayList<Integer>();
		for(Resources bean : list1) {
			list.add(bean.getResourcesId());
		}
		return list;
	}

	@Override
	public List<Resources> findRourcesByAll() {
		ResourcesExample example = new ResourcesExample();
		List<Resources> selectByExample = resourcesDao.selectByExample(example);
		return selectByExample;
	}

}
