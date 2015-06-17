package com.lotour.article.entity.vms;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ljing on 2015/5/8.
 * 足迹文章实体
 */
@Entity
@Table(name="DMS_Foot")
public class ArticleFoot
{
    /**
     * 足迹文章ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    /**
     * 文章作者
     */
    @Column(name="Author")
    private int authorId;

    /**
     * 文章所属目的地
     */
    @Column(name="RegionId")
    private int regionId;

    /**
     * 文章标题
     */
    @Column(name="Title")
    private String title;

    /**
     * 文章内容
     */
    @Column(name="Content")
    private String content;

    /**
     * 文章最后修改时间
     */
    @Column(name="ModifyTime")
    private Date modifyDate;

    /**
     * 文章当前状态
     */
    @Column(name="States")
    private int status;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(int authorId)
    {
        this.authorId = authorId;
    }

    public int getRegionId()
    {
        return regionId;
    }

    public void setRegionId(int regionId)
    {
        this.regionId = regionId;
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

    public Date getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
