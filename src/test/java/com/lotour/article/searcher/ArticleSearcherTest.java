package com.lotour.article.searcher;

import com.lotour.article.entity.ArticleAbstract;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.google.gson.Gson;

/**
 * Created by ljing on 2015/5/5.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextSearcher.xml"})
public class ArticleSearcherTest
{
    @Autowired
    private IArticleSearcher searcher;

    @Ignore
    @Test
    public void testArticleSearch()
    {
        ArticleAbstract queryKey = new ArticleAbstract();
//        queryKey.setContent("ŒÂ“ª");
        queryKey.setTitle("≤‚ ‘");
//        queryKey.setArticleId(1);
//        queryKey.setModifyDate(4);
        int maxSize = 10;
        ArticleAbstract[] articles = searcher.SearchArticle(queryKey, maxSize);
        Gson gson = new Gson();
        System.out.println(gson.toJson(articles));
    }
}
