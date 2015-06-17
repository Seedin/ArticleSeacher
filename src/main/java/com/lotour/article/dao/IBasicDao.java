package com.lotour.article.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ljing on 2015/5/6
 * ���ݷ��ʲ�ӿ�.
 */
public interface IBasicDao<T>
{
    /**
     * �������
     * @param o
     * @return
     */
    public Serializable save(T o);

    /**
     * �������¶���
     * @param o
     */
    public void saveOrUpdate(T o);

    /**
     * ɾ������
     * @param o
     */
    public void delete(T o);

    /**
     * ����idɾ������
     * @param id
     */
    public void delete(Serializable id);

    /**
     * ���¶���
     * @param o
     */
    public void update(T o);

    /**
     * ����id���ض���
     * @param id
     * @return
     */
    public T load(int id);

    /**
     * ����id��ö���
     * @param id
     * @return
     */
    public T get(Serializable id);

    /**
     * �ϲ�����
     * @param o
     */
    public void merge(T o);

    /**
     * �ж϶����Ƿ����
     * @param id
     * @return
     */
    public boolean exists(Serializable id);

    /**
     * ���session
     */
    public void clear();

    /**
     * ǿ�����session
     */
    public void flush();

    /**
     * ����sql��ѯģ���б�
     * @param sql ��ѯ���
     * @return ��ѯ����б�
     */
    public List<T> list(String sql);
    /**
     * ����sql������������ѯģ���б�
     * @param sql ��ѯ���
     * @param o ��������
     * @return ��ѯ����б�
     */
    public List<T> list(String sql, Object o);
    /**
     * ����sql���������������ѯģ���б�
     * @param sql ��ѯ���
     * @param o ���������б�
     * @return ��ѯ����б�
     */
    public List<T> list(String sql, Object[] o);
    /**
     * ����sql����������ӳ�伯��ѯģ���б�
     * @param sql ��ѯ���
     * @param alias ��������ӳ�伯
     * @return ��ѯ����б�
     */
    public List<T> listByAlias(String sql, Map<String, Object> alias);
    /**
     * ����sql�������������鼰��������ӳ�伯��ѯģ���б�
     * @param sql
     * @param o
     * @param alias
     * @return
     */
    public List<T> list(String sql, Object[] o, Map<String, Object> alias);

    /**
     * ����sql��ѯ�򵥶���
     * @param sql ��ѯ���
     * @return ��ѯ���
     */
    public Object queryObject(String sql);
    /**
     * ����sql������������ѯ�򵥶���
     * @param sql ��ѯ���
     * @param o ��������
     * @return ��ѯ���
     */
    public Object queryObject(String sql, Object o);
    /**
     * ����sql���������������ѯ�򵥶���
     * @param sql ��ѯ���
     * @param o	������������
     * @return	��ѯ���
     */
    public Object queryObject(String sql, Object[] o);
    /**
     * ����sql����������ӳ�伯��ѯ�򵥶���
     * @param sql ��ѯ���
     * @param alias ��������ӳ�伯
     * @return ��ѯ���
     */
    public Object queryObject(String sql, Map<String, Object> alias);
    /**
     * ����sql�������������鼰��������ӳ�伯��ѯ�򵥶���
     * @param sql ��ѯ���
     * @param args ������������
     * @param alias ��������ӳ�伯
     * @return ��ѯ���
     */
    public Object queryObject(String sql, Object[] args, Map<String, Object> alias);
}
