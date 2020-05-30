package com.wjf.github.overall.situation.gateway.config;

import com.wjf.github.overall.situation.gateway.filter.UserTokenFilter;
import com.wjf.github.userinfoconsumerclient.service.RedisService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenAuthConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private RedisService redisService;

	private UserDetailsService userDetailsService;

	public TokenAuthConfigure(RedisService redisService, UserDetailsService userDetailsService) {
		this.redisService = redisService;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		UserTokenFilter userTokenFilter = new UserTokenFilter(userDetailsService, redisService);
		builder.addFilterBefore(userTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
