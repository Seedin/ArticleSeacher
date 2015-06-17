package com.lotour.article.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ljing on 2015/5/6
 * 数据访问层接口.
 */
public interface IBasicDao<T>
{
    /**
     * 保存对象
     * @param o
     * @return
     */
    public Serializable save(T o);

    /**
     * 保存或更新对象
     * @param o
     */
    public void saveOrUpdate(T o);

    /**
     * 删除对象
     * @param o
     */
    public void delete(T o);

    /**
     * 根据id删除队形
     * @param id
     */
    public void delete(Serializable id);

    /**
     * 更新对象
     * @param o
     */
    public void update(T o);

    /**
     * 根据id加载对象
     * @param id
     * @return
     */
    public T load(int id);

    /**
     * 根据id获得对象
     * @param id
     * @return
     */
    public T get(Serializable id);

    /**
     * 合并对象
     * @param o
     */
    public void merge(T o);

    /**
     * 判断对象是否存在
     * @param id
     * @return
     */
    public boolean exists(Serializable id);

    /**
     * 清空session
     */
    public void clear();

    /**
     * 强制清空session
     */
    public void flush();

    /**
     * 根据sql查询模板列表
     * @param sql 查询语句
     * @return 查询结果列表
     */
    public List<T> list(String sql);
    /**
     * 根据sql及索引参数查询模板列表
     * @param sql 查询语句
     * @param o 索引参数
     * @return 查询结果列表
     */
    public List<T> list(String sql, Object o);
    /**
     * 根据sql及索引参数数组查询模板列表
     * @param sql 查询语句
     * @param o 索引参数列表
     * @return 查询结果列表
     */
    public List<T> list(String sql, Object[] o);
    /**
     * 根据sql及别名参数映射集查询模板列表
     * @param sql 查询语句
     * @param alias 别名参数映射集
     * @return 查询结果列表
     */
    public List<T> listByAlias(String sql, Map<String, Object> alias);
    /**
     * 根据sql、索引参数数组及别名参数映射集查询模板列表
     * @param sql
     * @param o
     * @param alias
     * @return
     */
    public List<T> list(String sql, Object[] o, Map<String, Object> alias);

    /**
     * 根据sql查询简单对象
     * @param sql 查询语句
     * @return 查询结果
     */
    public Object queryObject(String sql);
    /**
     * 根据sql及索引参数查询简单对象
     * @param sql 查询语句
     * @param o 索引参数
     * @return 查询结果
     */
    public Object queryObject(String sql, Object o);
    /**
     * 根据sql及索引参数数组查询简单对象
     * @param sql 查询语句
     * @param o	索引参数数组
     * @return	查询结果
     */
    public Object queryObject(String sql, Object[] o);
    /**
     * 根据sql及别名参数映射集查询简单对象
     * @param sql 查询语句
     * @param alias 别名参数映射集
     * @return 查询结果
     */
    public Object queryObject(String sql, Map<String, Object> alias);
    /**
     * 根据sql、索引参数数组及别名参数映射集查询简单对象
     * @param sql 查询语句
     * @param args 索引参数数组
     * @param alias 别名参数映射集
     * @return 查询结果
     */
    public Object queryObject(String sql, Object[] args, Map<String, Object> alias);
}
