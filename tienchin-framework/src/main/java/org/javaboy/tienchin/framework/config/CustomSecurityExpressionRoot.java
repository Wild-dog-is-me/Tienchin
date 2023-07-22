package org.javaboy.tienchin.framework.config;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;

/**
 * @Author: Odin
 * @Date: 2023/7/7 15:37
 * @Description:
 */
public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;

    private Object returnObject;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * Creates a new instance
     *
     * @param authentication the {@link Authentication} to use. Cannot be null.
     */
    public CustomSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean hasPermission(String permission) {
        // 获取当前登陆用户所具有的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (antPathMatcher.match(authority.getAuthority(), permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAnyPermission(String... permissions) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            for (String permission : permissions) {
                if (antPathMatcher.match(authority.getAuthority(), permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasAllPermission(String... permissions) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (permissions.length == 0 || permissions == null) {
            return false;
        }
        for (String permission : permissions) {
            boolean flag = false;
            for (GrantedAuthority authority : authorities) {
                if (antPathMatcher.match(authority.getAuthority(), permission)) {
                    flag = true;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

        @Override
        public void setFilterObject (Object filterObject){

        }

        @Override
        public Object getFilterObject () {
            return null;
        }

        @Override
        public void setReturnObject (Object returnObject){

        }

        @Override
        public Object getReturnObject () {
            return null;
        }

        @Override
        public Object getThis () {
            return null;
        }
    }
