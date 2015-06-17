package com.lotour.article.service.impl;

import com.lotour.article.dao.IArticleDao;
import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.entity.ArticleType;
import com.lotour.article.entity.vms.ArticleFoot;
import com.lotour.article.service.IVmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljing on 2015/5/8.
 */
@Service("IVmsService")
public class VmsService implements IVmsService
{
    @Resource()
    protected IArticleDao<ArticleFoot> infoDao;

    /**
     * 分页获取足迹文章摘要
     * @param page  页码
     * @param pageSize  页大小
     * @return  文章摘要列表
     */
    public List<ArticleAbstract> getFootAbstractsInPage(int page,
                                                        int pageSize)
    {
        List<ArticleAbstract> result = FormatArticleAbstract(infoDao.GetArticlesInPage(page, pageSize));;
        return result;
    }

    /**
     * 获取足迹总数
     * @return
     */
    public int getFootCount()
    {
        return infoDao.GetArticlesCount();
    }

    /**
     * 将足迹源列表格式化为文章摘要列表
     * @param sources 源列表
     * @return 文章摘要列表
     */
    protected List<ArticleAbstract> FormatArticleAbstract(List<ArticleFoot> sources)
    {
        List<ArticleAbstract> result = new ArrayList<>();

        for (ArticleFoot source : sources)
        {
            ArticleAbstract article = new ArticleAbstract();
            article.setArticleId(source.getId());
            article.setArticleType(ArticleType.FOOT.ordinal());
            article.setRegionId(source.getRegionId() > 0 ? source.getRegionId() : -1);
            article.setAuthorId(source.getAuthorId());
            article.setModifyDate(source.getModifyDate().getTime());
            article.setTitle(source.getTitle());
            article.setContent(source.getContent());
            result.add(article);
        }

        return result;
    }
}
