package com.lotour.article.indexer;

import com.lotour.article.entity.ArticleAbstract;

/**
 * Created by ljing on 2015/5/4.
 * ���������ӿ�
 */
public interface IArticleIndexer
{
    /**
     * �����������
     * @param article ����ժҪ��Ϣ
     * @return �Ƿ񱣴�ɹ�
     */
    public boolean AddArticleIndex(ArticleAbstract article);

    /**
     * ɾ����������
     * @param article ����ժҪ��Ϣ
     * @return �Ƿ�ɾ���ɹ�
     */
    public boolean DeleteArticleIndex(ArticleAbstract article);

    /**
     * �޸���������
     * @param article ����ժҪ��Ϣ
     * @return �Ƿ񱣴�ɹ�
     */
    public boolean UpdateArticleIndex(ArticleAbstract article);

    /**
     * �ύ���
     * @return �Ƿ��ύ�ɹ�
     */
    public boolean CommitChanges();

    /**
     * �ع����
     * @return �Ƿ�ع��ɹ�
     */
    public boolean RollbackChanges();

    /**
     * �����ϲ�
     * @return �Ƿ�ϲ��ɹ�
     */
    public boolean Merge();

    /**
     * ��������
     * @return
     */
    public boolean Reset();
}
