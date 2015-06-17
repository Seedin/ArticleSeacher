package com.lotour.article.entity.cms;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljing on 2015/5/6.
 * 正文页文章实体
 */
@Entity
@Table(name="Cms_Information")
public class ArticleInformation
{
    /**
     * 正文页文章ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="InformationId")
    private int id;

    /**
     * 文章标题
     */
    @Column(name="Title")
    private String title;

    /**
     * 文章正文
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
    @Column(name="Status")
    private int status;

    /**
     * 正文页文章关联目的地列表
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "InformationId", referencedColumnName = "InformationId", insertable = false, updatable = false)
    private List<ArticleInformationRegion> regions = new ArrayList<>();

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public List<ArticleInformationRegion> getRegions()
    {
        return regions;
    }

    public void setRegions(List<ArticleInformationRegion> regions)
    {
        this.regions = regions;
    }
}
