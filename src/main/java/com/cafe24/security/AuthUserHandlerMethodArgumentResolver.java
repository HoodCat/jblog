package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.jblog.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
        // @AuthUser 어노테이션이 붙지 않았을 경우
        if(authUser == null) {
            return false;
        }
        
        // 어노테이션이 붙은 파라미터의 타입이 UserVo 체크
        if(parameter.getParameterType().equals(UserVo.class) == false) {
            return false;
        }
        return true;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter, 
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, 
            WebDataBinderFactory binderFactory) throws Exception {
        
        if(supportsParameter(parameter) == false) {
            return WebArgumentResolver.UNRESOLVED;
        }
        
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();
        if(session == null) {
            return null;
        }
        return session.getAttribute("authUser");
    }

}
