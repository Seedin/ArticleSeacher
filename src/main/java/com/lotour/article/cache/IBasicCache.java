package com.lotour.article.cache;

import java.util.List;

/**
 * Created by ljing on 2015/5/18.
 * 缓存访问层接口
 */
public interface IBasicCache<T>
{
    /**
     * 序列化并保存值对象至缓存
     * @param key 键
     * @param value 值对象
     * @return 是否保存成功
     */
    boolean set(String key, T value);

    /**
     * 序列化并保存值对象列表至缓存
     * @param key 键
     * @param value 值对象列表
     * @return
     */
    boolean setList(String key, List<T> value);

    /**
     * 序列化并保存值对象数组至缓存
     * @param key 键
     * @param value 值对象数组
     * @return
     */
    boolean setArray(String key, T[] value);

    /**
     * 获取反序列化后的值对象
     * @param key 键
     * @return 值对象
     */
    T get(String key);

    /**
     * 获取反序列化后的值对象列表
     * @param key 键
     * @return 值对象
     */
    List<T> getList(String key);

    /**
     * 获取反序列化后的值对象数组
     * @param key 键
     * @return
     */
    T[] getArray(String key);


    /**
     * 删除指定键缓存
     * @param key 键
     * @return 是否删除成功
     */
    boolean del(String key);

    /**
     * 检查指定键对象是否存在
     * @param key 键
     * @return 是否存在
     */
    boolean exists(String key);

    /**
     * 设置指定键对象失效时间
     * @param key 键
     * @param span 失效间隔(秒)
     * @return
     */
    boolean expire(String key, int span);
}
