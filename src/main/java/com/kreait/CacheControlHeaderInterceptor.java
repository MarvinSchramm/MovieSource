package com.kreait;


import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CacheControlHeaderInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            final CacheControl methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(CacheControl.class);
            if (methodAnnotation != null) {

                System.out.printf("das");
                response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=" + methodAnnotation.maxage());
            }
        }
    }
}
