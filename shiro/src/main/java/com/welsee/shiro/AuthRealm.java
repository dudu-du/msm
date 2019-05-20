package com.welsee.shiro;

import com.welsee.entity.Login;
import com.welsee.exception.ProgramException;
import com.welsee.extentity.ExtPerson;
import com.welsee.service.ILoginService;
import com.welsee.service.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wuzhenzhen on 2018-11-15.
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private ILoginService loginService;

    @Autowired
    @Lazy
    private IPersonService personService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("进入权限验证");
        Login login = (Login) principals.getPrimaryPrincipal();
        if (login != null && !("").equals(login.getId())) {
//            ApplicationContextRegister.getBean(MenuService.class);
            try {
                ExtPerson person = personService.selectByLoginId(login.getId());
                Set<String> roles = new HashSet<>();
                List<String> roleList = person.getRoleList();
                if(!roleList.isEmpty()){
                    roles = new HashSet(roleList);
                }
                SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
                authenticationInfo.setRoles(roles);

                Set<String> permissions = new HashSet<>();
                List<String> permissionList = person.getPermissionList();
                if(!permissionList.isEmpty()){
                    permissions = new HashSet<>(permissionList);
                }
                authenticationInfo.setStringPermissions(permissions);
                return authenticationInfo;
            } catch (ProgramException e) {
                log.error(e.getMessage());
                return null;
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = String.valueOf(usernamePasswordToken.getUsername());
        Login login = null;
        try {
            login = loginService.selectByLoginName(username);
        } catch (ProgramException e) {
            e.printStackTrace();
        }
        AuthenticationInfo authenticationInfo = null;
        if (null != login) {
            String password = new String(usernamePasswordToken.getPassword());

            if (password.equalsIgnoreCase(login.getPassword())) {
                authenticationInfo = new SimpleAuthenticationInfo(login, login.getPassword(), getName());
            }
        }
        return authenticationInfo;
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的 认证缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有 授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        if(getAuthorizationCache() != null) {
            getAuthorizationCache().clear();
        }
    }

    /**
     * 自定义方法：清除所有 认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        if(getAuthenticationCache() != null) {
            getAuthenticationCache().clear();
        }
    }

    /**
     * 自定义方法：清除所有的  认证缓存  和 授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    /**
     * 自定义方法：清除指定用户  认证缓存  和 授权缓存
     */
    public void clearUserCache(Login login) {

        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        cache.remove(login.getId());
//        clearAllCachedAuthenticationInfo();
//        clearAllCachedAuthorizationInfo();
    }

}