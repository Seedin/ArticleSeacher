package com.lotour.article.filter;

import com.lotour.article.entity.ArticleAbstract;

/**
 * Created by ljing on 2015/5/15.
 * ����ժҪ������
 */
public interface IArticleFilter
{
    /**
     * ���ˣ���Ч���·���false����Ч���½�����Ԥ����ת��
     * @param article
     * @return
     */
    boolean filter(ArticleAbstract article);
}
