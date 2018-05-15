package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object handler) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        ApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(request.getServletContext());
        
        UserVo authUser = context.getBean(UserDao.class).getUser(id, password);
        
        if(authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("authUser", authUser);
        response.sendRedirect(request.getContextPath() + "/main");
        return false;
    }
    
}
