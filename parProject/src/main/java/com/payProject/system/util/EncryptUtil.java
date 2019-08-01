package com.payProject.system.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>权限密码加密，这个类可以用来做密码注册</p>
 * <li><strong>用户注册的时候必须使用这个类,不然会造成密码错误</strong></li>
 * @author 2019-07-31
 *
 */
public class EncryptUtil {
	@Value("${spring.shiro.hashIterations}")
	private static int hashIterations;  //散列迭代次数
	@Value("${spring.shiro.algorithmName}")
	private static String algorithmName = "md5"; // 算法名称
	/**
	 * <p>密码加密</p>
	 * <li>这个类来源于shiro密码加密的方法</li>
	 * @param username			用户名
	 * @param password			密码
	 * @return					返回密码和盐值，用于注册用户
	 * @author  
	 */
	public static Map<String,String> encryptPassword(String username,String password){
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		// algorithmName: 加密算法的名称 ；password：明文密码   ； salt: 盐值  ； hashIterations：算法迭代次数
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		SimpleHash simpleHash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
		String payPassword = simpleHash.toHex();
		String encodedPassword = hash.toHex();  
		Map<String,String> map = new HashMap<>();
		map.put("password", encodedPassword);
		map.put("payPassword", payPassword);
		map.put("salt", salt1 + salt2);
		return map;
	}
	
	/**
	 * 这个类只为了获取支付密码
	 * @param payPassword
	 * @return
	 */
	public static Map<String,String> encryptPassword(String payPassword){
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		// algorithmName: 加密算法的名称 ；payPassword：明文密码   ； salt: 盐值  ； hashIterations：算法迭代次数
		SimpleHash hash = new SimpleHash(algorithmName, payPassword,salt2, hashIterations);
		String payPassword1 = hash.toHex();
		Map<String,String> map = new HashMap<>();
		map.put("payPassword", payPassword1);
		map.put("salt",salt2);
		return map;
	}
	// 手动测试密码加密方法
	public static void main(String[] args) {
		Map<String, String> map = encryptPassword("admin", "admin");
		System.out.println(map.get("password"));
		System.out.println(map.get("salt"));
	}
}
