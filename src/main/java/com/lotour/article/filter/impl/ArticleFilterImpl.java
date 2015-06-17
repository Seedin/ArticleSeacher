package com.lotour.article.filter.impl;

import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.filter.IArticleFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by ljing on 2015/5/15.
 */
@Component
public class ArticleFilterImpl implements IArticleFilter
{
    /**
     * Html标记正则
     */
    protected String HtmlRex = "<.+?>";

    /**
     * 空字符串
     */
    protected String EmptyString = "";
    /**
     * 过滤，无效文章返回false，有效文章将进行预处理转换
     * @param article 文章摘要信息
     * @return  是否有效
     */
    public boolean filter(ArticleAbstract article)
    {
        //非空检查
        if (article == null ||
            StringUtils.isEmpty(article.getTitle()) ||
            StringUtils.isEmpty(article.getContent()))
        {
            return false;
        }

        //更新过滤后正文内容
        article.setContent(contentFilter(article).toString());

        return true;
    }

    /**
     * 正文过滤
     * @param article 文章摘要信息
     * @return 过滤后正文
     */
    protected StringBuilder contentFilter(ArticleAbstract article)
    {
        //处理正文内容
        StringBuilder newContent = new StringBuilder();

        //利用正则表达式处理正文内容内Html标记
        newContent.append(Pattern.compile(HtmlRex, Pattern.DOTALL)
                .matcher(article.getContent())
                .replaceAll(EmptyString));

        //正文附加标题内容
        newContent.append(article.getTitle());

        return newContent;
    }
}
