package com.lotour.article.filter.impl;

import com.lotour.article.entity.ArticleAbstract;
import com.lotour.article.filter.IArticleFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by ljing on 2015/5/15.
 */
@Component
public class ArticleFilterImpl implements IArticleFilter
{
    /**
     * Html�������
     */
    protected String HtmlRex = "<.+?>";

    /**
     * ���ַ���
     */
    protected String EmptyString = "";
    /**
     * ���ˣ���Ч���·���false����Ч���½�����Ԥ����ת��
     * @param article ����ժҪ��Ϣ
     * @return  �Ƿ���Ч
     */
    public boolean filter(ArticleAbstract article)
    {
        //�ǿռ��
        if (article == null ||
            StringUtils.isEmpty(article.getTitle()) ||
            StringUtils.isEmpty(article.getContent()))
        {
            return false;
        }

        //���¹��˺���������
        article.setContent(contentFilter(article).toString());

        return true;
    }

    /**
     * ���Ĺ���
     * @param article ����ժҪ��Ϣ
     * @return ���˺�����
     */
    protected StringBuilder contentFilter(ArticleAbstract article)
    {
        //������������
        StringBuilder newContent = new StringBuilder();

        //����������ʽ��������������Html���
        newContent.append(Pattern.compile(HtmlRex, Pattern.DOTALL)
                .matcher(article.getContent())
                .replaceAll(EmptyString));

        //���ĸ��ӱ�������
        newContent.append(article.getTitle());

        return newContent;
    }
}
