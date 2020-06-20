package com.wjf.github.commons.domain.redis.prefixkey;

public class RoleInfoPrefixKey extends BasePrefixKey {
	private static final long serialVersionUID = 1329458006041969475L;

	public RoleInfoPrefixKey() {
		super(-1, "role_info");
	}

	public RoleInfoPrefixKey(int expire) {
		super(expire, "role_info");
	}

	public static BasePrefixKey getBasePrefixKey(){
		return new RoleInfoPrefixKey();
	}
	public static BasePrefixKey getBasePrefixKey(int expire){
		return new RoleInfoPrefixKey(expire);
	}
}
