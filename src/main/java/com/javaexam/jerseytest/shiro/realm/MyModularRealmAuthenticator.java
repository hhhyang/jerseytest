package com.javaexam.jerseytest.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;


public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authnToken)
            throws AuthenticationException {


        // 判断getRealms()是否返回为空
        assertRealmsConfigured();

        UsernamePasswordToken token = (UsernamePasswordToken) authnToken;

        // 所有Realm
        Collection<Realm> realms = getRealms();
        return doMultiRealmAuthentication(realms, token);

    }
}