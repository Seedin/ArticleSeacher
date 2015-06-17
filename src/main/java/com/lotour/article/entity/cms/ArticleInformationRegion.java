package com.lotour.article.entity.cms;

import javax.persistence.*;

/**
 * Created by ljing on 2015/5/6.
 * ����ҳ����Ŀ�ĵع���ʵ��
 */
@Entity
@Table(name="Cms_InformationRegion")
public class ArticleInformationRegion
{
    /**
     * Ŀ�ĵع���Id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private  int id;

    /**
     * ����ҳ����ID
     */
    @Column(name="InformationId")
    private int informationId;

    /**
     * Ŀ�ĵ�Id
     */
    @Column(name="RegionId")
    private int regionId;

    /**
     * ��������ҳ����
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
