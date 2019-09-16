package com.payProject.manage.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payProject.config.common.Constant;
import com.payProject.config.common.Constant.Common;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.OtherErrors;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.entity.WithdrawalsRecord;
import com.payProject.manage.service.AccountService;
import com.payProject.manage.service.WithdrawalsService;
import com.payProject.manage.util.SendUtil;
import com.payProject.system.entity.User;
import com.payProject.system.service.UserService;
import com.payProject.system.util.EncryptUtil;
import com.payProject.system.util.MapUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@Controller
@RequestMapping("/manage/merchants")
public class MerchantContorller {
	static Logger log = LoggerFactory.getLogger(MerchantContorller.class);
	@Autowired
	WithdrawalsService withdrawalsServiceImpl;
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountServiceImpl;
	@Autowired
	SendUtil sendUtil;
	private final static String DPAY = "/merchants/amount";//补发通知,生成流水
	/***
	 * <p>商户提现管理，申请提现</p>
	 * @return
	 */
	@RequestMapping("/merchantsShow")
	public String merchantsShow( ){
		return "/manage/merchants/merchantsShow";
	}
	/**
	 * <p>申请提现页面</p>
	 * @return
	 */
	@RequestMapping("/withdrawals")
	public String withdrawals(Model m ){
		Subject subject = SecurityUtils.getSubject();
		 Session session = subject.getSession();
		 Object attribute = session.getAttribute(Constant.User.USER_IN_SESSION());
		 Map<String, Object> objectToMap = MapUtil.objectToMap(attribute);
		 com.payProject.system.entity.User user = MapUtil.mapToBean(objectToMap,com.payProject.system.entity.User.class);
		 String userId = (String)objectToMap.get(Constant.User.USER_ID());
		 if(StrUtil.isBlank(userId))
			 throw  new OtherErrors("用户未登录，或登录数据错误");
		 List list = new ArrayList();
		 List<String> accountList = new ArrayList<String>();
		 m.addAttribute("user", user);
		 List<UserAccount> UserAccountList = userService.findUserAccountByUserId(user.getUserId());
		 m.addAttribute("UserAccountList", UserAccountList);
		 for(UserAccount acc : UserAccountList) {
			 accountList.add(acc.getAccountId());
		 }
		 if(CollUtil.isNotEmpty(accountList)) {
			 List<AccountEntity> account = accountServiceImpl.findBankCardByAccountId(accountList);
			 for(AccountEntity entity : account) {
				 if(StrUtil.isNotBlank(entity.getBankCard())) {
					 list.add(entity.getBankCard());
				 }
			 }
		 }
		 m.addAttribute("backCard", list);
		return "/manage/merchants/withdrawals";
	}
	
	/**
	 * <p>申请提现记录表</p>
	 * @param withdrawals
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/withdrawalsRecordList")
	public PageResult<WithdrawalsRecord> withdrawalsRecordList(WithdrawalsRecord withdrawals,String page,String limit){
		log.info("申请提现列表请求参数："+withdrawals.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		Subject subject = SecurityUtils.getSubject();
		 Session session = subject.getSession();
		 Object attribute = session.getAttribute(Constant.User.USER_IN_SESSION());
		 Map<String, Object> objectToMap = MapUtil.objectToMap(attribute);
		 com.payProject.system.entity.User user = MapUtil.mapToBean(objectToMap,com.payProject.system.entity.User.class);
		 String userId = (String)objectToMap.get(Constant.User.USER_ID());
		 if(StrUtil.isBlank(userId))
			 throw  new OtherErrors("用户未登录，或登录数据错误");
		 List<UserAccount> UserAccountList = userService.findUserAccountByUserId(user.getUserId());
		 List<String> accountList  = new ArrayList();
		 for(UserAccount uerAccount:UserAccountList) {
			 accountList.add(uerAccount.getAccountId());
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
	@RequestMapping("/amount")
	public JsonResult amount(WithdrawalsRecord withdrawals){
		return null;
	}
	
	
	/**
	 * <p>提现处理类,接收到提现申请</p>
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/amount")	
	@ResponseBody
	@Transactional
	public JsonResult amount(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String accountId = request.getParameter("accountId");
		String backCard = request.getParameter("backCard");
		String amount = request.getParameter("amount");
		String payPassword = request.getParameter("payPassword");
		if(StrUtil.isBlank(userId)||StrUtil.isBlank(userName)||StrUtil.isBlank(accountId)||StrUtil.isBlank(backCard)||StrUtil.isBlank(amount)||StrUtil.isBlank(payPassword)) {
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
		AccountEntity account = accountServiceImpl.findAccountByAccountId(accountId);
		if(ObjectUtil.isNull(account)) {
			throw new ParamException("提现商户号不存在");
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
		String accountId = request.getParameter("accountId");
		String backCard = request.getParameter("backCard");
		String amount = request.getParameter("amount");
		String payPassword = request.getParameter("payPassword");
		param.put("userId",userId);
		param.put("userName",userName);
		param.put("accountId",accountId);
		param.put("backCard",backCard);
		param.put("amount",amount);
		param.put("ipAddr",ipAddr);
		Map<String, Object> careteParam = sendUtil.careteParam(param);
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
}
