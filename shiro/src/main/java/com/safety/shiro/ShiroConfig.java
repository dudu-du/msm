package com.safety.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    private static String host;
//
//    @Value("${spring.redis.host}")
//    public void setHost(String value) {
//        this.host = value;
//    }
//
//    private static int port;
//
//    @Value("${spring.redis.port}")
//    public void setPort(int value) {
//        this.port = value;
//    }
//
//    private static String password;
//
//    @Value("${spring.redis.password}")
//    public void setPassword(String value) {
//        this.password = value;
//    }

    // 下面两个方法对 注解权限起作用有很大的关系，请把这两个方法，放在配置的最上面
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }



    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notlogin");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        //游客，开发权限
//        filterChainDefinitionMap.put("/guest/**", "anon");
//        //用户，需要角色权限 “user”
//        filterChainDefinitionMap.put("/user/**", "roles[user]");
//        //管理员，需要角色权限 “admin”
//        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/schoolLogin", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/oauth2/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/node_modules/**", "anon");
        filterChainDefinitionMap.put("/Public/**", "anon");
        filterChainDefinitionMap.put("/WeChat/**", "anon");
        filterChainDefinitionMap.put("/ClassCircle/**", "anon");
        // filterChainDefinitionMap.put("/View/**", "anon");
        filterChainDefinitionMap.put("/captcha", "anon");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(realm());
        // 自定义缓存实现 使用redis
//        securityManager.setCacheManager(cacheManagerShiro());
//        // 自定义session管理 使用redis
//        securityManager.setSessionManager(SessionManager());
        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */


    @Bean(name="realm")
    @DependsOn("lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    public Realm realm(){
        AuthRealm authRealm = new AuthRealm();
//        // 根据情况使用缓存器
//        authRealm.setCacheManager(cacheManagerShiro());
        return authRealm;
    }


//    /**
//     * shiro缓存管理器;
//     * 需要添加到securityManager中
//     * @return
//     */
//    @Bean
//    public RedisCacheManager cacheManagerShiro(){
//        RedisCacheManager redisCacheManager = new RedisCacheManager();
//        redisCacheManager.setRedisManager(redisManager());
//        //redis中针对不同用户缓存
//        redisCacheManager.setPrincipalIdFieldName("id");
//        //用户权限信息缓存时间
//        redisCacheManager.setExpire(200000);
//        return redisCacheManager;
//    }
//
//    @Bean
//    public RedisManager redisManager() {
//
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host+":"+port);
//        if(!StringUtils.isEmpty(password)) {
//            redisManager.setPassword(password);
//        }
//        return redisManager;
//    }

    /**
     * 让某个实例的某个方法的返回值注入为Bean的实例
     * Spring静态注入
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean(){
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(new Object[]{securityManager()});
        return factoryBean;
    }

}