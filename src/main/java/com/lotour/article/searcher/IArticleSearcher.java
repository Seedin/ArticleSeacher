package com.lotour.article.searcher;

import com.lotour.article.entity.ArticleAbstract;

/**
 * Created by ljing on 2015/5/4.
 */
public interface IArticleSearcher
{
    /**
     * ��������
     * @param queryKey ��ѯ�ؼ���
     * @return
     */
    public ArticleAbstract[] SearchArticle(ArticleAbstract queryKey, int maxResult);

    /**
     * ˢ�¼���Լ���������
     * @return
     */
    public boolean RefreshIndex();

    /**
     * ��Ч����������ֹͣ�ṩ��������
     * @return
     */
    public boolean DisableIndex();
}
