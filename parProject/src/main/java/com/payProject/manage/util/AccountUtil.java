package com.payProject.manage.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.payProject.manage.entity.AccountEntity;
import com.payProject.manage.entity.UserAccount;
import com.payProject.manage.service.AccountService;
import com.payProject.system.entity.User;

import cn.hutool.core.collection.CollUtil;
/**
 * <p>关于账户操作的工具类</p>
 * @author K
 */
@Component
public class AccountUtil {
	@Autowired
	 private AccountService accountServiceImpl;
	
	/**
	 * <p>根据自己的登录账号，查询自己所有的交易账号</p>
	 * @param account		自己的登录账号
	 * @return
	 */
	public List<String> findAccountMyAccount(String account){
		 List<UserAccount> findAccountMyAccount = accountServiceImpl.findAccountMyAccount(account);
		 List<String> accountList = new ArrayList();
		 for(UserAccount userA :  findAccountMyAccount)
			 accountList.add(userA.getAccountId());
		 if(CollUtil.isEmpty(accountList))
			 accountList.add("无子账户");
		return accountList;
	}
	
	/**
	 * <p>根据代理商账号查询所有的会员子账号</p>
	 * @param account			代理商账号
	 * @return
	 */
	public List<String> findAccountByAgentAccount(String account){
		List<String> list = new ArrayList();
		List<User> findAccountByAgent = accountServiceImpl.findAccountByAgent( account);
		List<String> userList = new ArrayList();
		for(User entity  : findAccountByAgent) 
			userList.add(entity.getUserId());
		if(userList.size() == 0) {
			userList.add("无子账户");
			return userList;
		}
		for(String account1 :  userList) 
			list = listMerge(list ,findAccountMyAccount(account1));
		if(CollUtil.isEmpty(list))
			list.add("无子账户");
		return list;
	}
	
	
	List<String> listMerge(List<String> con ,  List<? extends CharSequence> con1){
		for(CharSequence ch : con1)
			con.add(ch.toString());
		return con;
	}
	
	
	
	
	
	
	
	
	
	
	
}
