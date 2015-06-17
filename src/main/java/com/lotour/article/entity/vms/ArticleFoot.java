package com.lotour.article.entity.vms;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ljing on 2015/5/8.
 * �㼣����ʵ��
 */
@Entity
@Table(name="DMS_Foot")
public class ArticleFoot
{
    /**
     * �㼣����ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;

    /**
     * ��������
     */
    @Column(name="Author")
    private int authorId;

    /**
     * ��������Ŀ�ĵ�
     */
    @Column(name="RegionId")
    private int regionId;

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
