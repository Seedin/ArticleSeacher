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
     * ������д��
     */
    protected IndexWriter writer = null;

    /**
     * ����Ŀ¼·��
     */
    protected String indexDirPath;

    /**
     * MMSeg�ִ�������
     * @param indexPath
     */
    public MMSegArticleIndexer(String indexPath)
    {
        indexDirPath = indexPath;
        Reset();
    }

    /**
     * �����������
     * @param article ����ժҪ��Ϣ
     * @return �Ƿ񱣴�ɹ�
     */
    public boolean AddArticleIndex(ArticleAbstract article)
    {
        //��֯�����ĵ��ֶ�
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
     * ɾ����������
     * @param article ����ժҪ��Ϣ
     * @return �Ƿ�ɾ���ɹ�
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
     * �޸���������
     * @param article ����ժҪ��Ϣ
     * @return �Ƿ񱣴�ɹ�
     */
    public boolean UpdateArticleIndex(ArticleAbstract article)
    {
        //��֯�����ĵ��ֶ�
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
     * �ύ���
     * @return �Ƿ��ύ�ɹ�
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
     * �ع����
     * @return �Ƿ�ع��ɹ�
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
     * ������������������Ŀ¼
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
     * �����ϲ�
     * @return �Ƿ�ϲ��ɹ�
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
     * �ͷ���Դ
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
