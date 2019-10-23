package com.payProject.manage.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payProject.manage.entity.Channel;
import com.payProject.manage.entity.Statistics;
import com.payProject.manage.service.ChannelService;
import com.payProject.manage.service.StatisticsService;

import cn.hutool.core.collection.CollUtil;


/**
 * <p>数据统计工具类</p>
 * @author K
 */
@Service
public class StatisticsUtil {
	@Autowired
	StatisticsService statisticsServiceImpl;
	/**
	 * <p>柱状图数据统计</p>
	 */
	private Collection getData(Statistics entity){
		/**
		 * ###################################
		 * 	1,获取数据统计statistics表的信息
		 * 	2,根据信息进行分类
		 * 	3,获取当前的交易情况
		 * 	4,数据封装
		 * ###################################
		 */
		List<Statistics> staList = statisticsServiceImpl.findStatisticsByAll(entity);
		List<Integer> countList = new ArrayList<Integer>();
		List<Integer> countListSu = new ArrayList<Integer>();
		List<BigDecimal> dealDayMoneyList = new ArrayList<BigDecimal>();
		List<BigDecimal> dealDayMoneySuList = new ArrayList<BigDecimal>();
		List<String> timeList = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(Statistics bean : staList) {
			countList.add(Integer.valueOf(bean.getDealCount()));
			countListSu.add(Integer.valueOf(bean.getDealCountSu()));
			dealDayMoneyList.add(new BigDecimal(bean.getAmount()));
			dealDayMoneySuList.add(new BigDecimal(bean.getAmountSu()));
			timeList.add(format.format(bean.getTime()));
		}
		Collection<List> coll = new ArrayList<List>();
		coll.add(countList);
		coll.add(countListSu);
		coll.add(dealDayMoneyList);
		coll.add(dealDayMoneySuList);
		coll.add(timeList);
		return coll;
	}
	/**
	 * <p>渠道交易情况</p>
	 * <p>产品交易情况</p>
	 * <p>用户交易情况</p>
	 * @param list 	渠道ID集合 或者 用户ID集合 或者产品ID 集合
	 * @return	Collection
	 * 			<li>1,交易次数集合</li>
	 * 			<li>2,成功交易次数集合</li>
	 * 			<li>3,交易金额集合</li>
	 * 			<li>4,成功交易金额集合</li>
	 * 			<li>5,交易时间集合</li>
	 * 
	 * 
	 */
	public Collection<List> DealShow(List<String> list) {
		Collection<List> dealShow = DealShow(list,null);
		return dealShow;
	}
	
	/**
	 * <p>渠道加用户匹配交易情况</p>
	 * @param channelList	渠道集合
	 * @param appIdList		用户集合
	 * @return	Collection
	 * 			<li>1,交易次数集合</li>
	 * 			<li>2,成功交易次数集合</li>
	 * 			<li>3,交易金额集合</li>
	 * 			<li>4,成功交易金额集合</li>
	 * 			<li>5,交易时间集合</li>
	 */
	public Collection<List> DealShow(List<String> channelList,List<String> appIdList) {
		Collection<List> dealShow = DealShow(channelList,appIdList,null);
		return dealShow;
	}
	/**
	 * <p>渠道和用户和产品的交易情况数据</p>
	 * @param channelList	渠道集合
	 * @param appIdList		用户集合
	 * @param product		产品集合		
	 * @return	Collection
	 * 			<li>1,交易次数集合</li>
	 * 			<li>2,成功交易次数集合</li>
	 * 			<li>3,交易金额集合</li>
	 * 			<li>4,成功交易金额集合</li>
	 * 			<li>5,交易时间集合</li>
	 */
	public Collection<List> DealShow(List<String> channelList,List<String> product,List<String> appIdList) {
		List<List> list = new ArrayList();
		List<String> kets = new ArrayList();
		if(CollUtil.isNotEmpty(channelList))
			list.add(channelList);
		if(CollUtil.isNotEmpty(product))
			list.add(product);
		if(CollUtil.isNotEmpty(appIdList))
			list.add(appIdList);
		kets = getKets(list);
		List<String> keyList = new ArrayList();
		for(String key : kets) {
			keyList.add(key);
		}
		Statistics entity = new Statistics();
		entity.setKeylist(list);
		Collection data = getData(entity);
		return data;
	}
	
	
	/**
	 *	<p>key计算</p>
	 * @param list	集合内下标 0 为渠道  1为产品 2为 用户
	 * @return
	 */
	private List<String> getKets(List<? extends Collection> list ) {
		List keyList = new ArrayList();
		 List<String> lists = new ArrayList();
		if(list.size() > 1) {
			for(int i = 0 ; i<list.size(); i++) {
				Collection<String> collection = list.get(i);
				for(int j = list.size()-1; j>i; j--) {
					Collection<String> key = list.get(j);
					for(String keyi : collection) {
						for(String keyj : key) {
								lists.add(keyi+keyj);
						}
					}
				}
			}
			 for(int i = 0 ; i<list.size(); i++ ) {
				 Collection<String> collection = list.get(i);
				 for(String keyi : collection) {
					 for(String key : lists) {
						 if(!key.contains(keyi)) {
							 keyList.add(key+keyi);
						 }else {
							 keyList.add(key);
						 }
					 }
				 }
			 }
		}else {
			for(int i = 0 ; i<list.size(); i++) {
				Collection<String> collection = list.get(i);
				for(String key : collection) {
					lists.add(key);
				}
			}
		}
		return keyList;
	}
	
}
