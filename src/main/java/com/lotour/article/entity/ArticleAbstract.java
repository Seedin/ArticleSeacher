package com.lotour.article.entity;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

/**
 * Created by ljing on 2015/4/29.
 */
@Component
@Scope("prototype")
public class ArticleAbstract
{
    /**
     * 文章ID
     */
    private int articleId;
    /**
     * 文章类型
     */
    private int articleType;
    /**
     * 目的地ID
     */
    private int regionId;
    /**
     * 作者ID
     */
    private int authorId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 修改时间
     */
    private long modifyDate;

    public long getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public int getRegionId()
    {
        return regionId;
    }

    public void setRegionId(int regionId)
    {
        this.regionId = regionId;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(int authorId)
    {
        this.authorId = authorId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getArticleId()
    {
        return articleId;
    }

    public void setArticleId(int articleId)
    {
        this.articleId = articleId;
    }

    public int getArticleType()
    {
        return articleType;
    }

    public void setArticleType(int articleType)
    {
        this.articleType = articleType;
    }
}
