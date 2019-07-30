package com.payProject.system.shiro;

import java.util.concurrent.TimeUnit;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
	@Value("${shiro.retryTimes}")
	private Integer retryTimes;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	/**
	 * @param retryTimes
	 */
	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}
	/**
	 * @param AuthenticationToken token 这个类用来搜集用户信息和相关凭证
	 * @param AuthenticationInfo  info 这个类用来验证凭证的正确性
	 * @return 返回 false 密码错误 并提示还有几次的输入错误机会 true 密码正确
	 */
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// 从缓存中查询
		 ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue(); 
		 String count = (String) opsForValue.get(username); 
		 if (count == null) {
			 opsForValue.set(username, "0", 1, TimeUnit.DAYS); 
		 } 
		 // 获取到当前密码错误次数 count = (String)
		  opsForValue.get(username);
		 // 判断密码错误次数 
		  if (Integer.parseInt(count) >= retryTimes) { 
		   throw new ExcessiveAttemptsException();
		   }
		  //密码验证
		boolean matches = super.doCredentialsMatch(token, info);
		   if (matches) { 
			   // 密码验证成功，清除密码错误次数 
			   opsForValue.set(username, "0", 1, TimeUnit.DAYS);
		   } else { 
			   // 密码验证失败，将缓存中的密码错误次数 +1 
			   opsForValue.set(username, String.valueOf(Integer.parseInt(count) + 1),1, TimeUnit.DAYS); 
		   }
		   String count1 = (String) opsForValue.get(username);
		   System.out.println(count1);
		 
		return matches;
	}
}
