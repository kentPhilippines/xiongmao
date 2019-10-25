package com.payProject.system.contorller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.payProject.config.common.Constant;
import com.payProject.config.common.Constant.Common;
import com.payProject.config.common.HighchartsResult;
import com.payProject.config.exception.OtherErrors;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.Channel;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.PayType;
import com.payProject.manage.entity.Statistics;
import com.payProject.manage.service.AccountService;
import com.payProject.manage.service.ChannelService;
import com.payProject.manage.service.DealOrderService;
import com.payProject.manage.util.HighcharBena;
import com.payProject.manage.util.HighcharBenaSuper;
import com.payProject.manage.util.StatisticsUtil;
import com.payProject.system.entity.Resources;
import com.payProject.system.service.ResourcesService;
import com.payProject.system.service.RoleService;
import com.payProject.system.service.UserRoleService;
import com.payProject.system.service.UserService;
import com.payProject.system.util.MapUtil;
import com.payProject.system.util.MenuUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
@Controller
@RequestMapping("/")
public class IndexContorller {
	Logger log = LoggerFactory.getLogger(IndexContorller.class);
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	ResourcesService resourcesService;
	@Autowired
	DealOrderService dealOrderServiceImpl;
	@Autowired
	StatisticsUtil	statisticsUtil;
	@Autowired
	ChannelService channelService;
	@Autowired
	AccountService accountService;
	/**
	 * 主页面跳转，在这之前需要验证权限
	 * @param m
	 * @return
	 */
	 @RequestMapping("/index")
	 public String index(Model m,HttpServletRequest request) {
		 /**
		  * 1,验证登录权限
		  * 2,获取用户所有的权限信息
		  * 3,拿到当前用户的登录信息
		  * 4,获取其他登录跳转页面URL
		  * 5,日志记录
		  */
		 /*1,首先第一步是由于shiro框架引入所以登录在框架内做了验证*/
		 Subject subject = SecurityUtils.getSubject();
		 Session session = subject.getSession();
		 Object attribute = session.getAttribute(Constant.User.USER_IN_SESSION());
		 Map<String, Object> objectToMap = MapUtil.objectToMap(attribute);
		 com.payProject.system.entity.User user = MapUtil.mapToBean(objectToMap,com.payProject.system.entity.User.class);
		 String userId = (String)objectToMap.get(Constant.User.USER_ID());
		 if(StrUtil.isBlank(userId))
				throw  new OtherErrors("用户未登录，或登录数据错误");
		 /*2,获取用户的所有菜单资源并转换信息*/
		 /*3,拿到当前用户的登录信息*/
		 /*4,获取其他登录跳转页面URL*/
		 List<Resources> resourcesList = resourcesService.findRourcesIdByUserId(userId);
		 List<Resources> menuList = MenuUtil.getMenuList(resourcesList);
		 m.addAttribute("menuList", menuList);
		 m.addAttribute("user", user);
		 /*5,日志记录,该功能由aop完成*/
		 if(Common.USER_NEI.equals(user.getUserType())) {//外部账号不可以看到 交易情况
			m.addAttribute("higcharhs", "homePage");
		 }else {
			 m.addAttribute("higcharhs", "home");
		 }
	        return "index";
	    }
	 @RequestMapping("/home")
	 public String home(Model m,HttpServletRequest request) {
		return "home";
	 }
	 
	 /**
	  * <p>该成交量算法已被舍弃</p>  交易量10笔万内此算法有效
	  * @param m
	  * @param request
	  * @return
	 public String homePage(Model m,HttpServletRequest request) {
			String userId = getUserId();
			log.info("当前查询流水人为："+userId);
			DealOrderEntity dealOrder =   new DealOrderEntity();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
			Calendar c = Calendar.getInstance();
			Calendar d = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);    //得到前一个月  
			String start = format.format(c.getTime());
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
			        23, 59, 59);
			Date endOfDate = calendar2.getTime();
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String end = format.format(endOfDate);
			dealOrder.setTime(start+" - "+end);
			List<DealOrderEntity> list = dealOrderServiceImpl.findPageDealOrderByDealOrder(dealOrder);//一个月之内的数据
			BigDecimal dealAmount = null;
			BigDecimal dealAmountSu = null;
			List<String> timeList = new ArrayList();
			List<String> weekDayInMonth = getWeekDayInMonth(start);//一个月之内所有的天数
			String time = null;
			Double dealDay = null;//每日交易笔数
			Double dealDaySu = null;//每日交易笔数(成功)
			BigDecimal dealDayMoney = null;//每日交易金额
			BigDecimal dealDayMoneySu = null;//每日交易金额(成功)
			List<Double> dealDayList = new ArrayList<Double>();
			List<Double> dealDaySuList = new ArrayList<Double>();
			List<Integer> dealDayMoneyList = new ArrayList<Integer>();
			List<Integer> dealDayMoneySuList = new ArrayList<Integer>();
			int number = 0;
			Double dealnumberAmout = 0.00;
			Double dealnumberAmoutSu = 0.00;
			Double dealnumber = 0.00;
			Double dealnumberSu = 0.00;
			Double double1 = 0.00;
			Double double2  = 0.00;
			Integer integer = 0;
			Integer integer2 = 0;
			log.info("【订单："+list+"】");
			format = new SimpleDateFormat("yyyy-MM-dd ");
			if(CollUtil.isNotEmpty(list)) {
				list = CollUtil.sortByProperty(list,"createTime");//根据创建日期排序
				int dealSize = list.size();//一个月内总交易
			for(DealOrderEntity entity : list) {
				number ++ ;
				 time = format.format(entity.getCreateTime());  //当前交易时间
				 if(!timeList.contains(time)) {//不包含的时候
					 timeList.add(time);
					 if(ObjectUtil.isNotNull(dealDay))
						 dealDayList.add(dealDay*1000);
					 if(ObjectUtil.isNotNull(dealDayMoney))
						 dealDayMoneyList.add(dealDayMoney.intValue());
					 if(ObjectUtil.isNotNull(dealDayMoneySu))
						 dealDayMoneySuList.add(dealDayMoneySu.intValue());
					 if(ObjectUtil.isNotNull(dealDaySu))
						 dealDaySuList.add(dealDaySu*1000);
					 dealDay = new Double(1);
					 dealDayMoney = new BigDecimal(entity.getDealAmount().toString());
					 if(Common.DEAL_STATUS_SU.equals(entity.getOrderStatus())) {
						 dealDaySu = new Double(1);
						 dealDayMoneySu = new BigDecimal(entity.getDealAmount().toString());
					 }else {
						 dealDaySu = new Double(0);
						 dealDayMoneySu = new BigDecimal(0);
					 }
				 } else {//包含的时候 当天所有的数据        交易笔数  交易金额   发起交易金额  发起交易笔数
					 dealDay ++;
					 if(Common.DEAL_STATUS_SU.equals(entity.getOrderStatus())) {
						 dealDaySu++;
						 dealDayMoneySu = dealDayMoneySu.add(entity.getDealAmount());
					 }
					 dealDayMoney = dealDayMoney.add(entity.getDealAmount());
				 };
				 if(number == list.size()) {//最后一次添加
						 dealnumberAmout  = (double) dealDayMoney.intValue();
						 dealnumberAmoutSu = (double) dealDayMoneySu.intValue();
						 dealnumber = dealDay;
						 dealnumberSu = dealDaySu;
					 dealDayList.add(dealDay);
					 dealDayMoneyList.add(dealDayMoney.intValue());//上一交易日 交易总额 或当前交易日交易成功总额
					 dealDayMoneySuList.add(dealDayMoneySu.intValue());//上一交易日交易成功总额   今天交易日交易成功总额
					 dealDaySuList.add(dealDaySu);
				 }
			}
			
			//上一日数据
			double1 = (Double)(dealDayList.get(dealDayList.size()-1)>0?dealDayList.get(dealDayList.size()-1):1);
			double2 =  (Double)(dealDaySuList.get(dealDaySuList.size()-1)>0?dealDaySuList.get(dealDaySuList.size()-1):1);
			integer = dealDayMoneyList.get(dealDayMoneyList.size()-1)>0?dealDayMoneyList.get(dealDayMoneyList.size()-1):1;
			integer2 = dealDayMoneySuList.get(dealDayMoneySuList.size()-1)>0?dealDayMoneySuList.get(dealDayMoneySuList.size()-1):1;
			}
			dealnumberAmout = dealnumberAmout  != 0 ? (dealnumberAmout / integer) - 1:0 ;
			dealnumberAmoutSu =   dealnumberAmoutSu != 0? ( dealnumberAmoutSu / integer2) - 1 : 0;
			dealnumber =dealnumber != 0 ?  (dealnumber /double1 ) - 1 : 0;
			dealnumberSu = dealnumberSu != 0 ? (dealnumberSu /double2 ) - 1 :0;
			List sum = new ArrayList();
			sum.add(dealnumberAmout*100);
			sum.add(dealnumber*100);
			sum.add(dealnumberAmoutSu*100);
			
			sum.add(dealnumberSu*100);
			m.addAttribute("sum", new JSONArray(sum));
			m.addAttribute("dealDayList", new JSONArray(dealDayList));
			m.addAttribute("dealDaySuList",  new JSONArray(dealDaySuList));
			m.addAttribute("dealDayMoneyList", new JSONArray(dealDayMoneyList)  );
			m.addAttribute("dealDayMoneySuList", new JSONArray(dealDayMoneySuList) );
			m.addAttribute("timeList",new JSONArray(timeList) );
		return "homePage";
	 }
	 */
	 private String getUserId() {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			Object attribute = session.getAttribute(Constant.User.USER_IN_SESSION());
			Map<String, Object> objectToMap = MapUtil.objectToMap(attribute);
			com.payProject.system.entity.User user = MapUtil.mapToBean(objectToMap,com.payProject.system.entity.User.class);
			String userId = (String)objectToMap.get(Constant.User.USER_ID());
			return userId;
	}
	 public   List<String> getWeekDayInMonth(String date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> resultList = new ArrayList<>();
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(sdf.parse(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			resultList.add( sdf.format(calendar.getTime()));
			for (int i = 1; i < days; i++) {
				calendar.add(calendar.DATE, 1);
				resultList.add(sdf.format(calendar.getTime()));
			}
			return resultList;
		}
		@ResponseBody
		@RequestMapping("/myUserDealShow")
		public List<HighchartsResult> myUserDealShow(){
			DealOrderEntity dealOrder =   new DealOrderEntity();
			List result = new ArrayList();
			String userId = getUserId();
			log.info("当前查询流水人为："+userId);
			//2019-09-05 - 2019-10-23
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Calendar d = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);    //得到前一个月  
			String start = format.format(c.getTime());
			String end = format.format(d.getTime());
			dealOrder.setTime(start+" - "+end);
			List<DealOrderEntity> list = dealOrderServiceImpl.findPageDealOrderByDealOrder(dealOrder);
			for(DealOrderEntity entity : list) {
				List result1 = new ArrayList();
				result1.add(entity.getCreateTime().getTime());
				result1.add(entity.getDealAmount());
				result.add(result1);
			}
			
			return result;
		}
		/**
		 * <p>数据统计</p>
		 * <p>前端页面需要优化</p>
		 */
		@RequestMapping("/homePage")
		public String Statistics(Model m,HttpServletRequest request) {
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
			HighcharBenaSuper<List<HighcharBena>> statistics = statisticsUtil.statistics(dealShow,channelList);
			//产品交易量计算规则：各产品交易数据提取
			HighcharBenaSuper<List<HighcharBena>> statistics2 = statisticsUtil.statistics(dealShow2,productList);
			//用户交易量计算规则：各用户交易数据提取
			HighcharBenaSuper<List<HighcharBena>> statistics3 = statisticsUtil.statistics(dealShow3,accountList);
			/**
			 * <p>数据格式变化</p>
			 */
			HighcharBenaSuper<List<HighcharBena>> channel = statisticsUtil.toJson(statistics);
			HighcharBenaSuper<List<HighcharBena>> product = statisticsUtil.toJson(statistics2);
			HighcharBenaSuper<List<HighcharBena>> account = statisticsUtil.toJson(statistics3);
			List<String> list = new ArrayList();
			Set<String> keySet = sumZone.keySet();
			for(String time : keySet) {
				list.add(time);
			}
			statisticsUtil.sort2(list);
			//排序过后的时间与数据对应关系
			List<Double> amountList = new ArrayList();
			List<Double> amountSuList = new ArrayList();
			for(String time: list) {
				Statistics statistics4 = sumZone.get(time);//每日交易情况
				amountList.add(Double.valueOf(statistics4.getAmount()));
				amountSuList.add(Double.valueOf(statistics4.getAmountSu()));
			}
			HighcharBena bean = new HighcharBena();
			bean.setName("当日成功交易金额");
			bean.setData(amountSuList);
			HighcharBena bean1 = new HighcharBena();
			bean1.setName("当日交易金额");
			bean1.setData(amountList);
			List<HighcharBena> beanList = new ArrayList();
			beanList.add(bean);
			beanList.add(bean1);
			List channelTimeList = channel.getTimeList();
			List productTimeList = product.getTimeList();
			List accounttimeList = account.getTimeList();
			List<HighcharBena> channnelDate = channel.getObj();
			List<HighcharBena> productDate = product.getObj();
			List<HighcharBena> accountDate = account.getObj();
			m.addAttribute("channelTimeList", new JSONArray(channelTimeList));
			m.addAttribute("productTimeList", new JSONArray(productTimeList));
			m.addAttribute("accounttimeList", new JSONArray(accounttimeList));
			m.addAttribute("channnelDate", JSONUtil.toJsonStr(channnelDate).toString());
			m.addAttribute("productList", JSONUtil.toJsonStr(productDate).toString());
			m.addAttribute("accountDate", JSONUtil.toJsonStr(accountDate).toString());
			m.addAttribute("time",  new JSONArray(list));
			m.addAttribute("sumDate", JSONUtil.toJsonStr(beanList).toString());
			System.out.println(JSONUtil.toJsonStr(channel).toString());
			System.out.println(JSONUtil.toJsonStr(product).toString());
			System.out.println(JSONUtil.toJsonStr(account).toString());
			return "homePage";
		}
}
