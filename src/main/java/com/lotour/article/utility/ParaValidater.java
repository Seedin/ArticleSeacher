package com.lotour.article.utility;

import com.lotour.article.entity.ArticleType;

/**
 * Created by ljing on 2015/5/13.
 */
public class ParaValidater
{
    /**
     * �Ϸ��������ͼ��
     * @param para ����
     * @return �Ƿ�Ϸ�
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
