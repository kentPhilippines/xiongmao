package com.payProject.manage.util;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.hutool.http.HttpUtil;

@Component
public class SendUtil {
	@Value("${gateway.url}")
	private  String gatewayUrl;
	private final String privateKey = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAu0wb+QnOIwVPMj3hs1Q6pFuLLdQFdc9baTRMPw6X+x3Lhmrk16AGep6ggcvFKEAWyZmyg33gmgZwJMGoWj1utQIDAQABAkB9lv5W0p1X3FKLhPUX043y8bN0ymvS4HUSKVBLJBUC+4GUpH4ng/4NkA3hYoa91AJfK/kQ7PTuTNIbdzUzLkntAiEA/uQS8RMT41P/ZUQofiDDgUGRuFgL+vsOgR387QextfMCIQC8HL3wlkZfwvcVKDuQ5OEICpzc8Ci2ZfTEogPnrluqtwIhAL620CVo7NyPIO0YTmPxB9dSxEF2P6CO8I9TbMe9lg5ZAiEAhGV+UcySr2ebW6q7cdmFgJFnoiDtpqLPyW12biPLpLUCIDU0PmBhO3nomUgNdUwyQESFPBKh0T9Y0w3Dh1x8mb/F\r\n" ; 
	private final String publicKey ="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALtMG/kJziMFTzI94bNUOqRbiy3UBXXPW2k0TD8Ol/sdy4Zq5NegBnqeoIHLxShAFsmZsoN94JoGcCTBqFo9brUCAwEAAQ==\r\n"; //new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
	public  String getGatewayUrl() {
		return gatewayUrl;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	/**
	 * <p>对参数进行加密</p>
	 * @param map
	 */
	public Map<String ,Object > careteParam(Map<String,Object> map) throws Exception {
		System.out.println("私钥:" + privateKey);
        System.out.println("公钥:" + publicKey);
        XRsa rsa = new XRsa(publicKey,privateKey);
		String params = HttpUtil.toParams(map);
		System.out.println("-----------------------【请求参数加密，请求参数："+params+"】");
		String encryptData = rsa.publicEncrypt(params);
        System.out.println("加密后内容:" + encryptData);
        String sign = rsa.sign(params);
        Map<String ,Object > parasMap = new HashMap<String,Object>();
        parasMap.put("MD5",encryptData);
        parasMap.put("sign",sign);
        System.out.println("请求参数："+parasMap.toString());
		return parasMap;
	}
}
