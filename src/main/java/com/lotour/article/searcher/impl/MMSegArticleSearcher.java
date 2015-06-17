package com.lotour.article.searcher.impl;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.searcher.IArticleSearcher;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ljing on 2015/5/4.
 */
public class MMSegArticleSearcher implements IArticleSearcher
{
    /**
     * 索引读取器
     */
    protected IndexReader reader = null;

    /**
     * 索引查询器
     */
    protected IndexSearcher searcher;

    /**
     * 分词器
     */
    protected Analyzer analyzer;

    /**
     * 索引刷新锁
     */
    protected Lock lock;

    /**
     * 索引目录路径
     */
    protected String indexDirPath;

    /**
     * 构造函数
     * @param indexPath 索引目录路径
     */
    public MMSegArticleSearcher(String indexPath)
    {
        indexDirPath = indexPath;
        analyzer = new MMSegAnalyzer();
        lock = new ReentrantLock();
        if (!RefreshIndex())
        {
            throw new RuntimeException("搜索器初始化失败.");
        }
    }

    /**
     * 搜索文章
     * @param queryKey 查询关键词
     * @param maxResult 最大结果数
     * @return
     */
    public ArticleAbstract[] SearchArticle(ArticleAbstract queryKey, int maxResult)
    {
        ArticleAbstract[] candidates = new ArticleAbstract[0];
        if (reader == null)
        {
            return candidates;
        }
        BooleanQuery booleanQuery = new BooleanQuery();
        int keyCount = 0;
        try
        {
            Query subQuery;
            if (!StringUtils.isEmpty(queryKey.getContent()))
            {
                subQuery = new QueryParser("content", analyzer)
                        .parse(queryKey.getContent());
                booleanQuery.add(subQuery, BooleanClause.Occur.MUST);
                keyCount++;
            }
            if (!StringUtils.isEmpty(queryKey.getTitle()))
            {
                booleanQuery.add(new QueryParser("title", analyzer)
                        .parse(queryKey.getTitle()), BooleanClause.Occur.MUST);
                keyCount++;
            }
            if (queryKey.getArticleId() != 0)
            {
                booleanQuery.add(new TermQuery(new Term("articleId"
                        ,Integer.toString(queryKey.getArticleId())))
                        ,BooleanClause.Occur.MUST);
                keyCount++;
            }
            if (queryKey.getArticleType() != 0)
            {
                booleanQuery.add(new TermQuery(new Term("articleType"
                        ,Integer.toString(queryKey.getArticleType())))
                        ,BooleanClause.Occur.MUST);
                keyCount++;
            }
            if (queryKey.getAuthorId() != 0)
            {
                booleanQuery.add(new TermQuery(new Term("authorId"
                        ,Integer.toString(queryKey.getAuthorId())))
                        ,BooleanClause.Occur.MUST);
                keyCount++;
            }
            if (queryKey.getRegionId() != 0)
            {
                booleanQuery.add(new TermQuery(new Term("regionId"
                        ,Integer.toString(queryKey.getRegionId())))
                        ,BooleanClause.Occur.MUST);
                keyCount++;
            }
            if (queryKey.getModifyDate() != 0)
            {
                booleanQuery.add(NumericRangeQuery.newLongRange("modifyDate"
                        , queryKey.getModifyDate(), new java.util.Date().getTime(), true, true)
                        , BooleanClause.Occur.MUST);
                keyCount++;
            }

            //空查询条件，无需继续查询
            if (keyCount == 0)
            {
                return candidates;
            }

            //以修改时间降序作为排序顺序
            Sort sort = new Sort(new SortField("title", SortField.Type.SCORE),
                    new SortField("content", SortField.Type.SCORE),
                    new SortedNumericSortField("modifyDate", SortField.Type.LONG, true));

            TopDocs results = searcher.search(booleanQuery, Math.max(maxResult, 1), sort);

            int hitSize = Math.min(results.totalHits, maxResult);
            if (hitSize > 0)
            {
                candidates = new ArticleAbstract[hitSize];
                for (int i = 0; i < hitSize; i++)
                {
                    candidates[i] = ExtractArticleAbstractFromIndex(searcher.doc(results.scoreDocs[i].doc));
                }
            }
        }
        catch (ParseException err)
        {
            return candidates;
        }
        catch (IOException err)
        {
            return candidates;
        }
        return candidates;
    }

    /**
     * 从索引文档中提取文章摘要
     * @param doc 索引文档
     * @return 文章摘要
     */
    protected ArticleAbstract ExtractArticleAbstractFromIndex(Document doc)
    {
        if (doc == null)
        {
            return  null;
        }
        ArticleAbstract candidate = new ArticleAbstract();
        try
        {
            candidate.setArticleId(Integer.parseInt(doc.get("articleId")));
            candidate.setArticleType(Integer.parseInt(doc.get("articleType")));
            candidate.setAuthorId(Integer.parseInt(doc.get("authorId")));
            candidate.setRegionId(Integer.parseInt(doc.get("regionId")));
            candidate.setModifyDate(Long.parseLong(doc.get("modifyDate")));
            candidate.setTitle(doc.get("title"));
            candidate.setContent("");
        }
        catch(NumberFormatException err)
        {
            return  null;
        }
        return candidate;
    }

    /**
     * 刷新检查以加载新索引
     * @return
     */
    public boolean RefreshIndex()
    {
        try
        {
            if (lock.tryLock(10, TimeUnit.SECONDS))
            {
                try
                {
                    if (reader != null)
                    {
                        reader.close();
                    }
                    reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDirPath)));
                    searcher = new IndexSearcher(reader);
                }
                catch (Exception err)
                {
                    return false;
                }
                finally
                {
                    lock.unlock();
                }
            }
            else
            {
                return false;
            }
        }
        catch (InterruptedException err)
        {
            return false;
        }

        return true;
    }

    /**
     * 无效现有索引并停止提供搜索服务
     * @return
     */
    public boolean DisableIndex()
    {
        try
        {
            if (lock.tryLock(10, TimeUnit.SECONDS))
            {
                try
                {
                    if (reader != null)
                    {
                        reader.close();
                        reader = null;
                    }
                }
                catch (Exception err)
                {
                    return false;
                }
                finally
                {
                    lock.unlock();
                }
            }
            else
            {
                return false;
            }
        }
        catch (InterruptedException err)
        {
            return false;
        }

        return true;
    }
}
