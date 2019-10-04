package com.payProject.manage.contorller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payProject.config.common.AutocompleteResult;
import com.payProject.config.common.Constant;
import com.payProject.config.common.DataResult;
import com.payProject.config.common.HighchartsResult;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.common.Constant.Common;
import com.payProject.config.exception.OtherErrors;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.AccountFee;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.RunOrder;
import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.service.AccountService;
import com.payProject.manage.service.ChannelService;
import com.payProject.manage.service.DealOrderService;
import com.payProject.manage.service.OrderRunService;
import com.payProject.system.entity.User;
import com.payProject.system.service.UserService;
import com.payProject.system.util.EncryptUtil;
import com.payProject.system.util.MapUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
/**
 * <p>外部账号专用</p>
 * @author K
 */
@Controller
@RequestMapping("/manage/account")
public class MyAccountContorller {
	Logger log = LoggerFactory.getLogger(MyAccountContorller.class);
	@Autowired
	AccountService accountServiceImpl;
	@Autowired
	ChannelService channelServiceImpl;
	@Autowired
	UserService userService;
	@Autowired
	DealOrderService dealOrderServiceImpl;
	@Autowired
	OrderRunService	orderRunServiceImpl;
	/**
	 * <p>个人资料详情</p>
	 * @param m
	 * @return
	 */
	@RequestMapping("/show")
	public String accountShow(Model m){
		 Subject subject = SecurityUtils.getSubject();
		 Session session = subject.getSession();
		 Object attribute = session.getAttribute(Constant.User.USER_IN_SESSION());
		 Map<String, Object> objectToMap = MapUtil.objectToMap(attribute);
		 com.payProject.system.entity.User user = MapUtil.mapToBean(objectToMap,com.payProject.system.entity.User.class);
		 String userId = (String)objectToMap.get(Constant.User.USER_ID());
		 if(StrUtil.isBlank(userId))
			 throw  new OtherErrors("用户未登录，或登录数据错误");
		 m.addAttribute("user", user);
		return "/manage/account/accountShow";
	}
	
	/**
	 * <p>展示修改界面</p>
	 * @param m
	 * @param account
	 * @return
	 */
	@RequestMapping("/updataPasswordShow")
	public String updataPasswordShow(Model m ,User  user) {
		if(StrUtil.isBlank(user.getUserId()))
			throw new ParamException("商户号有误,请联系客服人员处理");
		User findUserByUserId = userService.findUserByUserId(user.getUserId());
		m.addAttribute("userId", findUserByUserId.getUserId());
		return "/manage/account/passwordUpdata";
	}
	@RequestMapping("/updataPayPasswordShow")
	public String updataPayPasswordShow(Model m ,User  user) {
		if(StrUtil.isBlank(user.getUserId()))
			throw new ParamException("商户号有误,请联系客服人员处理");
		User findUserByUserId = userService.findUserByUserId(user.getUserId());
		m.addAttribute("userId", findUserByUserId.getUserId());
		m.addAttribute("isPay", "isPay");
		return "/manage/account/passwordUpdata";
	}
	
	@ResponseBody
	@RequestMapping("/updataPassword")
	@Transactional
	public JsonResult updataPassword(Model m ,User  user,String password){
		if(StrUtil.isBlank(password))
			throw new ParamException("有误");
		User findUserByUserId = userService.findUserByUserId(user.getUserId());
		Map<String, String> encryptPassword = EncryptUtil.encryptPassword(findUserByUserId.getUserId(), user.getUserPassword());
		String pass = encryptPassword.get(Constant.Common.PASSWORD);
		String password1 = "";
		if(pass.equals(findUserByUserId.getUserPassword())){
			Map<String, String> encryptPassword2 = EncryptUtil.encryptPassword(user.getUserId(),password);
			password1 = encryptPassword2.get(Constant.Common.PASSWORD);//获得新密码
		}else {
			return JsonResult.buildFailResult("旧密码验证错误");
		}
		if(StrUtil.isBlank(password))
			return JsonResult.buildFailResult("未获取密码");
		findUserByUserId.setUserPassword(password1);
		boolean updateUserByUserId = userService.UpdateUserByUserId(findUserByUserId);
		if(updateUserByUserId) {
			SecurityUtils.getSubject().logout();
			return JsonResult.buildSuccessMessage("修改成功，请重新登录");
		}
		return JsonResult.buildFailResult("未知错误，联系管理员");
	}
	/**
	 * <p>修改支付密码</p>
	 * @param m
	 * @param user
	 * @param password		支付密码
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updataPayPassword")
	@Transactional
	public JsonResult updataPayPassword(Model m ,User  user,String password){
		if(StrUtil.isBlank(password))
			throw new ParamException("有误");
		User findUserByUserId = userService.findUserByUserId(user.getUserId());
		Map<String, String> encryptPassword = EncryptUtil.encryptPassword(user.getUserPassword());
		String pass = encryptPassword.get(Constant.Common.PAYPASSWORD);
		String password1 = "";
		if(pass.equals(findUserByUserId.getUserPassword())){
			Map<String, String> encryptPassword2 = EncryptUtil.encryptPassword(password);
			password1 = encryptPassword2.get(Constant.Common.PAYPASSWORD);//获得新密码
		}else {
			return JsonResult.buildFailResult("旧支付密码验证错误");
		}
		if(StrUtil.isBlank(password))
			return JsonResult.buildFailResult("未获取支付密码");
		findUserByUserId.setUserPassword(password1);
		boolean updateUserByUserId = userService.UpdateUserByUserId(findUserByUserId);
		if(updateUserByUserId) {
			SecurityUtils.getSubject().logout();
			return JsonResult.buildSuccessMessage("修改成功，请重新登录");
		}
		return JsonResult.buildFailResult("未知错误，联系管理员");
	}
	@RequestMapping("/userShow")
	public String userShow( ) {
		return "/manage/account/userShow";
	}
	
	@RequestMapping("/userAccount")
	public String userAccount(Model m ) {
		List<AccountEntity> accountList = accountServiceImpl.findAccountAll();
		List<User> userList = userService.findUserAll();
		List<AutocompleteResult> acco =new  ArrayList<AutocompleteResult>();
		List<AutocompleteResult> use =new  ArrayList<AutocompleteResult>();
		for( AccountEntity entity : accountList ) {
			AutocompleteResult acc = new AutocompleteResult();
			acc.setName(entity.getAccountName());
			acc.setPinyin(entity.getAccountId());
			acco.add(acc);
		}
		for( User entity : userList ) {
			AutocompleteResult acc = new AutocompleteResult();
			acc.setName(entity.getUserName());
			acc.setPinyin(entity.getUserId());
			use.add(acc);
		}
		m.addAttribute("accountList", JSONUtil.toJsonPrettyStr(acco));
		m.addAttribute("userList", JSONUtil.toJsonPrettyStr(use));
		return "/manage/account/userAccountAdd";
	}
	@ResponseBody
	@RequestMapping("addUserAccount")
	@Transactional
	public JsonResult addUserAccount(UserAccount userAccount ){
		if(StrUtil.isBlank(userAccount.getAccountId())) {
			throw new ParamException("商户号为空");
		}
		if(StrUtil.isBlank(userAccount.getUserId())) {
			throw new ParamException("账户号");
		}
		User findUserByUserId = userService.findUserByUserId(userAccount.getUserId());
		if(Constant.Common.USER_NEI.equals(findUserByUserId.getUserType())) {
			throw new OtherErrors("该账户号不支持绑定商户号");
		}
		boolean  flag = userService.addUserAccount(userAccount);
		if(flag) {
			return JsonResult.buildSuccessMessage("账户绑定成功");
		}
		return JsonResult.buildFailResult("账户绑定失败");
	}
	@ResponseBody
	@RequestMapping("/userAccountList")
	public PageResult<UserAccount> userAccountList(UserAccount account,String page,String limit){
		log.info("查询商户请求参数"+account.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<UserAccount> list = userService.findPageUserAccountByAccount(account);
		 PageInfo<UserAccount> pageInfo = new PageInfo<UserAccount>(list);
		 PageResult<UserAccount> pageR = new PageResult<UserAccount>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("商户列表响应结果集"+pageR.toString());
		return pageR;
	}
	@ResponseBody
	@RequestMapping("/userAccountDel")
	@Transactional
	public JsonResult userAccountDel(UserAccount account,Model m ) {
		 log.info("商户和账户删除对应关系的id为："+account.getId());
		 boolean flag = userService.deleteUserAccount(account.getId());
			if(flag) {
				return JsonResult.buildSuccessMessage("删除成功");
			}else {
				 throw new OtherErrors("删除商户异常");
			}
	    }
	/**
	 * <p>一个月之内的数据做数据展示</p>
	 * @return
	 */
	@RequestMapping("/userDealShow")
	public String userDealShow(Model m) {
		String userId = getUserId();
		log.info("当前查询流水人为："+userId);
		 List<String> accountList = new ArrayList<String>();
		 List<UserAccount> UserAccountList = userService.findUserAccountByUserId(userId);
		 for(UserAccount acc : UserAccountList) {
			 accountList.add(acc.getAccountId());
		 }
		DealOrderEntity dealOrder =   new DealOrderEntity();
		dealOrder.setAccountList(accountList);
		//2019-09-05 - 2019-10-23
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		Calendar d = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);    //得到前一个月  
		String start = format.format(c.getTime());
		String end = format.format(d.getTime());
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
		double dealnumberAmout = 0;
		double dealnumberAmoutSu = 0;
		double dealnumber = 0;
		double dealnumberSu = 0;
		Double double1 = 0.00;
		Double double2 = 0.00;
		int integer = 1;
		int integer2 = 1;
		if(CollUtil.isNotEmpty(list)) {
			list = CollUtil.sortByProperty(list,"createTime");//根据创建日期排序
			int dealSize = list.size();//一个月内总交易
		for(DealOrderEntity entity : list) {
			number ++ ;
			 time = format.format(entity.getCreateTime());  //当前交易时间
			 if(!timeList.contains(time)) {//不包含的时候
				 timeList.add(time);
				 if(ObjectUtil.isNotNull(dealDay))
					 dealDayList.add(dealDay);
				 if(ObjectUtil.isNotNull(dealDayMoney))
					 dealDayMoneyList.add(dealDayMoney.intValue());
				 if(ObjectUtil.isNotNull(dealDayMoneySu))
					 dealDayMoneySuList.add(dealDayMoneySu.intValue());
				 if(ObjectUtil.isNotNull(dealDaySu))
					 dealDaySuList.add(dealDaySu);
				 dealDay = new Double(1);
				 dealDayMoney = new BigDecimal(entity.getDealAmount().toString());
				 if(Common.DEAL_STATUS_SU.equals(entity.getOrderStatus())) {
					 dealDaySu = new Double(1);
					 dealDayMoneySu = new BigDecimal(entity.getDealAmount().toString());
				 }else {
					 dealDaySu = new Double(0);
					 dealDayMoneySu = new BigDecimal(0);
				 }
			 }else {//包含的时候 当天所有的数据        交易笔数  交易金额   发起交易金额  发起交易笔数
				 dealDay ++;
				 if(Common.DEAL_STATUS_SU.equals(entity.getOrderStatus())) {
					 dealDaySu++;
					 dealDayMoneySu = dealDayMoneySu.add(entity.getDealAmount());
				 }
				 dealDayMoney = dealDayMoney.add(entity.getDealAmount());
			 };
			 if(number == list.size()) {//最后一次添加
					 dealnumberAmout  = dealDayMoney.intValue();
					 dealnumberAmoutSu = dealDayMoneySu.intValue();
					 dealnumber = dealDay;
					 dealnumberSu = dealDaySu;
				 dealDayList.add(dealDay);
				 dealDayMoneyList.add(dealDayMoney.intValue());//上一交易日 交易总额 或当前交易日交易成功总额
				 dealDayMoneySuList.add(dealDayMoneySu.intValue());//上一交易日交易成功总额   今天交易日交易成功总额
				 dealDaySuList.add(dealDaySu);
			 }
		}
		//上一日数据
		double1  = (Double)(dealDayList.get(dealDayList.size()-1)>0?dealDayList.get(dealDayList.size()-1):1);
		double2 =  (Double)(dealDaySuList.get(dealDaySuList.size()-1)>0?dealDaySuList.get(dealDaySuList.size()-1):1);
		integer  = dealDayMoneyList.get(dealDayMoneyList.size()-1)>0?dealDayMoneyList.get(dealDayMoneyList.size()-1):1;
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
		return "/manage/account/userDealShow";
	}
	@RequiresPermissions("/manage/account/appAccount")
	@RequestMapping("/appAccount")
	public String appAccount(Model m) {
		String userId = getUserId();
		 if(StrUtil.isBlank(userId))
			 throw  new OtherErrors("用户未登录，或登录数据错误");
		 List list = new ArrayList();
		 List<String> accountList = new ArrayList<String>();
		 User user = userService.findUserByUserId(userId);
		 List<UserAccount> UserAccountList = userService.findUserAccountByUserId(userId);
		 BigDecimal freezeBalance = new BigDecimal("0");;//总冻结
		 BigDecimal freezeBalanceT1 = new BigDecimal("0");;//总T1冻结
		 BigDecimal freezeBalanceD1 = new BigDecimal("0");;//总D1冻结
		 BigDecimal accountBalance = new BigDecimal("0");;//总现金
		 BigDecimal cashBalance = new BigDecimal("0");//总可提现
		 for(UserAccount acc : UserAccountList) {
			 accountList.add(acc.getAccountId());
		 }
		 if(CollUtil.isEmpty(accountList)) {
			 return "/manage/account/appAccount";
		 }
		 if(CollUtil.isNotEmpty(accountList)) {
			 List<AccountEntity> account = accountServiceImpl.findAccountEntityByAccountId(accountList);//账户集合
			 for(AccountEntity acc : account) {
				 cashBalance = cashBalance.add(acc.getCashBalance());
				 accountBalance = accountBalance.add(acc.getAccountBalance());
				 freezeBalanceD1 = freezeBalanceD1.add(acc.getFreezeD1());
				 freezeBalanceT1 = freezeBalanceT1.add(acc.getFreezeT1());
				 freezeBalance = freezeBalance.add(acc.getFreezeBalance());
			 }
		 }		
		double casePercent = 0.00;//总可提现比例
		double t1Percent = 0.00;//t1比例
		double d1Percent = 0.00;//d1比例
		double  freezePercent = 0.00;//总冻结比例
		int r=accountBalance.compareTo(BigDecimal.ZERO);
		if(0 != r) {
			BigDecimal cash = cashBalance;
			BigDecimal multiply = cashBalance.divide(accountBalance);
			casePercent  = multiply.doubleValue();
			t1Percent = freezeBalanceT1.divide(accountBalance).doubleValue();
			d1Percent = freezeBalanceD1.divide(accountBalance).doubleValue();
			BigDecimal freeze = freezeBalance;
			freezePercent = freezeBalance.divide(accountBalance).doubleValue();
			m.addAttribute("cash",cash);//可取现金总额
			m.addAttribute("freeze",freeze);//总冻结金额
			m.addAttribute("casePercent",(int)(casePercent*100));
			m.addAttribute("t1Percent",(int)(t1Percent*100));
			m.addAttribute("d1Percent",(int)(d1Percent*100));
			m.addAttribute("freezePercent", (int)(freezePercent*100));
		}else {
			m.addAttribute("cash",0);//可取现金总额
			m.addAttribute("freeze",0);//总冻结金额
			m.addAttribute("casePercent",0);
			m.addAttribute("t1Percent",0);
			m.addAttribute("d1Percent",0);
			m.addAttribute("freezePercent",0);
		}
		
		//以上是饼图和资金报告数据
		List<String> payNoList = new ArrayList();
		List<String> payNameList = new ArrayList();
		List<AccountFee> accountFeeList = accountServiceImpl.findAccountFeeByAccountList(accountList);
		for(AccountFee cf : accountFeeList)  {
			if(!payNoList.contains(cf.getChannelProduct()))  
				payNoList.add(cf.getChannelProduct());
		}
		DealOrderEntity order = new DealOrderEntity();//根据订单信息类分析各类该账户各类产品的成功率
		order.setAccountList(accountList);
		order.setPayTypeList(payNoList);
		List<DealOrderEntity> orderList = dealOrderServiceImpl.findPageDealOrderByDealOrder(order);//获取到了当前用户下所有产品类型的订单   包括成功和失败
		List<DealOrderEntity> orderListSu = new ArrayList();
		List<DealOrderEntity> orderListOh = new ArrayList();
		for(DealOrderEntity orderDeal : orderList) {
			if(Common.DEAL_STATUS_SU.equals(orderDeal.getOrderStatus()))
				orderListSu.add(orderDeal);
			if(!Common.DEAL_STATUS_SU.equals(orderDeal.getOrderStatus()))
				orderListOh.add(orderDeal);
		}
		Map<String,Double> map = new HashMap<String,Double>();
		double number = 0;
		for(DealOrderEntity orderSuBypayType: orderListSu) {//成功的订单
			for(String payType: payNoList) {//占比较少的
				if(orderSuBypayType.getRetain4().equalsIgnoreCase(payType)) {//根据产品类型划分
					number += 1;
					map.put(payType, number);//不同产品之间的成功次数
				}
			}
		}
		double orderSuPercent = (double)(orderListSu.size()) / (double)(orderList.size());//订单成功率 总成功率
		double orderOhPercent = (double)(orderListOh.size()) / (double)(orderList.size());//其他订单占比
		List<DataResult> dataPayType = new ArrayList<DataResult>();
		for(String key : map.keySet()) {
			DataResult result =  new DataResult();
			result.setPayType(key);
			result.setPayTypeNumber(map.get(key)/ orderList.size());//产品成功率
			dataPayType.add(result);
			result = null;
		}
		m.addAttribute("dataPayType", dataPayType);
		m.addAttribute("orderOhPercent", (int)orderOhPercent*100);
		return "/manage/account/appAccount";
	}
	
	
	
	
	
	
	
	private String getUserId() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Object attribute = session.getAttribute(Constant.User.USER_IN_SESSION());
		Map<String, Object> objectToMap = MapUtil.objectToMap(attribute);
		com.payProject.system.entity.User user = MapUtil.mapToBean(objectToMap,com.payProject.system.entity.User.class);
		String userId = (String)objectToMap.get(Constant.User.USER_ID());
		return userId;
	}
	@ResponseBody
	@RequestMapping("/orderRunList")
	public PageResult<RunOrder> myOrderRunList(RunOrder runOrder,String page,String limit){
		log.info("查询订单流水请求参数"+runOrder.toString());
		String userId = getUserId();
		log.info("当前查询流水人为："+userId);
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		PageResult<RunOrder> pageR = new PageResult<RunOrder>();
		 List<String> accountList = new ArrayList<String>();
		 List<UserAccount> UserAccountList = userService.findUserAccountByUserId(userId);
		 for(UserAccount acc : UserAccountList) {
			 accountList.add(acc.getAccountId());
		 }
		if(CollUtil.isEmpty(accountList)) {
			return pageR;
		}
		runOrder.setOrderAccountList(accountList);
		List<Integer> runTypeList = new ArrayList<Integer>();
		if(null == runOrder.getRunType()) {
			runTypeList.add(Common.RUN_TYPE_DEAL);
			runTypeList.add(Common.RUN_SYSTEM_ADD_MONEY);
			runTypeList.add(Common.RUN_SYSTEM_DELETE_MONEY);
			runTypeList.add(Common.RUN_WITHDRAWALS_PAY);
			runTypeList.add(Common.RUN_FREEZE);
			runTypeList.add(Common.RUN_UN_FREEZE);
			runTypeList.add(Common.RUN_DPAY_FREEZE);
			runOrder.setRunTypeList(runTypeList);
		}
		List<RunOrder> list = orderRunServiceImpl.findPageRunOrderByRunOrder(runOrder);
		PageInfo<RunOrder> pageInfo = new PageInfo<RunOrder>(list);
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("查询订单流水相应结果集"+pageR.toString());
		return pageR;
	}
	
	@ResponseBody
	@RequestMapping("/myUserDealShow")
	public List<HighchartsResult> myUserDealShow(){
		DealOrderEntity dealOrder =   new DealOrderEntity();
		List result = new ArrayList();
		String userId = getUserId();
		log.info("当前查询流水人为："+userId);
		List<String> accountList = new ArrayList<String>();
		List<UserAccount> UserAccountList = userService.findUserAccountByUserId(userId);
		for(UserAccount acc : UserAccountList) {
			accountList.add(acc.getAccountId());
		}
		dealOrder.setAccountList(accountList);
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




















}
