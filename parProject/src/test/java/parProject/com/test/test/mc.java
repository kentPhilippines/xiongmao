package parProject.com.test.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class mc {
	public static void main(String[] args) throws Exception {
		byte[] data ; //= Files.readAllBytes(Paths.get("c:/tmp/apache-tomcat-8.5.23.zip"));
		data = "asdsadadsa".getBytes("UTF-8");
		Key key = new SecretKeySpec(STR_KEY.getBytes(), "");
		//使用MD5算法计算摘要
		byte[] md5Digest = mac("HmacMD5", key, data);
		byte[] shaDigest = mac("HmacSHA256", key, data);
		//把摘要后的结果转换成十六进制的字符串（也可以使用Base64进行编码）
		System.out.println(Hex.encodeHexString(md5Digest));
		System.out.println(Hex.encodeHexString(shaDigest));
	}
		 
		//秘钥（必须要是通信双方共享的）
		static final String STR_KEY = "266f5fe18e714688a083df4ca9f78064";
		
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
		

	

}
