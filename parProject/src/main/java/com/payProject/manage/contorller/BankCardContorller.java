package com.payProject.manage.contorller;

import java.math.BigDecimal;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.payProject.config.common.Constant.Common;
import com.payProject.config.common.JsonResult;
import com.payProject.config.common.PageResult;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.BackBankAmount;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.entity.BankCardRunEntity;
import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.service.BankCardService;
import com.payProject.system.entity.User;
import com.payProject.system.service.UserService;
import com.payProject.system.util.DealNumber;
import com.payProject.system.util.EncryptUtil;
import com.payProject.system.util.MapUtil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

@Controller
@RequestMapping("/manage/bankCard")
public class BankCardContorller {
	Logger log = LoggerFactory.getLogger(BankCardContorller.class);
	@Autowired
	BankCardService bankCardService;
	@Autowired
	UserService userService;
	
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
	@ResponseBody
	@PostMapping("/myBankCardInsert")
	@Transactional
	public JsonResult myBankCardInsert(BankCardEntity bankCard){
		log.info("增加银行卡请求参数"+bankCard.toString());
		BankCardEntity bankCards = bankCardService.findBankCardByBankCard(bankCard.getBankCard());
		if(ObjectUtil.isNull(bankCards)){
			bankCard.setRetain2("2");
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
		boolean flag  = bankCardService.updateBankCardByBankCardNo(bankCard);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
	}
	@RequestMapping("/bankCardAttributeEditShow")
	public String bankCardAttributeEdit(BankCardEntity bankCard ,Model m){
	if( StrUtil.isBlank(bankCard.getBankCard())) {
		throw new ParamException("请求参数无效");
	}
	BankCardEntity bankCard1 = bankCardService.findBankCardByBankCard(bankCard.getBankCard());
	m.addAttribute("bankCard", bankCard1);
		return "/manage/bankCard/bankCardAttributeEdit";
	}
	@ResponseBody
	@RequestMapping("/bankCardAttributeEdit")
	public JsonResult bankCardAttributeEdit(BankCardEntity bankCard){
		if( StrUtil.isBlank(bankCard.getBankCard())) {
			throw new ParamException("请求参数无效");
		}
		bankCard.setCreateTime(null);
		boolean flag  = bankCardService.updateBankCardByBankCardNo(bankCard);
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
		boolean flag  = bankCardService.updateBankCardByBankCardNo(bankCard);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
	}
	@RequestMapping("/myBankCardAttributeEditShow")
	public String myBankCardAttributeEditShow(BankCardEntity bankCard ,Model m){
	if( StrUtil.isBlank(bankCard.getBankCard())) {
		throw new ParamException("请求参数无效");
	}
	BankCardEntity bankCard1 = bankCardService.findBankCardByBankCard(bankCard.getBankCard());
	m.addAttribute("bankCard", bankCard1);
		return "/manage/bankCard/myBankCardAttributeEdit";
	}
	@ResponseBody
	@RequestMapping("/myBankCardAttributeEdit")
	public JsonResult myBankCardAttributeEdit(BankCardEntity bankCard){
		if( StrUtil.isBlank(bankCard.getBankCard())) {
			throw new ParamException("请求参数无效");
		}
		bankCard.setCreateTime(null);
		boolean flag  = bankCardService.updateBankCardByBankCardNo(bankCard);
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
	@ResponseBody
	@RequestMapping("/MyBankCardRunList")
	public PageResult<BankCardRunEntity> myBankCardRunList(BankCardRunEntity bankCardRun,String page,String limit){
		log.info("查询银行卡请求参数"+bankCardRun.toString());
		String userId = getUserId();
		List<BankCardEntity> bank = bankCardService.finBankCardByUserId(userId);//卡商下所有银行卡
		List<String> bankCardList = new ArrayList<String>();
		for(BankCardEntity entity : bank) {
			bankCardList.add(entity.getBankCard());
		}
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		bankCardRun.setDealBankCardList(bankCardList);
		List<BankCardRunEntity> list = bankCardService.findPageBankCardRunByBankCard(bankCardRun);
		PageInfo<BankCardRunEntity> pageInfo = new PageInfo<BankCardRunEntity>(list);
		PageResult<BankCardRunEntity> pageR = new PageResult<BankCardRunEntity>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	@RequestMapping("/myBankCardShow")
	public String myBankCardShow(Model m ){
		/**
		 * #######
		 * 	需要的数据
		 * 1,用户银行卡总数
		 * 2,当前用户银行卡总金额     
		 * 3,银行卡每张平均金额
		 * 4,银行卡每张收款额度
		 * 5,需要回款数额
		 * 6,几张银行卡超额
		 */
		String userId = getUserId();
		log.info("【当前操作码商为：】"+userId);
		/**
		 *  <p>根据银行卡负责人查询该码商下所有的银行卡</p>
		 */
		List<BankCardEntity> bank = bankCardService.finBankCardByUserId(userId);//卡商下所有银行卡
		BigDecimal toDayAmount = new BigDecimal(0);
		BigDecimal myMoney = new BigDecimal(0);
		BigDecimal amount = new BigDecimal(0);
		BigDecimal backAmount = new BigDecimal(0);
		BigDecimal sumAmount = new BigDecimal(0);
		BigDecimal sumIsDeal = new BigDecimal(0);
		int number = 0;
		if(CollUtil.isNotEmpty(bank)) {
		List<String> list = new ArrayList<String>();
		BigDecimal money = new BigDecimal(0);// 回款  =  交易  -  利率  - 当前已回款
		
		for(BankCardEntity entity : bank) {
			sumAmount = sumAmount.add(entity.getBankAmount());//当前卡商卡商总余额
			sumIsDeal = sumIsDeal.add(new BigDecimal(entity.getRetain1()));//总交易额度
			if( new BigDecimal(entity.getRetain1()).compareTo(entity.getBankAmount()) == -1){
				log.info("当前卡商："+userId+"，超额卡片为："+entity.getBankCard()+"，该卡上余额为："+entity.getBankAmount()+"，该卡交易额度为："+entity.getRetain1());
				number++;
			}
			list.add(entity.getBankCard());
		}
		//查询所有卡商交易和分润流水
		List<Integer> runTypelist = new ArrayList<Integer>();
		BankCardRunEntity bankCardRun = new BankCardRunEntity();
		bankCardRun.setDealBankCardList(list);
		runTypelist.add(Common.BANKCARD_RUN_DEAL);
		runTypelist.add(Common.BANKCARD_RUN_BENEFIT);
		bankCardRun.setRunTypeList(runTypelist);
		List<BankCardRunEntity> banRunList = bankCardService.findBankCardRunByBankCard(bankCardRun);//这是当前商户卡商总额
		
		/*
		 * 	数据配分的时候要考虑到   银行卡交易流水和 银行卡回款流水保持一样的 数据清理
		 */
		for(BankCardRunEntity bean : banRunList) {
			if(Common.BANKCARD_RUN_DEAL.equals(bean.getRunType())|| Common.BANKCARD_RUN_DEAL.equals(bean.getRunType())) //商户交易+系统分润
				amount = amount.add(bean.getDealAmount());
			if(Common.BANKCARD_RUN_DPAY.equals(bean.getDealAmount())) {//商户回款
				backAmount = backAmount.add(bean.getDealAmount());
			}
		}
		User user = userService.findUserByUserId(userId);
		String retain1 = user.getRetain1();
		BigDecimal bigDecimal = new BigDecimal(StrUtil.isBlank(retain1)?"0":retain1);//码商或者卡商利率
		myMoney = amount.multiply(bigDecimal);//码商卡商利润
		toDayAmount = amount.subtract(backAmount).subtract(myMoney);//当前需回款
		}
		log.info("回款金额："+toDayAmount);
		m.addAttribute("toDayAmount", toDayAmount.compareTo(new BigDecimal("0")) == -1?0:toDayAmount);//当前需回款
		m.addAttribute("sumAmount", sumAmount);//总余额
		m.addAttribute("sumIsDeal", sumIsDeal);//总交易额度
		m.addAttribute("myMoney", myMoney);//总盈利
		m.addAttribute("number", number);//超额卡片数量
		m.addAttribute("bankCount", bank.size());//卡片数量
		return "/manage/bankCard/myBankCardShow";
	}
	
	@RequestMapping("/bankAmount")
	public String bankAmount( ){
		return "/manage/bankCard/myBackBankAmountList";
	}

	@RequestMapping("/backbankAmount")
	public String backbankAmount( ) {
		return "/manage/bankCard/backbankAmountList";
	}
	@ResponseBody
	@RequestMapping("/backbankAmountList")
	public PageResult<BackBankAmount> backbankAmountList(BackBankAmount account,String page,String limit){
		log.info("查询商户请求参数"+account.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<BackBankAmount> list = bankCardService.findPageBackBankAmountByBank(account);
		 PageInfo<BackBankAmount> pageInfo = new PageInfo<BackBankAmount>(list);
		 PageResult<BackBankAmount> pageR = new PageResult<BackBankAmount>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("商户列表响应结果集"+pageR.toString());
		return pageR;
	}
	@ResponseBody
	@RequestMapping("/MyBackbankAmountList")
	public PageResult<BackBankAmount> MyBackbankAmountList(BackBankAmount account,String page,String limit){
		String userId = getUserId();
		log.info("【当前操作码商为：】"+userId);
		account.setAccountId(userId);
		log.info("查询商户请求参数"+account.toString());
		PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		List<BackBankAmount> list = bankCardService.findPageBackBankAmountByBank(account);
		PageInfo<BackBankAmount> pageInfo = new PageInfo<BackBankAmount>(list);
		PageResult<BackBankAmount> pageR = new PageResult<BackBankAmount>();
		pageR.setData(pageInfo.getList());
		pageR.setCode("0");
		pageR.setCount(String.valueOf(pageInfo.getTotal()));
		log.info("商户列表响应结果集"+pageR.toString());
		return pageR;
	}
	@RequestMapping("/backbankAdd")
	public String backbankAdd(Model m ) {
		String userId = getUserId();
		log.info("【当前操作码商为：】"+userId);
		User user = userService.findUserByUserId(userId);
		m.addAttribute("user", user);
		return "/manage/bankCard/backAmount";
	}
	@ResponseBody
	@RequestMapping("/addAmount")
	@Transactional
	public JsonResult myBankCardEdit(BackBankAmount backBankAmount,String payPassword,HttpServletRequest request){
		/**
		 * ##############################################
		 *  	卡商回款逻辑
		 *  1,填入订单
		 *  2,等待审核
		 */
		if(StrUtil.isBlank(backBankAmount.getAccountId())||StrUtil.isBlank(backBankAmount.getBankD()) || StrUtil.isBlank(backBankAmount.getBankR()) || StrUtil.isBlank(payPassword)) {
			throw new ParamException("请求参数无效");
		}
		User user = userService.findUserByUserId(backBankAmount.getAccountId());
		Map<String, String> password = EncryptUtil.encryptPassword(payPassword);
		String string = password.get(Constant.Common.PAYPASSWORD);
		if(!user.getPayPassword().equals(string)) {
			throw new ParamException("密码错误");
		}
		BankCardEntity bankcard = bankCardService.findBankCardByBankCard(backBankAmount.getBankR());
		if(ObjectUtil.isNull(bankcard)) {
			throw new ParamException("系统内不存在该入款卡号，请与对接人联系获取入款卡号");
		}
		if(!bankcard.getBankType().equals(Common.BANKCARDTYPE_TRANSFER)) {
			throw new ParamException("该入款卡号不符合回款要求，请与对接人员沟通获取最新入款卡号");
		}
		
		BankCardEntity bankCardEntity = bankCardService.findBankCardByBankCard(backBankAmount.getBankD());//获取当前出款的银行卡
		if(!Common.STATUS_NO.equals(bankCardEntity.getStatus())) {//出款卡只能为 当前超额的银行卡
			throw new ParamException("该出款卡号不符合回款要求，请与对接人员沟通获取最新出款卡号");
		}
		backBankAmount.setOrderId(DealNumber.GetBankOrder());
		backBankAmount.setBankStatus(Common.BACKBANK_OH);
		String ipAddr = getIpAddr(request);
		log.info("【码商提现ip为：】"+ipAddr+"，当前码商为："+user.getUserId());
		backBankAmount.setIp(ipAddr);
		boolean flag = bankCardService.addBacBankAmount(backBankAmount);
		if(flag) {
			return JsonResult.buildSuccessMessage("等待审批");
		}
		
		
		return JsonResult.buildFailResult("回款失败");
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
	 * </p>商户回款成功</p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/notifyOrderSu")
	@ResponseBody
	@Transactional
	public JsonResult notifyOrderSu(HttpServletRequest request, HttpServletResponse response) {
		String orderId = request.getParameter("orderId");
		if(StrUtil.isBlank(orderId)) {
			throw new ParamException("未找到订单");
		}
		BackBankAmount bankAmount = bankCardService.findBackBankAmountByOrderId(orderId);//当前回款订单
		BankCardEntity bankCardEntity = bankCardService.findBankCardByBankCard(bankAmount.getBankD());//获取当前出款的银行卡
		if(!Common.STATUS_NO.equals(bankCardEntity.getStatus())) {//出款卡只能为 当前超额的银行卡
			throw new ParamException("该出款卡号不符合回款要求，请与对接人员沟通获取最新出款卡号");
		}
		/**
		 * <p>卡商码商回款逻辑</p>
		 * 1,查询当前出款卡是否正确  是否不可用
		 * 2,当前入款卡是否为系统规定的
		 * 3,修改回款订单状态
		 * 4,写入银行卡流水
		 * 5,写入一笔资金流水表（暂时不写入,这里将上下游流水分开处理,减少压力）
		 */
		bankAmount.setBankStatus(Common.BACKBANK_SU);
		boolean flag = bankCardService.updataBackBankAmount(bankAmount);
		if(flag) {
			//银行卡流水
			BankCardRunEntity entity= new BankCardRunEntity();
			entity.setDealAmount(bankAmount.getAmount());
			entity.setDealAccount("SYS");
			entity.setDealBankCard(bankAmount.getBankR());
			entity.setRunType(Common.BANKCARD_RUN_DPAY);
			entity.setWithdrawAccount(bankAmount.getAccountId());
			entity.setWithdrawBankCard(bankAmount.getBankD());
			entity.setWithdrawAmount(bankAmount.getAmount());
			boolean runFlag = bankCardService.addBankRun(entity);
			if(runFlag) {
				return JsonResult.buildSuccessMessage("操作成功");
			}
		}
		return JsonResult.buildFailResult("操作失败");
	}
	/**
	 * <p>商户回款失败</p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/notifyOrderEr")
	@ResponseBody
	@Transactional
	public JsonResult notifyOrderEr(HttpServletRequest request, HttpServletResponse response) {
		log.info("【商户回款失败】");
		String orderId = request.getParameter("orderId");
		if(StrUtil.isBlank(orderId))
			throw new ParamException("未找到订单");
		BackBankAmount backBankAmount = bankCardService.findBackBankAmountByOrderId(orderId);
		if(backBankAmount.getBankStatus().equals(Common.BACKBANK_OH)) {
			backBankAmount.setBankStatus(Common.BACKBANK_ER);
		}
		boolean flag = bankCardService.updataBackBankAmount(backBankAmount);
		if(flag) {
			return JsonResult.buildSuccessMessage("该订单以处理为失败");
		}
		return JsonResult.buildFailResult("无效请求");
	}
}

