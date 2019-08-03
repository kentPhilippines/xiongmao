package com.payProject.system.shiro;

import java.util.concurrent.TimeUnit;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * <p>密码验证类</p>
 * <li>这个类重写shiro的密码验证方法</li>
 * @author K
 * @date 2019-07-31
 * <p>若该项目未整合redis,也可以使用缓存CacheUtil.newTimedCache(超时时间)
 * 	来实现当天密码错误次数,这个功能
 * 	CacheUtil.newTimedCache()引用自cn.hutool,集成工具类
 * </p>
 * 
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
	/**
	 * <p>密码错误最大次数</p>
	 */
	private Integer retryTimes = 5;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}
	
	
	
	/**
	 * <p>登录验证核心</p>
	 * <li>1,首先从缓存钟取登录错误次数</li>
	 * <li>2,密码比对</li>
	 * <li>3,成功则清除缓存；失败则记录缓存</li>
	 * <li>4,缓存时间为一天,也就是说一天之类错误次数为指定的次数</li>
	 * @param AuthenticationToken token 这个类用来搜集用户信息和相关凭证
	 * @param AuthenticationInfo  info 这个类用来验证凭证的正确性
	 * @return 返回 false 密码错误 并提示还有几次的输入错误机会 true 密码正确
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		 ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue(); 
		 String count = (String) opsForValue.get(username); 
		 if (count == null) {
			 opsForValue.set(username, "0", 1, TimeUnit.DAYS); 
		 } 
		 if (Integer.parseInt(count) >= retryTimes) { 
		   throw new ExcessiveAttemptsException();
		   }
		 boolean matches = super.doCredentialsMatch(token, info);//调用父类密码验证方法
		 if (matches) { 
			 opsForValue.set(username, "0", 1, TimeUnit.DAYS);
		  } else { 
			 opsForValue.set(username, String.valueOf(Integer.parseInt(count) + 1),1, TimeUnit.DAYS); 
		  }
		  String count1 = (String) opsForValue.get(username);
		  System.out.println(count1);
		return matches;
	}
}
