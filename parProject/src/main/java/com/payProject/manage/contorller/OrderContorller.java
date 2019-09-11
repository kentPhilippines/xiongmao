package com.payProject.manage.contorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.payProject.config.common.Constant;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.OtherErrors;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.ExceptionOrderEntity;
import com.payProject.manage.entity.RunOrder;
import com.payProject.manage.entity.WithdrawalsOrderEntity;
import com.payProject.manage.service.BankCardService;
import com.payProject.manage.service.DealOrderService;
import com.payProject.manage.service.OrderErrorService;
import com.payProject.manage.service.OrderRunService;
import com.payProject.manage.service.UrlNotfiy;
import com.payProject.manage.service.WithdrawalsOrderService;
import com.payProject.manage.util.SendUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@Controller
@RequestMapping("/manage/order")
public class OrderContorller implements UrlNotfiy{
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
	SendUtil sendUtil;
	@Value("${gateway.url}")
	private static String gatewayUrl;
	private final static String UPDATAORDER = gatewayUrl + "/notify/updataOrder";
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
	@RequestMapping("/merchantsShow")
	public String merchantsShow( ){
		return "/manage/merchants/merchantsShow";
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
	
	
	/**
	 * <p>将现有订单改为成功 并生成订单流水,向下游发送通知</p>
	 * @param runOrder
	 * @param page
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/notifyOrderSu")
	public JsonResult notifyOrderSu(DealOrderEntity dealOrder){
	//	throw new OtherErrors("功能暂未开放");
		DealOrderEntity deal = dealOrderServiceImpl.findDealOrderByOrderId(dealOrder.getOrderId());
		/**
		 * ######################
		 * 1,如果当前的订单为成功,则只发送回调通知
		 */
		if(Constant.Common.DEAL_STATUS_SU.equals(deal.getOrderStatus())) {
			 return  notifyOrderNo(deal);
		}
		/**
		 * #######################
		 * 2,如果当前的订单为非成功状态,修改用户订单状态为成功,并生成相应的流水,发送通知给用户
		 */
		String url = this.UPDATAORDER;
		Map<String, String> param = new HashMap<String, String>();
		param.put("orderId", deal.getOrderId());
		String createParam = SendUtil.createParam(param);
		String submitPost = SendUtil.submitPost(url, createParam);
		JSONObject parseObj = JSONUtil.parseObj(submitPost);
		JsonResult bean = JSONUtil.toBean(parseObj, JsonResult.class);
		return bean;
	}
	/**
	 * <p>讲现有订单改为失败 并生成订单流水,向下游发送通知</p>
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
	 */
	@ResponseBody
	@RequestMapping("/notifyOrderNo")
	public JsonResult notifyOrderNo(DealOrderEntity dealOrder){
		throw new OtherErrors("功能暂未开放");
	}
	
	
	
	
	
	
	
	
	
}
