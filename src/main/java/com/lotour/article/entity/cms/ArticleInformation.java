package com.lotour.article.entity.cms;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ljing on 2015/5/6.
 * ����ҳ����ʵ��
 */
@Entity
@Table(name="Cms_Information")
public class ArticleInformation
{
    /**
     * ����ҳ����ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="InformationId")
    private int id;

    /**
     * ���±���
     */
    @Column(name="Title")
    private String title;

    /**
     * ��������
     */
    @Column(name="Content")
    private String content;

    /**
     * ��������޸�ʱ��
     */
    @Column(name="ModifyTime")
    private Date modifyDate;

    /**
     * ���µ�ǰ״̬
     */
    @Column(name="Status")
    private int status;

    /**
     * ����ҳ���¹���Ŀ�ĵ��б�
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
