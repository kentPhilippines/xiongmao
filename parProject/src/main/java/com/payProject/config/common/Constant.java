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
		
		
	}
 
	/*******************************User常量列表*********************************/
	/**
	 * User 存放和User类相关的常量
	 ***/
	public static final class User {
 
		private static final String USER_IN_SESSION = "user_in_session";
		private static final String USER_NAME = "username";
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
