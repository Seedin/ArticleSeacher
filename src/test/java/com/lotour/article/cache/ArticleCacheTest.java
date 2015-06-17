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
        articleAbstract.setTitle("测试文章");
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
            article.setTitle("测试文章" + (i + 1));
            articles.add(article);
            switch (i)
            {
                case 0:
                    article.setContent("今年“五一”假期，高速公路继续推行7座以下小客车免费政策，出行成本的降低，有效地吸引了周边县市自驾来歙县游客数量的增加。据不完全统计，“五一”小长假期间，各主要景区共接待自驾游车辆23089车次，92349万人次，占主要景区接待总人次的85%以上。");
                    break;
                case 1:
                    article.setContent("值得一提的是5月2日，景区天气较为恶劣，按照恶劣天气下最大容量3.5万人的管理要求，景区一方面积极应对雨情，确保游客安全，另一方面，动员游客次日进山，引导客流向市内其他景区分流，达到了调峰控峰的目的。此外，景区还通过导游公司向全市导游发送了提示信息，提醒合理安排游览时间和线路，以便错峰出游。");
                    break;
                case 2:
                    article.setContent("小长假期间，景区实行分片管理负责制，各片领导负责人深入一线，靠前指挥。公安、综治、防火等一线管理人员全员上班，提前到岗，划定区域，走动管理，在旅游高峰日还抽调机关工作人员作为志愿者到一线岗位执勤，多部门共同努力、齐心协力，确保了景区交通秩序和旅游秩序良好。小长假期间，除了汤口地区已有的停车场和临时停车带外，黄山风景区管委会办公区域停车场也向游客开放，最大程度保证了游客来的了、停得下。新建的鳌鱼峰循环道投入使用并立即发挥了作用，作为游客来往前山和后山的必经之路，鳌鱼峰循环道有效缓解了瞬时客流高峰。");
                    break;
                case 3:
                    article.setContent("5月3日,记者从省旅游局获悉,今年“五一”小长假,我省旅游综合服务能力经受了检验,旅游市场秩序总体良好。小长假三天,全省共接待游客1157.31万人次,同比增长28.79%；实现旅游总收入46.29亿元,同比增长29.87%。");
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
        articleAbstract.setTitle("测试文章1");
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
        articleAbstract.setTitle("测试文章1");
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
            articles[i].setTitle("测试文章" + (i + 1));
            switch (i)
            {
                case 0:
                    articles[i].setContent("今年“五一”假期，高速公路继续推行7座以下小客车免费政策，出行成本的降低，有效地吸引了周边县市自驾来歙县游客数量的增加。据不完全统计，“五一”小长假期间，各主要景区共接待自驾游车辆23089车次，92349万人次，占主要景区接待总人次的85%以上。");
                    break;
                case 1:
                    articles[i].setContent("值得一提的是5月2日，景区天气较为恶劣，按照恶劣天气下最大容量3.5万人的管理要求，景区一方面积极应对雨情，确保游客安全，另一方面，动员游客次日进山，引导客流向市内其他景区分流，达到了调峰控峰的目的。此外，景区还通过导游公司向全市导游发送了提示信息，提醒合理安排游览时间和线路，以便错峰出游。");
                    break;
                case 2:
                    articles[i].setContent("小长假期间，景区实行分片管理负责制，各片领导负责人深入一线，靠前指挥。公安、综治、防火等一线管理人员全员上班，提前到岗，划定区域，走动管理，在旅游高峰日还抽调机关工作人员作为志愿者到一线岗位执勤，多部门共同努力、齐心协力，确保了景区交通秩序和旅游秩序良好。小长假期间，除了汤口地区已有的停车场和临时停车带外，黄山风景区管委会办公区域停车场也向游客开放，最大程度保证了游客来的了、停得下。新建的鳌鱼峰循环道投入使用并立即发挥了作用，作为游客来往前山和后山的必经之路，鳌鱼峰循环道有效缓解了瞬时客流高峰。");
                    break;
                case 3:
                    articles[i].setContent("5月3日,记者从省旅游局获悉,今年“五一”小长假,我省旅游综合服务能力经受了检验,旅游市场秩序总体良好。小长假三天,全省共接待游客1157.31万人次,同比增长28.79%；实现旅游总收入46.29亿元,同比增长29.87%。");
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
        articleAbstract.setTitle("测试文章");
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
        article.setTitle("测试文章");
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
        article.setTitle("测试文章");
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
        articleAbstract.setTitle("测试文章");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        System.out.println(cache.del(key));
    }

    @Ignore
    @Test
    public void testCacheExist()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("测试文章");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        System.out.println(cache.exists(key));
    }

    @Ignore
    @Test
    public void testCacheExpire()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("测试文章");
        Gson gson = new Gson();
        String key = gson.toJson(articleAbstract).replaceAll("[\",:{}]", "");
        System.out.println(cache.expire(key, 10));
    }

    @Test
    @Ignore
    public void testCacheArticleSet()
    {
        ArticleAbstract articleAbstract = new ArticleAbstract();
        articleAbstract.setTitle("测试文章");
        List<ArticleAbstract> articles = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            ArticleAbstract article = new ArticleAbstract();
            article.setArticleId(i + 1);
            article.setArticleType(i + 1);
            article.setAuthorId(i + 1);
            article.setModifyDate(i + 1);
            article.setRegionId(i + 1);
            article.setTitle("测试文章" + (i + 1));
            articles.add(article);
            switch (i)
            {
                case 0:
                    article.setContent("今年“五一”假期，高速公路继续推行7座以下小客车免费政策，出行成本的降低，有效地吸引了周边县市自驾来歙县游客数量的增加。据不完全统计，“五一”小长假期间，各主要景区共接待自驾游车辆23089车次，92349万人次，占主要景区接待总人次的85%以上。");
                    break;
                case 1:
                    article.setContent("值得一提的是5月2日，景区天气较为恶劣，按照恶劣天气下最大容量3.5万人的管理要求，景区一方面积极应对雨情，确保游客安全，另一方面，动员游客次日进山，引导客流向市内其他景区分流，达到了调峰控峰的目的。此外，景区还通过导游公司向全市导游发送了提示信息，提醒合理安排游览时间和线路，以便错峰出游。");
                    break;
                case 2:
                    article.setContent("小长假期间，景区实行分片管理负责制，各片领导负责人深入一线，靠前指挥。公安、综治、防火等一线管理人员全员上班，提前到岗，划定区域，走动管理，在旅游高峰日还抽调机关工作人员作为志愿者到一线岗位执勤，多部门共同努力、齐心协力，确保了景区交通秩序和旅游秩序良好。小长假期间，除了汤口地区已有的停车场和临时停车带外，黄山风景区管委会办公区域停车场也向游客开放，最大程度保证了游客来的了、停得下。新建的鳌鱼峰循环道投入使用并立即发挥了作用，作为游客来往前山和后山的必经之路，鳌鱼峰循环道有效缓解了瞬时客流高峰。");
                    break;
                case 3:
                    article.setContent("5月3日,记者从省旅游局获悉,今年“五一”小长假,我省旅游综合服务能力经受了检验,旅游市场秩序总体良好。小长假三天,全省共接待游客1157.31万人次,同比增长28.79%；实现旅游总收入46.29亿元,同比增长29.87%。");
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
        articleAbstract.setTitle("测试文章");
        List<ArticleAbstract> articles = cache.getSearchedAbstarctsList(articleAbstract);
        articles.forEach(article-> System.out.println(article.getContent()));
    }
}
