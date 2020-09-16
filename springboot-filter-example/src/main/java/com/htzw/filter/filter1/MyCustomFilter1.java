package com.htzw.filter.filter1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

/**
 * 自定义过滤器（配置 Bean 方式）
 * @author Administrator
 */
@Slf4j
public class MyCustomFilter1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("MyCustomFilter1 过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("MyCustomFilter1  before ...");
        // 过滤器放行
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("MyCustomFilter1  after ...");
    }

    @Override
    public void destroy() {
        log.error("MyCustomFilter1  过滤器销毁");
    }
}
