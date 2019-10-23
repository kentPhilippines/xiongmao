package com.payProject.manage.service;

import java.util.List;

import com.payProject.manage.entity.Statistics;

public interface StatisticsService {

	List<Statistics> findStatisticsByAll(Statistics entity);

}
