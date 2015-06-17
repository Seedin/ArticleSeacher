package com.lotour.article.service;

import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.entity.ArticleType;

import java.util.List;

/**
 * Created by ljing on 2015/5/7
 * CMS库访问服务接口.
 */
public interface ICmsService
{
    /**
     * 分页获取正文页文章摘要
     * @param page  页码
     * @param pageSize  页大小
     * @return  文章摘要列表
     */
    public List<ArticleAbstract> getInformationAbstractsInPage(int page,
                                                               int pageSize);

    /**
     * 获取正文页总数
     * @return
     */
    public int getInformationCount();
}
