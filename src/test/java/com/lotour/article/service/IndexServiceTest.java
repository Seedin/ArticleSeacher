package com.lotour.article.service;

import com.google.gson.Gson;
import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.indexer.IArticleIndexer;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ljing on 2015/5/7.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextIndexTest.xml"})
public class IndexServiceTest
{
    @Autowired
    private ICmsService cmsService;

    @Autowired
    private IVmsService vmsService;

    @Autowired
    private IArticleIndexer indexer;

    @Ignore
    @Test
    public void testInformationArticleIndex()
    {
        //总数统计
        System.out.println(cmsService.getInformationCount());
        //查询文章摘要
        List<ArticleAbstract> articles = cmsService.getInformationAbstractsInPage(125957, 10);
        Gson gson = new Gson();
        System.out.println(gson.toJson(articles));
        //文章加入索引
        articles.forEach((article) -> indexer.AddArticleIndex(article));
        System.out.println(indexer.CommitChanges());
    }

    @Ignore
    @Test
    public void testFootArticleIndex()
    {
        //总数统计
        System.out.println(vmsService.getFootCount());
        //查询文章摘要
        List<ArticleAbstract> articles = vmsService.getFootAbstractsInPage(62724, 10);
        Gson gson = new Gson();
        System.out.println(gson.toJson(articles));
        //文章加入索引
        articles.forEach((article) -> indexer.AddArticleIndex(article));
        System.out.println(indexer.CommitChanges());
    }
}
