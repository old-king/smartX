package com.oking.smartX;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 谢青(oking) on 2017/4/12 0012.
 */
public class DispatcherServlet  extends HttpServlet{
    private final static Logger LOGGER= LoggerFactory.getLogger(DispatcherServlet.class);

    /**
     * 初始化配置信息
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        LOGGER.info("  The smartX Framework start to load configuration by class:  {}",config.getServletName());
        //加载controller注解和请求地址配置
        ControllerFactory.init("com.oking.smartX");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

       String requestMethod= request.getMethod();
       String requestPath=request.getServletPath()+request.getPathInfo();

        Route route= HandlerMapping.getHandle(requestPath,requestMethod);

        if(null==route){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HandlerInvoker.invokeHandler(request,response,route);
    }

}
