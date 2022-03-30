package cn.lingex.basic.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * RedisService服务
 *
 * <p>该类为 {@code redis} 操作核心服务接口, 项目中所有 {@code redis} 的操作均使用此接口
 * <p>该类已默认注入到 {@code spring} 中, 默认实现为 {@link DefaultRedisServiceImpl}, 如需自定义实现请实现该接口并手动注入该接口
 *
 * @author JiYinchuan
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"all"})
@Component
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取Key
     *
     * @param pattern 正则表达式
     * @return 匹配的Key集合
     * @since 1.0.0
     */
    @Nullable
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key        键
     * @param expireTime 过期时间, 单位 [秒]
     * @return 返回操作结果
     * @since 1.0.0
     */
    public boolean expire(String key, long expireTime) {
        if (expireTime > 0) {
            Boolean result = redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            return result != null && result;
        }
        return false;
    }

    /**
     * 根据Key获取过期时间
     *
     * @param key 键
     * @return 过期时间, 单位 [秒], 不存在则小于 [0]
     * @since 1.0.0
     */
    public long getExpire(String key) {
        Long result = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return (result == null) ? -1 : result;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return 是否存在 [false: 不存在, true: 存在]
     * @since 1.0.0
     */
    public boolean hasKey(String key) {
        Boolean result = redisTemplate.hasKey(key);
        return result != null && result;
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @since 1.0.0
     */
    public void del(String... key) {
        if (key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }


    /**
     * 普通缓存获取
     *
     * @param key 键
     * @param <V> 值类型
     * @return 值
     * @since 1.0.0
     */
    @Nullable
    public <V> V get(String key) {
        return (V) redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @since 1.0.0
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒], 小于等于 [0] 将永不过期
     * @since 1.0.0
     */
    public void set(String key, Object value, long expireTime) {
        if (expireTime > 0) {
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * 普通缓存递增
     *
     * @param key    键
     * @param factor 递增因子, 必须大于 [0]
     * @return 递增后的值, 不存在则小于 [0]
     * @since 1.0.0
     */
    public long incr(String key, long factor) {
        if (factor <= 0) {
            throw new RuntimeException("递增因子必须大于 [0]");
        }
        Long result = redisTemplate.opsForValue().increment(key, factor);
        return result == null ? -1 : result;
    }

    /**
     * 普通缓存递减
     *
     * @param key    键
     * @param factor 递减因子, 必须大于 [0]
     * @return 递减后的值, 不存在则小于 [0]
     * @since 1.0.0
     */
    public long decr(String key, long factor) {
        if (factor <= 0) {
            throw new RuntimeException("递减因子必须大于 [0]");
        }
        Long result = redisTemplate.opsForValue().increment(key, -factor);
        return (result == null) ? -1 : result;
    }

    // ================================ Map =================================

    /**
     * Hash缓存获取项
     *
     * @param key  键
     * @param item 项
     * @param <V>  值类型
     * @return 值
     * @since 1.0.0
     */
    @Nullable
    public <V> V hget(String key, String item) {
        return (V) redisTemplate.opsForHash().get(key, item);
    }

    /**
     * Hash缓存获取所有项
     *
     * @param key 键
     * @return 对应的多个键值
     * @since 1.0.0
     */
    @Nullable
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Hash缓存放入所有项
     *
     * @param key 键
     * @param map 对应多个键值
     * @since 1.0.0
     */
    public void hmset(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * Hash缓存放入所有项并指定过期时间
     *
     * @param key        键
     * @param map        对应多个键与项
     * @param expireTime 过期时间(秒)
     * @since 1.0.0
     */
    public void hmset(String key, Map<String, Object> map, long expireTime) {
        redisTemplate.opsForHash().putAll(key, map);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    /**
     * Hash缓存放入项的值
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @since 1.0.0
     */
    public void hset(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * Hash缓存放入项的值并指定过期时间
     *
     * @param key        键
     * @param item       项
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒]
     * @since 1.0.0
     */
    public void hset(String key, String item, Object value, long expireTime) {
        redisTemplate.opsForHash().put(key, item, value);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    /**
     * Hash删除项
     *
     * @param key  键
     * @param item 项
     * @since 1.0.0
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断Hash缓存中是否存在该项的值
     *
     * @param key  键
     * @param item 项
     * @return 是否存在 [false: 不存在, true: 存在]
     * @since 1.0.0
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * Hash缓存项递增
     *
     * @param key    键
     * @param item   项
     * @param factor 递增因子
     * @return 递增后的值
     * @since 1.0.0
     */
    public double hincr(String key, String item, double factor) {
        return redisTemplate.opsForHash().increment(key, item, factor);
    }

    /**
     * Hash缓存项递减
     *
     * @param key    键
     * @param item   项
     * @param factor 递减因子
     * @return 递减后的值
     * @since 1.0.0
     */
    public double hdecr(String key, String item, double factor) {
        return redisTemplate.opsForHash().increment(key, item, -factor);
    }

    // ============================ Set =============================

    /**
     * Set缓存获取
     *
     * @param key 键
     * @param <V> 值类型
     * @return 值
     * @since 1.0.0
     */
    @Nullable
    public <V> Set<V> sGet(String key) {
        Set<Object> result = redisTemplate.opsForSet().members(key);
        return result == null ? null : result.stream().map(v -> (V) v).collect(Collectors.toSet());
    }

    /**
     * Set缓存放入
     *
     * @param key    键
     * @param values 值
     * @return 成功放入个数
     * @since 1.0.0
     */
    public long sSet(String key, Object... values) {
        Long result = redisTemplate.opsForSet().add(key, values);
        return (result == null) ? -1 : result;
    }

    /**
     * Set缓存放入并指定过期时间
     *
     * @param key        键
     * @param expireTime 过期时间, 单位 [秒]
     * @param values     值
     * @return 成功放入个数
     * @since 1.0.0
     */
    public long sSet(String key, long expireTime, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
        return (count == null) ? -1 : count;
    }

    /**
     * Set缓存长度获取
     *
     * @param key 键
     * @return Set缓存长度获取
     * @since 1.0.0
     */
    public long sGetSize(String key) {
        Long result = redisTemplate.opsForSet().size(key);
        return (result == null) ? -1 : result;
    }

    /**
     * 判断Set缓存是否存在值
     *
     * @param key   键
     * @param value 值
     * @return 是否存在 [false: 不存在, true: 存在]
     * @since 1.0.0
     */
    public boolean sHasKey(String key, Object value) {
        Boolean result = redisTemplate.opsForSet().isMember(key, value);
        return result != null && result;
    }

    /**
     * Set缓存删除指定值
     *
     * @param key    键
     * @param values 值
     * @return 成功删除的个数
     * @since 1.0.0
     */
    public long sdel(String key, Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return (count == null) ? -1 : count;
    }

    /**
     * List缓存获取
     *
     * @param key   键
     * @param start 开始
     * @param end   结束
     * @param <V>   值类型
     * @return 值
     * @since 1.0.0
     */
    @Nullable
    public <V> List<V> lGet(String key, long start, long end) {
        List<Object> result = redisTemplate.opsForList().range(key, start, end);
        return result == null ? null : result.stream().map(v -> (V) v).collect(Collectors.toList());
    }

    /**
     * List缓存长度获取
     *
     * @param key 键
     * @return List缓存长度
     * @since 1.0.0
     */
    public long lGetSize(String key) {
        Long result = redisTemplate.opsForList().size(key);
        return (result == null) ? -1 : result;
    }

    /**
     * List缓存指定索引获取
     *
     * @param key   键
     * @param index 索引, 小于 [0] 时则表示从末尾开始计算
     * @param <V>   值类型
     * @return 值
     * @since 1.0.0
     */
    @Nullable
    public <V> V lGetIndex(String key, long index) {
        return (V) redisTemplate.opsForList().index(key, index);
    }

    /**
     * List缓存放入
     *
     * @param key   键
     * @param value 值
     * @since 1.0.0
     */
    public void lSet(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * List缓存放入并指定过期时间
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒]
     * @since 1.0.0
     */
    public void lSet(String key, Object value, long expireTime) {
        redisTemplate.opsForList().rightPush(key, value);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    /**
     * List缓存放入
     *
     * @param key   键
     * @param value 值
     * @since 1.0.0
     */
    public void lSet(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * List缓存批量放入
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间, 单位 [秒]
     * @since 1.0.0
     */
    public void lSet(String key, List<Object> value, long expireTime) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (expireTime > 0) {
            expire(key, expireTime);
        }
    }

    /**
     * List缓存根据索引修改
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @since 1.0.0
     */
    public void lEditIndex(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * List缓存删除Count个值为value的值
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 成功删除个数
     * @since 1.0.0
     */
    public long lDel(String key, long count, Object value) {
        Long remove = redisTemplate.opsForList().remove(key, count, value);
        return (remove == null) ? -1 : remove;
    }

}
