package com.lotour.article.service;

import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.entity.ArticleType;

import java.util.List;

/**
 * Created by ljing on 2015/5/7
 * CMS����ʷ���ӿ�.
 */
public interface ICmsService
{
    /**
     * ��ҳ��ȡ����ҳ����ժҪ
     * @param page  ҳ��
     * @param pageSize  ҳ��С
     * @return  ����ժҪ�б�
     */
    public List<ArticleAbstract> getInformationAbstractsInPage(int page,
                                                               int pageSize);

    /**
     * ��ȡ����ҳ����
     * @return
     */
    public int getInformationCount();
}
