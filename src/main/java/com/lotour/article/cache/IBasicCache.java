package com.lotour.article.cache;

import java.util.List;

/**
 * Created by ljing on 2015/5/18.
 * ������ʲ�ӿ�
 */
public interface IBasicCache<T>
{
    /**
     * ���л�������ֵ����������
     * @param key ��
     * @param value ֵ����
     * @return �Ƿ񱣴�ɹ�
     */
    boolean set(String key, T value);

    /**
     * ���л�������ֵ�����б�������
     * @param key ��
     * @param value ֵ�����б�
     * @return
     */
    boolean setList(String key, List<T> value);

    /**
     * ���л�������ֵ��������������
     * @param key ��
     * @param value ֵ��������
     * @return
     */
    boolean setArray(String key, T[] value);

    /**
     * ��ȡ�����л����ֵ����
     * @param key ��
     * @return ֵ����
     */
    T get(String key);

    /**
     * ��ȡ�����л����ֵ�����б�
     * @param key ��
     * @return ֵ����
     */
    List<T> getList(String key);

    /**
     * ��ȡ�����л����ֵ��������
     * @param key ��
     * @return
     */
    T[] getArray(String key);


    /**
     * ɾ��ָ��������
     * @param key ��
     * @return �Ƿ�ɾ���ɹ�
     */
    boolean del(String key);

    /**
     * ���ָ���������Ƿ����
     * @param key ��
     * @return �Ƿ����
     */
    boolean exists(String key);

    /**
     * ����ָ��������ʧЧʱ��
     * @param key ��
     * @param span ʧЧ���(��)
     * @return
     */
    boolean expire(String key, int span);
}
