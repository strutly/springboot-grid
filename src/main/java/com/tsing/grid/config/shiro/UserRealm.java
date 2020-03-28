package com.tsing.grid.config.shiro;

import com.tsing.grid.entity.sys.Admin;
import com.tsing.grid.mapper.AdminMapper;
import com.tsing.grid.service.sys.PermissionService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

/**
 * 自定义Realm
 * @author lenovo
 *
 */
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private PermissionService permissionService;

	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		Admin admin = (Admin) principals.getPrimaryPrincipal();
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> permissions = permissionService.getPermsByAid(admin.getId());
		//添加资源的授权字符串
		info.addStringPermissions(permissions);
		return info;
	}

	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("进入登录验证");
		//1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		Admin admin =  adminMapper.findByLoginName(token.getUsername());
		if(null==admin){
			throw new AccountException("帐号或密码不正确！");
		}else if(admin.UNVALID.equals(admin.getStatus())){
			throw new DisabledAccountException("帐号已经禁止登录！");
		}else{
			admin.setUpdateTime(new Date());
			adminMapper.updateByPrimaryKeySelective(admin);
			return new SimpleAuthenticationInfo(admin,	admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), getName());
		}
	}
}
