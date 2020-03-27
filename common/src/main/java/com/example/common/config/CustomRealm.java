package com.example.common.config;

import com.example.common.entity.SysPermission;
import com.example.common.entity.SysRole;
import com.example.common.entity.User;
import com.example.common.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("-------权限认证方法--------");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<SysRole>roles = userService.getRoles(username);
        for(SysRole role:roles){
            info.addRole(role.getRole());
            for(SysPermission p:role.getSyspermissions()){
                info.addStringPermission(p.getPermission());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        String userName = (String) authenticationToken.getPrincipal();
        String userPwd = String.valueOf((char[])authenticationToken.getCredentials());
        //根据用户名从数据库获取密码
        User user = userService.getUser(userName);
        if (userName == null) {
            throw new AccountException("用户名不正确");
        } else if (!userPwd.equals(user.getPassword() )) {
            throw new AccountException("密码不正确");
        } else if(user.getAvailable()==false){
            throw new LockedAccountException("用户未审核");
        }
        return new SimpleAuthenticationInfo(userName, user.getPassword(),getName());
    }
}
