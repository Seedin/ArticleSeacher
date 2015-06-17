package com.lotour.article.entity;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ljing on 2015/5/4.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextEntity.xml"})
public class ArticleEntityTest
{
    @Autowired
    private ArticleAbstract article;

    @Ignore
    @Test
    public void testArticleAbstract()
    {
        System.out.println(article.getAuthorId());
    }
}
