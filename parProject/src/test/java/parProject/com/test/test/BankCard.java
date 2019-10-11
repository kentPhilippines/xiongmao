package parProject.com.test.test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.payProject.manage.util.QRCodeUtil;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;

public class BankCard {
	public static void main(String[] args) throws Exception {
		String bankAccount = "武涛";
		String money = "10";
		String bankMark = "CMB";
		String bankName = "招商银行";
		String bankCardId = "621483293468490";//8
		String bankCardName = "武涛";
		
		
		
		String url = "://platformapi/startapp?appId=09999988&actionType=toCard&sourceId=bill&"
				+ "cardNo=6666666666666666666"
				+ "&bankAccount=乔峰"
				+ "&money=0.01"
				+ "&amount='.$rand.'"
				+ "&bankMark=ICBC"
				+ "&bankName=中国工商银行";
		
		
		String url1 = "https://www.alipay.com/?appId=09999988&"+
				"actionType=toCard&sourceId=bill&cardNo="+bankCardId+""+
				"&bankAccount=&money=&amount=10&bankMark="+bankMark+"&bankName=&orderSource= from";
		System.out.println(url1);
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("url", "https://www.alipay.com/?appId=09999988&actionType=toCard&sourceId=bill&cardNo=6214832934684908&bankAccount=武涛&money=500.01&amount=500.01&bankMark=CMB&bankName=招商银行"));
		String sendHttpsGet = sendHttpsGet("http://tinyurl.com/api-create.php", params);
		 
		 System.out.println("短连接网址："+sendHttpsGet);
		QRCodeUtil.encode(
				sendHttpsGet,
		  "C:/Users/ADMIN/Pictures/Feedback/{5F6E618A-0465-4906-B660-E1115B4E2DEC}/Capture001.png",
		  "E:/img", true);
		
		System.out.println(sendHttpsGet);
	
	}
	
	public static String sendHttpsGet(String url, List<NameValuePair> params) {
		HttpGet httpGet;// 创建get请求
 
		if (params == null || params.isEmpty()) {
			httpGet = new HttpGet(url);
		} else {
			List<NameValuePair> parameters = new LinkedList<NameValuePair>();
			for (NameValuePair param : params) {
				if (StrUtil.isEmpty(param.getName()))
					continue;
				parameters.add(param);
			}
 
			if (!url.contains("?")) {
				url += "?" + URLEncodedUtils.format(parameters, "UTF-8");
			} else {
				url += "&" + URLEncodedUtils.format(parameters, "UTF-8");
			}
			System.out.println(url);
			httpGet = new HttpGet(url);
		}
		return sendHttpGet(httpGet);
	}
	private static  RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000)
			.setConnectionRequestTimeout(15000).build();
	/**
	 * 发送Get请求
	 * 
	 * @param httpPost
	 * @return
	 */
	private static String sendHttpGet(HttpGet httpGet) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String responseContent = null;
		try {
			// 创建默认的httpClient实例.
			httpClient = HttpClients.createDefault();
			httpGet.setConfig(requestConfig);
			// 执行请求
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭连接,释放资源
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

}
