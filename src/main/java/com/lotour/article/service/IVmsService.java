package com.lotour.article.service;

import com.lotour.article.entity.ArticleAbstract;

import java.util.List;

/**
 * Created by ljing on 2015/5/8.
 * VMS库访问服务接口
 */
public interface IVmsService
{
    /**
     * 分页获取足迹文章摘要
     * @param page  页码
     * @param pageSize  页大小
     * @return  文章摘要列表
     */
    public List<ArticleAbstract> getFootAbstractsInPage(int page,
                                                        int pageSize);

    /**
     * 获取足迹总数
     * @return
     */
    public int getFootCount();
}
