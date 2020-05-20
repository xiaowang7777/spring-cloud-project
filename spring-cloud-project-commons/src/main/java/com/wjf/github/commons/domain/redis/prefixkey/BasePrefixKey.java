package com.wjf.github.commons.domain.redis.prefixkey;

import java.io.Serializable;

public class BasePrefixKey implements Key, Serializable {

	private static final long serialVersionUID = 1186555384322908889L;

	private int expire;

	private String name;

	public BasePrefixKey(){}

	BasePrefixKey(int expire, String name) {
		this.expire = expire;
		this.name = name;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getExpire() {
		return this.expire;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
