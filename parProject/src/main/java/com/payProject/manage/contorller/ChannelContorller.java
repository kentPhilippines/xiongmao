package com.payProject.manage.contorller;

import java.util.ArrayList;
import java.util.List;

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
import com.payProject.config.common.Constant.Common;
import com.payProject.config.exception.OtherErrors;
import com.payProject.config.exception.ParamException;
import com.payProject.config.common.AutocompleteResult;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.Channel;
import com.payProject.manage.entity.ChannelFee;
import com.payProject.manage.entity.DealOrderEntity;
import com.payProject.manage.entity.PayType;
import com.payProject.manage.service.ChannelService;
import com.payProject.system.entity.User;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/manage/channel")
public class ChannelContorller {
	Logger log = LoggerFactory.getLogger(ChannelContorller.class);
	
	@Autowired
	ChannelService channelServiceImpl;

	@RequestMapping("/channelShow")
	public String channelShow( ){
		return "/manage/channel/channelShow";
	}
	@RequestMapping("/channelFee")
	public String channelFee(Model m){
		List<PayType> payList  = channelServiceImpl.findPayTypeByAll();
		m.addAttribute("payList", payList);
		return "/manage/channel/channelFee";
	}
	@RequestMapping("/channelPayType")
	public String channelPayType( ){
		return "/manage/channel/channelPayType";
	} 
	@RequestMapping("/channelRunList")
	public String channelRunList( ){
		return "/manage/channel/channelRunList";
	} 
	
	
	
	@ResponseBody
	@RequestMapping("/channelList")
	public PageResult<Channel> channelList(Channel channel,String page,String limit){
		log.info("查询渠道请求参数"+channel.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<Channel> list = channelServiceImpl.findPageChannelByChannel(channel);
		 PageInfo<Channel> pageInfo = new PageInfo<Channel>(list);
		 PageResult<Channel> pageR = new PageResult<Channel>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	@ResponseBody
	@RequestMapping("/channelFeeList")
	public PageResult<ChannelFee> channelFeeList(ChannelFee channelFee,String page,String limit){
		log.info("查询渠道费率请求参数"+channelFee.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<ChannelFee> list = channelServiceImpl.findPageChannelFeeByChannelFee(channelFee);
		PageInfo<ChannelFee> pageInfo = new PageInfo<ChannelFee>(list);
		PageResult<ChannelFee> pageR = new PageResult<ChannelFee>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("查询渠道费率响应结果集"+pageR.toString());
		return pageR;
	}
	@ResponseBody
	@RequestMapping("/payTypeList")
	public PageResult<PayType> payTypeList(PayType payType,String page,String limit){
		log.info("查询产品类型请求参数"+payType.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<PayType> list = channelServiceImpl.findPagePayTypeByPayType(payType);
		PageInfo<PayType> pageInfo = new PageInfo<PayType>(list);
		PageResult<PayType> pageR = new PageResult<PayType>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("查询产品类型响应结果集"+pageR.toString());
		return pageR;
	}
	
	@RequestMapping("/channelAdd")
	public String channelAdd(Model m ){
		String createChannelNo = createChannelNo();
		m.addAttribute("channelNo", createChannelNo);
		return "/manage/channel/channelAdd";
	} 
	
	public String createChannelNo() {
		Channel channel = channelServiceImpl.findChannelFirst();
		String name = "";
		if(ObjectUtil.isNotNull(channel))
			name = channel.getChannelNo();
		if(StrUtil.isBlankIfStr(name))
			name = Common.CHANNEL_NAME() + Common.CHANNEL_NAME_SUF();
		String subSuf = StrUtil.subSuf(name,Common.CHANNEL_NAME().length());
		Integer valueOf = Integer.valueOf(subSuf);
		return Common.CHANNEL_NAME() + ++valueOf;
	}
	@ResponseBody
	@RequestMapping("/addChannel")
	@Transactional
	public JsonResult addChannel(Channel channel){
		log.info("增加渠道请求参数"+channel.toString());
		if(StrUtil.isBlank(channel.getChannelNo()))
			throw new ParamException("请求参数无效");
		List<Channel> channel1 = channelServiceImpl.findChannelByChannelId(channel.getChannelNo());
		if(channel1.size()>0)
			throw new ParamException("当前渠道编号重复");
		Boolean flag = channelServiceImpl.addChannel(channel);
		if(flag)
			return JsonResult.buildSuccessMessage("增加成功");
		return JsonResult.buildFailResult("增加失败");	
	} 
	@ResponseBody
	@RequestMapping("/channelDel")
	@Transactional
	public JsonResult channelDel(Channel channel){
		if( StrUtil.isBlank(channel.getChannelNo()))  
			throw new ParamException("请求参数无效");
		boolean flag  = channelServiceImpl.deleteChannelByNO(channel.getChannelNo());
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	
	
	@RequestMapping("/payTypeAdd")
	public String payTypeAdd(Model m ){
		String payTypeNo = createPayTypeNo();
		m.addAttribute("payTypeNo", payTypeNo);
		return "/manage/channel/channelPayFeeAdd";
	} 
	
	
	
	private String createPayTypeNo() {
		PayType payType = channelServiceImpl.findPayTypeFirst();
		String name = "";
		if(ObjectUtil.isNotNull(payType))
			name = payType.getPayTypeNo();
		if(StrUtil.isBlankIfStr(name))
			name = Common.PAYTYPE_NAME() + Common.PAYTYPE_NAME_SUF();
		String subSuf = StrUtil.subSuf(name,Common.PAYTYPE_NAME().length());
		Integer valueOf = Integer.valueOf(subSuf);
		return Common.PAYTYPE_NAME() + ++valueOf;
	}

	@ResponseBody
	@RequestMapping("/addChannelPayType")
	@Transactional
	public JsonResult addChannelPayType(PayType payType){
		log.info("增加支付方式请求参数"+payType.toString());
		if(StrUtil.isBlank(payType.getPayTypeNo()))
			throw new ParamException("请求参数无效");
		List<PayType> payType1 = channelServiceImpl.findPayTypeByNo(payType.getPayTypeNo());
		if(payType1.size()>0)
			throw new ParamException("当前渠道编号重复");
		Boolean flag = channelServiceImpl.addPayType(payType);
		if(flag)
			return JsonResult.buildSuccessMessage("增加成功");
		return JsonResult.buildFailResult("增加失败");	
	} 

	@ResponseBody
	@RequestMapping("/payTypeDel")
	@Transactional
	public JsonResult payTypeDel(PayType payType){
		if( StrUtil.isBlank(payType.getPayTypeNo()))  
			throw new ParamException("请求参数无效");
		boolean flag  = channelServiceImpl.deletePayTypeByNO(payType.getPayTypeNo());
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	@RequestMapping("/channeFeeAdd")
	public String channeFeeAdd(Model m ){
		List<Channel> channelList  = channelServiceImpl.findChannelByAll();
		List<PayType> payList  = channelServiceImpl.findPayTypeByAll();
		m.addAttribute("channelList", channelList);
		m.addAttribute("payList", payList);
		return "/manage/channel/channelFeeAdd";
	} 
	@ResponseBody
	@RequestMapping("/addChannelFee")
	@Transactional
	public JsonResult addChannelFee(ChannelFee channelFee){
		log.info("增加渠道费率请求参数"+channelFee.toString());
		if(StrUtil.isBlank(channelFee.getChannelNo()) || StrUtil.isBlank(channelFee.getPayType())||StrUtil.isBlank(channelFee.getFee()))
			throw new ParamException("请求参数无效");
		ChannelFee channelFee1 = channelServiceImpl.findChannelFee(channelFee.getChannelNo(),channelFee.getPayType());
		if(ObjectUtil.isNotNull(channelFee1))
			throw new OtherErrors("渠道费率重复增加，请修改渠道费率");
		Boolean flag = channelServiceImpl.addChannelFee(channelFee);
		log.info("增加渠道费率后的返回值："+ flag);
		if(flag)
			return JsonResult.buildSuccessMessage("增加成功");
		return JsonResult.buildFailResult("增加失败");	
	} 
	@ResponseBody
	@RequestMapping("/channeFeelDel")
	@Transactional
	public JsonResult channeFeelDel(ChannelFee channelFee){
		log.info("删除渠道费率请求参数"+channelFee.toString());
		if(null == channelFee.getId())  
			throw new ParamException("请求参数无效");
		boolean flag  = channelServiceImpl.deleteChannelByI(channelFee.getId());
		log.info("删除渠道费率后的返回值："+ flag);
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}else {
			throw new OtherErrors("删除失败");
		}
	}
	@RequestMapping("/channelEditShow")
	public String channelEditShow(Channel channel,Model m ){
		if(StrUtil.isBlank(channel.getChannelNo())  )
			throw new ParamException("无法确定唯一账户，数据传输有误");
		List<Channel> findChannelByChannelId = channelServiceImpl.findChannelByChannelId(channel.getChannelNo());
		Channel channelL = CollUtil.getFirst(findChannelByChannelId);
		m.addAttribute("channel", channelL);
		return "/manage/channel/channelEdit";
	} 
	
	@ResponseBody
	@RequestMapping("/channelEdit")
	@Transactional
	public JsonResult channelEdit(Channel channel,Model m ){
		if(StrUtil.isBlank(channel.getChannelNo())  )
			throw new ParamException("无法确定唯一账户，数据传输有误");
		List<Channel> findChannelByChannelId = channelServiceImpl.findChannelByChannelId(channel.getChannelNo());
		if(CollUtil.isEmpty(findChannelByChannelId)) {
			throw new OtherErrors("当前渠道不存在或数据错误");
		}
		Channel first = CollUtil.getFirst(findChannelByChannelId);
		first.setChannelName(StrUtil.isBlank(channel.getChannelName())?channel.getChannelName() :first.getChannelName());
		first.setChannelStautus(null != channel.getChannelStautus() ?channel.getChannelStautus() : first.getChannelStautus());
		first.setChannelType(null != channel.getChannelType() ? channel.getChannelType():first.getChannelType());
		first.setChannelAccount(StrUtil.isBlank(channel.getChannelAccount())?channel.getChannelAccount() :first.getChannelAccount());
		boolean flag = channelServiceImpl.updataChannel(first);
		if(flag) {
			return 	JsonResult.buildSuccessMessage("渠道修改成功");
		}
		return JsonResult.buildFailResult("渠道修改失败");
	}
	
	@RequestMapping("/channelFeeEditShow")
	public String channelFeeEditShow(ChannelFee channelFee,Model m ){
		if(null == channelFee.getId() )
			throw new ParamException("无法确定唯一账号费率");
		List<ChannelFee> findChannelByChannelId = channelServiceImpl.findChannelFeeById(channelFee.getId());
		ChannelFee channelL = CollUtil.getFirst(findChannelByChannelId);
		List<Channel> channelList  = channelServiceImpl.findChannelByAll();
		List<PayType> payList  = channelServiceImpl.findPayTypeByAll();
		m.addAttribute("channelList", channelList);
		m.addAttribute("payList", payList);		
		m.addAttribute("channelFee", channelL);
		return "/manage/channel/channelFeeEdit";
	}
	

	@ResponseBody
	@RequestMapping("/channelFeeEdit")
	@Transactional
	public JsonResult channelFeeEdit(ChannelFee channelFee,Model m ){
		if(null == channelFee.getId() )
			throw new ParamException("无法确定唯一账户，数据传输有误");
		List<ChannelFee> findChannelByChannelId = channelServiceImpl.findChannelFeeById(channelFee.getId());
		if(CollUtil.isEmpty(findChannelByChannelId)) {
			throw new OtherErrors("当前渠道不存在或数据错误");
		}
		ChannelFee first = CollUtil.getFirst(findChannelByChannelId);
		first.setChannelNo(StrUtil.isBlank(channelFee.getChannelNo())?channelFee.getChannelNo():first.getChannelNo());
		first.setChannelName(StrUtil.isBlank(channelFee.getChannelName())?channelFee.getChannelName():first.getChannelName());
		first.setPayType(StrUtil.isBlank(channelFee.getPayType())?channelFee.getPayType():first.getPayType());
		first.setFee(StrUtil.isBlank(channelFee.getFee())?channelFee.getFee():first.getFee());
		first.setStatus(null==channelFee.getStatus()?channelFee.getStatus():first.getStatus());
		boolean flag = channelServiceImpl.updataChannelFee(first);
		if(flag) {
			return 	JsonResult.buildSuccessMessage("渠道费率修改成功");
		}
		return JsonResult.buildFailResult("渠道费率修改失败");
	}
	/**
	 * <p>根据渠道查询所有的产婆类型</p>
	 * @param channelFee
	 * @param m
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findProductToChannel")
	public JsonResult findProductToChannel(ChannelFee channelFee,Model m){
		if(StrUtil.isBlank(channelFee.getChannelNo())) {
			throw new ParamException("请求参数为空");
		}
		List payt =new  ArrayList();
		List<PayType> payTypeList = channelServiceImpl.findProductToChannel(channelFee.getChannelNo());
		for( PayType entity : payTypeList ) {
			AutocompleteResult pay = new AutocompleteResult();
			pay.setName(entity.getPayTypeName());
			pay.setPinyin(entity.getPayTypeNo());
			payt.add(pay);
		}
		return JsonResult.buildSuccessResult("成功", payt);
	}
}
