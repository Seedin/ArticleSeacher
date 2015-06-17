package com.lotour.article.cache.implRedis;

import com.google.gson.Gson;
import com.lotour.article.cache.IArticleCacher;
import com.lotour.article.entity.ArticleAbstract;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ljing on 2015/5/18.
 */
@Repository
public class ArticleCacherRedis
        extends BasicCacheRedis<ArticleAbstract>
        implements IArticleCacher
{
        /**
         * ����ʱ��
         */
        private int expiredSpan = 86400;

        /**
         * ��������

         */
        private String regexFilter = "[\",:{}]";

        /**
         * �����滻���
         */
        private String regexFiller = "";

        /**
         * ��������������ժҪ������
         * @param searchedKey ����Key
         * @param abstarcts �������
         * @return �Ƿ񻺴�ɹ�
         */
        public boolean setSearchedAbstarctsList(ArticleAbstract searchedKey, List<ArticleAbstract> abstarcts)
        {
                if (searchedKey == null ||
                        abstarcts == null)
                {
                        return false;
                }
                Gson gson = new Gson();
                String key = gson.toJson(searchedKey).replaceAll(regexFilter, regexFiller);
                if (!this.setList(key, abstarcts))
                {
                        return false;
                }
                if (!this.expire(key, expiredSpan))
                {
                        return false;
                }
                return true;
        }

        /**
         * ��������������ժҪ������
         * @param searchedKey ����Key
         * @param abstarcts �������
         * @return �Ƿ񻺴�ɹ�
         */
        public boolean setSearchedAbstarctsArray(ArticleAbstract searchedKey, ArticleAbstract[] abstarcts)
        {
                if (searchedKey == null ||
                        abstarcts == null)
                {
                        return false;
                }
                Gson gson = new Gson();
                String key = gson.toJson(searchedKey).replaceAll(regexFilter, regexFiller);
                if (!this.setArray(key, abstarcts))
                {
                        return false;
                }
                if (!this.expire(key, expiredSpan))
                {
                        return false;
                }
                return true;
        }

        /**
         * �Ի����ȡ����������ժҪ
         * @param searchedKey ����Key
         * @return �������
         */
        public List<ArticleAbstract> getSearchedAbstarctsList(ArticleAbstract searchedKey)
        {
                if (searchedKey == null)
                {
                        return new ArrayList<>();
                }
                Gson gson = new Gson();
                String key = gson.toJson(searchedKey).replaceAll(regexFilter, regexFiller);
                return getList(key);
        }

        /**
         * �Ի����ȡ����������ժҪ
         * @param searchedKey ����Key
         * @return �������
         */
        public ArticleAbstract[] getSearchedAbstarctsArray(ArticleAbstract searchedKey)
        {
                ArticleAbstract[] result;
                if (searchedKey == null)
                {
                        return new ArticleAbstract[0];
                }
                Gson gson = new Gson();
                String key = gson.toJson(searchedKey).replaceAll(regexFilter, regexFiller);
                result = this.getArray(key);
                return result == null ? new ArticleAbstract[0] : result;
        }

        public void setExpiredSpan(int expiredSpan)
        {
                this.expiredSpan = expiredSpan;
        }

        public void setRegexFilter(String regexFilter)
        {
                this.regexFilter = regexFilter;
        }

        public void setRegexFiller(String regexFiller)
        {
                this.regexFiller = regexFiller;
        }

}
