package com.payProject.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.manage.entity.Statistics;
import com.payProject.manage.entity.StatisticsExample;
import com.payProject.manage.entity.StatisticsExample.Criteria;
import com.payProject.manage.mapper.StatisticsMapper;
import com.payProject.manage.service.StatisticsService;

import cn.hutool.core.collection.CollUtil;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	StatisticsMapper statisticsDao;
	@Override
	public List<Statistics> findStatisticsByAll(Statistics entity) {
		StatisticsExample example = new StatisticsExample();
		Criteria criteria = example.createCriteria();
		example.setOrderByClause("`time` DESC");
		if(CollUtil.isNotEmpty(entity.getKeysList())) {
			criteria.andKeyListEqualTo(entity.getKeysList());
		}
		List<Statistics> selectByExample = statisticsDao.selectByExample(example);
		return selectByExample;
	}
	
		
}
