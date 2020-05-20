package com.wjf.github.commons.domain.redis.behavior;

/**
 * redis的储存时间类型
 */
public enum EXPX {
	//毫秒
	EX("EX"),
	//秒
	PX("PX");

	private final String name;

	EXPX(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
