package com.lotour.article.mvc.controller;

import com.lotour.article.cache.IArticleCacher;
import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.searcher.IArticleSearcher;
import com.lotour.article.utility.ParaMapper;
import com.lotour.article.utility.ParaValidater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by ljing on 2015/5/12.
 */
@Controller
@RequestMapping(value="/article")
public class ArticleController
{
    @Resource
    private IArticleSearcher searcher;

    @Resource
    private IArticleCacher cacher;

    @RequestMapping(value="/search", method= RequestMethod.GET)
    @ResponseBody
    public ArticleAbstract[] GetArticleAbstracts(
            @RequestParam(value = "content", required = false)String content,
            @RequestParam(value = "title", required = false)String title,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit,
            @RequestParam(value = "articleId", required = false)Integer articleId,
            @RequestParam(value = "articleType", required = false)Integer articleType,
            @RequestParam(value = "regionId", required = false)Integer regionId,
            @RequestParam(value = "authorId", required = false)Integer authorId,
            @RequestParam(value = "dateLimit", required = false)Integer dateLimit)
    {
        ArticleAbstract queryKey = new ArticleAbstract();
        ArticleAbstract[] result;
        queryKey.setContent(StringUtils.isEmpty(content) ? null : content);
        queryKey.setTitle(StringUtils.isEmpty(title) ? null : title);
        queryKey.setArticleId(articleId == null || articleId < 0
                ? 0 : articleId);
        queryKey.setArticleType(articleType == null
                || !ParaValidater.IsValidArticleType(articleType)
                ? 0 : articleType);
        queryKey.setRegionId(regionId == null || regionId < 0
                ? 0 : regionId);
        queryKey.setAuthorId(authorId == null || authorId < 0
                ? 0 : authorId);
        queryKey.setModifyDate(dateLimit == null
                ? 0 : ParaMapper.DateLimitTypeToLongLimit(dateLimit));
        limit = limit == null || limit <= 0 ? 10 : limit > 100 ? 100 : limit;
        page = page == null || page > 10 || page <= 0 ? 1 : page;
        int maxQuery = limit * 10;
        result = cacher.getSearchedAbstarctsArray(queryKey);
        if (result.length == 0)
        {
            result = searcher.SearchArticle(queryKey, maxQuery);
            cacher.setSearchedAbstarctsArray(queryKey, result);
        }
        return GetArticleAbstractsInPage(result, page, limit);
    }

    /**
     * 分页获取文章摘要数组
     * @param articles 文章摘要数组
     * @param page 页码
     * @param limit 页大小限制
     * @return 文章摘要数组
     */
    protected ArticleAbstract[] GetArticleAbstractsInPage(
            ArticleAbstract[] articles,
            int page,
            int limit)
    {
        if ((page - 1) * limit >= articles.length)
        {
            return new ArticleAbstract[0];
        }
        else
        {
            return Arrays.copyOfRange(articles,
                    (page - 1) * limit,
                    Integer.min(articles.length, page * limit));
        }
    }
}
