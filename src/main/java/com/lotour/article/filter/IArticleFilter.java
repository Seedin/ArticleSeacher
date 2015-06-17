package com.lotour.article.filter;

import com.lotour.article.entity.ArticleAbstract;

/**
 * Created by ljing on 2015/5/15.
 * 文章摘要过滤器
 */
public interface IArticleFilter
{
    /**
     * 过滤，无效文章返回false，有效文章将进行预处理转换
     * @param article
     * @return
     */
    boolean filter(ArticleAbstract article);
}
