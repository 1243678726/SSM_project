package cn.yq.oa.relam;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yq.oa.pojo.SysUser;
import cn.yq.oa.service.SysPermissionService;
import cn.yq.oa.service.UserService;

public class UserRelam extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	@Autowired
	private SysPermissionService sysPermissionService;
	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("UserRelam.doGetAuthorizationInfo()授权");
		/*
		 * 授权思路
		 * 1.根据当前用户的角色id查询出角色权限表中的所有的对应的权限id
		 * 
		 * 2.在根据所有的权限id查询出权限表中，对应的所有的权限表达式
		 * 
		 * 3.将这些权限赋给 AuthorizationInfo 对象
		 */
		
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		List<String> permissions = sysPermissionService.selectPermissionsByRoleid(user.getRoleId());
		//授权
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for (String permission : permissions) {
			if(permission!=null) {
				authorizationInfo.addStringPermission(permission);
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("UserRelam.doGetAuthenticationInfo()认证");
		String  usercode = (String) token.getPrincipal();
		System.out.println(usercode);
		SysUser user = userService.selectByUserCode(usercode);
		
		if(user==null) {
			return null;
		}
		System.out.println(user.getPassword());
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
		
		
//		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return authenticationInfo;
	}

}
