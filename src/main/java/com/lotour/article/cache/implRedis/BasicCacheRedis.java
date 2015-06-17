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
     * ����Redis�ͻ��˳�
     */
    @Autowired
    protected ShardedJedisPool shardedJedisPool;

    /**
     * ���ͷ�����
     */
    protected Class<T> entityClass;

    /**
     * ��ȡRedis�ͻ���
     * @return Redis�ͻ���
     */
    protected ShardedJedis getRedisClient()
    {
        //ȷ����������
        if (entityClass == null)
        {
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            entityClass = Class.class.cast(params[0]);
        }
        //��ȡRedis�ͻ���
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
     * �黹Redis�ͻ�������
     * @param shardedJedis Redis�ͻ���
     * @return �黹�ɹ�
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
     * ���л�������ֵ����������
     * @param key ��
     * @param value ֵ����
     * @return �Ƿ񱣴�ɹ�
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
     * ���л�������ֵ�����б�������
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
     * ���л�������ֵ��������������
     * @param key ��
     * @param value ֵ��������
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
     * ��ȡ�����л����ֵ����
     * @param key ��
     * @return ֵ����
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
     * ��ȡ�����л����ֵ�����б�
     * @param key ��
     * @return ֵ����
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
     * ��ȡ�����л����ֵ��������
     * @param key ��
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
     * ɾ��ָ��������
     * @param key ��
     * @return �Ƿ�ɾ���ɹ�
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
     * ���ָ���������Ƿ����
     * @param key ��
     * @return �Ƿ����
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
     * ����ָ��������ʧЧʱ��
     * @param key ��
     * @param span ʧЧ���(��)
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
