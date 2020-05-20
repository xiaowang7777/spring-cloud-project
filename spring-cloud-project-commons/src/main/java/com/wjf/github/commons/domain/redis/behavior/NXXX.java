package com.wjf.github.commons.domain.redis.behavior;

/**
 * redis是否插入的类型
 */
public enum NXXX {
	//仅在不存在时存入
	NX("NX"),
	//仅在存在时存入
	XX("XX");

	private final String name;

	NXXX(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
}
