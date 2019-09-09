package com.payProject.system.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payProject.config.interceptor.MyInterceptor;
import com.payProject.system.shiro.filter.KickoutSessionControlFilter;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author K
 * @date 2019-07-31
 * 
 */
@Configuration
public class ShiroConfig {
	Logger log = LoggerFactory.getLogger(ShiroConfig.class);
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.timeout}")
    private int timeout;
	/**
	 * <p>被踢后的页面</p>
	 */
	@Value("${spring.shiro.outLogin.url}")
	 private String outLoginUrl;
	/**
	 * <p>同时在线人数</p>
	 */
	 @Value("${spring.shiro.maxLoginSession}")
	 private int maxOnlineSession; 
	/**
	 * <p>ShiroFilterFactoryBean 处理拦截资源文件问题。</p>
	 * <li>注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在</li>
	 * <li> 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager</li>
	 * <p>Filter Chain定义说明 </p>
	 * <li>1、一个URL可以配置多个Filter，使用逗号分隔 </li>
	 * <li>2、当设置多个过滤器时，全部验证通过，才视为通过</li>
	 * <li>3、部分过滤器可指定参数，如perms，roles</li>
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		log.info("---shiroFilter---:shiro过滤器发挥作用");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/common/errors/error");
		//自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		//限制同一帐号同时在线的个数。
		filtersMap.put("kickout", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		log.info("---------------------shiroFilter--------------------:shiro过滤器发挥作用------->权限过滤器");
		// 权限控制map.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
			/**
			 * 这里地方应该以接口数据实现  需要优化
			 */	
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/loginOnline", "anon");//
		filterChainDefinitionMap.put("/index", "authc");//首页拦截
		filterChainDefinitionMap.put("/system/**", "authc");/* "roles[admin]"角色必须是admin才可以*/
		filterChainDefinitionMap.put("/manage/**", "authc");/* "roles[admin]"角色必须是admin才可以*/
		filterChainDefinitionMap.put("/**", "authc"); //所有全部拦截
		// 配置不会被拦截的链接 顺序判断
		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		// 从数据库获取动态的权限
		// filterChainDefinitionMap.put("/add", "perms[权限添加]");
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		//logout这个拦截器是shiro已经实现好了的。
		// 从数据库获取
		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
	
	
	
	
	
	

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager());
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * <p>身份认证realm; (这个需要自己写，账号密码校验；权限等)</p>
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(MyHashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public MyHashedCredentialsMatcher MyHashedCredentialsMatcher() {
		MyHashedCredentialsMatcher matcher = new MyHashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");//加密方法
		//TODO  这里需要优化
		matcher.setHashIterations(2);//加密迭代次数
		//TODO  这里需要优化
		matcher.setStoredCredentialsHexEncoded(true);
		return matcher;
	}
	/**
	 *<p> 配置shiro redisManager</p>
	 * <li>使用的是shiro-redis开源插件</li>
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(timeout);
		// redisManager.setPassword(password);
		return redisManager;
	}

	/**
	 * <p>cacheManager 缓存 redis实现</P>
	 * <li>使用的是shiro-redis开源插件</li>
	 * @return
	 */
	public RedisCacheManager cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManager());
		return redisCacheManager;
	}

	/**
	 * <p>RedisSessionDAO shiro sessionDao层的实现 通过redis</p>
	 * <li>使用的是shiro-redis开源插件</li>
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * <p>Session Manager</p>
	 * <li>使用的是shiro-redis开源插件</li>
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}
	
	/**
     * <p>cookie对象;</p>
     * @return
     */
    public SimpleCookie rememberMeCookie(){
       //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(2592000);
       return simpleCookie;
    }
    
    
    /**
     *<p> 限制同一账号登录同时登录人数控制</p>
     * @return
     */
    public KickoutSessionControlFilter kickoutSessionControlFilter(){
    	KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
    	//使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
    	//这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
    	//也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
    	kickoutSessionControlFilter.setCacheManager(cacheManager());
    	//用于根据会话ID，获取会话进行踢出操作的；
    	kickoutSessionControlFilter.setSessionManager(sessionManager());
    	//是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
    	kickoutSessionControlFilter.setKickoutAfter(false);
    	//同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
    	kickoutSessionControlFilter.setMaxSession(5);
    	//被踢出后重定向到的地址；
    	kickoutSessionControlFilter.setKickoutUrl(outLoginUrl);
        return kickoutSessionControlFilter;
     }
}
