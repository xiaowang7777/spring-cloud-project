package com.wjf.github.redisproducerclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@Component
@Configuration
public class RedisClientConfig {

	@Autowired
	private RedisClientProperties properties;

	@Bean
	public JedisCluster jedisCluster() {
		final JedisPoolConfig poolConfig = jedisPoolConfig();
		Set<HostAndPort> ports = new HashSet<>();

		HostAndPort hostAndPort = null;
		for (String s : properties.getAddressColony()) {
			System.out.println(s);
			final String[] split = s.split(":");
			hostAndPort = new HostAndPort(split[0], Integer.parseInt(split[1]));
			ports.add(hostAndPort);
		}
		if (properties.getPassword() != null) {
			return new JedisCluster(ports, properties.getConnectTimeOut(), properties.getSoTimeOut(), properties.getMaxAttempts(), properties.getPassword(), poolConfig);
		}
		return new JedisCluster(ports, poolConfig);
	}


//	@Bean
//	public JedisSentinelPool jedisSentinelPool() {
//		final JedisPoolConfig poolConfig = jedisPoolConfig();
//		return new JedisSentinelPool("Master", new HashSet<>(properties.getAddressColony()), poolConfig, properties.getPassword());
//	}


	private JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxWaitMillis(properties.getMaxWait() * 1000);
		poolConfig.setMinIdle(properties.getMinIdle());
		poolConfig.setMaxIdle(properties.getMaxIdle());
		poolConfig.setMaxTotal(properties.getMaxActive());

		return poolConfig;
	}
}
