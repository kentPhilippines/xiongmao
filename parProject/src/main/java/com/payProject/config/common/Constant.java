package com.payProject.config.common;

public final class Constant {
	private Constant() {
		super();
	}
 
	/******************************* Common常量列表 *********************************/
	/**
	 * Common常量列表(存放公共的常量)
	 ***/
	public static final class Common {
		private static final String RECORD_LOGIN_EXPIRE = "501";//账户未登录错误
		private static final String RECORD_LOGIN_EXPIRE_MESSAGE = "当前账户未登录";
		private static final String CHANNEL_NAME = "CH";//渠道命名前缀
		private static final String CHANNEL_NAME_SUF = "99999";//渠道首次命名
		private static final String PAYTYPE_NAME = "PAY";//渠道命名前缀
		private static final String PAYTYPE_NAME_SUF = "999";//渠道首次命名
		private static final String ACCOUNT_NAME = "AC";//商户命名前缀
		private static final String ACCOUNT_NAME_SUF = "999";//商户首次命名编号
		private static final String ACCOUNT_PRIAVTEKEY = "privateKey";//私钥key
		private static final String ACCOUNT_PUBLICKEY = "publicKey";//公钥key
		private static final Integer ACCOUNT_KEY_SIZE = 512;//公钥key
		private static final Integer ACCOUNT_FEE_STUSTA1 = 1;//费率启用状态
		private static final Integer ACCOUNT_FEE_STUSTA2 = 2;//费率停用状态
		private static final Integer ACCOUNT_FEE_STUSTA3 = 3;//自动切换
		
		private static final String ACCOUNTNAME = "请修改商户名";//商户命名首次默认值
		
		
		
		
		
		// 私有
		private Common() {
			super();
		}
		/**
		 * <p>账户未登录错误code</p>
		 * @return  501
		 */
		public static String RECORD_LOGIN_EXPIRE() {
			return RECORD_LOGIN_EXPIRE;
		}
		/**
		 * <p>账户未登录错误提示消息</p>
		 * @return  当前账户未登录
		 */
		public static String RECORD_LOGIN_EXPIRE_MESSAGE() {
			return RECORD_LOGIN_EXPIRE_MESSAGE;
		}
		public static String CHANNEL_NAME() {
			return CHANNEL_NAME;
		}
		public static String CHANNEL_NAME_SUF() {
			return CHANNEL_NAME_SUF;
		}
		public static String PAYTYPE_NAME() {
			return PAYTYPE_NAME;
		}
		public static String PAYTYPE_NAME_SUF() {
			return PAYTYPE_NAME_SUF;
		}
		public static String ACCOUNT_NAME() {
			return ACCOUNT_NAME;
		}
		public static String ACCOUNT_NAME_SUF() {
			return ACCOUNT_NAME_SUF;
		}
		public static String ACCOUNT_PRIAVTEKEY() {
			return ACCOUNT_PRIAVTEKEY;
		}
		public static String ACCOUNT_PUBLICKEY() {
			return ACCOUNT_PUBLICKEY;
		}
		public static String ACCOUNTNAME() {
			return ACCOUNTNAME;
		}
		public static Integer ACCOUNT_KEY_SIZE() {
			return ACCOUNT_KEY_SIZE;
		}
		public static Integer ACCOUNT_FEE_STUSTA1() {
			return ACCOUNT_FEE_STUSTA1;
		}
		public static Integer ACCOUNT_FEE_STUSTA2() {
			return ACCOUNT_FEE_STUSTA2;
		}
		public static Integer ACCOUNT_FEE_STUSTA3() {
			return ACCOUNT_FEE_STUSTA3;
		}
		
		
	}
 
	/*******************************User常量列表*********************************/
	/**
	 * User 存放和User类相关的常量
	 ***/
	public static final class User {
 
		private static final String USER_IN_SESSION = "user_in_session";
		private static final String USER_NAME = "userName";
		private static final String USER_ID = "userId";
		
 
		public static String USER_ID() {
			return USER_ID;
		}

		// 私有
		private User() {
			super();
		}
 
		//
		public static String USER_IN_SESSION() {
			return USER_IN_SESSION;
		}
		//
		public static String USER_NAME() {
			return USER_NAME;
		}
	}
	/*******************************Tree常量列表*********************************/
	/**
	 * Tree 存放和Tree类相关的常量
	 ***/
	public static final class Tree {
		private static final String LEVELONE = "0";//一级菜单
		private static final String LEVELTWO = "1";//二级菜单
		private static final String LEVELTHREE = "2";//三级菜单
		
		private Tree() {
			super();
		}
		public static String LEVELONE() {
			return LEVELONE;
		}
		public static String LEVELTWO() {
			return LEVELTWO;
		}
		public static String LEVELTHREE() {
			return LEVELTHREE;
		}
	}
 
}
