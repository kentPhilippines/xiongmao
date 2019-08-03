package parProject.com.test.test;

import java.util.List;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payProject.application;
import com.payProject.system.entity.User;
import com.payProject.system.service.UserService;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
public class PageTest {
    //查询
	@Autowired
	UserService userService;
    @Test
    public void selectAll() {
    	User user =  new User();
    	user.setUserName("213132");
		List<User> list = userService.findPageUserByUser(user);
		for(User users :   list ) {
			System.out.println(users);
		}
     }
  }
