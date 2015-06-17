package com.lotour.article.task;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ljing on 2015/5/8.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextTask.xml"})
public class ArticleTaskTest
{
    @Ignore
    @Test
    public void testIndexTask()
    {
        try
        {
            Thread.sleep(36000000);
            System.out.println("结束测试");
        }
        catch (InterruptedException err)
        {
            System.out.println("提前结束测试");
        }
    }
}
