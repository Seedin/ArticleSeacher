package com.lotour.article.indexer;

import com.lotour.article.entity.ArticleAbstract;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ljing on 2015/5/4.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextIndexer.xml"})
public class ArticleIndexerTest
{
    @Autowired
    private IArticleIndexer indexer;

    @Ignore
    @Test
    public void testArticleIndex()
    {
        ArticleAbstract[] articles = new ArticleAbstract[4];
        for (int i = 0; i < articles.length; i++)
        {
            articles[i] = new ArticleAbstract();
            articles[i].setArticleId(i + 1);
            articles[i].setArticleType(i + 1);
            articles[i].setAuthorId(i + 1);
            articles[i].setModifyDate(i + 1);
            articles[i].setRegionId(i + 1);
            articles[i].setTitle("��������" + (i + 1));
        }
        articles[0].setContent("���ꡰ��һ�����ڣ����ٹ�·��������7������С�ͳ�������ߣ����гɱ��Ľ��ͣ���Ч���������ܱ������Լ�������ο����������ӡ��ݲ���ȫͳ�ƣ�����һ��С�����ڼ䣬����Ҫ�������Ӵ��Լ��γ���23089���Σ�92349���˴Σ�ռ��Ҫ�����Ӵ����˴ε�85%���ϡ�");
        articles[1].setContent("ֵ��һ�����5��2�գ�����������Ϊ���ӣ����ն����������������3.5���˵Ĺ���Ҫ�󣬾���һ�������Ӧ�����飬ȷ���οͰ�ȫ����һ���棬��Ա�οʹ��ս�ɽ�������������������������������ﵽ�˵���ط��Ŀ�ġ����⣬������ͨ�����ι�˾��ȫ�е��η�������ʾ��Ϣ�����Ѻ���������ʱ�����·���Ա�����Ρ�");
        articles[2].setContent("С�����ڼ䣬����ʵ�з�Ƭ�������ƣ���Ƭ�쵼����������һ�ߣ���ǰָ�ӡ����������Ρ������һ�߹�����ԱȫԱ�ϰ࣬��ǰ���ڣ����������߶����������θ߷��ջ�������ع�����Ա��Ϊ־Ը�ߵ�һ�߸�λִ�ڣ��ಿ�Ź�ͬŬ��������Э����ȷ���˾�����ͨ����������������á�С�����ڼ䣬�������ڵ������е�ͣ��������ʱͣ�����⣬��ɽ�羰����ί��칫����ͣ����Ҳ���οͿ��ţ����̶ȱ�֤���ο������ˡ�ͣ���¡��½��������ѭ����Ͷ��ʹ�ò��������������ã���Ϊ�ο�����ǰɽ�ͺ�ɽ�ıؾ�֮·�������ѭ������Ч������˲ʱ�����߷塣");
        articles[3].setContent("5��3��,���ߴ�ʡ���ξֻ�Ϥ,���ꡰ��һ��С����,��ʡ�����ۺϷ������������˼���,�����г������������á�С��������,ȫʡ���Ӵ��ο�1157.31���˴�,ͬ������28.79%��ʵ������������46.29��Ԫ,ͬ������29.87%��");

        System.out.println(indexer.AddArticleIndex(articles[0]));
        System.out.println(indexer.AddArticleIndex(articles[1]));
        System.out.println(indexer.CommitChanges());
        System.out.println(indexer.AddArticleIndex(articles[2]));
        System.out.println(indexer.AddArticleIndex(articles[3]));
        System.out.println(indexer.CommitChanges());
    }
}
