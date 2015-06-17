package com.lotour.article.cache;

import com.google.gson.Gson;
import com.lotour.article.entity.ArticleAbstract;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljing on 2015/5/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextRedis.xml"})
public class ArticleCacheTest
{
    @Autowired
    private IArticleCacher cache;

    @Ignore
    @Test
    public void testCacheSetList()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        List<ArticleAbstract> articles = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            ArticleAbstract article = new ArticleAbstract();
            article.setArticleId(i + 1);
            article.setArticleType(i + 1);
            article.setAuthorId(i + 1);
            article.setModifyDate(i + 1);
            article.setRegionId(i + 1);
            article.setTitle("��������" + (i + 1));
            articles.add(article);
            switch (i)
            {
                case 0:
                    article.setContent("���ꡰ��һ�����ڣ����ٹ�·��������7������С�ͳ�������ߣ����гɱ��Ľ��ͣ���Ч���������ܱ������Լ�������ο����������ӡ��ݲ���ȫͳ�ƣ�����һ��С�����ڼ䣬����Ҫ�������Ӵ��Լ��γ���23089���Σ�92349���˴Σ�ռ��Ҫ�����Ӵ����˴ε�85%���ϡ�");
                    break;
                case 1:
                    article.setContent("ֵ��һ�����5��2�գ�����������Ϊ���ӣ����ն����������������3.5���˵Ĺ���Ҫ�󣬾���һ�������Ӧ�����飬ȷ���οͰ�ȫ����һ���棬��Ա�οʹ��ս�ɽ�������������������������������ﵽ�˵���ط��Ŀ�ġ����⣬������ͨ�����ι�˾��ȫ�е��η�������ʾ��Ϣ�����Ѻ���������ʱ�����·���Ա�����Ρ�");
                    break;
                case 2:
                    article.setContent("С�����ڼ䣬����ʵ�з�Ƭ�������ƣ���Ƭ�쵼����������һ�ߣ���ǰָ�ӡ����������Ρ������һ�߹�����ԱȫԱ�ϰ࣬��ǰ���ڣ����������߶����������θ߷��ջ�������ع�����Ա��Ϊ־Ը�ߵ�һ�߸�λִ�ڣ��ಿ�Ź�ͬŬ��������Э����ȷ���˾�����ͨ����������������á�С�����ڼ䣬�������ڵ������е�ͣ��������ʱͣ�����⣬��ɽ�羰����ί��칫����ͣ����Ҳ���οͿ��ţ����̶ȱ�֤���ο������ˡ�ͣ���¡��½��������ѭ����Ͷ��ʹ�ò��������������ã���Ϊ�ο�����ǰɽ�ͺ�ɽ�ıؾ�֮·�������ѭ������Ч������˲ʱ�����߷塣");
                    break;
                case 3:
                    article.setContent("5��3��,���ߴ�ʡ���ξֻ�Ϥ,���ꡰ��һ��С����,��ʡ�����ۺϷ������������˼���,�����г������������á�С��������,ȫʡ���Ӵ��ο�1157.31���˴�,ͬ������28.79%��ʵ������������46.29��Ԫ,ͬ������29.87%��");
                    break;
            }
        }
        System.out.println(cache.setList(key, articles));
    }

    @Ignore
    @Test
    public void testCacheGetArray()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������1");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        ArticleAbstract[] articles = cache.getArray(key);
        if (articles != null)
        {
            for (ArticleAbstract article : articles)
            {
                System.out.println(gson.toJson(article));
            }
        }
    }

    @Ignore
    @Test
    public void testCacheSetArray()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������1");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
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
            switch (i)
            {
                case 0:
                    articles[i].setContent("���ꡰ��һ�����ڣ����ٹ�·��������7������С�ͳ�������ߣ����гɱ��Ľ��ͣ���Ч���������ܱ������Լ�������ο����������ӡ��ݲ���ȫͳ�ƣ�����һ��С�����ڼ䣬����Ҫ�������Ӵ��Լ��γ���23089���Σ�92349���˴Σ�ռ��Ҫ�����Ӵ����˴ε�85%���ϡ�");
                    break;
                case 1:
                    articles[i].setContent("ֵ��һ�����5��2�գ�����������Ϊ���ӣ����ն����������������3.5���˵Ĺ���Ҫ�󣬾���һ�������Ӧ�����飬ȷ���οͰ�ȫ����һ���棬��Ա�οʹ��ս�ɽ�������������������������������ﵽ�˵���ط��Ŀ�ġ����⣬������ͨ�����ι�˾��ȫ�е��η�������ʾ��Ϣ�����Ѻ���������ʱ�����·���Ա�����Ρ�");
                    break;
                case 2:
                    articles[i].setContent("С�����ڼ䣬����ʵ�з�Ƭ�������ƣ���Ƭ�쵼����������һ�ߣ���ǰָ�ӡ����������Ρ������һ�߹�����ԱȫԱ�ϰ࣬��ǰ���ڣ����������߶����������θ߷��ջ�������ع�����Ա��Ϊ־Ը�ߵ�һ�߸�λִ�ڣ��ಿ�Ź�ͬŬ��������Э����ȷ���˾�����ͨ����������������á�С�����ڼ䣬�������ڵ������е�ͣ��������ʱͣ�����⣬��ɽ�羰����ί��칫����ͣ����Ҳ���οͿ��ţ����̶ȱ�֤���ο������ˡ�ͣ���¡��½��������ѭ����Ͷ��ʹ�ò��������������ã���Ϊ�ο�����ǰɽ�ͺ�ɽ�ıؾ�֮·�������ѭ������Ч������˲ʱ�����߷塣");
                    break;
                case 3:
                    articles[i].setContent("5��3��,���ߴ�ʡ���ξֻ�Ϥ,���ꡰ��һ��С����,��ʡ�����ۺϷ������������˼���,�����г������������á�С��������,ȫʡ���Ӵ��ο�1157.31���˴�,ͬ������28.79%��ʵ������������46.29��Ԫ,ͬ������29.87%��");
                    break;
            }
        }
        System.out.println(cache.setArray(key, articles));
    }

    @Ignore
    @Test
    public void testCacheGetList()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        cache.getList(key).forEach(article -> System.out.println(gson.toJson(article)));
    }

    @Ignore
    @Test
    public void testCacheSet()
    {
        ArticleAbstract article = new ArticleAbstract();
        article.setArticleId(1);
        article.setArticleType(1);
        article.setAuthorId(1);
        article.setModifyDate(1);
        article.setRegionId(1);
        article.setTitle("��������");
        Gson gson = new Gson();
        String key = gson.toJson(article).replaceAll("[\",:{}]", "");
        System.out.println(cache.set(key, article));
    }

    @Ignore
    @Test
    public void testCacheGet()
    {
        ArticleAbstract article = new ArticleAbstract();
        article.setArticleId(1);
        article.setArticleType(1);
        article.setAuthorId(1);
        article.setModifyDate(1);
        article.setRegionId(1);
        article.setTitle("��������");
        Gson gson = new Gson();
        String key = gson.toJson(article).replaceAll("[\",:{}]", "");
        article = cache.get(key);
        System.out.println(article.getTitle());
    }

    @Ignore
    @Test
    public void testCacheDel()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        System.out.println(cache.del(key));
    }

    @Ignore
    @Test
    public void testCacheExist()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        System.out.println(cache.exists(key));
    }

    @Ignore
    @Test
    public void testCacheExpire()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        System.out.println(cache.expire(key, 10));
    }

    @Test
    @Ignore
    public void testCacheArticleSet()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������");
        List<ArticleAbstract> articles = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            ArticleAbstract article = new ArticleAbstract();
            article.setArticleId(i + 1);
            article.setArticleType(i + 1);
            article.setAuthorId(i + 1);
            article.setModifyDate(i + 1);
            article.setRegionId(i + 1);
            article.setTitle("��������" + (i + 1));
            articles.add(article);
            switch (i)
            {
                case 0:
                    article.setContent("���ꡰ��һ�����ڣ����ٹ�·��������7������С�ͳ�������ߣ����гɱ��Ľ��ͣ���Ч���������ܱ������Լ�������ο����������ӡ��ݲ���ȫͳ�ƣ�����һ��С�����ڼ䣬����Ҫ�������Ӵ��Լ��γ���23089���Σ�92349���˴Σ�ռ��Ҫ�����Ӵ����˴ε�85%���ϡ�");
                    break;
                case 1:
                    article.setContent("ֵ��һ�����5��2�գ�����������Ϊ���ӣ����ն����������������3.5���˵Ĺ���Ҫ�󣬾���һ�������Ӧ�����飬ȷ���οͰ�ȫ����һ���棬��Ա�οʹ��ս�ɽ�������������������������������ﵽ�˵���ط��Ŀ�ġ����⣬������ͨ�����ι�˾��ȫ�е��η�������ʾ��Ϣ�����Ѻ���������ʱ�����·���Ա�����Ρ�");
                    break;
                case 2:
                    article.setContent("С�����ڼ䣬����ʵ�з�Ƭ�������ƣ���Ƭ�쵼����������һ�ߣ���ǰָ�ӡ����������Ρ������һ�߹�����ԱȫԱ�ϰ࣬��ǰ���ڣ����������߶����������θ߷��ջ�������ع�����Ա��Ϊ־Ը�ߵ�һ�߸�λִ�ڣ��ಿ�Ź�ͬŬ��������Э����ȷ���˾�����ͨ����������������á�С�����ڼ䣬�������ڵ������е�ͣ��������ʱͣ�����⣬��ɽ�羰����ί��칫����ͣ����Ҳ���οͿ��ţ����̶ȱ�֤���ο������ˡ�ͣ���¡��½��������ѭ����Ͷ��ʹ�ò��������������ã���Ϊ�ο�����ǰɽ�ͺ�ɽ�ıؾ�֮·�������ѭ������Ч������˲ʱ�����߷塣");
                    break;
                case 3:
                    article.setContent("5��3��,���ߴ�ʡ���ξֻ�Ϥ,���ꡰ��һ��С����,��ʡ�����ۺϷ������������˼���,�����г������������á�С��������,ȫʡ���Ӵ��ο�1157.31���˴�,ͬ������28.79%��ʵ������������46.29��Ԫ,ͬ������29.87%��");
                    break;
            }
        }
        System.out.println(cache.setSearchedAbstarctsList(articleAbstract, articles));
    }

    @Test
    @Ignore
    public void testCacheArticleGet()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("��������");
        List<ArticleAbstract> articles = cache.getSearchedAbstarctsList(articleAbstract);
        articles.forEach(article-> System.out.println(article.getContent()));
    }
}
