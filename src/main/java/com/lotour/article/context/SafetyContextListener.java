package com.lotour.article.context;

import org.hibernate.SessionFactory;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljing on 2015/6/1.
 */
public class SafetyContextListener implements ServletContextListener
{
    /**
     * 会话工厂列表
     */
    private List<SessionFactory> sfList = new ArrayList<>();

    /**
     *  这个方法在Web应用服务被移除，没有能力再接受请求的时候被调用。
     * @param event 事件参数
     */
    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        boolean success = true;
        if (sfList.size() > 0)
        {
            sfList.forEach(sf -> closeSessionFactory(sf));
        }
        else
        {
            success = false;
        }
        if (success)
        {
            System.out.println("系统服务善后完成!");
        }
        else
        {
            System.out.println("系统服务善后未完成!");
        }
    }

    /**
     * 这个方法在Web应用服务做好接受请求的时候被调用。
     * @param event
     */
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        if (springContext != null)
        {
            sfList.add((SessionFactory)springContext.getBean("sessionFactoryCms"));
            sfList.add((SessionFactory)springContext.getBean("sessionFactoryVms"));
        } else
        {
            System.out.println("获取应用程序上下文失败!");
            return;
        }
        System.out.println("系统服务初始化完成!");
    }

    /**
     * 处理Hibernate连接池释放时的内存泄漏问题
     * @param factory 会话工厂
     */
    private void closeSessionFactory(SessionFactory factory) {
        if(factory instanceof SessionFactoryImpl) {
            SessionFactoryImpl sf = (SessionFactoryImpl)factory;
            ConnectionProvider conn = sf.getConnectionProvider();
            if(conn instanceof C3P0ConnectionProvider) {
                ((C3P0ConnectionProvider)conn).stop();
            }
        }
        factory.close();
    }
}
