package com.ayding.system.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/13
 * @Description:
 */
@Component
@Slf4j
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("<=====================过滤器MyFilter:doFilter==============================>");
        log.info("<=====================过滤器MyFilter:doFilter:requestUrl:{}==============================>",request.getRequestURI());
        //执行
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("<=====================过滤器MyFilter:doFilter:response==============================>");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("<=====================过滤器MyFilter:init=============================>");
    }

    @Override
    public void destroy() {
        log.info("<=====================过滤器MyFilter:destroy=============================>");
    }
}
