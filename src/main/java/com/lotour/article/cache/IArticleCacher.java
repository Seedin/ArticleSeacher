package com.lotour.article.cache;

import com.lotour.article.entity.ArticleAbstract;

import java.util.List;

/**
 * Created by ljing on 2015/5/18.
 */
public interface IArticleCacher extends IBasicCache<ArticleAbstract>
{
    /**
     * 保存已搜索文章摘要至缓存
     * @param searchedKey 搜索Key
     * @param abstarcts 搜索结果
     * @return 是否缓存成功
     */
    boolean setSearchedAbstarctsList(ArticleAbstract searchedKey, List<ArticleAbstract> abstarcts);

    /**
     * 保存已搜索文章摘要至缓存
     * @param searchedKey 搜索Key
     * @param abstarcts 搜索结果
     * @return 是否缓存成功
     */
    boolean setSearchedAbstarctsArray(ArticleAbstract searchedKey, ArticleAbstract[] abstarcts);

    /**
     * 自缓存获取已搜索文章摘要
     * @param searchedKey 搜索Key
     * @return 搜索结果
     */
    List<ArticleAbstract> getSearchedAbstarctsList(ArticleAbstract searchedKey);

    /**
     * 自缓存获取已搜索文章摘要
     * @param searchedKey 搜索Key
     * @return 搜索结果
     */
    ArticleAbstract[] getSearchedAbstarctsArray(ArticleAbstract searchedKey);
}
