package com.risking.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

//要加@Component，不然过滤器不加载
@Component
@WebFilter(filterName="userfilter", urlPatterns="/*")
public class UserFilter implements Filter{
    //无登录配置列表
    private static final Set<String> ALLOWED_PATHS = 
            Collections.unmodifiableSet(new HashSet<>(
                    Arrays.asList("/","/index.html","/login","/regist","/logout", "/upload")));

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * 过滤器做鉴权
     * 1. 如果session中没有"user":"用户名"的Attribute，跳转到登录接口
     *    登录接口中在校验通过后，给session设置"user":"用户名"的Attribute
     * 2. 注册接口不设置任何属性
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        String  path = ((HttpServletRequest) request)
                .getRequestURI(); 

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        if (allowedPath == false 
        		&& (path.equals("/") || path.indexOf("/css/") == 0 
        		|| path.indexOf("/js/") == 0)) {
        	allowedPath = true;
        }
        
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        }
        else {
            ((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/");
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

}
