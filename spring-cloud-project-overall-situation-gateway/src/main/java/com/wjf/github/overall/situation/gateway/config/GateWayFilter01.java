package com.wjf.github.overall.situation.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Objects;

public class GateWayFilter01 implements GatewayFilter, Ordered {

	private final Logger logger = LoggerFactory.getLogger(GateWayFilter01.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		final ServerHttpRequest request = exchange.getRequest();
		final URI uri = request.getURI();
		final HttpHeaders headers = request.getHeaders();
		final List<String> allowHeaders = headers.getAccessControlAllowHeaders();
		for (String allowHeader : allowHeaders) {
			System.out.println(allowHeader);
		}
		logger.info(Objects.requireNonNull(exchange.getAttribute("cachedRequestBodyObject")).toString());
		logger.info("******请求地址:" + request.getURI().getHost() + "***请求路径:" + uri.getPath() + "******");
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
