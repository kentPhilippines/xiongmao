package parProject.com.test.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payProject.application;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
public class Md5 {
	
	
	@Test
	public void test() {
		try {
			MessageDigest instance = MessageDigest.getInstance("MD5");
			System.out.println("存在MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("不存在MD5");
			 String msg = "No native '" + "MD5" + "' MessageDigest instance available on the current JVM.";
			e.printStackTrace();
		}
	}
	

}
