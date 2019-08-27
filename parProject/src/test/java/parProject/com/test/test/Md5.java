package parProject.com.test.test;


import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payProject.application;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
public class Md5 {
	static String privateKey = "";
	static String publicKey = "";
	//秘钥（必须要是通信双方共享的）
	static final String STR_KEY = "266f5fe18e714688a083df4ca9f78064";
	static void init() throws Exception{
		 KeyPair keyPair = getKeyPair();
		 privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
		 publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
	}
	/**
	 * 加密
	 * @throws Exception 
	 */
	@Test
	public void test() throws Exception {
		String context = "amount=10&appid=mx_test";
		
		String data1 =  "amount=10&appid=mx_test";
		byte[] data = data1.getBytes("UTF-8");
		Key key = new SecretKeySpec(STR_KEY.getBytes(), "");
		//使用MD5算法计算摘要
		byte[] md5Digest = mac("HmacMD5", key, data);
		byte[] shaDigest = mac("HmacSHA256", key, data);
		//把摘要后的结果转换成十六进制的字符串（也可以使用Base64进行编码）
		System.out.println(Hex.encodeHexString(md5Digest));
		System.out.println(Hex.encodeHexString(shaDigest));
		
		
		
		
		
		
		
		
		 // RSA加密
        String encryptData = encrypt(data1, getPublicKey(publicKey));
        System.out.println("加密后内容:" + encryptData);
        // RSA解密
        String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
        System.out.println("解密后内容:" + decryptData);
        // RSA签名
        String sign = sign(data1, getPrivateKey(privateKey));
        // RSA验签
        boolean result = verify(data1, getPublicKey(publicKey), sign);
        System.out.print("验签结果:" + result);
		
		
		
	}
	/**
	 * 解密验签
	 */
	@Test
	public void test1() {
		
		
		
		
		
	}

	/**
	 * 其中，Mac.getInstance支持的算法有：HmacMD5、HmacSHA1、HmacSHA256等等
	 * 全部支持的算法见官方文档：
	 * https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#Mac
	 */
	public static byte[] mac(String algorithm, Key key, byte[] data) throws Exception {
		Mac mac = Mac.getInstance(algorithm);
		//这里是关键，需要一个key（这里就是和普通的消息摘要的区别点）
		mac.init(key);
		byte[] result = mac.doFinal(data);
		return result;
	}
	
	
	  /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    /**
     * 获取密钥对
     * 
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024);
        return generator.generateKeyPair();
    }
    /**
     * 获取私钥
     * 
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }
    /**
     * 获取公钥
     * 
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }
    /**
     * RSA加密
     * 
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }
    /**
     * RSA解密
     * 
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容 
        return new String(decryptedData, "UTF-8");
    }
    /**
     * 签名
     * 
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }
    /**
     * 验签
     * 
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }

}
