package com.lotour.article.searcher;

import com.lotour.article.entity.ArticleAbstract;

/**
 * Created by ljing on 2015/5/4.
 */
public interface IArticleSearcher
{
    /**
     * 搜索文章
     * @param queryKey 查询关键词
     * @return
     */
    public ArticleAbstract[] SearchArticle(ArticleAbstract queryKey, int maxResult);

    /**
     * 刷新检查以加载新索引
     * @return
     */
    public boolean RefreshIndex();

    /**
     * 无效现有索引并停止提供搜索服务
     * @return
     */
    public boolean DisableIndex();
}
