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
     * ��ҳ��ȡ�㼣����ժҪ
     * @param page  ҳ��
     * @param pageSize  ҳ��С
     * @return  ����ժҪ�б�
     */
    public List<ArticleAbstract> getFootAbstractsInPage(int page,
                                                        int pageSize)
    {
        List<ArticleAbstract> result = FormatArticleAbstract(infoDao.GetArticlesInPage(page, pageSize));;
        return result;
    }

    /**
     * ��ȡ�㼣����
     * @return
     */
    public int getFootCount()
    {
        return infoDao.GetArticlesCount();
    }

    /**
     * ���㼣Դ�б��ʽ��Ϊ����ժҪ�б�
     * @param sources Դ�б�
     * @return ����ժҪ�б�
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
