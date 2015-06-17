package com.lotour.article.utility;

import com.lotour.article.entity.ArticleType;

/**
 * Created by ljing on 2015/5/13.
 */
public class ParaValidater
{
    /**
     * 合法文章类型检查
     * @param para 参数
     * @return 是否合法
     */
    public static boolean IsValidArticleType(int para)
    {
        for (ArticleType aType : ArticleType.values())
        {
            if (aType.ordinal() == para)
            {
                return true;
            }
        }
        return false;
    }
}
