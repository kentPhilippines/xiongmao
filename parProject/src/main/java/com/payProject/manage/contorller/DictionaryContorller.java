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
import com.payProject.config.entity.Dictionary;
import com.payProject.config.exception.ParamException;
import com.payProject.manage.entity.BankCardEntity;
import com.payProject.manage.service.DictionaryService;
import com.payProject.manage.service.impl.BankCardServiceImpl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * <p>数据字典操作</p>
 * @author K
 */
@Controller
@RequestMapping("/config/dictionary")
public class DictionaryContorller {
	Logger log = LoggerFactory.getLogger(DictionaryContorller.class);
	@Autowired
	DictionaryService dictionaryService;
	@ResponseBody
	@PostMapping("/dictionaryInsert")
	@Transactional
	public JsonResult bankCardInsert(Dictionary dictionary){
		log.info("增加银行卡请求参数"+dictionary.toString());
			Boolean flag = dictionaryService.addDictionary(dictionary);
			if(flag)
				return JsonResult.buildSuccessMessage("增加成功");
			return JsonResult.buildFailResult("增加失败");
	}
	
	@RequestMapping("/dictionaryShow")
	public String dictionaryShow( ){
		return "/config/dictionary/dictionaryShow";
	}
	
	@ResponseBody
	@RequestMapping("/dictionaryList")
	public PageResult<Dictionary> dictionaryList(Dictionary dictionary,String page,String limit){
		log.info("查询银行卡请求参数"+dictionary.toString());
		 PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(limit));
		 List<Dictionary> list = dictionaryService.findDictionaryByDictionary(dictionary);
		 PageInfo<Dictionary> pageInfo = new PageInfo<Dictionary>(list);
		 PageResult<Dictionary> pageR = new PageResult<Dictionary>();
			pageR.setData(pageInfo.getList());
			pageR.setCode("0");
			pageR.setCount(String.valueOf(pageInfo.getTotal()));
			log.info("增加银行卡相应参数"+pageR.toString());
		return pageR;
	}
	@RequestMapping("/dictionaryAdd")
	public String bankCardAdd( ){
		return "/config/dictionary/dictionaryManageAdd";
	}
	
	@ResponseBody
	@RequestMapping("/dictionaryDel")
	@Transactional
	public JsonResult dictionaryDel(Dictionary dictionary ){
		if(  null == dictionary.getId() ) {
			throw new ParamException("请求参数无效");
		}
		boolean flag  = dictionaryService.deleteDictionaryByDictionaryId(dictionary.getId());
		if(flag) {
			return JsonResult.buildSuccessMessage("删除成功");
		}
		return JsonResult.buildFailResult();
	}
	
	@RequestMapping("/bankCardEditShow")
	public String bankCardEditShow(Dictionary dictionary ,Model m){
	if(  null == dictionary.getId() ) {
		throw new ParamException("请求参数无效");
	}
	Dictionary dictionary1 = dictionaryService.findDictionaryById(dictionary.getId());
	m.addAttribute("dictionary", dictionary1);
		return "/manage/bankCard/bankCardEdit";
	}
	@ResponseBody
	@RequestMapping("/bankCardEdit")
	public JsonResult bankCardEdit(Dictionary dictionary){
		if(  null == dictionary.getId() ) {
			throw new ParamException("请求参数无效");
		}
		dictionary.setCreateTime(null);
		boolean flag  = dictionaryService.UpdateDictionaryById(dictionary);
		if(flag) {
			return JsonResult.buildSuccessMessage("修改成功");
		}
		return JsonResult.buildFailResult();
	}

}
