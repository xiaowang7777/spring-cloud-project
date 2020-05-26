package com.wjf.github.overall.situation.gateway.config;

import com.wjf.github.commons.vo.gateway.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Configuration
public class AllGateWayConfig {

	private final static Logger logger = LoggerFactory.getLogger(AllGateWayConfig.class);

	/**
	 * 获取post请求中的数据
	 * 解决方案参考：  https://blog.51cto.com/thinklili/2329184?cid=725051
	 * 注意此时项目中不能导入tomcat相关的包
	 */
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		final RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
		routes.route("SPRING-CLOUD-PROJECT-USERINFO-CONSUMER-CLIENT-POST-JSON", predicateSpec ->
				predicateSpec.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.and()
						.method(HttpMethod.POST)
						.and()
						.readBody(UserInfo.class,s -> {
							logger.info(s.toString());
							return true;
						})
						.and()
						.path("/userinfoConsumerClient/**")
						.uri("lb://spring-cloud-project-userinfo-consumer-client/")
						.filter(new GateWayFilter01())
		);
		routes.route("SPRING-CLOUD-PROJECT-USERINFO-CONSUMER-CLIENT-POST-FORM",predicateSpec ->
				predicateSpec.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_FORM_URLENCODED_VALUE)
						.and()
						.method(HttpMethod.POST)
						.and()
						.readBody(String.class,s -> {
							logger.info(s);
							return true;
						})
						.and()
						.path("/userinfoConsumerClient/**")
						.uri("lb://spring-cloud-project-userinfo-consumer-client/")
						.filter(new GateWayFilter01())
		);
		return routes.build();
	}

}
