package parProject.com.test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.payProject.application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = application.class)
@WebAppConfiguration
public class TmallApplicationTests {
	@Before
    public void init() {
        System.out.println("开始测试-----------------");
    }
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}
