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
 * 索引任务.
 */
@Component
public class IndexTask
{
    /**
     * cms数据服务
     */
    @Autowired
    private ICmsService cmsService;

    /**
     * vms数据服务
     */
    @Autowired
    private IVmsService vmsService;

    /**
     * 索引服务
     */
    @Autowired
    private IArticleIndexer indexer;

    /**
     * 索引服务
     */
    @Autowired
    private IArticleSearcher searcher;

    /**
     * 过滤服务
     */
    @Autowired
    private IArticleFilter filter;

    /**
     * 索引路径
     */
    private String indexPath;

    /**
     * 搜索路径
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
     * 页大小：1000
     */
    public static final int PAGE_SIZE = 1000;

    /**
     * 索引正文页文章
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
     * 索引足迹文章
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
     * 将索引路径应用至搜索路径
     */
    void applyIndexToSearch()
    {
        searcher.DisableIndex();
        try
        {
            //初始化路径
            File indexDir = new File(indexPath);
            File searchDir = new File(searchPath);
            //删除搜索路径文件
            for (File file : searchDir.listFiles())
            {
                FileUtils.deleteQuietly(file);
            }
            //复制索引路径文件至搜索路径
            FileUtils.copyDirectory(indexDir,searchDir);
        }
        catch (IOException err)
        {
            System.out.println("");
        }
        searcher.RefreshIndex();
    }

    /**
     * 重置索引，当前索引会被新索引覆盖
     */
    public void resetIndex()
    {
        indexer.Reset();
    }

    /**
     * 索引工作
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
