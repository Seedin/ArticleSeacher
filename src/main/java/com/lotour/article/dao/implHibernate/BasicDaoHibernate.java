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
 * 基本Hibernate数据访问类.
 * 仅实现基本的ORM对应CRUD基本操作,复杂操作应由子类实现
 * @author LiuJing
 * @param <T> 模板实体类
 */
@Repository("IBasicDao")
@SuppressWarnings("unchecked")
public class BasicDaoHibernate<T> implements IBasicDao<T>
{
    /**
     * 泛型反射类
     */
    protected Class<T> entityClass;

    /**
     * 获取当前可用会话
     * @return 可用会话
     */
    protected Session currentSession()
    {
        //确定泛型类型
        if (entityClass == null)
        {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            entityClass = Class.class.cast(params[0]);
        }
        return getSessionFactory().getCurrentSession();
    }

    /**
     * 获取会话工厂
     */
    protected SessionFactory getSessionFactory()
    {
        return null;
    }

    /**
     * 添加实体
     */
    @Override
    public Serializable save(T o) {
        Serializable ser = (Serializable) currentSession().save(o);
        return ser;
    }

    /**
     * 添加或更新实体
     */
    @Override
    public void saveOrUpdate(T o) {
        currentSession().saveOrUpdate(o);
    }

    /**
     * 删除实体
     */
    @Override
    public void delete(T o) {
        currentSession().delete(o);
    }

    /**
     * 根据主键删除实体
     */
    @Override
    public void delete(Serializable id) {
        currentSession().delete(get(id));
    }

    /**
     * 更新实体
     */
    @Override
    public void update(T o) {
        currentSession().update(o);
    }

    /**
     * 根据Int型主键加载实体
     */
    @Override
    public T load(int id) {
        Object o = currentSession().load(this.entityClass, id);
        T t = this.entityClass.isInstance(o) ? this.entityClass.cast(o) : null;
        return t;
    }

    /**
     * 根据主键获取实体
     */
    @Override
    public T get(Serializable id) {
        Object o = currentSession().get(this.entityClass, id);
        T t = this.entityClass.isInstance(o) ? this.entityClass.cast(o) : null;
        return t;
    }

    /**
     * 归并实体
     */
    @Override
    public void merge(T o) {
        currentSession().merge(o);
    }

    /**
     * 根据主键检查实体是否存在
     */
    @Override
    public boolean exists(Serializable id) {
        Object o = currentSession().get(this.entityClass, id);
        return o != null;
    }

    /**
     * 清理使所有缓存实体
     */
    @Override
    public void clear() {
        currentSession().clear();
    }

    /**
     * 刷新所有缓存实体
     */
    @Override
    public void flush() {
        currentSession().flush();
    }

    /**
     * 根据sql查询
     * @param sql 查询语句
     * @return 查询结果列表
     */
    @Override
    public List<T> list(String sql)
    {
        Query query = currentSession().createQuery(sql);
        List<T> list = query.list();
        return list;
    }
    /**
     * 根据sql及索引参数查询
     * @param sql 查询语句
     * @param o 索引参数
     * @return 查询结果列表
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
     * 根据sql及索引参数数组查询
     * @param sql 查询语句
     * @param o 索引参数列表
     * @return 查询结果列表
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
     * 根据sql及别名参数映射集查询
     * @param sql 查询语句
     * @param alias 别名参数映射集
     * @return 查询结果列表
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
     * 根据sql、索引参数数组及别名参数映射集查询
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
     * 根据sql查询简单对象
     * @param sql 查询语句
     * @return 查询结果
     */
    @Override
    public Object queryObject(String sql)
    {
        Query query = currentSession().createQuery(sql);
        Object obj = query.uniqueResult();
        return obj;
    }
    /**
     * 根据sql及索引参数查询简单对象
     * @param sql 查询语句
     * @param o 索引参数
     * @return 查询结果
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
     * 根据sql及索引参数数组查询简单对象
     * @param sql 查询语句
     * @param o	索引参数数组
     * @return	查询结果
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
     * 根据sql及别名参数映射集查询简单对象
     * @param sql 查询语句
     * @param alias 别名参数映射集
     * @return 查询结果
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
     * 根据sql、索引参数数组及别名参数映射集查询简单对象
     * @param sql 查询语句
     * @param args 索引参数数组
     * @param alias 别名参数映射集
     * @return 查询结果
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
     * 设置别名查询参数
     * @param query 查询对象
     * @param alias 别名参数映射集合
     */
    private void setAliasParameter(Query query, Map<String,Object> alias) {
        if(alias != null) {
            Set<String> keys = alias.keySet();
            for(String key:keys) {
                Object val = alias.get(key);
                if(val instanceof Collection) {
                    //查询条件是列表
                    query.setParameterList(key, Collection.class.cast(val));
                } else {
                    query.setParameter(key, val);
                }
            }
        }
    }

    /**
     * 设置索引查询参数
     * @param query 查询对象
     * @param o 索引参数数组
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
