package com.lotour.article.task;

import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.filter.IArticleFilter;
import com.lotour.article.indexer.IArticleIndexer;
import com.lotour.article.searcher.IArticleSearcher;
import com.lotour.article.service.ICmsService;
import com.lotour.article.service.IVmsService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by ljing on 2015/5/8
 * ��������.
 */
@Component
public class IndexTask
{
    /**
     * cms���ݷ���
     */
    @Autowired
    private ICmsService cmsService;

    /**
     * vms���ݷ���
     */
    @Autowired
    private IVmsService vmsService;

    /**
     * ��������
     */
    @Autowired
    private IArticleIndexer indexer;

    /**
     * ��������
     */
    @Autowired
    private IArticleSearcher searcher;

    /**
     * ���˷���
     */
    @Autowired
    private IArticleFilter filter;

    /**
     * ����·��
     */
    private String indexPath;

    /**
     * ����·��
     */
    private String searchPath;

    public String getIndexPath()
    {
        return indexPath;
    }

    public void setIndexPath(String indexPath)
    {
        this.indexPath = indexPath;
    }

    public String getSearchPath()
    {
        return searchPath;
    }

    public void setSearchPath(String searchPath)
    {
        this.searchPath = searchPath;
    }

    /**
     * ҳ��С��1000
     */
    public static final int PAGE_SIZE = 1000;

    /**
     * ��������ҳ����
     */
    void indexArticleInformations(int articleLimit)
    {
        int articleCount = cmsService.getInformationCount();
        int totalPage = articleCount % PAGE_SIZE == 0 ?
                articleCount / PAGE_SIZE :
                articleCount / PAGE_SIZE + 1;
        List<ArticleAbstract> articles;
        int indexCount = 0;
        for (int i = 1; i <= totalPage; i++)
        {
            articles = cmsService.getInformationAbstractsInPage(i, PAGE_SIZE);
            articles.stream().filter((article) -> filter.filter(article))
                    .forEach((article) -> indexer.AddArticleIndex(article));
            indexer.CommitChanges();
            indexCount += articles.size();
            if (articleLimit > 0 && articleLimit <= indexCount)
            {
                return;
            }
        }
    }

    /**
     * �����㼣����
     */
    void indexArticleFoots(int articleLimit)
    {
        int articleCount = vmsService.getFootCount();
        int totalPage = articleCount % PAGE_SIZE == 0 ?
                articleCount / PAGE_SIZE :
                articleCount / PAGE_SIZE + 1;
        List<ArticleAbstract> articles;
        int indexCount = 0;
        for (int i = 1; i <= totalPage; i++)
        {
            articles = vmsService.getFootAbstractsInPage(i, PAGE_SIZE);
            articles.forEach((article) -> indexer.AddArticleIndex(article));
            indexer.CommitChanges();
            indexCount += articles.size();
            if (articleLimit > 0 && articleLimit <= indexCount)
            {
                return;
            }
        }
    }

    /**
     * ������·��Ӧ��������·��
     */
    void applyIndexToSearch()
    {
        searcher.DisableIndex();
        try
        {
            //��ʼ��·��
            File indexDir = new File(indexPath);
            File searchDir = new File(searchPath);
            //ɾ������·���ļ�
            for (File file : searchDir.listFiles())
            {
                FileUtils.deleteQuietly(file);
            }
            //��������·���ļ�������·��
            FileUtils.copyDirectory(indexDir,searchDir);
        }
        catch (IOException err)
        {
            System.out.println("");
        }
        searcher.RefreshIndex();
    }

    /**
     * ������������ǰ�����ᱻ����������
     */
    public void resetIndex()
    {
        indexer.Reset();
    }

    /**
     * ��������
     */
    @Scheduled(cron = "0 0 0 * * ?")
    void index()
    {
        indexArticleInformations(0);
        indexArticleFoots(0);
        applyIndexToSearch();
        resetIndex();
    }
}
