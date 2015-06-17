package com.lotour.article.cache;

import com.lotour.article.entity.ArticleAbstract;

import java.util.List;

/**
 * Created by ljing on 2015/5/18.
 */
public interface IArticleCacher extends IBasicCache<ArticleAbstract>
{
    /**
     * ��������������ժҪ������
     * @param searchedKey ����Key
     * @param abstarcts �������
     * @return �Ƿ񻺴�ɹ�
     */
    boolean setSearchedAbstarctsList(ArticleAbstract searchedKey, List<ArticleAbstract> abstarcts);

    /**
     * ��������������ժҪ������
     * @param searchedKey ����Key
     * @param abstarcts �������
     * @return �Ƿ񻺴�ɹ�
     */
    boolean setSearchedAbstarctsArray(ArticleAbstract searchedKey, ArticleAbstract[] abstarcts);

    /**
     * �Ի����ȡ����������ժҪ
     * @param searchedKey ����Key
     * @return �������
     */
    List<ArticleAbstract> getSearchedAbstarctsList(ArticleAbstract searchedKey);

    /**
     * �Ի����ȡ����������ժҪ
     * @param searchedKey ����Key
     * @return �������
     */
    ArticleAbstract[] getSearchedAbstarctsArray(ArticleAbstract searchedKey);
}
