package com.oking.smartX;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by 谢青(oking) on 2017/4/12 0012.
 */
public class SmartXFilter implements Filter {
    private final static Logger LOGGER= LoggerFactory.getLogger(SmartXFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("This is a Start Point!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.info("This is a filter Point!");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

        LOGGER.info("This is a End Point!");
    }
}
