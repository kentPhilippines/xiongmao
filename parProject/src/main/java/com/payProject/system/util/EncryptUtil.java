package com.payProject.system.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

import com.payProject.config.common.Constant;

/**
 * <p>权限密码加密，这个类可以用来做密码注册</p>
 * <li><strong>用户注册的时候必须使用这个类,不然会造成密码错误</strong></li>
 * @author 2019-07-31
 *
 */
@Component
public class EncryptUtil {
	private static   int hashIterations = 2;  //散列次数
	//TODO  这里需要优化
	private static  String algorithmName = "MD5"; // 算法名称
	//TODO  这里需要优化
	/**
	 * <p>密码加密</p>
	 * <li>这个类来源于shiro密码加密的方法</li>
	 * @param username			用户名
	 * @param password			密码
	 * @return					返回密码和盐值，用于注册用户
	 * @author  
	 */
	public static  Map<String,String> encryptPassword(String userId,String password){
		String salt1 = userId;
	//	String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash hash = new SimpleHash(algorithmName, password, salt1 , hashIterations);
		String encodedPassword = hash.toHex();  
		Map<String,String> map = new HashMap<>();
		map.put(Constant.Common.PASSWORD, encodedPassword);
		map.put(Constant.Common.SALT, salt1 );
		return map;
	}
	/**
	 * 这个类只为了获取支付密码
	 * @param payPassword
	 * @return
	 */
	public static  Map<String,String> encryptPassword(String payPassword){
		String salt2 = payPassword; //new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash hash = new SimpleHash(algorithmName, payPassword,salt2, hashIterations);
		String payPassword1 = hash.toHex();
		Map<String,String> map = new HashMap<>();
		map.put(Constant.Common.PAYPASSWORD, payPassword1);
		return map;
	}
	// 手动测试密码加密方法
	public   void main(String[] args) {
		Map<String, String> map = encryptPassword("admin", "admin");
		System.out.println(map.get(Constant.Common.PASSWORD));
		System.out.println(map.get(Constant.Common.SALT));
	}
}
