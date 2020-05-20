package com.wjf.github.redisproducerclient.service;

import com.wjf.github.commons.domain.redis.behavior.EXPX;
import com.wjf.github.commons.domain.redis.behavior.NXXX;
import com.wjf.github.commons.domain.redis.prefixkey.BasePrefixKey;

import java.util.List;

public interface RedisService {
	boolean setKey(String key, String value);

	boolean setExKey(String key, int expire, String value);

	boolean setKey(BasePrefixKey key, String name, String value);

	boolean setKey(String key, String value, String nx, String ex, long expire);

	boolean setKey(BasePrefixKey key, String name, String value, String nx, String ex);

	boolean setKey(BasePrefixKey key, String name, String value, NXXX nxxx, EXPX expx);

	boolean setNXKey(String key, String value);

	boolean setNXKey(BasePrefixKey key, String name, String value);

	<T> boolean setObject(String key, T t);

	<T> boolean setObject(String key, int expire, T t);

	<T> boolean setObject(BasePrefixKey key, String name, T t);

	String getValue(String key);

	String getValue(BasePrefixKey key, String name);

	<T> T getObject(String key, Class<T> clazz);

	<T> T getObject(BasePrefixKey key, String name, Class<T> clazz);

	<T> List<T> getList(String key, Class<T> clazz);

	<T> List<T> getList(BasePrefixKey key, String name, Class<T> clazz);

	boolean lock(String key, String value);

	boolean lock(BasePrefixKey key, String name, String value);

	boolean unlock(String key, String value);

	boolean unlock(BasePrefixKey key, String name, String value);

	boolean expire(String key, int expire);

	boolean expire(BasePrefixKey key, String name);
}
