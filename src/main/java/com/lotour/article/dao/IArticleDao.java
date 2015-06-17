package com.lotour.article.dao;

import com.lotour.article.entity.cms.ArticleInformation;

import java.util.List;

/**
 * Created by ljing on 2015/5/7.
 */
public interface IArticleDao<T>
{
    /**
     * 按页码获取文章
     * @param page 页码
     * @param pageSize 页大小
     * @return 正文页文章列表
     */
    public List<T> GetArticlesInPage(int page, int pageSize);

    /**
     * 获取文章总数
     * @return 文章总数
     */
    public int GetArticlesCount();
}
