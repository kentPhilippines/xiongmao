package parProject.com.test.test;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;

public class haofu {
	
	public Map ylcode(Map<String, String> map) {
		Map<String,String> requestMap = new HashMap<String,String>();
        requestMap.put("partner", "1006355498");
        requestMap.put("amount", "20");
        requestMap.put("request_time", System.currentTimeMillis()+"");
        requestMap.put("trade_no",UUID.randomUUID().toString());
        requestMap.put("callback_url", "hf");
        requestMap.put("notify_url", "www.baidu.com");
		String sign = getRequestStr(requestMap)+"&"+"KIHSJDM0izQcZFTZOXjwPAMxLRRs04kz7nxMuIDcBSOCHE489uVTgvo4i8L8BrZ29owL79hKZQcnBUUVp9byRsbeaNGlwaXhD8TvtsSUxMsjfLkoy7FQQPz9gnGDVJD6";
		sign = MD5Encode(sign, "UTF-8");
		requestMap.put("sign", sign);
		String returnMsg = null;
		returnMsg = sendHttpPost("", requestMap, "UTF-8");
		// 响应参数
		Map<String,String> retMap = new HashMap<String,String>();
		String codeurl = "";
		if(StrUtil.isNotBlank(returnMsg) && returnMsg.startsWith("<form")){
			List<String> list =  match(returnMsg,"form", "action");
			if(list != null && list.size() > 0) {
				codeurl = list.get(0);
			}
			retMap.put("errcode", "3");
			retMap.put("sperrcode", "success");
			retMap.put("spmsg","请求成功");
			retMap.put("codeurl",codeurl);
		}else{
			JSONObject  jsonObject = new JSONObject(returnMsg);
			retMap.put("errcode", "1");
			retMap.put("sperrcode", jsonObject.getStr("fail_code"));
			retMap.put("spmsg",jsonObject.getStr("fail_msg"));
			retMap.put("codeurl",codeurl);
		}
		return retMap;
	}
	
	
	public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }
	
	public String createParam(Map<String, Object> map, String appInitKey) 	{
		try {
			if (map == null || map.isEmpty()) 
				return null;
			// 对参数名按照ASCII升序排序
			Object[] key = map.keySet().toArray();
			Arrays.sort(key);
			//生成加密原串
			StringBuffer res = new StringBuffer(128);
			for(int i=0; i<key.length; i++) 
				res.append(key[i] + "=" + map.get(key[i]) + "&");
			String rStr = res.append("key="+appInitKey).toString();
			return getKeyedDigestUTF8(rStr, "").toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getKeyedDigestUTF8(String strSrc, String key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(strSrc.getBytes("UTF8"));
			String result = "";
			byte[] temp;
			temp = md5.digest(key.getBytes("UTF8"));
			for (int i = 0; i < temp.length; i++)
				result += Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6);
			return result;
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String getRequestStr(Map<String,String> mmap) {
		StringBuffer sbuf = new StringBuffer(128);
		//对参数名进行ASCII排序
    	Object[] key = mmap.keySet().toArray();
    	Arrays.sort(key);
    	for(int i=0; i<key.length; i++){
    		sbuf.append(key[i]+"="+mmap.get(key[i])+"&");
    	}
    	String sSend = sbuf.toString();
		return sSend.substring(0,sSend.length()-1);
	}
	  public static String MD5Encode(String origin, String charsetname) {  
	        String resultString = null;  
	        try {  
	            resultString = new String(origin);  
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            if (StrUtil.isBlank(charsetname))  
	                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));  
	            else  
	                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));  
	        } catch (Exception e) {  
	        	throw new RuntimeException("MD5签名错误",e);
	        }  
	        return resultString;  
	    }  
	  
	  private static String byteArrayToHexString(byte b[]) {  
	        StringBuffer resultSb = new StringBuffer();  
	        for (int i = 0; i < b.length; i++)  
	            resultSb.append(byteToHexString(b[i]));  
	  
	        return resultSb.toString();  
	    }  
	  
	    private static String byteToHexString(byte b) {  
	        int n = b;  
	        if (n < 0)  
	            n += 256;  
	        int d1 = n / 16;  
	        int d2 = n % 16;  
	        return hexDigits[d1] + hexDigits[d2];  
	    }  

	    public static String MD5Encode(String origin) {  
	    	return MD5Encode(origin,"");  
	    }  
	    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",  
	            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	    
	    /**  
	     * 发送 post请求  
	     * @param httpUrl 地址  
	     * @param maps 参数  
	     */    
	    public String sendHttpPost(String httpUrl, Map<String, String> maps,String charset) {    
	        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
	        // 创建参数队列      
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();    
	        for (String key : maps.keySet()) {    
	            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));    
	        }    
	        try {    
	            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
	        } catch (Exception e) {    
	            e.printStackTrace();    
	        }    
	        return sendHttpPost(httpPost,charset);    
	    }    
	    private RequestConfig requestConfig = RequestConfig.custom()
	            .setSocketTimeout(15000)  
	            .setConnectTimeout(15000)  
	            .setConnectionRequestTimeout(15000)  
	            .build();  
	    /**  
	     * 发送Post请求  
	     * @param httpPost  
	     * @return  
	     */    
	    private String sendHttpPost(HttpPost httpPost, String charset) {
	        CloseableHttpClient httpClient = null;
	        CloseableHttpResponse response = null;
	        HttpEntity entity = null;    
	        String responseContent = null;    
	        try {    
	            // 创建默认的httpClient实例.    
	            httpClient = HttpClients.createDefault();
	            httpPost.setConfig(requestConfig);    
	            // 执行请求    
	            response = httpClient.execute(httpPost);    
	            entity = response.getEntity();    
	            responseContent = EntityUtils.toString(entity,charset);    
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

