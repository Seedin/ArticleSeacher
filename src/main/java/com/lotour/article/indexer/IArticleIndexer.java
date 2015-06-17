package com.lotour.article.indexer;

import com.lotour.article.entity.ArticleAbstract;

/**
 * Created by ljing on 2015/5/4.
 * 文章索引接口
 */
public interface IArticleIndexer
{
    /**
     * 添加文章索引
     * @param article 文章摘要信息
     * @return 是否保存成功
     */
    public boolean AddArticleIndex(ArticleAbstract article);

    /**
     * 删除文章索引
     * @param article 文章摘要信息
     * @return 是否删除成功
     */
    public boolean DeleteArticleIndex(ArticleAbstract article);

    /**
     * 修改文章索引
     * @param article 文章摘要信息
     * @return 是否保存成功
     */
    public boolean UpdateArticleIndex(ArticleAbstract article);

    /**
     * 提交变更
     * @return 是否提交成功
     */
    public boolean CommitChanges();

    /**
     * 回滚变更
     * @return 是否回滚成功
     */
    public boolean RollbackChanges();

    /**
     * 索引合并
     * @return 是否合并成功
     */
    public boolean Merge();

    /**
     * 引擎重置
     * @return
     */
    public boolean Reset();
}
