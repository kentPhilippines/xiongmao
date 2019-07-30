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
import com.payProject.system.entity.Role;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserExample;
import com.payProject.system.mapper.ResourcesMapper;
import com.payProject.system.mapper.RoleMapper;
import com.payProject.system.mapper.UserMapper;
/**
 * <p>关于shiro的核心类</p>
 * <div>
 * <u>该类有两个核心作用</u>
 * 		<li>1,存入用户权限信息</li>
 * 		<li>2,用户密码加密以及比对验证</li>
 * </div>
 * @author K
 * @date 2019-07-30
 */
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
		List<Resources> list = resourcesMapper.findResourceByUserId(user.getUserId());
		List<Role> roleList = roleMapper.findByUserId(user.getId());
		// 遍历角色集合，将角色名称保存到set集合中
		Set<String> stringRoles = new HashSet<String>();
		for(Role role:roleList){
			stringRoles.add(role.getRoleName());
		}
		// 遍历权限集合，将权限code保存到set集合中
		Set<String> stringPermissions = new HashSet<String>();
		for(Resources func:list){
			if(func.getResourcesUrl()!=null){
				stringPermissions.add(func.getResourcesUrl());
			}
		}
		// 将 角色的set 及 权限的set 保存到 SimpleAuthorizationInfo
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();		
		info.setStringPermissions(stringPermissions);//这里存进去的都是该用户可以看到的信息
		info.setRoles(stringRoles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//登录token保存用户信息
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		String username = upToken.getUsername();
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(username);
		User user = (User) userMapper.selectByExample(example);
		if(user == null) {
			//当返回值为null的时候   密码验证期会验证不通过   相关的授权操作不会发生
			return null;
		}
		/**
		 * principal		            用户数据	
		 * hashedCredentials	密码
		 * credentialsSalt		该类需要的盐值类型
		 * realmName 			自定义realm的类名
		 * 
		 */
		return new SimpleAuthenticationInfo(user,user.getUserPassword(),ByteSource.Util.bytes(user.getUserSalt()),"MyShiroRealm");
	}
	/*
	 * //清除缓存//////要是这里不清空缓存就会加载jedis 的连接池 为了防止缓存数据连接问题造成 shiro的异常 所以这里要清空缓存 public
	 * void clearCached() { PrincipalCollection principals =
	 * SecurityUtils.getSubject().getPrincipals(); super.clearCache(principals); }
	 */
	


}
