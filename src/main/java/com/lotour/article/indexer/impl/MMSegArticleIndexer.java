package com.lotour.article.indexer.impl;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;
import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.indexer.IArticleIndexer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by ljing on 2015/54.
 */
@Component
public class MMSegArticleIndexer implements IArticleIndexer
{
    /**
     * 索引书写器
     */
    protected IndexWriter writer = null;

    /**
     * 索引目录路径
     */
    protected String indexDirPath;

    /**
     * MMSeg分词索引器
     * @param indexPath
     */
    public MMSegArticleIndexer(String indexPath)
    {
        indexDirPath = indexPath;
        Reset();
    }

    /**
     * 添加文章索引
     * @param article 文章摘要信息
     * @return 是否保存成功
     */
    public boolean AddArticleIndex(ArticleAbstract article)
    {
        //组织索引文档字段
        Document doc = new Document();
        try
        {
            doc.add(new StringField("articleId", Integer.toString(article.getArticleId()), Field.Store.YES));
            doc.add(new StringField("articleType", Integer.toString(article.getArticleType()), Field.Store.YES));
            doc.add(new StringField("authorId", Integer.toString(article.getAuthorId()), Field.Store.YES));
            doc.add(new StringField("regionId", Integer.toString(article.getRegionId()), Field.Store.YES));
            doc.add(new LongField("modifyDate", article.getModifyDate(), Field.Store.YES));
            doc.add(new SortedNumericDocValuesField("modifyDate", article.getModifyDate()));
            doc.add(new TextField("title", article.getTitle(), Field.Store.YES));
            doc.add(new TextField("content", article.getContent(), Field.Store.NO));
        }
        catch (Exception err)
        {
            return false;
        }

        try
        {
            writer.addDocument(doc);
        }
        catch (IOException err)
        {
            return false;
        }
        return true;
    }

    /**
     * 删除文章索引
     * @param article 文章摘要信息
     * @return 是否删除成功
     */
    public boolean DeleteArticleIndex(ArticleAbstract article)
    {
        try
        {
            writer.deleteDocuments(new Term("articleId", Integer.toString(article.getArticleId())));
        }
        catch (IOException err)
        {
            return false;
        }
        return true;
    }

    /**
     * 修改文章索引
     * @param article 文章摘要信息
     * @return 是否保存成功
     */
    public boolean UpdateArticleIndex(ArticleAbstract article)
    {
        //组织索引文档字段
        Document doc = new Document();
        doc.add(new IntField("articleId", article.getArticleId(), Field.Store.YES));
        doc.add(new IntField("articleType", article.getArticleType(), Field.Store.YES));
        doc.add(new IntField("authorId", article.getAuthorId(), Field.Store.YES));
        doc.add(new IntField("regionId", article.getRegionId(), Field.Store.YES));
        doc.add(new LongField("modifyDate", article.getModifyDate(), Field.Store.YES));
        doc.add(new StringField("title", article.getTitle(), Field.Store.YES));
        doc.add(new TextField("contents", article.getContent(), Field.Store.NO));

        try
        {
            writer.updateDocument(new Term("articleId", Integer.toString(article.getArticleId())), doc);
        }
        catch (IOException err)
        {
            return false;
        }
        return true;
    }

    /**
     * 提交变更
     * @return 是否提交成功
     */
    public boolean CommitChanges()
    {
        try
        {
            writer.commit();
        }
        catch (IOException err)
        {
            return false;
        }
        return true;
    }

    /**
     * 回滚变更
     * @return 是否回滚成功
     */
    public boolean RollbackChanges()
    {
        try
        {
            writer.rollback();
        }
        catch (IOException err)
        {
            return false;
        }
        return true;
    }

    /**
     * 析构函数，处理所用目录
     */
    protected void finalize()
    {
        if (writer != null)
        {
            try
            {
                writer.close();
                writer = null;
                super.finalize();
            }
            catch (Throwable e)
            {
            }
        }
    }

    /**
     * 索引合并
     * @return 是否合并成功
     */
    public boolean Merge()
    {
        try
        {
            writer.forceMerge(1);
        }
        catch (IOException err)
        {
            return false;
        }
        return true;
    }

    /**
     * 释放资源
     * @return
     */
    public boolean Reset()
    {
        try
        {
            if (writer != null)
            {
                writer.close();
                writer = null;
            }
            Directory dir = FSDirectory.open(Paths.get(indexDirPath));
            Analyzer analyzer = new MMSegAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            writer = new IndexWriter(dir, iwc);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
