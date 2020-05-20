package com.wjf.github.redisproducerclient.config;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "redis.client")
public class RedisClientProperties {
	private Integer port;

	private String host;

	private Integer maxActive;

	private Integer maxWait;

	private Integer maxIdle;

	private Integer minIdle;

	private String password;

	//连接超时
	private Integer connectTimeOut = 3000;

	//读取超时
	private Integer soTimeOut = 3000;

	private Integer maxAttempts = 5;

	private String clientName = "defaultJedisCluster";

	private List<String> addressColony;

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Integer getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Integer maxWait) {
		this.maxWait = maxWait;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getAddressColony() {
		return addressColony;
	}

	public Integer getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(Integer connectTimeOut) {
		if (connectTimeOut != null) {
			this.connectTimeOut = connectTimeOut;
		}
	}

	public Integer getSoTimeOut() {
		return soTimeOut;
	}

	public void setSoTimeOut(Integer soTimeOut) {
		if (soTimeOut != null) {
			this.soTimeOut = soTimeOut;
		}
	}

	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(Integer maxAttempts) {
		if (maxAttempts != null) {
			this.maxAttempts = maxAttempts;
		}
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		if (clientName != null && !"".equals(clientName)) {
			this.clientName = clientName;
		}
	}

	public void setAddressColony(List<String> addressColony) {
		this.addressColony = addressColony;
	}
}
