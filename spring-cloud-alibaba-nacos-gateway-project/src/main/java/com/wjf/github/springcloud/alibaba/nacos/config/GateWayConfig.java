package com.wjf.github.springcloud.alibaba.nacos.config;

import com.wjf.github.springcloud.alibaba.nacos.config.filters.GateWayFilter01;
import com.wjf.github.springcloud.alibaba.nacos.config.filters.GetGateWayFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Map;

@Slf4j
@SpringBootConfiguration
public class GateWayConfig {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		RouteLocatorBuilder.Builder routes = builder.routes();
		routes.route("spring-cloud-alibaba-project-nacos-comsuer-post-json", predicateSpec ->
			predicateSpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					.and()
					.path("/test/**")
					.and()
					.method(HttpMethod.POST)
					.and()
					.readBody(Map.class, map -> {
						log.info(map.toString());
						return true;
					})
					.and()
					.uri("lb://spring-cloud-alibaba-project-nacos-comsuer/")
					.filter(new GateWayFilter01())
		);
		routes.route("spring-cloud-alibaba-project-nacos-comsuer-post-form",predicateSpec -> {
			return predicateSpec.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE)
					.and()
					.method(HttpMethod.POST)
					.and()
					.path("/test/**")
					.uri("lb://spring-cloud-alibaba-project-nacos-comsuer/")
					.filter(new GateWayFilter01());
		});
		routes.route("spring-cloud-alibaba-project-nacos-comsuer-get",predicateSpec -> {
			return predicateSpec.method(HttpMethod.GET)
					.and()
					.path("/test/**")
					.uri("lb://spring-cloud-alibaba-project-nacos-comsuer/")
					.filter(new GetGateWayFilter());
		});
		return routes.build();
	}

}
