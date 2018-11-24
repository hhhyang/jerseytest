package com.javaexam.jerseytest.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.security.krb5.Realm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/usersPage");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        //自定义过滤器
        // Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        // filtersMap.put("myAccessControlFilter", new MyAccessControlFilter());
        // shiroFilterFactoryBean.setFilters(filtersMap);

        // 过滤器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        // 对应 shiro.ini 的 [urls]部分配置
        // 配置 进来的url需要执行的验证和授权检查

        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/font-awesome/**","anon");



        //  filterChainDefinitionMap.put("/users", "anon");
        filterChainDefinitionMap.put("/createPermission", "anon");
        // filterChainDefinitionMap.put("/**", "myAccessControlFilter");
        // filterChainDefinitionMap.put("/**", "authc");

        //自定义加载权限资源关系
//        List<Resources> resourcesList = resourcesService.queryAll();
//        for(Resources resources:resourcesList){
//
//            if (StringUtil.isNotEmpty(resources.getResurl())) {
//                String permission = "perms[" + resources.getResurl()+ "]";
//                filterChainDefinitionMap.put(resources.getResurl(),permission);
//            }
//        }

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //设置realm.
//        securityManager.setAuthenticator(modularRealmAuthenticator());
        securityManager.setAuthenticator(customizedModularRealmAuthenticator());

        List<Realm> realms=new ArrayList<>();
        realms.add(myShiroRealm());
        realms.add(myShiroRealm2());
        securityManager.setRealms(realms);
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //我自己实现的加密判断，这里被备注起来，仅供参考
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public MyShiroRealm2 myShiroRealm2(){
        MyShiroRealm2 myShiroRealm = new MyShiroRealm2();
        return myShiroRealm;
    }


    /**
     * （这里我是用自己定义的加密方式，没用到这块，需要的可以释放出来，注意上面的加载）
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1);

        return hashedCredentialsMatcher;
    }


    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 自定义的Realm管理，主要针对多realm
     * */
    @Bean
    public MyModularRealmAuthenticator customizedModularRealmAuthenticator(){
        MyModularRealmAuthenticator customizedModularRealmAuthenticator=new MyModularRealmAuthenticator();
        //设置realm判断条件
        customizedModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());

        return customizedModularRealmAuthenticator;
    }

    /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        ModularRealmAuthenticator modularRealmAuthenticator=new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }


}
