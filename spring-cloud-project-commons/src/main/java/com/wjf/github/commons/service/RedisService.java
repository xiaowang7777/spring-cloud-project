package com.wjf.github.commons.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wjf.github.commons.domain.redis.behavior.EXPX;
import com.wjf.github.commons.domain.redis.behavior.NXXX;
import com.wjf.github.commons.domain.redis.prefixkey.BasePrefixKey;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RedisService {
	boolean setKey(String key, String value);

	boolean setExKey(String key, int expire, String value);

	boolean setKey(BasePrefixKey key, String name, String value);

	boolean setKey(String key, String value, String nx, String ex, long expire);

	boolean setKey(BasePrefixKey key, String name, String value, String nx, String ex);

	boolean setKey(BasePrefixKey key, String name, String value, NXXX nxxx, EXPX expx);

	boolean setNXKey(String key, String value);

	boolean setNXKey(BasePrefixKey key, String name, String value);

	<T> boolean setObject(String key, T t) throws JsonProcessingException;

	<T> boolean setObject(String key, int expire, T t) throws JsonProcessingException;

	<T> boolean setObject(BasePrefixKey key, String name, T t) throws JsonProcessingException;

	String getValue(String key);

	String getValue(BasePrefixKey key, String name);

	<T> T getObject(String key, Class<T> clazz) throws JsonProcessingException;

	<T> T getObject(BasePrefixKey key, String name, Class<T> clazz) throws JsonProcessingException;

	<T> List<T> getList(String key, Class<T> clazz) throws JsonProcessingException;

	<T> List<T> getList(BasePrefixKey key, String name, Class<T> clazz) throws JsonProcessingException;

	boolean lock(String key, String value);

	boolean lock(BasePrefixKey key, String name, String value);

	boolean unlock(String key, String value);

	boolean unlock(BasePrefixKey key, String name, String value);

	boolean expire(String key, int expire);

	boolean expire(BasePrefixKey key, String name);
}
