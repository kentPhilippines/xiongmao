package com.payProject.manage.contorller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payProject.config.common.Constant;
import com.payProject.config.common.HighchartsResult;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.common.Constant.Common;
import com.payProject.config.exception.OtherErrors;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.AccountFee;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.entity.WithdrawalsRecord;
import com.payProject.manage.service.AccountService;
import com.payProject.manage.service.DealOrderService;
import com.payProject.manage.service.OrderRunService;
import com.payProject.manage.service.WithdrawalsService;
import com.payProject.manage.util.SendUtil;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserRole;
import com.payProject.system.service.UserRoleService;
import com.payProject.system.service.UserService;
import com.payProject.system.util.EncryptUtil;
import com.payProject.system.util.MapUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@Controller
@RequestMapping("/manage/agent")
public class AgentContorller {
	static Logger log = LoggerFactory.getLogger(ChannelContorller.class);
	@Autowired
	AccountService accountServiceImpl;
	@Autowired
	UserService userService;
	@Autowired
	DealOrderService dealOrderServiceImpl;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	SendUtil sendUtil;
	@Autowired
	OrderRunService	orderRunServiceImpl;
	@Autowired
	WithdrawalsService withdrawalsServiceImpl;
	private final static String DPAY = "/merchants/agentAmount";//补发通知,生成流水
	/**
	 * <p>代理商子账户交易情况</p>
	 * <p>一个月交易数据</p>
	 * @return
	 */
	@RequiresPermissions("/manage/agent/myAppDealShow")
	@RequestMapping("/myAppDealShow")
	public String myAppDealShow(Model m){
		String userId = getUserId();
		log.info("当前查询交易流水的人为："+userId);
		List<String> accountList = new ArrayList<String>();
		List<User> use  = userService.findUserByAgent(userId);//获取所有的代理子账户
		for(User user: use) {
			accountList.add(user.getUserId());
		};
		if(CollUtil.isEmpty(accountList)) {
			m.addAttribute("sum", new JSONArray(new ArrayList<>()));
			m.addAttribute("dealDayList", new JSONArray(new ArrayList<>()));
			m.addAttribute("dealDaySuList",  new JSONArray(new ArrayList<>()));
			m.addAttribute("dealDayMoneyList", new JSONArray(new ArrayList<>())  );
			m.addAttribute("dealDayMoneySuList", new JSONArray(new ArrayList<>()) );
			m.addAttribute("timeList",new JSONArray(new ArrayList<>()) );
			return "/manage/agent/myAppDealList";
		}
		List<UserAccount> UserAccountList = userService.findUserAccountByUserId(accountList);
		if(CollUtil.isNotEmpty(UserAccountList)) {
			for(UserAccount acc : UserAccountList) {
				accountList.add(acc.getAccountId());
			}
		};
		DealOrderEntity dealOrder =   new DealOrderEntity();
		dealOrder.setAccountList(accountList);
		if(CollUtil.isEmpty(accountList)) {
			m.addAttribute("sum", new JSONArray(new ArrayList<>()));
			m.addAttribute("dealDayList", new JSONArray(new ArrayList<>()));
			m.addAttribute("dealDaySuList",  new JSONArray(new ArrayList<>()));
			m.addAttribute("dealDayMoneyList", new JSONArray(new ArrayList<>())  );
			m.addAttribute("dealDayMoneySuList", new JSONArray(new ArrayList<>()) );
			m.addAttribute("timeList",new JSONArray(new ArrayList<>()) );
			return "/manage/agent/myAppDealList";
		}
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
		double dealnumberAmout = 0;
		double dealnumberAmoutSu = 0;
		double dealnumber = 0;
		double dealnumberSu = 0;
		Double double1 = 1.0;
		Double double2 = 1.0;
		int integer = 1;
		int integer2 = 1;
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
		return "/manage/agent/myAppDealList";
	}
	
	@ResponseBody
	@RequiresPermissions("/manage/agent/myAppDealList")
	@RequestMapping("/myAppDealList")
	public PageResult<AccountEntity> myAppDealList(){
		
		return null;
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
	@RequestMapping("/myUserDealShowForAgent")
	public List<HighchartsResult> myUserDealShowForAgent(){
		DealOrderEntity dealOrder =   new DealOrderEntity();
		List result = new ArrayList();
		String userId = getUserId();
		log.info("当前查询流水人为："+userId);
		List<String> accountList = new ArrayList<String>();
		List<User> use  = userService.findUserByAgent(userId);//获取所有的代理子账户
		for(User user: use) {
			accountList.add(user.getUserId());
		};
		if(CollUtil.isEmpty(accountList)) {
			log.info("交易记录为null"+userId);
			return result;
		}
		List<UserAccount> UserAccountList = userService.findUserAccountByUserId(accountList);
		if(CollUtil.isNotEmpty(UserAccountList)) {
			for(UserAccount acc : UserAccountList) {
				accountList.add(acc.getAccountId());
			}
		};
		dealOrder.setAccountList(accountList);
		if(CollUtil.isEmpty(accountList)) {
			log.info("交易记录为null"+userId);
			return result;
		}
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
	
	@RequestMapping("/myAppList")
	public String myAppList(){
		return "/manage/agent/appAccountManage";
	}
	/**
	 * <p>代理商商户管理</p>
	 * @param user
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("appAccountManageList")
	public PageResult<User> userList(User user,String page,String limit){
		log.info("【外部商户管理】");
		user.setUserType(Common.USER_WAI);
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		user.setRetain4(getUserId());
		List<User> list = userService.findPageUserByUser(user);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		PageResult<User> pageR = new PageResult<User>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
		return pageR;
	}
	
	@RequestMapping("/userManageAdd")
	public String userAdd( ){
		return "/manage/agent/appAccountManageAdd";
	}
	@RequestMapping("/appAccountEditShow")
	public String appAccountEditShow(User user ,Model m ){
		if( StrUtil.isBlank(user.getUserId())) {
			throw new ParamException("请求参数无效");
		}
		User findUserByUserId = userService.findUserByUserId(user.getUserId());
		m.addAttribute("user", findUserByUserId);
		return "/manage/account/userManage/appAccountEdit";
	}
	@ResponseBody
	@PostMapping("/appAccountManageAdd")
	@Transactional
	public JsonResult appAccountManageAdd(User user){
		log.info("增加用户请求参数"+user.toString());
		User users = userService.findUserByUserId(user.getUserId());
		if(ObjectUtil.isNull(users)){
		Map<String, String> map = EncryptUtil.encryptPassword(user.getUserId(), user.getUserPassword());
		//systemUser(user);//这里使用只为获取用户支付密码
		Map<String, String> encryptPassword = EncryptUtil.encryptPassword(user.getPayPassword());
		user.setPayPassword(encryptPassword.get(Constant.Common.PAYPASSWORD));
		user.setUserPassword(map.get(Constant.Common.PASSWORD));
		user.setUserSalt(map.get(Constant.Common.SALT));
		user.setUserType(Common.USER_WAI);//外部商户号
		user.setRetain2("2");//商户账户号
		user.setRetain4(getUserId());
		Boolean flag = userService.addUser(user);
		/**
		 * <p>目前这里要写死</p>
		 */
		boolean flag1 = addRole(user);
		if(!flag1) {
			throw new ParamException("角色权限赋予失败");
		}
		if(flag )
			return JsonResult.buildSuccessMessage("增加成功");
		throw new ParamException("账户增加失败");
	}
	return  JsonResult.buildFailResult("当前用户名已存在");
	}
	/**
	 * <><>
	 * @param user
	 * @param i
	 * @return
	 */
	private boolean addRole(User user) {
		//1码商2商户3运营4财务5客服6代理商
		String retain2 = user.getRetain2();
		Integer role = Integer.valueOf(retain2);
		Integer i = 0;
		if(role.equals(1)) {
			i  = 5;
		}else if(role.equals(2)) {
			i  = 4;
		}else if(role.equals(6)) {
			i  = 6;
		}		
		if(!i.equals(0)) {
			UserRole entity = new UserRole();
			entity.setRole(i);
			entity.setUserId(user.getId().toString());
			boolean flag = userRoleService.addroleId(entity);
			return flag;
		}
		return false;
	}
	@RequestMapping("/appAccountFee")
	public String appAccountFee( ){
		return "/manage/agent/appAccountFee";
	}
	
	
	@ResponseBody
	@RequiresPermissions("/manage/agent/accountFeeList")
	@RequestMapping("/accountFeeList")
	public PageResult<AccountFee> accountFeeList(AccountFee accountFee,String page,String limit){
		log.info("查询商户费率请求参数"+accountFee.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 String userId = getUserId();
			log.info("当前查询流水人为："+userId);
			List<String> userList = new ArrayList<String>();
			List<String> accountList = new ArrayList<String>();
			List<User> use  = userService.findUserByAgent(userId);//获取所有的代理子账户
			for(User user: use) {
				userList.add(user.getUserId());
			};
			if(CollUtil.isEmpty(userList)) {
				 List<AccountFee> list = new ArrayList<AccountFee>();
				 PageInfo<AccountFee> pageInfo = new PageInfo<AccountFee>(list);
				 PageResult<AccountFee> pageR = new PageResult<AccountFee>();
					pageR.setData(pageInfo.getList());
					pageR.setCode("0");
					pageR.setCount(String.valueOf(pageInfo.getTotal()));
					log.info("商户费率列表响应结果集"+pageR.toString());
				return pageR;
			}
			List<UserAccount> UserAccountList = userService.findUserAccountByUserId(userList);
			if(CollUtil.isNotEmpty(UserAccountList)) {
				for(UserAccount acc : UserAccountList) {
					accountList.add(acc.getAccountId());
				}
			};
			
		 if(CollUtil.isEmpty(accountList)) {
			 List<AccountFee> list = new ArrayList<AccountFee>();
			 PageInfo<AccountFee> pageInfo = new PageInfo<AccountFee>(list);
			 PageResult<AccountFee> pageR = new PageResult<AccountFee>();
				pageR.setData(pageInfo.getList());
				pageR.setCode("0");
				pageR.setCount(String.valueOf(pageInfo.getTotal()));
				log.info("商户费率列表响应结果集"+pageR.toString());
			return pageR;
		 }
		 accountFee.setAccountIdList(accountList);
		 List<AccountFee> list = accountServiceImpl.findPageAccountFeeByAccountFee(accountFee);
		 PageInfo<AccountFee> pageInfo = new PageInfo<AccountFee>(list);
		 PageResult<AccountFee> pageR = new PageResult<AccountFee>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("商户费率列表响应结果集"+pageR.toString());
		return pageR;
	}
	
	@RequiresPermissions("/manage/agent/appAccountList")
	@RequestMapping("/appAccountList")
	public String accountlShow( ){
		
		
		return "/manage/agent/appAccountList";
	}
	@ResponseBody
	@RequiresPermissions("/manage/agent/accountList")
	@RequestMapping("/accountList")
	public PageResult<AccountEntity> accountList(AccountEntity account,String page,String limit){
		log.info("查询商户请求参数"+account.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 String userId = getUserId();
			log.info("当前查询流水人为："+userId);
			List<String> accountList = new ArrayList<String>();
			List<String> userList = new ArrayList<String>();
			List<User> use  = userService.findUserByAgent(userId);//获取所有的代理子账户
			for(User user: use) {
				userList.add(user.getUserId());
			};
			 if(CollUtil.isEmpty(userList)) {
				 List<AccountEntity> list = new ArrayList<AccountEntity>();
				 PageInfo<AccountEntity> pageInfo = new PageInfo<AccountEntity>(list);
				 PageResult<AccountEntity> pageR = new PageResult<AccountEntity>();
					pageR.setData(pageInfo.getList());
					pageR.setCode("0");
					pageR.setCount(String.valueOf(pageInfo.getTotal()));
					log.info("商户列表响应结果集"+pageR.toString());
				return pageR;
			 }
			List<UserAccount> UserAccountList = userService.findUserAccountByUserId(userList);
			if(CollUtil.isNotEmpty(UserAccountList)) {
				for(UserAccount acc : UserAccountList) {
					accountList.add(acc.getAccountId());
				}
			};
		 if(CollUtil.isEmpty(accountList)) {
			 List<AccountEntity> list = new ArrayList<AccountEntity>();
			 PageInfo<AccountEntity> pageInfo = new PageInfo<AccountEntity>(list);
			 PageResult<AccountEntity> pageR = new PageResult<AccountEntity>();
				pageR.setData(pageInfo.getList());
				pageR.setCode("0");
				pageR.setCount(String.valueOf(pageInfo.getTotal()));
				log.info("商户列表响应结果集"+pageR.toString());
			return pageR;
		 }
		 account.setAccountIdList(accountList);
		 List<AccountEntity> list = accountServiceImpl.findPageAccountByAccount(account);
		 PageInfo<AccountEntity> pageInfo = new PageInfo<AccountEntity>(list);
		 PageResult<AccountEntity> pageR = new PageResult<AccountEntity>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("商户列表响应结果集"+pageR.toString());
		return pageR;
	}
	@RequiresPermissions("/manage/agent/merchantsShow")
	@RequestMapping("/merchantsShow")
	public String merchantsShow(Model m  ){
		String userId = getUserId();
		 if(StrUtil.isBlank(userId))
			 throw  new OtherErrors("用户未登录，或登录数据错误");
		 List list = new ArrayList();
		 List<String> accountList = new ArrayList<String>();
		 User user = userService.findUserByUserId(userId);
		 if(StrUtil.isBlank(user.getRetain3()))
			 user.setRetain3("0");
		 m.addAttribute("user", user);
		return "/manage/agent/appMerchantsShow";
	}
	/**
	 * <p>申请提现页面</p>
	 * @return
	 */
	@RequestMapping("/withdrawals")
	public String withdrawals(Model m ){
		String userId = getUserId();
		 if(StrUtil.isBlank(userId))
			 throw  new OtherErrors("用户未登录，或登录数据错误");
		 List list = new ArrayList();
		 List<String> accountList = new ArrayList<String>();
		 User user = userService.findUserByUserId(userId);
		 if(StrUtil.isBlank(user.getRetain3()))
			 user.setRetain3("0");
		 m.addAttribute("user", user);
		return "/manage/agent/appWithdrawals";
	}
	
	@PostMapping("/amount")	
	@ResponseBody
	@Transactional
	public JsonResult amount(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String backCard = request.getParameter("backCard");
		String amount = request.getParameter("amount");
		String payPassword = request.getParameter("payPassword");
		if(StrUtil.isBlank(userId)||StrUtil.isBlank(userName) ||StrUtil.isBlank(backCard)||StrUtil.isBlank(amount)||StrUtil.isBlank(payPassword)) {
			throw new ParamException("必传参数为空");
		}
		User user = userService.findUserByUserId(userId);
		if(ObjectUtil.isNull(user)) {
			throw new ParamException("提现账户不存在");
		}
		String payPassword2 = user.getPayPassword();
		Map<String, String> encryptPassword = EncryptUtil.encryptPassword(payPassword);
		String pay = encryptPassword.get(Common.PAYPASSWORD);
		if(!pay.equals(payPassword2)) {
			throw new ParamException("提现密码错误");
		}
		try {
			JsonResult careteDpay = careteDpay(request);
			return	careteDpay;
		} catch (Exception e) {
			throw new OtherErrors("发送通知参数组合异常，请联系开发人员处理");
		}
		/**
		 * ##########################################
		 * 提现逻辑
		 * 1,判断传入参数是否正确    若提现参数不对生成  异常订单表和提现记录表  审核人为  系统审核    关联订单为  异常订单
		 * 2,写入全局订单表
		 * 3,写入代付订单表
		 * 4,写入提现记录表
		 * 5,等待财务审核
		 * 6,审核完完毕
		 * 7,下发资金
		 */
	}
	/**
	 * <p>生成提现记录数据</p>
	 * <p>远程调用</p>
	 * @throws Exception 
	 */
	private JsonResult careteDpay(HttpServletRequest request) throws Exception {
		String ipAddr = getIpAddr(request);//真实IP
		Map<String, Object> param = new HashMap<String, Object>();
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String backCard = request.getParameter("backCard");
		String amount = request.getParameter("amount");
		String payPassword = request.getParameter("payPassword");
		param.put("userId",userId);
		param.put("userName",userName);
		param.put("backCard",backCard);
		param.put("amount",amount);
		param.put("ipAddr",ipAddr);
		Map<String, Object> careteParam = sendUtil.careteParam(param);
		log.info(sendUtil.getGatewayUrl()+this.DPAY);
		String result= HttpUtil.post(sendUtil.getGatewayUrl()+this.DPAY, careteParam);
		JSONObject parseObj = JSONUtil.parseObj(result);
		JsonResult bean = JSONUtil.toBean(parseObj, JsonResult.class);
		return bean;
	}
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for"); 
        System.out.println("x-forwarded-for ip: " + ip);
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if( ip.indexOf(",")!=-1 ){
                ip = ip.split(",")[0];
            }
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
            System.out.println("Proxy-Client-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
            System.out.println("WL-Proxy-Client-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
            System.out.println("HTTP_CLIENT_IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("X-Real-IP");  
            System.out.println("X-Real-IP ip: " + ip);
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
            System.out.println("getRemoteAddr ip: " + ip);
        } 
        log.info("登录账户正在发起提现请求，获取客户端ip: " + ip);
        return ip;  
    }
	@ResponseBody
	@RequestMapping("/withdrawalsRecordList")
	public PageResult<WithdrawalsRecord> withdrawalsRecordList(WithdrawalsRecord withdrawals,String page,String limit){
		log.info("申请提现列表请求参数："+withdrawals.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		String userId = getUserId();
		log.info("当前查询流水人为："+userId);
		List<String> accountList = new ArrayList<String>();
		List<User> use  = userService.findUserByAgent(userId);//获取所有的代理子账户
		for(User user: use) {
			accountList.add(user.getUserId());
		};
		 if(CollUtil.isEmpty(accountList)) {
			 List list = new ArrayList();
			  PageInfo<WithdrawalsRecord> pageInfo = new PageInfo<WithdrawalsRecord>(list);
				PageResult<WithdrawalsRecord> pageR = new PageResult<WithdrawalsRecord>();
				pageR.setData(pageInfo.getList());
				pageR.setCode("0");
				pageR.setCount(String.valueOf(pageInfo.getTotal()));
				log.info("申请提现列表相应结果集："+pageR.toString());
				return pageR;
		 }
		List<UserAccount> UserAccountList = userService.findUserAccountByUserId(accountList);
		if(CollUtil.isNotEmpty(UserAccountList)) {
			for(UserAccount acc : UserAccountList) {
				accountList.add(acc.getAccountId());
			}
		};
		 if(CollUtil.isEmpty(accountList)) {
			 List list = new ArrayList();
			 PageInfo<WithdrawalsRecord> pageInfo = new PageInfo<WithdrawalsRecord>(list);
				PageResult<WithdrawalsRecord> pageR = new PageResult<WithdrawalsRecord>();
				pageR.setData(pageInfo.getList());
				pageR.setCode("0");
				pageR.setCount(String.valueOf(pageInfo.getTotal()));
				log.info("申请提现列表相应结果集："+pageR.toString());
				return pageR;
		 }
		withdrawals.setAccountList(accountList);
		List<WithdrawalsRecord> list = withdrawalsServiceImpl.findPageWithdrawalsByWithdrawals(withdrawals);
		PageInfo<WithdrawalsRecord> pageInfo = new PageInfo<WithdrawalsRecord>(list);
		PageResult<WithdrawalsRecord> pageR = new PageResult<WithdrawalsRecord>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("申请提现列表相应结果集："+pageR.toString());
		return pageR;
	}
	
}