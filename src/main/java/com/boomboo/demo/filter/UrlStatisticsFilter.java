package com.boomboo.demo.filter;


import com.boomboo.demo.handler.UrlStatisticsHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author dapeng
 */
@Component
@Order(1)
public class UrlStatisticsFilter implements Filter {

    @Resource
    UrlStatisticsHandler handler;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        handler.statistics(httpServletRequest);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
