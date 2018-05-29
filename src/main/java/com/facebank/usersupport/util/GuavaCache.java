package com.facebank.usersupport.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * guava本地缓存
 *
 * @author  hailong.Yang
 * @create 2017-11-09 下午3:02
 **/

@Component
public class GuavaCache {

    private final static Logger logger = LoggerFactory.getLogger(GuavaCache.class);

    private int maximumSize;

    private int duration;

    private GuavaCache(){

    };

    private final static Cache<Object, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(24, TimeUnit.HOURS)
            .expireAfterAccess(24,TimeUnit.HOURS)
            .build();

    //get value from cache
    public static Object get(final Object key){
        Object var = null;
        try {
            var = cache.getIfPresent(key);
        } catch (Exception e) {
            logger.error(" get value failure from cache ",e);
        }
        return var;
    }

    //put value to cache
    public static void put(final Object key, final Object value) {
        cache.put(key, value);
    }

    //invilidate
    public static void invilidate(Object key){
        cache.invalidate(key);
    }

}
