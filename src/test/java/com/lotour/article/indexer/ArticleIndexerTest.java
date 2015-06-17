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
            articles[i].setTitle("测试文章" + (i + 1));
        }
        articles[0].setContent("今年“五一”假期，高速公路继续推行7座以下小客车免费政策，出行成本的降低，有效地吸引了周边县市自驾来歙县游客数量的增加。据不完全统计，“五一”小长假期间，各主要景区共接待自驾游车辆23089车次，92349万人次，占主要景区接待总人次的85%以上。");
        articles[1].setContent("值得一提的是5月2日，景区天气较为恶劣，按照恶劣天气下最大容量3.5万人的管理要求，景区一方面积极应对雨情，确保游客安全，另一方面，动员游客次日进山，引导客流向市内其他景区分流，达到了调峰控峰的目的。此外，景区还通过导游公司向全市导游发送了提示信息，提醒合理安排游览时间和线路，以便错峰出游。");
        articles[2].setContent("小长假期间，景区实行分片管理负责制，各片领导负责人深入一线，靠前指挥。公安、综治、防火等一线管理人员全员上班，提前到岗，划定区域，走动管理，在旅游高峰日还抽调机关工作人员作为志愿者到一线岗位执勤，多部门共同努力、齐心协力，确保了景区交通秩序和旅游秩序良好。小长假期间，除了汤口地区已有的停车场和临时停车带外，黄山风景区管委会办公区域停车场也向游客开放，最大程度保证了游客来的了、停得下。新建的鳌鱼峰循环道投入使用并立即发挥了作用，作为游客来往前山和后山的必经之路，鳌鱼峰循环道有效缓解了瞬时客流高峰。");
        articles[3].setContent("5月3日,记者从省旅游局获悉,今年“五一”小长假,我省旅游综合服务能力经受了检验,旅游市场秩序总体良好。小长假三天,全省共接待游客1157.31万人次,同比增长28.79%；实现旅游总收入46.29亿元,同比增长29.87%。");

        System.out.println(indexer.AddArticleIndex(articles[0]));
        System.out.println(indexer.AddArticleIndex(articles[1]));
        System.out.println(indexer.CommitChanges());
        System.out.println(indexer.AddArticleIndex(articles[2]));
        System.out.println(indexer.AddArticleIndex(articles[3]));
        System.out.println(indexer.CommitChanges());
    }
}
