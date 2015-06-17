package com.lotour.article.service;

import com.google.gson.Gson;
import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.entity.ArticleType;
import com.lotour.article.searcher.IArticleSearcher;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ljing on 2015/5/8.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextSearchTest.xml"})
public class SearchServiceTest
{
    @Autowired
    private IArticleSearcher searcher;

    @Ignore
    @Test
    public void testInformationArticleSearch()
    {
        //≤È—ØŒƒ’¬
        Gson gson = new Gson();
        ArticleAbstract queryKey = new ArticleAbstract();
        queryKey.setContent("œ„…Ω∫Ï“∂");
        queryKey.setArticleType(ArticleType.FOOT.ordinal());
        ArticleAbstract[] queryResults = searcher.SearchArticle(queryKey, 10);
        System.out.println(gson.toJson(queryResults));
    }
}
