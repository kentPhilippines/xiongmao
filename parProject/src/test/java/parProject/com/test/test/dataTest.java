package parProject.com.test.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payProject.application;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
public class dataTest {
	
	@Test
	public void datatest(){
		

			Date date = new Date();
			Timestamp timeStamp = new Timestamp(date.getTime());

				Timestamp timestamp1 = Timestamp.valueOf("2015-11-17 11:20:19");
				System.out.println(timestamp1);
			
			System.out.println(timeStamp);
			String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date m_time1;
			try {
				m_time1 = sdf.parse(nowtime);
				Timestamp m_time2 = new Timestamp(m_time1.getTime());
				System.out.println(m_time2);
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			 new Timestamp(new Date().getTime());
			 
			 System.out.println(new Timestamp(new Date().getTime()));
			 
	}

}
