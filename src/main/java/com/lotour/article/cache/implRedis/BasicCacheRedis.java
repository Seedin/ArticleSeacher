package com.lotour.article.cache.implRedis;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lotour.article.cache.IBasicCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljing on 2015/5/18.
 */
@Repository("IBasicCache")
@SuppressWarnings("unchecked")
public class BasicCacheRedis<T> implements IBasicCache<T>
{
    /**
     * 共享Redis客户端池
     */
    @Autowired
    protected ShardedJedisPool shardedJedisPool;

    /**
     * 泛型反射类
     */
    protected Class<T> entityClass;

    /**
     * 获取Redis客户端
     * @return Redis客户端
     */
    protected ShardedJedis getRedisClient()
    {
        //确定泛型类型
        if (entityClass == null)
        {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            entityClass = Class.class.cast(params[0]);
        }
        //获取Redis客户端
        try
        {
            return shardedJedisPool.getResource();
        }
        catch (Exception err)
        {
            return null;
        }
    }

    /**
     * 归还Redis客户端至池
     * @param shardedJedis Redis客户端
     * @return 归还成功
     */
    protected boolean returnResource(ShardedJedis shardedJedis)
    {
        try
        {
            shardedJedisPool.returnResourceObject(shardedJedis);
            return true;
        }
        catch (Exception err)
        {
            return false;
        }
    }

    /**
     * 序列化并保存值对象至缓存
     * @param key 键
     * @param value 值对象
     * @return 是否保存成功
     */
    public boolean set(String key, T value)
    {
        ShardedJedis client = null;
        try
        {
            client = getRedisClient();
            Gson gson = new Gson();
            client.set(key, gson.toJson(value));
            return true;
        }
        catch (Exception err)
        {
            return false;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 序列化并保存值对象列表至缓存
     * @param key
     * @param value
     * @return
     */
    public boolean setList(String key, List<T> value)
    {
        ShardedJedis client = null;
        try
        {
            client = getRedisClient();
            Gson gson = new Gson();
            client.set(key, gson.toJson(value));
            return true;
        }
        catch (Exception err)
        {
            return false;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 序列化并保存值对象数组至缓存
     * @param key 键
     * @param value 值对象数组
     * @return
     */
    public boolean setArray(String key, T[] value)
    {
        ShardedJedis client = null;
        try
        {
            client = getRedisClient();
            Gson gson = new Gson();
            client.set(key, gson.toJson(value));
            return true;
        }
        catch (Exception err)
        {
            return false;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 获取反序列化后的值对象
     * @param key 键
     * @return 值对象
     */
    public T get(String key)
    {
        ShardedJedis client = null;
        T result = null;
        try
        {
            client = getRedisClient();
            Gson gson = new Gson();
            String redisStr = client.get(key);
            if (StringUtils.isEmpty(redisStr))
            {
                return result;
            }
            result = gson.fromJson(redisStr, entityClass);
            return result;
        }
        catch (Exception err)
        {
            return result;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 获取反序列化后的值对象列表
     * @param key 键
     * @return 值对象
     */
    public List<T> getList(String key)
    {
        ShardedJedis client = null;
        List<T> redisList;
        List<T> result = new ArrayList<>();
        try
        {
            client = getRedisClient();
            Gson gson = new Gson();
            String redisStr = client.get(key);
            if (StringUtils.isEmpty(redisStr))
            {
                return result;
            }
            redisList = gson.fromJson(redisStr,
                    new TypeToken<List<T>>()
                    {
                    }.getType());
            for (Object o : redisList)
            {
                result.add(gson.fromJson(gson.toJson(o), entityClass));
            }
            return result;
        }
        catch (Exception err)
        {
            return result;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 获取反序列化后的值对象数组
     * @param key 键
     * @return
     */
    public T[] getArray(String key)
    {
        ShardedJedis client = null;
        T[] redisList;
        T[] result = null;
        try
        {
            client = getRedisClient();
            Gson gson = new Gson();
            String redisStr = client.get(key);
            if (StringUtils.isEmpty(redisStr))
            {
                return result;
            }
            redisList = gson.fromJson(redisStr,
                    new TypeToken<T[]>()
                    {
                    }.getType());
            if (redisList == null ||
                    redisList.length == 0)
            {
                return result;
            }
            result = (T[])Array.newInstance(entityClass, redisList.length);
            for (int i = 0; i < redisList.length; i++)
            {
                result[i] = gson.fromJson(gson.toJson(redisList[i]), entityClass);
            }
            return result;
        }
        catch (Exception err)
        {
            return result;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 删除指定键缓存
     * @param key 键
     * @return 是否删除成功
     */
    public boolean del(String key)
    {
        ShardedJedis client = null;
        try
        {
            client = getRedisClient();
            return client.del(key) > 0;
        }
        catch (Exception err)
        {
            return false;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 检查指定键对象是否存在
     * @param key 键
     * @return 是否存在
     */
    public boolean exists(String key)
    {
        ShardedJedis client = null;
        try
        {
            client = getRedisClient();
            return client.exists(key);
        }
        catch (Exception err)
        {
            return false;
        }
        finally
        {
            returnResource(client);
        }
    }

    /**
     * 设置指定键对象失效时间
     * @param key 键
     * @param span 失效间隔(秒)
     * @return
     */
    public  boolean expire(String key, int span)
    {
        ShardedJedis client = null;
        try
        {
            client = getRedisClient();
            return client.expire(key, span) > 0;
        }
        catch (Exception err)
        {
            return false;
        }
        finally
        {
            returnResource(client);
        }
    }
}
