package com.wjf.github.commons.util;

public class ResultTemplate<T> {

	private Integer code;

	private ResultSym sym;

	private String msg;

	private T t;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public ResultSym getSym() {
		return sym;
	}

	public void setSym(ResultSym sym) {
		this.sym = sym;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public ResultTemplate() {
	}

	private ResultTemplate(Integer code, ResultSym sym, String msg, T t) {
		this.code = code;
		this.sym = sym;
		this.msg = msg;
		this.t = t;
	}

	public static ResultTemplate getSuccessResult() {
		return getSuccessResult(200, null);
	}

	public static ResultTemplate getSuccessResult(Integer code) {
		return getSuccessResult(code, null);
	}

	public static <T> ResultTemplate<T> getSuccessResult(T t) {
		return getSuccessResult(200, t);
	}

	public static <T> ResultTemplate<T> getSuccessResult(Integer code, T t) {
		return new ResultTemplate<>(code, ResultSym.OK, "请求成功！", t);
	}

	public static <T> ResultTemplate<T> getFailResult(Integer code, String msg, T t) {
		return new ResultTemplate<>(code, ResultSym.FAIL, msg, t);
	}

	public static <T> ResultTemplate<T> getFailResult(Integer code, T t) {
		return getFailResult(code, "请求失败！", t);
	}

	public static ResultTemplate getFailResult(Integer code) {
		return getFailResult(code, null);
	}

	public static ResultTemplate getFailResult() {
		return getFailResult(400);
	}

	public static ResultTemplate getFailResult(Integer code, String msg) {
		return getFailResult(code, msg, null);
	}

	public static ResultTemplate getFailResult(String msg) {
		return getFailResult(400, msg);
	}

}
