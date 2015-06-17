package com.lotour.article.dao.implHibernate;

import com.lotour.article.dao.IBasicDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ����Hibernate���ݷ�����.
 * ��ʵ�ֻ�����ORM��ӦCRUD��������,���Ӳ���Ӧ������ʵ��
 * @author LiuJing
 * @param <T> ģ��ʵ����
 */
@Repository("IBasicDao")
@SuppressWarnings("unchecked")
public class BasicDaoHibernate<T> implements IBasicDao<T>
{
    /**
     * ���ͷ�����
     */
    protected Class<T> entityClass;

    /**
     * ��ȡ��ǰ���ûỰ
     * @return ���ûỰ
     */
    protected Session currentSession()
    {
        //ȷ����������
        if (entityClass == null)
        {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            entityClass = Class.class.cast(params[0]);
        }
        return getSessionFactory().getCurrentSession();
    }

    /**
     * ��ȡ�Ự����
     */
    protected SessionFactory getSessionFactory()
    {
        return null;
    }

    /**
     * ���ʵ��
     */
    @Override
    public Serializable save(T o) {
        Serializable ser = (Serializable) currentSession().save(o);
        return ser;
    }

    /**
     * ��ӻ����ʵ��
     */
    @Override
    public void saveOrUpdate(T o) {
        currentSession().saveOrUpdate(o);
    }

    /**
     * ɾ��ʵ��
     */
    @Override
    public void delete(T o) {
        currentSession().delete(o);
    }

    /**
     * ��������ɾ��ʵ��
     */
    @Override
    public void delete(Serializable id) {
        currentSession().delete(get(id));
    }

    /**
     * ����ʵ��
     */
    @Override
    public void update(T o) {
        currentSession().update(o);
    }

    /**
     * ����Int����������ʵ��
     */
    @Override
    public T load(int id) {
        Object o = currentSession().load(this.entityClass, id);
        T t = this.entityClass.isInstance(o) ? this.entityClass.cast(o) : null;
        return t;
    }

    /**
     * ����������ȡʵ��
     */
    @Override
    public T get(Serializable id) {
        Object o = currentSession().get(this.entityClass, id);
        T t = this.entityClass.isInstance(o) ? this.entityClass.cast(o) : null;
        return t;
    }

    /**
     * �鲢ʵ��
     */
    @Override
    public void merge(T o) {
        currentSession().merge(o);
    }

    /**
     * �����������ʵ���Ƿ����
     */
    @Override
    public boolean exists(Serializable id) {
        Object o = currentSession().get(this.entityClass, id);
        return o != null;
    }

    /**
     * ����ʹ���л���ʵ��
     */
    @Override
    public void clear() {
        currentSession().clear();
    }

    /**
     * ˢ�����л���ʵ��
     */
    @Override
    public void flush() {
        currentSession().flush();
    }

    /**
     * ����sql��ѯ
     * @param sql ��ѯ���
     * @return ��ѯ����б�
     */
    @Override
    public List<T> list(String sql)
    {
        Query query = currentSession().createQuery(sql);
        List<T> list = query.list();
        return list;
    }
    /**
     * ����sql������������ѯ
     * @param sql ��ѯ���
     * @param o ��������
     * @return ��ѯ����б�
     */
    @Override
    public List<T> list(String sql, Object o)
    {
        Query query = currentSession().createQuery(sql);
        setParameter(query, new Object[]{o});
        List<T> list = query.list();
        return list;
    }
    /**
     * ����sql���������������ѯ
     * @param sql ��ѯ���
     * @param o ���������б�
     * @return ��ѯ����б�
     */
    @Override
    public List<T> list(String sql, Object[] o)
    {
        Query query = currentSession().createQuery(sql);
        setParameter(query, o);
        List<T> list = query.list();
        return list;
    }
    /**
     * ����sql����������ӳ�伯��ѯ
     * @param sql ��ѯ���
     * @param alias ��������ӳ�伯
     * @return ��ѯ����б�
     */
    @Override
    public List<T> listByAlias(String sql, Map<String, Object> alias)
    {
        Query query = currentSession().createQuery(sql);
        setAliasParameter(query, alias);
        List<T> list = query.list();
        return list;
    }
    /**
     * ����sql�������������鼰��������ӳ�伯��ѯ
     * @param sql
     * @param o
     * @param alias
     * @return
     */
    @Override
    public List<T> list(String sql, Object[] o, Map<String, Object> alias)
    {
        Query query = currentSession().createQuery(sql);
        setAliasParameter(query, alias);
        setParameter(query, o);
        List<T> list = query.list();
        return list;
    }

    /**
     * ����sql��ѯ�򵥶���
     * @param sql ��ѯ���
     * @return ��ѯ���
     */
    @Override
    public Object queryObject(String sql)
    {
        Query query = currentSession().createQuery(sql);
        Object obj = query.uniqueResult();
        return obj;
    }
    /**
     * ����sql������������ѯ�򵥶���
     * @param sql ��ѯ���
     * @param o ��������
     * @return ��ѯ���
     */
    @Override
    public Object queryObject(String sql, Object o)
    {
        Query query = currentSession().createQuery(sql);
        setParameter(query, new Object[]{o});
        Object obj = query.uniqueResult();
        return obj;
    }
    /**
     * ����sql���������������ѯ�򵥶���
     * @param sql ��ѯ���
     * @param o	������������
     * @return	��ѯ���
     */
    @Override
    public Object queryObject(String sql, Object[] o)
    {
        Query query = currentSession().createQuery(sql);
        setParameter(query, o);
        Object obj = query.uniqueResult();
        return obj;
    }
    /**
     * ����sql����������ӳ�伯��ѯ�򵥶���
     * @param sql ��ѯ���
     * @param alias ��������ӳ�伯
     * @return ��ѯ���
     */
    @Override
    public Object queryObject(String sql, Map<String, Object> alias)
    {
        Query query = currentSession().createQuery(sql);
        setAliasParameter(query, alias);
        Object obj = query.uniqueResult();
        return obj;
    }
    /**
     * ����sql�������������鼰��������ӳ�伯��ѯ�򵥶���
     * @param sql ��ѯ���
     * @param args ������������
     * @param alias ��������ӳ�伯
     * @return ��ѯ���
     */
    @Override
    public Object queryObject(String sql, Object[] args, Map<String, Object> alias)
    {
        Query query = currentSession().createQuery(sql);
        setAliasParameter(query, alias);
        setParameter(query, args);
        Object obj = query.uniqueResult();
        return obj;
    }

    /**
     * ���ñ�����ѯ����
     * @param query ��ѯ����
     * @param alias ��������ӳ�伯��
     */
    private void setAliasParameter(Query query, Map<String,Object> alias) {
        if(alias != null) {
            Set<String> keys = alias.keySet();
            for(String key:keys) {
                Object val = alias.get(key);
                if(val instanceof Collection) {
                    //��ѯ�������б�
                    query.setParameterList(key, Collection.class.cast(val));
                } else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    /**
     * ����������ѯ����
     * @param query ��ѯ����
     * @param o ������������
     */
    private void setParameter(Query query,Object[] o) {
        if(o !=null && o.length>0) {
            int index = 0;
            for(Object arg:o) {
                query.setParameter(index++, arg);
            }
        }
    }
}
