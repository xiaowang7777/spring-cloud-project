package com.wjf.github.redisproducerclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Component
@Configuration
public class RedisClientConfig {

	@Autowired
	private RedisClientProperties properties;

	@Bean
	public JedisCluster jedisCluster() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxWaitMillis(properties.getMaxWait() * 1000);
		poolConfig.setMinIdle(properties.getMinIdle());
		poolConfig.setMaxIdle(properties.getMaxIdle());
		poolConfig.setMaxTotal(properties.getMaxActive());
		System.out.println(properties.getAddressColony());
		Set<HostAndPort> ports = new HashSet<>();

		HostAndPort hostAndPort = null;
		for (String s : properties.getAddressColony()) {
			final String[] split = s.split(":");
			hostAndPort = new HostAndPort(split[0], Integer.parseInt(split[1]));
			ports.add(hostAndPort);
		}
		return new JedisCluster(ports, properties.getConnectTimeOut(), properties.getSoTimeOut(), properties.getMaxAttempts(), properties.getPassword(), poolConfig);
	}

}
