package parProject.com.test.test;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.Channel;
import com.payProject.manage.entity.PayType;
import com.payProject.manage.entity.Statistics;
import com.payProject.manage.service.AccountService;
import com.payProject.manage.service.ChannelService;
import com.payProject.manage.util.HighcharBena;
import com.payProject.manage.util.HighcharBenaSuper;
import com.payProject.manage.util.StatisticsUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import parProject.com.test.TmallApplicationTests;

public class Data extends TmallApplicationTests {
	@Autowired
	StatisticsUtil	statisticsUtil;
	@Autowired
	ChannelService channelService;
	@Autowired
	AccountService accountService;
	@Test
	public void dateShow(){
		List<Channel> findChannelByAll = channelService.findChannelByAll();
		List<PayType> findPayTypeByAll = channelService.findPayTypeByAll();
		List<AccountEntity> findAccountAll = accountService.findAccountAll();
		List<String> channelList = new ArrayList<String>();
		List<String> productList = new ArrayList<String>();
		List<String> accountList = new ArrayList<String>();
		for(Channel ch : findChannelByAll) {
			channelList.add(ch.getChannelNo());
		}
		for(PayType pt : findPayTypeByAll) {
			productList.add(pt.getPayTypeNo());
		}
		for(AccountEntity ac : findAccountAll) {
			accountList.add(ac.getAccountId());
		}
		List<Statistics> dealShow = statisticsUtil.DealShow(channelList);//渠道交易
		List<Statistics> dealShow2 = statisticsUtil.DealShow(productList);//产品交易
		List<Statistics> dealShow3 = statisticsUtil.DealShow(accountList);//用户交易
		HighcharBenaSuper<List<HighcharBena>> channel = new HighcharBenaSuper<List<HighcharBena>>();
		if(CollUtil.isNotEmpty(dealShow)) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Statistics> sumZone = new HashMap<String, Statistics>();
		//总交易量计算规则： 所有渠道交易量相加
		for(Statistics bean : dealShow) {
			String date = format.format(bean.getTime());
			  Statistics statistics = sumZone.get(date);
			 if(ObjectUtil.isNull(statistics)) {//没存过
				 sumZone.put(date, bean);
			 } else {//存过
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
				 sumZone.put(date, bean);
			 }
		}
		//渠道交易量计算规则：各渠道交易数据提取
		HighcharBenaSuper<List<HighcharBena>> statistics = statisticsUtil.statisticsToAmount(dealShow,channelList);
		channel = statisticsUtil.toJson(statistics);
		}
		HighcharBenaSuper<List<HighcharBena>> prodecut = new HighcharBenaSuper<List<HighcharBena>>();
		if(CollUtil.isNotEmpty(dealShow2)) {
			//产品交易量计算规则：各产品交易数据提取
			HighcharBenaSuper<List<HighcharBena>> statistics2 = statisticsUtil.statisticsToAmount(dealShow2,productList);
			prodecut = statisticsUtil.toJson(statistics2);
		}
		HighcharBenaSuper<List<HighcharBena>> account = new HighcharBenaSuper<List<HighcharBena>>();
		if(CollUtil.isNotEmpty(dealShow3)) {
			HighcharBenaSuper<List<HighcharBena>> statistics3 = statisticsUtil.statisticsToAmount(dealShow3,accountList);
			account = statisticsUtil.toJson(statistics3);
		}
		//用户交易量计算规则：各用户交易数据提取
		System.out.println(JSONUtil.toJsonStr(channel).toString());
		System.out.println(JSONUtil.toJsonStr(prodecut).toString());
		System.out.println(JSONUtil.toJsonStr(account).toString());
	}
	void sort2(List<String> list){
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
