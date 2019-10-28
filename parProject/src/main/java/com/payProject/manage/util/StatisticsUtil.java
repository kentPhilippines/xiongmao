package com.payProject.manage.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.payProject.manage.entity.Channel;
import com.payProject.manage.entity.Statistics;
import com.payProject.manage.service.ChannelService;
import com.payProject.manage.service.StatisticsService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;


/**
 * <p>数据统计工具类</p>
 * @author K
 */
@Component
public class StatisticsUtil {
	@Autowired
	StatisticsService statisticsServiceImpl;
	/**
	 * <p>柱状图数据统计</p>
	 */
	private List<Statistics> getData(Statistics entity){
		List<Statistics> staList = statisticsServiceImpl.findStatisticsByAll(entity);
		/**
		 * ###################################
		 * 	1,获取数据统计statistics表的信息
		 * 	2,根据信息进行分类
		 * 	3,获取当前的交易情况
		 * 	4,数据封装
		 * ###################################
		  */
		return staList;
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
	public List<Statistics> DealShow(List<String> list) {
		List<Statistics> dealShow = DealShow(list,null);
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
	public List<Statistics> DealShow(List<String> channelList,List<String> appIdList) {
		List<Statistics> dealShow = DealShow(channelList,appIdList,null);
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
	public List<Statistics> DealShow(List<String> channelList,List<String> product,List<String> appIdList) {
		List<List> list = new ArrayList();
		List<String> kets = new ArrayList();
		if(CollUtil.isNotEmpty(channelList))
			list.add(channelList);
		if(CollUtil.isNotEmpty(product))
			list.add(product);
		if(CollUtil.isNotEmpty(appIdList))
			list.add(appIdList);
		kets = getKets(list);
		if(CollUtil.isEmpty(kets)) {
			return null;
		}
		List<String> keyList = new ArrayList();
		for(String key : kets) {
			keyList.add(key);
		}
		Statistics entity = new Statistics();
		entity.setKeysList(keyList);
		return getData(entity);
	}
	
	
	/**
	 *	<p>key计算</p>
	 * @param list	集合内下标 0 为渠道  1为产品 2为 用户
	 * @return
	 */
	private List<String> getKets(List<? extends Collection> list ) {
		List keyList = new ArrayList();
		 List<String> lists = new ArrayList();
		 if(CollUtil.isEmpty(list)) {
			 return null;
		 }
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
					keyList.add(key);
				}
			}
		}
		return keyList;
	}
	
	
	/**
	 * ################ ###{@link  }
	 * <strong>当前前端的可视化数据图形插件发生变更时,该方法要做出同步改变</strong>
	 * <p>将交易数据转换为可视化数据格式</p>
	 * @param dealShow		渠道交易数据或者产品交易数据或者用户交易数据
	 * @param List			渠道数据或者产品数据或者用户数据
	 * @return
	 * @see 当个人用户获取交易数据的时候 List 值为  产品或者渠道 或者个人的交易账号   与运营传递规则一致 只需要控制个人的参数多少即可
	 * #################################### 
	 */
	@SuppressWarnings("unchecked")
	public HighcharBenaSuper<List<HighcharBena>> statisticsToAmount(List<Statistics> dealShow, List<String> List){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Statistics> SumZone = new HashMap<String, Statistics>();
		for(Statistics bean : dealShow) {
			String date = format.format(bean.getTime());
			  Statistics statistics = SumZone.get(date+bean.getKey());
			 if(ObjectUtil.isNull(statistics)) {//没存过
				 SumZone.put(date+bean.getKey(), bean);
			 } else {//防止数据出现错误
				 BigDecimal amount = new BigDecimal(statistics.getAmount());
				 BigDecimal amountSu = new BigDecimal(statistics.getAmountSu());
				 BigDecimal amountBean = new BigDecimal(bean.getAmount());
				 BigDecimal amountSuBean = new BigDecimal(bean.getAmountSu());
				 Integer countBean = Integer.valueOf(bean.getDealCount());
				 Integer count = Integer.valueOf(statistics.getDealCount());
				 Integer countSuBean = Integer.valueOf(bean.getDealCountSu());
				 Integer countSu = Integer.valueOf(statistics.getDealCountSu());
				 bean.setAmount(amount.add(amountBean).toString());
				 bean.setAmountSu(amountSu.add(amountSuBean).toString());
				 bean.setDealCount(String.valueOf(countBean+count));
				 bean.setDealCountSu(String.valueOf(countSuBean+countSu));
				 SumZone.put(date+bean.getKey(), bean);
			 }
		}
		Set<String> keySet = SumZone.keySet();
		List<List> SumList = new ArrayList<List>();
		List<List> SumCountList = new ArrayList<List>();
		for(String No: List) {
			for(String key : keySet) {
				if(key.contains(No)) {//一个渠道三个集合,遍历总集合,拿到渠道分类数据
					Statistics statistics = SumZone.get(key);
					List<String> amountList = new ArrayList<String>();
					List<String> countList = new ArrayList<String>();
					String format2 = format.format(statistics.getTime());
					amountList.add(key);
					amountList.add(statistics.getAmount()+format2);
					amountList.add(statistics.getAmountSu()+format2);
					amountList.add(format2);
					countList.add(key);
					countList.add(statistics.getDealCount()+format2);
					countList.add(statistics.getDealCountSu()+format2);
					countList.add(format2);
					SumList.add(amountList);
					SumCountList.add(countList);
				}
			}
		}
		/**
		 * 单独的时间集合
		 * 数据格式为：
		 * name ：数据情况
		 * data ：数据集合
		 * 转为json数据格式
		 */
		Map<String,List> higAmountMap = new HashMap<String, List>();//交易金额情况
		Map<String,List> higCountMap = new HashMap<String, List>();//交易笔数情况
		List<String> timeList = new ArrayList();
		sort(SumList);
		sort(SumCountList);
		for(List<String> sum : SumList) {//各KEY交易数据 金额
			String key = CollUtil.getFirst(sum);
			String time = CollUtil.getLast(sum);
			if(!timeList.contains(time)) {
				timeList.add(time);
			}
			higAmountMap.put(key, sum);//当前key必为唯一
		}
		for(List<String> sum : SumCountList) {//各KEY交易数据  笔数
			String key = CollUtil.getFirst(sum);
			String time = CollUtil.getLast(sum);
			higCountMap.put(key, sum);//当前key必为唯一
		}
		List<String> keyMaps = new ArrayList<String>();
		for(int e =0;e < timeList.size() ; e++) {
			for(String string2 : List) {
				String string = timeList.get(e);
				keyMaps.add(string+string2);
			}
		}
		/**
		 * <p>一个渠道两条数据</p>
		 */
		List<HighcharBena> number = new ArrayList<HighcharBena>();
		for(String key : List) {
			List<String> amount = new ArrayList<String>();//按照时间顺序的所有金额
			List<String> amountSu = new ArrayList<String>();//按照时间顺序的所有成功金额
			HighcharBena bean = new HighcharBena();
			HighcharBena beanSu = new HighcharBena();
			for(String keyMap : keyMaps) {
				 if(keyMap.contains(key)) {//该KEY是该渠道的组合key
					  List list2 = higAmountMap.get(keyMap);
					  if(CollUtil.isNotEmpty(list2)) {
						  Object object = list2.get(1);//该渠道的所有金额
						  Object object2 = list2.get(2);//该渠道的所有金额
						  amount.add(object.toString());
						  amountSu.add(object2.toString());
					  }
				 }
				 
			}
			if(CollUtil.isNotEmpty(amount) && CollUtil.isNotEmpty(amountSu)) {
				bean.setName(key + "交易总金额");
				bean.setData(amount);
				beanSu.setName(key + "成功交易金额");
				beanSu.setData(amountSu);
				number.add(bean);
				number.add(beanSu);
			}
		}
		HighcharBenaSuper supe = new HighcharBenaSuper<HighcharBena>();
		supe.setObj(number);
		supe.setTimeList(timeList);
		return supe;
	}
	/**
	 * ################ ###{@link  }
	 * <strong>当前前端的可视化数据图形插件发生变更时,该方法要做出同步改变</strong>
	 * <p>将交易数据转换为可视化数据格式</p>
	 * @param dealShow		渠道交易数据或者产品交易数据或者用户交易数据
	 * @param List			渠道数据或者产品数据或者用户数据
	 * @return
	 * @see 当个人用户获取交易数据的时候 List 值为  产品或者渠道 或者个人的交易账号   与运营传递规则一致 只需要控制个人的参数多少即可
	 * #################################### 
	 */
	@SuppressWarnings("unchecked")
	public HighcharBenaSuper<List<HighcharBena>> statisticsToCount(List<Statistics> dealShow, List<String> List){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Statistics> SumZone = new HashMap<String, Statistics>();
		for(Statistics bean : dealShow) {
			String date = format.format(bean.getTime());
			Statistics statistics = SumZone.get(date+bean.getKey());
			if(ObjectUtil.isNull(statistics)) {//没存过
				SumZone.put(date+bean.getKey(), bean);
			} else {//防止数据出现错误
				BigDecimal amount = new BigDecimal(statistics.getAmount());
				BigDecimal amountSu = new BigDecimal(statistics.getAmountSu());
				BigDecimal amountBean = new BigDecimal(bean.getAmount());
				BigDecimal amountSuBean = new BigDecimal(bean.getAmountSu());
				Integer countBean = Integer.valueOf(bean.getDealCount());
				Integer count = Integer.valueOf(statistics.getDealCount());
				Integer countSuBean = Integer.valueOf(bean.getDealCountSu());
				Integer countSu = Integer.valueOf(statistics.getDealCountSu());
				bean.setAmount(amount.add(amountBean).toString());
				bean.setAmountSu(amountSu.add(amountSuBean).toString());
				bean.setDealCount(String.valueOf(countBean+count));
				bean.setDealCountSu(String.valueOf(countSuBean+countSu));
				SumZone.put(date+bean.getKey(), bean);
			}
		}
		Set<String> keySet = SumZone.keySet();
		List<List> SumList = new ArrayList<List>();
		List<List> SumCountList = new ArrayList<List>();
		for(String No: List) {
			for(String key : keySet) {
				if(key.contains(No)) {//一个渠道三个集合,遍历总集合,拿到渠道分类数据
					Statistics statistics = SumZone.get(key);
					List<String> amountList = new ArrayList<String>();
					List<String> countList = new ArrayList<String>();
					String format2 = format.format(statistics.getTime());
					amountList.add(key);
					amountList.add(statistics.getAmount()+format2);
					amountList.add(statistics.getAmountSu()+format2);
					amountList.add(format2);
					countList.add(key);
					countList.add(statistics.getDealCount()+format2);
					countList.add(statistics.getDealCountSu()+format2);
					countList.add(format2);
					SumList.add(amountList);
					SumCountList.add(countList);
				}
			}
		}
		/**
		 * 单独的时间集合
		 * 数据格式为：
		 * name ：数据情况
		 * data ：数据集合
		 * 转为json数据格式
		 */
		Map<String,List> higAmountMap = new HashMap<String, List>();//交易金额情况
		Map<String,List> higCountMap = new HashMap<String, List>();//交易笔数情况
		List<String> timeList = new ArrayList();
		sort(SumList);
		sort(SumCountList);
		for(List<String> sum : SumList) {//各KEY交易数据 金额
			String key = CollUtil.getFirst(sum);
			String time = CollUtil.getLast(sum);
			if(!timeList.contains(time)) {
				timeList.add(time);
			}
			higAmountMap.put(key, sum);//当前key必为唯一
		}
		for(List<String> sum : SumCountList) {//各KEY交易数据  笔数
			String key = CollUtil.getFirst(sum);
			String time = CollUtil.getLast(sum);
			higCountMap.put(key, sum);//当前key必为唯一
		}
		List<String> keyMaps = new ArrayList<String>();
		for(int e =0;e < timeList.size() ; e++) {
			for(String string2 : List) {
				String string = timeList.get(e);
				keyMaps.add(string+string2);
			}
		}
		/**
		 * <p>一个渠道两条数据</p>
		 */
		List<HighcharBena> number = new ArrayList<HighcharBena>();
		for(String key : List) {
			List<String> amount = new ArrayList<String>();//按照时间顺序的所有金额
			List<String> amountSu = new ArrayList<String>();//按照时间顺序的所有成功金额
			HighcharBena bean = new HighcharBena();
			HighcharBena beanSu = new HighcharBena();
			for(String keyMap : keyMaps) {
				if(keyMap.contains(key)) {//该KEY是该渠道的组合key
					List list2 = higCountMap.get(keyMap);
					if(CollUtil.isNotEmpty(list2)) {
						Object object = list2.get(1);//该渠道的所有金额
						Object object2 = list2.get(2);//该渠道的所有金额
						amount.add(object.toString());
						amountSu.add(object2.toString());
					}
				}
				
			}
			if(CollUtil.isNotEmpty(amount) && CollUtil.isNotEmpty(amountSu)) {
				bean.setName(key + "交易总笔数");
				bean.setData(amount);
				beanSu.setName(key + "成功交易笔数");
				beanSu.setData(amountSu);
				number.add(bean);
				number.add(beanSu);
			}
		}
		HighcharBenaSuper supe = new HighcharBenaSuper<HighcharBena>();
		supe.setObj(number);
		supe.setTimeList(timeList);
		return supe;
	}
	/**
	 * <p>对当前集合数据进行规整</p>
	 * <p>当前维度的数据进行补0操作</p>
	 * <p>对多余数据进行舍弃</p>
	 * @param statistics
	 * @return
	 */
	public HighcharBenaSuper<List<HighcharBena>> toJson(HighcharBenaSuper<List<HighcharBena>> statistics){
		List<HighcharBena> obj = statistics.getObj();
		List<String> timeList = statistics.getTimeList();
		List<Map> mapList = new ArrayList();
		for(HighcharBena cc : obj){//对当前时间内无数据的集合 补0 标识当前时间交易量为0
			List<String> data = cc.getData();
			List<String> dataList = new ArrayList<String>();//900次数据匹配
			for(int j = 0; j < timeList.size(); j++) {
				String string = timeList.get(j);//时间
				 for(String objs : data) {
					 if(string.equals(StrUtil.subSufByLength(objs,10))) {
						 if(!dataList.contains(objs)) {
							 dataList.add(objs);
						 }
					 }else {
						 if(!dataList.contains(StrUtil.subSufByLength(objs,10))) {
							 if(!dataList.contains("0"+string)) {
								 dataList.add("0"+string);
							 }
						 }
					 }
				 }
			}
			Map<String,String> maps = new HashMap<String,String>();
			List<String> list = new ArrayList();
			for(String a : dataList) {//删除当前时间内重复的数据 删除多余的补0数据
				String object = maps.get(StrUtil.subSufByLength(a,10));
				if(StrUtil.isNotBlank(object)) {
					if(!a.equals("0"+StrUtil.subSufByLength(a,10)) && object.equals("0"+StrUtil.subSufByLength(a,10))) {
						maps.put(StrUtil.subSufByLength(a,10), a);
					}
				}else {
					maps.put(StrUtil.subSufByLength(a,10), a);
				}
			}
			Set<String> keySet = maps.keySet();
			for(String key : keySet) {
				list.add(maps.get(key));
			}
			sort2(list);//依据时间对集合排序
			List zoneList =  new ArrayList();
			for(String st: list) {
				String subWithLength = StrUtil.subWithLength(st,0,-10);
				zoneList.add(Double.valueOf(subWithLength));
			}
			cc.setData(zoneList);
		}
		return statistics;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * <p>依据时间先后进行排序</p>
	 * @param list
	 */
	public void sort(List<List> list){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Collections.sort(list,new Comparator<List>() {
			@Override
			public int compare( List arg0,  List arg1) {
				Object object = arg0.get(3);//获取时间
				Object object1 = arg1.get(3);//获取时间
				Date parse = null;
				Date parse2 = null;
				try {
					 parse = format.parse(object.toString());
					 parse2 = format.parse(object1.toString());
				} catch (ParseException e) {
					System.out.println("数据筛选是时间格式转换出现异常");
					e.printStackTrace();
				}
				return parse.compareTo(parse2);
			}
		});
	}
	public void sort2(List<String> list){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Collections.sort(list,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String subSufByLength = StrUtil.subSufByLength(o1,10);
				String subSufByLength2 = StrUtil.subSufByLength(o2,10);
				Date parse = null;
				Date parse2 = null;
				try {
					 parse = format.parse(subSufByLength.toString());
					 parse2 = format.parse(subSufByLength2.toString());
				} catch (ParseException e) {
					System.out.println("数据筛选时时间格式转换出现异常");
					e.printStackTrace();
				}
				return parse.compareTo(parse2);
			}
		 
		});
	}
}
