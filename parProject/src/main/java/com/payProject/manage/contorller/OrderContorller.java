package com.payProject.manage.contorller;

import java.util.List;

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
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.WithdrawalsOrderEntity;
import com.payProject.manage.service.BankCardService;
import com.payProject.manage.service.DealOrderService;
import com.payProject.manage.service.WithdrawalsOrderService;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/manage/order")
public class OrderContorller {
	Logger log = LoggerFactory.getLogger(OrderContorller.class);
	
	@Autowired
	DealOrderService dealOrderServiceImpl;
	@Autowired
	WithdrawalsOrderService	withdrawalsOrderServiceImpl;
	
	
	@RequestMapping("/dealOrderShow")
	public String dealOrderShow( ){
		return "/manage/deal/dealOrderShow";
	}
	@ResponseBody
	@RequestMapping("/dealOrderList")
	public PageResult<DealOrderEntity> dealOrderList(DealOrderEntity dealOrder,String page,String limit){
		log.info("查询银行卡请求参数"+dealOrder.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<DealOrderEntity> list = dealOrderServiceImpl.findPageDealOrderByDealOrder(dealOrder);
		 PageInfo<DealOrderEntity> pageInfo = new PageInfo<DealOrderEntity>(list);
		 PageResult<DealOrderEntity> pageR = new PageResult<DealOrderEntity>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	@RequestMapping("/merchantsShow")
	public String merchantsShow( ){
		return "/manage/merchants/merchantsShow";
	}
	@ResponseBody
	@RequestMapping("/merchantsList")
	public PageResult<WithdrawalsOrderEntity> merchantsList(WithdrawalsOrderEntity withdrawalsOrder,String page,String limit){
		log.info("查询银行卡请求参数"+withdrawalsOrder.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<WithdrawalsOrderEntity> list = withdrawalsOrderServiceImpl.findPageWithdrawalsByWithdrawals(withdrawalsOrder);
		PageInfo<WithdrawalsOrderEntity> pageInfo = new PageInfo<WithdrawalsOrderEntity>(list);
		PageResult<WithdrawalsOrderEntity> pageR = new PageResult<WithdrawalsOrderEntity>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	
	
	
	
	
	


}
