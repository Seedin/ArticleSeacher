package com.lotour.article.service.impl;

import com.lotour.article.dao.IArticleDao;
import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.entity.ArticleType;
import com.lotour.article.entity.cms.ArticleInformation;
import com.lotour.article.service.ICmsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljing on 2015/5/7.
 *  CMS����ʷ���
 */
@Service("ICmsService")
public class CmsService implements ICmsService
{
    @Resource()
    protected IArticleDao<ArticleInformation> infoDao;

    /**
     * ��ҳ��ȡ����ժҪ
     * @param page  ҳ��
     * @param pageSize  ҳ��С
     * @return  ����ժҪ�б�
     */
    public List<ArticleAbstract> getInformationAbstractsInPage(int page,
                                                               int pageSize)
    {
        List<ArticleAbstract> result = FormatArticleAbstract(infoDao.GetArticlesInPage(page, pageSize));;
        return result;
    }

    /**
     * ��ȡ����ҳ����
     * @return
     */
    public int getInformationCount()
    {
        return infoDao.GetArticlesCount();
    }

    /**
     * ������ҳԴ�б��ʽ��Ϊ����ժҪ�б�
     * @param sources Դ�б�
     * @return ����ժҪ�б�
     */
    protected List<ArticleAbstract> FormatArticleAbstract(List<ArticleInformation> sources)
    {
        List<ArticleAbstract> result = new ArrayList<>();

        for (ArticleInformation source : sources)
        {
            ArticleAbstract article = new ArticleAbstract();
            article.setArticleId(source.getId());
            article.setArticleType(ArticleType.INFORMATION.ordinal());
            article.setRegionId(source.getRegions().size() > 0 ? source.getRegions().get(0).getRegionId() : -1);
            article.setAuthorId(-1);
            article.setModifyDate(source.getModifyDate().getTime());
            article.setTitle(source.getTitle());
            article.setContent(source.getContent());
            result.add(article);
        }

        return result;
    }
}
