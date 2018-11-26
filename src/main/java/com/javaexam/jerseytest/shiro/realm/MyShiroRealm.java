package com.javaexam.jerseytest.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger LOG = LoggerFactory.getLogger(MyShiroRealm.class);

    @Override
    public String getName() {
        return "myShiroRealm1";
    }

    // 根据 输入的身份与凭证，返回用于认证的身份与凭证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        LOG.info("enter doGetAuthenticationInfo");

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("user1", "pwd1", getName());

        return authenticationInfo;
    }


    // 根据 身份获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        LOG.info("enter doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("as");
        info.addStringPermission("sdf");

        return info;
    }

}