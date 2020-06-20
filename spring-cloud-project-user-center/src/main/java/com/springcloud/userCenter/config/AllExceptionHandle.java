package com.springcloud.userCenter.config;

import com.wjf.github.commons.util.ResultTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AllExceptionHandle {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResultTemplate MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
		e.printStackTrace();
		if ("/user/login".equals(request.getRequestURI())) {
			return ResultTemplate.getFailResult("用户名密码不能为空！");
		}
		return ResultTemplate.getFailResult();
	}

}
