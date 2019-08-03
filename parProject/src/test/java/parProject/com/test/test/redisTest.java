package parProject.com.test.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payProject.application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
public class redisTest {
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	
	@Test
	public  void redisClose() {
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		opsForValue.set("3299026706", "0", 1, TimeUnit.DAYS);
	}

}
