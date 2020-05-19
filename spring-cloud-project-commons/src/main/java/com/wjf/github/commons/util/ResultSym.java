package com.wjf.github.commons.util;

public enum ResultSym {

	OK("ok"),
	FAIL("fail");

	private final String sym;

	ResultSym(String sym) {
		this.sym = sym;
	}

	public String getSym() {
		return sym;
	}
}
