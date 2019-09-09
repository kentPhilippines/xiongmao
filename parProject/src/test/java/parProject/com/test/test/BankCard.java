package parProject.com.test.test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
		/*
		 * String sendHttpsGet =
		 * HttpUtil.sendHttpsGet("http://tinyurl.com/api-create.php", params);
		 * //QRCodeUtil.encode(
		 * "alipays://platformapi/startapp?appId=20000067&url=www.baidu.com",
		 * "C:/Users/ADMIN/Pictures/Feedback/{5F6E618A-0465-4906-B660-E1115B4E2DEC}/Capture001.png",
		 * "E:/img", true); QRCodeUtil.encode(
		 * "alipays://platformapi/startapp?appId=20000067&url=www.baidu.com",
		 * "C:/Users/ADMIN/Pictures/Feedback/{5F6E618A-0465-4906-B660-E1115B4E2DEC}/Capture001.png",
		 * "E:/img", true);
		 */
	
	}
	
	
	
}
