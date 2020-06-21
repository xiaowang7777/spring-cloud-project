package com.wjf.github.springcloud.alibaba.nacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

	private final static String [] exculdedAuthPath = {
			"/**/login/**",
			"/**/logout/**",
			"/health",
			"/api/socket"
	};

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security){
		security.authorizeExchange()
				.pathMatchers(exculdedAuthPath).permitAll()
				.pathMatchers(HttpMethod.GET).permitAll()
				.anyExchange().authenticated()
				.and()
				.httpBasic()
				.and()
				.logout().disable();
		return security.build();
	}

}
