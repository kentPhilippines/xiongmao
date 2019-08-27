package com.payProject.manage.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {
	// 判断是否全是数字的正则表达式0
	private static Pattern numPattern = Pattern.compile("[0-9]+");
	// 判断是否全是字母
	private static Pattern letterPattern = Pattern.compile("[a-z]+",
			Pattern.CASE_INSENSITIVE);
	// 判断是否为浮点数的正则表达式
	private static Pattern floatPattern = Pattern
			.compile("[-+]?[0-9]*\\.?[0-9]+");
	// 邮件模板是
	private static Pattern emailPattern = Pattern.compile(
			"[^@]+@\\w+[.]\\w+([.]+\\w+){0,}", Pattern.CASE_INSENSITIVE);
	// 普通字符串模板。
	//private static Pattern mobilePattern = Pattern.compile("1(1|3|4|5|8)\\d{9}");// 手机号的正则
	private static Pattern mobilePattern = Pattern.compile("\\d{11}$");// 手机号的正则
	private static Pattern datePattern = Pattern
			.compile("20[0-9][0-9]-[0-1][0-9]-[0-3][0-9]");// 日期格式匹配

	private static Pattern charNumPattern = Pattern.compile("\\d+[a-zA-Z]+(\\w){0,}+|[a-zA-Z]+\\d+(\\w+){0,}");// 字符串中必须同时含数字、字母
	static String allNo_regex = ".*\\W+.*";
	static String char_regex = ".*[a-zA-Z]+.*";
	static String num_regex = ".*\\d+.*";
	/*------------身份证验证-------------------*/
	// 位权值数组
	private static byte[] Wi = new byte[17];
	// 身份证算法求模关键值
	private static final byte fMod = 11;
	// 旧身份证长度
	private static final byte oldIDLen = 15;
	// 新身份证长度
	private static final byte newIDLen = 18;
	// 新身份证年份标志
	private static final String yearFlag = "19";
	// 身份证前部分字符数
	private static final byte fPart = 6;
	// 校验码串
	private static final String CheckCode = "10X98765432";

	private static boolean isNew = false;
	
	public static void main(String args[]){
		String phone = "0000000000";
		System.out.println(isPhoneNum(phone));
	}

	// 判断字符串是否为空
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		}
		return false;
	}

	// 判断字符串是否为空
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 判断一个字符串是否全部都是数字。
	 */
	public static boolean isNumber(String str) {
		Matcher mr = numPattern.matcher(str);
		return mr.matches();
	}

	/**
	 * 判断一个字符串是否全部都是字母。
	 */
	public static boolean isLetter(String str) {
		Matcher mr = letterPattern.matcher(str);
		return mr.matches();
	}

	/**
	 * 判断某个字符串是否是字母数字组合。
	 */
	public static boolean isCharNum(String str) {
		Matcher mr = charNumPattern.matcher(str);
		return mr.matches();
	}
	/**
	 * 判断字符串是否是数字字母组合并且大于等于6位小于等于20位
	 */
	public static boolean isPwdRegual(String pwd) {
		if(CheckUtil.isNotEmpty(pwd)){
			pwd = CheckUtil.trimStr(pwd);
			if(pwd.length() >= 6 && pwd.length() <= 20){
				if (pwd.matches(allNo_regex) && pwd.matches(char_regex) && pwd.matches(num_regex)) {
					return true;
				}else if(pwd.matches("[0-9]{6,}") || pwd.matches("[a-zA-Z]{6,}")){
					return false;
				}else{
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 判断是否浮点数 但是不能判断小数点后位数
	 */
	public static boolean isFloat(String str) {
		Matcher mr = floatPattern.matcher(str);
		return mr.matches();
	}

	/**
	 * 判断一个字符串是不是全部都是中文。
	 */
	public static boolean isAllChinese(String str) {
		try {
			str = new String(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		for (int i = 0, n = str.length(); i < n; i++) {
			if (str.charAt(i) > 0 && str.charAt(i) < 256) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否符合邮箱的规则
	 */
	public static boolean isEmail(String str) {
		Matcher mr = emailPattern.matcher(str.trim());
		return mr.matches();
	}

	/**
	 * 判断是否手机号码,以13或15或18开头，11位数字
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isPhoneNum(String num) {
		Matcher mr = mobilePattern.matcher(num.trim());
		return mr.matches();
	}

	/**
	 * 
	 * 把字符串转化为时间类型
	 */
	public static Date formatDate(String date) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date da = null;
		try {
			da = bartDateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 判断当点时间是否在有效期内 在 true
	 * @param sdate 过期时间
	 * @return
	 */
	public static boolean  isDataExpire(String sdate){
		if(CheckUtil.isEmpty(sdate)){
			return false;
		}
        try {
    		Date date1 = formatDate(sdate);
    		Date now = new Date();
    		return date1.after(now);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	/**
	 * 把时间类型转化为字符串
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String da = "";
		try {
			da = bartDateFormat.format(date);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return da;
	}

	/**
	 * 比较两个时间差是否在一分钟之内 date2在date1一分钟之内返回true，否则返回false
	 */
	public static Boolean validedate(Date date1, Date date2) {
		date1.setMinutes(date1.getMinutes() + 1);
		Boolean f = date1.after(date2);
		return f;
	}

	/**
	 * 日期格式是否为'YYYY-MM-DD HH:mm:ss'
	 * 
	 * @param order_date
	 *            日期
	 * @return 如果是日期,返回true,否则返回false
	 */
	public static boolean isDate2(String order_date) {

		if (order_date.length() != 19) {
			return false;
		}
		String str = order_date.substring(0, 10);
		Matcher mp = datePattern.matcher(str);

		return mp.matches();
	}

	/*-----------------验证身份证---------------------*/
	// 获取时间串
	private static String getIDDate(final String idCard, boolean newIDFlag) {
		String dateStr = "";
		if (newIDFlag)
			dateStr = idCard.substring(fPart, fPart + 8);
		else
			dateStr = yearFlag + idCard.substring(fPart, fPart + 6);
		return dateStr;
	}

	private static void setWiBuffer() {
		for (int i = 0; i < Wi.length; i++) {
			int k = (int) Math.pow(2, (Wi.length - i));
			Wi[i] = (byte) (k % fMod);
		}
	}

	// 获取新身份证的最后一位:检验位
	private static String getCheckFlag(String idCard) {
		int sum = 0;
		// 进行加权求和
		for (int i = 0; i < 17; i++) {
			sum += Integer.parseInt(idCard.substring(i, i + 1)) * Wi[i];
		}
		// 取模运算，得到模值
		byte iCode = (byte) (sum % fMod);
		return CheckCode.substring(iCode, iCode + 1);
	}

	// 判断串长度的合法性
	private static boolean checkLength(final String idCard, boolean newIDFlag) {
		boolean right = (idCard.length() == oldIDLen)
				|| (idCard.length() == newIDLen);
		isNew = false;
		if (right) {
			isNew = (idCard.length() == newIDLen);
		}
		return right;
	}

	// 判断时间合法性
	private static boolean checkDate(final String dateSource) {
		String dateStr = dateSource.substring(0, 4) + "-"
				+ dateSource.substring(4, 6) + "-" + dateSource.substring(6, 8);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		try {
			
			Date date = df.parse(dateStr);
			return (date != null);
		} catch (ParseException e) {
			
			return false;
		}
	}

	// 判断身份证号码的合法性
	public static boolean checkIDCard(String idCard) {
		// 初始化方法
		CheckUtil.setWiBuffer();
		if (!checkLength(idCard, isNew)) {
			System.out.println("11111111111111111111111");
			return false;
		}
		String idDate = getIDDate(idCard, isNew);
		System.out.println(idDate);
		if (!checkDate(idDate)) {
			System.out.println("2222222222222222222222");
			return false;
		}
		if (isNew) {
			String checkFlag = getCheckFlag(idCard);
			String theFlag = idCard.substring(idCard.length() - 1, idCard
					.length());
			if (!checkFlag.equals(theFlag)) {
				// message = "新身份证校验位异常";
				System.out.println("3333333333333333333");
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断卡号密码 规则： 非空 数字 重号7位 连号 卡号长度10-19位 密码长度6-21位
	 */
	public static String checkCard(String no, String pwd) {
		if (null == no || "".equals(no)) {
			return "卡号不能为空！";
		}
		if (null == pwd || "".equals(pwd)) {
			return "密码不能为空！";
		}
		if (!isNumber(no)) {
			return "卡号只能为数字！";
		}
		if (!isNumber(pwd)) {
			return "密码只能为数字！";
		}
		int noMaxLength = 19;
		int noMinLength = 10;
		int pwdMaxLength = 21;
		int pwdMinLength = 6;
		if (no.length() < noMinLength || no.length() > noMaxLength) {
			if (pwd.length() < pwdMinLength || pwd.length() > pwdMaxLength)
				return "卡号,密码位数不正确！";
			return "卡号位数不正确！";
		}
		if (pwd.length() < pwdMinLength || pwd.length() > pwdMaxLength) {
			if (no.length() < noMinLength || no.length() > noMaxLength)
				return "卡号,密码位数不正确！";
			return "密码位数不正确！";
		}
		// 连号,重号判断
		String str1 = "0123456789";
		String str2 = "9876543210";
		String str3 = "123456789";
		String str4 = "987654321";
		if (no.indexOf(str1) > 0 || no.indexOf(str2) > 0
				|| no.indexOf(str3) > 0 || no.indexOf(str4) > 0
				|| CheckUtil.isRepeat(no, 7)) {
			return "请填写有效的充值卡序列号！";
		}
		if (pwd.indexOf(str1) > 0 || pwd.indexOf(str2) > 0
				|| pwd.indexOf(str3) > 0 || pwd.indexOf(str4) > 0
				|| CheckUtil.isRepeat(pwd, 7)) {
			return "请填写有效的充值卡密码！";
		}
		return null;
	}

	/**
	 * 判断字符串中连续重复字符个数是否大于给定的个数
	 */
	public static boolean isRepeat(String str1, int i) {
		int m;
		int n;
		int count = 0;
		for (m = 0; m <= str1.length() - i; m++) {
			count = 1;
			for (n = 0; n < i - 1; n++) {
				if (str1.charAt(m + n) == str1.charAt(m + n + 1)) {
					count++;
				}
				if (count == i) {
					break;
				}
			}
			if (count == i) {
				break;
			}
		}
		if (count == i) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个数组内所有元素不能为空
	 */
	public static boolean isEmpty(String[] items) {
		
		if (null == items) {
			return true;
		}
		
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null || items[i].equals("")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否是支付通道：条件1非空 2长度5-15 3开头字母
	 */
	public static boolean isPcid(String pcid) {
		if (isEmpty(pcid)) {
			return false;
		} else if (!isLen(pcid, 5, 15)) {
			return false;
		} else if (!pcid.startsWith("CMJFK") && !pcid.startsWith("LTJFK")
				&& !pcid.startsWith("DXJFK") && !pcid.startsWith("BC")) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是银行卡 1数字 2长度 12-40
	 */

	public static boolean isBankCard(String bankCard) {
		if (isEmpty(bankCard)) {
			return false;
		} else if (!isNumber(bankCard)) {
			return false;
		} else if (!isLen(bankCard, 12, 40)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个字符串的长度是否是某个值。
	 */
	public static boolean isLen(String str, int len) {
		return str.length() == len;
	}

	/**
	 * 判断一个字符串的长度是否介于两个值之间。
	 */
	public static boolean isLen(String str, int slen, int elen) {
		if(str==null){
			return false;
		}
		return str.getBytes().length >= slen && str.getBytes().length <= elen;
	}
	/**
	 * @author laodb
	 * 密码强度 低 包含数字和字母,不区分大小写,长度大于等于6位小于10位
	 * 		   
	 * 			高 包含数字、字母和特殊字符， 区分大小写 长度大于10位
	 * 
	 * 中 除了低高，其他全是中
	 */
	public static String pwdStrength(String pwd){
		String allNo_regex = ".*\\W+.*";
		String char_regex = ".*[a-zA-Z]+.*";
		String num_regex = ".*\\d+.*";
		System.out.println(pwd.matches(allNo_regex));
		System.out.println( pwd.matches("[a-zA-Z]{6,}"));
		System.out.println( pwd.matches("[0-9]{6,}"));
		if(pwd.length() >= 6 && pwd.length() <= 20){
			if(pwd.indexOf("_") != -1){
				pwd = pwd.replace("_", "\\_");
			}
			if (pwd.matches(allNo_regex) && pwd.matches(char_regex) && pwd.matches(num_regex)) {
				return "3";
			}else if(pwd.matches("[0-9]{6,}") || pwd.matches("[a-zA-Z]{6,}") || pwd.matches("\\W+")){
				return "1";
			}else{
				return "2";
			}
		}else{
			return "1";
		}
	}
	/**
	 * 判断是否是钱包用户名（手机号或者邮箱）
	 */
	public static boolean isLoginName(String loginName){
		if(isEmpty(loginName)){
			return false;
		}else if(isEmail(loginName)||isPhoneNum(loginName)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断list中是否包含该obj.属性值相同就返回true|不同于contains
	 * @shuoming 该obj必须是VO类.并且字段类型都为String。且只会比较到父类级别
	 * @param list
	 * @param obj
	 */
	public static boolean isContains(java.util.List list,Object obj)
	{
		if(list!=null)
		{
			for(int i=0;i<list.size();i++)
			{
				Object ob = list.get(i);
				Class c=ob.getClass();
				Class c2= obj.getClass();
				//相同的类类型
				if(c.getName().equals(c2.getName()))
				{
					java.lang.reflect.Field[] f1 =c.getDeclaredFields();
					java.lang.reflect.Field[] f3= c.getSuperclass().getDeclaredFields();
					
					java.lang.reflect.Field[] f11 =c2.getDeclaredFields();
					java.lang.reflect.Field[] f4= c2.getSuperclass().getDeclaredFields();
					
					java.lang.reflect.Field f[] = new java.lang.reflect.Field[f1.length+f3.length];
					java.lang.reflect.Field f2[] = new java.lang.reflect.Field[f11.length+f4.length];
					
					for(int qq=0;qq<f1.length;qq++)
					{
						f[qq]=f1[qq];
					}
					for(int qq2=f1.length;qq2<f1.length+f3.length;qq2++)
					{
						f[qq2]=f3[qq2-f1.length];
					}
					for(int qq3=0;qq3<f11.length;qq3++)
					{
						f2[qq3]=f11[qq3];
					}
					for(int qq4=f11.length;qq4<f11.length+f4.length;qq4++)
					{
						f[qq4]=f3[qq4-f11.length];
					}
					
						
					if(f.length==f2.length)
					{
						boolean b=false;
						for(int j=0;j<f.length;j++)
						{
							String name=f[j].getName();
							String start=name.substring(0,1).toUpperCase();
							String end=name.substring(1);
							String fieldname=start+end;
							try {
								Method m = c.getMethod("get"+fieldname,new Class[]{});
								Object res = m.invoke(ob, new Object[]{});
								Method m2 = c2.getMethod("get"+fieldname,new Class[]{});
								Object res2 = m2.invoke(obj, new Object[]{});
								if(res!=null&&!res.equals(res2))
								{
									b=true; 
								}
								if(res==null&&res2!=null)
								{
									b=true;
								}
							} catch (Exception e) {
							}
							
						}
						if(!b)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * 判断是否是银行卡号
	 * @author xuehy add
	 */
	public static boolean isCardN0(String cardNO) {
		// 不为空
		if (null == cardNO)
			return false;
		cardNO = cardNO.trim();
		// 全部是数字
		if (!isNumber(cardNO.trim()))
			return false;
		// 长度6~30位
		if (!isLen(cardNO, 6, 30))
			return false;
		return true;
	}
	/**
	 * 判断传入的字符串是否只有 中英文或数字 且小于80个字符
	 * 
	 * @author wyy
	 * @param str
	 * @return 是 返回true 否则返回false;
	 */
	public static Boolean isbankname(String str) {
		if(CheckUtil.isEmpty(str)||!CheckUtil.isLen(str, 1, 80)){
			return false;
		}
		try {
			str = new String(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		for (int i = 0, n = str.length(); i < n; i++) {
			if (str.charAt(i) >= 0 || str.charAt(i) < 257) {
				continue;
				// 0~256为中文
			} else if (str.charAt(i) > 47 || str.charAt(i) < 58) {
				continue;
				// 其中第48～57号为0～9十个阿拉伯数字；
			} else if (str.charAt(i) > 64 || str.charAt(i) < 91) {
				continue;
				// 65～90号为26个大写英文字母
			} else if (str.charAt(i) > 96 || str.charAt(i) < 123) {
				continue;
				// 97～122号为26个小写英文字母
			} else {
				return false;
			}
		}
		// 长度1-100位
		if (!isLen(str, 1, 100))
			return false;
		
		return true;
	}
	/**
	 * 是否是金额
	 * @author xuehy
	 * @param str
	 * @return
	 */
	public static boolean isMoney(String str) {
		if(isNumber(str)){
			return true;
		}
		if(isFloat(str)){
			return true;
		}
		return false;
	}
	/**
	 * @author laodb
	 * 去掉空白字符 转换成小写
	 * @param str
	 * @return
	 */
	public static String  trimStr(String str) {
		if(str != null && !"".equals(str)){
			str = str.toLowerCase().replaceAll("\\s", "");
		}
		return str;
	}

}
