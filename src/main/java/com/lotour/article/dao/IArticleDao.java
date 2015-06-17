package com.lotour.article.dao;

import com.lotour.article.entity.cms.ArticleInformation;

import java.util.List;

/**
 * Created by ljing on 2015/5/7.
 */
public interface IArticleDao<T>
{
    /**
     * ��ҳ���ȡ����
     * @param page ҳ��
     * @param pageSize ҳ��С
     * @return ����ҳ�����б�
     */
    public List<T> GetArticlesInPage(int page, int pageSize);

    /**
     * ��ȡ��������
     * @return ��������
     */
    public int GetArticlesCount();
}
