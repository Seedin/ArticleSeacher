package com.lotour.article.dao.implHibernate;

import com.lotour.article.dao.IArticleDao;
import com.lotour.article.entity.vms.ArticleFoot;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ljing on 2015/5/8.
 */
@Repository
public class ArticleFootDaoHibernate
        extends BasicDaoHibernate<ArticleFoot>
        implements IArticleDao<ArticleFoot>
{
    /**
     * �Ự����
     */
    @Autowired
    @Qualifier("sessionFactoryVms")
    protected SessionFactory sessionFactory;

    /**
     * ��ȡ�Ự������������д�Դ���ѡ���Ự����
     */
    @Override
    protected SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    /**
     * ��ҳ���ȡ����
     * @param page ҳ��
     * @param pageSize ҳ��С
     * @return ����ҳ�����б�
     */
    public List<ArticleFoot> GetArticlesInPage(int page, int pageSize)
    {
        List<ArticleFoot> result;
//        String hql = "select a from ArticleFoot as a where a.id between ? and ? and a.status >= 1";
//        result = this.list(hql, new Object[]{(page > 1 ? page - 1 : 0) * pageSize
//                , (page > 1 ? page : 1) * pageSize - 1});
        String hql = "select a from ArticleFoot as a where a.id between :startId and :endId and a.status >= 1";
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("startId", (page > 1 ? page - 1 : 0) * pageSize);
        paraMap.put("endId", (page > 1 ? page : 1) * pageSize - 1);
        result = this.listByAlias(hql, paraMap);
        return result;
    }

    /**
     * ��ȡ��������
     * @return ��������
     */
    public int GetArticlesCount()
    {
        int result;
        String hql = "select max(a.id) from ArticleFoot as a";
        result = Integer.class.cast(this.queryObject(hql));
        return result;
    }
}
