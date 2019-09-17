package com.payProject.manage.contorller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
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
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.BankCardRunEntity;
import com.payProject.manage.service.BankCardService;
import com.payProject.system.util.MapUtil;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/manage/bankCard")
public class BankCardContorller {
	Logger log = LoggerFactory.getLogger(BankCardContorller.class);
	@Autowired
	BankCardService bankCardService;
	@ResponseBody
	@PostMapping("/bankCardInsert")
	@Transactional
	public JsonResult bankCardInsert(BankCardEntity bankCard){
		log.info("增加银行卡请求参数"+bankCard.toString());
		BankCardEntity bankCards = bankCardService.findBankCardByBankCard(bankCard.getBankCard());
		if(ObjectUtil.isNull(bankCards)){
			Boolean flag = bankCardService.addBankCard(bankCard);
			if(flag)
				return JsonResult.buildSuccessMessage("增加成功");
			return JsonResult.buildFailResult("增加失败");	
		}
		return  JsonResult.buildFailResult("当前银行卡已存在");
	}
	
	@RequestMapping("/bankManage")
	public String bankManage( ){
		return "/manage/bankCard/bankManage";
	}
	
	/**
	 * <p>银行卡展示</p>
	 * @return 2019-07-31
	 */
	@ResponseBody
	@RequestMapping("/bankCardList")
	public PageResult<BankCardEntity> bankCardList(BankCardEntity bankCard,String page,String limit){
		log.info("查询银行卡请求参数"+bankCard.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<BankCardEntity> list = bankCardService.findPageBankCardByBankCard(bankCard);
		 PageInfo<BankCardEntity> pageInfo = new PageInfo<BankCardEntity>(list);
		 PageResult<BankCardEntity> pageR = new PageResult<BankCardEntity>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	@RequestMapping("/bankCardAdd")
	public String bankCardAdd( ){
		return "/manage/bankCard/bankCardManageAdd";
	}
	@RequestMapping("/myBankCardAdd")
	public String myBankCardAdd(Model m){
		String userId = getUserId();
		m.addAttribute("userId", userId);
		return "/manage/bankCard/myBankCardManageAdd";
	}
	@ResponseBody
	@RequestMapping("/caeateBankId")
	public JsonResult caeateBankId( ){
		String bankId = createBankId();
		return JsonResult.buildSuccessResult(bankId);
	}
	/**
	 * <p>生成银行卡(本地)编号</p>
	 * @return
	 */
	private String createBankId() {
		BankCardEntity bankCard = bankCardService.findBankCardByBankCardId();
		if(ObjectUtil.isNull(bankCard))
			return "10000" ;
		Integer bankId = bankCard.getBankId();
		return ++bankId+""; 
	}
	
	
	@ResponseBody
	@RequestMapping("/myBankCardDel")
	@Transactional
	public JsonResult myBankCardDel(BankCardEntity bankCard ){
		if( StrUtil.isBlank(bankCard.getBankCard())) {
			throw new ParamException("请求参数无效");
		}
		bankCard.setStatus(0);
		bankCard.setRetain2("1");
		boolean flag  = bankCardService.updataBankCard(bankCard);
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	
	@ResponseBody
	@RequestMapping("/bankCardDel")
	@Transactional
	public JsonResult bankCardDel(BankCardEntity bankCard ){
		if( StrUtil.isBlank(bankCard.getBankCard())) {
			throw new ParamException("请求参数无效");
		}
		boolean flag  = bankCardService.deleteBankCardByBankCardNo(bankCard.getBankCard());
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	
	@RequestMapping("/bankCardEditShow")
	public String bankCardEditShow(BankCardEntity bankCard ,Model m){
	if( StrUtil.isBlank(bankCard.getBankCard())) {
		throw new ParamException("请求参数无效");
	}
	BankCardEntity bankCard1 = bankCardService.findBankCardByBankCard(bankCard.getBankCard());
	m.addAttribute("bankCard", bankCard1);
		return "/manage/bankCard/bankCardEdit";
	}
	@ResponseBody
	@RequestMapping("/bankCardEdit")
	public JsonResult bankCardEdit(BankCardEntity bankCard){
		if( StrUtil.isBlank(bankCard.getBankCard())) {
			throw new ParamException("请求参数无效");
		}
		bankCard.setCreateTime(null);
		boolean flag  = bankCardService.UpdateBankCardByBankCardNo(bankCard);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
	}
	@RequestMapping("/myBankCard")
	public String myBankCard( ){
		return "/manage/bankCard/myBankCard";
	}

	/**
	 * <p>我的银行卡展示</p>
	 * @return 2019-07-31
	 */
	@ResponseBody
	@RequestMapping("/myBankCardList")
	public PageResult<BankCardEntity> myBankCardList(BankCardEntity bankCard,String page,String limit){
		log.info("查询银行卡请求参数"+bankCard.toString());
		String userId = getUserId();
		log.info("当前银行卡负责人："+userId);
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 bankCard.setLiabilities(userId);
		 bankCard.setRetain2("2");//逻辑可用
		 List<BankCardEntity> list = bankCardService.findPageBankCardByBankCard(bankCard);
		 PageInfo<BankCardEntity> pageInfo = new PageInfo<BankCardEntity>(list);
		 PageResult<BankCardEntity> pageR = new PageResult<BankCardEntity>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	@RequestMapping("/myBankCardEditShow")
	public String myBankCardEditShow(BankCardEntity bankCard ,Model m){
	if( StrUtil.isBlank(bankCard.getBankCard())) {
		throw new ParamException("请求参数无效");
	}
	BankCardEntity bankCard1 = bankCardService.findBankCardByBankCard(bankCard.getBankCard());
	m.addAttribute("bankCard", bankCard1);
		return "/manage/bankCard/myBankCardEdit";
	}
	@ResponseBody
	@RequestMapping("/myBankCardEdit")
	public JsonResult myBankCardEdit(BankCardEntity bankCard){
		if( StrUtil.isBlank(bankCard.getBankCard())) {
			throw new ParamException("请求参数无效");
		}
		bankCard.setCreateTime(null);
		boolean flag  = bankCardService.UpdateBankCardByBankCardNo(bankCard);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
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
	@RequestMapping("/dealBankCardShow")
	public String dealBankCardShow( ){
		return "/manage/bankCard/bankCardRunShow";
	}
	
	
	@ResponseBody
	@RequestMapping("/bankCardRunList")
	public PageResult<BankCardRunEntity> bankCardRunList(BankCardRunEntity bankCardRun,String page,String limit){
		log.info("查询银行卡请求参数"+bankCardRun.toString());
		String userId = getUserId();
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<BankCardRunEntity> list = bankCardService.findPageBankCardRunByBankCard(bankCardRun);
		 PageInfo<BankCardRunEntity> pageInfo = new PageInfo<BankCardRunEntity>(list);
		 PageResult<BankCardRunEntity> pageR = new PageResult<BankCardRunEntity>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
