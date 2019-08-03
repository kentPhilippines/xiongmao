package com.payProject.system.shiro;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.payProject.system.entity.Resources;
import com.payProject.system.entity.Role;
import com.payProject.system.entity.User;
import com.payProject.system.entity.UserExample;
import com.payProject.system.mapper.ResourcesMapper;
import com.payProject.system.mapper.RoleMapper;
import com.payProject.system.mapper.UserMapper;

import cn.hutool.core.collection.CollUtil;

/**
 * shiro身份校验核心类
 * 
 * @author 作者: z77z
 * @date 创建时间：2017年2月10日 下午3:19:48
 */

public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	UserMapper userMapper;
	@Resource
	ResourcesMapper resourcesMapper;
	@Resource
	RoleMapper  roleMapper;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object user = principals.getPrimaryPrincipal();
		Map<String, Object> objectToMap = objectToMap(user);
		List<Resources> list = resourcesMapper.findResourceByUserId(objectToMap.get("userId").toString());
		List<Role> roleList = roleMapper.findByUserId(objectToMap.get("userId").toString());
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
		criteria.andUserIdEqualTo(username);
		List<User> userList = userMapper.selectByExample(example);
		if(CollUtil.isEmpty(userList)) {
			return null;
		}
		User user = CollUtil.getFirst(userList);
		/**
		 * principal		            用户数据	
		 * hashedCredentials	密码
		 * credentialsSalt		该类需要的盐值类型
		 * realmName 			自定义realm的类名
		 * 
		 */
		return new SimpleAuthenticationInfo(user,user.getUserPassword(),ByteSource.Util.bytes(user.getUserSalt()),"MyShiroRealm");
	}
	/**
	 * <p>实体类对象转map</p>
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try { for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
