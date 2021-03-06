package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object handler) throws Exception {
        
        if(handler instanceof HandlerMethod == false) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        
        System.out.println("PreHandle" + auth);
        
        if(auth==null) {
            return true;
        }
        
        HttpSession session = request.getSession();
        
        if(session == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
        
        UserVo authUser = (UserVo)session.getAttribute("authUser"); 
        if(authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
        
        return true;
    }
    
}
