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
     * �Ự�����б�
     */
    private List<SessionFactory> sfList = new ArrayList<>();

    /**
     *  ���������WebӦ�÷����Ƴ���û�������ٽ��������ʱ�򱻵��á�
     * @param event �¼�����
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
            System.out.println("ϵͳ�����ƺ����!");
        }
        else
        {
            System.out.println("ϵͳ�����ƺ�δ���!");
        }
    }

    /**
     * ���������WebӦ�÷������ý��������ʱ�򱻵��á�
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
            System.out.println("��ȡӦ�ó���������ʧ��!");
            return;
        }
        System.out.println("ϵͳ�����ʼ�����!");
    }

    /**
     * ����Hibernate���ӳ��ͷ�ʱ���ڴ�й©����
     * @param factory �Ự����
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
