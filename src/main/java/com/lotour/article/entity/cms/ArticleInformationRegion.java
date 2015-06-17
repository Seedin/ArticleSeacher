package com.lotour.article.entity.cms;

import javax.persistence.*;

/**
 * Created by ljing on 2015/5/6.
 * 正文页文章目的地关联实体
 */
@Entity
@Table(name="Cms_InformationRegion")
public class ArticleInformationRegion
{
    /**
     * 目的地关联Id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private  int id;

    /**
     * 正文页文章ID
     */
    @Column(name="InformationId")
    private int informationId;

    /**
     * 目的地Id
     */
    @Column(name="RegionId")
    private int regionId;

    /**
     * 关联正文页文章
     */
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "InformationId", referencedColumnName = "InformationId", insertable = false, updatable = false)
    private ArticleInformation information;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getInformationId()
    {
        return informationId;
    }

    public void setInformationId(int informationId)
    {
        this.informationId = informationId;
    }

    public int getRegionId()
    {
        return regionId;
    }

    public void setRegionId(int regionId)
    {
        this.regionId = regionId;
    }

    public ArticleInformation getInformation()
    {
        return information;
    }

    public void setInformation(ArticleInformation information)
    {
        this.information = information;
    }
}
