package com.payProject.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.config.entity.Dictionary;
import com.payProject.config.entity.DictionaryExample;
import com.payProject.config.entity.DictionaryExample.Criteria;
import com.payProject.config.mapper.DictionaryMapper;
import com.payProject.manage.service.DictionaryService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

@Service
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	DictionaryMapper dictionaryDao;
	@Override
	public Boolean addDictionary(Dictionary dictionary) {
		int insertSelective = dictionaryDao.insertSelective(dictionary);
		return insertSelective>0 && insertSelective <2 ;
	}
	@Override
	public List<Dictionary> findDictionaryByDictionary(Dictionary dictionary) {
		DictionaryExample example = new DictionaryExample();
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("createTime DESC");
		if(StrUtil.isNotBlank(dictionary.getDataType()))
			criteria.andDataTypeEqualTo(dictionary.getDataType());
		if(StrUtil.isNotBlank(dictionary.getDateSource()))
			criteria.andDataTypeEqualTo(dictionary.getDateSource());
		List<Dictionary> selectByExample = dictionaryDao.selectByExample(example);
		return selectByExample;
	}
	@Override
	public boolean deleteDictionaryByDictionaryId(Integer id) {
		int deleteByPrimaryKey = dictionaryDao.deleteByPrimaryKey(id);
		return deleteByPrimaryKey>0 && deleteByPrimaryKey <2 ;
	}
	@Override
	public Dictionary findDictionaryById(Integer id) {
		DictionaryExample example = new DictionaryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<Dictionary> selectByExample = dictionaryDao.selectByExample(example);
		Dictionary first = CollUtil.getFirst(selectByExample);
		return first;
	}
	@Override
	public boolean UpdateDictionaryById(Dictionary dictionary) {
		DictionaryExample example = new DictionaryExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(dictionary.getId());
		int updateByExampleSelective = dictionaryDao.updateByExampleSelective(dictionary, example);
		return updateByExampleSelective > 0 && updateByExampleSelective < 2;
	}
}
