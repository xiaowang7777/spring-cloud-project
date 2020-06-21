package com.wjf.github.commons.domain.redis.vo;

import com.wjf.github.commons.domain.redis.behavior.EXPX;
import com.wjf.github.commons.domain.redis.behavior.NXXX;
import com.wjf.github.commons.domain.redis.prefixkey.BasePrefixKey;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RedisVo<T> implements Serializable {

	private static final long serialVersionUID = -1520683351759006087L;

	@NotNull
	private String key;

	@NotNull
	private String value;

	private BasePrefixKey k;

	private String nx;

	private String ex;

	private NXXX nxxx;

	private EXPX expx;

	private T t;

	private int expire;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BasePrefixKey getK() {
		return k;
	}

	public void setK(BasePrefixKey k) {
		this.k = k;
	}

	public String getNx() {
		return nx;
	}

	public void setNx(String nx) {
		this.nx = nx;
	}

	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

	public NXXX getNxxx() {
		return nxxx;
	}

	public void setNxxx(NXXX nxxx) {
		this.nxxx = nxxx;
	}

	public EXPX getExpx() {
		return expx;
	}

	public void setExpx(EXPX expx) {
		this.expx = expx;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}
