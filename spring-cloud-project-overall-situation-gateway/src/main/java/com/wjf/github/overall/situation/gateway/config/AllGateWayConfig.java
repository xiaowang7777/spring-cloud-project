package com.wjf.github.overall.situation.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllGateWayConfig {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
		final RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
		routes.route("SPRING-CLOUD-PROJECT-USERINFO-CONSUMER-CLIENT",predicateSpec ->
			predicateSpec.path("/userinfoConsumerClient/**")
					.uri("lb://spring-cloud-project-userinfo-consumer-client/")
					.filter(new GateWayFilter01())
		);
		return routes.build();
	}

}
