package parProject.com.test.test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.payProject.application;
import com.payProject.manage.util.HttpUtil;
import com.payProject.manage.util.QRCodeUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = application.class)
public class BankCard {
	public static void main(String[] args) throws Exception {
		String bankAccount = "武涛";
		String money = "10";
		String bankMark = "CMB";
		String bankName = "招商银行";
		String bankCardId = "621483293468490";//8
		String bankCardName = "武涛";
		String url1 = "https://www.alipay.com/?appId=09999988&"+
				"actionType=toCard&sourceId=bill&cardNo="+bankCardId+""+
				"&bankAccount=&money=&amount=10&bankMark=&bankName=&orderSource= from";
		System.out.println(url1);
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("url", url1));
		String sendHttpsGet = HttpUtil.sendHttpsGet("http://tinyurl.com/api-create.php", params);
		//QRCodeUtil.encode("alipays://platformapi/startapp?appId=20000067&url=www.baidu.com", "C:/Users/ADMIN/Pictures/Feedback/{5F6E618A-0465-4906-B660-E1115B4E2DEC}/Capture001.png", "E:/img", true);
		QRCodeUtil.encode(sendHttpsGet, "C:/Users/ADMIN/Pictures/Feedback/{5F6E618A-0465-4906-B660-E1115B4E2DEC}/Capture001.png", "E:/img", true);
	
	
	}
		
}
