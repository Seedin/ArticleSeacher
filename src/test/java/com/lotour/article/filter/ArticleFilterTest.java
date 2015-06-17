package com.lotour.article.filter;

import com.lotour.article.entity.ArticleAbstract;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ljing on 2015/5/15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration({"classpath:contextFilter.xml"})
public class ArticleFilterTest
{
    @Autowired
    private IArticleFilter filter;

    @Ignore
    @Test
    public void testArticleAbstractFilter()
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
        articles[0].setContent("<p>第一天乘机抵达<a href=\"http://lasa.lotour.com/\" target=\"_blank\" title=\"拉萨\">拉萨</a>，先喝杯酥油茶缓解一下高反，然后漫步<a href=\"http://lasa.lotour.com/budalagong/\" target=\"_blank\" title=\"布达拉宫\">布达拉宫</a>广场。第二天一早参观世界海拔最高的古代宫堡式建筑布达拉宫，下午再去趟拉萨城的源起之地<a href=\"http://lasa.lotour.com/dazhaosi/\" target=\"_blank\" title=\"大昭寺\">大昭寺</a>。第三天从拉萨启程，翻越海拔4990米的冈巴拉雪山，远眺冈巴拉雷达站，前往世界最高淡水湖<a href=\"http://shannan.lotour.com/xzyangzuoyongcuo/\" target=\"_blank\" title=\"羊卓雍错\">羊卓雍错</a>。绕羊卓雍湖前往抗英英雄镇<a href=\"http://rikaze.lotour.com/jiangzi/\" target=\"_blank\" title=\"江孜\">江孜</a>，途中远眺<a href=\"http://shannan.lotour.com/karuolabingchuan/\" target=\"_blank\" title=\"卡若拉冰川\">卡若拉冰川</a>。在江孜县城远眺<a href=\"http://rikaze.lotour.com/jiangzizongshan/\" target=\"_blank\" title=\"宗山抗英遗址\">宗山抗英遗址</a>后驱车前往后藏重地<a href=\"http://rikaze.lotour.com/rikazeshi/\" target=\"_blank\" title=\"日喀则\">日喀则</a>。第四天乘车前往新<a href=\"http://rikaze.lotour.com/dingri/\" target=\"_blank\" title=\"定日\">定日</a>协格尔，翻越海拔5220米的嘉措拉雪山，途经达乌拉山口没准能看到“<a href=\"http://rizhao.lotour.com/\" target=\"_blank\" title=\"日照\">日照</a><a href=\"http://shanghai.lotour.com/jinshanqu/\" target=\"_blank\" title=\"金山\">金山</a>”。而后直奔<a href=\"http://rikaze.lotour.com/rongbusi/\" target=\"_blank\" title=\"绒布寺\">绒布寺</a>，乘环保车到<a href=\"http://rikaze.lotour.com/zhufengdby/\" target=\"_blank\" title=\"珠峰大本营\">珠峰大本营</a>，清晨在地球之巅迎着阳光仰望珠峰。而后沿喜马拉雅山脉返回<a href=\"http://rikaze.lotour.com/\" target=\"_blank\" title=\"日喀则\">日喀则</a>，游览历代班禅大师驻锡地<a href=\"http://rikaze.lotour.com/zhashilunbusi/\" target=\"_blank\" title=\"扎什伦布寺\">扎什伦布寺</a>，从寺中远观<a href=\"http://rikaze.lotour.com/nianchuhe/\" target=\"_blank\" title=\"年楚河\">年楚河</a>谷的自然风光，下午回到拉萨。</p>");
        articles[1].setContent("<p>台北市公共运输处表示，因应104年4月25日(六)台北市内湖区公所举办「2015多元文化艺术节-台日踩街嘉年华活动」，公车路线配合当日三阶段交通管制禁止车辆进入，届时部分公车路线将配合改道行驶，管制时间及路段(管制时间视活动情况提前或延后)为18－21时管制内湖路2段（内湖路2段103巷口～金龙路口）。</br>本次活动受影响之公车路线共9条路线、6处站位，活动期间公车将改走文德路接成功路3、4段，再接回原线行驶。请民众参考附件公车改道表至邻近捷运站或其他站位，搭乘捷运或其他公车转乘至他处。</br>台北市联营公车路线因交通管制改道而停用之站位，在5284公车动态资讯网页(5284.taipei.gov.tw)、手机版网页(pda.5284.com.tw)、语音查询专线(0800-00-5284)以及智慧型站牌，在停用站位的到站资讯将显示「交管不停靠」，该资讯也同步提供APP业者介接发布，请民众可善加利用。</br>公共运输处吁请民众届时多加留意该处网站(http://www.pto.taipei.gov.tw)相关公车改道讯息，如有任何疑问可电洽站牌上各公车调度站询问；另亦可利用台北市市民当家热线1999（外县市：02-27208889）了解相关讯息。</br>-以下空白-</br>自行车禁行骑楼。自行车超越行人前请说借过，再从左侧缓慢通过并说谢谢。</br>计程车(含敬老爱心车队、无障碍计程车)叫车电话请拨0800-055-850(市话免付费)或手机直拨55850(付费)。</br>复康巴士叫车专线请拨02-40556789或网址http://40556789.taipei.gov.tw/。</br></p><br/>发布机关:台北市公共运输处<br/>资料来源:台北市政府");
        articles[2].setContent("<p><span >4月3日，江西婺源迎来清明前最后一场大雨，据天气预报显示，清明小长假第一天婺源将进入阴天。大雨过后的婺源将会以崭新的面貌迎接八方游客。这场大雨为婺源浓浓的春意中增添了另一道风采，婺源卧龙谷景区在大雨的洗礼下迎来了“飞流直下三千尺”的瀑布奇观。众多游客在瀑布下争相留影，被瀑布激起的水雾溅湿也毫不在乎，奔腾的水声与游人欢快的笑语融为一片，为春日的卧龙谷增加了别样的生气。春夏季，卧龙谷的大瀑布随处可见，场面蔚为壮观。</span><br/></p><p ><img src=\"http://img1.lotour.net/News/201504/2015042116103099401520_min.jpg\"/></p><p>卧龙谷里春季山花烂漫，森林覆盖率高达90.7%，满山遍野的杜鹃山已经盛放，清明前的这场大雨为卧龙谷的瀑布群注入生机，如今峡谷内成群的瀑布飞龙吐玉。瀑布是卧龙谷景区的一绝，各种岩体层峦叠嶂，加之水资源丰富，形成银河飞落、百瀑争流的壮观场面。谷内有水帘瀑、白龙瀑、青龙瀑、大龙瀑等场面宏大的瀑布，其中大龙瀑尤为壮观。<br/></p><p ><img src=\"http://img1.lotour.net/News/201504/201504211610311363490_min.jpg\"/></p><p>“大龙瀑”是大自然的杰作，它从主峰汇集灵泉，沿山道奔泻而下，水流经“里锅”“外锅”连续旋转后，从“锅口”喷泻而出，好似一条巨龙凌空而起，震天憾地，天然落差240米，堪称“华夏第一高瀑”。<br/></p><p ><img src=\"http://img1.lotour.net/News/201504/2015042116103129561839_min.jpg\"/></p><p>清明小长假即将来临，到婺源赏花的同时还可以走进卧龙谷，感受大自然的清新。山上植物种类有一千多种，有南方红豆杉、银杏等国家一级保护植物，景区负氧离子含量每立方厘米10万个单位。卧龙谷因其“树在石上生，石在水中长，瀑在岩上飞，泉在山间唱” 被游客称为“江南第一谜谷”，峡谷仅长3.5公里，天然落差竟达到730米。<br/></p><p ><img src=\"http://img1.lotour.net/News/201504/2015042116103143223808_min.jpg\"/></p><p>卧龙谷被称为“江南第一谜谷”，不仅仅因为它诡怪秀丽的自然景观，更因为它是金庸大师笔下的一处创作原型，是一处金庸笔下的人间美景世外桃源。而且查良镛（金庸）先生的祖籍就在婺源卧龙谷，金庸先生还亲笔为大鄣山卧龙谷景区题词。<br/></p><p ><img src=\"http://img1.lotour.net/News/201504/2015042116103157363352_min.jpg\"/></p><p>这个清明小长假，来婺源赏花踏春，探寻大自然的奥秘，寻访卧龙谷高山峡谷、飞瀑流泉、雄峰巧石，大鄣山卧龙谷景区完美诠释了望得见山，看得见水，记得住乡愁的概念。卧龙谷景区里纯原始的生态、丰富的动植物、独特的地质地貌更能给广大游客带去知识性的趣味。<br/></p><p><strong>在这里还要告诉各位一个好消息，清明出游高速免费！</strong></p><p><strong>为方便游客朋友顺利从南昌出发前来婺源，婺源君特别为各位规划了以下线路：</strong></p><p><span >1.南昌前往婺源江湾、江岭</span></p><p>绕行线路一：南昌-德昌高速-德婺高速-杭瑞高速景婺黄段（婺源北收费站）-江湾-江岭 （从婺源北收费站下高速，途经201省道，全程约289公里）。</p><p>绕行线路二：南昌-德昌高速-德婺高速（婺源收费站）-婺源县-201省道-江湾-江岭（从婺源收费站下高速，途经201省道，全程约283公里）。</p><p>2.景德镇前往婺源江湾、江岭</p><p>绕行线路：景德镇-杭瑞高速景婺黄段-婺源北收费站下高速-江湾-江岭（从婺源北收费站下高速，途经201省道，全程约125公里）。</p><p><br/></p>");
        articles[3].setContent("<p >临近新年，“耕读李家”尚书故里的“李家旅舍”历经半年的辛苦筹建终于“杀青了，给新年增添了一份喜气。</p><p >这幢古色古香的“李家旅舍”是尚书故里景点里第一个农家乐，具备餐饮和住宿双重功能，占地面积300余平方米，设有标间8个及餐饮包间2个。李家旅舍的竣工，标志着耕读李家旅游项目已初具旅游接待能力。</p><p >业主李大叔告诉笔者，“李家旅舍”从去年7月份开始动工，投资40余万元。为配合“耕读李家”的建设理念，采用“依旧建旧”的方式，在自家原有的木式古民居基础上进行改造，注重风格与自然环境协调，突显“李家”人对儒家文化的尊崇。李家旅舍的建成不仅多了一个增加收入的渠道，还能将李家文化传播出去，这也是自己作为“李家”后人的责任和骄傲。</p><p >据了解，为鼓励村民参与到“耕读李家”旅游项目建设中来，享受旅游发展成果，杉城镇制定了《在“耕读李家”范围内经营农家乐的奖励方案》，从资金上给予奖励，李家旅舍是政府引导扶持、农民积极参与建设的典范。</p><p >耕读李家旅游专业村的建设着眼于“为民办实事，让民享实惠”，截至目前，完成总投资850万元，完成新建道路1100米、拓宽道路2000米、改建桥涵2座、修建公厕3个等多项民生工程，切实让群众享实惠。（江慧芳）</p><p align=\"center\"><img src=\"http://img1.lotour.net/News/201504/2015042115095146850030_min.jpg\"/></p><p align=\"center\"><img src=\"http://img1.lotour.net/News/201504/201504211509516565989_min.jpg\"/></p><p ><br /></p>");

        for (int i = 0; i < articles.length; i++)
        {
            if (filter.filter(articles[i]))
            {
                System.out.println(articles[i].getContent());
            }
        }
    }
}
