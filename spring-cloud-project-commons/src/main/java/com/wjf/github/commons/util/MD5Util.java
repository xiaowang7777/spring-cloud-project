package com.wjf.github.commons.util;

import cn.hutool.crypto.SecureUtil;

public class MD5Util {

	public static String getMD5Str(String string){
		return SecureUtil.md5(string);
	}

}
