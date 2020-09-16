package com.htzw.filter.filter2;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

/**
 * 自定义过滤器（注解方式）
 *
 * @author Administrator
 */
@Slf4j
@Order(1)
@WebFilter(filterName = "myFilter", urlPatterns = {"/aa/*", "/bb/*"},  description = "自定义过滤器")
public class MyCustomFilter2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("MyCustomFilter2 过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyCustomFilter2 before ...");
        // 过滤器放行
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("MyCustomFilter2 after ...");
    }

    @Override
    public void destroy() {
        log.error("MyCustomFilter2 过滤器销毁");
    }
}
