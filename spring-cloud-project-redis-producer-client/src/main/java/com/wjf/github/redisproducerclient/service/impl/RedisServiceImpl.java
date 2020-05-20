package com.wjf.github.redisproducerclient.service.impl;

import com.alibaba.fastjson.JSON;
import com.wjf.github.commons.domain.redis.behavior.EXPX;
import com.wjf.github.commons.domain.redis.behavior.NXXX;
import com.wjf.github.commons.domain.redis.prefixkey.BasePrefixKey;
import com.wjf.github.commons.properties.RedisProperties;
import com.wjf.github.redisproducerclient.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.Collections;
import java.util.List;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public boolean setKey(String key, String value) {
		return RedisProperties.REDIS_RETURN_STRING_OK.equals(jedisCluster.set(key, value));
	}

	@Override
	public boolean setExKey(String key, int expire, String value) {
		return RedisProperties.REDIS_RETURN_STRING_OK.equals(jedisCluster.setex(key, expire, value));
	}

	@Override
	public boolean setKey(BasePrefixKey key, String name, String value) {
		if (key.getExpire() <= 0) {
			return setKey(key.getName() + "_" + name, value);
		} else {
			return setExKey(key.getName() + "_" + name, key.getExpire(), value);
		}
	}

	@Override
	public boolean setKey(String key, String value, String nx, String ex, long expire) {
		return RedisProperties.REDIS_RETURN_STRING_OK.equals(jedisCluster.set(key, value, nx, ex, expire));
	}

	@Override
	public boolean setKey(BasePrefixKey key, String name, String value, String nx, String ex) {
		return setKey(key.getName() + "_" + name, value, nx, ex, key.getExpire());
	}

	@Override
	public boolean setKey(BasePrefixKey key, String name, String value, NXXX nxxx, EXPX expx) {
		return setKey(key, name, value, nxxx.getName(), expx.getName());
	}

	@Override
	public boolean setNXKey(String key, String value) {
		return RedisProperties.REDIS_RETURN_LONG_OK == jedisCluster.setnx(key, value);
	}

	@Override
	public boolean setNXKey(BasePrefixKey key, String name, String value) {
		return setNXKey(key.getName() + "_" + name, value);
	}

	@Override
	public <T> boolean setObject(String key, T t) {
		return setKey(key, JSON.toJSONString(t));
	}

	@Override
	public <T> boolean setObject(String key, int expire, T t) {
		return setExKey(key, expire, JSON.toJSONString(t));
	}

	@Override
	public <T> boolean setObject(BasePrefixKey key, String name, T t) {
		if (key.getExpire() <= 0) {
			return setObject(key.getName() + "_" + name, t);
		} else {
			return setObject(key.getName() + "_" + name, key.getExpire(), t);
		}
	}

	@Override
	public String getValue(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public String getValue(BasePrefixKey key, String name) {
		return getValue(key.getName() + "_" + name);
	}

	@Override
	public <T> T getObject(String key, Class<T> clazz) {
		return JSON.parseObject(getValue(key), clazz);
	}

	@Override
	public <T> T getObject(BasePrefixKey key, String name, Class<T> clazz) {
		return getObject(key.getName() + "_" + name, clazz);
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clazz) {
		return JSON.parseArray(getValue(key), clazz);
	}

	@Override
	public <T> List<T> getList(BasePrefixKey key, String name, Class<T> clazz) {
		return getList(key.getName() + "_" + name, clazz);
	}

	@Override
	public boolean lock(String key, String value) {
		return setKey(key, value, NXXX.NX.getName(), EXPX.EX.getName(), 10);
	}

	@Override
	public boolean lock(BasePrefixKey key, String name, String value) {
		if (key.getExpire() > 0) {
			return setKey(key.getName() + "_" + name, value, NXXX.NX.getName(), EXPX.EX.getName(), key.getExpire());
		} else {
			return lock(key.getName() + "_" + name, value);
		}
	}

	@Override
	public boolean unlock(String key, String value) {
		String luaStr = "if(redis.call('get',KEYS[1])==ARGV[1]) then " +
				"return redis.call('del',KEYS[1]) " +
				"else " +
				"return '0' " +
				"end";
		return RedisProperties.REDIS_RETURN_LONG_OK.equals(jedisCluster.eval(luaStr, Collections.singletonList(key), Collections.singletonList(value)));
	}

	@Override
	public boolean unlock(BasePrefixKey key, String name, String value) {
		return unlock(key.getName() + "_" + name, value);
	}

	@Override
	public boolean expire(String key, int expire) {
		return RedisProperties.REDIS_RETURN_LONG_OK.equals(jedisCluster.expire(key, expire));
	}

	@Override
	public boolean expire(BasePrefixKey key, String name) {
		return expire(key.getName() + "_" + name, key.getExpire());
	}

}
