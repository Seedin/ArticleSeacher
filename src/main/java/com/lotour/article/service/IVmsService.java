package com.lotour.article.service;

import com.lotour.article.entity.ArticleAbstract;

import java.util.List;

/**
 * Created by ljing on 2015/5/8.
 * VMS����ʷ���ӿ�
 */
public interface IVmsService
{
    /**
     * ��ҳ��ȡ�㼣����ժҪ
     * @param page  ҳ��
     * @param pageSize  ҳ��С
     * @return  ����ժҪ�б�
     */
    public List<ArticleAbstract> getFootAbstractsInPage(int page,
                                                        int pageSize);

    /**
     * ��ȡ�㼣����
     * @return
     */
    public int getFootCount();
}
