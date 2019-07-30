package com.payProject.system.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.payProject.system.entity.Resources;
import com.payProject.system.entity.User;
import com.payProject.system.mapper.ResourcesMapper;
import com.payProject.system.mapper.RoleMapper;
import com.payProject.system.mapper.UserMapper;

@Component
public class MyShiroRealm extends AuthorizingRealm {
	@Resource
	UserMapper userMapper;
	@Resource
	ResourcesMapper resourcesMapper;
	@Resource
	RoleMapper  roleMapper;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		User user = (User) principals.getPrimaryPrincipal();
		// 查询当前用户的所有权限，配置到shiro中  根据当前用户的id    查询该用户的所有权限
		List<Resources> list = resourcesMapper.findResourceByUserId(user.getUserid())
				
				
				findByUserId_menu(user.getId(), null );
		
		// 查询当前用户的角色信息     根据当前用户的id   查询该用户所对应的权限
		List<Role> roleList = roleDao.findByUserId(user.getId());
		
		
		// 遍历角色集合，将角色名称保存到set集合中
		Set stringRoles = new HashSet();
		for(Role role:roleList){
			stringRoles.add(role.getRolename());
		}
		
		// 遍历权限集合，将权限code保存到set集合中
		Set stringPermissions = new HashSet();
		for(Function func:list){
			if(func.getFuncurl()!=null){
				stringPermissions.add(func.getFuncurl());
			}
		}
		
		// 将 角色的set 及 权限的set 保存到 SimpleAuthorizationInfo
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();		
		info.setStringPermissions(stringPermissions);		
		info.setRoles(stringRoles);
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//关于登录的
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		String username = upToken.getUsername();
		//根据姓名查询的user
		User user = userDao.findByName(username);
		if(user == null) {
			//当返回值为null的时候   密码验证期会验证不通过   相关的授权操作不会发生
			return null;
		}
		/**
		 * principal		    用户数据	
		 * hashedCredentials	数据库密码
		 * credentialsSalt		该类需要的盐值类型
		 * realmName 			自定义realm的类名
		 * 
		 */
	
		return new SimpleAuthenticationInfo(user,user.getPassword(),ByteSource.Util.bytes(user.getSalt()),"MyRealm");
	}
	
	//清除缓存//////要是这里不清空缓存就会加载jedis 的连接池   为了防止缓存数据连接问题造成  shiro的异常  所以这里要清空缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
	


}
