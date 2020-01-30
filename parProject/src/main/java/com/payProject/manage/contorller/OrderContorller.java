package com.payProject.manage.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.ExceptionOrderEntity;
import com.payProject.manage.entity.RunOrder;
import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.entity.WithdrawalsOrderEntity;
import com.payProject.manage.service.DealOrderService;
import com.payProject.manage.service.OrderErrorService;
import com.payProject.manage.service.OrderRunService;
import com.payProject.manage.service.WithdrawalsOrderService;
import com.payProject.manage.util.AccountUtil;
import com.payProject.manage.util.SendUtil;
import com.payProject.system.service.UserService;
import com.payProject.system.util.MapUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
@Controller
@RequestMapping("/manage/order")
public class OrderContorller  {
	Logger log = LoggerFactory.getLogger(OrderContorller.class);
	@Autowired
	DealOrderService dealOrderServiceImpl;
	@Autowired
	WithdrawalsOrderService	withdrawalsOrderServiceImpl;
	@Autowired
	OrderErrorService orderErrorServiceImpl;
	@Autowired
	OrderRunService	orderRunServiceImpl;
	@Autowired
	UserService userService;
	@Autowired
	AccountUtil accountUtil;
	@Autowired
	SendUtil sendUtil;
	private final static String UPDATAORDER = "/notify/updataOrder";//补发通知,生成流水
	private final static String UPDATAORDERE = "/notify/notifyOrder";//现有状态发送通知
	@RequestMapping("/dealOrderShow")
	public String dealOrderShow( ){
		return "/manage/deal/dealOrderShow";
	}
	@ResponseBody
	@RequestMapping("/dealOrderList")
	public PageResult<DealOrderEntity> dealOrderList(DealOrderEntity dealOrder,String page,String limit){
		log.info("交易订单列表请求参数："+dealOrder.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<DealOrderEntity> list = dealOrderServiceImpl.findPageDealOrderByDealOrder(dealOrder);
		 PageInfo<DealOrderEntity> pageInfo = new PageInfo<DealOrderEntity>(list);
		 PageResult<DealOrderEntity> pageR = new PageResult<DealOrderEntity>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("交易订单列表响应结果集："+pageR.toString());
		return pageR;
	}
	@RequestMapping("/merchants")
	public String merchantsShow( ){
		return "/manage/merchants/merchantsList";
	}
	@ResponseBody
	@RequestMapping("/merchantsList")
	public PageResult<WithdrawalsOrderEntity> merchantsList(WithdrawalsOrderEntity withdrawalsOrder,String page,String limit){
		log.info("代付订单列表请求参数："+withdrawalsOrder.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<WithdrawalsOrderEntity> list = withdrawalsOrderServiceImpl.findPageWithdrawalsByWithdrawals(withdrawalsOrder);
		PageInfo<WithdrawalsOrderEntity> pageInfo = new PageInfo<WithdrawalsOrderEntity>(list);
		PageResult<WithdrawalsOrderEntity> pageR = new PageResult<WithdrawalsOrderEntity>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("代付订单列表相应结果集："+pageR.toString());
		return pageR;
	}
	@RequestMapping("/orderError")
	public String orderErrorShow( ){
		return "/manage/except/orderErrorShow";
	}
	@ResponseBody
	@RequestMapping("/orderErrorList")
	public PageResult<ExceptionOrderEntity> orderErrorList(ExceptionOrderEntity orderError,String page,String limit){
		log.info("异常订单列表请求参数："+orderError.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<ExceptionOrderEntity> list = orderErrorServiceImpl.findPageOrderErrorByOrderError(orderError);
		PageInfo<ExceptionOrderEntity> pageInfo = new PageInfo<ExceptionOrderEntity>(list);
		PageResult<ExceptionOrderEntity> pageR = new PageResult<ExceptionOrderEntity>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("异常订单列表相应结果集："+pageR.toString());
		return pageR;
	}
	@RequestMapping("/orderRunShow")
	public String orderRunShow( ){
		return "/manage/run/runOrderShow";
	}
	@ResponseBody
	@RequestMapping("/orderRunList")
	public PageResult<RunOrder> orderRunList(RunOrder runOrder,String page,String limit){
		log.info("查询订单流水请求参数"+runOrder.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<RunOrder> list = orderRunServiceImpl.findPageRunOrderByRunOrder(runOrder);
		PageInfo<RunOrder> pageInfo = new PageInfo<RunOrder>(list);
		PageResult<RunOrder> pageR = new PageResult<RunOrder>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("查询订单流水相应结果集"+pageR.toString());
		return pageR;
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
	
	/**
	 * <p>将现有订单改为成功 并生成订单流水,向下游发送通知</p>
	 * @param runOrder
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@PostMapping("/notifyOrderSu")
	public JsonResult notifyOrderSu(DealOrderEntity dealOrder,HttpServletRequest request){
	//	throw new OtherErrors("功能暂未开放");
		log.info("【当前订单置为 成功，订单号为："+dealOrder.getOrderId()+"，操作人为："+getUserId()+"，操作ip为："+getIpAddr(request)+"】");
		DealOrderEntity deal = dealOrderServiceImpl.findDealOrderByOrderId(dealOrder.getOrderId());
		/**
		 * ######################
		 * 1,如果当前的订单为成功,则只发送回调通知
		 */
		if(Constant.Common.DEAL_STATUS_SU.equals(deal.getOrderStatus())) {
			 try {
				return  notifyOrderNo(deal);
			} catch (Exception e) {
				throw new OtherErrors("发送通知参数组合异常，联系开发人员处理");
			}
		}
		/**
		 * #######################
		 * 2,如果当前的订单为非成功状态,修改用户订单状态为成功,并生成相应的流水,发送通知给用户
		 */
		String url = sendUtil.getGatewayUrl() + this.UPDATAORDER;
		log.info("修改订单请求路径："+url);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", deal.getOrderId());
		Map<String, Object> careteParam;
		try {
			careteParam = sendUtil.careteParam(param);
		} catch (Exception e) {
			throw new OtherErrors("发送通知参数组合异常，联系开发人员处理");
		}
		String result= HttpUtil.post(url, careteParam);
		System.out.println("返回结果集："+result);
		JSONObject parseObj = JSONUtil.parseObj(result);
		JsonResult bean = JSONUtil.toBean(parseObj, JsonResult.class);
		return bean;
	}
	/**
	 * <p>将现有订单改为失败,向下游发送通知</p>
	 * <li>如果当前订单为成功，则此功能不可用</li>
	 * @param runOrder
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/notifyOrderEr")
	public JsonResult notifyOrderEr(DealOrderEntity dealOrder){
		throw new OtherErrors("功能暂未开放");
	}
	/**
	 * <p>以现有状态订单向用户发送通知</p>
	 * @param runOrder
	 * @param page
	 * @param limit
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/notifyOrderNo")
	public JsonResult notifyOrderNo(DealOrderEntity dealOrder) throws Exception{
		DealOrderEntity deal = dealOrderServiceImpl.findDealOrderByOrderId(dealOrder.getOrderId());
		if(ObjectUtil.isNull(deal)) {
			return JsonResult.buildFailResult("服务器内未查询到有效订单");
		}
		String url = sendUtil.getGatewayUrl() + this.UPDATAORDERE;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", deal.getOrderId());
	    Map<String, Object> careteParam = sendUtil.careteParam(param);
		String result= HttpUtil.post(url, careteParam);
		JSONObject parseObj = JSONUtil.parseObj(result);
		JsonResult bean = JSONUtil.toBean(parseObj, JsonResult.class);
		return bean;
	}
	public  String getIpAddr(HttpServletRequest request) {
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
	/**
	 * <p>补单页面</p>
	 * @return
	 */
	@RequestMapping("/returnOrder")
	public String returnOrder( ){
		return "/manage/deal/returnOrder";
	}
	@GetMapping("/myDealOrder")
	public String myDealOrder( ){
		return "/manage/deal/myDealOrder";
	}
	/***
	 * <p>商户交易记录</p>
	 * @param dealOrder
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/MydealOrderList")
	public PageResult<DealOrderEntity> MydealOrderList(DealOrderEntity dealOrder,String page,String limit){
		log.info("交易订单列表请求参数："+dealOrder.toString());
		dealOrder.setAccountList(accountUtil.findAccountMyAccount(getUserId()));
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<DealOrderEntity> list = dealOrderServiceImpl.findPageDealOrderByDealOrder(dealOrder);
		PageInfo<DealOrderEntity> pageInfo = new PageInfo<DealOrderEntity>(list);
		PageResult<DealOrderEntity> pageR = new PageResult<DealOrderEntity>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		return pageR;
	}
}
